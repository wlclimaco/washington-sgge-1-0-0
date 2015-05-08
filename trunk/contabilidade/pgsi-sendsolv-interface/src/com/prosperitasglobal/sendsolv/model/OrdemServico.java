package com.prosperitasglobal.sendsolv.model;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class OrdemServico extends QATModel
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Integer cdEmpresa;

	/** The description. */
	private String nome;

	/** The estado. */
	private Integer data;

	/** The bairro. */
	private String type;

	/** The numero. */
	private String assunto;

	/** The cep. */
	private String texto;

	/** The tipo endereco. */
	private String status;

	/**
	 * Default constructor.
	 */
	public OrdemServico()
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

	/**
	 * Gets the cd empresa.
	 *
	 * @return the cd empresa
	 */
	public Integer getCdEmpresa()
	{
		return cdEmpresa;
	}

	/**
	 * Sets the cd empresa.
	 *
	 * @param cdEmpresa the new cd empresa
	 */
	public void setCdEmpresa(Integer cdEmpresa)
	{
		this.cdEmpresa = cdEmpresa;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Integer getData()
	{
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Integer data)
	{
		this.data = data;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Gets the assunto.
	 *
	 * @return the assunto
	 */
	public String getAssunto()
	{
		return assunto;
	}

	/**
	 * Sets the assunto.
	 *
	 * @param assunto the new assunto
	 */
	public void setAssunto(String assunto)
	{
		this.assunto = assunto;
	}

	/**
	 * Gets the texto.
	 *
	 * @return the texto
	 */
	public String getTexto()
	{
		return texto;
	}

	/**
	 * Sets the texto.
	 *
	 * @param texto the new texto
	 */
	public void setTexto(String texto)
	{
		this.texto = texto;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

}
