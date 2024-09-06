package com.triocupado.entity.assemble;

import com.triocupado.entity.Quarto;
import com.triocupado.entity.dto.QuartoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartoAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public QuartoDTO convertToDTO(Quarto quarto) {
        QuartoDTO quartoDTO = modelMapper.map(quarto, QuartoDTO.class);
        quartoDTO.setHotelId(quarto.getHotel().getId());
        quartoDTO.setUsuario(quarto.getUsuario());
        quartoDTO.setDataCheckIn(quarto.getDataCheckIn());
        quartoDTO.setDataCheckOut(quarto.getDataCheckOut());
        return quartoDTO;
    }
}
