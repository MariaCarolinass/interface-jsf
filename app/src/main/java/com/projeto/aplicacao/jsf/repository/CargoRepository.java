package com.projeto.aplicacao.jsf.repository;

import java.util.List;
import com.projeto.aplicacao.jsf.model.Cargo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class CargoRepository {
        
    @PersistenceContext
    private EntityManager em;

    public void save(Cargo cargo) {
        em.persist(cargo);
    }

    public void update(Cargo cargo) {
        em.merge(cargo);
    }

    public void delete(Cargo cargo) {
        em.remove(em.contains(cargo) ? cargo : em.merge(cargo));
    }

    public Cargo findById(Long id) {
        return em.find(Cargo.class, id);
    }

    public List<Cargo> findAll() {
        return em.createQuery("SELECT c FROM Cargo c", Cargo.class).getResultList();
    }

}
