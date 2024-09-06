package com.triocupado.controller;

import com.triocupado.entity.Quarto;
import com.triocupado.entity.dto.QuartoDTO;
import com.triocupado.entity.dto.ReservarQuartoDTO;
import com.triocupado.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartoController {

    @Autowired
    private QuartoService quartoService;

    @GetMapping
    public List<QuartoDTO> listarTodosQuartos() {
        return quartoService.listarTodosQuartos();
    }

    @PostMapping("/reservar")
    public ResponseEntity<String> reservarQuarto(@RequestBody ReservarQuartoDTO reservarQuartoDTO) {
        try {
            quartoService.reservarQuarto(reservarQuartoDTO);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.created(URI.create("/quartos")).build();
    }

    @DeleteMapping("/liberar/{hospedeId}")
    public ResponseEntity<String> liberarQuarto(@PathVariable Long hospedeId) {
        try {
            quartoService.liberarQuarto(hospedeId);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }
}
