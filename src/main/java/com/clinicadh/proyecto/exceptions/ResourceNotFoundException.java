package com.clinicadh.proyecto.exceptions;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
