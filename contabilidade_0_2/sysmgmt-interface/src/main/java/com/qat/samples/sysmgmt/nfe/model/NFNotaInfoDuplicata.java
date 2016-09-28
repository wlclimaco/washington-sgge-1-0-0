/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoDuplicata extends NFBase
{

    /** The econtabil numeroDuplicata for the NFNotaInfoDuplicata. */
    private String numeroduplicata;

    /** The econtabil dataVencimento for the NFNotaInfoDuplicata. */
    private Long datavencimento;

    /** The econtabil valorDuplicata for the NFNotaInfoDuplicata. */
    private String valorduplicata;



    /**
     * Default constructor.
     */
    public NFNotaInfoDuplicata()
    {
        super();
    }


    /**
     * Gets the numeroduplicata.
     *
     * @return the numeroduplicata
     */
    /**
     * Gets the numeroduplicata.
     *
     * @return the numeroduplicata
     */
    public String getNumeroDuplicata()
    {
        return numeroduplicata;
    }

    /**
     * Sets the numeroduplicata.
     *
* @param id the numeroduplicata to set
 */
public void setNumeroDuplicata(String numeroduplicata)
{
        this.numeroduplicata = numeroduplicata;
    }

    /**
     * Gets the datavencimento.
     *
     * @return the datavencimento
     */
    /**
     * Gets the datavencimento.
     *
     * @return the datavencimento
     */
    public Long getDataVencimento()
    {
        return datavencimento;
    }

    /**
     * Sets the datavencimento.
     *
* @param id the datavencimento to set
 */
public void setDataVencimento(Long datavencimento)
{
        this.datavencimento = datavencimento;
    }

    /**
     * Gets the valorduplicata.
     *
     * @return the valorduplicata
     */
    /**
     * Gets the valorduplicata.
     *
     * @return the valorduplicata
     */
    public String getValorDuplicata()
    {
        return valorduplicata;
    }

    /**
     * Sets the valorduplicata.
     *
* @param id the valorduplicata to set
 */
public void setValorDuplicata(String valorduplicata)
{
        this.valorduplicata = valorduplicata;
    }


	public String getNumeroduplicata() {
		return numeroduplicata;
	}


	public void setNumeroduplicata(String numeroduplicata) {
		this.numeroduplicata = numeroduplicata;
	}


	public Long getDatavencimento() {
		return datavencimento;
	}


	public void setDatavencimento(Long datavencimento) {
		this.datavencimento = datavencimento;
	}


	public String getValorduplicata() {
		return valorduplicata;
	}


	public void setValorduplicata(String valorduplicata) {
		this.valorduplicata = valorduplicata;
	}


	@Override
	public String toString() {
		return "NFNotaInfoDuplicata [getNumeroDuplicata()=" + getNumeroDuplicata() + ", getDataVencimento()="
				+ getDataVencimento() + ", getValorDuplicata()=" + getValorDuplicata() + ", getNumeroduplicata()="
				+ getNumeroduplicata() + ", getDatavencimento()=" + getDatavencimento() + ", getValorduplicata()="
				+ getValorduplicata() + ", toString()=" + super.toString() + "]";
	}



 }
