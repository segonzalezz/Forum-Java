package org.example.controller;

import org.example.model.Usuario;
import org.example.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    private final UsuarioService usuarioService;

    public UsuarioViewController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/vista")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios"; // Renderiza el template usuarios.html
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "crear_usuario"; // Renderiza el template crear_usuario.html
    }

    @PostMapping
    public String guardarUsuario(Usuario usuario) {
        usuarioService.crearUsuario(usuario);
        return "redirect:/usuarios/vista"; // Redirige a la lista de usuarios
    }
}
