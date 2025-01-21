package com.finalboss.foroalura.domain.curso.dto;

import com.finalboss.foroalura.domain.curso.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearCursoDTO(
        @NotBlank String name,
        @NotNull Categoria categoria) {
}

