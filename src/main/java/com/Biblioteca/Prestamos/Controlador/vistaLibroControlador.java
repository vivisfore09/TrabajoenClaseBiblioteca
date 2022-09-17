package com.Biblioteca.Prestamos.Controlador;

import com.Biblioteca.Prestamos.Entidades.Libro;
import com.Biblioteca.Prestamos.Servicios.libroServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class vistaLibroControlador {

    libroServicio servicio;

    public vistaLibroControlador(libroServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/Prueba/{nombre}")
    public String prueba(@PathVariable("nombre") String nombre, Model model){
        model.addAttribute("nombre",nombre);
        return "Libros";
    }

    @GetMapping("/Libros")
    public String prueba(Model model){
        List<Libro> lista=this.servicio.listarLibros();
        model.addAttribute("lista",lista);
        return "Libros";
    }

    @GetMapping("/formLibro")
    public String mostrarFormulario(Model model){
       // model.addAttribute()
        return "registrarLibro";
    }
}