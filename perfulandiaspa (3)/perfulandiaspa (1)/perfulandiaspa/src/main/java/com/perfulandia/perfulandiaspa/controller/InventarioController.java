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

import com.perfulandia.perfulandiaspa.model.InventarioModel;
import com.perfulandia.perfulandiaspa.service.InventarioService;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    public InventarioModel crearItem(@RequestBody InventarioModel item) {
        return inventarioService.crearItem(item);
    }

    @GetMapping
    public List<InventarioModel> listarItems() {
        return inventarioService.listarItems();
    }

    @GetMapping("/{id}")
    public InventarioModel obtenerItem(@PathVariable Long id) {
        return inventarioService.obtenerItemPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarItem(@PathVariable Long id) {
        inventarioService.eliminarItem(id);
    }
}
