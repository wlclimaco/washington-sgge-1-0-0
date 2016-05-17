package com.qat.samples.sysmgmt.nf.model.classes.cadastro;

import com.fincatto.nfe310.validadores.StringValidador;
import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFInfoConsultaCadastro extends NFBase {

    private String servico;

    private String uf;

    private String inscricaoEstadual;

    private String cnpj;

    private String cpf;

    public String getServico() {
        return this.servico;
    }

    public void setServico(final String servico) {
        this.servico = servico;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(final String uf) {
        this.uf = uf;
    }

    public String getInscricaoEstadual() {
        return this.inscricaoEstadual;
    }

    public void setInscricaoEstadual(final String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(final String cnpj) {
        if (this.cpf != null) {
            throw new IllegalStateException("Nao pode setar CPF pois CNPJ ja esta setado");
        }

        StringValidador.cnpj(cnpj);
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(final String cpf) {
        if (this.cnpj != null) {
            throw new IllegalStateException("Nao pode setar CPF pois CNPJ ja esta setado");
        }

        StringValidador.cpf(cpf);
        this.cpf = cpf;
    }
}