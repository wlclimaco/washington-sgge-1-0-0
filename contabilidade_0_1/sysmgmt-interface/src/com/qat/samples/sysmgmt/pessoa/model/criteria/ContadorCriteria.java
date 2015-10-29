package com.qat.samples.sysmgmt.pessoa.model.criteria;

import com.qat.samples.sysmgmt.util.model.criteria.ComumCriteria;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class ContadorCriteria extends ComumCriteria
{

	/** The member. */
	private String crc;

	/**
	 * The Constructor.
	 */
	public ContadorCriteria()
	{
		super();
	}

	/**
	 * @return the crc
	 */
	public String getCrc()
	{
		return crc;
	}

	/**
	 * @param crc the crc to set
	 */
	public void setCrc(String crc)
	{
		this.crc = crc;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ContadorCriteria [getCrc()=" + getCrc() + ", getEmprId()=" + getEmprId() + ", getId()=" + getId()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}
