package com.lucasbarros.salaosb.service;


import com.lucasbarros.salaosb.model.Cliente;
import com.lucasbarros.salaosb.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPeloId(long id){
        return clienteRepository.findById(id);
    }

    public void removerCliente(long id){
        clienteRepository.deleteById(id);
    }








}
