package com.kronosapisql.controller.docs;

import com.kronosapisql.dto.ReportDTO;
import com.kronosapisql.dto.ReportFunctionDTO;
import com.kronosapisql.dto.StatusUpdateDTO;
import com.kronosapisql.model.Report;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Reports", description = "Operações relacionadas aos reports")
@RequestMapping("/api/report")
public interface ReportControllerDocs {

    @Operation(summary = "Lista todos os reports")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reports retornados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum report encontrado")
    })
    @GetMapping("/listar")
    ResponseEntity<List<Report>> listarReport();

    @Operation(summary = "Lista todos os reports dos usuários de um gestor específico baseado na function")
    @GetMapping("/selecionarFunction/{idGestor}")
    List<ReportFunctionDTO> listarReportsFuncionariosGestor(@PathVariable Long idGestor);

    @Operation(summary = "Lista report pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Report retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Report não encontrado")
    })
    @GetMapping("/selecionar/{id}")
    ResponseEntity<Report> buscarPorId(@PathVariable Long id);

    @Operation(summary = "Lista report pelo status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reports retornados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum report encontrado para o status informado")
    })
    @GetMapping("/selecionarStatus/{status}")
    ResponseEntity<List<Report>> buscarPorStatus(@PathVariable String status);

    @Operation(summary = "Inserir um novo report")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Report criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/adicionar")
    ResponseEntity<Report> inserirReport(@RequestBody ReportDTO dto);

    @Operation(summary = "Atualiza um report")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Report atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Report não encontrado")
    })
    @PutMapping("/atualizar")
    ResponseEntity<String> atualizarReport(@Valid @RequestBody Report report);

    @Operation(summary = "Atualiza o status de um report")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Report não encontrado")
    })
    @PutMapping("/atualizarStatus/{id}")
    ResponseEntity<String> atualizarStatus(@PathVariable Long id, @RequestBody StatusUpdateDTO dto);

    @Operation(summary = "Deleta um Report pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Report deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Report não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    ResponseEntity<Void> deletarReport(@PathVariable Long id);
}

