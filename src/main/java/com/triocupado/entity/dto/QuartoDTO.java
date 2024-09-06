package com.triocupado.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.triocupado.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuartoDTO {

    private Long id;

    private String descricao;

    private LocalDate dataCheckIn;

    private LocalDate dataCheckOut;

    private Double avaliacao;

    private Double preco;

    private Long hotelId;

    private Boolean disponivel;

    private Usuario usuario;
}
