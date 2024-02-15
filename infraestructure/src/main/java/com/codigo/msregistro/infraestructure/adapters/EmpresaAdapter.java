package com.codigo.msregistro.infraestructure.adapters;

import com.codigo.msregistro.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestEmpresa;
import com.codigo.msregistro.domain.ports.out.EmpresaServiceOut;

import java.util.List;
import java.util.Optional;

public class EmpresaAdapter implements EmpresaServiceOut {
    @Override
    public EmpresaDTO crearEmpresaOut(RequestEmpresa requestEmpresa) {
        return null;
    }

    @Override
    public Optional<EmpresaDTO> optenerEmpresaOut(Long id) {
        return Optional.empty();
    }

    @Override
    public List<EmpresaDTO> obtenerTodoOut() {
        return null;
    }

    @Override
    public EmpresaDTO actualizarOut(Long id, RequestEmpresa requestEmpresa) {
        return null;
    }

    @Override
    public EmpresaDTO deleteOut(Long id) {
        return null;
    }
}
