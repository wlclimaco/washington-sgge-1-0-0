package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.fiscal.model.Classificacao;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Ipi extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
private Integer id;

private Integer prodId;

private DoisValores sitTributaria;

private String classeCigarrosBebidas;

private String cNPJProdutor;

private String codControleIPI;

private String qtdSeloIPI;

private DoisValores codEnquadramento;

private DoisValores tipoCalculo;

private Double aliquotaIPI;


	/**
	 * Default constructor.
	 */
	public Ipi()
	{
		super();
	}

	public Ipi(Integer id)
	{
		super();
		this.id = id;
	}

	public Ipi(int i, String string) {
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

	public String getClasseCigarrosBebidas() {
		return classeCigarrosBebidas;
	}

	public void setClasseCigarrosBebidas(String classeCigarrosBebidas) {
		this.classeCigarrosBebidas = classeCigarrosBebidas;
	}

	public String getcNPJProdutor() {
		return cNPJProdutor;
	}

	public void setcNPJProdutor(String cNPJProdutor) {
		this.cNPJProdutor = cNPJProdutor;
	}

	public String getCodControleIPI() {
		return codControleIPI;
	}

	public void setCodControleIPI(String codControleIPI) {
		this.codControleIPI = codControleIPI;
	}

	public String getQtdSeloIPI() {
		return qtdSeloIPI;
	}

	public void setQtdSeloIPI(String qtdSeloIPI) {
		this.qtdSeloIPI = qtdSeloIPI;
	}

	public DoisValores getCodEnquadramento() {
		return codEnquadramento;
	}

	public void setCodEnquadramento(DoisValores codEnquadramento) {
		this.codEnquadramento = codEnquadramento;
	}

	public DoisValores getTipoCalculo() {
		return tipoCalculo;
	}

	public void setTipoCalculo(DoisValores tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}

	public Double getAliquotaIPI() {
		return aliquotaIPI;
	}

	public void setAliquotaIPI(Double aliquotaIPI) {
		this.aliquotaIPI = aliquotaIPI;
	}

	@Override
	public String toString() {
		return "Ipi [getId()=" + getId() + ", getProdId()=" + getProdId() + ", getSitTributaria()=" + getSitTributaria()
				+ ", getClasseCigarrosBebidas()=" + getClasseCigarrosBebidas() + ", getcNPJProdutor()="
				+ getcNPJProdutor() + ", getCodControleIPI()=" + getCodControleIPI() + ", getQtdSeloIPI()="
				+ getQtdSeloIPI() + ", getCodEnquadramento()=" + getCodEnquadramento() + ", getTipoCalculo()="
				+ getTipoCalculo() + ", getAliquotaIPI()=" + getAliquotaIPI() + ", toString()=" + super.toString()
				+ "]";
	}


}
