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

@RestController
@RequestMapping("/api/envios")
public class EnviosController {

    @Autowired
    private EnviosService enviosService;

    @PostMapping
    public EnviosModel crearEnvio(@RequestBody EnviosModel envio) {
        return enviosService.crearEnvio(envio);
    }

    @GetMapping
    public List<EnviosModel> listarEnvios() {
        return enviosService.listarEnvios();
    }

    @GetMapping("/{id}")
    public EnviosModel obtenerEnvio(@PathVariable Long id) {
        return enviosService.obtenerEnvioPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarEnvio(@PathVariable Long id) {
        enviosService.eliminarEnvio(id);
    }
}
