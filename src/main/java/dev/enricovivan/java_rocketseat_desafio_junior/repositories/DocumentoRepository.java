package dev.enricovivan.java_rocketseat_desafio_junior.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.enricovivan.java_rocketseat_desafio_junior.models.Documento;
import java.util.List;


@Repository
public interface DocumentoRepository extends JpaRepository<Documento, UUID>{
    
    List<Documento> findByBeneficiarioId(UUID id);

}
