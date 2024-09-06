package com.triocupado.service;

import com.triocupado.entity.Usuario;
import com.triocupado.entity.dto.AtualizarEmailDTO;
import com.triocupado.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void atualizarEmailUsuario(AtualizarEmailDTO atualizarEmailDTO){
        Usuario usuario = usuarioRepository.findById(atualizarEmailDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
        usuario.setEmail(atualizarEmailDTO.getEmail());
        usuarioRepository.save(usuario);
    }

}
