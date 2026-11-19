package com.lucasbarros.salaosb.exception;

public class ClienteDuplicadoException extends RuntimeException {

    public ClienteDuplicadoException(String message){
        super(message);
    }
}
