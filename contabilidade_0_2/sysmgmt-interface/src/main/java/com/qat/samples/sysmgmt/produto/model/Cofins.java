package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.fiscal.model.Classificacao;
import com.qat.samples.sysmgmt.util.model.DoisValores;
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


private Integer prodId;

private DoisValores sitTributaria;

private Double valorTribCOFINS;

private DoisValores tipoCalculoSubstTrib;

private Double aliquotaCOFINSST;

	/**
	 * Default constructor.
	 */
	public Cofins()
	{
		super();
	}

	public Cofins(Integer id)
	{
		super();
		this.id = id;
	}

	public Cofins(int i, String string) {
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

	public DoisValores getSitTributaria() {
		return sitTributaria;
	}

	public void setSitTributaria(DoisValores sitTributaria) {
		this.sitTributaria = sitTributaria;
	}

	public Double getValorTribCOFINS() {
		return valorTribCOFINS;
	}

	public void setValorTribCOFINS(Double valorTribCOFINS) {
		this.valorTribCOFINS = valorTribCOFINS;
	}

	public DoisValores getTipoCalculoSubstTrib() {
		return tipoCalculoSubstTrib;
	}

	public void setTipoCalculoSubstTrib(DoisValores tipoCalculoSubstTrib) {
		this.tipoCalculoSubstTrib = tipoCalculoSubstTrib;
	}

	public Double getAliquotaCOFINSST() {
		return aliquotaCOFINSST;
	}

	public void setAliquotaCOFINSST(Double aliquotaCOFINSST) {
		this.aliquotaCOFINSST = aliquotaCOFINSST;
	}

	@Override
	public String toString() {
		return "Cofins [getId()=" + getId() + ", getProdId()=" + getProdId() + ", getSitTributaria()="
				+ getSitTributaria() + ", getValorTribCOFINS()=" + getValorTribCOFINS() + ", getTipoCalculoSubstTrib()="
				+ getTipoCalculoSubstTrib() + ", getAliquotaCOFINSST()=" + getAliquotaCOFINSST() + ", toString()="
				+ super.toString() + "]";
	}




}
