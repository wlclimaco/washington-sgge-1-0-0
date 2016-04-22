package com.qat.samples.sysmgmt.pessoa.model.criteria;

import com.qat.samples.sysmgmt.util.model.criteria.ComumCriteria;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class ClienteCriteria extends ComumCriteria
{

	/** The member. */
	private String nome;

	private Integer parentId;

	/**
	 * The Constructor.
	 */
	public ClienteCriteria()
	{
		super();
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Integer getParentId()
	{
		return parentId;
	}

	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	@Override
	public String toString()
	{
		return "ClienteCriteria [getNome()=" + getNome() + ", getParentId()=" + getParentId() + ", toString()="
				+ super.toString() + "]";
	}

}
