package com.qat.samples.sysmgmt.pessoa;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Paciente extends Pessoa
{

	/**
	 * Default constructor.
	 */
	public Paciente()
	{
		super();
	}

	public Paciente(Integer id)
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
