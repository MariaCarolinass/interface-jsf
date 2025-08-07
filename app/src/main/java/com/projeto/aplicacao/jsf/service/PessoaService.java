package com.projeto.aplicacao.jsf.service;

import java.util.List;
import com.projeto.aplicacao.jsf.model.Pessoa;
import com.projeto.aplicacao.jsf.model.Usuario;
import com.projeto.aplicacao.jsf.repository.PessoaRepository;
import com.projeto.aplicacao.jsf.repository.UsuarioRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class PessoaService {
    
    @Inject
    private PessoaRepository pessoaRepository;

    @Inject
    private UsuarioRepository usuarioRepository;

    public void savePessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    public void updatePessoa(Pessoa pessoa) {
        pessoaRepository.update(pessoa);
    }

    public void deletePessoa(Pessoa pessoa) {
        pessoaRepository.delete(pessoa);
    }

    public Pessoa findPessoaById(Long id) {
        return pessoaRepository.findById(id);
    }

    public List<Pessoa> findAllPessoas() {
        return pessoaRepository.findAll();
    }

    public Usuario buscarUsuarioPorLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

}
