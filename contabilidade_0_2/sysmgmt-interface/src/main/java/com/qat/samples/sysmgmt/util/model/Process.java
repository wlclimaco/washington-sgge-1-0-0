package com.qat.samples.sysmgmt.util.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Process extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Long data;

	/**
	 * Default constructor.
	 */
	public Process()
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
	 * @return the data
	 */
	public Long getData()
	{
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Long data)
	{
		this.data = data;
	}

	@Override
	public String toString()
	{
		return "Process [getId()=" + getId() + ", getData()=" + getData() + ", toString()=" + super.toString() + "]";
	}

}
