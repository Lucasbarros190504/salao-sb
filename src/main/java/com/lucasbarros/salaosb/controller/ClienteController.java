package com.lucasbarros.salaosb.controller;


import com.lucasbarros.salaosb.model.Cliente;
import com.lucasbarros.salaosb.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping("/clientes")
    public Cliente cadastrarCliente(@RequestBody Cliente cliente){
        return clienteService.cadastrarCliente(cliente);
    }

    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> buscarPeloId(@PathVariable Long id){
        Optional<Cliente> clienteEncontrado = clienteService.buscarPeloId(id);

        if(clienteEncontrado.isPresent()){
            return ResponseEntity.ok(clienteEncontrado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> removerCliente(@PathVariable Long id){
        clienteService.removerCliente(id);
        return ResponseEntity.noContent().build();
    }




}
