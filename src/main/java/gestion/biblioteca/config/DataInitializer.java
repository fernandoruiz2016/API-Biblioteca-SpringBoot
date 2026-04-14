package gestion.biblioteca.config;

import gestion.biblioteca.entity.Rol;
import gestion.biblioteca.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;

    @Override
    public void run(String... args) throws Exception {
        if (rolRepository.count() == 0) {
            Rol admin = Rol.builder()
                    .nombre("ADMIN")
                    .estado(1)
                    .usuarioCreacion("SYSTEM")
                    .fechaCreacion(LocalDateTime.now())
                    .build();

            Rol user = Rol.builder()
                    .nombre("USER")
                    .estado(1)
                    .usuarioCreacion("SYSTEM")
                    .fechaCreacion(LocalDateTime.now())
                    .build();

            rolRepository.save(admin);
            rolRepository.save(user);
        }
    }
}