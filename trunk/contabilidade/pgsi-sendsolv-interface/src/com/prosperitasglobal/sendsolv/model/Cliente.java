package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Cliente extends Pessoa
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer cdEmpr;

	/** The horarios. */
	private List<Profissao> profissao;

	/**
	 * Default constructor.
	 */
	public Cliente()
	{
		super();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Override
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	@Override
	public void setId(Integer id)
	{
		this.id = id;
	}

}
