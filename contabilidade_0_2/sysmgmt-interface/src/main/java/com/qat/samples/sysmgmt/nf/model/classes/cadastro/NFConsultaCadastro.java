package com.qat.samples.sysmgmt.nf.model.classes.cadastro;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;



public class NFConsultaCadastro extends NFBase {

    private String versao;

    private NFInfoConsultaCadastro consultaCadastro;

    public NFInfoConsultaCadastro getConsultaCadastro() {
        return this.consultaCadastro;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setConsultaCadastro(final NFInfoConsultaCadastro consultaCadastro) {
        this.consultaCadastro = consultaCadastro;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }
}