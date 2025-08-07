package com.projeto.aplicacao.jsf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cargo_vencimento")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CargoVencimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "vencimento_id")
    private Vencimento vencimento;

}
