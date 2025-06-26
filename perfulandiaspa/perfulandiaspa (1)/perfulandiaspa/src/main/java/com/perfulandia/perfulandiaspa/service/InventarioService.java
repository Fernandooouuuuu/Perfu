package com.perfulandia.perfulandiaspa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.perfulandiaspa.model.InventarioModel;
import com.perfulandia.perfulandiaspa.repository.InventarioRepository;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public InventarioModel crearItem(InventarioModel item) {
        return inventarioRepository.save(item);
    }

    public List<InventarioModel> listarItems() {
        return inventarioRepository.findAll();
    }

    public InventarioModel obtenerItemPorId(Long id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    public void eliminarItem(Long id) {
        inventarioRepository.deleteById(id);
    }

    public void actualizarItem(InventarioModel item) {
        if (inventarioRepository.existsById(item.getId())) {
            inventarioRepository.save(item);
        } else {
            throw new RuntimeException("Item no encontrado con ID: " + item.getId());
        }
    }
}
