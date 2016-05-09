package com.fincatto.nfe310.classes.cadastro;

import com.fincatto.nfe310.classes.NFBase;
import com.fincatto.nfe310.validadores.StringValidador;
import org.simpleframework.xml.Element;

public class NFInfoConsultaCadastro extends NFBase {

    @Element(name = "xServ", required = true)
    private String servico;

    @Element(name = "UF", required = true)
    private String uf;

    @Element(name = "IE", required = false)
    private String inscricaoEstadual;

    @Element(name = "CNPJ", required = false)
    private String cnpj;

    @Element(name = "CPF", required = false)
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