package com.codigo.msregistro.domain.impl;

import com.codigo.msregistro.domain.aggregates.response.ResponseReniec;
import com.codigo.msregistro.domain.ports.in.ReniecServiceIn;
import com.codigo.msregistro.domain.ports.out.RestReniecOut;
import org.springframework.stereotype.Service;

@Service
public class ReniecServiceImpl implements ReniecServiceIn {

    private final RestReniecOut restReniecOut;

    public ReniecServiceImpl(RestReniecOut restReniecOut) {
        this.restReniecOut = restReniecOut;
    }

    @Override
    public ResponseReniec getInfoIn(String numero) {
        return restReniecOut.getInfoReniec(numero);
    }
}
