package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
    Optional<Usuario> findByCorreoElectronicoAndContrasena(String correo, String contrasena);
}
