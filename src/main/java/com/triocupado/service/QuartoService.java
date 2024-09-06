package com.triocupado.service;

import com.triocupado.entity.Hospede;
import com.triocupado.entity.Hotel;
import com.triocupado.entity.Quarto;
import com.triocupado.entity.Usuario;
import com.triocupado.entity.assemble.QuartoAssemble;
import com.triocupado.entity.dto.QuartoDTO;
import com.triocupado.entity.dto.ReservarQuartoDTO;
import com.triocupado.repository.HospedeRepository;
import com.triocupado.repository.QuartoRepository;
import com.triocupado.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.SendFailedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private QuartoAssemble quartoAssemble;

    @Autowired
    private NotificacaoService notificacaoService;

    @Autowired
    HospedeRepository hospedeRepository;

    @Autowired
    private HotelService hotelService;

    public List<QuartoDTO> listarTodosQuartos() {
        List<Quarto> quartos = quartoRepository.findAll();
        return quartos.stream()
                .map(quartoAssemble::convertToDTO)
                .collect(Collectors.toList());
    }

    public Quarto buscarQuartoPorId(Long id) {
        return quartoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Quarto não encontrado"));
    }

    public void reservarQuarto(ReservarQuartoDTO reservarQuartoDTO) {
        Quarto quarto = buscarQuartoPorId(reservarQuartoDTO.getId());
        Hospede hospedeHospedado = hospedeRepository.buscarHospedePorDataCheckIn(reservarQuartoDTO.getDataCheckIn());

        if (hospedeHospedado != null) {
            throw new IllegalArgumentException("Quarto já está reservado.");
        }

        Usuario usuario = usuarioRepository.findById(reservarQuartoDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Hotel hotel = hotelService.buscarPorId(reservarQuartoDTO.getHotelId());

        Hospede hospede = new Hospede();
        hospede.setDataCheckIn(reservarQuartoDTO.getDataCheckIn());
        hospede.setDataCheckOut(reservarQuartoDTO.getDataCheckOut());
        hospede.setQuantidade(reservarQuartoDTO.getQuantidadeHospede());
        hospede.setQuarto(quarto);
        hospede.setFormaDePagamento(usuario.getFormaDePagamento());
        hospede.setNome(usuario.getNome());

        Hospede hospedeSalvo = hospedeRepository.save(hospede);
        List<Hospede> hospedes = quarto.getHospedes();
        if (hospedes == null) {
            hospedes = new ArrayList<>();
        }
        hospedes.add(hospedeSalvo);

        quarto.setHospedes(hospedes);

        quartoRepository.save(quarto);

        try {
            notificacaoService.enviarEmail(
                    usuario.getEmail(),
                    reservarQuartoDTO.getDataCheckIn(),
                    reservarQuartoDTO.getDataCheckOut(),
                    hotel.getNome()
            );
        }catch (SendFailedException e){
            e.getStackTrace();
        }
    }

    public void liberarQuarto(Long hospedeId) {
        hospedeRepository.findById(hospedeId)
                .orElseThrow(() -> new IllegalArgumentException("Hospede não encontrado"));
        hospedeRepository.deleteById(hospedeId);
    }
}
