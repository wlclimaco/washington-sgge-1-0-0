package com.sensus.lc.suplemento.model;

import java.sql.Date;

import com.sensus.common.model.SensusModel;

@SuppressWarnings("serial")
public class Suplemento extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;
	private Integer cdsuple;
	private String supleme;
	private String marca;
	private Float qntdia;
	private Date dtinic;
	private Date dtfina;
	private String obssup;
	private String coment;

	/**
	 * Gets the cdsuple.
	 * 
	 * @return the cdsuple
	 */
	public Integer getCdsuple()
	{
		return cdsuple;
	}

	/**
	 * Sets the cdsuple.
	 * 
	 * @param cdsuple the new cdsuple
	 */
	public void setCdsuple(Integer cdsuple)
	{
		this.cdsuple = cdsuple;
	}

	/**
	 * Gets the supleme.
	 * 
	 * @return the supleme
	 */
	public String getSupleme()
	{
		return supleme;
	}

	/**
	 * Sets the supleme.
	 * 
	 * @param supleme the new supleme
	 */
	public void setSupleme(String supleme)
	{
		this.supleme = supleme;
	}

	/**
	 * Gets the marca.
	 * 
	 * @return the marca
	 */
	public String getMarca()
	{
		return marca;
	}

	/**
	 * Sets the marca.
	 * 
	 * @param marca the new marca
	 */
	public void setMarca(String marca)
	{
		this.marca = marca;
	}

	/**
	 * Gets the qntdia.
	 * 
	 * @return the qntdia
	 */
	public Float getQntdia()
	{
		return qntdia;
	}

	/**
	 * Sets the qntdia.
	 * 
	 * @param qntdia the new qntdia
	 */
	public void setQntdia(Float qntdia)
	{
		this.qntdia = qntdia;
	}

	/**
	 * Gets the dtinic.
	 * 
	 * @return the dtinic
	 */
	public Date getDtinic()
	{
		return dtinic;
	}

	/**
	 * Sets the dtinic.
	 * 
	 * @param dtinic the new dtinic
	 */
	public void setDtinic(Date dtinic)
	{
		this.dtinic = dtinic;
	}

	/**
	 * Gets the dtfina.
	 * 
	 * @return the dtfina
	 */
	public Date getDtfina()
	{
		return dtfina;
	}

	/**
	 * Sets the dtfina.
	 * 
	 * @param dtfina the new dtfina
	 */
	public void setDtfina(Date dtfina)
	{
		this.dtfina = dtfina;
	}

	/**
	 * Gets the obssup.
	 * 
	 * @return the obssup
	 */
	public String getObssup()
	{
		return obssup;
	}

	/**
	 * Sets the obssup.
	 * 
	 * @param obssup the new obssup
	 */
	public void setObssup(String obssup)
	{
		this.obssup = obssup;
	}

	/**
	 * Gets the coment.
	 * 
	 * @return the coment
	 */
	public String getComent()
	{
		return coment;
	}

	/**
	 * Sets the coment.
	 * 
	 * @param coment the new coment
	 */
	public void setComent(String coment)
	{
		this.coment = coment;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Suplemento[ getCdsuple()=" + getCdsuple() + ", getSupleme()=" + getSupleme() + ", getMarca()="
				+ getMarca() + ", getQntdia()=" + getQntdia() + ", getDtinic()=" + getDtinic() + ", getDtfina()="
				+ getDtfina() + ", getObssup()=" + getObssup() + ", getComent()=" + getComent()
				+ "]";
	}
}
