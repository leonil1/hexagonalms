package com.codigo.msregistro.infraestructure.maper;

import com.codigo.msregistro.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msregistro.infraestructure.entity.EmpresaEntity;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmpresaMaper {
    private static final ModelMapper modelMaper = new  ModelMapper();

    public EmpresaDTO mapToDto(EmpresaEntity entity){
        return modelMaper.map(entity, EmpresaDTO.class);
    }

    public EmpresaEntity dtoToMap(EmpresaDTO empresaDTO){
        return modelMaper.map(empresaDTO, EmpresaEntity.class);
    }
}
