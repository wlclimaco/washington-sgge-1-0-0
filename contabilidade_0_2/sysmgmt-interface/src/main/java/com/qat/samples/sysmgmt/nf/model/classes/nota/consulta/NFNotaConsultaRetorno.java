package com.qat.samples.sysmgmt.nf.model.classes.nota.consulta;

import java.time.LocalDateTime;
import java.util.List;

import com.qat.samples.sysmgmt.nf.model.classes.NFAmbiente;
import com.qat.samples.sysmgmt.nf.model.classes.NFBase;
import com.qat.samples.sysmgmt.nf.model.classes.NFProtocolo;
import com.qat.samples.sysmgmt.nf.model.classes.NFUnidadeFederativa;
import com.qat.samples.sysmgmt.nf.model.classes.evento.cancelamento.NFRetornoCancelamento;


public class NFNotaConsultaRetorno extends NFBase {

    private String versao;

    private NFAmbiente ambiente;

    private String versaoAplicacao;

    private String status;

    private String motivo;

    private NFUnidadeFederativa uf;

    private LocalDateTime dataHoraRecibo;

    private String chave;

    protected NFProtocolo protocolo;

    private NFRetornoCancelamento protocoloCancelamento;

    private List<NFProtocoloEvento> protocoloEvento;

    public NFNotaConsultaRetorno() {
        this.versao = null;
        this.ambiente = null;
        this.versaoAplicacao = null;
        this.status = null;
        this.motivo = null;
        this.uf = null;
        this.chave = null;
        this.protocolo = null;
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

    public String getVersaoAplicacao() {
        return this.versaoAplicacao;
    }

    public void setVersaoAplicacao(final String versaoAplicacao) {
        this.versaoAplicacao = versaoAplicacao;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
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

    public String getChave() {
        return this.chave;
    }

    public List<NFProtocoloEvento> getProtocoloEvento() {
        return this.protocoloEvento;
    }

    public NFRetornoCancelamento getProtocoloCancelamento() {
        return this.protocoloCancelamento;
    }

    public void setChave(final String chave) {
        this.chave = chave;
    }

    public NFProtocolo getProtocolo() {
        return this.protocolo;
    }

    public void setProtocolo(final NFProtocolo protocolo) {
        this.protocolo = protocolo;
    }

    public LocalDateTime getDataHoraRecibo() {
        return this.dataHoraRecibo;
    }

    public void setDataHoraRecibo(final LocalDateTime dataHoraRecibo) {
        this.dataHoraRecibo = dataHoraRecibo;
    }

    public void setProtocoloEvento(final List<NFProtocoloEvento> protocoloEvento) {
        this.protocoloEvento = protocoloEvento;
    }

    public void setProtocoloCancelamento(final NFRetornoCancelamento protocoloCancelamento) {
        this.protocoloCancelamento = protocoloCancelamento;
    }
}