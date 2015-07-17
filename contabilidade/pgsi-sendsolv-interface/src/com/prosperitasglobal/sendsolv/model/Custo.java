package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Custo extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer idproduto;

	private Integer entidadeId;

	/** The type of an account. */
	private CustoItem custo;

	private Double valor;

	/**
	 * Default constructor.
	 */
	public Custo()
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
	 * @return the idproduto
	 */
	public Integer getIdproduto()
	{
		return idproduto;
	}

	/**
	 * @param idproduto the idproduto to set
	 */
	public void setIdproduto(Integer idproduto)
	{
		this.idproduto = idproduto;
	}

	/**
	 * @return the custo
	 */
	public CustoItem getCusto()
	{
		return custo;
	}

	/**
	 * @param custo the custo to set
	 */
	public void setCusto(CustoItem custo)
	{
		this.custo = custo;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Custo [getId()=" + getId() + ", getIdproduto()=" + getIdproduto() + ", getCusto()=" + getCusto()
				+ ", getValor()=" + getValor() + ", getEntidadeId()=" + getEntidadeId() + ", getTabelaEnumValue()="
				+ getTabelaEnumValue() + ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()="
				+ getAcaoEnumValue() + ", getParentId()=" + getParentId() + ", getType()=" + getType()
				+ ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()="
				+ getStatusList() + ", getEmprId()=" + getEmprId() + ", getSite()=" + getSite() + ", getProcessId()="
				+ getProcessId() + ", getUserId()=" + getUserId() + ", getNotes()=" + getNotes() + ", toString()="
				+ super.toString() + ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
