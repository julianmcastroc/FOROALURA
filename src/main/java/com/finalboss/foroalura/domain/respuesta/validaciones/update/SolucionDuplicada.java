package com.finalboss.foroalura.domain.respuesta.validaciones.update;

import com.finalboss.foroalura.domain.respuesta.Respuesta;
import com.finalboss.foroalura.domain.respuesta.dto.ActualizarRespuestaDTO;
import com.finalboss.foroalura.domain.respuesta.dto.CrearRespuestaDTO;
import com.finalboss.foroalura.domain.respuesta.repository.RespuestaRepository;
import com.finalboss.foroalura.domain.respuesta.validaciones.create.ValidarRespuestaCreada;
import com.finalboss.foroalura.domain.topico.Estado;
import com.finalboss.foroalura.domain.topico.repository.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolucionDuplicada implements ValidarRespuestaCreada {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validate(CrearRespuestaDTO data) {

    }

    @Override
    public void validate(ActualizarRespuestaDTO data, Long respuestaId) {
       if (data.solucion()){
           Respuesta respuesta = respuestaRepository.getReferenceById(respuestaId);
           var topicoResuelto = topicoRepository.getReferenceById(respuesta.getTopico().getId());
           if (topicoResuelto.getEstado() == Estado.CLOSED){
               throw new ValidationException("Este topico ya esta solucionado.");
           }
       }
    }
}


