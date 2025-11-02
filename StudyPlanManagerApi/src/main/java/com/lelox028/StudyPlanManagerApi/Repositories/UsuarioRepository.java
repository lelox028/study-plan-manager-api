package com.lelox028.StudyPlanManagerApi.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.lelox028.StudyPlanManagerApi.Models.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
}
