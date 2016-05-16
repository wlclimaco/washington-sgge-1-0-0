package com.qat.samples.sysmgmt.nf.model.classes.nota.consulta;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;
import com.qat.samples.sysmgmt.nf.model.classes.evento.NFEvento;
import com.qat.samples.sysmgmt.nf.model.classes.evento.NFEventoRetorno;

public class NFProtocoloEvento extends NFBase {

    private String versao;

    private NFEvento evento;

    private NFEventoRetorno eventoRetorno;

    public NFEvento getEvento() {
        return this.evento;
    }

    public void setEvento(final NFEvento evento) {
        this.evento = evento;
    }

    public NFEventoRetorno getInfoEventoRetorno() {
        return this.eventoRetorno;
    }

    public void setEventoRetorno(final NFEventoRetorno infoEventoRetorno) {
        this.eventoRetorno = infoEventoRetorno;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }
}