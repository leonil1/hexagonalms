package com.codigo.msregistro.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestPersona {
    private String tipoDoc;
    private String numDoc;
}
