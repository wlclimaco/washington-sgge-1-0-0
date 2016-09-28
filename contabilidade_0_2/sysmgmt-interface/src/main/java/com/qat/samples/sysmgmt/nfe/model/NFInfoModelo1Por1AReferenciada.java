/** create by system gera-java version 1.0.0 28/09/2016 12:13 : 30*/
package com.qat.samples.sysmgmt.nfe.model;


import com.qat.samples.sysmgmt.estado.model.Estado;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFInfoModelo1Por1AReferenciada extends NFBase
{

    /** The econtabil uf for the NFInfoModelo1Por1AReferenciada. */
    private Estado uf;

    /** The econtabil anoMesEmissaoNFe for the NFInfoModelo1Por1AReferenciada. */
    private String anomesemissaonfe;

    /** The econtabil cnpj for the NFInfoModelo1Por1AReferenciada. */
    private String cnpj;

    /** The econtabil modeloDocumentoFiscal for the NFInfoModelo1Por1AReferenciada. */
    private String modelodocumentofiscal;

    /** The econtabil serie for the NFInfoModelo1Por1AReferenciada. */
    private String serie;

    /** The econtabil numeroDocumentoFiscal for the NFInfoModelo1Por1AReferenciada. */
    private String numerodocumentofiscal;



    /**
     * Default constructor.
     */
    public NFInfoModelo1Por1AReferenciada()
    {
        super();
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
    public Estado getUf()
    {
        return uf;
    }

    /**
     * Sets the uf.
     *
* @param id the uf to set
 */
public void setUf(Estado uf)
{
        this.uf = uf;
    }

    /**
     * Gets the anomesemissaonfe.
     *
     * @return the anomesemissaonfe
     */
    /**
     * Gets the anomesemissaonfe.
     *
     * @return the anomesemissaonfe
     */
    public String getAnoMesEmissaoNFe()
    {
        return anomesemissaonfe;
    }

    /**
     * Sets the anomesemissaonfe.
     *
* @param id the anomesemissaonfe to set
 */
public void setAnoMesEmissaoNFe(String anomesemissaonfe)
{
        this.anomesemissaonfe = anomesemissaonfe;
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
     * Gets the serie.
     *
     * @return the serie
     */
    /**
     * Gets the serie.
     *
     * @return the serie
     */
    public String getSerie()
    {
        return serie;
    }

    /**
     * Sets the serie.
     *
* @param id the serie to set
 */
public void setSerie(String serie)
{
        this.serie = serie;
    }

    /**
     * Gets the numerodocumentofiscal.
     *
     * @return the numerodocumentofiscal
     */
    /**
     * Gets the numerodocumentofiscal.
     *
     * @return the numerodocumentofiscal
     */
    public String getNumeroDocumentoFiscal()
    {
        return numerodocumentofiscal;
    }

    /**
     * Sets the numerodocumentofiscal.
     *
* @param id the numerodocumentofiscal to set
 */
public void setNumeroDocumentoFiscal(String numerodocumentofiscal)
{
        this.numerodocumentofiscal = numerodocumentofiscal;
    }


	public String getAnomesemissaonfe() {
		return anomesemissaonfe;
	}


	public void setAnomesemissaonfe(String anomesemissaonfe) {
		this.anomesemissaonfe = anomesemissaonfe;
	}


	public String getModelodocumentofiscal() {
		return modelodocumentofiscal;
	}


	public void setModelodocumentofiscal(String modelodocumentofiscal) {
		this.modelodocumentofiscal = modelodocumentofiscal;
	}


	public String getNumerodocumentofiscal() {
		return numerodocumentofiscal;
	}


	public void setNumerodocumentofiscal(String numerodocumentofiscal) {
		this.numerodocumentofiscal = numerodocumentofiscal;
	}


	@Override
	public String toString() {
		return "NFInfoModelo1Por1AReferenciada [getUf()=" + getUf() + ", getAnoMesEmissaoNFe()=" + getAnoMesEmissaoNFe()
				+ ", getCnpj()=" + getCnpj() + ", getModeloDocumentoFiscal()=" + getModeloDocumentoFiscal()
				+ ", getSerie()=" + getSerie() + ", getNumeroDocumentoFiscal()=" + getNumeroDocumentoFiscal()
				+ ", getAnomesemissaonfe()=" + getAnomesemissaonfe() + ", getModelodocumentofiscal()="
				+ getModelodocumentofiscal() + ", getNumerodocumentofiscal()=" + getNumerodocumentofiscal()
				+ ", toString()=" + super.toString() + "]";
	}





 }
