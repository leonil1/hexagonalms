package com.codigo.msregistro.infraestructure.adapters;

import com.codigo.msregistro.domain.aggregates.constnts.Constants;
import com.codigo.msregistro.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msregistro.domain.aggregates.dto.PersonaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestEmpresa;
import com.codigo.msregistro.domain.aggregates.response.ResponseEmpresa;
import com.codigo.msregistro.domain.aggregates.response.ResponseReniec;
import com.codigo.msregistro.domain.ports.out.EmpresaServiceOut;
import com.codigo.msregistro.infraestructure.entity.EmpresaEntity;
import com.codigo.msregistro.infraestructure.entity.TipoDocumentoEntity;
import com.codigo.msregistro.infraestructure.maper.EmpresaMaper;
import com.codigo.msregistro.infraestructure.repository.EmpresaRespository;
import com.codigo.msregistro.infraestructure.repository.TipoDocumentoRepository;
import com.codigo.msregistro.infraestructure.rest.client.ClientReniec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaAdapter implements EmpresaServiceOut {

    private final EmpresaRespository empresaRespository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final EmpresaMaper empresaMaper;
    private final ClientReniec reniec;

    public EmpresaAdapter(EmpresaRespository empresaRespository, TipoDocumentoRepository tipoDocumentoRepository,
                          EmpresaMaper empresaMaper, ClientReniec reniec) {
        this.empresaRespository = empresaRespository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.empresaMaper = empresaMaper;
        this.reniec = reniec;
    }

    @Value("${token.api}")
    private String tokenApi;

    @Override
    public EmpresaDTO crearEmpresaOut(RequestEmpresa requestEmpresa) {
        ResponseEmpresa datosEmpresa = getExecutionReniec(requestEmpresa.getNumDoc());
        empresaRespository.save(getEntity(datosEmpresa, requestEmpresa));
        return empresaMaper.mapToDto(getEntity(datosEmpresa, requestEmpresa));
    }

    @Override
    public Optional<EmpresaDTO> optenerEmpresaOut(Long id) {
        return Optional.ofNullable(empresaMaper.mapToDto(empresaRespository.findById(id).get()));
    }

    @Override
    public List<EmpresaDTO> obtenerTodoOut() {
        List<EmpresaDTO> empresaDTOList = new ArrayList<>();
        List<EmpresaEntity> entities = empresaRespository.findAll();
        for(EmpresaEntity empresa: entities){
            EmpresaDTO empresaDTO = empresaMaper.mapToDto(empresa);
            empresaDTOList.add(empresaDTO);
        }
        return empresaDTOList;
    }

    @Override
    public EmpresaDTO actualizarOut(Long id, RequestEmpresa requestEmpresa) {
        boolean exist = empresaRespository.existsById(id);
        if (exist){
            Optional<EmpresaEntity> entity = empresaRespository.findById(id);
            ResponseEmpresa responseEmpresa = getExecutionReniec(requestEmpresa.getNumDoc());
            empresaRespository.save(getEntitityUpdate(responseEmpresa, entity.get()));
            return empresaMaper.mapToDto(getEntitityUpdate(responseEmpresa, entity.get()));
        }
        return null;
    }

    @Override
    public EmpresaDTO deleteOut(Long id) {
        boolean exist = empresaRespository.existsById(id);
        if (exist){
            Optional<EmpresaEntity> entity = empresaRespository.findById(id);
            entity.get().setEstado(0);
            entity.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            entity.get().setDateDelet(gettimestamp());
            empresaRespository.save(entity.get());
            return empresaMaper.mapToDto(entity.get());
        }
        return null;
    }

    public ResponseEmpresa getExecutionReniec(String numero){
        String authorization = "Bearer " + tokenApi;
        ResponseEmpresa responseEmpresa = reniec.getInfoReniecEmpresa(numero, authorization);
        return responseEmpresa;
    }

    public EmpresaEntity getEntity(ResponseEmpresa empresa, RequestEmpresa requestEmpresa){
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(requestEmpresa.getTipoDoc());
        EmpresaEntity entity = new EmpresaEntity();
        entity.setNumDocu(empresa.getNumeroDocumento());
        entity.setRazonSocial(empresa.getRazonSocial());
        entity.setNomComercial("Nuevo Empresa");
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(gettimestamp());
        entity.setTipoDocumento(tipoDocumento);
        return entity;
    }

    public EmpresaEntity getEntitityUpdate(ResponseEmpresa empresa , EmpresaEntity updateEmpresa){
        updateEmpresa.setNumDocu(empresa.getNumeroDocumento());
        updateEmpresa.setRazonSocial(empresa.getRazonSocial());
        updateEmpresa.setUsuaModif(Constants.AUDIT_ADMIN);
        updateEmpresa.setDateModif(gettimestamp());
        return updateEmpresa;
    }

    private Timestamp gettimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
