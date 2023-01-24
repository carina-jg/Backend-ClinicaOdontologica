package com.clinicadh.proyecto.service;

import com.clinicadh.proyecto.exceptions.BadRequestException;
import com.clinicadh.proyecto.exceptions.ResourceNotFoundException;
import com.clinicadh.proyecto.model.Odontologo;
import com.clinicadh.proyecto.model.Paciente;
import com.clinicadh.proyecto.model.Turno;
import com.clinicadh.proyecto.repository.impl.OdontologoRepository;
import com.clinicadh.proyecto.repository.impl.PacienteRepository;
import com.clinicadh.proyecto.repository.impl.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private final TurnoRepository turnoRepository;

    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    OdontologoRepository odontologoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    final static Logger logger = Logger.getLogger(OdontologoService.class);

    public Turno registrarTurno(Turno turno) throws BadRequestException {
        Optional<Paciente> paciente = pacienteRepository.findById(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turno.getOdontologo().getId());

        if(paciente.isPresent() && odontologo.isPresent()){
            logger.info("Turno registrado exitosamente");
            return turnoRepository.save(turno);
        }else{
            throw new BadRequestException("No se puede registrar el turno porque el paciente o el odontólogo no existen.");
        }
    }

    public List<Turno> listar() {
        logger.info("Listando turnos");
        return turnoRepository.findAll();
    }

    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado= buscar(id);
        if (turnoBuscado.isPresent())
            turnoRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("No existe el turno con el id = " + id);
    }

    public Turno actualizar(Turno turno) {
        return turnoRepository.save(turno);
    }

    public Optional<Turno> buscar(Long id) {
        return turnoRepository.findById(id);
    }

}
