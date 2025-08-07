package com.projeto.aplicacao.jsf.controller;

import java.io.Serializable;
import java.util.List;
import com.projeto.aplicacao.jsf.model.PessoaSalarioConsolidado;
import com.projeto.aplicacao.jsf.service.PessoaSalarioConsolidadoService;
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
public class PessoaSalarioConsolidadoBean implements Serializable {
    
    @Inject
    private PessoaSalarioConsolidadoService pessoaSalarioConsolidadoService;

    private static final long serialVersionUID = 1L;

    private PessoaSalarioConsolidado pessoaSalarioConsolidado;
    private List<PessoaSalarioConsolidado> pessoaSalarioConsolidados;

    @PostConstruct
    public void init() {
        pessoaSalarioConsolidados = pessoaSalarioConsolidadoService.findAllPessoaSalarioConsolidados();
    }

    public void save() {
        if (pessoaSalarioConsolidado.getId() == null) {
            pessoaSalarioConsolidadoService.savePessoaSalarioConsolidado(pessoaSalarioConsolidado);
        } else {
            pessoaSalarioConsolidadoService.updatePessoaSalarioConsolidado(pessoaSalarioConsolidado);
        }
        pessoaSalarioConsolidado = new PessoaSalarioConsolidado();
        pessoaSalarioConsolidados = pessoaSalarioConsolidadoService.findAllPessoaSalarioConsolidados();
    }

    public void delete(PessoaSalarioConsolidado pessoaSalarioConsolidado) {
        pessoaSalarioConsolidadoService.deletePessoaSalarioConsolidado(pessoaSalarioConsolidado);
        pessoaSalarioConsolidados.remove(pessoaSalarioConsolidado);
    }

    public void edit(PessoaSalarioConsolidado pessoaSalarioConsolidado) {
        this.pessoaSalarioConsolidado = pessoaSalarioConsolidado;
    }

    public void clear() {
        pessoaSalarioConsolidado = new PessoaSalarioConsolidado();
    }

    public List<PessoaSalarioConsolidado> getPessoaSalarioConsolidados() {
        return pessoaSalarioConsolidados;
    }

    public double getTotalSalario() {
        return pessoaSalarioConsolidadoService.findTotalSalaryByPersonId(pessoaSalarioConsolidado.getPessoaId());
    }

}
