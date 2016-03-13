package com.qat.samples.sysmgmt.pessoa;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Medico extends Pessoa
{

	/**
	 * Default constructor.
	 */
	public Medico()
	{
		super();
	}

	public Medico(Integer id)
	{
		super();
		setId(id);
	}

	@Override
	public String toString()
	{
		return "Transportador [toString()=" + super.toString() + "]";
	}

}
