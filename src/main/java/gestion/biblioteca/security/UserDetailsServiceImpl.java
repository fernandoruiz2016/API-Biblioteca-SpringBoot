package gestion.biblioteca.security;

import gestion.biblioteca.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inicianco usuario con login {}", username);
        return usuarioRepository.findByUsername(username)
                .map(UserDetailsImpl::new) //Optional<UserDetialsImpl>
                .orElseThrow(() -> new UsernameNotFoundException("El usuario ingresado no existe: "+username));
    }
}