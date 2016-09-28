/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoAvulsa extends NFBase
{

    /** The econtabil cnpj for the NFNotaInfoAvulsa. */
    private String cnpj;

    /** The econtabil orgaoEmitente for the NFNotaInfoAvulsa. */
    private String orgaoemitente;

    /** The econtabil matriculaAgente for the NFNotaInfoAvulsa. */
    private String matriculaagente;

    /** The econtabil nomeAgente for the NFNotaInfoAvulsa. */
    private String nomeagente;

    /** The econtabil fone for the NFNotaInfoAvulsa. */
    private String fone;

    /** The econtabil uf for the NFNotaInfoAvulsa. */
    private String uf;

    /** The econtabil numeroDocumentoArrecadacaoReceita for the NFNotaInfoAvulsa. */
    private String numerodocumentoarrecadacaoreceita;

    /** The econtabil dataEmissaoDocumentoArrecadacao for the NFNotaInfoAvulsa. */
    private Long dataemissaodocumentoarrecadacao;

    /** The econtabil valorTotalConstanteDocumentoArrecadacaoReceita for the NFNotaInfoAvulsa. */
    private String valortotalconstantedocumentoarrecadacaoreceita;

    /** The econtabil reparticaoFiscalEmitente for the NFNotaInfoAvulsa. */
    private String reparticaofiscalemitente;

    /** The econtabil dataPagamentoDocumentoArrecadacao for the NFNotaInfoAvulsa. */
    private Long datapagamentodocumentoarrecadacao;



    /**
     * Default constructor.
     */
    public NFNotaInfoAvulsa()
    {
        super();
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
     * Gets the orgaoemitente.
     *
     * @return the orgaoemitente
     */
    /**
     * Gets the orgaoemitente.
     *
     * @return the orgaoemitente
     */
    public String getOrgaoEmitente()
    {
        return orgaoemitente;
    }

    /**
     * Sets the orgaoemitente.
     *
* @param id the orgaoemitente to set
 */
public void setOrgaoEmitente(String orgaoemitente)
{
        this.orgaoemitente = orgaoemitente;
    }

    /**
     * Gets the matriculaagente.
     *
     * @return the matriculaagente
     */
    /**
     * Gets the matriculaagente.
     *
     * @return the matriculaagente
     */
    public String getMatriculaAgente()
    {
        return matriculaagente;
    }

    /**
     * Sets the matriculaagente.
     *
* @param id the matriculaagente to set
 */
public void setMatriculaAgente(String matriculaagente)
{
        this.matriculaagente = matriculaagente;
    }

    /**
     * Gets the nomeagente.
     *
     * @return the nomeagente
     */
    /**
     * Gets the nomeagente.
     *
     * @return the nomeagente
     */
    public String getNomeAgente()
    {
        return nomeagente;
    }

    /**
     * Sets the nomeagente.
     *
* @param id the nomeagente to set
 */
public void setNomeAgente(String nomeagente)
{
        this.nomeagente = nomeagente;
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
     * Gets the numerodocumentoarrecadacaoreceita.
     *
     * @return the numerodocumentoarrecadacaoreceita
     */
    /**
     * Gets the numerodocumentoarrecadacaoreceita.
     *
     * @return the numerodocumentoarrecadacaoreceita
     */
    public String getNumeroDocumentoArrecadacaoReceita()
    {
        return numerodocumentoarrecadacaoreceita;
    }

    /**
     * Sets the numerodocumentoarrecadacaoreceita.
     *
* @param id the numerodocumentoarrecadacaoreceita to set
 */
public void setNumeroDocumentoArrecadacaoReceita(String numerodocumentoarrecadacaoreceita)
{
        this.numerodocumentoarrecadacaoreceita = numerodocumentoarrecadacaoreceita;
    }

    /**
     * Gets the dataemissaodocumentoarrecadacao.
     *
     * @return the dataemissaodocumentoarrecadacao
     */
    /**
     * Gets the dataemissaodocumentoarrecadacao.
     *
     * @return the dataemissaodocumentoarrecadacao
     */
    public Long getDataEmissaoDocumentoArrecadacao()
    {
        return dataemissaodocumentoarrecadacao;
    }

    /**
     * Sets the dataemissaodocumentoarrecadacao.
     *
* @param id the dataemissaodocumentoarrecadacao to set
 */
public void setDataEmissaoDocumentoArrecadacao(Long dataemissaodocumentoarrecadacao)
{
        this.dataemissaodocumentoarrecadacao = dataemissaodocumentoarrecadacao;
    }

    /**
     * Gets the valortotalconstantedocumentoarrecadacaoreceita.
     *
     * @return the valortotalconstantedocumentoarrecadacaoreceita
     */
    /**
     * Gets the valortotalconstantedocumentoarrecadacaoreceita.
     *
     * @return the valortotalconstantedocumentoarrecadacaoreceita
     */
    public String getValorTotalConstanteDocumentoArrecadacaoReceita()
    {
        return valortotalconstantedocumentoarrecadacaoreceita;
    }

    /**
     * Sets the valortotalconstantedocumentoarrecadacaoreceita.
     *
* @param id the valortotalconstantedocumentoarrecadacaoreceita to set
 */
public void setValorTotalConstanteDocumentoArrecadacaoReceita(String valortotalconstantedocumentoarrecadacaoreceita)
{
        this.valortotalconstantedocumentoarrecadacaoreceita = valortotalconstantedocumentoarrecadacaoreceita;
    }

    /**
     * Gets the reparticaofiscalemitente.
     *
     * @return the reparticaofiscalemitente
     */
    /**
     * Gets the reparticaofiscalemitente.
     *
     * @return the reparticaofiscalemitente
     */
    public String getReparticaoFiscalEmitente()
    {
        return reparticaofiscalemitente;
    }

    /**
     * Sets the reparticaofiscalemitente.
     *
* @param id the reparticaofiscalemitente to set
 */
public void setReparticaoFiscalEmitente(String reparticaofiscalemitente)
{
        this.reparticaofiscalemitente = reparticaofiscalemitente;
    }

    /**
     * Gets the datapagamentodocumentoarrecadacao.
     *
     * @return the datapagamentodocumentoarrecadacao
     */
    /**
     * Gets the datapagamentodocumentoarrecadacao.
     *
     * @return the datapagamentodocumentoarrecadacao
     */
    public Long getDataPagamentoDocumentoArrecadacao()
    {
        return datapagamentodocumentoarrecadacao;
    }

    /**
     * Sets the datapagamentodocumentoarrecadacao.
     *
* @param id the datapagamentodocumentoarrecadacao to set
 */
public void setDataPagamentoDocumentoArrecadacao(Long datapagamentodocumentoarrecadacao)
{
        this.datapagamentodocumentoarrecadacao = datapagamentodocumentoarrecadacao;
    }


	public String getOrgaoemitente() {
		return orgaoemitente;
	}


	public void setOrgaoemitente(String orgaoemitente) {
		this.orgaoemitente = orgaoemitente;
	}


	public String getMatriculaagente() {
		return matriculaagente;
	}


	public void setMatriculaagente(String matriculaagente) {
		this.matriculaagente = matriculaagente;
	}


	public String getNomeagente() {
		return nomeagente;
	}


	public void setNomeagente(String nomeagente) {
		this.nomeagente = nomeagente;
	}


	public String getNumerodocumentoarrecadacaoreceita() {
		return numerodocumentoarrecadacaoreceita;
	}


	public void setNumerodocumentoarrecadacaoreceita(String numerodocumentoarrecadacaoreceita) {
		this.numerodocumentoarrecadacaoreceita = numerodocumentoarrecadacaoreceita;
	}


	public Long getDataemissaodocumentoarrecadacao() {
		return dataemissaodocumentoarrecadacao;
	}


	public void setDataemissaodocumentoarrecadacao(Long dataemissaodocumentoarrecadacao) {
		this.dataemissaodocumentoarrecadacao = dataemissaodocumentoarrecadacao;
	}


	public String getValortotalconstantedocumentoarrecadacaoreceita() {
		return valortotalconstantedocumentoarrecadacaoreceita;
	}


	public void setValortotalconstantedocumentoarrecadacaoreceita(String valortotalconstantedocumentoarrecadacaoreceita) {
		this.valortotalconstantedocumentoarrecadacaoreceita = valortotalconstantedocumentoarrecadacaoreceita;
	}


	public String getReparticaofiscalemitente() {
		return reparticaofiscalemitente;
	}


	public void setReparticaofiscalemitente(String reparticaofiscalemitente) {
		this.reparticaofiscalemitente = reparticaofiscalemitente;
	}


	public Long getDatapagamentodocumentoarrecadacao() {
		return datapagamentodocumentoarrecadacao;
	}


	public void setDatapagamentodocumentoarrecadacao(Long datapagamentodocumentoarrecadacao) {
		this.datapagamentodocumentoarrecadacao = datapagamentodocumentoarrecadacao;
	}


	@Override
	public String toString() {
		return "NFNotaInfoAvulsa [getCnpj()=" + getCnpj() + ", getOrgaoEmitente()=" + getOrgaoEmitente()
				+ ", getMatriculaAgente()=" + getMatriculaAgente() + ", getNomeAgente()=" + getNomeAgente()
				+ ", getFone()=" + getFone() + ", getUf()=" + getUf() + ", getNumeroDocumentoArrecadacaoReceita()="
				+ getNumeroDocumentoArrecadacaoReceita() + ", getDataEmissaoDocumentoArrecadacao()="
				+ getDataEmissaoDocumentoArrecadacao() + ", getValorTotalConstanteDocumentoArrecadacaoReceita()="
				+ getValorTotalConstanteDocumentoArrecadacaoReceita() + ", getReparticaoFiscalEmitente()="
				+ getReparticaoFiscalEmitente() + ", getDataPagamentoDocumentoArrecadacao()="
				+ getDataPagamentoDocumentoArrecadacao() + ", getOrgaoemitente()=" + getOrgaoemitente()
				+ ", getMatriculaagente()=" + getMatriculaagente() + ", getNomeagente()=" + getNomeagente()
				+ ", getNumerodocumentoarrecadacaoreceita()=" + getNumerodocumentoarrecadacaoreceita()
				+ ", getDataemissaodocumentoarrecadacao()=" + getDataemissaodocumentoarrecadacao()
				+ ", getValortotalconstantedocumentoarrecadacaoreceita()="
				+ getValortotalconstantedocumentoarrecadacaoreceita() + ", getReparticaofiscalemitente()="
				+ getReparticaofiscalemitente() + ", getDatapagamentodocumentoarrecadacao()="
				+ getDatapagamentodocumentoarrecadacao() + ", toString()=" + super.toString() + "]";
	}



 }
