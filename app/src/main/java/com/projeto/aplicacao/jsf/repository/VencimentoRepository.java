package com.projeto.aplicacao.jsf.repository;

import java.util.List;
import com.projeto.aplicacao.jsf.model.Vencimento;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class VencimentoRepository {
    
    @PersistenceContext
    private EntityManager em;

    public void save(Vencimento vencimento) {
        em.persist(vencimento);
    }

    public void update(Vencimento vencimento) {
        em.merge(vencimento);
    }

    public void delete(Vencimento vencimento) {
        em.remove(em.contains(vencimento) ? vencimento : em.merge(vencimento));
    }

    public Vencimento findById(Long id) {
        return em.find(Vencimento.class, id);
    }

    public List<Vencimento> findAll() {
        return em.createQuery("SELECT v FROM Vencimento v", Vencimento.class).getResultList();
    }

}
