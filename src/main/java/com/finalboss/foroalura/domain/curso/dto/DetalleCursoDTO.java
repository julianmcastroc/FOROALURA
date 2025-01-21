package com.finalboss.foroalura.domain.curso.dto;

import com.finalboss.foroalura.domain.curso.Categoria;
import com.finalboss.foroalura.domain.curso.Curso;

public record DetalleCursoDTO(
        String name,
        Long id,
        Categoria categoria,
        Boolean activo ) {
    public DetalleCursoDTO(Curso curso){
        this(
                curso .getName(),
                curso. getId(),
                curso . getCategoria(),
                curso . getActivo() );

    }
}
