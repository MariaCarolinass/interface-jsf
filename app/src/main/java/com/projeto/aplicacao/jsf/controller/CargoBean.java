package com.projeto.aplicacao.jsf.controller;

import java.io.Serializable;
import java.util.List;
import com.projeto.aplicacao.jsf.model.Cargo;
import com.projeto.aplicacao.jsf.service.CargoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class CargoBean implements Serializable {
    
    @Inject
    private CargoService cargoService;

    private static final long serialVersionUID = 1L;

    private Cargo cargo = new Cargo();
    private List<Cargo> cargos;

    @PostConstruct
    public void init() {
        cargos = cargoService.findAllCargos();
    }

    public void save() {
        if (cargo.getId() == null) {
            cargoService.saveCargo(cargo);
        } else {
            cargoService.updateCargo(cargo);
        }
        cargo = new Cargo();
        cargos = cargoService.findAllCargos();
    }

    public void delete(Cargo cargo) {
        cargoService.deleteCargo(cargo);
        cargos.remove(cargo);
    }

    public String edit(Cargo cargo) {
        this.cargo = cargo;
        return "/cargo/form.xhtml?faces-redirect=true";
    }

    public void clear() {
        cargo = new Cargo();
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

}
