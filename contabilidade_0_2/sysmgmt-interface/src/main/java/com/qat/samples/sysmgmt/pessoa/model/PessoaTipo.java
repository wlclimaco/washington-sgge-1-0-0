package com.qat.samples.sysmgmt.pessoa.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class PessoaTipo extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;


	/** The type of an account. */
	private PessoaTypeEnum pessoaTypeEnum;



	/**
	 * Default constructor.
	 */
	public PessoaTipo()
	{
		super();
	}

	public Integer getPessoaTypeEnumValue()
	{
		if (pessoaTypeEnum != null)
		{
			return pessoaTypeEnum.getValue();
		}
		return null;
	}

	public void setPessoaTypeEnumValue(Integer acaoTypeValue)
	{
		pessoaTypeEnum = PessoaTypeEnum.enumForValue(acaoTypeValue);
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

	public PessoaTypeEnum getPessoaTypeEnum() {
		return pessoaTypeEnum;
	}

	public void setPessoaTypeEnum(PessoaTypeEnum pessoaTypeEnum) {
		this.pessoaTypeEnum = pessoaTypeEnum;
	}

	@Override
	public String toString() {
		return "PessoaTipo [getPessoaTypeEnumValue()=" + getPessoaTypeEnumValue() + ", getId()=" + getId()
				+ ", getPessoaTypeEnum()=" + getPessoaTypeEnum() + ", toString()=" + super.toString() + "]";
	}


}
