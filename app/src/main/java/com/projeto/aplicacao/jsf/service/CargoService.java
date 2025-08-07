package com.projeto.aplicacao.jsf.service;

import java.util.List;
import com.projeto.aplicacao.jsf.model.Cargo;
import com.projeto.aplicacao.jsf.repository.CargoRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class CargoService {
    
    @Inject
    private CargoRepository cargoRepository;

    public void saveCargo(Cargo cargo) {
        cargoRepository.save(cargo);
    }

    public void updateCargo(Cargo cargo) {
        cargoRepository.update(cargo);
    }

    public void deleteCargo(Cargo cargo) {
        cargoRepository.delete(cargo);
    }

    public Cargo findCargoById(Long id) {
        return cargoRepository.findById(id);
    }

    public List<Cargo> findAllCargos() {
        return cargoRepository.findAll();
    }

}
