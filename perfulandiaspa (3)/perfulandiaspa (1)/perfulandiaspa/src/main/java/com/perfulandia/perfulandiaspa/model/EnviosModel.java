package com.perfulandia.perfulandiaspa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "envios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnviosModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @Column(name = "direccion_envio", nullable = false)
    private String direccionEnvio;

    @Column(name = "telefono_cliente", nullable = false)
    private String telefonoCliente;

    @Column(name = "estado_envio", nullable = false)
    private String estadoEnvio;

    @Column(name = "fecha_envio", nullable = false)
    private String fechaEnvio;

    @Column(name = "costo_envio", nullable = false)
    private Double costoEnvio;

}
