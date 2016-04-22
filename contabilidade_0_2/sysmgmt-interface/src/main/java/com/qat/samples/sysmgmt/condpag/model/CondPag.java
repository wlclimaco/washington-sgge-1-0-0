package com.qat.samples.sysmgmt.condpag.model;

import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class CondPag extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	/** The description. */
	private double valorIni;

	private double valorFin;

	/** The estado. */
	private Integer parcelas;

	/** The bairro. */
	private List<TipoPag> listTipoPag;

	public CondPag()
	{

	}

	public CondPag(Integer id, String nome, double valorIni, double valorFin, Integer parcelas,
			List<TipoPag> listTipoPag, PersistenceActionEnum modelAction)
	{
		super();
		this.id = id;
		this.nome = nome;
		this.valorIni = valorIni;
		this.valorFin = valorFin;
		this.parcelas = parcelas;
		this.listTipoPag = listTipoPag;
		setModelAction(modelAction);
	}

	public CondPag(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * @return the valorIni
	 */
	public double getValorIni()
	{
		return valorIni;
	}

	/**
	 * @param valorIni the valorIni to set
	 */
	public void setValorIni(double valorIni)
	{
		this.valorIni = valorIni;
	}

	/**
	 * @return the valorFin
	 */
	public double getValorFin()
	{
		return valorFin;
	}

	/**
	 * @param valorFin the valorFin to set
	 */
	public void setValorFin(double valorFin)
	{
		this.valorFin = valorFin;
	}

	/**
	 * @return the parcelas
	 */
	public Integer getParcelas()
	{
		return parcelas;
	}

	/**
	 * @param parcelas the parcelas to set
	 */
	public void setParcelas(Integer parcelas)
	{
		this.parcelas = parcelas;
	}

	/**
	 * @return the listTipoPag
	 */
	public List<TipoPag> getListTipoPag()
	{
		return listTipoPag;
	}

	/**
	 * @param listTipoPag the listTipoPag to set
	 */
	public void setListTipoPag(List<TipoPag> listTipoPag)
	{
		this.listTipoPag = listTipoPag;
	}

	@Override
	public String toString()
	{
		return "CondPag [getId()=" + getId() + ", getNome()=" + getNome() + ", getValorIni()=" + getValorIni()
				+ ", getValorFin()=" + getValorFin() + ", getParcelas()=" + getParcelas() + ", getListTipoPag()="
				+ getListTipoPag() + ", toString()=" + super.toString() + "]";
	}

}
