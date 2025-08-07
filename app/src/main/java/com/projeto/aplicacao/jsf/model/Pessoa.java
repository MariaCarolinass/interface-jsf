package com.projeto.aplicacao.jsf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pessoa")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "cep")
    private String cep;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "pais")
    private String pais;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @OneToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

}
