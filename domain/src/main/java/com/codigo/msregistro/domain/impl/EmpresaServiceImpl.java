package com.codigo.msregistro.domain.impl;

import com.codigo.msregistro.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestEmpresa;
import com.codigo.msregistro.domain.ports.in.EmpresaServiceIn;
import com.codigo.msregistro.domain.ports.out.EmpresaServiceOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaServiceIn {

    private final EmpresaServiceOut empresaServiceOut;

    public EmpresaServiceImpl(EmpresaServiceOut empresaServiceOut) {
        this.empresaServiceOut = empresaServiceOut;
    }


    @Override
    public EmpresaDTO crearEmpresaIn(RequestEmpresa requestEmpresa) {
        return empresaServiceOut.crearEmpresaOut(requestEmpresa);
    }

    @Override
    public Optional<EmpresaDTO> optenerEmpresaIn(Long id) {
        return empresaServiceOut.optenerEmpresaOut(id);
    }

    @Override
    public List<EmpresaDTO> obtenerTodoIn() {
        return empresaServiceOut.obtenerTodoOut();
    }

    @Override
    public EmpresaDTO actualizarIn(Long id, RequestEmpresa requestEmpresa) {
        return empresaServiceOut.actualizarOut(id, requestEmpresa);
    }

    @Override
    public EmpresaDTO deleteIn(Long id) {
        return empresaServiceOut.deleteOut(id);
    }
}
