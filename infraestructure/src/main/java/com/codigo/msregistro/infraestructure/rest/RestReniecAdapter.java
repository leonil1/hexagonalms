package com.codigo.msregistro.infraestructure.rest;

import com.codigo.msregistro.domain.aggregates.response.ResponseReniec;
import com.codigo.msregistro.domain.ports.out.RestReniecOut;
import com.codigo.msregistro.infraestructure.rest.client.ClientReniec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RestReniecAdapter implements RestReniecOut {

    private final ClientReniec reniec;

    public RestReniecAdapter(ClientReniec reniec) {
        this.reniec = reniec;
    }

    @Value("${token.api}")
    private String tokenApi;

    @Override
    public ResponseReniec getInfoReniec(String numDoc) {
        String authorization = "Bearer " + tokenApi;
        ResponseReniec responseReniec = reniec.getInfoReniec(numDoc, authorization);
        return responseReniec;
    }
}
