package com.qat.samples.sysmgmt.nf.model.classes.nota.assinatura;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFKeyInfo extends NFBase {

    private NFX509Data data;

    public NFX509Data getData() {
        return this.data;
    }

    public void setData(final NFX509Data data) {
        this.data = data;
    }
}