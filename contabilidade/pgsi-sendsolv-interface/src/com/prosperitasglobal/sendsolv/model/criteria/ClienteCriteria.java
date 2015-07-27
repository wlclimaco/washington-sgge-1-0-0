package com.prosperitasglobal.sendsolv.model.criteria;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class ClienteCriteria extends ComumCriteria
{

	/** The member. */
	private String nome;

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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ClienteCriteria [getNome()=" + getNome() + ", getEmprId()=" + getEmprId() + ", getId()=" + getId()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}
