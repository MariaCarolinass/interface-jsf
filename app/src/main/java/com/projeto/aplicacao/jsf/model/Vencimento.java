package com.projeto.aplicacao.jsf.model;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "vencimento")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vencimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoVencimento tipo;

    @OneToMany(mappedBy = "vencimento")
    private List<CargoVencimento> cargoVencimentos;
    
}
