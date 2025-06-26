package com.perfulandia.perfulandiaspa.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.perfulandia.perfulandiaspa.model.EnviosModel;
import com.perfulandia.perfulandiaspa.model.InventarioModel;
import com.perfulandia.perfulandiaspa.model.UsuarioModel;
import com.perfulandia.perfulandiaspa.model.VentasModel;
import com.perfulandia.perfulandiaspa.repository.EnviosRepository;
import com.perfulandia.perfulandiaspa.repository.InventarioRepository;
import com.perfulandia.perfulandiaspa.repository.UsuarioRepository;
import com.perfulandia.perfulandiaspa.repository.VentasRepository;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;

@Component
@Profile({"dev","test"})
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final EnviosRepository    enviosRepo;
    private final UsuarioRepository   usuariosRepo;
    private final InventarioRepository inventarioRepo;
    private final VentasRepository    ventasRepo;

    @Override
    public void run(String... args) {
        System.out.println(">>> DataLoader se está ejecutando para el perfil activo");
        Faker faker = new Faker();

        // 5 Envios de ejemplo
        for (int i = 0; i < 5; i++) {
            EnviosModel e = new EnviosModel(
                null,
                faker.name().fullName(),
                faker.address().fullAddress(),
                faker.phoneNumber().cellPhone(),
                faker.options().option("EN PROCESO","ENVIADO","ENTREGADO"),
                LocalDate.now()
                        .minusDays(faker.number().numberBetween(0, 30))
                        .toString(),
                faker.number().randomDouble(2, 1000, 10000)
            );
            enviosRepo.save(e);
        }

        // 5 Usuarios de ejemplo
        for (int i = 0; i < 5; i++) {
            UsuarioModel u = new UsuarioModel(
                null,
                faker.name().username(),
                faker.internet().emailAddress(),
                faker.internet().password()
            );
            usuariosRepo.save(u);
        }

        // 5 Ítems de inventario
        for (int i = 0; i < 5; i++) {
            InventarioModel it = new InventarioModel(
                null,
                faker.commerce().productName(),
                faker.lorem().sentence(),
                faker.number().numberBetween(1, 50),
                faker.number().randomDouble(2, 10, 500)
            );
            inventarioRepo.save(it);
        }

        // 5 Ventas de ejemplo
        for (int i = 0; i < 5; i++) {
            VentasModel v = new VentasModel(
                null,
                faker.name().fullName(),
                LocalDate.now()
                        .minusDays(faker.number().numberBetween(0, 30))
                        .toString(),
                faker.number().randomDouble(2, 100, 5000),
                faker.options().option("PENDIENTE","COMPLETADA","CANCELADA")
            );
            ventasRepo.save(v);
        }
    }
}