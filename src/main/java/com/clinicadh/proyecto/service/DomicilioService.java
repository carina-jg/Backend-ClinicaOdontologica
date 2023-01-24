package com.clinicadh.proyecto.service;

import com.clinicadh.proyecto.model.Domicilio;
import com.clinicadh.proyecto.repository.impl.DomicilioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {

    @Autowired
    DomicilioRepository repository;

    final static Logger logger = Logger.getLogger(OdontologoService.class);

    public List<Domicilio> listarDomicilios(){
        return repository.findAll();
    }

    public Domicilio guardar(Domicilio domicilio){
        logger.info("Domicilio registrado exitosamente");
        return repository.save(domicilio);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }

    public Optional<Domicilio> buscar(Long id){
        return repository.findById(id);
    }

    public Domicilio actualizar(Domicilio domicilio) {
        if(buscar(domicilio.getId()).isPresent())
            return repository.save(domicilio);
        else
            return null;
    }
}
