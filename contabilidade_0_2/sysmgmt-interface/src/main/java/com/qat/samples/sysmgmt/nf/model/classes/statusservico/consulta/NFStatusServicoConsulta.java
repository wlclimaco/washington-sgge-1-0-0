package com.qat.samples.sysmgmt.nf.model.classes.statusservico.consulta;

import com.qat.samples.sysmgmt.nf.model.classes.NFAmbiente;
import com.qat.samples.sysmgmt.nf.model.classes.NFBase;
import com.qat.samples.sysmgmt.nf.model.classes.NFUnidadeFederativa;

public class NFStatusServicoConsulta extends NFBase {

    private String versao;

    //@Element(name = "tpAmb", required = true)
    private NFAmbiente ambiente;

    //@Element(name = "cUF", required = true)
    private NFUnidadeFederativa uf;

    //@Element(name = "xServ", required = true)
    private String servico;

    public NFStatusServicoConsulta() {
        this.versao = null;
        this.ambiente = null;
        this.uf = null;
        this.servico = null;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public NFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public void setAmbiente(final NFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public NFUnidadeFederativa getUf() {
        return this.uf;
    }

    public void setUf(final NFUnidadeFederativa uf) {
        this.uf = uf;
    }

    public String getServico() {
        return this.servico;
    }

    public void setServico(final String servico) {
        this.servico = servico;
    }
}