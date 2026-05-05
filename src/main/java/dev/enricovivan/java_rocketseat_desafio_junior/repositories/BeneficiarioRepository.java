package dev.enricovivan.java_rocketseat_desafio_junior.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.enricovivan.java_rocketseat_desafio_junior.models.Beneficiario;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, UUID>{
    
}
