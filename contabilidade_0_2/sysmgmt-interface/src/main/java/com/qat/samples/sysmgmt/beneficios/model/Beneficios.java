package com.qat.samples.sysmgmt.beneficios.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Beneficios extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

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

	public Beneficios(Integer id)
	{
		super();
		this.id = id;
	}

	public Beneficios(Integer id, String nome, String codigo, String descricao, double valor, double porcentagem,
			String tipo, PersistenceActionEnum modelAction)
	{
		super();
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.porcentagem = porcentagem;
		this.tipo = tipo;
		setModelAction(modelAction);
	}

	public Beneficios(int i, String string) {
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString()
	{
		return "Beneficios [getId()=" + getId() + ", getNome()=" + getNome() + ", getCodigo()=" + getCodigo()
				+ ", getDescricao()=" + getDescricao() + ", getValor()=" + getValor() + ", getPorcentagem()="
				+ getPorcentagem() + ", getTipo()=" + getTipo() + ", toString()=" + super.toString() + "]";
	}

}
