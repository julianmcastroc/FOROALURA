package com.finalboss.foroalura.domain.topico.dto;

import com.finalboss.foroalura.domain.curso.Categoria;
import com.finalboss.foroalura.domain.topico.Estado;
import com.finalboss.foroalura.domain.topico.Topico;

import java.time.LocalDateTime;

public record DetallesTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        LocalDateTime ultimaActualizacion,
        Estado estado,
        String usuario,
        String curso,
        Categoria categoriaCurso

) {

    public DetallesTopicoDTO(Topico topico) {
        this(topico.getId(),
            topico.getTitulo(),
            topico.getMensaje(),
            topico.getFechaCreacion(),
            topico.getUltimaActualizacion(),
            topico.getEstado(),
            topico.getUsuario().getUsername(),
            topico.getCurso().getName(),
            topico.getCurso().getCategoria()
                );
    }
}
