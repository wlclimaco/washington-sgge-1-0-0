package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.fiscal.model.Classificacao;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Produto extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String ncm;

	/** The description. */
	private String cdBarras;

	/** The nome. */
	private Long dataCreate;

	/** The left. */
	private String produto;

	private String excTabIPI;

	private String cEST;

	/**
	 * Default constructor.
	 */
	public Produto()
	{
		super();
	}

	public Produto(Integer id)
	{
		super();
		this.id = id;
	}

	public Produto(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getCdBarras() {
		return cdBarras;
	}

	public void setCdBarras(String cdBarras) {
		this.cdBarras = cdBarras;
	}

	public Long getDataCreate() {
		return dataCreate;
	}

	public void setDataCreate(Long dataCreate) {
		this.dataCreate = dataCreate;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getExcTabIPI() {
		return excTabIPI;
	}

	public void setExcTabIPI(String excTabIPI) {
		this.excTabIPI = excTabIPI;
	}

	public String getcEST() {
		return cEST;
	}

	public void setcEST(String cEST) {
		this.cEST = cEST;
	}

	@Override
	public String toString() {
		return "Produto [getId()=" + getId() + ", getNcm()=" + getNcm() + ", getCdBarras()=" + getCdBarras()
				+ ", getDataCreate()=" + getDataCreate() + ", getProduto()=" + getProduto() + ", getExcTabIPI()="
				+ getExcTabIPI() + ", getcEST()=" + getcEST() + ", toString()=" + super.toString() + "]";
	}

	
}
