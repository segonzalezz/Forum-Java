package org.example.controller;

import org.example.model.Topico;
import org.example.model.Usuario;
import org.example.service.TopicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/topicos")
@SessionAttributes("usuario")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    // Página para listar los tópicos
    @GetMapping
    public String listarTopicos(Model model) {
        List<Topico> topicos = topicoService.obtenerTodosLosTopicos();
        model.addAttribute("topicos", topicos);
        return "topicos"; // Renderiza el template topicos.html
    }

    // Página para crear un nuevo tópico
    @GetMapping("/nuevo")
    public String mostrarFormularioCrearTopico(@ModelAttribute("usuario") Usuario usuario, Model model) {
        // Solo permite acceso si el usuario está autenticado
        if (usuario == null) {
            return "redirect:/login"; // Redirige al login si no hay usuario en sesión
        }
        model.addAttribute("topico", new Topico()); // Nuevo tópico vacío
        return "crear_topico"; // Renderiza el formulario
    }

    // Guardar el tópico creado
    @PostMapping
    public String crearTopico(@ModelAttribute("usuario") Usuario usuario, @ModelAttribute Topico topico) {
        if (usuario == null) {
            return "redirect:/login";
        }

        System.out.println("Usuario autenticado: " + usuario.getNombre());
        System.out.println("Título del tópico: " + topico.getTitulo());
        System.out.println("Mensaje del tópico: " + topico.getMensaje());

        topico.setAutor(usuario); // Asocia el tópico al usuario autenticado
        topicoService.crearTopico(topico);
        return "redirect:/topicos"; // Redirige a la lista de tópicos
    }

    @GetMapping("/mis-topicos")
    public String listarMisTopicos(@ModelAttribute("usuario") Usuario usuario, Model model) {
        if (usuario == null) {
            return "redirect:/login"; // Redirige al login si no hay usuario en sesión
        }
        List<Topico> misTopicos = topicoService.obtenerTopicosPorUsuario(usuario.getId());
        model.addAttribute("topicos", misTopicos);
        return "topicos"; // Renderiza el template para listar los tópicos del usuario
    }

    @GetMapping("/todos")
    public String listarTodosLosTopicos(Model model) {
        List<Topico> todosLosTopicos = topicoService.obtenerTodosLosTopicos(); // Obtiene todos los tópicos
        model.addAttribute("topicos", todosLosTopicos);
        return "todos_topicos"; // Renderiza el template de todos los tópicos
    }

}
