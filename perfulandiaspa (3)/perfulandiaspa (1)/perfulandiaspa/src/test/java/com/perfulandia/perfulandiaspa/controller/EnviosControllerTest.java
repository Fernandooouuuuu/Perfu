package com.perfulandia.perfulandiaspa.controller;

import java.time.LocalDate;

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
import com.perfulandia.perfulandiaspa.model.EnviosModel;
import com.perfulandia.perfulandiaspa.service.EnviosService;

@WebMvcTest(EnviosController.class)
public class EnviosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnviosService enviosService;

    @Autowired
    private ObjectMapper objectMapper;

    private EnviosModel envio;

    @BeforeEach
    void setUp(){
        envio=new EnviosModel();
        envio.setId(1L);
        envio.setNombreCliente("Juan Pérez");
        envio.setDireccionEnvio("Calle 123");
        envio.setTelefonoCliente("987654321");
        envio.setEstadoEnvio("EN PROCESO");
        envio.setFechaEnvio(LocalDate.now().toString());
        envio.setCostoEnvio(4500.0);
    }

    @Test
    void testObtenerEnvioPorId() throws Exception {
        when(enviosService.obtenerEnvioPorId(1L)).thenReturn(envio);

        mockMvc.perform(get("/api/envios/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nombreCliente").value("Juan Pérez"));
    }

    @Test
    void testCrearEnvio() throws Exception {
        when(enviosService.crearEnvio(envio)).thenReturn(envio);

        mockMvc.perform(post("/api/envios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(envio)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testEliminarEnvio() throws Exception {
        mockMvc.perform(delete("/api/envios/1"))
            .andExpect(status().isOk());
    }
}
