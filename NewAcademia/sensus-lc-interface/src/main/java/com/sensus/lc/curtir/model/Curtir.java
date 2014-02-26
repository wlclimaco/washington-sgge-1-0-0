package com.sensus.lc.curtir.model;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.base.model.AcaoTabelaEnum;
import com.sensus.lc.pessoa.model.Pessoa;

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
	private AcaoTabelaEnum acaoEnum;

	/** The pessoa. */
	private Pessoa pessoa;

	/** The cd evento. */
	private Integer cdEvento;

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
	public AcaoTabelaEnum getAcaoEnum()
	{
		return acaoEnum;
	}

	/**
	 * Sets the acao enum.
	 * 
	 * @param acaoEnum the new acao enum
	 */
	public void setAcaoEnum(AcaoTabelaEnum acaoEnum)
	{
		this.acaoEnum = acaoEnum;
	}

	/**
	 * Gets the pessoa.
	 * 
	 * @return the pessoa
	 */
	public Pessoa getPessoa()
	{
		return pessoa;
	}

	/**
	 * Sets the pessoa.
	 * 
	 * @param pessoa the new pessoa
	 */
	public void setPessoa(Pessoa pessoa)
	{
		this.pessoa = pessoa;
	}

	/**
	 * Gets the cd evento.
	 * 
	 * @return the cd evento
	 */
	public Integer getCdEvento()
	{
		return cdEvento;
	}

	/**
	 * Sets the cd evento.
	 * 
	 * @param cdEvento the new cd evento
	 */
	public void setCdEvento(Integer cdEvento)
	{
		this.cdEvento = cdEvento;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Curtir [getCdcurtir()=" + getCdcurtir() + ", getCdCurtir()=" + getCdCurtir() + ", getCurtirTypeEnum()="
				+ getCurtirTypeEnum() + ", getAcaoEnum()=" + getAcaoEnum() + ", getPessoa()=" + getPessoa()
				+ ", getCdEvento()=" + getCdEvento() + ", toString()=" + super.toString() + "]";
	}
}
