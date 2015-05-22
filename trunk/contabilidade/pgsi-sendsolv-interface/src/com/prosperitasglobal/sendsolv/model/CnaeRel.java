package com.prosperitasglobal.sendsolv.model;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class CnaeRel extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	/** The parent key. */
	private Integer parentId;

	/**
	 * The Constructor.
	 */
	public CnaeRel()
	{

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
	 * @param id the id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	@Override
	public Integer getParentId()
	{
		return parentId;
	}

	@Override
	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	@Override
	public String toString()
	{
		return "CnaeRel [getId()=" + getId() + ", getParentId()=" + getParentId() + ", getType()=" + getType()
				+ ", getAcaoType()=" + getAcaoType()
				+ ", getTabelaEnum()=" + getTabelaEnum() + ", toString()=" + super.toString() + "]";
	}

}