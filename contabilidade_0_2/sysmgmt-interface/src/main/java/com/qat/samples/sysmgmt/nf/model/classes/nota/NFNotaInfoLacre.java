package com.qat.samples.sysmgmt.nf.model.classes.nota;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFNotaInfoLacre extends NFBase {

    private String numeroLacres;

//    public void setNumeroLacre(final String numeroLacres) {
//        StringValidador.tamanho60(numeroLacres);
//        this.numeroLacres = numeroLacres;
//    }

    public String getNumeroLacres() {
        return this.numeroLacres;
    }
}