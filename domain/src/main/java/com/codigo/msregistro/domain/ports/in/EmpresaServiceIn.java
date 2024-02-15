package com.codigo.msregistro.domain.ports.in;

import com.codigo.msregistro.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestEmpresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {
    EmpresaDTO crearEmpresaIn(RequestEmpresa requestEmpresa);
    Optional<EmpresaDTO> optenerEmpresaIn(Long id);
    List<EmpresaDTO> obtenerTodoIn();

    EmpresaDTO actualizarIn(Long id, RequestEmpresa requestEmpresa);

    EmpresaDTO deleteIn(Long id);
}
