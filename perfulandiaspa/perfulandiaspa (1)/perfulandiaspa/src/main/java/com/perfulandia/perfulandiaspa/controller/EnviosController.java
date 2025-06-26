package com.perfulandia.perfulandiaspa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.perfulandiaspa.model.EnviosModel;
import com.perfulandia.perfulandiaspa.service.EnviosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/envios")
@Tag(name = "Envios", description = "Operaciones relacionadas con los envíos")

public class EnviosController {

    @Autowired
    private EnviosService enviosService;

    @Operation(summary = "Controlador para gestionar envíos")
    @PostMapping
    public EnviosModel crearEnvio(@RequestBody EnviosModel envio) {
        return enviosService.crearEnvio(envio);
    }

    @Operation(summary = "Listar todos los envíos")
    @GetMapping
    public List<EnviosModel> listarEnvios() {
        return enviosService.listarEnvios();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un envío por ID")
    public EnviosModel obtenerEnvio(@PathVariable Long id) {
        return enviosService.obtenerEnvioPorId(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un envío por ID")
    public void eliminarEnvio(@PathVariable Long id) {
        enviosService.eliminarEnvio(id);
    }

    @PutMapping("path/{id}")
    @Operation(summary = "Listar envíos por ID de cliente")
    public void ActualizarEnvios(EnviosModel envio) {
        enviosService.actualizarEnvio(envio);

    }
}
