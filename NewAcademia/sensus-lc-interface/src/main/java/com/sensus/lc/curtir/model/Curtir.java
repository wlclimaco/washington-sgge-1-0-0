/*
 *
 */
package com.sensus.lc.curtir.model;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.base.model.TabelaEnum;

/**
 * The Class Curtir.
 */
@SuppressWarnings("serial")
public class Curtir extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The cd curtir. */
	private Integer cdCurtir;

	/** The curtir type enum. */
	private CurtirTypeEnum curtirTypeEnum;

	/** The acao enum. */
	private TabelaEnum acaoEnum;

	/** The id. */
	private Integer id;

	/**
	 * Gets the cd curtir.
	 * 
	 * @return the cd curtir
	 */
	public Integer getCdCurtir()
	{
		return cdCurtir;
	}

	/**
	 * Sets the cd curtir.
	 * 
	 * @param cdCurtir the new cd curtir
	 */
	public void setCdCurtir(Integer cdCurtir)
	{
		this.cdCurtir = cdCurtir;
	}

	/**
	 * Gets the curtir type enum.
	 * 
	 * @return the curtir type enum
	 */
	public CurtirTypeEnum getCurtirTypeEnum()
	{
		return curtirTypeEnum;
	}

	/**
	 * Sets the curtir type enum.
	 * 
	 * @param curtirTypeEnum the new curtir type enum
	 */
	public void setCurtirTypeEnum(CurtirTypeEnum curtirTypeEnum)
	{
		this.curtirTypeEnum = curtirTypeEnum;
	}

	/**
	 * Gets the acao enum.
	 * 
	 * @return the acao enum
	 */
	public TabelaEnum getAcaoEnum()
	{
		return acaoEnum;
	}

	/**
	 * Sets the acao enum.
	 * 
	 * @param acaoEnum the new acao enum
	 */
	public void setAcaoEnum(TabelaEnum acaoEnum)
	{
		this.acaoEnum = acaoEnum;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "Curtir [getCdCurtir()=" + getCdCurtir() + ", getCurtirTypeEnum()=" + getCurtirTypeEnum()
				+ ", getAcaoEnum()=" + getAcaoEnum() + ", getId()=" + getId() + ", toString()=" + super.toString()
				+ "]";
	}
}
