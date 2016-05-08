package com.qat.samples.sysmgmt.produto.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Servico extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	private String descricao;

	private List<Preco> preco;

	/**
	 * Default constructor.
	 */
	public Servico()
	{
		super();
	}

	public Servico(int i, String string) {

		this.nome = string;
		this.id = i;
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
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

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public List<Preco> getPreco()
	{
		return preco;
	}

	public void setPreco(List<Preco> preco)
	{
		this.preco = preco;
	}

	@Override
	public String toString()
	{
		return "Servico [getId()=" + getId() + ", getNome()=" + getNome() + ", getDescricao()=" + getDescricao()
				+ ", getPreco()=" + getPreco() + ", getStatusList()=" + getStatusList() + ", toString()="
				+ super.toString() + "]";
	}

}
