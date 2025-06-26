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

import com.perfulandia.perfulandiaspa.model.VentasModel;
import com.perfulandia.perfulandiaspa.service.VentasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/ventas")
@Tag(name = "Ventas", description = "Operaciones relacionadas con las ventas")
public class VentasController {

    @Autowired
    private VentasService ventasService;

    @PostMapping
    @Operation(summary = "Controlador para gestionar ventas")
    public VentasModel crearVenta(@RequestBody VentasModel venta) {
        return ventasService.crearVenta(venta);
    }

    @GetMapping
    @Operation(summary = "Listar todas las ventas")
    public List<VentasModel> listarVentas() {
        return ventasService.listarVentas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una venta por ID")
    public VentasModel obtenerVenta(@PathVariable Long id) {
        return ventasService.obtenerVentaPorId(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una venta por ID")
    public void eliminarVenta(@PathVariable Long id) {
        ventasService.eliminarVenta(id);
    }

    @PutMapping("path/{id}")
    @Operation(summary = "Actualizar una venta")
    public void actualizarVenta(VentasModel venta) {
        ventasService.actualizarVenta(venta);
    }
}
