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

@RestController
@RequestMapping("/api/ventas")
public class VentasController {

    @Autowired
    private VentasService ventasService;

    @PostMapping
    public VentasModel crearVenta(@RequestBody VentasModel venta) {
        return ventasService.crearVenta(venta);
    }

    @GetMapping
    public List<VentasModel> listarVentas() {
        return ventasService.listarVentas();
    }

    @GetMapping("/{id}")
    public VentasModel obtenerVenta(@PathVariable Long id) {
        return ventasService.obtenerVentaPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable Long id) {
        ventasService.eliminarVenta(id);
    }
}
