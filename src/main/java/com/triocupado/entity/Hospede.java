package com.triocupado.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hospede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer quantidade;

    private LocalDate dataCheckIn;

    private LocalDate dataCheckOut;

    private String formaDePagamento;

    @ManyToOne
    @JoinColumn(name = "quarto_id", nullable = true)
    private Quarto quarto;
}
