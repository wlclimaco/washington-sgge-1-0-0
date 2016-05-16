package com.qat.samples.sysmgmt.nf.model.classes.nota;

import java.math.BigDecimal;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFImpostoDevolvido extends NFBase {

    private String percentualDevolucao;

    private NFInformacaoImpostoDevolvido informacaoIPIDevolvido;

    public String getPercentualDevolucao() {
        return this.percentualDevolucao;
    }

    public void setPercentualDevolucao(final BigDecimal percentualDevolucao) {
    //    this.percentualDevolucao = BigDecimalParser.tamanho5Com2CasasDecimais(percentualDevolucao);
    }

  //  public NFInformacaoImpostoDevolvido getInformacaoIPIDevolvido() {
  //      return this.informacaoIPIDevolvido;
  //  }

    public void setInformacaoIPIDevolvido(final NFInformacaoImpostoDevolvido informacaoIPIDevolvido) {
 //       this.informacaoIPIDevolvido = informacaoIPIDevolvido;
    }
}