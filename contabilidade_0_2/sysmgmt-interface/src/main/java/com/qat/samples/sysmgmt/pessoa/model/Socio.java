package com.qat.samples.sysmgmt.pessoa.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Socio extends ModelCosmeDamiao
{

	private Integer id;
	/** The description. */
	private Double cota;

	private Long dataProlabore;

	private Double valorProlabore;

	/** The description. */
	private String porcentagem;

	private Integer socioAdm;

	private Cliente pessoa;

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
	public Double getCota()
	{
		return cota;
	}

	/**
	 * Sets the cota.
	 *
	 * @param cota the cota to set
	 */
	public void setCota(Double cota)
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



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getPessoa() {
		return pessoa;
	}

	public void setPessoa(Cliente pessoa) {
		this.pessoa = pessoa;
	}



	public Long getDataProlabore() {
		return dataProlabore;
	}

	public void setDataProlabore(Long dataProlabore) {
		this.dataProlabore = dataProlabore;
	}

	public Double getValorProlabore() {
		return valorProlabore;
	}

	public void setValorProlabore(Double valorProlabore) {
		this.valorProlabore = valorProlabore;
	}

	@Override
	public String toString() {
		return "Socio [getCota()=" + getCota() + ", getPorcentagem()=" + getPorcentagem() + ", getSocioAdm()="
				+ getSocioAdm() + ", getId()=" + getId() + ", getPessoa()=" + getPessoa() + ", getDataProlabore()="
				+ getDataProlabore() + ", getValorProlabore()=" + getValorProlabore() + ", toString()="
				+ super.toString() + "]";
	}

}
