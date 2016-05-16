package com.qat.samples.sysmgmt.nf.model.classes.nota;

import java.util.List;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFNotaInfoCobranca extends NFBase {
    //@Element(name = "fat", required = false)
    private NFNotaInfoFatura fatura;

    //@ElementList(entry = "dup", inline = true, required = false)
    private List<NFNotaInfoDuplicata> duplicatas;

    public void setFatura(final NFNotaInfoFatura fatura) {
        this.fatura = fatura;
    }

    public void setDuplicatas(final List<NFNotaInfoDuplicata> duplicatas) {
      //  ListValidador.tamanho120(duplicatas);
        this.duplicatas = duplicatas;
    }

    public NFNotaInfoFatura getFatura() {
        return this.fatura;
    }

    public List<NFNotaInfoDuplicata> getDuplicatas() {
        return this.duplicatas;
    }
}