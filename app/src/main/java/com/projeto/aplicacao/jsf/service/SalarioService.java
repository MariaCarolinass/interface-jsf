package com.projeto.aplicacao.jsf.service;

import com.projeto.aplicacao.jsf.repository.SalarioRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class SalarioService {
    
    @Inject
    private SalarioRepository salarioRepository;

    public void calcularSalarios() {
        salarioRepository.calcularSalarios();
    }

    public byte[] gerarRelatorioSalariosPDF() {
        return salarioRepository.gerarRelatorioSalariosPDF();
    }

}
