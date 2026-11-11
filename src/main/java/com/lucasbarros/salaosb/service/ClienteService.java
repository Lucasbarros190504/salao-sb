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

    public boolean removerCliente(long id){
        if (!clienteRepository.existsById(id)){
            return false;
        }
        clienteRepository.deleteById(id);
        return true;

    }

    public Optional<Cliente> atualizarCliente(Long id, Cliente dadosAtualizados){
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);
        if (clienteEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Cliente clienteExistente = clienteEncontrado.get();

        clienteExistente.setNome(dadosAtualizados.getNome());
        clienteExistente.setEmail(dadosAtualizados.getEmail());
        clienteExistente.setTelefone(dadosAtualizados.getTelefone());
        clienteExistente.setCpf(dadosAtualizados.getCpf());

        Cliente clienteSalvo = clienteRepository.save(clienteExistente);

        return Optional.of(clienteSalvo);
    }








}
