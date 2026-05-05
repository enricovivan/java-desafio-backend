package dev.enricovivan.java_rocketseat_desafio_junior.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.enricovivan.java_rocketseat_desafio_junior.dtos.Beneficiario.BeneficiarioRequestDto;
import dev.enricovivan.java_rocketseat_desafio_junior.dtos.Beneficiario.BeneficiarioResponseDto;
import dev.enricovivan.java_rocketseat_desafio_junior.models.Beneficiario;
import dev.enricovivan.java_rocketseat_desafio_junior.services.BeneficiarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {
    
    private final BeneficiarioService beneficiarioService;

    public BeneficiarioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }

    // cadastrar beneficiario
    @PostMapping
    public ResponseEntity<BeneficiarioResponseDto> cadastrarBeneficiario(
        @RequestBody @Valid BeneficiarioRequestDto beneficiarioRequestDto
    ){

        Beneficiario beneficiario = new Beneficiario( 
            beneficiarioRequestDto.getNome(), 
            beneficiarioRequestDto.getTelefone(), 
            beneficiarioRequestDto.getDataNascimento(), 
            new Date(), 
            new Date()
        );

        beneficiarioService.cadastrarBeneficiario(beneficiario);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BeneficiarioResponseDto(
            beneficiario.getId(),
            beneficiario.getNome(),
            beneficiario.getTelefone(),
            beneficiario.getDataNascimento(),
            beneficiario.getDataInclusao(),
            beneficiario.getDataAtualizacao(),
            beneficiario.getDocumentos()
        ));
    }    

    // TODO: cadastrar beneficiario com seus documentos

    // listar beneficiarios cadastrados
    @GetMapping
    public ResponseEntity<List<BeneficiarioResponseDto>> getAllBeneficiarios() {

        var beneficiarios = beneficiarioService.listarBeneficiarios();

        // mapeia pra dto
        var beneficiariosMap = beneficiarios.stream()
            .map(b -> new BeneficiarioResponseDto(
                b.getId(),
                b.getNome(),
                b.getTelefone(),
                b.getDataNascimento(),
                b.getDataInclusao(),
                b.getDataAtualizacao(),
                b.getDocumentos()
            )).toList();

        return ResponseEntity.status(HttpStatus.OK).body(beneficiariosMap);

    }
    
    // listar todos os documentos de um beneficiario

    // atualizar dados cadastrais de um beneficiario

    // deletar um beneficiario


}
