package com.qat.samples.sysmgmt.pessoa.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Contador extends Pessoa
{

	private String crc;

	/**
	 * Default constructor.
	 */
	public Contador()
	{
		super();
	}

	/**
	 * @return the crc
	 */
	public String getCrc()
	{
		return crc;
	}

	/**
	 * @param crc the crc to set
	 */
	public void setCrc(String crc)
	{
		this.crc = crc;
	}

	@Override
	public String toString()
	{
		return "Contador [getCrc()=" + getCrc() + ", toString()=" + super.toString() + "]";
	}

}
