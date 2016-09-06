package com.qat.samples.sysmgmt.produto.model;

import java.util.List;

import com.qat.samples.sysmgmt.cfop.model.CfopParentId;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ProdutoEmpresa extends ModelCosmeDamiao
{

	/** The SendSolv id for the account. */
	private Integer id;

	private Produto prodId;
	
	private String codigo;

	private String informAdicionaisParaNFe;

	private Integer anotainternas;

	private Long dataCadastro;
	
	private Double margemLucro;

	private List<Estoque> estoqueList;

	private Tributacao tributacao;

	private List<Preco> precoList;

	private List<Custo> custoList;

	private List<Porcao> porcaoList;

	private List<Rentabilidade> rentabilidadeList;

	private String aplicacao;

	private String fracao;

	private Double pesoBruto;

	private Double pesoLiquido;

	private String modoUso;
	
	private String InfaddNFe;

	private String AnotInt;
	
	private UniMed uniMed;
	
	private Categoria categoria;
	
	private Marca marca;

	/**
	 * Default constructor.
	 */
	public ProdutoEmpresa()
	{
		super();
	}

	public ProdutoEmpresa(Integer id)
	{
		super();
		this.id = id;
	}

	public ProdutoEmpresa(int i, String string) {
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
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	public Produto getProdId() {
		return prodId;
	}

	public void setProdId(Produto prodId) {
		this.prodId = prodId;
	}

	public String getInformAdicionaisParaNFe() {
		return informAdicionaisParaNFe;
	}

	public void setInformAdicionaisParaNFe(String informAdicionaisParaNFe) {
		this.informAdicionaisParaNFe = informAdicionaisParaNFe;
	}

	public Integer getAnotainternas() {
		return anotainternas;
	}

	public void setAnotainternas(Integer anotainternas) {
		this.anotainternas = anotainternas;
	}

	public Long getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Long dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Estoque> getEstoqueList() {
		return estoqueList;
	}

	public void setEstoqueList(List<Estoque> estoqueList) {
		this.estoqueList = estoqueList;
	}

	public Tributacao getTributacao() {
		return tributacao;
	}

	public void setTributacao(Tributacao tributacao) {
		this.tributacao = tributacao;
	}

	public List<Preco> getPrecoList() {
		return precoList;
	}

	public void setPrecoList(List<Preco> precoList) {
		this.precoList = precoList;
	}

	public List<Custo> getCustoList() {
		return custoList;
	}

	public void setCustoList(List<Custo> custoList) {
		this.custoList = custoList;
	}

	public List<Porcao> getPorcaoList() {
		return porcaoList;
	}

	public void setPorcaoList(List<Porcao> porcaoList) {
		this.porcaoList = porcaoList;
	}

	public List<Rentabilidade> getRentabilidadeList() {
		return rentabilidadeList;
	}

	public void setRentabilidadeList(List<Rentabilidade> rentabilidadeList) {
		this.rentabilidadeList = rentabilidadeList;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public UniMed getUniMed() {
		return uniMed;
	}

	public void setUniMed(UniMed uniMed) {
		this.uniMed = uniMed;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Double getMargemLucro() {
		return margemLucro;
	}

	public void setMargemLucro(Double margemLucro) {
		this.margemLucro = margemLucro;
	}

	@Override
	public String toString() {
		return "ProdutoEmpresa [getId()=" + getId() + ", getProdId()=" + getProdId() + ", getInformAdicionaisParaNFe()="
				+ getInformAdicionaisParaNFe() + ", getAnotainternas()=" + getAnotainternas() + ", getDataCadastro()="
				+ getDataCadastro() + ", getEstoqueList()=" + getEstoqueList() + ", getTributacao()=" + getTributacao()
				+ ", getPrecoList()=" + getPrecoList() + ", getCustoList()=" + getCustoList() + ", getPorcaoList()="
				+ getPorcaoList() + ", getRentabilidadeList()=" + getRentabilidadeList() + ", getCodigo()="
				+ getCodigo() + ", getAplicacao()=" + getAplicacao() + ", getFracao()=" + getFracao()
				+ ", getPesoBruto()=" + getPesoBruto() + ", getPesoLiquido()=" + getPesoLiquido() + ", getModoUso()="
				+ getModoUso() + ", getInfaddNFe()=" + getInfaddNFe() + ", getAnotInt()=" + getAnotInt()
				+ ", getUniMed()=" + getUniMed() + ", getCategoria()=" + getCategoria() + ", getMarca()=" + getMarca()
				+ ", getMargemLucro()=" + getMargemLucro() + ", toString()=" + super.toString() + "]";
	}

	
}
