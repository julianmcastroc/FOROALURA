package com.finalboss.foroalura.domain.usuario.dto;

import com.finalboss.foroalura.domain.usuario.Role;

public record ActualizarUsuarioDTO(
   String password,
   Role role,
   String nombre,
   String apellido,
   String email,
   Boolean enabled

) {
}


