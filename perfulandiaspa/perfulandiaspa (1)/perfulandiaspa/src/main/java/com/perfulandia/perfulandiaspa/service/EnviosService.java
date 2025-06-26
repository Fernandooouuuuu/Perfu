package com.perfulandia.perfulandiaspa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.perfulandiaspa.model.EnviosModel;
import com.perfulandia.perfulandiaspa.repository.EnviosRepository;

@Service
public class EnviosService {

    @Autowired
    private EnviosRepository enviosRepository;

    public EnviosModel crearEnvio(EnviosModel envio) {
        return enviosRepository.save(envio);
    }

    public List<EnviosModel> listarEnvios() {
        return enviosRepository.findAll();
    }

    public EnviosModel obtenerEnvioPorId(Long id) {
        return enviosRepository.findById(id).orElse(null);
    }
    
    public void eliminarEnvio(Long id) {
        enviosRepository.deleteById(id);
    }

    public void actualizarEnvio(EnviosModel envio) {
        if (enviosRepository.existsById(envio.getId())) {
            enviosRepository.save(envio);
        } else {
            throw new RuntimeException("Envio no encontrado con ID: " + envio.getId());
        }
    }
}
