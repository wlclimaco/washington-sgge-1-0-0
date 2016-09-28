/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoFatura extends NFBase
{

    /** The econtabil numeroFatura for the NFNotaInfoFatura. */
    private String numerofatura;

    /** The econtabil valorOriginalFatura for the NFNotaInfoFatura. */
    private String valororiginalfatura;

    /** The econtabil valorDesconto for the NFNotaInfoFatura. */
    private String valordesconto;

    /** The econtabil valorLiquidoFatura for the NFNotaInfoFatura. */
    private String valorliquidofatura;



    /**
     * Default constructor.
     */
    public NFNotaInfoFatura()
    {
        super();
    }


    /**
     * Gets the numerofatura.
     *
     * @return the numerofatura
     */
    /**
     * Gets the numerofatura.
     *
     * @return the numerofatura
     */
    public String getNumeroFatura()
    {
        return numerofatura;
    }

    /**
     * Sets the numerofatura.
     *
* @param id the numerofatura to set
 */
public void setNumeroFatura(String numerofatura)
{
        this.numerofatura = numerofatura;
    }

    /**
     * Gets the valororiginalfatura.
     *
     * @return the valororiginalfatura
     */
    /**
     * Gets the valororiginalfatura.
     *
     * @return the valororiginalfatura
     */
    public String getValorOriginalFatura()
    {
        return valororiginalfatura;
    }

    /**
     * Sets the valororiginalfatura.
     *
* @param id the valororiginalfatura to set
 */
public void setValorOriginalFatura(String valororiginalfatura)
{
        this.valororiginalfatura = valororiginalfatura;
    }

    /**
     * Gets the valordesconto.
     *
     * @return the valordesconto
     */
    /**
     * Gets the valordesconto.
     *
     * @return the valordesconto
     */
    public String getValorDesconto()
    {
        return valordesconto;
    }

    /**
     * Sets the valordesconto.
     *
* @param id the valordesconto to set
 */
public void setValorDesconto(String valordesconto)
{
        this.valordesconto = valordesconto;
    }

    /**
     * Gets the valorliquidofatura.
     *
     * @return the valorliquidofatura
     */
    /**
     * Gets the valorliquidofatura.
     *
     * @return the valorliquidofatura
     */
    public String getValorLiquidoFatura()
    {
        return valorliquidofatura;
    }

    /**
     * Sets the valorliquidofatura.
     *
* @param id the valorliquidofatura to set
 */
public void setValorLiquidoFatura(String valorliquidofatura)
{
        this.valorliquidofatura = valorliquidofatura;
    }


	public String getNumerofatura() {
		return numerofatura;
	}


	public void setNumerofatura(String numerofatura) {
		this.numerofatura = numerofatura;
	}


	public String getValororiginalfatura() {
		return valororiginalfatura;
	}


	public void setValororiginalfatura(String valororiginalfatura) {
		this.valororiginalfatura = valororiginalfatura;
	}


	public String getValordesconto() {
		return valordesconto;
	}


	public void setValordesconto(String valordesconto) {
		this.valordesconto = valordesconto;
	}


	public String getValorliquidofatura() {
		return valorliquidofatura;
	}


	public void setValorliquidofatura(String valorliquidofatura) {
		this.valorliquidofatura = valorliquidofatura;
	}


	@Override
	public String toString() {
		return "NFNotaInfoFatura [getNumeroFatura()=" + getNumeroFatura() + ", getValorOriginalFatura()="
				+ getValorOriginalFatura() + ", getValorDesconto()=" + getValorDesconto() + ", getValorLiquidoFatura()="
				+ getValorLiquidoFatura() + ", getNumerofatura()=" + getNumerofatura() + ", getValororiginalfatura()="
				+ getValororiginalfatura() + ", getValordesconto()=" + getValordesconto() + ", getValorliquidofatura()="
				+ getValorliquidofatura() + ", toString()=" + super.toString() + "]";
	}



 }
