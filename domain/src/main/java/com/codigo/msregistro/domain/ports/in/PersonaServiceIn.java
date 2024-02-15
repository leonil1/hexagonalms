package com.codigo.msregistro.domain.ports.in;

import com.codigo.msregistro.domain.aggregates.dto.PersonaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {

    PersonaDTO crearPersonaIn(RequestPersona requestPersona);
    Optional<PersonaDTO> optenerPersonaIn(Long id);
    List<PersonaDTO> obtenerTodoIn();
    PersonaDTO actualizarIn(Long id, RequestPersona requestPersona);

    PersonaDTO delete(Long id);
}
