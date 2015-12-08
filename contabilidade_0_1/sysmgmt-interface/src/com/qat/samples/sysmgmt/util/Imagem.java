package com.qat.samples.sysmgmt.util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
public class Imagem extends ModelCosmeDamiao
{

	/** The local. */
	private String local;

	/** The nome. */
	private String nome;

	/** The foto id. */
	private Integer fotoId;

	/** The id. */
	private Integer id;

	/** The table enum. */
	private TableTypeEnum tableEnum;

	private Boolean principal;

	/**
	 * Instantiates a new imagem.
	 */
	public Imagem()
	{

	}

	/**
	 * Instantiates a new imagem.
	 * 
	 * @param local the local
	 * @param nome the nome
	 * @param fotoId the foto id
	 */
	public Imagem(String local, String nome, Integer fotoId)
	{
		super();
		this.local = local;
		this.nome = nome;
		this.fotoId = fotoId;
	}

	/**
	 * Instantiates a new imagem.
	 * 
	 * @param local the local
	 * @param nome the nome
	 */
	public Imagem(String local, String nome)
	{
		super();
		this.local = local;
		this.nome = nome;
	}

	/**
	 * Gets the local.
	 * 
	 * @return the local
	 */
	public String getLocal()
	{
		return local;
	}

	/**
	 * Sets the local.
	 * 
	 * @param local the new local
	 */
	public void setLocal(String local)
	{
		this.local = local;
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
	 * Gets the foto id.
	 * 
	 * @return the foto id
	 */
	public Integer getFotoId()
	{
		return fotoId;
	}

	/**
	 * Sets the foto id.
	 * 
	 * @param fotoId the new foto id
	 */
	public void setFotoId(Integer fotoId)
	{
		this.fotoId = fotoId;
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the table enum.
	 * 
	 * @return the table enum
	 */
	public TableTypeEnum getTableEnum()
	{
		return tableEnum;
	}

	/**
	 * Sets the table enum.
	 * 
	 * @param tableEnum the new table enum
	 */
	public void setTableEnum(TableTypeEnum tableEnum)
	{
		this.tableEnum = tableEnum;
	}

	/**
	 * Methods that follow the naming pattern get.....Value() provide convenience for returning the primitive value of
	 * an enum. For example, database mapping of an enum to a database column could make use of this method.
	 * 
	 * @return the table type value
	 */
	public Integer getTableTypeValue()
	{
		return tableEnum.getValue();
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 * 
	 * @param cadastroTypeValue the new table type value
	 */
	public void setTableTypeValue(Integer cadastroTypeValue)
	{
		tableEnum = TableTypeEnum.enumForValue(cadastroTypeValue);
	}

	public Boolean getPrincipal()
	{
		return principal;
	}

	public void setPrincipal(Boolean principal)
	{
		this.principal = principal;
	}

	@Override
	public String toString()
	{
		return "Imagem [getLocal()=" + getLocal() + ", getNome()=" + getNome() + ", getFotoId()=" + getFotoId()
				+ ", getId()=" + getId() + ", getTableEnum()=" + getTableEnum() + ", getTableTypeValue()="
				+ getTableTypeValue() + ", getPrincipal()=" + getPrincipal() + ", toString()=" + super.toString() + "]";
	}

}
