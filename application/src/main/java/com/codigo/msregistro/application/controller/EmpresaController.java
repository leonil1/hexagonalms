package com.codigo.msregistro.application.controller;

import com.codigo.msregistro.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestEmpresa;
import com.codigo.msregistro.domain.ports.in.EmpresaServiceIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empresa")
public class EmpresaController {

    private final EmpresaServiceIn empresaServiceIn;

    public EmpresaController(EmpresaServiceIn empresaServiceIn) {
        this.empresaServiceIn = empresaServiceIn;
    }

    @PostMapping("/create")
    public ResponseEntity<EmpresaDTO> regitrar(@RequestBody RequestEmpresa requestEmpresa){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.crearEmpresaIn(requestEmpresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> obtenerEmpresa(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.optenerEmpresaIn(id).get());
    }

    @GetMapping("/lista")
    public ResponseEntity<List<EmpresaDTO>> obtenerTodo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.obtenerTodoIn());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> actualizar(@PathVariable Long id, @RequestBody RequestEmpresa requestEmpresa ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.actualizarIn(id, requestEmpresa));
    }

}
