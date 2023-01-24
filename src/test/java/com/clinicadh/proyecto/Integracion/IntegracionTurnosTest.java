package com.clinicadh.proyecto.Integracion;

import com.clinicadh.proyecto.exceptions.BadRequestException;
import com.clinicadh.proyecto.model.Domicilio;
import com.clinicadh.proyecto.model.Odontologo;
import com.clinicadh.proyecto.model.Paciente;
import com.clinicadh.proyecto.model.Turno;
import com.clinicadh.proyecto.service.OdontologoService;
import com.clinicadh.proyecto.service.PacienteService;
import com.clinicadh.proyecto.service.TurnoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarDataSet() throws BadRequestException {
        Domicilio domicilio = new Domicilio("Av. Libertador", 777, "CABA", "Buenos Aires");
        Paciente paciente = pacienteService.guardar(new Paciente("Fausto", "López", 99999, LocalDate.of(2022,01,01), domicilio));
        this.odontologoService.registrar(new Odontologo("Facundo", "Hernández", "ALP9879"));
        turnoService.registrarTurno(new Turno(pacienteService.buscar(1L).get(),odontologoService.buscar(1L).get(),LocalDate.of(2022,01,01)));

    }
    @Test
    public void listarTurnos() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
