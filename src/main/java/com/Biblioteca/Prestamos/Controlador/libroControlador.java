package com.Biblioteca.Prestamos.Controlador;

import com.Biblioteca.Prestamos.Entidades.Libro;
import com.Biblioteca.Prestamos.Servicios.libroServicio;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class libroControlador {

    private libroServicio servicio;

    public libroControlador(libroServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/ListarLibros")
    public List<Libro> listar(){
        return servicio.listarLibros();
    }

    @GetMapping("/BuscarLibro/{id}")
    public Optional<Libro> buscarLibro(@PathVariable("id") String isbn){
        return servicio.buscarLibro(isbn);
    }

    @GetMapping("/BuscarAutor/{autor}")
    public ArrayList<Libro> buscarAutor(@PathVariable("autor") String autor){
        return servicio.buscarAutor(autor);
    }

    @PostMapping("/AgregarLibro")
    public String agregarLibro(@RequestBody Libro libro){
        return servicio.agregarLibro(libro);
    }

    @PutMapping("/ActualizarLibro")
    public String actualizarLibro(@RequestBody Libro libro){
        return  servicio.actualizarLibro(libro);
    }

    @PatchMapping("/ActualizarEdit/{isbn}/{edit}")
    public String actualizarEdit(@PathVariable("isbn") String isbn, @PathVariable("edit") String editorial){
        return servicio.actualizarEditorial(isbn, editorial);
    }

    @PatchMapping("/ActualizarCampo/{isbn}")
    public Libro actualizarCampo(@PathVariable("isbn") String isbn,@RequestBody Map<Object,Object> libroMap){
        return servicio.actualizarCampo(isbn,libroMap);
    }

    @DeleteMapping("/EliminarLibro/{id}")
    public String eliminarLibro(@PathVariable("id") String isbn){
        return servicio.eliminarLibro(isbn);
    }
}
