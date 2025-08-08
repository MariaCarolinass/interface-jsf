package com.projeto.aplicacao.jsf.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import com.projeto.aplicacao.jsf.service.SalarioService;
import com.projeto.aplicacao.jsf.model.PessoaSalarioConsolidado;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;

@Named
@ViewScoped
public class SalarioBean implements Serializable {

    @Inject
    private SalarioService salarioService;

    private boolean processando = false;
    private List<PessoaSalarioConsolidado> pessoaSalarioConsolidados;

    public void calcularSalarios() {
        processando = true;
        salarioService.calcularSalarios();
        pessoaSalarioConsolidados = salarioService.listarSalarios();

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("Cálculo de salários iniciado. Aguarde alguns segundos."));
    }

    public boolean isProcessando() {
        return processando;
    }

    public List<PessoaSalarioConsolidado> getPessoaSalarioConsolidados() {
        if (pessoaSalarioConsolidados == null) {
            pessoaSalarioConsolidados = salarioService.listarSalarios();
        }
        return pessoaSalarioConsolidados;
    }

    public void exportarPDF() {
        try {
            byte[] pdf = salarioService.gerarRelatorioSalariosPDF();

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"salarios.pdf\"");
            response.getOutputStream().write(pdf);
            response.getOutputStream().flush();
            facesContext.responseComplete();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao exportar relatório em PDF", e);
        }
    }

}
