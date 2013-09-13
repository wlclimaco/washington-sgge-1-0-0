package com.sensus.dm.common.dieta.model;

import com.sensus.common.model.SensusModel;

@SuppressWarnings("serial")
public class Dietaitens extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;
	private Integer cddietitens;
	private String refeicao;
	private String opção;
	private Float qnt;

	/**
	 * Gets the cddietitens.
	 * 
	 * @return the cddietitens
	 */
	public Integer getCddietitens()
	{
		return cddietitens;
	}

	/**
	 * Sets the cddietitens.
	 * 
	 * @param cddietitens the new cddietitens
	 */
	public void setCddietitens(Integer cddietitens)
	{
		this.cddietitens = cddietitens;
	}

	/**
	 * Gets the refeicao.
	 * 
	 * @return the refeicao
	 */
	public String getRefeicao()
	{
		return refeicao;
	}

	/**
	 * Sets the refeicao.
	 * 
	 * @param refeicao the new refeicao
	 */
	public void setRefeicao(String refeicao)
	{
		this.refeicao = refeicao;
	}

	/**
	 * Gets the opção.
	 * 
	 * @return the opção
	 */
	public String getOpção()
	{
		return opção;
	}

	/**
	 * Sets the opção.
	 * 
	 * @param opção the new opção
	 */
	public void setOpção(String opção)
	{
		this.opção = opção;
	}

	/**
	 * Gets the qnt.
	 * 
	 * @return the qnt
	 */
	public Float getQnt()
	{
		return qnt;
	}

	/**
	 * Sets the qnt.
	 * 
	 * @param qnt the new qnt
	 */
	public void setQnt(Float qnt)
	{
		this.qnt = qnt;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Dietaitens[ getCddietitens()=" + getCddietitens() + ", getRefeicao()=" + getRefeicao()
				+ ", getOpção()=" + getOpção() + ", getQnt()=" + getQnt()
				+ "]";
	}
}
