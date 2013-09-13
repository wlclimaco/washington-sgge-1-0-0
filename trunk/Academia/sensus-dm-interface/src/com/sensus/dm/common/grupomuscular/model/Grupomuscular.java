package com.sensus.dm.commons.grupomuscular.model;

import com.sensus.common.model.SensusModel;

@SuppressWarnings("serial")
public class Grupomuscular extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;
	private Integer cdgrmusc;
	private String musculo;
	private String dsgrmusc;

	/**
	 * Gets the cdgrmusc.
	 * 
	 * @return the cdgrmusc
	 */
	public Integer getCdgrmusc()
	{
		return cdgrmusc;
	}

	/**
	 * Sets the cdgrmusc.
	 * 
	 * @param cdgrmusc the new cdgrmusc
	 */
	public void setCdgrmusc(Integer cdgrmusc)
	{
		this.cdgrmusc = cdgrmusc;
	}

	/**
	 * Gets the musculo.
	 * 
	 * @return the musculo
	 */
	public String getMusculo()
	{
		return musculo;
	}

	/**
	 * Sets the musculo.
	 * 
	 * @param musculo the new musculo
	 */
	public void setMusculo(String musculo)
	{
		this.musculo = musculo;
	}

	/**
	 * Gets the dsgrmusc.
	 * 
	 * @return the dsgrmusc
	 */
	public String getDsgrmusc()
	{
		return dsgrmusc;
	}

	/**
	 * Sets the dsgrmusc.
	 * 
	 * @param dsgrmusc the new dsgrmusc
	 */
	public void setDsgrmusc(String dsgrmusc)
	{
		this.dsgrmusc = dsgrmusc;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Grupomuscular[ getCdgrmusc()=" + getCdgrmusc() + ", getMusculo()=" + getMusculo() + ", getDsgrmusc()="
				+ getDsgrmusc()
				+ "]";
	}
}
