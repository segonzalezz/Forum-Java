package org.example.service;

import org.example.model.Topico;
import org.example.repository.TopicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoService {
    private final TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public void crearTopico(Topico topico) {
        if (topico.getTitulo() == null || topico.getMensaje() == null) {
            throw new IllegalArgumentException("El título y el mensaje son obligatorios");
        }
        topico.setFechaCreacion(LocalDateTime.now());
        topicoRepository.save(topico);
    }


    public List<Topico> obtenerTodosLosTopicos() {
        return topicoRepository.findAll();
    }

    public List<Topico> obtenerTopicosPorUsuario(Long usuarioId) {
        return topicoRepository.findByAutorId(usuarioId);
    }
}
