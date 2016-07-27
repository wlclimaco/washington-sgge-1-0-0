package com.qat.samples.sysmgmt.fiscal.model;

import com.qat.samples.sysmgmt.produto.model.Incidencia;
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
	private Integer produtoId;
	private Integer sitTributaria;
	private Integer classCigarro;
	private String cnpjProd;
	private Integer codSeloIPI;
	private Integer qtdSeloIPI;
	private Integer codEnquadramento;
	private Integer tipoCalculo;
	private Double aliqIPI;



	/**
	 * Default constructor.
	 */
	public Ipi()
	{
		super();
	}

	public Ipi(int i, String string) {
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

	public Integer getSitTributaria() {
		return sitTributaria;
	}

	public void setSitTributaria(Integer sitTributaria) {
		this.sitTributaria = sitTributaria;
	}

	public Integer getClassCigarro() {
		return classCigarro;
	}

	public void setClassCigarro(Integer classCigarro) {
		this.classCigarro = classCigarro;
	}

	public String getCnpjProd() {
		return cnpjProd;
	}

	public void setCnpjProd(String cnpjProd) {
		this.cnpjProd = cnpjProd;
	}

	public Integer getCodSeloIPI() {
		return codSeloIPI;
	}

	public void setCodSeloIPI(Integer codSeloIPI) {
		this.codSeloIPI = codSeloIPI;
	}

	public Integer getQtdSeloIPI() {
		return qtdSeloIPI;
	}

	public void setQtdSeloIPI(Integer qtdSeloIPI) {
		this.qtdSeloIPI = qtdSeloIPI;
	}

	public Integer getCodEnquadramento() {
		return codEnquadramento;
	}

	public void setCodEnquadramento(Integer codEnquadramento) {
		this.codEnquadramento = codEnquadramento;
	}

	public Integer getTipoCalculo() {
		return tipoCalculo;
	}

	public void setTipoCalculo(Integer tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}

	public Double getAliqIPI() {
		return aliqIPI;
	}

	public void setAliqIPI(Double aliqIPI) {
		this.aliqIPI = aliqIPI;
	}
}
