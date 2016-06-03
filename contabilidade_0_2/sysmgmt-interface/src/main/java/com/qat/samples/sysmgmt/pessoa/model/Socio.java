package com.qat.samples.sysmgmt.pessoa.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Socio extends Pessoa
{

	/** The description. */
	private String cota;

	/** The description. */
	private String porcentagem;

	private Integer socioAdm;

	/**
	 * Default constructor.
	 */
	public Socio()
	{
		super();
	}

	/**
	 * Gets the cota.
	 *
	 * @return the cota
	 */
	public String getCota()
	{
		return cota;
	}

	/**
	 * Sets the cota.
	 *
	 * @param cota the cota to set
	 */
	public void setCota(String cota)
	{
		this.cota = cota;
	}

	/**
	 * Gets the porcentagem.
	 *
	 * @return the porcentagem
	 */
	public String getPorcentagem()
	{
		return porcentagem;
	}

	/**
	 * Sets the porcentagem.
	 *
	 * @param porcentagem the porcentagem to set
	 */
	public void setPorcentagem(String porcentagem)
	{
		this.porcentagem = porcentagem;
	}

	public Integer getSocioAdm() {
		return socioAdm;
	}

	public void setSocioAdm(Integer socioAdm) {
		this.socioAdm = socioAdm;
	}

	@Override
	public String toString() {
		return "Socio [getCota()=" + getCota() + ", getPorcentagem()=" + getPorcentagem() + ", getSocioAdm()="
				+ getSocioAdm() + ", toString()=" + super.toString() + "]";
	}

}
