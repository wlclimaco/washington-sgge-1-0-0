package com.qat.samples.sysmgmt.nf.model.classes.nota;

import java.math.BigDecimal;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;
import com.qat.samples.sysmgmt.nf.model.classes.NFProtocolo;

//@Root(name = "nfeProc")
//@Namespace(reference = "http://www.portalfiscal.inf.br/nfe")
public class NFNotaProcessada extends NFBase {

  //  @Attribute(name = "versao")
    private String versao;

    //@Element(name = "NFe")
    private NFNota nota;

    //@Element(name = "protNFe")
    private NFProtocolo protocolo;

    public NFNota getNota() {
        return this.nota;
    }

    public String getVersao() {
        return this.versao;
    }

    public NFProtocolo getProtocolo() {
        return this.protocolo;
    }

    public void setNota(final NFNota nota) {
        this.nota = nota;
    }

    public void setProtocolo(final NFProtocolo protocolo) {
        this.protocolo = protocolo;
    }

    public void setVersao(final BigDecimal versao) {
     //   this.versao = BigDecimalParser.tamanho4Com2CasasDecimais(versao);
    }
}