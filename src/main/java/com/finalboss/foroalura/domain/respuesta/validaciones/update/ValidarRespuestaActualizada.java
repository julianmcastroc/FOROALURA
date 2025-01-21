package com.finalboss.foroalura.domain.respuesta.validaciones.update;

import com.finalboss.foroalura.domain.respuesta.dto.ActualizarRespuestaDTO;

public interface ValidarRespuestaActualizada {

    public void validate(ActualizarRespuestaDTO data, Long respuestaId);

}
