package com.perfulandia.perfulandiaspa.controller;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.perfulandia.perfulandiaspa.model.EnviosModel;
import com.perfulandia.perfulandiaspa.repository.EnviosRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EnviosIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EnviosRepository enviosRepo;

    @BeforeEach
    void setUp() {
        // Limpia la tabla antes de cada test
        enviosRepo.deleteAll();

        // Inserta un envío de prueba
        EnviosModel envio = new EnviosModel(
            null,                         // id nulo para que JPA lo genere
            "Test Cliente",               // nombreCliente
            "Calle Test 123",             // direccionEnvio
            "999999999",                  // telefonoCliente
            "EN PROCESO",                 // estadoEnvio
            "2025-06-27",                 // fechaEnvio
            1234.5                        // costoEnvio
        );
        enviosRepo.save(envio);
    }

    @Test
    void listarEnviosDevuelveLosGuardados() {
        ResponseEntity<List<EnviosModel>> resp = restTemplate.exchange(
            "/api/envios",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {}
        );

        // Verifica status y contenido
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<EnviosModel> lista = resp.getBody();
        assertThat(lista).isNotNull().hasSize(1);
        assertThat(lista.get(0).getNombreCliente()).isEqualTo("Test Cliente");
    }

    @Test
    void crearEnvioPersistido() {
        // Construye un nuevo envío
        EnviosModel nuevo = new EnviosModel(
            null,
            "Otro Cliente",
            "Calle X",
            "911111111",
            "ENTREGADO",
            "2025-06-28",
            777.7
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Llama al endpoint POST /api/envios
        ResponseEntity<EnviosModel> post = restTemplate.postForEntity(
            "/api/envios",
            new HttpEntity<>(nuevo, headers),
            EnviosModel.class
        );

        // Verifica que respondió OK y se guardó correctamente
        assertThat(post.getStatusCode()).isEqualTo(HttpStatus.OK);
        EnviosModel creado = post.getBody();
        assertThat(creado).isNotNull();
        assertThat(creado.getId()).isNotNull();
        assertThat(creado.getNombreCliente()).isEqualTo("Otro Cliente");

        // Finalmente comprueba que ahora hay 2 registros
        assertThat(enviosRepo.findAll()).hasSize(2);
    }
}
