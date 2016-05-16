package com.qat.samples.sysmgmt.nf.model.classes.nota.assinatura;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFTransform extends NFBase {

    private String algorithm;

    public void setAlgorithm(final String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }
}