package com.Biblioteca.Prestamos.Repositorio;

import com.Biblioteca.Prestamos.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface usuarioRepositorio extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
