package com.sensus.dm.common.academia.model;

import com.sensus.common.model.SensusModel;

@SuppressWarnings("serial")
public class Academia extends SensusModel
{
	private Integer cdacad;
	private String academ;
	private String lograd;
	private String numen;
	private String bairr;
	private String cidade;
	private String cep;
	private String telef;
	private String latlog;

	/**
	 * Gets the cdacad.
	 * 
	 * @return the cdacad
	 */
	public Integer getCdacad()
	{
		return cdacad;
	}

	/**
	 * Sets the cdacad.
	 * 
	 * @param cdacad the new cdacad
	 */
	public void setCdacad(Integer cdacad)
	{
		this.cdacad = cdacad;
	}

	/**
	 * Gets the academ.
	 * 
	 * @return the academ
	 */
	public String getAcadem()
	{
		return academ;
	}

	/**
	 * Sets the academ.
	 * 
	 * @param academ the new academ
	 */
	public void setAcadem(String academ)
	{
		this.academ = academ;
	}

	/**
	 * Gets the lograd.
	 * 
	 * @return the lograd
	 */
	public String getLograd()
	{
		return lograd;
	}

	/**
	 * Sets the lograd.
	 * 
	 * @param lograd the new lograd
	 */
	public void setLograd(String lograd)
	{
		this.lograd = lograd;
	}

	/**
	 * Gets the numen.
	 * 
	 * @return the numen
	 */
	public String getNumen()
	{
		return numen;
	}

	/**
	 * Sets the numen.
	 * 
	 * @param numen the new numen
	 */
	public void setNumen(String numen)
	{
		this.numen = numen;
	}

	/**
	 * Gets the bairr.
	 * 
	 * @return the bairr
	 */
	public String getBairr()
	{
		return bairr;
	}

	/**
	 * Sets the bairr.
	 * 
	 * @param bairr the new bairr
	 */
	public void setBairr(String bairr)
	{
		this.bairr = bairr;
	}

	/**
	 * Gets the cidade.
	 * 
	 * @return the cidade
	 */
	public String getCidade()
	{
		return cidade;
	}

	/**
	 * Sets the cidade.
	 * 
	 * @param cidade the new cidade
	 */
	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}

	/**
	 * Gets the cep.
	 * 
	 * @return the cep
	 */
	public String getCep()
	{
		return cep;
	}

	/**
	 * Sets the cep.
	 * 
	 * @param cep the new cep
	 */
	public void setCep(String cep)
	{
		this.cep = cep;
	}

	/**
	 * Gets the telef.
	 * 
	 * @return the telef
	 */
	public String getTelef()
	{
		return telef;
	}

	/**
	 * Sets the telef.
	 * 
	 * @param telef the new telef
	 */
	public void setTelef(String telef)
	{
		this.telef = telef;
	}

	/**
	 * Gets the latlog.
	 * 
	 * @return the latlog
	 */
	public String getLatlog()
	{
		return latlog;
	}

	/**
	 * Sets the latlog.
	 * 
	 * @param latlog the new latlog
	 */
	public void setLatlog(String latlog)
	{
		this.latlog = latlog;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Academia[ getCdacad()=" + getCdacad() + ", getAcadem()=" + getAcadem() + ", getLograd()=" + getLograd()
				+ ", getNumen()=" + getNumen() + ", getBairr()="
				+ getBairr() + ",getCidade()=" + getCidade() + ", getCep()=" + getCep() + ", getTelef()=" + getTelef()
				+ ", getLatlog()=" + getLatlog()
				+ "]";
	}
}
