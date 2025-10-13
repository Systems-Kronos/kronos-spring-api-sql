package com.kronosapisql.repository;

import com.kronosapisql.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByCpf(String cpf);

    @Query(value = "SELECT * FROM fn_funcinarios_gestor(:idGestor)", nativeQuery = true)
    List<Object[]> listarFuncionariosGestorRaw(@Param("idGestor") Long idGestor);
}
