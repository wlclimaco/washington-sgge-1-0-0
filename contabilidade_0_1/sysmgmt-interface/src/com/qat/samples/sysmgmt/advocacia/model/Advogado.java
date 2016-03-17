package com.qat.samples.sysmgmt.advocacia.model;

import com.qat.samples.sysmgmt.pessoa.Pessoa;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Advogado extends Pessoa
{

	public Advogado()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		return "Advogado [toString()=" + super.toString() + "]";
	}

}
