package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.fiscal.model.Classificacao;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Pis extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;
	private Integer prodId;
	private DoisValores pISSituaTributaria;
	private Double valorUnidtribPIS;
	private DoisValores tipocalculoSubstTrib;
	private Double valorTribPISST;


	/**
	 * Default constructor.
	 */
	public Pis()
	{
		super();
	}

	public Pis(Integer id)
	{
		super();
		this.id = id;
	}

	public Pis(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public DoisValores getpISSituaTributaria() {
		return pISSituaTributaria;
	}

	public void setpISSituaTributaria(DoisValores pISSituaTributaria) {
		this.pISSituaTributaria = pISSituaTributaria;
	}

	public Double getValorUnidtribPIS() {
		return valorUnidtribPIS;
	}

	public void setValorUnidtribPIS(Double valorUnidtribPIS) {
		this.valorUnidtribPIS = valorUnidtribPIS;
	}

	public DoisValores getTipocalculoSubstTrib() {
		return tipocalculoSubstTrib;
	}

	public void setTipocalculoSubstTrib(DoisValores tipocalculoSubstTrib) {
		this.tipocalculoSubstTrib = tipocalculoSubstTrib;
	}

	public Double getValorTribPISST() {
		return valorTribPISST;
	}

	public void setValorTribPISST(Double valorTribPISST) {
		this.valorTribPISST = valorTribPISST;
	}

	@Override
	public String toString() {
		return "Pis [getId()=" + getId() + ", getProdId()=" + getProdId() + ", getpISSituaTributaria()="
				+ getpISSituaTributaria() + ", getValorUnidtribPIS()=" + getValorUnidtribPIS()
				+ ", getTipocalculoSubstTrib()=" + getTipocalculoSubstTrib() + ", getValorTribPISST()="
				+ getValorTribPISST() + ", toString()=" + super.toString() + "]";
	}

}
