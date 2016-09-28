/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;


import com.qat.samples.sysmgmt.util.model.DoisValores;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoCartao extends NFBase
{

    /** The econtabil tipoIntegracao for the NFNotaInfoCartao. */
    private DoisValores tipointegracao;

    /** The econtabil cnpj for the NFNotaInfoCartao. */
    private String cnpj;

    /** The econtabil operadoraCartao for the NFNotaInfoCartao. */
    private DoisValores operadoracartao;

    /** The econtabil numeroAutorizacaoOperacaoCartao for the NFNotaInfoCartao. */
    private String numeroautorizacaooperacaocartao;



    /**
     * Default constructor.
     */
    public NFNotaInfoCartao()
    {
        super();
    }


    /**
     * Gets the tipointegracao.
     *
     * @return the tipointegracao
     */
    /**
     * Gets the tipointegracao.
     *
     * @return the tipointegracao
     */
    public DoisValores getTipoIntegracao()
    {
        return tipointegracao;
    }

    /**
     * Sets the tipointegracao.
     *
* @param id the tipointegracao to set
 */
public void setTipoIntegracao(DoisValores tipointegracao)
{
        this.tipointegracao = tipointegracao;
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
     * Gets the operadoracartao.
     *
     * @return the operadoracartao
     */
    /**
     * Gets the operadoracartao.
     *
     * @return the operadoracartao
     */
    public DoisValores getOperadoraCartao()
    {
        return operadoracartao;
    }

    /**
     * Sets the operadoracartao.
     *
* @param id the operadoracartao to set
 */
public void setOperadoraCartao(DoisValores operadoracartao)
{
        this.operadoracartao = operadoracartao;
    }

    /**
     * Gets the numeroautorizacaooperacaocartao.
     *
     * @return the numeroautorizacaooperacaocartao
     */
    /**
     * Gets the numeroautorizacaooperacaocartao.
     *
     * @return the numeroautorizacaooperacaocartao
     */
    public String getNumeroAutorizacaoOperacaoCartao()
    {
        return numeroautorizacaooperacaocartao;
    }

    /**
     * Sets the numeroautorizacaooperacaocartao.
     *
* @param id the numeroautorizacaooperacaocartao to set
 */
public void setNumeroAutorizacaoOperacaoCartao(String numeroautorizacaooperacaocartao)
{
        this.numeroautorizacaooperacaocartao = numeroautorizacaooperacaocartao;
    }


	public DoisValores getTipointegracao() {
		return tipointegracao;
	}


	public void setTipointegracao(DoisValores tipointegracao) {
		this.tipointegracao = tipointegracao;
	}


	public DoisValores getOperadoracartao() {
		return operadoracartao;
	}


	public void setOperadoracartao(DoisValores operadoracartao) {
		this.operadoracartao = operadoracartao;
	}


	public String getNumeroautorizacaooperacaocartao() {
		return numeroautorizacaooperacaocartao;
	}


	public void setNumeroautorizacaooperacaocartao(String numeroautorizacaooperacaocartao) {
		this.numeroautorizacaooperacaocartao = numeroautorizacaooperacaocartao;
	}


	@Override
	public String toString() {
		return "NFNotaInfoCartao [getTipoIntegracao()=" + getTipoIntegracao() + ", getCnpj()=" + getCnpj()
				+ ", getOperadoraCartao()=" + getOperadoraCartao() + ", getNumeroAutorizacaoOperacaoCartao()="
				+ getNumeroAutorizacaoOperacaoCartao() + ", getTipointegracao()=" + getTipointegracao()
				+ ", getOperadoracartao()=" + getOperadoracartao() + ", getNumeroautorizacaooperacaocartao()="
				+ getNumeroautorizacaooperacaocartao() + ", toString()=" + super.toString() + "]";
	}





 }
