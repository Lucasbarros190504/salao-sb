package com.lucasbarros.salaosb.controller;

import com.lucasbarros.salaosb.exception.ClienteDuplicadoException;
import com.lucasbarros.salaosb.model.Cliente;
import com.lucasbarros.salaosb.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @MockitoBean
    private ClienteService clienteService;

    @Autowired
    private MockMvc mockMvc;



    @Test
    void cadastroComEmailDuplicadoRetorna409() throws Exception {
        when(clienteService.cadastrarCliente(any(Cliente.class)))
                .thenThrow(new ClienteDuplicadoException("Este e-mail já está em uso"));

        String json = """
               {
                  "nome": "Ana Teste",
                  "email": "ana.teste@example.com",
                  "telefone": "85999990000",
                  "cpf": "12345678900"
               }
               """;
        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.erro").value("Este e-mail já está em uso"));
    }

    @Test
    void cadastroComEmailInvalidoRetorna400() throws Exception{

        String json = """
               {
                "nome": "Ana Teste",
                  "email": "anaexample.com",
                  "telefone": "85999990000",
                  "cpf": "12345678900"
               }
               """;
        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email[0]").value("O e-mail deve ter um formato válido"));
    }

}
