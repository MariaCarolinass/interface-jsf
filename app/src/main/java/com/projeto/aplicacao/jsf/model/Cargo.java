package com.projeto.aplicacao.jsf.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cargo")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;
    
    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pessoa> pessoas;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CargoVencimento> cargoVencimentos;

}
