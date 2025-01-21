package com.finalboss.foroalura.domain.curso.repository;

import com.finalboss.foroalura.domain.curso.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Override
    Page<Curso> findAll(Pageable pageable);
}
