package com.lucasbarros.salaosb.repository;

import com.lucasbarros.salaosb.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
