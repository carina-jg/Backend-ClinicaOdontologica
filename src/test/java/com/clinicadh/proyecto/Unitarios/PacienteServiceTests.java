package com.clinicadh.proyecto.Unitarios;

import com.clinicadh.proyecto.model.Domicilio;
import com.clinicadh.proyecto.model.Paciente;
import com.clinicadh.proyecto.exceptions.ResourceNotFoundException;
import com.clinicadh.proyecto.service.PacienteService;
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
public class PacienteServiceTests {

    @Autowired
    private PacienteService pacienteService;


    public void cargarDataSet() {
        Domicilio domicilio1 = new Domicilio("Av. Cabildo", 989, "CABA", "Buenos Aires");
        Paciente paciente1 = pacienteService.guardar(new Paciente("Fernández", "Martín", 789789, LocalDate.of(2022,05,02), domicilio1));
        Domicilio domicilio2 = new Domicilio("Av. Libertador", 333, "CABA", "Buenos Aires");
        Paciente paciente2 = pacienteService.guardar(new Paciente("González", "Mónica", 123456789, LocalDate.of(2022,01,01), domicilio2));
    }

    @Test
    public void agregarYBuscarPacienteTest() {
        this.cargarDataSet();
        Domicilio domicilio = new Domicilio("Monroe", 123, "CABA", "Buenos Aires");
        Paciente paciente3 = pacienteService.guardar(new Paciente("Jimenez", "Sergio", 987789, LocalDate.of(2022,04,01), domicilio));

        Assert.assertNotNull(pacienteService.buscar(paciente3.getId()));
    }

    @Test
    public void listarPacientesTest() {
        Assert.assertNotNull(pacienteService.buscarTodos());
    }

    @Test
    public void eliminarPacienteTest() throws ResourceNotFoundException {
        pacienteService.eliminar(1L);
        Assert.assertTrue(pacienteService.buscar(1L).isEmpty());

    }
}
