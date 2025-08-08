package com.projeto.aplicacao.jsf.controller;

import java.io.Serializable;
import java.util.List;
import com.projeto.aplicacao.jsf.model.Pessoa;
import com.projeto.aplicacao.jsf.service.PessoaService;
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
public class PessoaBean implements Serializable {
    
    @Inject
    private PessoaService pessoaService;

    private static final long serialVersionUID = 1L;

    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas;

    @PostConstruct
    public void init() {
        pessoas = pessoaService.findAllPessoas();
    }

    public void save() {
        if (pessoa.getId() == null) {
            pessoaService.savePessoa(pessoa);
        } else {
            pessoaService.updatePessoa(pessoa);
        }
        pessoa = new Pessoa();
        pessoas = pessoaService.findAllPessoas();
    }

    public void delete(Pessoa pessoa) {
        pessoaService.deletePessoa(pessoa);
        pessoas.remove(pessoa);
    }

    public String edit(Pessoa pessoa) {
        this.pessoa = pessoa;
        return "/pessoa/form.xhtml?faces-redirect=true";
    }

    public void clear() {
        pessoa = new Pessoa();
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

}
