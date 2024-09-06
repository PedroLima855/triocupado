package com.triocupado.service;

import com.triocupado.entity.Hotel;
import com.triocupado.entity.Quarto;
import com.triocupado.entity.assemble.HotelAssemble;
import com.triocupado.entity.dto.HotelDTO;
import com.triocupado.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelAssemble hotelAssemble;

    public List<HotelDTO> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .map(hotelAssemble::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HotelDTO> filtrarHoteis(
            String localizacao,
            LocalDate dataCheckIn,
            LocalDate dataCheckOut,
            Integer numeroHospedes
    ) {
        List<Hotel> hotels = hotelRepository.filtrarHoteis(
                localizacao,
                dataCheckIn,
                dataCheckOut,
                numeroHospedes
        );
        return hotels.stream()
                .map(hotelAssemble::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HotelDTO> compararHoteis(String localizacao, Double avaliacao) {
        List<Hotel> hoteis = filtrarHoteisPorPrecoEAvaliacao(hotelRepository.buscarHoteisPorLocalizacao(localizacao), avaliacao);
        return hoteis.stream()
                .map(hotelAssemble::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<Hotel> filtrarHoteisPorPrecoEAvaliacao(List<Hotel> hoteis, Double avaliacao) {
        return hoteis.stream()
                .filter(hotel -> hotel.getAvaliacao() >= avaliacao)
                .map(hotel -> {
                    List<Quarto> quartosOrdenados = hotel.getQuartos().stream()
                            .sorted(Comparator.comparing(Quarto::getPreco))
                            .collect(Collectors.toList());

                    return new Hotel(
                            hotel.getId(),
                            hotel.getNome(),
                            hotel.getDescricao(),
                            hotel.getAvaliacao(),
                            hotel.getLocalizacao(),
                            quartosOrdenados
                    );
                })
                .collect(Collectors.toList());
    }

    public Hotel buscarPorId(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel não encontrado"));
    }
}
