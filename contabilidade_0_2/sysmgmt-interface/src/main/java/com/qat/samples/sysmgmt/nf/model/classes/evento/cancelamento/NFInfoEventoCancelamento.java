package com.qat.samples.sysmgmt.nf.model.classes.evento.cancelamento;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.axis2.databinding.types.xsd.DateTime;

import com.fincatto.nfe310.validadores.IntegerValidador;
import com.fincatto.nfe310.validadores.StringValidador;
import com.qat.samples.sysmgmt.nf.model.classes.NFAmbiente;
import com.qat.samples.sysmgmt.nf.model.classes.NFBase;
import com.qat.samples.sysmgmt.nf.model.classes.NFUnidadeFederativa;

public class NFInfoEventoCancelamento extends NFBase {

    private String id;

    private NFUnidadeFederativa orgao;

    private NFAmbiente ambiente;

    private String cnpj;

    private String cpf;

    private String chave;

    private Date dataHoraEvento;

    private String codigoEvento;

    private Integer numeroSequencialEvento;

    private String versaoEvento;

    private NFInfoCancelamento cancelamento;

    public void setOrgao(final NFUnidadeFederativa orgao) {
        this.orgao = orgao;
    }

    public void setVersaoEvento(final BigDecimal versaoEvento) {
     //   this.versaoEvento = BigDecimalParser.tamanho5Com2CasasDecimais(versaoEvento);
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        StringValidador.exatamente54(id);
        this.id = id;
    }

    public NFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public void setAmbiente(final NFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(final String cnpj) {
        if (this.cpf != null) {
            throw new IllegalStateException("CPF ja foi setado");
        }
        StringValidador.cnpj(cnpj);
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(final String cpf) {
        if (this.cnpj != null) {
            throw new IllegalStateException("CNPJ ja foi setado");
        }
        StringValidador.cpf(cpf);
        this.cpf = cpf;
    }

    public String getChave() {
        return this.chave;
    }

    public void setChave(final String chave) {
        StringValidador.exatamente44N(chave);
        this.chave = chave;
    }

    public DateTime getDataHoraEvento() {
        return this.dataHoraEvento;
    }

    public void setDataHoraEvento(final DateTime dataHoraEvento) {
        this.dataHoraEvento = dataHoraEvento;
    }

    public String getCodigoEvento() {
        return this.codigoEvento;
    }

    public void setCodigoEvento(final String codigoEvento) {
        StringValidador.exatamente6N(codigoEvento);
        this.codigoEvento = codigoEvento;
    }

    public int getNumeroSequencialEvento() {
        return this.numeroSequencialEvento;
    }

    public void setNumeroSequencialEvento(final int numeroSequencialEvento) {
        IntegerValidador.tamanho1a2(numeroSequencialEvento);
        this.numeroSequencialEvento = numeroSequencialEvento;
    }

    public String getVersaoEvento() {
        return this.versaoEvento;
    }

    public NFInfoCancelamento getCancelamento() {
        return this.cancelamento;
    }

    public void setCancelamento(final NFInfoCancelamento cancelamento) {
        this.cancelamento = cancelamento;
    }

    public NFUnidadeFederativa getOrgao() {
        return this.orgao;
    }
}