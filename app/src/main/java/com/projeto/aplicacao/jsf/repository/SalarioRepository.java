package com.projeto.aplicacao.jsf.repository;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import com.projeto.aplicacao.jsf.model.Pessoa;
import com.projeto.aplicacao.jsf.model.PessoaSalarioConsolidado;
import com.projeto.aplicacao.jsf.model.Vencimento;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Stateless
public class SalarioRepository {

    @PersistenceContext
    private EntityManager em;

    @PersistenceContext
    private PessoaSalarioConsolidadoRepository pessoaSalarioConsolidadoRepository;

    @Asynchronous
    public void calcularSalarios() {
        List<Pessoa> pessoas = em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();

        for (Pessoa pessoa : pessoas) {
            BigDecimal salario = calcularSalarioPessoa(pessoa);
            salvarConsolidado(pessoa, salario);
        }
    }

    private BigDecimal calcularSalarioPessoa(Pessoa pessoa) {
        BigDecimal salario = BigDecimal.ZERO;

        List<Vencimento> vencimentos = em.createQuery(
                "SELECT v FROM CargoVencimentos cv JOIN cv.vencimento v WHERE cv.cargo.id = :cargoId",
                Vencimento.class)
                .setParameter("cargoId", pessoa.getCargo().getId())
                .getResultList();

        for (Vencimento v : vencimentos) {
            if ("CREDITO".equalsIgnoreCase(v.getTipo().toString())) {
                salario = salario.add(v.getValor());
            } else if ("DEBITO".equalsIgnoreCase(v.getTipo().toString())) {
                salario = salario.subtract(v.getValor());
            }
        }

        return salario;
    }

    private void salvarConsolidado(Pessoa pessoa, BigDecimal salario) {
        PessoaSalarioConsolidado consolidado = new PessoaSalarioConsolidado();
        consolidado.setPessoaId(pessoa.getId());
        consolidado.setNomePessoa(pessoa.getNome());
        consolidado.setNomeCargo(pessoa.getCargo().getNome());
        consolidado.setSalario(salario);

        em.merge(consolidado);
    }

    public byte[] gerarRelatorioSalariosPDF() {
        try {
            List<PessoaSalarioConsolidado> dados = pessoaSalarioConsolidadoRepository.findAll();

            InputStream input = getClass().getResourceAsStream("/reports/salarios_report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(input);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório de salários", e);
        }
    }
    
    public List<PessoaSalarioConsolidado> listarSalarios() {
        return pessoaSalarioConsolidadoRepository.findAll();
    }
}
