package com.projeto.aplicacao.jsf.repository;

import java.util.List;
import com.projeto.aplicacao.jsf.model.CargoVencimento;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class CargoVencimentoRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(CargoVencimento cargoVencimento) {
        em.persist(cargoVencimento);
    }

    public void update(CargoVencimento cargoVencimento) {
        em.merge(cargoVencimento);
    }

    public void delete(CargoVencimento cargoVencimento) {
        em.remove(em.contains(cargoVencimento) ? cargoVencimento : em.merge(cargoVencimento));
    }

    public CargoVencimento findById(Long id) {
        return em.find(CargoVencimento.class, id);
    }

    public List<CargoVencimento> findAll() {
        return em.createQuery("SELECT cv FROM CargoVencimento cv", CargoVencimento.class).getResultList();
    }

}
