/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFInfoCupomFiscalReferenciado extends NFBase
{

    /** The econtabil modeloDocumentoFiscal for the NFInfoCupomFiscalReferenciado. */
    private String modelodocumentofiscal;

    /** The econtabil numeroOrdemSequencialECF for the NFInfoCupomFiscalReferenciado. */
    private String numeroordemsequencialecf;

    /** The econtabil numeroContadorOrdemOperacao for the NFInfoCupomFiscalReferenciado. */
    private String numerocontadorordemoperacao;



    /**
     * Default constructor.
     */
    public NFInfoCupomFiscalReferenciado()
    {
        super();
    }


    /**
     * Gets the modelodocumentofiscal.
     *
     * @return the modelodocumentofiscal
     */
    /**
     * Gets the modelodocumentofiscal.
     *
     * @return the modelodocumentofiscal
     */
    public String getModeloDocumentoFiscal()
    {
        return modelodocumentofiscal;
    }

    /**
     * Sets the modelodocumentofiscal.
     *
* @param id the modelodocumentofiscal to set
 */
public void setModeloDocumentoFiscal(String modelodocumentofiscal)
{
        this.modelodocumentofiscal = modelodocumentofiscal;
    }

    /**
     * Gets the numeroordemsequencialecf.
     *
     * @return the numeroordemsequencialecf
     */
    /**
     * Gets the numeroordemsequencialecf.
     *
     * @return the numeroordemsequencialecf
     */
    public String getNumeroOrdemSequencialECF()
    {
        return numeroordemsequencialecf;
    }

    /**
     * Sets the numeroordemsequencialecf.
     *
* @param id the numeroordemsequencialecf to set
 */
public void setNumeroOrdemSequencialECF(String numeroordemsequencialecf)
{
        this.numeroordemsequencialecf = numeroordemsequencialecf;
    }

    /**
     * Gets the numerocontadorordemoperacao.
     *
     * @return the numerocontadorordemoperacao
     */
    /**
     * Gets the numerocontadorordemoperacao.
     *
     * @return the numerocontadorordemoperacao
     */
    public String getNumeroContadorOrdemOperacao()
    {
        return numerocontadorordemoperacao;
    }

    /**
     * Sets the numerocontadorordemoperacao.
     *
* @param id the numerocontadorordemoperacao to set
 */
public void setNumeroContadorOrdemOperacao(String numerocontadorordemoperacao)
{
        this.numerocontadorordemoperacao = numerocontadorordemoperacao;
    }




    public String getModelodocumentofiscal() {
		return modelodocumentofiscal;
	}


	public void setModelodocumentofiscal(String modelodocumentofiscal) {
		this.modelodocumentofiscal = modelodocumentofiscal;
	}


	public String getNumeroordemsequencialecf() {
		return numeroordemsequencialecf;
	}


	public void setNumeroordemsequencialecf(String numeroordemsequencialecf) {
		this.numeroordemsequencialecf = numeroordemsequencialecf;
	}


	public String getNumerocontadorordemoperacao() {
		return numerocontadorordemoperacao;
	}


	public void setNumerocontadorordemoperacao(String numerocontadorordemoperacao) {
		this.numerocontadorordemoperacao = numerocontadorordemoperacao;
	}


	@Override
	public String toString() {
		return "NFInfoCupomFiscalReferenciado [getModeloDocumentoFiscal()=" + getModeloDocumentoFiscal()
				+ ", getNumeroOrdemSequencialECF()=" + getNumeroOrdemSequencialECF()
				+ ", getNumeroContadorOrdemOperacao()=" + getNumeroContadorOrdemOperacao()
				+ ", getModelodocumentofiscal()=" + getModelodocumentofiscal() + ", getNumeroordemsequencialecf()="
				+ getNumeroordemsequencialecf() + ", getNumerocontadorordemoperacao()="
				+ getNumerocontadorordemoperacao() + ", toString()=" + super.toString() + "]";
	}


 }
