package com.triocupado.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double avaliacao;
    private String localizacao;
    private List<QuartoDTO> quartos;
}
