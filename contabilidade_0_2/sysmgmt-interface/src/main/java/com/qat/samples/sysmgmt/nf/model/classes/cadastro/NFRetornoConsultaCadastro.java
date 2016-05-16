package com.qat.samples.sysmgmt.nf.model.classes.cadastro;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;


public class NFRetornoConsultaCadastro extends NFBase {


    private String versao;

    private NFRetornoConsultaCadastroDados dados;

    public NFRetornoConsultaCadastroDados getDados() {
        return this.dados;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setDados(final NFRetornoConsultaCadastroDados dados) {
        this.dados = dados;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }
}