package com.qat.samples.sysmgmt.nf.model.classes.nota.assinatura;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFX509Data extends NFBase {

    private String x509certificate;

    public String getX509certificate() {
        return this.x509certificate;
    }

    public void setX509certificate(final String x509certificate) {
        this.x509certificate = x509certificate;
    }
}