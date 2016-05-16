package com.qat.samples.sysmgmt.nf.model.classes.nota;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;
import com.qat.samples.sysmgmt.nf.model.classes.nota.assinatura.NFSignature;

public class NFNota extends NFBase {

    private long identificadorLocal;

    //@Element(name = "infNFe")
    private NFNotaInfo info;

    //@Element(name = "infNFeSupl", required = false)
    private NFNotaInfoSuplementar infoSuplementar;

    //@Element(name = "Signature", required = false)
    private NFSignature assinatura;

    public void setInfo(final NFNotaInfo info) {
        this.info = info;
    }

    public NFNotaInfo getInfo() {
        return this.info;
    }

    public NFNotaInfoSuplementar getInfoSuplementar() {
		return infoSuplementar;
	}

	public void setInfoSuplementar(NFNotaInfoSuplementar infoSuplementar) {
		this.infoSuplementar = infoSuplementar;
	}

	/**
     * Utilizado para identificacao interna do sistema
     * @param identificadorLocal Identificador.
     */
    public void setIdentificadorLocal(final long identificadorLocal) {
        this.identificadorLocal = identificadorLocal;
    }

    public long getIdentificadorLocal() {
        return this.identificadorLocal;
    }

    public void setAssinatura(final NFSignature assinatura) {
        this.assinatura = assinatura;
    }

    public NFSignature getAssinatura() {
        return this.assinatura;
    }
}