package com.qat.samples.sysmgmt.nf.model.classes.cadastro;

import java.text.SimpleDateFormat;
import java.util.List;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;
import com.qat.samples.sysmgmt.nf.model.classes.NFUnidadeFederativa;

public class NFRetornoConsultaCadastroDados extends NFBase {

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private String versaoAplicacao;

    private String statusResposta;

    private String motivo;

    private NFUnidadeFederativa uf;

    private String inscricaoEstadual;

    private String cnpj;

    private String cpf;

    private String dataHoraProcessamento;

    private NFUnidadeFederativa ufAutorizadora;

    private List<NFRetornoConsultaCadastroSituacaoCadastral> situacaoCadastral;

    public String getVersaoAplicacao() {
        return this.versaoAplicacao;
    }

    public void setVersaoAplicacao(final String versaoAplicacao) {
        this.versaoAplicacao = versaoAplicacao;
    }

    public String getStatusResposta() {
        return this.statusResposta;
    }

    public void setStatusResposta(final String statusResposta) {
        this.statusResposta = statusResposta;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(final String motivo) {
        this.motivo = motivo;
    }

    public NFUnidadeFederativa getUf() {
        return this.uf;
    }

    public void setUf(final NFUnidadeFederativa uf) {
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
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(final String cpf) {
        this.cpf = cpf;
    }

//    public LocalDateTime getDataHoraProcessamento() {
//        try {
//            return LocalDateTime.fromDateFields(NFRetornoConsultaCadastroDados.DATE_FORMATTER.parse(this.dataHoraProcessamento));
//        } catch (final ParseException e) {
//            throw new IllegalStateException("Houve um problema em parsear a data");
//        }
//    }
//
//    public void setDataHoraProcessamento(final LocalDateTime dataHoraProcessamento) {
//        this.dataHoraProcessamento = NFRetornoConsultaCadastroDados.DATE_FORMATTER.format(dataHoraProcessamento.toDate());
//    }
//
//    public NFUnidadeFederativa getUfAutorizadora() {
//        return this.ufAutorizadora;
//    }
//
//    public void setUfAutorizadora(final NFUnidadeFederativa ufAutorizadora) {
//        this.ufAutorizadora = ufAutorizadora;
//    }

    public List<NFRetornoConsultaCadastroSituacaoCadastral> getSituacaoCadastral() {
        return this.situacaoCadastral;
    }

    public void setSituacaoCadastral(final List<NFRetornoConsultaCadastroSituacaoCadastral> situacaoCadastral) {
        this.situacaoCadastral = situacaoCadastral;
    }
}