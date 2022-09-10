package com.Biblioteca.Prestamos.Servicios;

import com.Biblioteca.Prestamos.Entidades.Prestamo;
import com.Biblioteca.Prestamos.Repositorio.estudianteRepositorio;
import com.Biblioteca.Prestamos.Repositorio.libroRepositorio;
import com.Biblioteca.Prestamos.Repositorio.prestamoRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class prestamoServicio {

    private prestamoRepositorio pRepo;
    private libroRepositorio libRepo;
    private estudianteRepositorio estRepo;

    public prestamoServicio(prestamoRepositorio pRepo, libroRepositorio libRepo, estudianteRepositorio estRepo) {
        this.pRepo = pRepo;
        this.libRepo = libRepo;
        this.estRepo = estRepo;
    }

    public Prestamo agregarPrestamo(String isbn, String doc, Prestamo prestamo){

        libRepo.findById(isbn).map(libro -> {
            prestamo.setLibro(libro);
            return libro;
        });
        return estRepo.findById(doc).map(est ->{
            prestamo.setEstudiante(est);
            return pRepo.save(prestamo);
        }).get();

    }

    public ArrayList<Prestamo> porEstudiante(String doc){

        try {
            return estRepo.findById(doc).map(est->{
                return pRepo.findByEstudiante(est);
            }).get();
        }catch(Exception ex){
            System.out.println("Error: "+ex);
        }

        return null;

    }
}
