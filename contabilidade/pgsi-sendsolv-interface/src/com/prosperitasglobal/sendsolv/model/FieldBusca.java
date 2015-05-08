package com.prosperitasglobal.sendsolv.model;


// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class FieldBusca extends QATModel
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Tabela tabela;

	/**
	 * Default constructor.
	 */
	public FieldBusca()
	{
		super();
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
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	public Tabela getTabela()
	{
		return tabela;
	}

	public void setTabela(Tabela tabela)
	{
		this.tabela = tabela;
	}

}
