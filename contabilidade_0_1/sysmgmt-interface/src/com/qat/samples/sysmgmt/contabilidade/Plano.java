package com.qat.samples.sysmgmt.contabilidade;

import java.util.List;

import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.TabPreco;
import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Plano extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	private Long dataInicio;

	private Long dataFinal;

	private List<TabPreco> preco;

	private Integer numeroContrato;

	private Produto produto;

	public Plano(Integer id)
	{
		super();
		this.id = id;
	}

	/**
	 * The Constructor.
	 */
	public Plano()
	{

	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the dataInicio
	 */
	public Long getDataInicio()
	{
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Long dataInicio)
	{
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFinal
	 */
	public Long getDataFinal()
	{
		return dataFinal;
	}

	/**
	 * @param dataFinal the dataFinal to set
	 */
	public void setDataFinal(Long dataFinal)
	{
		this.dataFinal = dataFinal;
	}

	/**
	 * @return the numeroContrato
	 */
	public Integer getNumeroContrato()
	{
		return numeroContrato;
	}

	/**
	 * @param numeroContrato the numeroContrato to set
	 */
	public void setNumeroContrato(Integer numeroContrato)
	{
		this.numeroContrato = numeroContrato;
	}

	/**
	 * @return the produto
	 */
	public Produto getProduto()
	{
		return produto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}

	/**
	 * @return the preco
	 */
	public List<TabPreco> getPreco()
	{
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(List<TabPreco> preco)
	{
		this.preco = preco;
	}

	@Override
	public String toString()
	{
		return "Plano [getId()=" + getId() + ", getDataInicio()=" + getDataInicio() + ", getDataFinal()="
				+ getDataFinal() + ", getNumeroContrato()=" + getNumeroContrato() + ", getProduto()=" + getProduto()
				+ ", getPreco()=" + getPreco() + ", toString()=" + super.toString() + "]";
	}

}