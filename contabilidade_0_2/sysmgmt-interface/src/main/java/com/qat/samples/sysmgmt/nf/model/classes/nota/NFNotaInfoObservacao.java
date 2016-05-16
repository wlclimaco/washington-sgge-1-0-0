package com.qat.samples.sysmgmt.nf.model.classes.nota;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFNotaInfoObservacao extends NFBase {

    private String identificacaoCampo;

    //@Element(name = "xTexto", required = true)
    private String conteudoCampo;
//
//    public void setIdentificacaoCampo(final String identificacaoCampo) {
//        StringValidador.tamanho20(identificacaoCampo);
//        this.identificacaoCampo = identificacaoCampo;
//    }
//
//    public void setConteudoCampo(final String conteudoCampo) {
//        StringValidador.tamanho60(conteudoCampo);
//        this.conteudoCampo = conteudoCampo;
//    }

    public String getConteudoCampo() {
        return this.conteudoCampo;
    }

    public String getIdentificacaoCampo() {
        return this.identificacaoCampo;
    }
}