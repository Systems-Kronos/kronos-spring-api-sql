package com.kronosapisql.repository;

import com.kronosapisql.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    @Query(value = "SELECT * FROM fn_lista_tarefa_usuario(:usuario, :tipoTarefa, :status)", nativeQuery = true)
    List<Object[]> listarTarefasUsuarioRaw(@Param("usuario") Long usuario,
                                           @Param("tipoTarefa") String tipoTarefa,
                                           @Param("status") String status);

    @Query(value = "SELECT * FROM fn_lista_tarefa_usuario_gestor(:idGestor, :tipoTarefa, :status)", nativeQuery = true)
    List<Object[]> listarTarefasUsuarioGestorRaw(@Param("idGestor") Long idGestor,
                                           @Param("tipoTarefa") String tipoTarefa,
                                           @Param("status") String status);
}
