package com.projeto.aplicacao.jsf.controller;

import com.projeto.aplicacao.jsf.config.PasswordEncoder;
import com.projeto.aplicacao.jsf.model.Usuario;
import com.projeto.aplicacao.jsf.service.UsuarioService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class DefinirSenhaBean {

    private String login;
    private String senha;
    private String confirmacao;

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private PasswordEncoder passwordEncoder;

    public void definirSenha() {
        if (!senha.equals(confirmacao)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas não coincidem", null));
            return;
        }

        Usuario usuario = usuarioService.buscarPorLogin(login);
        if (usuario == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado", null));
            return;
        }

        usuario.setSenha(passwordEncoder.encode(senha));
        usuarioService.updateUsuario(usuario);

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha definida com sucesso!", null));
    }

}
