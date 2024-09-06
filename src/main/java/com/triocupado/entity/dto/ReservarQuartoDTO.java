package com.triocupado.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservarQuartoDTO {

    private Long id;
    private Long userId;
    private Long hotelId;
    private Integer quantidadeHospede;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
}
