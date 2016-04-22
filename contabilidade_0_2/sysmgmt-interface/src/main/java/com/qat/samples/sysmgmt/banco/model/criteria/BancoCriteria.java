package com.qat.samples.sysmgmt.banco.model.criteria;

import com.qat.samples.sysmgmt.util.model.criteria.ComumCriteria;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class BancoCriteria extends ComumCriteria
{

	/** The business id. */
	private Integer agenciaId;

	/**
	 * The Constructor.
	 */
	public BancoCriteria()
	{
		super();
	}

	/**
	 * @return the agenciaId
	 */
	public Integer getAgenciaId()
	{
		return agenciaId;
	}

	/**
	 * @param agenciaId the agenciaId to set
	 */
	public void setAgenciaId(Integer agenciaId)
	{
		this.agenciaId = agenciaId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BancoCriteria [getAgenciaId()=" + getAgenciaId() + ", getEmprId()=" + getEmprId() + ", getId()="
				+ getId() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
