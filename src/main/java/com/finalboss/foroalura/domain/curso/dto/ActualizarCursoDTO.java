package com.finalboss.foroalura.domain.curso.dto;

import com.finalboss.foroalura.domain.curso.Categoria;

public record ActualizarCursoDTO(
        String name,
        Categoria categoria,
        Boolean activo) {
}
