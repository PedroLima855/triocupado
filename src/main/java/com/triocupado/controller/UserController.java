package com.triocupado.controller;

import com.triocupado.entity.dto.AtualizarEmailDTO;
import com.triocupado.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;

    @PutMapping()
    public ResponseEntity<String> atualizarEmailUsuario(@RequestBody AtualizarEmailDTO atualizarEmailDTO) {
        try {
            usuarioService.atualizarEmailUsuario(atualizarEmailDTO);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }
}
