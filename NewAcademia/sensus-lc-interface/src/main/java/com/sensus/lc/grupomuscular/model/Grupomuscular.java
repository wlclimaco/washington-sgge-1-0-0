package com.sensus.lc.grupomuscular.model;

import com.sensus.lc.base.model.PrincipalClass;

/**
 * The Class Grupomuscular.
 */
@SuppressWarnings("serial")
public class Grupomuscular extends PrincipalClass
{

	/** The cdgrmusc. */
	private Integer cdgrmusc;

	/** The musculo. */
	private String musculo;

	/** The dsgrmusc. */
	private String dsgrmusc;

	/**
	 * Instantiates a new grupomuscular.
	 */
	public Grupomuscular()
	{
		super();
	}

	/**
	 * Instantiates a new grupomuscular.
	 * 
	 * @param cdgrmusc the cdgrmusc
	 * @param musculo the musculo
	 * @param dsgrmusc the dsgrmusc
	 */
	public Grupomuscular(Integer cdgrmusc, String musculo, String dsgrmusc)
	{
		super();
		this.cdgrmusc = cdgrmusc;
		this.musculo = musculo;
		this.dsgrmusc = dsgrmusc;
	}

	/**
	 * Instantiates a new grupomuscular.
	 * 
	 * @param cdgrmusc the cdgrmusc
	 */
	public Grupomuscular(Integer cdgrmusc)
	{
		super();
		this.cdgrmusc = cdgrmusc;
	}

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
		return "Grupomuscular [getCdgrmusc()=" + getCdgrmusc() + ", getMusculo()=" + getMusculo() + ", getDsgrmusc()="
				+ getDsgrmusc() + ", toString()=" + super.toString() + "]";
	}

}
