package com.prosperitasglobal.sendsolv.model;


/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class RentabilidadeItens extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer idRentabilidade;

	/** The type of an account. */
	private Produto produto;

	private Double valor;

	private RentabilidadeTypeEnum rentabilidadeTypeEnum;

	/**
	 * Default constructor.
	 */
	public RentabilidadeItens()
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the idRentabilidade
	 */
	public Integer getIdRentabilidade()
	{
		return idRentabilidade;
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
	 * @param idRentabilidade the idRentabilidade to set
	 */
	public void setIdRentabilidade(Integer idRentabilidade)
	{
		this.idRentabilidade = idRentabilidade;
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
	 * @return the rentabilidadeTypeEnum
	 */
	public RentabilidadeTypeEnum getRentabilidadeTypeEnum()
	{
		return rentabilidadeTypeEnum;
	}

	/**
	 * @param rentabilidadeTypeEnum the rentabilidadeTypeEnum to set
	 */
	public void setRentabilidadeTypeEnum(RentabilidadeTypeEnum rentabilidadeTypeEnum)
	{
		this.rentabilidadeTypeEnum = rentabilidadeTypeEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RentabilidadeItens [getId()=" + getId() + ", getIdRentabilidade()=" + getIdRentabilidade()
				+ ", getProduto()=" + getProduto() + ", getValor()=" + getValor() + ", getRentabilidadeTypeEnum()="
				+ getRentabilidadeTypeEnum() + ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()="
				+ getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId()
				+ ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()="
				+ getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId()
				+ ", getSite()=" + getSite() + ", toString()=" + super.toString() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
