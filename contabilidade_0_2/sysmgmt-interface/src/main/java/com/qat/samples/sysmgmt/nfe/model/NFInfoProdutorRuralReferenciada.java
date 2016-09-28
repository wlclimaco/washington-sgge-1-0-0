/** create by system gera-java version 1.0.0 28/09/2016 16:9 : 17*/
package com.qat.samples.sysmgmt.nfe.model;


import com.qat.samples.sysmgmt.estado.model.Estado;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFInfoProdutorRuralReferenciada extends NFBase
{

    /** The econtabil ufEmitente for the NFInfoProdutorRuralReferenciada. */
    private Estado ufemitente;

    /** The econtabil anoMesEmissao for the NFInfoProdutorRuralReferenciada. */
    private String anomesemissao;

    /** The econtabil cnpjEmitente for the NFInfoProdutorRuralReferenciada. */
    private String cnpjemitente;

    /** The econtabil cpfEmitente for the NFInfoProdutorRuralReferenciada. */
    private String cpfemitente;

    /** The econtabil ieEmitente for the NFInfoProdutorRuralReferenciada. */
    private String ieemitente;

    /** The econtabil modeloDocumentoFiscal for the NFInfoProdutorRuralReferenciada. */
    private String modelodocumentofiscal;

    /** The econtabil serieDocumentoFiscal for the NFInfoProdutorRuralReferenciada. */
    private Integer seriedocumentofiscal;

    /** The econtabil numeroDocumentoFiscal for the NFInfoProdutorRuralReferenciada. */
    private Integer numerodocumentofiscal;



    /**
     * Default constructor.
     */
    public NFInfoProdutorRuralReferenciada()
    {
        super();
    }


    /**
     * Gets the ufemitente.
     *
     * @return the ufemitente
     */
    /**
     * Gets the ufemitente.
     *
     * @return the ufemitente
     */
    public Estado getUfEmitente()
    {
        return ufemitente;
    }

    /**
     * Sets the ufemitente.
     *
* @param id the ufemitente to set
 */
public void setUfEmitente(Estado ufemitente)
{
        this.ufemitente = ufemitente;
    }

    /**
     * Gets the anomesemissao.
     *
     * @return the anomesemissao
     */
    /**
     * Gets the anomesemissao.
     *
     * @return the anomesemissao
     */
    public String getAnoMesEmissao()
    {
        return anomesemissao;
    }

    /**
     * Sets the anomesemissao.
     *
* @param id the anomesemissao to set
 */
public void setAnoMesEmissao(String anomesemissao)
{
        this.anomesemissao = anomesemissao;
    }

    /**
     * Gets the cnpjemitente.
     *
     * @return the cnpjemitente
     */
    /**
     * Gets the cnpjemitente.
     *
     * @return the cnpjemitente
     */
    public String getCnpjEmitente()
    {
        return cnpjemitente;
    }

    /**
     * Sets the cnpjemitente.
     *
* @param id the cnpjemitente to set
 */
public void setCnpjEmitente(String cnpjemitente)
{
        this.cnpjemitente = cnpjemitente;
    }

    /**
     * Gets the cpfemitente.
     *
     * @return the cpfemitente
     */
    /**
     * Gets the cpfemitente.
     *
     * @return the cpfemitente
     */
    public String getCpfEmitente()
    {
        return cpfemitente;
    }

    /**
     * Sets the cpfemitente.
     *
* @param id the cpfemitente to set
 */
public void setCpfEmitente(String cpfemitente)
{
        this.cpfemitente = cpfemitente;
    }

    /**
     * Gets the ieemitente.
     *
     * @return the ieemitente
     */
    /**
     * Gets the ieemitente.
     *
     * @return the ieemitente
     */
    public String getIeEmitente()
    {
        return ieemitente;
    }

    /**
     * Sets the ieemitente.
     *
* @param id the ieemitente to set
 */
public void setIeEmitente(String ieemitente)
{
        this.ieemitente = ieemitente;
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
     * Gets the seriedocumentofiscal.
     *
     * @return the seriedocumentofiscal
     */
    /**
     * Gets the seriedocumentofiscal.
     *
     * @return the seriedocumentofiscal
     */
    public Integer getSerieDocumentoFiscal()
    {
        return seriedocumentofiscal;
    }

    /**
     * Sets the seriedocumentofiscal.
     *
* @param id the seriedocumentofiscal to set
 */
public void setSerieDocumentoFiscal(Integer seriedocumentofiscal)
{
        this.seriedocumentofiscal = seriedocumentofiscal;
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
    public Integer getNumeroDocumentoFiscal()
    {
        return numerodocumentofiscal;
    }

    /**
     * Sets the numerodocumentofiscal.
     *
* @param id the numerodocumentofiscal to set
 */
public void setNumeroDocumentoFiscal(Integer numerodocumentofiscal)
{
        this.numerodocumentofiscal = numerodocumentofiscal;
    }


	public Estado getUfemitente() {
		return ufemitente;
	}


	public void setUfemitente(Estado ufemitente) {
		this.ufemitente = ufemitente;
	}


	public String getAnomesemissao() {
		return anomesemissao;
	}


	public void setAnomesemissao(String anomesemissao) {
		this.anomesemissao = anomesemissao;
	}


	public String getCnpjemitente() {
		return cnpjemitente;
	}


	public void setCnpjemitente(String cnpjemitente) {
		this.cnpjemitente = cnpjemitente;
	}


	public String getCpfemitente() {
		return cpfemitente;
	}


	public void setCpfemitente(String cpfemitente) {
		this.cpfemitente = cpfemitente;
	}


	public String getIeemitente() {
		return ieemitente;
	}


	public void setIeemitente(String ieemitente) {
		this.ieemitente = ieemitente;
	}


	public String getModelodocumentofiscal() {
		return modelodocumentofiscal;
	}


	public void setModelodocumentofiscal(String modelodocumentofiscal) {
		this.modelodocumentofiscal = modelodocumentofiscal;
	}


	public Integer getSeriedocumentofiscal() {
		return seriedocumentofiscal;
	}


	public void setSeriedocumentofiscal(Integer seriedocumentofiscal) {
		this.seriedocumentofiscal = seriedocumentofiscal;
	}


	public Integer getNumerodocumentofiscal() {
		return numerodocumentofiscal;
	}


	public void setNumerodocumentofiscal(Integer numerodocumentofiscal) {
		this.numerodocumentofiscal = numerodocumentofiscal;
	}


	@Override
	public String toString() {
		return "NFInfoProdutorRuralReferenciada [getUfEmitente()=" + getUfEmitente() + ", getAnoMesEmissao()="
				+ getAnoMesEmissao() + ", getCnpjEmitente()=" + getCnpjEmitente() + ", getCpfEmitente()="
				+ getCpfEmitente() + ", getIeEmitente()=" + getIeEmitente() + ", getModeloDocumentoFiscal()="
				+ getModeloDocumentoFiscal() + ", getSerieDocumentoFiscal()=" + getSerieDocumentoFiscal()
				+ ", getNumeroDocumentoFiscal()=" + getNumeroDocumentoFiscal() + ", getUfemitente()=" + getUfemitente()
				+ ", getAnomesemissao()=" + getAnomesemissao() + ", getCnpjemitente()=" + getCnpjemitente()
				+ ", getCpfemitente()=" + getCpfemitente() + ", getIeemitente()=" + getIeemitente()
				+ ", getModelodocumentofiscal()=" + getModelodocumentofiscal() + ", getSeriedocumentofiscal()="
				+ getSeriedocumentofiscal() + ", getNumerodocumentofiscal()=" + getNumerodocumentofiscal()
				+ ", toString()=" + super.toString() + "]";
	}






 }
