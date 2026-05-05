package dev.enricovivan.java_rocketseat_desafio_junior.dtos.Beneficiario;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import dev.enricovivan.java_rocketseat_desafio_junior.models.Documento;

public class BeneficiarioResponseDto {
    
    private UUID id;
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private Date dataInclusao;
    private Date dataAtualizacao;
    private List<Documento> documentos;
    
    public BeneficiarioResponseDto(UUID id, String nome, String telefone, Date dataNascimento, Date dataInclusao,
            Date dataAtualizacao, List<Documento> documentos) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
        this.documentos = documentos;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    

}
