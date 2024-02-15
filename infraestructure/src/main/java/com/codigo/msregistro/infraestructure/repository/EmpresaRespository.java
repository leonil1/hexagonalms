package com.codigo.msregistro.infraestructure.repository;

import com.codigo.msregistro.infraestructure.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRespository extends JpaRepository<EmpresaEntity, Long> {
}
