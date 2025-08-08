package com.projeto.aplicacao.jsf.controller;

import java.io.Serializable;
import java.util.List;
import com.projeto.aplicacao.jsf.model.Vencimento;
import com.projeto.aplicacao.jsf.service.VencimentoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class VencimentoBean implements Serializable {

    @Inject
    private VencimentoService vencimentoService;

    private static final long serialVersionUID = 1L;
    
    private Vencimento vencimento = new Vencimento();
    private List<Vencimento> vencimentos;

    @PostConstruct
    public void init() {
        vencimentos = vencimentoService.findAllVencimentos();
    }

    public void save() {
        if (vencimento.getId() == null) {
            vencimentoService.saveVencimento(vencimento);
        } else {
            vencimentoService.updateVencimento(vencimento);
        }
        vencimento = new Vencimento();
        vencimentos = vencimentoService.findAllVencimentos();
    }

    public void delete(Vencimento vencimento) {
        vencimentoService.deleteVencimento(vencimento);
        vencimentos.remove(vencimento);
    }

    public String edit(Vencimento vencimento) {
        this.vencimento = vencimento;
        return "/vencimento/form.xhtml?faces-redirect=true";
    }

    public void clear() {
        vencimento = new Vencimento();
    }

    public List<Vencimento> getVencimentos() {
        return vencimentos;
    }

}
