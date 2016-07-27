package com.qat.samples.sysmgmt.fiscal.model;

import com.qat.samples.sysmgmt.produto.model.Incidencia;
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
	private Integer produtoId;
	private Double aliquotaPis;
	private Integer valorUnidPIS;
	private Integer tipocalculoST;
	private Double valorPisSt;



	/**
	 * Default constructor.
	 */
	public Pis()
	{
		super();
	}

	public Pis(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public Double getAliquotaPis() {
		return aliquotaPis;
	}

	public void setAliquotaPis(Double aliquotaPis) {
		this.aliquotaPis = aliquotaPis;
	}

	public Integer getValorUnidPIS() {
		return valorUnidPIS;
	}

	public void setValorUnidPIS(Integer valorUnidPIS) {
		this.valorUnidPIS = valorUnidPIS;
	}

	public Integer getTipocalculoST() {
		return tipocalculoST;
	}

	public void setTipocalculoST(Integer tipocalculoST) {
		this.tipocalculoST = tipocalculoST;
	}

	public Double getValorPisSt() {
		return valorPisSt;
	}

	public void setValorPisSt(Double valorPisSt) {
		this.valorPisSt = valorPisSt;
	}

	@Override
	public String toString() {
		return "Pis [getId()=" + getId() + ", getProdutoId()=" + getProdutoId() + ", getAliquotaPis()="
				+ getAliquotaPis() + ", getValorUnidPIS()=" + getValorUnidPIS() + ", getTipocalculoST()="
				+ getTipocalculoST() + ", getValorPisSt()=" + getValorPisSt() + ", toString()=" + super.toString()
				+ "]";
	}



}
