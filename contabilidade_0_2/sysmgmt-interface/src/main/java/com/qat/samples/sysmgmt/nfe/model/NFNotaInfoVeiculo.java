/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoVeiculo extends NFBase
{

    /** The econtabil placaVeiculo for the NFNotaInfoVeiculo. */
    private String placaveiculo;

    /** The econtabil uf for the NFNotaInfoVeiculo. */
    private String uf;

    /** The econtabil registroNacionalTransportadorCarga for the NFNotaInfoVeiculo. */
    private String registronacionaltransportadorcarga;



    /**
     * Default constructor.
     */
    public NFNotaInfoVeiculo()
    {
        super();
    }


    /**
     * Gets the placaveiculo.
     *
     * @return the placaveiculo
     */
    /**
     * Gets the placaveiculo.
     *
     * @return the placaveiculo
     */
    public String getPlacaVeiculo()
    {
        return placaveiculo;
    }

    /**
     * Sets the placaveiculo.
     *
* @param id the placaveiculo to set
 */
public void setPlacaVeiculo(String placaveiculo)
{
        this.placaveiculo = placaveiculo;
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
     * Gets the registronacionaltransportadorcarga.
     *
     * @return the registronacionaltransportadorcarga
     */
    /**
     * Gets the registronacionaltransportadorcarga.
     *
     * @return the registronacionaltransportadorcarga
     */
    public String getRegistroNacionalTransportadorCarga()
    {
        return registronacionaltransportadorcarga;
    }

    /**
     * Sets the registronacionaltransportadorcarga.
     *
* @param id the registronacionaltransportadorcarga to set
 */
public void setRegistroNacionalTransportadorCarga(String registronacionaltransportadorcarga)
{
        this.registronacionaltransportadorcarga = registronacionaltransportadorcarga;
    }


	public String getPlacaveiculo() {
		return placaveiculo;
	}


	public void setPlacaveiculo(String placaveiculo) {
		this.placaveiculo = placaveiculo;
	}


	public String getRegistronacionaltransportadorcarga() {
		return registronacionaltransportadorcarga;
	}


	public void setRegistronacionaltransportadorcarga(String registronacionaltransportadorcarga) {
		this.registronacionaltransportadorcarga = registronacionaltransportadorcarga;
	}


	@Override
	public String toString() {
		return "NFNotaInfoVeiculo [getPlacaVeiculo()=" + getPlacaVeiculo() + ", getUf()=" + getUf()
				+ ", getRegistroNacionalTransportadorCarga()=" + getRegistroNacionalTransportadorCarga()
				+ ", getPlacaveiculo()=" + getPlacaveiculo() + ", getRegistronacionaltransportadorcarga()="
				+ getRegistronacionaltransportadorcarga() + ", toString()=" + super.toString() + "]";
	}



 }
