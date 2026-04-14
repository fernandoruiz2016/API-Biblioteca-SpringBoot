package gestion.biblioteca.config;

import gestion.biblioteca.entity.Rol;
import gestion.biblioteca.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;

    @Override
    public void run(String... args) throws Exception {
        // Un solo método run que centraliza la lógica
        log.info("Iniciando la verificación de roles en la base de datos...");

        crearRolSiNoExiste("ADMIN", "Administrador del sistema");
        crearRolSiNoExiste("USER", "Usuario estándar");
    }

    private void crearRolSiNoExiste(String nombre, String descripcion) {
        if (rolRepository.findByNombre(nombre).isEmpty()) {
            Rol rol = Rol.builder()
                    .nombre(nombre)
                    .descripcion(descripcion)
                    .estado(1)
                    .usuarioCreacion("SYSTEM")
                    .fechaCreacion(LocalDateTime.now())
                    .ipCreacion("127.0.0.1")
                    .build();

            rolRepository.save(rol);
            log.info("Rol {} creado exitosamente.", nombre);
        } else {
            log.info("El rol {} ya existe, saltando creación.", nombre);
        }
    }
}