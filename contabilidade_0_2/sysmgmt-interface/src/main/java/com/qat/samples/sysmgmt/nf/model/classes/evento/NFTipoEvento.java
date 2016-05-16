package com.qat.samples.sysmgmt.nf.model.classes.evento;

import java.math.BigDecimal;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;


public class NFTipoEvento extends NFBase {

    private String versao;


    private String descricaoEvento;

    // Carta correcao

    private String textoCorrecao;;


    private String condicaoUso;

    public void setVersao(final BigDecimal versao) {
      //  this.versao = BigDecimalParser.tamanho5Com2CasasDecimais(versao);
    }

    public void setDescricaoEvento(final String descricaoEvento) {
      //  StringValidador.tamanho5a60(descricaoEvento);
        this.descricaoEvento = descricaoEvento;
    }

    public String getDescricaoEvento() {
        return this.descricaoEvento;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setCondicaoUso(final String condicaoUso) {
        this.condicaoUso = condicaoUso;
    }

    public void setTextoCorrecao(final String textoCorrecao) {
        this.textoCorrecao = textoCorrecao;
    }

    public String getCondicaoUso() {
        return this.condicaoUso;
    }

    public String getTextoCorrecao() {
        return this.textoCorrecao;
    }
}