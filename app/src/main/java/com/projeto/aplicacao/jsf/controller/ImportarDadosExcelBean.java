package com.projeto.aplicacao.jsf.controller;

import java.io.Serializable;
import com.projeto.aplicacao.jsf.utils.ImportarDadosExcel;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class ImportarDadosExcelBean implements Serializable  {
    
    @Inject
    private ImportarDadosExcel importarDadosExcel;

    private static final long serialVersionUID = 1L;

    private String caminhoArquivo;

    public void importar() {
        try {
            importarDadosExcel.importarDados(caminhoArquivo);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Importação realizada com sucesso!", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na importação: " + e.getMessage(), null));
        }
    }

}
