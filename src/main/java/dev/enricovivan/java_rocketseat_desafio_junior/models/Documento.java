package dev.enricovivan.java_rocketseat_desafio_junior.models;

import java.util.Date;
import java.util.UUID;

import dev.enricovivan.java_rocketseat_desafio_junior.common.enums.TipoDocumento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "Documento")
public class Documento {
    
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    private String descricao;

    private Date dataInclusao;

    private Date dataAtualizacao;

    public Documento() {
    }

    public Documento(UUID id, TipoDocumento tipoDocumento, String descricao, Date dataInclusao, Date dataAtualizacao) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.descricao = descricao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

}
