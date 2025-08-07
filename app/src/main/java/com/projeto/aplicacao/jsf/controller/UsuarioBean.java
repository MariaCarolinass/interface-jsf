package com.projeto.aplicacao.jsf.controller;

import java.io.Serializable;
import java.util.List;
import com.projeto.aplicacao.jsf.model.Usuario;
import com.projeto.aplicacao.jsf.service.UsuarioService;
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
public class UsuarioBean  implements Serializable {

    @Inject
    private UsuarioService usuarioService;

    private static final long serialVersionUID = 1L;

    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios;

    @PostConstruct
    public void init() {
        usuarios = usuarioService.findAllUsuarios();
    }

    public void save() {
        if (usuario.getId() == null) {
            usuarioService.saveUsuario(usuario);
        } else {
            usuarioService.updateUsuario(usuario);
        }
        usuario = new Usuario();
        usuarios = usuarioService.findAllUsuarios();
    }

    public void delete(Usuario usuario) {
        usuarioService.deleteUsuario(usuario);
        usuarios.remove(usuario);
    }

    public void edit(Usuario usuario) {
        this.usuario = usuario;
    }

    public void clear() {
        usuario = new Usuario();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
}
