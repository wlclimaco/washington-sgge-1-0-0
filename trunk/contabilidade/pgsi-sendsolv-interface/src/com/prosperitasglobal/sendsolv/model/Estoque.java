package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Estoque extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private EstoqueTypeEnum estoqueTypeEnum;

	private Integer entidadeId;

	/** The description. */
	private Long ultimoMov;

	private Double quant;

	/**
	 * Default constructor.
	 */
	public Estoque()
	{
		super();
	}

	public Integer getEstoqueTypeEnumValue()
	{
		if (estoqueTypeEnum != null)
		{
			return estoqueTypeEnum.getValue();
		}
		return null;
	}

	public void setEstoqueTypeEnumValue(Integer acaoTypeValue)
	{
		estoqueTypeEnum = EstoqueTypeEnum.enumForValue(acaoTypeValue);
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
	 * @return the estoqueTypeEnum
	 */
	public EstoqueTypeEnum getEstoqueTypeEnum()
	{
		return estoqueTypeEnum;
	}

	/**
	 * @param estoqueTypeEnum the estoqueTypeEnum to set
	 */
	public void setEstoqueTypeEnum(EstoqueTypeEnum estoqueTypeEnum)
	{
		this.estoqueTypeEnum = estoqueTypeEnum;
	}

	/**
	 * @return the ultimoMov
	 */
	public Long getUltimoMov()
	{
		return ultimoMov;
	}

	/**
	 * @param ultimoMov the ultimoMov to set
	 */
	public void setUltimoMov(Long ultimoMov)
	{
		this.ultimoMov = ultimoMov;
	}

	/**
	 * @return the quant
	 */
	public Double getQuant()
	{
		return quant;
	}

	/**
	 * @param quant the quant to set
	 */
	public void setQuant(Double quant)
	{
		this.quant = quant;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Estoque [getId()=" + getId() + ", getEstoqueTypeEnum()=" + getEstoqueTypeEnum() + ", getUltimoMov()="
				+ getUltimoMov() + ", getQuant()=" + getQuant() + ", getTabelaEnumValue()=" + getTabelaEnumValue()
				+ ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue()
				+ ", getParentId()=" + getParentId() + ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType()
				+ ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()="
				+ getEmprId() + ", getSite()=" + getSite() + ", toString()=" + super.toString() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
