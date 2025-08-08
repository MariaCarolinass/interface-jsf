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

    private PessoaSalarioConsolidado pessoaSalarioConsolidado;
    private List<PessoaSalarioConsolidado> pessoaSalarioConsolidados;

    @PostConstruct
    public void init() {
        pessoaSalarioConsolidados = pessoaSalarioConsolidadoService.findAllPessoaSalarioConsolidados();
        pessoaSalarioConsolidado = new PessoaSalarioConsolidado();
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

    public void delete(PessoaSalarioConsolidado psc) {
        pessoaSalarioConsolidadoService.deletePessoaSalarioConsolidado(psc);
        pessoaSalarioConsolidados.remove(psc);
    }

    public void edit(PessoaSalarioConsolidado psc) {
        this.pessoaSalarioConsolidado = psc;
    }

    public void clear() {
        pessoaSalarioConsolidado = new PessoaSalarioConsolidado();
    }

    public double getTotalSalario() {
        return pessoaSalarioConsolidadoService.findTotalSalaryByPersonId(pessoaSalarioConsolidado.getPessoaId());
    }
    
}