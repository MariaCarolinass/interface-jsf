package com.projeto.aplicacao.jsf.service;

import java.util.List;
import com.projeto.aplicacao.jsf.model.PessoaSalarioConsolidado;
import com.projeto.aplicacao.jsf.repository.PessoaSalarioConsolidadoRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class PessoaSalarioConsolidadoService {
    
    @Inject
    private PessoaSalarioConsolidadoRepository pessoaSalarioConsolidadoRepository;

    public void savePessoaSalarioConsolidado(PessoaSalarioConsolidado pessoaSalarioConsolidado) {
        pessoaSalarioConsolidadoRepository.save(pessoaSalarioConsolidado);
    }

    public void updatePessoaSalarioConsolidado(PessoaSalarioConsolidado pessoaSalarioConsolidado) {
        pessoaSalarioConsolidadoRepository.update(pessoaSalarioConsolidado);
    }

    public void deletePessoaSalarioConsolidado(PessoaSalarioConsolidado pessoaSalarioConsolidado) {
        pessoaSalarioConsolidadoRepository.delete(pessoaSalarioConsolidado);
    }

    public PessoaSalarioConsolidado findPessoaSalarioConsolidadoById(Long id) {
        return pessoaSalarioConsolidadoRepository.findById(id);
    }

    public List<PessoaSalarioConsolidado> findAllPessoaSalarioConsolidados() {
        return pessoaSalarioConsolidadoRepository.findAll();
    }

    public Double findTotalSalaryByPersonId(Long personId) {
        return pessoaSalarioConsolidadoRepository.findTotalSalaryByPersonId(personId);
    }

}
