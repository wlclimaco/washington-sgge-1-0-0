package com.qat.samples.sysmgmt.produto.model;

import java.util.List;

import com.qat.samples.sysmgmt.cfop.model.CfopParentId;
import com.qat.samples.sysmgmt.fiscal.model.Classificacao;
import com.qat.samples.sysmgmt.fiscal.model.Tributacao;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ProdutoParent extends ModelCosmeDamiao
{

	/** The SendSolv id for the account. */
	private Integer id;


	private Long dataValidade;

	private String comissao;

	private String fracao;

	private String localizacao;

	/** The numero. */
	private Classificacao classificacao;

	private Tributacao tributacao;

	private List<Estoque> estoqueList;

	private List<Preco> precoList;

	private List<Custo> custoList;

	private List<Porcao> porcaoList;

	private List<Rentabilidade> rentabilidadeList;

	private List<CfopParentId> cfopList;

	/**
	 * Default constructor.
	 */
	public ProdutoParent()
	{
		super();
	}

	public ProdutoParent(Integer id)
	{
		super();
		this.id = id;
	}

	public ProdutoParent(int i, String string) {
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

	public Long getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Long dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getComissao() {
		return comissao;
	}

	public void setComissao(String comissao) {
		this.comissao = comissao;
	}

	public String getFracao() {
		return fracao;
	}

	public void setFracao(String fracao) {
		this.fracao = fracao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public Tributacao getTributacao() {
		return tributacao;
	}

	public void setTributacao(Tributacao tributacao) {
		this.tributacao = tributacao;
	}

	public List<Estoque> getEstoqueList() {
		return estoqueList;
	}

	public void setEstoqueList(List<Estoque> estoqueList) {
		this.estoqueList = estoqueList;
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

	public List<CfopParentId> getCfopList() {
		return cfopList;
	}

	public void setCfopList(List<CfopParentId> cfopList) {
		this.cfopList = cfopList;
	}

	@Override
	public String toString() {
		return "ProdutoParent [getId()=" + getId() + ", getDataValidade()=" + getDataValidade() + ", getComissao()="
				+ getComissao() + ", getFracao()=" + getFracao() + ", getLocalizacao()=" + getLocalizacao()
				+ ", getClassificacao()=" + getClassificacao() + ", getTributacao()=" + getTributacao()
				+ ", getEstoqueList()=" + getEstoqueList() + ", getPrecoList()=" + getPrecoList() + ", getCustoList()="
				+ getCustoList() + ", getPorcaoList()=" + getPorcaoList() + ", getRentabilidadeList()="
				+ getRentabilidadeList() + ", getCfopList()=" + getCfopList() + ", toString()=" + super.toString()
				+ "]";
	}

}
