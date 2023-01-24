package com.clinicadh.proyecto.controller;

import com.clinicadh.proyecto.exceptions.ResourceNotFoundException;
import com.clinicadh.proyecto.model.Domicilio;
import com.clinicadh.proyecto.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    private DomicilioService service;

    @GetMapping
    public List<Domicilio> listarDomicilios(){
        return service.listarDomicilios();
    }

    @PostMapping
    public Domicilio registrarDomicilio(@RequestBody Domicilio domicilio){
        return service.guardar(domicilio);
    }

    @PutMapping
    public Domicilio actualizarDomicilio(@RequestBody Domicilio domicilio){
        return service.actualizar(domicilio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domicilio> buscarDomicilio(@PathVariable Long id){
        if(service.buscar(id).isPresent()){
            return ResponseEntity.ok(service.buscar(id).get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDomicilio(@PathVariable Long id) throws ResourceNotFoundException {
        service.eliminar(id);
        return ResponseEntity.ok("Domicilio eliminado correctamente.");
    }
}
