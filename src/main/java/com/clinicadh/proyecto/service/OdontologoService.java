package com.clinicadh.proyecto.service;

import com.clinicadh.proyecto.model.Odontologo;
import com.clinicadh.proyecto.exceptions.ResourceNotFoundException;
import com.clinicadh.proyecto.repository.impl.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    final static Logger logger = Logger.getLogger(OdontologoService.class);

    public Odontologo registrar(Odontologo odontologo) {
        logger.info("Odontólogo registrado exitosamente");
        return odontologoRepository.save(odontologo);
    }

    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = buscar(id);
        if(odontologoBuscado.isPresent()){
            odontologoRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("No existe el odontólogo con el id = " + id);
        }
    }

    public Optional<Odontologo> buscar(Long id){
        return odontologoRepository.findById(id);
    }

    public List<Odontologo> buscarTodos() {
        logger.info("Listando odontólogos");
        return odontologoRepository.findAll();
    }

    public Odontologo actualizar(Odontologo odontologo) {
        if(buscar(odontologo.getId()).isPresent()){
            return odontologoRepository.save(odontologo);
        }else{
            return null;
        }
    }
}
