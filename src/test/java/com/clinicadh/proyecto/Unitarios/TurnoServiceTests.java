package com.clinicadh.proyecto.Unitarios;

import com.clinicadh.proyecto.exceptions.BadRequestException;
import com.clinicadh.proyecto.exceptions.ResourceNotFoundException;
import com.clinicadh.proyecto.model.Domicilio;
import com.clinicadh.proyecto.model.Odontologo;
import com.clinicadh.proyecto.model.Paciente;
import com.clinicadh.proyecto.model.Turno;
import com.clinicadh.proyecto.service.OdontologoService;
import com.clinicadh.proyecto.service.PacienteService;
import com.clinicadh.proyecto.service.TurnoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTests {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    public void cargarDataSet() {
        Domicilio domicilio = new Domicilio("Av. Cramer", 123, "CABA", "Buenos Aires");
        Paciente paciente = pacienteService.guardar(new Paciente("Ponce", "Mónica", 777888, LocalDate.of(2022,01,01), domicilio));
        this.odontologoService.registrar(new Odontologo("Silvia", "Sánchez", "ABC9999"));
    }

    @Test
    public void altaTurnoTest() throws BadRequestException {
        this.cargarDataSet();
        turnoService.registrarTurno(new Turno(pacienteService.buscar(1L).get(),odontologoService.buscar(1L).get(),LocalDate.of(2022,01,01)));
        Assert.assertNotNull(turnoService.buscar(1L));
    }

    @Test
    public void buscarTurnoTest(){
        Assert.assertNotNull(turnoService.buscar(1L));
    }

    @Test
    public void listarTurnoTest() {
        Assert.assertNotNull(turnoService.listar());
    }

    @Test
    public void eliminarTurnoTest() throws ResourceNotFoundException {
        turnoService.eliminar(1L);
        Assert.assertFalse(turnoService.buscar(1L).isPresent());
    }
}
