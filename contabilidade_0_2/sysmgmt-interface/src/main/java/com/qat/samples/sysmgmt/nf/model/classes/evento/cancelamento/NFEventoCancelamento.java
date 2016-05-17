package com.qat.samples.sysmgmt.nf.model.classes.evento.cancelamento;

import java.math.BigDecimal;

import com.fincatto.nfe310.validadores.BigDecimalParser;
import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFEventoCancelamento extends NFBase {

    private String versao;

    private NFInfoEventoCancelamento infoEvento;

    private String assinatura;

    public void setVersao(final BigDecimal versao) {
        this.versao = BigDecimalParser.tamanho5Com2CasasDecimais(versao);
    }

    public NFInfoEventoCancelamento getInfoEvento() {
        return this.infoEvento;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setInfoEvento(final NFInfoEventoCancelamento infoEvento) {
        this.infoEvento = infoEvento;
    }

    public void setAssinatura(final String assinatura) {
        this.assinatura = assinatura;
    }

    public String getAssinatura() {
        return this.assinatura;
    }
}