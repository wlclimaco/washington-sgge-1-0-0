package com.qat.samples.sysmgmt.nf.model.classes.nota;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFNotaInfoSuplementar extends NFBase {

	//@Element(data = true, name = "qrCode", required = true)
	private String qrCode;

	public void setQrCode(final String qrCode) {
		//StringValidador.tamanho100a600(qrCode);
		this.qrCode = qrCode;
	}

	public String getQrCode() {
		return this.qrCode;
	}
}