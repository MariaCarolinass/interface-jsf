package com.projeto.aplicacao.jsf.service;

import java.util.List;
import com.projeto.aplicacao.jsf.model.Usuario;
import com.projeto.aplicacao.jsf.repository.UsuarioRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class UsuarioService {
    
    @Inject
    private UsuarioRepository usuarioRepository;

    public void saveUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void updateUsuario(Usuario usuario) {
        usuarioRepository.update(usuario);
    }

    public void deleteUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

}
