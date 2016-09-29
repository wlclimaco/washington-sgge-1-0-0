/** create by system gera-java version 1.0.0 28/09/2016 12:13 : 30*/
package com.qat.samples.sysmgmt.nfe.model;


import java.util.List;

import com.qat.samples.sysmgmt.nf.model.NotaFiscalItens;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfo extends NFBase
{

    /** The econtabil identificador for the NFNotaInfo. */
    private String identificador;

    /** The econtabil versao for the NFNotaInfo. */
    private String versao;

    /** The econtabil identificacao for the NFNotaInfo. */
    private NFNotaInfoIdentificacao identificacao;

    /** The econtabil emitente for the NFNotaInfo. */
    private NFNotaInfoEmitente emitente;

    /** The econtabil avulsa for the NFNotaInfo. */
    private NFNotaInfoAvulsa avulsa;

    /** The econtabil destinatario for the NFNotaInfo. */
    private NFNotaInfoDestinatario destinatario;

    /** The econtabil retirada for the NFNotaInfo. */
    private NFNotaInfoLocal retirada;

    /** The econtabil entrega for the NFNotaInfo. */
    private NFNotaInfoLocal entrega;

    /** The econtabil pessoasAutorizadasDownloadNFe for the NFNotaInfo. */
    private List<NFPessoaAutorizadaDownloadNFe> pessoasautorizadasdownloadnfe;

    /** The econtabil itens for the NFNotaInfo. */
    private List<NotaFiscalItens> itens;

    /** The econtabil total for the NFNotaInfo. */
    private NFNotaInfoTotal total;

    /** The econtabil transporte for the NFNotaInfo. */
    private NFNotaInfoTransporte transporte;

    /** The econtabil cobranca for the NFNotaInfo. */
    private NFNotaInfoCobranca cobranca;

    /** The econtabil pagamentos for the NFNotaInfo. */
    private List<NFNotaInfoPagamento> pagamentos;

    /** The econtabil informacoesAdicionais for the NFNotaInfo. */
    private NFNotaInfoInformacoesAdicionais informacoesadicionais;

    /** The econtabil exportacao for the NFNotaInfo. */
    private NFNotaInfoExportacao exportacao;

    /** The econtabil compra for the NFNotaInfo. */
    private NFNotaInfoCompra compra;

    /** The econtabil cana for the NFNotaInfo. */
    private NFNotaInfoCana cana;



    /**
     * Default constructor.
     */
    public NFNotaInfo()
    {
        super();
    }



	public String getIdentificador() {
		return identificador;
	}



	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}



	public String getVersao() {
		return versao;
	}



	public void setVersao(String versao) {
		this.versao = versao;
	}



	public NFNotaInfoIdentificacao getIdentificacao() {
		return identificacao;
	}



	public void setIdentificacao(NFNotaInfoIdentificacao identificacao) {
		this.identificacao = identificacao;
	}



	public NFNotaInfoEmitente getEmitente() {
		return emitente;
	}



	public void setEmitente(NFNotaInfoEmitente emitente) {
		this.emitente = emitente;
	}



	public NFNotaInfoAvulsa getAvulsa() {
		return avulsa;
	}



	public void setAvulsa(NFNotaInfoAvulsa avulsa) {
		this.avulsa = avulsa;
	}



	public NFNotaInfoDestinatario getDestinatario() {
		return destinatario;
	}



	public void setDestinatario(NFNotaInfoDestinatario destinatario) {
		this.destinatario = destinatario;
	}



	public NFNotaInfoLocal getRetirada() {
		return retirada;
	}



	public void setRetirada(NFNotaInfoLocal retirada) {
		this.retirada = retirada;
	}



	public NFNotaInfoLocal getEntrega() {
		return entrega;
	}



	public void setEntrega(NFNotaInfoLocal entrega) {
		this.entrega = entrega;
	}



	public List<NFPessoaAutorizadaDownloadNFe> getPessoasautorizadasdownloadnfe() {
		return pessoasautorizadasdownloadnfe;
	}



	public void setPessoasautorizadasdownloadnfe(List<NFPessoaAutorizadaDownloadNFe> pessoasautorizadasdownloadnfe) {
		this.pessoasautorizadasdownloadnfe = pessoasautorizadasdownloadnfe;
	}



	public List<NotaFiscalItens> getItens() {
		return itens;
	}



	public void setItens(List<NotaFiscalItens> itens) {
		this.itens = itens;
	}



	public NFNotaInfoTotal getTotal() {
		return total;
	}



	public void setTotal(NFNotaInfoTotal total) {
		this.total = total;
	}



	public NFNotaInfoTransporte getTransporte() {
		return transporte;
	}



	public void setTransporte(NFNotaInfoTransporte transporte) {
		this.transporte = transporte;
	}



	public NFNotaInfoCobranca getCobranca() {
		return cobranca;
	}



	public void setCobranca(NFNotaInfoCobranca cobranca) {
		this.cobranca = cobranca;
	}



	public List<NFNotaInfoPagamento> getPagamentos() {
		return pagamentos;
	}



	public void setPagamentos(List<NFNotaInfoPagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}



	public NFNotaInfoInformacoesAdicionais getInformacoesadicionais() {
		return informacoesadicionais;
	}



	public void setInformacoesadicionais(NFNotaInfoInformacoesAdicionais informacoesadicionais) {
		this.informacoesadicionais = informacoesadicionais;
	}



	public NFNotaInfoExportacao getExportacao() {
		return exportacao;
	}



	public void setExportacao(NFNotaInfoExportacao exportacao) {
		this.exportacao = exportacao;
	}



	public NFNotaInfoCompra getCompra() {
		return compra;
	}



	public void setCompra(NFNotaInfoCompra compra) {
		this.compra = compra;
	}



	public NFNotaInfoCana getCana() {
		return cana;
	}



	public void setCana(NFNotaInfoCana cana) {
		this.cana = cana;
	}



	@Override
	public String toString() {
		return "NFNotaInfo [getIdentificador()=" + getIdentificador() + ", getVersao()=" + getVersao()
				+ ", getIdentificacao()=" + getIdentificacao() + ", getEmitente()=" + getEmitente() + ", getAvulsa()="
				+ getAvulsa() + ", getDestinatario()=" + getDestinatario() + ", getRetirada()=" + getRetirada()
				+ ", getEntrega()=" + getEntrega() + ", getPessoasautorizadasdownloadnfe()="
				+ getPessoasautorizadasdownloadnfe() + ", getItens()=" + getItens() + ", getTotal()=" + getTotal()
				+ ", getTransporte()=" + getTransporte() + ", getCobranca()=" + getCobranca() + ", getPagamentos()="
				+ getPagamentos() + ", getInformacoesadicionais()=" + getInformacoesadicionais() + ", getExportacao()="
				+ getExportacao() + ", getCompra()=" + getCompra() + ", getCana()=" + getCana() + ", toString()="
				+ super.toString() + "]";
	}



 }
