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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/inventario")
@Tag(name = "Inventario", description = "Operaciones relacionadas con el inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    @Operation(summary = "Controlador para gestionar inventario")
    public InventarioModel crearItem(@RequestBody InventarioModel item) {
        return inventarioService.crearItem(item);
    }

    @GetMapping
    @Operation(summary = "Listar todos los items del inventario")
    public List<InventarioModel> listarItems() {
        return inventarioService.listarItems();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un item del inventario por ID")
    public InventarioModel obtenerItem(@PathVariable Long id) {
        return inventarioService.obtenerItemPorId(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un item del inventario por ID")
    public void eliminarItem(@PathVariable Long id) {
        inventarioService.eliminarItem(id);
    }

    @PutMapping("path/{id}")
    @Operation(summary = "Actualizar un item del inventario")
    public void actualizarItem(InventarioModel item) {
        inventarioService.actualizarItem(item);
    }
}
