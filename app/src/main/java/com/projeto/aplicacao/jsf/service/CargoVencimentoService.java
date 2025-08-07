package com.projeto.aplicacao.jsf.service;

import java.util.List;
import com.projeto.aplicacao.jsf.model.CargoVencimento;
import com.projeto.aplicacao.jsf.repository.CargoVencimentoRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class CargoVencimentoService {
    
    @Inject
    private CargoVencimentoRepository cargoVencimentoRepository;

    public void saveCargoVencimento(CargoVencimento cargoVencimento) {
        cargoVencimentoRepository.save(cargoVencimento);
    }

    public void updateCargoVencimento(CargoVencimento cargoVencimento) {
        cargoVencimentoRepository.update(cargoVencimento);
    }

    public void deleteCargoVencimento(CargoVencimento cargoVencimento) {
        cargoVencimentoRepository.delete(cargoVencimento);
    }

    public CargoVencimento findCargoVencimentoById(Long id) {
        return cargoVencimentoRepository.findById(id);
    }

    public List<CargoVencimento> findAllCargoVencimentos() {
        return cargoVencimentoRepository.findAll();
    }

}
