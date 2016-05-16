package com.qat.samples.sysmgmt.nf.model.classes.nota.consulta;

import java.math.BigDecimal;

import com.qat.samples.sysmgmt.nf.model.classes.NFAmbiente;
import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFNotaConsulta extends NFBase {

    private String versao;

    private NFAmbiente ambiente;

    private String servico;

    private String chave;

    public void setVersao(final BigDecimal versao) {
      //  this.versao = BigDecimalParser.tamanho4Com2CasasDecimais(versao);
    }

    public void setAmbiente(final NFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public void setServico(final String servico) {
        this.servico = servico;
    }

    public void setChave(final String chave) {
    //    StringValidador.exatamente44N(chave);
        this.chave = chave;
    }

    public String getVersao() {
        return this.versao;
    }

    public NFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public String getServico() {
        return this.servico;
    }

    public String getChave() {
        return this.chave;
    }
}