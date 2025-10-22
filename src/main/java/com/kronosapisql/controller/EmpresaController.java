package com.kronosapisql.controller;

import com.kronosapisql.controller.docs.EmpresaControllerDocs;
import com.kronosapisql.model.Empresa;
import com.kronosapisql.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpresaController implements EmpresaControllerDocs {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @Override
    public List<Empresa> listarEmpresa() {
        return empresaService.listar();
    }

    @Override
    public ResponseEntity<Empresa> selecionarPeloId(Long id) {
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<String> adicionarEmpresa(@Valid Empresa empresa) {
        empresaService.salvar(empresa);
        return ResponseEntity.ok("Empresa adicionada com sucesso");
    }

    @Override
    public ResponseEntity<String> atualizarEmpresa(@Valid Empresa empresa) {
        empresaService.atualizar(empresa);
        return ResponseEntity.ok("Empresa atualizada com sucesso");
    }

    @Override
    public ResponseEntity<String> deletarEmpresa(Long id) {
        empresaService.deletar(id);
        return ResponseEntity.ok("Empresa removida com sucesso");
    }
}
