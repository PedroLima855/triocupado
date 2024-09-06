package com.triocupado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.SendFailedException;
import java.time.LocalDate;

@Service
public class NotificacaoService {

    @Autowired
    private JavaMailSender emailSender;

    public void enviarEmail(String para, LocalDate checkIn, LocalDate checkOut, String nomeHotel) throws SendFailedException {

        try {
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setFrom("Triocupado <enviarnotificacaoteste@gmail.com>");
            mensagem.setTo(para);
            mensagem.setSubject("Reserva Realizada no " + nomeHotel);
            mensagem.setText("Parabéns, sua reserva foi realizada com Sucesso! " +
                    "Seu checkIn será feito em " + checkIn + " podendo aproveitar até o dia do checkout em " + checkOut);

            emailSender.send(mensagem);
        } catch (Exception e){
            throw new SendFailedException("Falha ao enviar email");
        }
    }

}
