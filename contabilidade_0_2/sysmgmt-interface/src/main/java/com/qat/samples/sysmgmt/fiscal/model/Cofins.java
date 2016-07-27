package com.qat.samples.sysmgmt.fiscal.model;

import com.qat.samples.sysmgmt.produto.model.Incidencia;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Cofins extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;
	private Integer produtoId;
	private Double aliquotaCOFINS;
	private Double valorUnidTribCOFINS;
	private Integer TipoCalculoST;
	private Double valorCOFINSST;



	/**
	 * Default constructor.
	 */
	public Cofins()
	{
		super();
	}

	public Cofins(int i, String string) {
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

	public Double getAliquotaCOFINS() {
		return aliquotaCOFINS;
	}

	public void setAliquotaCOFINS(Double aliquotaCOFINS) {
		this.aliquotaCOFINS = aliquotaCOFINS;
	}

	public Double getValorUnidTribCOFINS() {
		return valorUnidTribCOFINS;
	}

	public void setValorUnidTribCOFINS(Double valorUnidTribCOFINS) {
		this.valorUnidTribCOFINS = valorUnidTribCOFINS;
	}

	public Integer getTipoCalculoST() {
		return TipoCalculoST;
	}

	public void setTipoCalculoST(Integer tipoCalculoST) {
		TipoCalculoST = tipoCalculoST;
	}

	public Double getValorCOFINSST() {
		return valorCOFINSST;
	}

	public void setValorCOFINSST(Double valorCOFINSST) {
		this.valorCOFINSST = valorCOFINSST;
	}

	@Override
	public String toString() {
		return "Cofins [getId()=" + getId() + ", getProdutoId()=" + getProdutoId() + ", getAliquotaCOFINS()="
				+ getAliquotaCOFINS() + ", getValorUnidTribCOFINS()=" + getValorUnidTribCOFINS()
				+ ", getTipoCalculoST()=" + getTipoCalculoST() + ", getValorCOFINSST()=" + getValorCOFINSST()
				+ ", toString()=" + super.toString() + "]";
	}

}
