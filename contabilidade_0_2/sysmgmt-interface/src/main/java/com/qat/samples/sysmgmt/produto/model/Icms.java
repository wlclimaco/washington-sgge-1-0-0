package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Icms extends ModelCosmeDamiao
{


private Integer id;
private Integer prodId;
private DoisValores sitTributaria;
private DoisValores origem;
private DoisValores modalidadeBC;
private String redBase;
private String aliqICMS;
private DoisValores motDesoneracao;

	/**
	 * Default constructor.
	 */
	public Icms()
	{
		super();
	}

	public Icms(Integer id)
	{
		super();
		this.id = id;
	}

	public Icms(int i, String string) {
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

	public DoisValores getOrigem() {
		return origem;
	}

	public void setOrigem(DoisValores origem) {
		this.origem = origem;
	}

	public DoisValores getModalidadeBC() {
		return modalidadeBC;
	}

	public void setModalidadeBC(DoisValores modalidadeBC) {
		this.modalidadeBC = modalidadeBC;
	}

	public String getRedBase() {
		return redBase;
	}

	public void setRedBase(String redBase) {
		this.redBase = redBase;
	}

	public String getAliqICMS() {
		return aliqICMS;
	}

	public void setAliqICMS(String aliqICMS) {
		this.aliqICMS = aliqICMS;
	}

	public DoisValores getMotDesoneracao() {
		return motDesoneracao;
	}

	public void setMotDesoneracao(DoisValores motDesoneracao) {
		this.motDesoneracao = motDesoneracao;
	}

	@Override
	public String toString() {
		return "Icms [getId()=" + getId() + ", getProdId()=" + getProdId() + ", getSitTributaria()="
				+ getSitTributaria() + ", getOrigem()=" + getOrigem() + ", getModalidadeBC()=" + getModalidadeBC()
				+ ", getRedBase()=" + getRedBase() + ", getAliqICMS()=" + getAliqICMS() + ", getMotDesoneracao()="
				+ getMotDesoneracao() + ", toString()=" + super.toString() + "]";
	}


}
