package com.qat.samples.sysmgmt.cnae;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class CnaeEmpresa extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;
	private Cnae idCnae;

	public CnaeEmpresa(Integer id, PersistanceActionEnum mode)
	{
		super();
		this.id = id;
		setModelAction(mode);
	}

	/**
	 * The Constructor.
	 */
	public CnaeEmpresa()
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
	 * @return the idCnae
	 */
	public Cnae getIdCnae()
	{
		return idCnae;
	}

	/**
	 * @param idCnae the idCnae to set
	 */
	public void setIdCnae(Cnae idCnae)
	{
		this.idCnae = idCnae;
	}

	@Override
	public String toString()
	{
		return "CnaeEmpresa [getId()=" + getId() + ", getIdCnae()=" + getIdCnae() + ", toString()=" + super.toString()
				+ "]";
	}
}