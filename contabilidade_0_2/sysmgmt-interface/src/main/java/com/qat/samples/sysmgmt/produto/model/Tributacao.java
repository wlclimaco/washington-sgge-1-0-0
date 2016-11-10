package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.fiscal.model.Classificacao;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Tributacao extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
private Integer id;

private Integer prodId;

private Cfop cfop;

private Icms icms;

private Pis pis;

private Cofins cofins;

private Ipi ipi;

private ICMSOpInter icmsOpInter;


	/**
	 * Default constructor.
	 */
	public Tributacao()
	{
		super();
	}

	public Tributacao(Integer id)
	{
		super();
		this.id = id;
	}

	public Tributacao(int i, String string) {
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

	public Cfop getCfop() {
		return cfop;
	}

	public void setCfop(Cfop cfop) {
		this.cfop = cfop;
	}

	public Icms getIcms() {
		return icms;
	}

	public void setIcms(Icms icms) {
		this.icms = icms;
	}

	public Pis getPis() {
		return pis;
	}

	public void setPis(Pis pis) {
		this.pis = pis;
	}

	public Cofins getCofins() {
		return cofins;
	}

	public void setCofins(Cofins cofins) {
		this.cofins = cofins;
	}

	public Ipi getIpi() {
		return ipi;
	}

	public void setIpi(Ipi ipi) {
		this.ipi = ipi;
	}

	public ICMSOpInter getIcmsOpInter() {
		return icmsOpInter;
	}

	public void setIcmsOpInter(ICMSOpInter icmsOpInter) {
		this.icmsOpInter = icmsOpInter;
	}

	@Override
	public String toString() {
		return "Tributacao [getId()=" + getId() + ", getProdId()=" + getProdId() + ", getCfop()=" + getCfop()
				+ ", getIcms()=" + getIcms() + ", getPis()=" + getPis() + ", getCofins()=" + getCofins() + ", getIpi()="
				+ getIpi() + ", getIcmsOpInter()=" + getIcmsOpInter() + ", toString()=" + super.toString() + "]";
	}


}
