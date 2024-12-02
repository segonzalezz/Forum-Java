package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.model.Usuario;
import org.example.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/login")
@SessionAttributes("usuario")
public class LoginController {
    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String mostrarFormularioLogin() {
        return "login"; // Renderiza el template login.html
    }

    @PostMapping
    public String autenticarUsuario(
            @RequestParam("correo") String correo,
            @RequestParam("contrasena") String contrasena,
            HttpSession session,
            Model model) {
        try {
            Usuario usuario = usuarioService.autenticarUsuario(correo, contrasena);
            session.setAttribute("usuario", usuario); // Guarda el usuario en la sesión
            return "redirect:/menu";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login"; // Vuelve al login con el mensaje de error
        }
    }

}
