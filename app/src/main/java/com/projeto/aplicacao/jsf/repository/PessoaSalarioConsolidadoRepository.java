package com.projeto.aplicacao.jsf.repository;

import java.util.List;
import com.projeto.aplicacao.jsf.model.PessoaSalarioConsolidado;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PessoaSalarioConsolidadoRepository {
    
    @PersistenceContext
    private EntityManager em;

    public void save(PessoaSalarioConsolidado pessoaSalarioConsolidado) {
        em.persist(pessoaSalarioConsolidado);
    }

    public void update(PessoaSalarioConsolidado pessoaSalarioConsolidado) {
        em.merge(pessoaSalarioConsolidado);
    }

    public void delete(PessoaSalarioConsolidado pessoaSalarioConsolidado) {
        em.remove(em.contains(pessoaSalarioConsolidado) ? pessoaSalarioConsolidado : em.merge(pessoaSalarioConsolidado));
    }

    public PessoaSalarioConsolidado findById(Long id) {
        return em.find(PessoaSalarioConsolidado.class, id);
    }

    public List<PessoaSalarioConsolidado> findAll() {
        return em.createQuery("SELECT p FROM PessoaSalarioConsolidado p", PessoaSalarioConsolidado.class).getResultList();
    }

    public Double findTotalSalaryByPersonId(Long personId) {
        return em.createQuery("SELECT SUM(p.salary) FROM PessoaSalarioConsolidado p WHERE p.person.id = :personId", Double.class)
                 .setParameter("personId", personId)
                 .getSingleResult();
    }

}
