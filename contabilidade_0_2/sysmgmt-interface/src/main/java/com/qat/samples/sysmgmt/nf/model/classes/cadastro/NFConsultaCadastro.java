package com.qat.samples.sysmgmt.nf.model.classes.cadastro;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.fincatto.nfe310.classes.NFBase;
import com.fincatto.nfe310.classes.cadastro.NFInfoConsultaCadastro;

@Root(name = "ConsCad")
@Namespace(reference = "http://www.portalfiscal.inf.br/nfe")
public class NFConsultaCadastro extends NFBase {
    @Attribute(name = "versao", required = true)
    private String versao;

    @Element(name = "infCons", required = true)
    private NFInfoConsultaCadastro consultaCadastro;

    public NFInfoConsultaCadastro getConsultaCadastro() {
        return this.consultaCadastro;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setConsultaCadastro(final NFInfoConsultaCadastro consultaCadastro) {
        this.consultaCadastro = consultaCadastro;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }
}