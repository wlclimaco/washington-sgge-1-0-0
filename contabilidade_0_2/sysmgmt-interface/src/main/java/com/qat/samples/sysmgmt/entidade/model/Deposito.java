package com.qat.samples.sysmgmt.entidade.model;

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

	public Deposito(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		return "Deposito [toString()=" + super.toString() + "]";
	}

}
