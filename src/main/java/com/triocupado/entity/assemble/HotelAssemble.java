package com.triocupado.entity.assemble;

import com.triocupado.entity.Hotel;
import com.triocupado.entity.dto.HotelDTO;
import com.triocupado.entity.dto.QuartoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class HotelAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public HotelDTO convertToDTO(Hotel hotel) {
        HotelDTO hotelDTO = modelMapper.map(hotel, HotelDTO.class);
        hotelDTO.setQuartos(hotel.getQuartos().stream()
                .map(quarto -> modelMapper.map(quarto, QuartoDTO.class))
                .collect(Collectors.toList()));
        return hotelDTO;
    }
}
