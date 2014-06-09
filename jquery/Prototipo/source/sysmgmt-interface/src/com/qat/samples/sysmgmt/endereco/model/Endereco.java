package com.qat.samples.sysmgmt.endereco.model;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.util.TableTypeEnum;
import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Bundle", propOrder = {"id", "code", "description", "price"})
public class Endereco extends Util
{

	/** The id. */
	private Integer id;

	/** The enderecoid. */
	private Integer enderecoid;

	/** The code. */
	private String logradouro;

	/** The bairro. */
	private String bairro;

	/** The estado. */
	private String estado;

	/** The cidade. */
	private String cidade;

	/** The numero. */
	private String numero;

	/** The description. */
	private String cep;

	/** The price. */
	private String nome;

	/** The complemento. */
	private String complemento;

	/** The tabela. */
	private TableTypeEnum tabela;

	/**
	 * Instantiates a new bundle.
	 */
	public Endereco()
	{

	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 */
	public Endereco(Integer id)
	{
		this.id = id;
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
	 * Gets the logradouro.
	 * 
	 * @return the logradouro
	 */
	public String getLogradouro()
	{
		return logradouro;
	}

	/**
	 * Sets the logradouro.
	 * 
	 * @param logradouro the logradouro to set
	 */
	public void setLogradouro(String logradouro)
	{
		this.logradouro = logradouro;
	}

	/**
	 * Gets the bairro.
	 * 
	 * @return the bairro
	 */
	public String getBairro()
	{
		return bairro;
	}

	/**
	 * Sets the bairro.
	 * 
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro)
	{
		this.bairro = bairro;
	}

	/**
	 * Gets the estado.
	 * 
	 * @return the estado
	 */
	public String getEstado()
	{
		return estado;
	}

	/**
	 * Sets the estado.
	 * 
	 * @param estado the estado to set
	 */
	public void setEstado(String estado)
	{
		this.estado = estado;
	}

	/**
	 * Gets the cidade.
	 * 
	 * @return the cidade
	 */
	public String getCidade()
	{
		return cidade;
	}

	/**
	 * Sets the cidade.
	 * 
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}

	/**
	 * Gets the numero.
	 * 
	 * @return the numero
	 */
	public String getNumero()
	{
		return numero;
	}

	/**
	 * Sets the numero.
	 * 
	 * @param numero the numero to set
	 */
	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	/**
	 * Gets the cep.
	 * 
	 * @return the cep
	 */
	public String getCep()
	{
		return cep;
	}

	/**
	 * Sets the cep.
	 * 
	 * @param cep the cep to set
	 */
	public void setCep(String cep)
	{
		this.cep = cep;
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
	 * Gets the complemento.
	 * 
	 * @return the complemento
	 */
	public String getComplemento()
	{
		return complemento;
	}

	/**
	 * Sets the complemento.
	 * 
	 * @param complemento the complemento to set
	 */
	public void setComplemento(String complemento)
	{
		this.complemento = complemento;
	}

	/**
	 * Gets the enderecoid.
	 * 
	 * @return the enderecoid
	 */
	public Integer getEnderecoid()
	{
		return enderecoid;
	}

	/**
	 * Sets the enderecoid.
	 * 
	 * @param enderecoid the new enderecoid
	 */
	public void setEnderecoid(Integer enderecoid)
	{
		this.enderecoid = enderecoid;
	}

	/**
	 * Gets the tabela.
	 * 
	 * @return the tabela
	 */
	public TableTypeEnum getTabela()
	{
		return tabela;
	}

	/**
	 * Sets the tabela.
	 * 
	 * @param tabela the new tabela
	 */
	public void setTabela(TableTypeEnum tabela)
	{
		this.tabela = tabela;
	}

	/**
	 * Gets the table type value.
	 * 
	 * @return the table type value
	 */
	public Integer getTableTypeValue()
	{
		return tabela.getValue();
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 * 
	 * @param cadastroTypeValue the new table type value
	 */
	public void setTableTypeValue(Integer cadastroTypeValue)
	{
		tabela = TableTypeEnum.enumForValue(cadastroTypeValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.QATModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Endereco [getId()=" + getId() + ", getLogradouro()=" + getLogradouro() + ", getBairro()=" + getBairro()
				+ ", getEstado()=" + getEstado() + ", getCidade()=" + getCidade() + ", getNumero()=" + getNumero()
				+ ", getCep()=" + getCep() + ", getNome()=" + getNome() + ", getComplemento()=" + getComplemento()
				+ ", getEnderecoid()=" + getEnderecoid() + ", getTabela()=" + getTabela() + ", getTableTypeValue()="
				+ getTableTypeValue() + ", toString()=" + super.toString() + "]";
	}

}
