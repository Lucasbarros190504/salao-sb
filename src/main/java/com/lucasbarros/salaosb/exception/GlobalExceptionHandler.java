package com.lucasbarros.salaosb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> tratarErrosDeValidacao(MethodArgumentNotValidException excecao){
        Map<String, List<String>> erros = new LinkedHashMap<>();

        excecao.getBindingResult().getFieldErrors().forEach(erro -> {
            String campo = erro.getField();
            String message = erro.getDefaultMessage();

            erros.computeIfAbsent(campo, chave -> new ArrayList<>()).add(message);
        });  

        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(ClienteDuplicadoException.class)
    public ResponseEntity<Map<String, String>> tratarErrosDeDuplicacao(ClienteDuplicadoException excecao){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("erro", excecao.getMessage()));
    }
}


