package com.Biblioteca.Prestamos.Servicios;

import com.Biblioteca.Prestamos.Entidades.Libro;
import com.Biblioteca.Prestamos.Repositorio.libroRepositorio;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class libroServicio {

    private libroRepositorio repositorio;

    public libroServicio(libroRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public ArrayList<Libro> listarLibros(){ //Consultar Todos
        return (ArrayList<Libro>) repositorio.findAll();
    }

    public Optional<Libro> buscarLibro(String isbn){
        return repositorio.findById(isbn);
    }

    public ArrayList<Libro> buscarAutor(String autor){
        return repositorio.findByAutor(autor);
    }

    public String agregarLibro(Libro libro){
        if(!buscarLibro(libro.getIsbn()).isPresent()) {
            repositorio.save(libro);
            return "Libro Registrado exitosamente.";
        }else{
           return "El libro ya existe. " ;
        }
    }

    public String actualizarLibro(Libro libro){
        if(buscarLibro(libro.getIsbn()).isPresent()){
            repositorio.save(libro);
            return "Libro actualizado exitosamente";
        }else {
            return "El libro a modificar no existe.";
        }
    }

    public String actualizarEditorial(String isbn, String editorial){
       if(buscarLibro(isbn).isPresent()){
         Libro libro1=repositorio.findById(isbn).get();
         libro1.setEditorial(editorial);
         repositorio.save(libro1);
         return "Editorial Actualizada";
       } else{
           return "Libro a actualizar no existe";
       }
    }

    public String eliminarLibro(String isbn){
        if(buscarLibro(isbn).isPresent()){
            repositorio.deleteById(isbn);
            return "Libro Eliminado";
        }else{
            return "El Libro a eliminar no existe";
        }
    }

    public Libro actualizarCampo(String isbn, Map<Object,Object> libroMap){
        Libro libro=repositorio.findById(isbn).get();

        libroMap.forEach((key,value)->{
            Field campo= ReflectionUtils.findField(Libro.class, (String) key);
            campo.setAccessible(true);
            ReflectionUtils.setField(campo, libro, value);
        });
        return repositorio.save(libro);
    }
}