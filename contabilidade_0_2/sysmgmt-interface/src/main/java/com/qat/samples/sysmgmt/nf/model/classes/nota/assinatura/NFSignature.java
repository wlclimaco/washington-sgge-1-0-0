package com.qat.samples.sysmgmt.nf.model.classes.nota.assinatura;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;


public class NFSignature extends NFBase {


    private NFSignedInfo signedInfo;

    private String signatureValue;

    private NFKeyInfo keyInfo;

    public NFSignedInfo getSignedInfo() {
        return this.signedInfo;
    }

    public void setSignedInfo(final NFSignedInfo signedInfo) {
        this.signedInfo = signedInfo;
    }

    public String getSignatureValue() {
        return this.signatureValue;
    }

    public void setSignatureValue(final String signatureValue) {
        this.signatureValue = signatureValue;
    }

    public NFKeyInfo getKeyInfo() {
        return this.keyInfo;
    }

    public void setKeyInfo(final NFKeyInfo keyInfo) {
        this.keyInfo = keyInfo;
    }
}