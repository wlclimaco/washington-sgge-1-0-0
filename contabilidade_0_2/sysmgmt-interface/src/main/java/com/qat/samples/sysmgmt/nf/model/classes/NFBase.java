package com.qat.samples.sysmgmt.nf.model.classes;

import java.io.StringWriter;

import org.simpleframework.xml.core.Persister;

public abstract class NFBase {

    @Override
    public String toString() {
        final Persister persister = new Persister();
        try (StringWriter writer = new StringWriter()) {
            persister.write(this, writer);
            return writer.toString();
        } catch (final Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}