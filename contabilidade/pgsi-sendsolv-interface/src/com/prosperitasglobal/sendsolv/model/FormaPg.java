package com.prosperitasglobal.sendsolv.model;


/**
 * The Class FormaPg.
 */
@SuppressWarnings("serial")
public class FormaPg extends ModelCosmeDamiao
{

	/** The id. */
	private Integer id;

	/** The descricao. */
	private String descricao;

	/** The dias pg. */
	private Integer diasPg;

	/** The entrada. */
	private Integer entrada;

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
	 * Gets the dias pg.
	 *
	 * @return the diasPg
	 */
	public Integer getDiasPg()
	{
		return diasPg;
	}

	/**
	 * Sets the dias pg.
	 *
	 * @param diasPg the diasPg to set
	 */
	public void setDiasPg(Integer diasPg)
	{
		this.diasPg = diasPg;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FormaPg [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", getDiasPg()=" + getDiasPg()
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
