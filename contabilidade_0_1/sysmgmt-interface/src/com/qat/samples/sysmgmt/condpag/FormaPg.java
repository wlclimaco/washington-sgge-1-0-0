package com.qat.samples.sysmgmt.condpag;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

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

	public FormaPg(Integer id, PersistanceActionEnum modelAction)
	{
		super();
		this.id = id;
		setModelAction(modelAction);
	}

	public FormaPg()
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

	@Override
	public String toString()
	{
		return "FormaPg [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", getDiasPg()=" + getDiasPg()
				+ ", toString()=" + super.toString() + "]";
	}

}
