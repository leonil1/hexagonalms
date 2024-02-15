package com.codigo.msregistro.infraestructure.maper;

import com.codigo.msregistro.domain.aggregates.dto.PersonaDTO;
import com.codigo.msregistro.infraestructure.entity.PersonaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonaMaper {
    private static final ModelMapper modelMapper  = new ModelMapper();

    public PersonaDTO mapToDto(PersonaEntity entity){
        return modelMapper.map(entity, PersonaDTO.class);
    }

    public PersonaEntity dtoToMap(PersonaDTO personaDTO){
        return  modelMapper.map(personaDTO, PersonaEntity.class);
    }
}
