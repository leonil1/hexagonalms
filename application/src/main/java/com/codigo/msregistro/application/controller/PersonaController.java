package com.codigo.msregistro.application.controller;

import com.codigo.msregistro.domain.aggregates.dto.PersonaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestPersona;
import com.codigo.msregistro.domain.ports.in.PersonaServiceIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {
    private final PersonaServiceIn personaServiceIn;

    public PersonaController(PersonaServiceIn personaServiceIn) {
        this.personaServiceIn = personaServiceIn;
    }

    @PostMapping("/create")
    public ResponseEntity<PersonaDTO> registrar(@RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(requestPersona));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> obtenerPersona(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.optenerPersonaIn(id).get());
    }

    @GetMapping("/lista")
    public ResponseEntity<List<PersonaDTO>>obtenerTodo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerTodoIn());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> actualizat(@PathVariable Long id, @RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.actualizarIn(id, requestPersona));
    }
}
