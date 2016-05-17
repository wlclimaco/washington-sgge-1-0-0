package com.qat.samples.sysmgmt.nf.model.classes.evento.cancelamento;

import java.math.BigDecimal;
import java.util.List;

import com.fincatto.nfe310.validadores.BigDecimalParser;
import com.fincatto.nfe310.validadores.ListValidador;
import com.fincatto.nfe310.validadores.StringValidador;
import com.qat.samples.sysmgmt.nf.model.classes.NFBase;


public class NFEnviaEventoCancelamento extends NFBase {

    private String versao;

    private String idLote;

    private List<NFEventoCancelamento> evento;

    public void setVersao(final BigDecimal versao) {
        this.versao = BigDecimalParser.tamanho5Com2CasasDecimais(versao);
    }

    public String getVersao() {
        return this.versao;
    }

    public String getIdLote() {
        return this.idLote;
    }

    public void setIdLote(final String idLote) {
        StringValidador.tamanho15N(idLote);
        this.idLote = idLote;
    }

    public List<NFEventoCancelamento> getEvento() {
        return this.evento;
    }

    public void setEvento(final List<NFEventoCancelamento> evento) {
        ListValidador.tamanho20(evento);
        this.evento = evento;
    }
}