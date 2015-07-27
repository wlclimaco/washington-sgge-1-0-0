package com.prosperitasglobal.sendsolv.model.criteria;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class ContatoCriteria extends ComumCriteria
{

	private Long data;

	/**
	 * The Constructor.
	 */
	public ContatoCriteria()
	{
		super();
	}

	/**
	 * @return the data
	 */
	public Long getData()
	{
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Long data)
	{
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ContatoCriteria [getData()=" + getData() + ", getEmprId()=" + getEmprId() + ", getId()=" + getId()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}
