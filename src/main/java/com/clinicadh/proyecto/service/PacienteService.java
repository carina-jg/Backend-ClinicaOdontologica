package com.clinicadh.proyecto.service;

import com.clinicadh.proyecto.exceptions.ResourceNotFoundException;
import com.clinicadh.proyecto.model.Paciente;
import com.clinicadh.proyecto.repository.impl.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService{

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    final static Logger logger = Logger.getLogger(OdontologoService.class);

    public Paciente guardar(Paciente paciente) {
        logger.info("Paciente registrado exitosamente");
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscar(Long id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodos() {
        logger.info("Listando pacientes");
        return pacienteRepository.findAll();
    }

    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado=buscar(id);
        if(pacienteBuscado.isPresent())
            pacienteRepository.deleteById(id);
        else{
            throw new ResourceNotFoundException("No existe el paciente con el id = " + id);
        }
    }

    public Paciente actualizar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
}