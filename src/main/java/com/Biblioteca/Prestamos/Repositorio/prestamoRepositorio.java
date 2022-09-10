package com.Biblioteca.Prestamos.Repositorio;

import com.Biblioteca.Prestamos.Entidades.Estudiante;
import com.Biblioteca.Prestamos.Entidades.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface prestamoRepositorio extends JpaRepository<Prestamo,Integer> {

    ArrayList<Prestamo> findByEstudiante(Estudiante estudiante);
}
