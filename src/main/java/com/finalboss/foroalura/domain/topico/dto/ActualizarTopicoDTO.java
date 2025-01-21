package com.finalboss.foroalura.domain.topico.dto;

import com.finalboss.foroalura.domain.topico.Estado;

public record ActualizarTopicoDTO(
        String titulo,
        String mensaje,
        Estado estado,
        Long cursoId
) {
}
