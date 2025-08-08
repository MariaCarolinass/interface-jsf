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

    public void save(PessoaSalarioConsolidado psc) {
        em.persist(psc);
    }

    public void update(PessoaSalarioConsolidado psc) {
        em.merge(psc);
    }

    public void delete(PessoaSalarioConsolidado psc) {
        em.remove(em.contains(psc) ? psc : em.merge(psc));
    }

    public PessoaSalarioConsolidado findById(Long id) {
        return em.find(PessoaSalarioConsolidado.class, id);
    }

    public List<PessoaSalarioConsolidado> findAll() {
        return em.createQuery("SELECT p FROM PessoaSalarioConsolidado p", PessoaSalarioConsolidado.class).getResultList();
    }

    public Double findTotalSalaryByPersonId(Long personId) {
        return em.createQuery("SELECT SUM(p.salario) FROM PessoaSalarioConsolidado p WHERE p.pessoaId = :personId", Double.class)
                 .setParameter("personId", personId)
                 .getSingleResult();
    }

}
