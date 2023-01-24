package com.clinicadh.proyecto.Unitarios;

import com.clinicadh.proyecto.exceptions.ResourceNotFoundException;
import com.clinicadh.proyecto.model.Odontologo;
import com.clinicadh.proyecto.service.OdontologoService;;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTests {

    @Autowired
    private OdontologoService odontologoService;


    public Odontologo cargarDataSet(){
        Odontologo odontologo = new Odontologo("Mariano", "López", "ABC123");
        odontologoService.registrar(odontologo);
        return odontologo;
    }

    @Test
    public void agregarOdontologoTest() {
        Odontologo odontologo = odontologoService.registrar(new Odontologo("Natalia", "Martínez", "98AB99"));
        Assert.assertTrue(odontologo.getId() != null);

    }

    @Test
    public void buscarOdontologoTest(){
        Assert.assertNotNull(odontologoService.buscar(1L));
    }

    @Test
    public void traerTodosTest() {
        Assert.assertNotNull(odontologoService.buscarTodos());
    }

    @Test
    public void eliminarOdontologoTest() throws ResourceNotFoundException {
        odontologoService.eliminar(1L);
        Assert.assertFalse(odontologoService.buscar(1L).isPresent());
    }
}
