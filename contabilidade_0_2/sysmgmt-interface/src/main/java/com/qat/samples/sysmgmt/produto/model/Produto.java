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
	private String codigo;

	/** The type of an account. */
	private String ncm;

	/** The description. */
	private String cdBarras;

	/** The nome. */
	private Long dataCreate;

	/** The left. */
	private String produto;

	private String aplicacao;

	private String fracao;

	/** The numero. */
	private Classificacao classificacao;

	private Double pesoBruto;

	private Double pesoLiquido;

	private String modoUso;

	private String excTabIPI;

	private String cEST;

	private String InfaddNFe;

	private String AnotInt;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

	public String getFracao() {
		return fracao;
	}

	public void setFracao(String fracao) {
		this.fracao = fracao;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public Double getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(Double pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public Double getPesoLiquido() {
		return pesoLiquido;
	}

	public void setPesoLiquido(Double pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}

	public String getModoUso() {
		return modoUso;
	}

	public void setModoUso(String modoUso) {
		this.modoUso = modoUso;
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

	public String getInfaddNFe() {
		return InfaddNFe;
	}

	public void setInfaddNFe(String infaddNFe) {
		InfaddNFe = infaddNFe;
	}

	public String getAnotInt() {
		return AnotInt;
	}

	public void setAnotInt(String anotInt) {
		AnotInt = anotInt;
	}

	@Override
	public String toString() {
		return "Produto [getId()=" + getId() + ", getCodigo()=" + getCodigo() + ", getNcm()=" + getNcm()
				+ ", getCdBarras()=" + getCdBarras() + ", getDataCreate()=" + getDataCreate() + ", getProduto()="
				+ getProduto() + ", getAplicacao()=" + getAplicacao() + ", getFracao()=" + getFracao()
				+ ", getClassificacao()=" + getClassificacao() + ", getPesoBruto()=" + getPesoBruto()
				+ ", getPesoLiquido()=" + getPesoLiquido() + ", getModoUso()=" + getModoUso() + ", getExcTabIPI()="
				+ getExcTabIPI() + ", getcEST()=" + getcEST() + ", getInfaddNFe()=" + getInfaddNFe() + ", getAnotInt()="
				+ getAnotInt() + ", toString()=" + super.toString() + "]";
	}






}
