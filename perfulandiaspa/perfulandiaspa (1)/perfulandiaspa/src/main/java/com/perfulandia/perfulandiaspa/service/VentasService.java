package com.perfulandia.perfulandiaspa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.perfulandiaspa.model.VentasModel;
import com.perfulandia.perfulandiaspa.repository.VentasRepository;

@Service
public class VentasService {

    @Autowired
    private VentasRepository ventasRepository;

    public VentasModel crearVenta(VentasModel venta) {
        return ventasRepository.save(venta);
    }

    public List<VentasModel> listarVentas() {
        return ventasRepository.findAll();
    }

    public VentasModel obtenerVentaPorId(Long id) {
        return ventasRepository.findById(id).orElse(null);
    }

    public void eliminarVenta(Long id) {
        ventasRepository.deleteById(id);
    }

    public void actualizarVenta(VentasModel venta) {
        if (ventasRepository.existsById(venta.getId())) {
            ventasRepository.save(venta);
        } else {
            throw new RuntimeException("Venta no encontrada con ID: " + venta.getId());
        }
    }
}
