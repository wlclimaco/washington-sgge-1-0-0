package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ItensEspeciais extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer IdNota;

	/** The tipo endereco. */
	private String nome;

	private Integer item;

	private Double valor;

	private Double baseCalculo;

	private Double aliguotaICMS;

	private Double valorICMS;

	/**
	 * Default constructor.
	 */
	public ItensEspeciais()
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
	 * @return the idNota
	 */
	public Integer getIdNota()
	{
		return IdNota;
	}

	/**
	 * @param idNota the idNota to set
	 */
	public void setIdNota(Integer idNota)
	{
		IdNota = idNota;
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * @return the item
	 */
	public Integer getItem()
	{
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Integer item)
	{
		this.item = item;
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
	 * @return the baseCalculo
	 */
	public Double getBaseCalculo()
	{
		return baseCalculo;
	}

	/**
	 * @param baseCalculo the baseCalculo to set
	 */
	public void setBaseCalculo(Double baseCalculo)
	{
		this.baseCalculo = baseCalculo;
	}

	/**
	 * @return the aliguotaICMS
	 */
	public Double getAliguotaICMS()
	{
		return aliguotaICMS;
	}

	/**
	 * @param aliguotaICMS the aliguotaICMS to set
	 */
	public void setAliguotaICMS(Double aliguotaICMS)
	{
		this.aliguotaICMS = aliguotaICMS;
	}

	/**
	 * @return the valorICMS
	 */
	public Double getValorICMS()
	{
		return valorICMS;
	}

	/**
	 * @param valorICMS the valorICMS to set
	 */
	public void setValorICMS(Double valorICMS)
	{
		this.valorICMS = valorICMS;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ItensEspeciais [getId()=" + getId() + ", getIdNota()=" + getIdNota() + ", getNome()=" + getNome()
				+ ", getItem()=" + getItem() + ", getValor()=" + getValor() + ", getBaseCalculo()=" + getBaseCalculo()
				+ ", getAliguotaICMS()=" + getAliguotaICMS() + ", getValorICMS()=" + getValorICMS()
				+ ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()=" + getTypeValue()
				+ ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId() + ", getType()="
				+ getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum()
				+ ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId() + ", getSite()=" + getSite()
				+ ", toString()=" + super.toString() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()="
				+ getModifyUser() + ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
