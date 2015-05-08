package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModel;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Beneficios extends QATModel
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Integer data;

	/** The description. */
	private String nome;

	/** The codigo. */
	private String codigo;

	/** The horario sair. */
	private String descricao;

	/** The valor. */
	private double valor;

	/** The porcentagem. */
	private double porcentagem;

	/** The tipo. */
	private String tipo;

	/**
	 * Default constructor.
	 */
	public Beneficios()
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
	 * @param data the data to set
	 */
	public void setData(Integer data)
	{
		this.data = data;
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
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
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

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public double getValor()
	{
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor the valor to set
	 */
	public void setValor(double valor)
	{
		this.valor = valor;
	}

	/**
	 * Gets the porcentagem.
	 *
	 * @return the porcentagem
	 */
	public double getPorcentagem()
	{
		return porcentagem;
	}

	/**
	 * Sets the porcentagem.
	 *
	 * @param porcentagem the porcentagem to set
	 */
	public void setPorcentagem(double porcentagem)
	{
		this.porcentagem = porcentagem;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo()
	{
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Beneficios [getId()=" + getId() + ", getData()=" + getData() + ", getNome()=" + getNome()
				+ ", getCodigo()=" + getCodigo() + ", getDescricao()=" + getDescricao() + ", getValor()=" + getValor()
				+ ", getPorcentagem()=" + getPorcentagem() + ", getTipo()=" + getTipo() + ", toString()="
				+ super.toString() + "]";
	}

}
