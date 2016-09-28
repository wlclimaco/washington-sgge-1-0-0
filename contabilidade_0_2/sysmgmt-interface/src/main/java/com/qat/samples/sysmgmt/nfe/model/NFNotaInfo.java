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
    private List<List<NFPessoaAutorizadaDownloadNFe>> pessoasautorizadasdownloadnfe;

    /** The econtabil itens for the NFNotaInfo. */
    private List<List<NotaFiscalItens>> itens;

    /** The econtabil total for the NFNotaInfo. */
    private NFNotaInfoTotal total;

    /** The econtabil transporte for the NFNotaInfo. */
    private NFNotaInfoTransporte transporte;

    /** The econtabil cobranca for the NFNotaInfo. */
    private NFNotaInfoCobranca cobranca;

    /** The econtabil pagamentos for the NFNotaInfo. */
    private List<List<NFNotaInfoPagamento>> pagamentos;

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


    /**
     * Gets the identificador.
     *
     * @return the identificador
     */
    /**
     * Gets the identificador.
     *
     * @return the identificador
     */
    public String getIdentificador()
    {
        return identificador;
    }

    /**
     * Sets the identificador.
     *
* @param id the identificador to set
 */
public void setIdentificador(String identificador)
{
        this.identificador = identificador;
    }

    /**
     * Gets the versao.
     *
     * @return the versao
     */
    /**
     * Gets the versao.
     *
     * @return the versao
     */
    public String getVersao()
    {
        return versao;
    }

    /**
     * Sets the versao.
     *
* @param id the versao to set
 */
public void setVersao(String versao)
{
        this.versao = versao;
    }

    /**
     * Gets the identificacao.
     *
     * @return the identificacao
     */
    /**
     * Gets the identificacao.
     *
     * @return the identificacao
     */
    public NFNotaInfoIdentificacao getIdentificacao()
    {
        return identificacao;
    }

    /**
     * Sets the identificacao.
     *
* @param id the identificacao to set
 */
public void setIdentificacao(NFNotaInfoIdentificacao identificacao)
{
        this.identificacao = identificacao;
    }

    /**
     * Gets the emitente.
     *
     * @return the emitente
     */
    /**
     * Gets the emitente.
     *
     * @return the emitente
     */
    public NFNotaInfoEmitente getEmitente()
    {
        return emitente;
    }

    /**
     * Sets the emitente.
     *
* @param id the emitente to set
 */
public void setEmitente(NFNotaInfoEmitente emitente)
{
        this.emitente = emitente;
    }

    /**
     * Gets the avulsa.
     *
     * @return the avulsa
     */
    /**
     * Gets the avulsa.
     *
     * @return the avulsa
     */
    public NFNotaInfoAvulsa getAvulsa()
    {
        return avulsa;
    }

    /**
     * Sets the avulsa.
     *
* @param id the avulsa to set
 */
public void setAvulsa(NFNotaInfoAvulsa avulsa)
{
        this.avulsa = avulsa;
    }

    /**
     * Gets the destinatario.
     *
     * @return the destinatario
     */
    /**
     * Gets the destinatario.
     *
     * @return the destinatario
     */
    public NFNotaInfoDestinatario getDestinatario()
    {
        return destinatario;
    }

    /**
     * Sets the destinatario.
     *
* @param id the destinatario to set
 */
public void setDestinatario(NFNotaInfoDestinatario destinatario)
{
        this.destinatario = destinatario;
    }

    /**
     * Gets the retirada.
     *
     * @return the retirada
     */
    /**
     * Gets the retirada.
     *
     * @return the retirada
     */
    public NFNotaInfoLocal getRetirada()
    {
        return retirada;
    }

    /**
     * Sets the retirada.
     *
* @param id the retirada to set
 */
public void setRetirada(NFNotaInfoLocal retirada)
{
        this.retirada = retirada;
    }

    /**
     * Gets the entrega.
     *
     * @return the entrega
     */
    /**
     * Gets the entrega.
     *
     * @return the entrega
     */
    public NFNotaInfoLocal getEntrega()
    {
        return entrega;
    }

    /**
     * Sets the entrega.
     *
* @param id the entrega to set
 */
public void setEntrega(NFNotaInfoLocal entrega)
{
        this.entrega = entrega;
    }

    /**
     * Gets the pessoasautorizadasdownloadnfe.
     *
     * @return the pessoasautorizadasdownloadnfe
     */
    /**
     * Gets the pessoasautorizadasdownloadnfe.
     *
     * @return the pessoasautorizadasdownloadnfe
     */
    public List<List<NFPessoaAutorizadaDownloadNFe>> getPessoasAutorizadasDownloadNFe()
    {
        return pessoasautorizadasdownloadnfe;
    }

    /**
     * Sets the pessoasautorizadasdownloadnfe.
     *
* @param id the pessoasautorizadasdownloadnfe to set
 */
public void setPessoasAutorizadasDownloadNFe(List<List<NFPessoaAutorizadaDownloadNFe>> pessoasautorizadasdownloadnfe)
{
        this.pessoasautorizadasdownloadnfe = pessoasautorizadasdownloadnfe;
    }

    /**
     * Gets the itens.
     *
     * @return the itens
     */
    /**
     * Gets the itens.
     *
     * @return the itens
     */
    public List<List<NotaFiscalItens>> getItens()
    {
        return itens;
    }

    /**
     * Sets the itens.
     *
* @param id the itens to set
 */
