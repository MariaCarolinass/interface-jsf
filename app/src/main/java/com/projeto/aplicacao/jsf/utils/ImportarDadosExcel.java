package com.projeto.aplicacao.jsf.utils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.projeto.aplicacao.jsf.model.Cargo;
import com.projeto.aplicacao.jsf.model.CargoVencimento;
import com.projeto.aplicacao.jsf.model.Pessoa;
import com.projeto.aplicacao.jsf.model.TipoVencimento;
import com.projeto.aplicacao.jsf.model.Usuario;
import com.projeto.aplicacao.jsf.model.Vencimento;
import com.projeto.aplicacao.jsf.service.CargoService;
import com.projeto.aplicacao.jsf.service.CargoVencimentoService;
import com.projeto.aplicacao.jsf.service.PessoaService;
import com.projeto.aplicacao.jsf.service.UsuarioService;
import com.projeto.aplicacao.jsf.service.VencimentoService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ImportarDadosExcel {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private PessoaService pessoaService;

    @Inject
    private CargoService cargoService;

    @Inject
    private VencimentoService vencimentoService;

    @Inject
    private CargoVencimentoService cargoVencimentoService;

    public void importarDados(String caminhoArquivo) {
        try (FileInputStream file = new FileInputStream(new File(caminhoArquivo));
            Workbook workbook = new XSSFWorkbook(file)) {

            importarUsuarios(workbook.getSheet("Pessoa"));
            importarPessoas(workbook.getSheet("Pessoa"));
            importarCargos(workbook.getSheet("Cargo"));
            importarVencimentos(workbook.getSheet("Vencimentos"));
            importarCargoVencimentos(workbook.getSheet("Cargo_Vencimentos"));

            System.out.println("Importação concluída com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void importarUsuarios(Sheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String usuarioLogin = row.getCell(7).getStringCellValue();
            
            Usuario usuario = Usuario.builder()
                    .login(usuarioLogin)
                    .senha(null)
                    .build();

            usuarioService.saveUsuario(usuario);
        }
    }

    private void importarPessoas(Sheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String nome = row.getCell(1).getStringCellValue();
            String cidade = row.getCell(2).getStringCellValue();
            String email = row.getCell(3).getStringCellValue();
            String cep = row.getCell(4).getStringCellValue();
            String endereco = row.getCell(5).getStringCellValue();
            String pais = row.getCell(6).getStringCellValue();
            String usuarioLogin = row.getCell(7).getStringCellValue();
            String telefone = row.getCell(8).getStringCellValue();
            String dataNascimento = row.getCell(9).getStringCellValue();
            String cargoId = row.getCell(10).getStringCellValue();

            Usuario usuario = pessoaService.buscarUsuarioPorLogin(usuarioLogin);

            Pessoa pessoa = Pessoa.builder()
                    .nome(nome)
                    .cidade(cidade)
                    .email(email)
                    .cep(cep)
                    .endereco(endereco)
                    .pais(pais)
                    .usuario(usuario)
                    .telefone(telefone)
                    .dataNascimento(java.sql.Date.valueOf(dataNascimento))
                    .cargo(cargoService.findCargoById(Long.parseLong(cargoId)))
                    .build();

            pessoaService.savePessoa(pessoa);
        }
    }

    private void importarCargos(Sheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String nomeCargo = row.getCell(1).getStringCellValue();

            Cargo cargo = Cargo.builder()
                    .nome(nomeCargo)
                    .build();

            cargoService.saveCargo(cargo);
        }
    }

    private void importarVencimentos(Sheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String descricao = row.getCell(1).getStringCellValue();
            BigDecimal valor = new BigDecimal(row.getCell(2).getNumericCellValue());
            TipoVencimento tipoVencimento = TipoVencimento.valueOf(row.getCell(3).getStringCellValue());

            Vencimento vencimento = Vencimento.builder()
                    .descricao(descricao)
                    .valor(valor)
                    .tipo(tipoVencimento)
                    .build();

            vencimentoService.saveVencimento(vencimento);
        }
    }

    private void importarCargoVencimentos(Sheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String cargoId = row.getCell(1).getStringCellValue();
            String vencimentoId = row.getCell(2).getStringCellValue();

            Cargo cargo = cargoService.findCargoById(Long.parseLong(cargoId));
            Vencimento vencimento = vencimentoService.findVencimentoById(Long.parseLong(vencimentoId));

            CargoVencimento cargoVencimento = CargoVencimento.builder()
                    .cargo(cargo)
                    .vencimento(vencimento)
                    .build();

            cargoVencimentoService.saveCargoVencimento(cargoVencimento);
        }
    }

}
