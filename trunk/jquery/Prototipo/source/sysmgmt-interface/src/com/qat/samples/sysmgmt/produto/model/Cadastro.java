package com.qat.samples.sysmgmt.produto.model;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.util.Imagem;
import com.qat.samples.sysmgmt.util.TableTypeEnum;
import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Cadastro", propOrder = {"id", "type", "nome", "produtos", "descricao", "imagens"})
public class Cadastro extends Util
{

	/** The id. */
	private Integer id;

	/** The code. */
	private TableTypeEnum type = TableTypeEnum.CLIENTE;

	/** The description. */
	private String nome;

	private Integer produtos;

	/** The price. */
	private String descricao;

	private List<Imagem> imagens;

	/**
	 * Instantiates a new bundle.
	 */
	public Cadastro()
	{

	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 */
	public Cadastro(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getProdutos()
	{
		return produtos;
	}

	public void setProdutos(Integer produtos)
	{
		this.produtos = produtos;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public TableTypeEnum getType()
	{
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type the type to set
	 */
	public void setType(TableTypeEnum type)
	{
		this.type = type;
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
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Gets the descricao.
	 * 
	 * @return the descricao
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * Sets the descricao.
	 * 
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public List<Imagem> getImagens()
	{
		return imagens;
	}

	public void setImagens(List<Imagem> imagens)
	{
		this.imagens = imagens;
	}

	/**
	 * Methods that follow the naming pattern get.....Value() provide convenience for returning the primitive value of
	 * an enum. For example, database mapping of an enum to a database column could make use of this method.
	 * 
	 * @return
	 */
	public Integer getTableTypeEnumValue()
	{
		return type.getValue();
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 * 
	 * @return
	 */
	public void setTableTypeEnumValue(Integer tableTypeEnumValue)
	{
		type = TableTypeEnum.enumForValue(tableTypeEnumValue);
	}

	/**
	 * Instantiates a new cadastro.
	 * 
	 * @param id the id
	 * @param type the type
	 */
	public Cadastro(Integer id, TableTypeEnum type)
	{
		super();
		this.id = id;
		this.type = type;
	}

	/**
	 * Instantiates a new cadastro.
	 * 
	 * @param id the id
	 * @param type the type
	 * @param nome the nome
	 */
	public Cadastro(Integer id, TableTypeEnum type, String nome)
	{
		super();
		this.id = id;
		this.type = type;
		this.nome = nome;
	}

	public Cadastro(TableTypeEnum type)
	{
		super();
		this.type = type;
	}

	@Override
	public String toString()
	{
		return "Cadastro [getId()=" + getId() + ", getProdutos()=" + getProdutos() + ", getType()=" + getType()
				+ ", getNome()=" + getNome() + ", getDescricao()=" + getDescricao() + ", getImagens()=" + getImagens()
				+ ", getTableTypeEnumValue()=" + getTableTypeEnumValue() + ", toString()=" + super.toString() + "]";
	}

}
