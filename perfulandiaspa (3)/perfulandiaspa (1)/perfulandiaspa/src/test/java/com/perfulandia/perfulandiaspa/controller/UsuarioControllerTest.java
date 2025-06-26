package com.perfulandia.perfulandiaspa.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.perfulandiaspa.model.UsuarioModel;
import com.perfulandia.perfulandiaspa.service.UsuarioService;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private UsuarioModel usuario;

    @BeforeEach
    void setUp() {
        usuario = new UsuarioModel();
        usuario.setId(1L);
        usuario.setNombreUsuario("user1");
        usuario.setEmail("user1@example.com");
        usuario.setPassword("pass123");
    }

    @Test
    void testListarUsuarios() throws Exception {
        when(usuarioService.listarUsuarios()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/api/usuarios").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nombreUsuario").value("user1"))
            .andExpect(jsonPath("$[0].email").value("user1@example.com"));
    }

    @Test
    void testObtenerUsuarioPorId() throws Exception {
        when(usuarioService.obtenerUsuarioPorId(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nombreUsuario").value("user1"));
    }

    @Test
    void testCrearUsuario() throws Exception {
        when(usuarioService.crearUsuario(usuario)).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.email").value("user1@example.com"));
    }

    @Test
    void testEliminarUsuario() throws Exception {
        mockMvc.perform(delete("/api/usuarios/1"))
            .andExpect(status().isOk());
    }

}
