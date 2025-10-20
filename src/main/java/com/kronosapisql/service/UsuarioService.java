package com.kronosapisql.service;


import com.kronosapisql.dto.UsuarioDTO;
import com.kronosapisql.dto.UsuarioTelefoneDTO;
import com.kronosapisql.enums.OpcaoStatus;
import com.kronosapisql.model.*;

import com.kronosapisql.dto.UsuarioFunctionDTO;

import com.kronosapisql.repository.CargoRepository;
import com.kronosapisql.repository.EmpresaRepository;
import com.kronosapisql.repository.SetorRepository;
import com.kronosapisql.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final CargoRepository cargoRepository;
    private final EmpresaRepository empresaRepository;
    private final SetorRepository setorRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, SetorRepository setorRepository, EmpresaRepository empresaRepository, CargoRepository cargoRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.setorRepository = setorRepository;
        this.empresaRepository = empresaRepository;
        this.cargoRepository = cargoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo");
        }
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID " + id));
    }

    public Usuario buscarPorCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF do usuário não pode ser nulo");
        }
        return usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com CPF " + cpf));
    }

    public UsuarioTelefoneDTO buscarTelefonePorCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF do usuário não pode ser nulo");
        }

        Usuario usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com CPF " + cpf));

        return new UsuarioTelefoneDTO(usuario.getId(), usuario.getTelefone());
    }




    public List<UsuarioFunctionDTO> listarFuncionariosGestor(Long idGestor) {
        return usuarioRepository.listarFuncionariosGestorRaw(idGestor)
                .stream()
                .map(UsuarioFunctionDTO::fromRow)
                .toList();
    }

    public List<Usuario> listar() {
        return this.usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        usuarioRepository.findByCpf(usuario.getCpf()).ifPresent(u -> {
            throw new IllegalArgumentException("Já existe um usuário com este CPF");
        });
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Long criarUsuario(UsuarioDTO dto) {
        Setor setor = setorRepository.findById(dto.getSetorId())
                .orElseThrow(() -> new RuntimeException("Setor não encontrado"));

        Usuario gestor = usuarioRepository.findById(dto.getGestorId())
                .orElseThrow(() -> new RuntimeException("Gestor não encontrado"));

        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        Cargo cargo = cargoRepository.findById(dto.getCargoId())
                .orElseThrow(() -> new RuntimeException("Cargo não encontrado"));

        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .gestor(gestor)
                .booleanGestor(dto.getBooleanGestor())
                .empresa(empresa)
                .setor(setor)
                .cargo(cargo)
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .senha(senhaCriptografada)
                .foto(dto.getFoto())
                .ativo(dto.getAtivo())
                .build();

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioSalvo.getId();
    }

    public Usuario atualizar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new EntityNotFoundException("Usuário não encontrado com ID " + usuario.getId());
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarParcial(Long id, Map<String, Object> campos) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (campos.containsKey("nome")) {
            usuario.setNome((String) campos.get("nome"));
        }

        if (campos.containsKey("email")) {
            usuario.setEmail((String) campos.get("email"));
        }
        if (campos.containsKey("senha")) {
            String senhaCriptografada = passwordEncoder.encode((String) campos.get("senha"));
            usuario.setSenha(senhaCriptografada);
        }

        if (campos.containsKey("booleanGestor")) {
            usuario.setBooleanGestor((Boolean) campos.get("booleanGestor"));
        }

        if (campos.containsKey("ativo")) {
            usuario.setAtivo((Boolean) campos.get("ativo"));
        }

        if (campos.containsKey("setor")) {
            Object setorObj = campos.get("setor");
            if (setorObj instanceof Map<?, ?> setorMap && setorMap.containsKey("id")) {
                Long setorId = ((Number) setorMap.get("id")).longValue();
                Setor setor = setorRepository.findById(setorId)
                        .orElseThrow(() -> new RuntimeException("Setor não encontrado"));
                usuario.setSetor(setor);
            }
        }
        if (campos.containsKey("telefone")){
            usuario.setTelefone((String) campos.get("telefone"));
        }

        if (campos.containsKey("cargo")) {
            Object cargoObj = campos.get("cargo");
            if (cargoObj instanceof Map<?, ?> cargoMap && cargoMap.containsKey("id")) {
                Long cargoId = ((Number) cargoMap.get("id")).longValue();
                Cargo cargo = cargoRepository.findById(cargoId)
                        .orElseThrow(() -> new RuntimeException("Cargo não encontrado"));
                usuario.setCargo(cargo);
            }
        }
        if (campos.containsKey("foto")){
            usuario.setFoto((String) campos.get("foto"));
        }


        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario loginApp(String cpf, String senha) {
        Usuario usuario = buscarPorCpf(cpf);
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new BadCredentialsException("Senha inválida");
        }

        if (!usuario.getAtivo()) {
            throw new BadCredentialsException("Usuário inativo");
        }
        return usuario;
    }

    public Usuario loginPlataforma(String cpf, String senha) {
        Usuario usuario = loginApp(cpf, senha);
        if (!usuario.getBooleanGestor()) {
            throw new BadCredentialsException("Acesso restrito a gestores");
        }
        return usuario;
    }
}
