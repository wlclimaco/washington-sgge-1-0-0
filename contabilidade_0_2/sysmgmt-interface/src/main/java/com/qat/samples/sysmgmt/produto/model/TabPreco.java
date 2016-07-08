package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class TabPreco extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer entidadeId;

	/** The type of an account. */
	private Long dataMarcacao;

	/** The description. */
	private PrecoTypeEnum precoTypeEnum;

	/** The estado. */
	private Double valor;

	private Long dataProInicial;

	private Long dataProFinal;

	private Integer maxVendProd;

	/**
	 * Default constructor.
	 */
	public TabPreco()
	{
		super();
	}

	public Integer getPrecoTypeEnumValue()
	{
		if (precoTypeEnum != null)
		{
			return precoTypeEnum.getValue();
		}
		return null;
	}

	public void setPrecoTypeEnumValue(Integer acaoTypeValue)
	{
		precoTypeEnum = PrecoTypeEnum.enumForValue(acaoTypeValue);
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
	 * @return the dataMarcacao
	 */
	public Long getDataMarcacao()
	{
		return dataMarcacao;
	}

	/**
	 * @param dataMarcacao the dataMarcacao to set
	 */
	public void setDataMarcacao(Long dataMarcacao)
	{
		this.dataMarcacao = dataMarcacao;
	}

	/**
	 * @return the precoTypeEnum
	 */
	public PrecoTypeEnum getPrecoTypeEnum()
	{
		return precoTypeEnum;
	}

	/**
	 * @param precoTypeEnum the precoTypeEnum to set
	 */
	public void setPrecoTypeEnum(PrecoTypeEnum precoTypeEnum)
	{
		this.precoTypeEnum = precoTypeEnum;
	}

	/**
	 * @return the valor
	 */
	public Double getValor()
	{
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor)
	{
		this.valor = valor;
	}

	/**
	 * @return the dataProInicial
	 */
	public Long getDataProInicial()
	{
		return dataProInicial;
	}

	/**
	 * @param dataProInicial the dataProInicial to set
	 */
	public void setDataProInicial(Long dataProInicial)
	{
		this.dataProInicial = dataProInicial;
	}

	/**
	 * @return the dataProFinal
	 */
	public Long getDataProFinal()
	{
		return dataProFinal;
	}

	/**
	 * @param dataProFinal the dataProFinal to set
	 */
	public void setDataProFinal(Long dataProFinal)
	{
		this.dataProFinal = dataProFinal;
	}

	/**
	 * @return the entidadeId
	 */
	public Integer getEntidadeId()
	{
		return entidadeId;
	}

	/**
	 * @param entidadeId the entidadeId to set
	 */
	public void setEntidadeId(Integer entidadeId)
	{
		this.entidadeId = entidadeId;
	}

	public Integer getMaxVendProd()
	{
		return maxVendProd;
	}

	public void setMaxVendProd(Integer maxVendProd)
	{
		this.maxVendProd = maxVendProd;
	}

	@Override
	public String toString()
	{
		return "TabPreco [getPrecoTypeEnumValue()=" + getPrecoTypeEnumValue() + ", getId()=" + getId()
				+ ", getDataMarcacao()=" + getDataMarcacao() + ", getPrecoTypeEnum()=" + getPrecoTypeEnum()
				+ ", getValor()=" + getValor() + ", getDataProInicial()=" + getDataProInicial()
				+ ", getDataProFinal()=" + getDataProFinal() + ", getEntidadeId()=" + getEntidadeId()
				+ ", getMaxVendProd()=" + getMaxVendProd() + ", toString()=" + super.toString() + "]";
	}

}
