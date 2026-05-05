package dev.enricovivan.java_rocketseat_desafio_junior.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.enricovivan.java_rocketseat_desafio_junior.models.Beneficiario;
import dev.enricovivan.java_rocketseat_desafio_junior.models.Documento;
import dev.enricovivan.java_rocketseat_desafio_junior.repositories.BeneficiarioRepository;
import dev.enricovivan.java_rocketseat_desafio_junior.repositories.DocumentoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BeneficiarioService {
    
    private final BeneficiarioRepository beneficiarioRepository;
    private final DocumentoRepository documentoRepository;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository, DocumentoRepository documentoRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.documentoRepository = documentoRepository;
    }

    // cadastrar beneficiario
    public Beneficiario cadastrarBeneficiario(Beneficiario beneficiario) {
        return this.beneficiarioRepository.save(beneficiario);
    }

    // listar todos os beneficiarios cadastrados
    public List<Beneficiario> listarBeneficiarios() {
        return this.beneficiarioRepository.findAll();
    }

    // lista os documetos do beneficiario
    public List<Documento> getDocumentosDoBeneficiario(UUID uuid) {

        // verifica se existe beneficiario
        Optional<Beneficiario> beneficiario = beneficiarioRepository.findById(uuid);

        if (beneficiario.isEmpty()) {
            throw new EntityNotFoundException("Beneficiario nao encontrado");
        }

        List<Documento> documentos = documentoRepository.findByBeneficiarioId(uuid);
        return documentos;
    }

    // atualizar dados do beneficiario
    public Beneficiario updateBeneficiario(UUID uuid, Beneficiario beneficiario){
        
        // verifica se existe beneficiario
        Optional<Beneficiario> beneficiarioDb = beneficiarioRepository.findById(uuid);

        if (beneficiarioDb.isEmpty()) {
            throw new EntityNotFoundException("Beneficiario nao encontrado");
        }

        beneficiarioDb.get().setNome(beneficiario.getNome());
        beneficiarioDb.get().setDataNascimento(beneficiario.getDataNascimento());
        beneficiarioDb.get().setDataAtualizacao(new Date());

        return beneficiarioRepository.save(beneficiarioDb.get());
    }

    // remove um baneficiario
    public Beneficiario deleteBeneficiario(UUID uuid){
        
        // verifica se existe beneficiario
        Optional<Beneficiario> beneficiarioDb = beneficiarioRepository.findById(uuid);

        if (beneficiarioDb.isEmpty()) {
            throw new EntityNotFoundException("Beneficiario nao encontrado");
        }
        
        beneficiarioRepository.delete(beneficiarioDb.get());
        return beneficiarioDb.get();
    }


}
