package com.sensus.lc.curtir.model;

import java.sql.Date;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.base.model.AcaoTabelaEnum;

@SuppressWarnings("serial")
public class Curtir extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;
	private AcaoTabelaEnum acaoEnum;
	private Date dtcurtir;
	private String coment;
	private String curtir;

	/**
	 * Gets the cdcurtir.
	 * 
	 * @return the cdcurtir
	 */
	public AcaoTabelaEnum getCdcurtir()
	{
		return acaoEnum;
	}

	/**
	 * Sets the cdcurtir.
	 * 
	 * @param cdcurtir the new cdcurtir
	 */
	public void setCdcurtir(AcaoTabelaEnum cdcurtir)
	{
		acaoEnum = cdcurtir;
	}

	/**
	 * Gets the dtpost.
	 * 
	 * @return the dtpost
	 */
	public Date getDtpost()
	{
		return dtcurtir;
	}

	/**
	 * Sets the dtpost.
	 * 
	 * @param dtpost the new dtpost
	 */
	public void setDtpost(Date dtpost)
	{
		dtcurtir = dtpost;
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

	/**
	 * Gets the curtir.
	 * 
	 * @return the curtir
	 */
	public String getFoto()
	{
		return curtir;
	}

	/**
	 * Sets the curtir.
	 * 
	 * @param curtir the new curtir
	 */
	public void setFoto(String curtir)
	{
		this.curtir = curtir;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Foto[ getCdcurtir()=" + getCdcurtir() + ", getDtpost()=" + getDtpost() + ", getComent()=" + getComent()
				+ ", getFoto()=" + getFoto()
				+ "]";
	}
}
