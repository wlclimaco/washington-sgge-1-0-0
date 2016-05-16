package com.qat.samples.sysmgmt.nf.model.classes.nota.assinatura;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFSignedInfo extends NFBase {

    private NFCanonicalizationMethod canonicalizationMethod;

    private NFSignatureMethod signatureMethod;

    private NFReference reference;

    public NFCanonicalizationMethod getCanonicalizationMethod() {
        return this.canonicalizationMethod;
    }

    public void setCanonicalizationMethod(final NFCanonicalizationMethod canonicalizationMethod) {
        this.canonicalizationMethod = canonicalizationMethod;
    }

    public NFSignatureMethod getSignatureMethod() {
        return this.signatureMethod;
    }

    public void setSignatureMethod(final NFSignatureMethod signatureMethod) {
        this.signatureMethod = signatureMethod;
    }

    public NFReference getReference() {
        return this.reference;
    }

    public void setReference(final NFReference reference) {
        this.reference = reference;
    }
}