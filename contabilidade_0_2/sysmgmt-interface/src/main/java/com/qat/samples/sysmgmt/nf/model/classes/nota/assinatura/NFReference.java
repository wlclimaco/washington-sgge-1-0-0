package com.qat.samples.sysmgmt.nf.model.classes.nota.assinatura;

import java.util.List;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFReference extends NFBase {

    private String uri;

    private List<NFTransform> transform;

    private NFDigestMethod digestMethod;

    private String digestValue;

    public String getUri() {
        return this.uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public List<NFTransform> getTransform() {
        return this.transform;
    }

    public void setTransform(final List<NFTransform> transform) {
        this.transform = transform;
    }

    public NFDigestMethod getDigestMethod() {
        return this.digestMethod;
    }

    public void setDigestMethod(final NFDigestMethod digestMethod) {
        this.digestMethod = digestMethod;
    }

    public String getDigestValue() {
        return this.digestValue;
    }

    public void setDigestValue(final String digestValue) {
        this.digestValue = digestValue;
    }
}