package com.qat.samples.sysmgmt.entidade;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Deposito extends Entidade
{

	/**
	 * Default constructor.
	 */
	public Deposito()
	{
		super();
	}

	@Override
	public String toString()
	{
		return "Deposito [toString()=" + super.toString() + "]";
	}

}
