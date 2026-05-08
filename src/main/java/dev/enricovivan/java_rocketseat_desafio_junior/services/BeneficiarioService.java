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

    // cadastrar beneficiario simples ✅
    public Beneficiario cadastrarBeneficiario(Beneficiario beneficiario) {
        return this.beneficiarioRepository.save(beneficiario);
    }

    // cadastra beneficiario com documentos (manualmente)
    @Transactional
    public Beneficiario cadastrarBeneficiarioComDocumentosManuelamente(Beneficiario beneficiario){

        // pega os documentos que vieram na requisição (se houverem)
        List<Documento> documentosBeneficiario = beneficiario.getDocumentos();

        // limpa os documentos da requisicao
        beneficiario.setDocumentos(null);

        // primerio cadastra o beneficiario
        Beneficiario beneficiarioCadastrado = beneficiarioRepository.save(beneficiario);

        // se há documentos, salvamos
        if (documentosBeneficiario != null && !documentosBeneficiario.isEmpty()) {

            documentosBeneficiario.stream()
                .forEach(doc -> {
                    doc.setBeneficiario(beneficiarioCadastrado);
                });

            List<Documento> documentosBeneficiarioSalvos = documentoRepository.saveAll(documentosBeneficiario);

            // volta a referencia para o beneficiario para que possa retornar na requisição
            beneficiarioCadastrado.setDocumentos(documentosBeneficiarioSalvos);
        }

        return beneficiarioCadastrado;

    }

    // cadastra o beneficiario e os documetnos automaticamente (usando o cascade.all do model)
    @Transactional
    public Beneficiario cadastrarBeneficiarioComDocumentosAutomaticamente(Beneficiario beneficiario){

        // verifica se veio com documentos
        if (beneficiario.getDocumentos() != null && !beneficiario.getDocumentos().isEmpty()) {
            // definimos quem é o pai do documento:
            beneficiario.getDocumentos().stream()
                .forEach(doc -> {
                    doc.setBeneficiario(beneficiario);
                });
        }

        // salva o beneficiario c documentos
        // como usamos CascadeType.ALL na entidade, salvar o beneficiário 
        // salva automaticamente todos os documentos associados a ele
        return beneficiarioRepository.save(beneficiario);

    }

    // listar todos os beneficiarios cadastrados ✅
    public List<Beneficiario> listarBeneficiarios() {
        return this.beneficiarioRepository.findAll();
    }

    // lista os documetos do beneficiario ✅
    @Transactional(readOnly=true)
    public List<Documento> getDocumentosDoBeneficiario(UUID uuidBeneficiario) {

        // verifica se o beneficiário existe
        if (beneficiarioRepository.existsById(uuidBeneficiario)) {
            throw new EntityNotFoundException("Beneficiário não encontrado");
        }

        return documentoRepository.findByBeneficiarioId(uuidBeneficiario);
    }

    // atualiza beneficiario ✅
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

    // remove um baneficiario ✅
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