public void setItens(List<List<NotaFiscalItens>> itens)
{
        this.itens = itens;
    }

    /**
     * Gets the total.
     *
     * @return the total
     */
    /**
     * Gets the total.
     *
     * @return the total
     */
    public NFNotaInfoTotal getTotal()
    {
        return total;
    }

    /**
     * Sets the total.
     *
* @param id the total to set
 */
public void setTotal(NFNotaInfoTotal total)
{
        this.total = total;
    }

    /**
     * Gets the transporte.
     *
     * @return the transporte
     */
    /**
     * Gets the transporte.
     *
     * @return the transporte
     */
    public NFNotaInfoTransporte getTransporte()
    {
        return transporte;
    }

    /**
     * Sets the transporte.
     *
* @param id the transporte to set
 */
public void setTransporte(NFNotaInfoTransporte transporte)
{
        this.transporte = transporte;
    }

    /**
     * Gets the cobranca.
     *
     * @return the cobranca
     */
    /**
     * Gets the cobranca.
     *
     * @return the cobranca
     */
    public NFNotaInfoCobranca getCobranca()
    {
        return cobranca;
    }

    /**
     * Sets the cobranca.
     *
* @param id the cobranca to set
 */
public void setCobranca(NFNotaInfoCobranca cobranca)
{
        this.cobranca = cobranca;
    }

    /**
     * Gets the pagamentos.
     *
     * @return the pagamentos
     */
    /**
     * Gets the pagamentos.
     *
     * @return the pagamentos
     */
    public List<List<NFNotaInfoPagamento>> getPagamentos()
    {
        return pagamentos;
    }

    /**
     * Sets the pagamentos.
     *
* @param id the pagamentos to set
 */
public void setPagamentos(List<List<NFNotaInfoPagamento>> pagamentos)
{
        this.pagamentos = pagamentos;
    }

    /**
     * Gets the informacoesadicionais.
     *
     * @return the informacoesadicionais
     */
    /**
     * Gets the informacoesadicionais.
     *
     * @return the informacoesadicionais
     */
    public NFNotaInfoInformacoesAdicionais getInformacoesAdicionais()
    {
        return informacoesadicionais;
    }

    /**
     * Sets the informacoesadicionais.
     *
* @param id the informacoesadicionais to set
 */
public void setInformacoesAdicionais(NFNotaInfoInformacoesAdicionais informacoesadicionais)
{
        this.informacoesadicionais = informacoesadicionais;
    }

    /**
     * Gets the exportacao.
     *
     * @return the exportacao
     */
    /**
     * Gets the exportacao.
     *
     * @return the exportacao
     */
    public NFNotaInfoExportacao getExportacao()
    {
        return exportacao;
    }

    /**
     * Sets the exportacao.
     *
* @param id the exportacao to set
 */
public void setExportacao(NFNotaInfoExportacao exportacao)
{
        this.exportacao = exportacao;
    }

    /**
     * Gets the compra.
     *
     * @return the compra
     */
    /**
     * Gets the compra.
     *
     * @return the compra
     */
    public NFNotaInfoCompra getCompra()
    {
        return compra;
    }

    /**
     * Sets the compra.
     *
* @param id the compra to set
 */
public void setCompra(NFNotaInfoCompra compra)
{
        this.compra = compra;
    }

    /**
     * Gets the cana.
     *
     * @return the cana
     */
    /**
     * Gets the cana.
     *
     * @return the cana
     */
    public NFNotaInfoCana getCana()
    {
        return cana;
    }

    /**
     * Sets the cana.
     *
* @param id the cana to set
 */
public void setCana(NFNotaInfoCana cana)
{
        this.cana = cana;
    }


	public List<List<NFPessoaAutorizadaDownloadNFe>> getPessoasautorizadasdownloadnfe() {
		return pessoasautorizadasdownloadnfe;
	}


	public void setPessoasautorizadasdownloadnfe(List<List<NFPessoaAutorizadaDownloadNFe>> pessoasautorizadasdownloadnfe) {
		this.pessoasautorizadasdownloadnfe = pessoasautorizadasdownloadnfe;
	}


	public NFNotaInfoInformacoesAdicionais getInformacoesadicionais() {
		return informacoesadicionais;
	}


	public void setInformacoesadicionais(NFNotaInfoInformacoesAdicionais informacoesadicionais) {
		this.informacoesadicionais = informacoesadicionais;
	}


	@Override
	public String toString() {
		return "NFNotaInfo [getIdentificador()=" + getIdentificador() + ", getVersao()=" + getVersao()
				+ ", getIdentificacao()=" + getIdentificacao() + ", getEmitente()=" + getEmitente() + ", getAvulsa()="
				+ getAvulsa() + ", getDestinatario()=" + getDestinatario() + ", getRetirada()=" + getRetirada()
				+ ", getEntrega()=" + getEntrega() + ", getPessoasAutorizadasDownloadNFe()="
				+ getPessoasAutorizadasDownloadNFe() + ", getItens()=" + getItens() + ", getTotal()=" + getTotal()
				+ ", getTransporte()=" + getTransporte() + ", getCobranca()=" + getCobranca() + ", getPagamentos()="
				+ getPagamentos() + ", getInformacoesAdicionais()=" + getInformacoesAdicionais() + ", getExportacao()="
				+ getExportacao() + ", getCompra()=" + getCompra() + ", getCana()=" + getCana()
				+ ", getPessoasautorizadasdownloadnfe()=" + getPessoasautorizadasdownloadnfe()
				+ ", getInformacoesadicionais()=" + getInformacoesadicionais() + ", toString()=" + super.toString()
				+ "]";
	}





 }
