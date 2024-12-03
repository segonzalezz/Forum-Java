package org.example.controller;

import org.example.config.JwtUtil;
import org.example.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.config.JwtUtil.*;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final JwtUtil jwtUtil;

    public MenuController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public String mostrarMenu(Model model, @RequestHeader("Authorization") String token) {
        // Opcional: Validar token si es necesario
        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        model.addAttribute("usuario", username); // Puedes cargar datos adicionales del usuario
        return "menu";
    }
}
