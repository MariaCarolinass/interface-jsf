package com.projeto.aplicacao.jsf.repository;

import java.util.List;
import com.projeto.aplicacao.jsf.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UsuarioRepository {
    
    @PersistenceContext
    private EntityManager em;

    public void save(Usuario usuario) {
        em.persist(usuario);
    }

    public void update(Usuario usuario) {
        em.merge(usuario);
    }

    public void delete(Usuario usuario) {
        em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
    }

    public Usuario findById(Long id) {
        return em.find(Usuario.class, id);
    }

    public List<Usuario> findAll() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    public Usuario findByLogin(String login) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login", Usuario.class)
                     .setParameter("login", login)
                     .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
