package org.example.controller;

import org.example.config.JwtUtil;
import org.example.dto.AuthRequest;
import org.example.dto.UserDetailsAdapter;
import org.example.model.Usuario;
import org.example.service.MyUserDetailsService;
import org.example.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UsuarioService usuarioService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Nombre del archivo HTML en templates/login.html
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            // Imprimir la contraseña proporcionada para debugging
            System.out.println("Contraseña proporcionada desde el cliente: " + authRequest.getPassword());

            // Autenticar al usuario
            Usuario usuario = usuarioService.autenticarUsuario(authRequest.getEmail(), authRequest.getPassword());

            // Adaptar Usuario a UserDetails
            UserDetails userDetails = new UserDetailsAdapter(usuario);

            // Generar token JWT
            String token = jwtUtil.generateToken(userDetails);

            // Retornar la respuesta con token y mensaje de éxito, además de URL de redirección
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "message", "Login exitoso.",
                    "redirectUrl", "/menu" // URL del menú para redirección
            ));
        } catch (RuntimeException e) {
            // Manejo de errores y retorno de mensaje apropiado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "message", "Credenciales incorrectas",
                    "error", e.getMessage()
            ));
        }
    }

}
