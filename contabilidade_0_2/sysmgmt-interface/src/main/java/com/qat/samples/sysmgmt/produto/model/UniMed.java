package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class UniMed extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String unimed;

	/** The description. */
	private String sigla;

	/**
	 * Default constructor.
	 */
	public UniMed()
	{
		super();
	}

	public UniMed(Integer id)
	{
		super();
		this.id = id;
	}

	public UniMed(int i, String string) {
		// TODO Auto-generated constructor stub
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
	 * @return the unimed
	 */
	public String getUnimed()
	{
		return unimed;
	}

	/**
	 * @param unimed the unimed to set
	 */
	public void setUnimed(String unimed)
	{
		this.unimed = unimed;
	}

	/**
	 * @return the sigla
	 */
	public String getSigla()
	{
		return sigla;
	}

	/**
	 * @param sigla the sigla to set
	 */
	public void setSigla(String sigla)
	{
		this.sigla = sigla;
	}

	@Override
	public String toString()
	{
		return "UniMed [getId()=" + getId() + ", getUnimed()=" + getUnimed() + ", getSigla()=" + getSigla()
				+ ", toString()=" + super.toString() + "]";
	}

}
