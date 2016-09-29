/** create by system gera-java version 1.0.0 29/09/2016 14:23 : 49*/
package com.qat.samples.sysmgmt.nfe.model;


import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoAvulsa extends ModelCosmeDamiao
{

    /** The econtabil id for the NFNotaInfoAvulsa. */
    private Integer id;

    /** The econtabil cnpj for the NFNotaInfoAvulsa. */
    private String cnpj;

    /** The econtabil orgaoEmitente for the NFNotaInfoAvulsa. */
    private String orgaoEmitente;

    /** The econtabil matriculaAgente for the NFNotaInfoAvulsa. */
    private String matriculaAgente;

    /** The econtabil nomeAgente for the NFNotaInfoAvulsa. */
    private String nomeAgente;

    /** The econtabil fone for the NFNotaInfoAvulsa. */
    private String fone;

    /** The econtabil uf for the NFNotaInfoAvulsa. */
    private String uf;

    /** The econtabil numeroDocumentoArrecadacaoReceita for the NFNotaInfoAvulsa. */
    private String numeroDocumentoArrecadacaoReceita;

    /** The econtabil dataEmissaoDocumentoArrecadacao for the NFNotaInfoAvulsa. */
    private Long dataEmissaoDocumentoArrecadacao;

    /** The econtabil valorTotalConstanteDocumentoArrecadacaoReceita for the NFNotaInfoAvulsa. */
    private String valorTotalConstanteDocumentoArrecadacaoReceita;

    /** The econtabil reparticaoFiscalEmitente for the NFNotaInfoAvulsa. */
    private String reparticaoFiscalEmitente;

    /** The econtabil dataPagamentoDocumentoArrecadacao for the NFNotaInfoAvulsa. */
    private Long dataPagamentoDocumentoArrecadacao;



    /**
     * Default constructor.
     */
    public NFNotaInfoAvulsa()
    {
        super();
    }


    /**
     * Gets the id.
     *
     * @return the id
     */
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * Sets the id.
     *
* @param id the id to set
 */
public void setId(Integer id)
{
        this.id = id;
    }

    /**
     * Gets the cnpj.
     *
     * @return the cnpj
     */
    /**
     * Gets the cnpj.
     *
     * @return the cnpj
     */
    public String getCnpj()
    {
        return cnpj;
    }

    /**
     * Sets the cnpj.
     *
* @param id the cnpj to set
 */
public void setCnpj(String cnpj)
{
        this.cnpj = cnpj;
    }

    /**
     * Gets the orgaoEmitente.
     *
     * @return the orgaoEmitente
     */
    /**
     * Gets the orgaoEmitente.
     *
     * @return the orgaoEmitente
     */
    public String getOrgaoEmitente()
    {
        return orgaoEmitente;
    }

    /**
     * Sets the orgaoemitente.
     *
* @param id the orgaoemitente to set
 */
public void setOrgaoEmitente(String orgaoemitente)
{
        this.orgaoEmitente = orgaoemitente;
    }

    /**
     * Gets the matriculaAgente.
     *
     * @return the matriculaAgente
     */
    /**
     * Gets the matriculaAgente.
     *
     * @return the matriculaAgente
     */
    public String getMatriculaAgente()
    {
        return matriculaAgente;
    }

    /**
     * Sets the matriculaagente.
     *
* @param id the matriculaagente to set
 */
public void setMatriculaAgente(String matriculaagente)
{
        this.matriculaAgente = matriculaagente;
    }

    /**
     * Gets the nomeAgente.
     *
     * @return the nomeAgente
     */
    /**
     * Gets the nomeAgente.
     *
     * @return the nomeAgente
     */
    public String getNomeAgente()
    {
        return nomeAgente;
    }

    /**
     * Sets the nomeagente.
     *
* @param id the nomeagente to set
 */
public void setNomeAgente(String nomeagente)
{
        this.nomeAgente = nomeagente;
    }

    /**
     * Gets the fone.
     *
     * @return the fone
     */
    /**
     * Gets the fone.
     *
     * @return the fone
     */
    public String getFone()
    {
        return fone;
    }

    /**
     * Sets the fone.
     *
* @param id the fone to set
 */
public void setFone(String fone)
{
        this.fone = fone;
    }

    /**
     * Gets the uf.
     *
     * @return the uf
     */
    /**
     * Gets the uf.
     *
     * @return the uf
     */
    public String getUf()
    {
        return uf;
    }

    /**
     * Sets the uf.
     *
* @param id the uf to set
 */
public void setUf(String uf)
{
        this.uf = uf;
    }

    /**
     * Gets the numeroDocumentoArrecadacaoReceita.
     *
     * @return the numeroDocumentoArrecadacaoReceita
     */
    /**
     * Gets the numeroDocumentoArrecadacaoReceita.
     *
     * @return the numeroDocumentoArrecadacaoReceita
     */
    public String getNumeroDocumentoArrecadacaoReceita()
    {
        return numeroDocumentoArrecadacaoReceita;
    }

    /**
     * Sets the numerodocumentoarrecadacaoreceita.
     *
* @param id the numerodocumentoarrecadacaoreceita to set
 */
public void setNumeroDocumentoArrecadacaoReceita(String numerodocumentoarrecadacaoreceita)
{
        this.numeroDocumentoArrecadacaoReceita = numerodocumentoarrecadacaoreceita;
    }

    /**
     * Gets the dataEmissaoDocumentoArrecadacao.
     *
     * @return the dataEmissaoDocumentoArrecadacao
     */
    /**
     * Gets the dataEmissaoDocumentoArrecadacao.
     *
     * @return the dataEmissaoDocumentoArrecadacao
     */
    public Long getDataEmissaoDocumentoArrecadacao()
    {
        return dataEmissaoDocumentoArrecadacao;
    }

    /**
     * Sets the dataemissaodocumentoarrecadacao.
     *
* @param id the dataemissaodocumentoarrecadacao to set
 */
public void setDataEmissaoDocumentoArrecadacao(Long dataemissaodocumentoarrecadacao)
{
        this.dataEmissaoDocumentoArrecadacao = dataemissaodocumentoarrecadacao;
    }

    /**
     * Gets the valorTotalConstanteDocumentoArrecadacaoReceita.
     *
     * @return the valorTotalConstanteDocumentoArrecadacaoReceita
     */
    /**
     * Gets the valorTotalConstanteDocumentoArrecadacaoReceita.
     *
     * @return the valorTotalConstanteDocumentoArrecadacaoReceita
     */
    public String getValorTotalConstanteDocumentoArrecadacaoReceita()
    {
        return valorTotalConstanteDocumentoArrecadacaoReceita;
    }

    /**
     * Sets the valortotalconstantedocumentoarrecadacaoreceita.
     *
* @param id the valortotalconstantedocumentoarrecadacaoreceita to set
 */
public void setValorTotalConstanteDocumentoArrecadacaoReceita(String valortotalconstantedocumentoarrecadacaoreceita)
{
        this.valorTotalConstanteDocumentoArrecadacaoReceita = valortotalconstantedocumentoarrecadacaoreceita;
    }

    /**
     * Gets the reparticaoFiscalEmitente.
     *
     * @return the reparticaoFiscalEmitente
     */
    /**
     * Gets the reparticaoFiscalEmitente.
     *
     * @return the reparticaoFiscalEmitente
     */
    public String getReparticaoFiscalEmitente()
    {
        return reparticaoFiscalEmitente;
    }

    /**
     * Sets the reparticaofiscalemitente.
     *
* @param id the reparticaofiscalemitente to set
 */
public void setReparticaoFiscalEmitente(String reparticaofiscalemitente)
{
        this.reparticaoFiscalEmitente = reparticaofiscalemitente;
    }

    /**
     * Gets the dataPagamentoDocumentoArrecadacao.
     *
     * @return the dataPagamentoDocumentoArrecadacao
     */
    /**
     * Gets the dataPagamentoDocumentoArrecadacao.
     *
     * @return the dataPagamentoDocumentoArrecadacao
     */
    public Long getDataPagamentoDocumentoArrecadacao()
    {
        return dataPagamentoDocumentoArrecadacao;
    }

    /**
     * Sets the datapagamentodocumentoarrecadacao.
     *
* @param id the datapagamentodocumentoarrecadacao to set
 */
public void setDataPagamentoDocumentoArrecadacao(Long datapagamentodocumentoarrecadacao)
{
        this.dataPagamentoDocumentoArrecadacao = datapagamentodocumentoarrecadacao;
    }


	@Override
	public String toString() {
		return "NFNotaInfoAvulsa [getId()=" + getId() + ", getCnpj()=" + getCnpj() + ", getOrgaoEmitente()="
				+ getOrgaoEmitente() + ", getMatriculaAgente()=" + getMatriculaAgente() + ", getNomeAgente()="
				+ getNomeAgente() + ", getFone()=" + getFone() + ", getUf()=" + getUf()
				+ ", getNumeroDocumentoArrecadacaoReceita()=" + getNumeroDocumentoArrecadacaoReceita()
				+ ", getDataEmissaoDocumentoArrecadacao()=" + getDataEmissaoDocumentoArrecadacao()
				+ ", getValorTotalConstanteDocumentoArrecadacaoReceita()="
				+ getValorTotalConstanteDocumentoArrecadacaoReceita() + ", getReparticaoFiscalEmitente()="
				+ getReparticaoFiscalEmitente() + ", getDataPagamentoDocumentoArrecadacao()="
				+ getDataPagamentoDocumentoArrecadacao() + ", toString()=" + super.toString() + "]";
	}





 }
