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

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO usuario (
            cNmUsuario, nCdGestor, bGestor, nCdEmpresa, nCdSetor, nCdCargo,
            cCPF, cTelefone, cEmail, cSenha, cFoto, bAtivo
        ) VALUES (
            :nome, :gestorId, :booleanGestor, :empresaId, :setorId, :cargoId,
            :cpf, :telefone, :email, :senha, :foto, :ativo
        )
        """, nativeQuery = true)
    void inserirUsuarioNative(
            @Param("nome") String nome,
            @Param("gestorId") Long gestorId,
            @Param("booleanGestor") Boolean booleanGestor,
            @Param("empresaId") Long empresaId,
            @Param("setorId") Long setorId,
            @Param("cargoId") Long cargoId,
            @Param("cpf") String cpf,
            @Param("telefone") String telefone,
            @Param("email") String email,
            @Param("senha") String senha,
            @Param("foto") String foto,
            @Param("ativo") Boolean ativo
    );
    @Query(value = "SELECT * FROM fn_funcinarios_gestor(:idGestor)", nativeQuery = true)
    List<Object[]> listarFuncionariosGestorRaw(@Param("idGestor") Long idGestor);
}
