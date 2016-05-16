package com.qat.samples.sysmgmt.nf.model.classes.cadastro;

import java.time.LocalDate;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;
import com.qat.samples.sysmgmt.nf.model.classes.NFUnidadeFederativa;



public class NFRetornoConsultaCadastroSituacaoCadastral extends NFBase {


    private String inscricaoEstadual;


    private String cnpj;


    private String cpf;


    private NFUnidadeFederativa uf;

    private NFSituacaoContribuinte situacaoContribuinte;

    private NFIndicadorContribuinteNFe indicadorContribuinteNFe;

    private NFIndicadorContribuinteCTe indicaodrContribuinteCTe;

    private String razaoSocial;

    private String nomeFantasia;

    private String regimeApuracaoICMSContribuinte;

    private String cnaePrincipalContribuinte;

    private LocalDate dataInicioAtividade;

    private LocalDate dataUltimaModificacaoSituacaoCadastral;

    private LocalDate dataOcorrenciaBaixa;

    private String inscricaoEstadualUnica;

    private String inscricaoEstadualAtual;

    private NFRetornoConsultaCadastroEndereco endereco;

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
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(final String cpf) {
        this.cpf = cpf;
    }

    public NFUnidadeFederativa getUf() {
        return this.uf;
    }

    public void setUf(final NFUnidadeFederativa uf) {
        this.uf = uf;
    }

    public NFSituacaoContribuinte getSituacaoContribuinte() {
        return this.situacaoContribuinte;
    }

    public void setSituacaoContribuinte(final NFSituacaoContribuinte situacaoContribuinte) {
        this.situacaoContribuinte = situacaoContribuinte;
    }

    public NFIndicadorContribuinteNFe getIndicadorContribuinteNFe() {
        return this.indicadorContribuinteNFe;
    }

    public void setIndicadorContribuinteNFe(final NFIndicadorContribuinteNFe indicadorContribuinteNFe) {
        this.indicadorContribuinteNFe = indicadorContribuinteNFe;
    }

    public NFIndicadorContribuinteCTe getIndicaodrContribuinteCTe() {
        return this.indicaodrContribuinteCTe;
    }

    public void setIndicaodrContribuinteCTe(final NFIndicadorContribuinteCTe indicaodrContribuinteCTe) {
        this.indicaodrContribuinteCTe = indicaodrContribuinteCTe;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public void setRazaoSocial(final String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return this.nomeFantasia;
    }

    public void setNomeFantasia(final String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRegimeApuracaoICMSContribuinte() {
        return this.regimeApuracaoICMSContribuinte;
    }

    public void setRegimeApuracaoICMSContribuinte(final String regimeApuracaoICMSContribuinte) {
        this.regimeApuracaoICMSContribuinte = regimeApuracaoICMSContribuinte;
    }

    public String getCnaePrincipalContribuinte() {
        return this.cnaePrincipalContribuinte;
    }

    public void setCnaePrincipalContribuinte(final String cnaePrincipalContribuinte) {
        this.cnaePrincipalContribuinte = cnaePrincipalContribuinte;
    }

    public LocalDate getDataInicioAtividade() {
        return this.dataInicioAtividade;
    }

    public void setDataInicioAtividade(final LocalDate dataInicioAtividade) {
        this.dataInicioAtividade = dataInicioAtividade;
    }

    public LocalDate getDataUltimaModificacaoSituacaoCadastral() {
        return this.dataUltimaModificacaoSituacaoCadastral;
    }

    public void setDataUltimaModificacaoSituacaoCadastral(final LocalDate dataUltimaModificacaoSituacaoCadastral) {
        this.dataUltimaModificacaoSituacaoCadastral = dataUltimaModificacaoSituacaoCadastral;
    }

    public LocalDate getDataOcorrenciaBaixa() {
        return this.dataOcorrenciaBaixa;
    }

    public void setDataOcorrenciaBaixa(final LocalDate dataOcorrenciaBaixa) {
        this.dataOcorrenciaBaixa = dataOcorrenciaBaixa;
    }

    public String getInscricaoEstadualUnica() {
        return this.inscricaoEstadualUnica;
    }

    public void setInscricaoEstadualUnica(final String inscricaoEstadualUnica) {
        this.inscricaoEstadualUnica = inscricaoEstadualUnica;
    }

    public String getInscricaoEstadualAtual() {
        return this.inscricaoEstadualAtual;
    }

    public void setInscricaoEstadualAtual(final String inscricaoEstadualAtual) {
        this.inscricaoEstadualAtual = inscricaoEstadualAtual;
    }

    public NFRetornoConsultaCadastroEndereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(final NFRetornoConsultaCadastroEndereco endereco) {
        this.endereco = endereco;
    }
}