package com.qat.samples.sysmgmt.produto.model;

import java.util.List;

import com.qat.samples.sysmgmt.cfop.model.CfopPessoa;
import com.qat.samples.sysmgmt.fiscal.model.Classificacao;
import com.qat.samples.sysmgmt.fiscal.model.Tributacao;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
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

	/** The description. */
	private String cdBarras;

	/** The nome. */
	private Long dataCreate;

	/** The left. */
	private String produto;

	private String aplicacao;

	private String localizacao;

	private Long dataValidade;

	private String comissao;

	private String fracao;

	/** The numero. */
	private Classificacao classificacao;

	/** The top. */
	private UniMedProd uniMed;

	/** The width. */
	private GrupoProd grupo;

	/** The height. */
	private SubGrupoProd subGrupo;

	private Double porcao;

	private MarcaProd marca;

	private Double pesoBruto;

	private Double pesoLiquido;

	private String modoUso;

	private Tributacao tributacao;

	private List<Estoque> estoqueList;

	private List<TabPreco> precoList;

	private List<Custo> custoList;

	private List<Porcao> porcaoList;

	private List<Rentabilidade> rentabilidadeList;

	private List<CfopPessoa> cfopList;

	private List<Fornecedor> fornecedorList;

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

	/**
	 * @return the codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * @return the cdBarras
	 */
	public String getCdBarras()
	{
		return cdBarras;
	}

	/**
	 * @param cdBarras the cdBarras to set
	 */
	public void setCdBarras(String cdBarras)
	{
		this.cdBarras = cdBarras;
	}

	/**
	 * @return the classificacao
	 */
	public Classificacao getClassificacao()
	{
		return classificacao;
	}

	/**
	 * @param classificacao the classificacao to set
	 */
	public void setClassificacao(Classificacao classificacao)
	{
		this.classificacao = classificacao;
	}

	/**
	 * @return the dataCreate
	 */
	public Long getDataCreate()
	{
		return dataCreate;
	}

	/**
	 * @param dataCreate the dataCreate to set
	 */
	public void setDataCreate(Long dataCreate)
	{
		this.dataCreate = dataCreate;
	}

	/**
	 * @return the produto
	 */
	public String getProduto()
	{
		return produto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto(String produto)
	{
		this.produto = produto;
	}

	/**
	 * @return the aplicacao
	 */
	public String getAplicacao()
	{
		return aplicacao;
	}

	/**
	 * @param aplicacao the aplicacao to set
	 */
	public void setAplicacao(String aplicacao)
	{
		this.aplicacao = aplicacao;
	}

	/**
	 * @return the localizacao
	 */
	public String getLocalizacao()
	{
		return localizacao;
	}

	/**
	 * @param localizacao the localizacao to set
	 */
	public void setLocalizacao(String localizacao)
	{
		this.localizacao = localizacao;
	}

	/**
	 * @return the dataValidade
	 */
	public Long getDataValidade()
	{
		return dataValidade;
	}

	/**
	 * @param dataValidade the dataValidade to set
	 */
	public void setDataValidade(Long dataValidade)
	{
		this.dataValidade = dataValidade;
	}

	/**
	 * @return the comissao
	 */
	public String getComissao()
	{
		return comissao;
	}

	/**
	 * @param comissao the comissao to set
	 */
	public void setComissao(String comissao)
	{
		this.comissao = comissao;
	}

	/**
	 * @return the fracao
	 */
	public String getFracao()
	{
		return fracao;
	}

	/**
	 * @param fracao the fracao to set
	 */
	public void setFracao(String fracao)
	{
		this.fracao = fracao;
	}

	/**
	 * @return the uniMed
	 */
	public UniMedProd getUniMed()
	{
		return uniMed;
	}

	/**
	 * @param uniMed the uniMed to set
	 */
	public void setUniMed(UniMedProd uniMed)
	{
		this.uniMed = uniMed;
	}

	/**
	 * @return the grupo
	 */
	public GrupoProd getGrupo()
	{
		return grupo;
	}

	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(GrupoProd grupo)
	{
		this.grupo = grupo;
	}

	/**
	 * @return the subGrupo
	 */
	public SubGrupoProd getSubGrupo()
	{
		return subGrupo;
	}

	/**
	 * @param subGrupo the subGrupo to set
	 */
	public void setSubGrupo(SubGrupoProd subGrupo)
	{
		this.subGrupo = subGrupo;
	}

	/**
	 * @return the marca
	 */
	public MarcaProd getMarca()
	{
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(MarcaProd marca)
	{
		this.marca = marca;
	}

	/**
	 * @return the modoUso
	 */
	public String getModoUso()
	{
		return modoUso;
	}

	/**
	 * @param modoUso the modoUso to set
	 */
	public void setModoUso(String modoUso)
	{
		this.modoUso = modoUso;
	}

	/**
	 * @return the pesoBruto
	 */
	public Double getPesoBruto()
	{
		return pesoBruto;
	}

	/**
	 * @param pesoBruto the pesoBruto to set
	 */
	public void setPesoBruto(Double pesoBruto)
	{
		this.pesoBruto = pesoBruto;
	}

	/**
	 * @return the pesoLiquido
	 */
	public Double getPesoLiquido()
	{
		return pesoLiquido;
	}

	/**
	 * @param pesoLiquido the pesoLiquido to set
	 */
	public void setPesoLiquido(Double pesoLiquido)
	{
		this.pesoLiquido = pesoLiquido;
	}

	/**
	 * @return the tributacao
	 */
	public Tributacao getTributacao()
	{
		return tributacao;
	}

	/**
	 * @param tributacao the tributacao to set
	 */
	public void setTributacao(Tributacao tributacao)
	{
		this.tributacao = tributacao;
	}

	/**
	 * @return the estoqueList
	 */
	public List<Estoque> getEstoqueList()
	{
		return estoqueList;
	}

	/**
	 * @param estoqueList the estoqueList to set
	 */
	public void setEstoqueList(List<Estoque> estoqueList)
	{
		this.estoqueList = estoqueList;
	}

	/**
	 * @return the precoList
	 */
	public List<TabPreco> getPrecoList()
	{
		return precoList;
	}

	/**
	 * @param precoList the precoList to set
	 */
	public void setPrecoList(List<TabPreco> precoList)
	{
		this.precoList = precoList;
	}

	/**
	 * @return the custoList
	 */
	public List<Custo> getCustoList()
	{
		return custoList;
	}

	/**
	 * @param custoList the custoList to set
	 */
	public void setCustoList(List<Custo> custoList)
	{
		this.custoList = custoList;
	}

	/**
	 * @return the porcaoList
	 */
	public List<Porcao> getPorcaoList()
	{
		return porcaoList;
	}

	/**
	 * @param porcaoList the porcaoList to set
	 */
	public void setPorcaoList(List<Porcao> porcaoList)
	{
		this.porcaoList = porcaoList;
	}

	/**
	 * @return the rentabilidadeList
	 */
	public List<Rentabilidade> getRentabilidadeList()
	{
		return rentabilidadeList;
	}

	/**
	 * @param rentabilidadeList the rentabilidadeList to set
	 */
	public void setRentabilidadeList(List<Rentabilidade> rentabilidadeList)
	{
		this.rentabilidadeList = rentabilidadeList;
	}

	/**
	 * @return the cfopList
	 */
	public List<CfopPessoa> getCfopList()
	{
		return cfopList;
	}

	/**
	 * @param cfopList the cfopList to set
	 */
	public void setCfopList(List<CfopPessoa> cfopList)
	{
		this.cfopList = cfopList;
	}

	/**
	 * @return the fornecedorList
	 */
	public List<Fornecedor> getFornecedorList()
	{
		return fornecedorList;
	}

	/**
	 * @param fornecedorList the fornecedorList to set
	 */
	public void setFornecedorList(List<Fornecedor> fornecedorList)
	{
		this.fornecedorList = fornecedorList;
	}

	/**
	 * @return the porcao
	 */
	public Double getPorcao()
	{
		return porcao;
	}

	/**
	 * @param porcao the porcao to set
	 */
	public void setPorcao(Double porcao)
	{
		this.porcao = porcao;
	}

	@Override
	public String toString()
	{
		return "Produto [getId()=" + getId() + ", getCodigo()=" + getCodigo() + ", getCdBarras()=" + getCdBarras()
				+ ", getClassificacao()=" + getClassificacao() + ", getDataCreate()=" + getDataCreate()
				+ ", getProduto()=" + getProduto() + ", getAplicacao()=" + getAplicacao() + ", getLocalizacao()="
				+ getLocalizacao() + ", getDataValidade()=" + getDataValidade() + ", getComissao()=" + getComissao()
				+ ", getFracao()=" + getFracao() + ", getUniMed()=" + getUniMed() + ", getGrupo()=" + getGrupo()
				+ ", getSubGrupo()=" + getSubGrupo() + ", getMarca()=" + getMarca() + ", getModoUso()=" + getModoUso()
				+ ", getPesoBruto()=" + getPesoBruto() + ", getPesoLiquido()=" + getPesoLiquido()
				+ ", getTributacao()=" + getTributacao() + ", getEstoqueList()=" + getEstoqueList()
				+ ", getPrecoList()=" + getPrecoList() + ", getCustoList()=" + getCustoList() + ", getPorcaoList()="
				+ getPorcaoList() + ", getRentabilidadeList()=" + getRentabilidadeList() + ", getCfopList()="
				+ getCfopList() + ", getFornecedorList()=" + getFornecedorList() + ", getPorcao()=" + getPorcao()
				+ ", toString()=" + super.toString() + "]";
	}

}
