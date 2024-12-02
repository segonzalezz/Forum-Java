package org.example.controller;

import org.example.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/menu")
@SessionAttributes("usuario")
public class MenuController {

    @GetMapping
    public String mostrarMenu(@ModelAttribute("usuario") Usuario usuario, Model model) {
        if (usuario == null) {
            return "redirect:/login"; // Si no hay usuario en sesión, redirige al login
        }
        model.addAttribute("usuario", usuario);
        return "menu"; // Renderiza el template del menú
    }
}
