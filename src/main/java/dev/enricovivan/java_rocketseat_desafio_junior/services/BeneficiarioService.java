package dev.enricovivan.java_rocketseat_desafio_junior.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.enricovivan.java_rocketseat_desafio_junior.models.Beneficiario;
import dev.enricovivan.java_rocketseat_desafio_junior.models.Documento;
import dev.enricovivan.java_rocketseat_desafio_junior.repositories.BeneficiarioRepository;
import dev.enricovivan.java_rocketseat_desafio_junior.repositories.DocumentoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
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
    @Transactional(readOnly=true)
    public List<Documento> getDocumentosDoBeneficiario(UUID uuidBeneficiario) {

        // verifica se o beneficiário existe
        if (beneficiarioRepository.existsById(uuidBeneficiario)) {
            throw new EntityNotFoundException("Beneficiário não encontrado");
        }

        return documentoRepository.findByBeneficiarioId(uuidBeneficiario);
    }

    // atualiza beneficiario
    public Beneficiario updateBeneficiario(UUID uuiBeneficiario, Beneficiario req){
        return beneficiarioRepository.findById(uuiBeneficiario)
            .map(beneficiario -> {
                beneficiario.setNome(req.getNome());
                beneficiario.setDataNascimento(req.getDataNascimento());
                beneficiario.setDataAtualizacao(new Date());

                return beneficiarioRepository.save(beneficiario);
            })
            .orElseThrow(() -> new EntityNotFoundException("Beneficiário não encontrado"));
    }

    // remove um baneficiario
    public Beneficiario deleteBeneficiario(UUID uuid){
        
        // como delete by id retorna void, e  precisamos retornar o beneficiario nessa função
        // entçao temos que armazenar o beneficiario seele for encontrado
        Beneficiario beneficiario = beneficiarioRepository.findById(uuid)
            .orElseThrow(() -> new EntityNotFoundException("Beneficiário não encontrado"));

        // remove 
        beneficiarioRepository.delete(beneficiario);

        return beneficiario;
        
    }


}
