package com.perfulandia.perfulandiaspa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.perfulandiaspa.model.UsuarioModel;
import com.perfulandia.perfulandiaspa.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public UsuarioModel crearUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
