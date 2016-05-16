package com.qat.samples.sysmgmt.nf.model.classes.cadastro;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFRetornoConsultaCadastroEndereco extends NFBase {

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String codigoMunicipio;

    private String descricaoMunicipio;

    private String cep;

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(final String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(final String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(final String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(final String bairro) {
        this.bairro = bairro;
    }

    public String getCodigoMunicipio() {
        return this.codigoMunicipio;
    }

    public void setCodigoMunicipio(final String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getDescricaoMunicipio() {
        return this.descricaoMunicipio;
    }

    public void setDescricaoMunicipio(final String descricaoMunicipio) {
        this.descricaoMunicipio = descricaoMunicipio;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(final String cep) {
        this.cep = cep;
    }
}