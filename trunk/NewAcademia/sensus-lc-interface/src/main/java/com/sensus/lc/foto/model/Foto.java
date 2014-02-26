package com.sensus.lc.foto.model;

import java.sql.Date;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.base.model.AcaoTabelaEnum;

@SuppressWarnings("serial")
public class Foto extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;
	private Integer cdfoto;
	private Date dtpost;
	private String coment;
	private String foto;
	private AcaoTabelaEnum acaoComentarioEnum;

	/**
	 * Gets the cdfoto.
	 * 
	 * @return the cdfoto
	 */
	public Integer getCdfoto()
	{
		return cdfoto;
	}

	/**
	 * Sets the cdfoto.
	 * 
	 * @param cdfoto the new cdfoto
	 */
	public void setCdfoto(Integer cdfoto)
	{
		this.cdfoto = cdfoto;
	}

	/**
	 * Gets the dtpost.
	 * 
	 * @return the dtpost
	 */
	public Date getDtpost()
	{
		return dtpost;
	}

	/**
	 * Sets the dtpost.
	 * 
	 * @param dtpost the new dtpost
	 */
	public void setDtpost(Date dtpost)
	{
		this.dtpost = dtpost;
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
	 * Gets the foto.
	 * 
	 * @return the foto
	 */
	public String getFoto()
	{
		return foto;
	}

	/**
	 * Sets the foto.
	 * 
	 * @param foto the new foto
	 */
	public void setFoto(String foto)
	{
		this.foto = foto;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Foto[ getCdfoto()=" + getCdfoto() + ", getDtpost()=" + getDtpost() + ", getComent()=" + getComent()
				+ ", getFoto()=" + getFoto()
				+ "]";
	}
}
