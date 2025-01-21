package com.finalboss.foroalura.domain.respuesta.validaciones.create;

import com.finalboss.foroalura.domain.respuesta.dto.ActualizarRespuestaDTO;
import com.finalboss.foroalura.domain.respuesta.dto.CrearRespuestaDTO;

public interface ValidarRespuestaCreada {
    public void validate(CrearRespuestaDTO data);

    void validate(ActualizarRespuestaDTO data, Long respuestaId);
}
