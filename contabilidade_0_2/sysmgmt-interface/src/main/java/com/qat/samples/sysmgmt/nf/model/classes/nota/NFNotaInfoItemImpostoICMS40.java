package com.qat.samples.sysmgmt.nf.model.classes.nota;

import java.math.BigDecimal;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;
import com.qat.samples.sysmgmt.nf.model.classes.NFNotaInfoImpostoTributacaoICMS;
import com.qat.samples.sysmgmt.nf.model.classes.NFNotaMotivoDesoneracaoICMS;
import com.qat.samples.sysmgmt.nf.model.classes.NFOrigem;

public class NFNotaInfoItemImpostoICMS40 extends NFBase {
    //@Element(name = "orig", required = true)
    private NFOrigem origem;

    //@Element(name = "CST", required = true)
    private NFNotaInfoImpostoTributacaoICMS situacaoTributaria;

    //@Element(name = "vICMSDeson", required = false)
    private String valorICMSDesoneracao;

    //@Element(name = "motDesICMS", required = false)
    private NFNotaMotivoDesoneracaoICMS motivoDesoneracaoICMS;

    public void setOrigem(final NFOrigem origem) {
        this.origem = origem;
    }

    public void setSituacaoTributaria(final NFNotaInfoImpostoTributacaoICMS situacaoTributaria) {
        this.situacaoTributaria = situacaoTributaria;
    }

//    public void setValorICMSDesoneracao(final BigDecimal valorICMSDesoneracao) {
//        this.valorICMSDesoneracao = BigDecimalParser.tamanho15Com2CasasDecimais(valorICMSDesoneracao);
//    }

    public void setMotivoDesoneracaoICMS(final NFNotaMotivoDesoneracaoICMS motivoDesoneracaoICMS) {
        this.motivoDesoneracaoICMS = motivoDesoneracaoICMS;
    }

    public NFOrigem getOrigem() {
        return this.origem;
    }

    public NFNotaInfoImpostoTributacaoICMS getSituacaoTributaria() {
        return this.situacaoTributaria;
    }

    public String getValorICMSDesoneracao() {
        return this.valorICMSDesoneracao;
    }

    public NFNotaMotivoDesoneracaoICMS getMotivoDesoneracaoICMS() {
        return this.motivoDesoneracaoICMS;
    }
}