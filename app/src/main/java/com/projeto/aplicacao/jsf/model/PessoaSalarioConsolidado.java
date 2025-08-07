package com.projeto.aplicacao.jsf.model;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pessoa_salario_consolidado")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaSalarioConsolidado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pessoa_id")
    private Long pessoaId;

    @Column(name = "nome_pessoa")
    private String nomePessoa;

    @Column(name = "nome_cargo")
    private String nomeCargo;

    @Column(name = "salario")
    private BigDecimal salario;

}
