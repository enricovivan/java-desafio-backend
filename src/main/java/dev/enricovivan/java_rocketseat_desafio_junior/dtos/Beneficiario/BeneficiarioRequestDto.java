package dev.enricovivan.java_rocketseat_desafio_junior.dtos.Beneficiario;

import java.util.Date;

import jakarta.validation.constraints.NotNull;

public class BeneficiarioRequestDto {
    
    @NotNull
    private String nome;

    @NotNull
    private Date dataNascimento;

    @NotNull
    private String telefone;

    public BeneficiarioRequestDto(@NotNull String nome, @NotNull Date dataNascimento, @NotNull String telefone) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    

}
