package com.triocupado.controller;

import com.triocupado.entity.dto.HotelDTO;
import com.triocupado.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hoteis")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<HotelDTO> listarTodos() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/comparar")
    public List<HotelDTO> compararHoteis(
            @RequestParam(required = false) String localizacao,
            @RequestParam(required = false) Double avaliacao) {
        return hotelService.compararHoteis(localizacao, avaliacao);
    }

    @GetMapping("/filtrar")
    public List<HotelDTO> filtrarHoteis(
            @RequestParam(required = false) String localizacao,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(required = false) LocalDate dataCheckIn,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(required = false) LocalDate dataCheckOut,
            @RequestParam(required = false) Integer numeroHospedes) {
        return hotelService.filtrarHoteis(
                localizacao,
                dataCheckIn,
                dataCheckOut,
                numeroHospedes);
    }
}
