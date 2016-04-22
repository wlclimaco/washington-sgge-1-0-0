package com.qat.samples.sysmgmt.pessoa.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Transportador extends Pessoa
{

	/**
	 * Default constructor.
	 */
	public Transportador()
	{
		super();
	}

	public Transportador(Integer id)
	{
		super();
		setId(id);
	}

	public Transportador(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		return "Transportador [toString()=" + super.toString() + "]";
	}

}
