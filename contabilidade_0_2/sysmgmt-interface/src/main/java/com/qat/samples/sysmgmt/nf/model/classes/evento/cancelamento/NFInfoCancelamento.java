package com.qat.samples.sysmgmt.nf.model.classes.evento.cancelamento;

import com.qat.samples.sysmgmt.nf.model.classes.evento.NFTipoEvento;

public class NFInfoCancelamento extends NFTipoEvento {

    private String protocoloAutorizacao;


    private String justificativa;

    public void setJustificativa(final String justificativa) {
     //   StringValidador.tamanho15a256(justificativa);
        this.justificativa = justificativa;
    }

    public void setProtocoloAutorizacao(final String protocoloAutorizacao) {
     //   StringValidador.exatamente15N(protocoloAutorizacao);
        this.protocoloAutorizacao = protocoloAutorizacao;
    }

    public String getJustificativa() {
        return this.justificativa;
    }

    public String getProtocoloAutorizacao() {
        return this.protocoloAutorizacao;
    }

    @Override
    public void setCondicaoUso(final String condicaoUso) {
        throw new UnsupportedOperationException("Evento de cancelamento nao possui condicao de uso");
    }

    @Override
    public void setTextoCorrecao(final String textoCorrecao) {
        throw new UnsupportedOperationException("Evento de cancelamento nao possui texto de correcao");
    }
}