package com.projeto.aplicacao.jsf.service;

import java.util.List;
import com.projeto.aplicacao.jsf.model.Vencimento;
import com.projeto.aplicacao.jsf.repository.VencimentoRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class VencimentoService {
    
    @Inject
    private VencimentoRepository vencimentoRepository;

    public void saveVencimento(Vencimento vencimento) {
        vencimentoRepository.save(vencimento);
    }

    public void updateVencimento(Vencimento vencimento) {
        vencimentoRepository.update(vencimento);
    }

    public void deleteVencimento(Vencimento vencimento) {
        vencimentoRepository.delete(vencimento);
    }

    public Vencimento findVencimentoById(Long id) {
        return vencimentoRepository.findById(id);
    }

    public List<Vencimento> findAllVencimentos() {
        return vencimentoRepository.findAll();
    }

}
