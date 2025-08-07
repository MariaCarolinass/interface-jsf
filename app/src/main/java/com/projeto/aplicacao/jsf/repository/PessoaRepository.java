package com.projeto.aplicacao.jsf.repository;

import java.util.List;
import com.projeto.aplicacao.jsf.model.Pessoa;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PessoaRepository {
    
    @PersistenceContext
    private EntityManager em;

    public void save(Pessoa pessoa) {
        em.persist(pessoa);
    }

    public void update(Pessoa pessoa) {
        em.merge(pessoa);
    }

    public void delete(Pessoa pessoa) {
        em.remove(em.contains(pessoa) ? pessoa : em.merge(pessoa));
    }

    public Pessoa findById(Long id) {
        return em.find(Pessoa.class, id);
    }

    public List<Pessoa> findAll() {
        return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
    }

}