package com.qat.samples.sysmgmt.nf.model;

import java.util.List;

import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.fiscal.model.Classificacao;
import com.qat.samples.sysmgmt.fiscal.model.Cst;
import com.qat.samples.sysmgmt.fiscal.model.Tributacao;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class NotaFiscalItens extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer IdNota;

	/** The tipo endereco. */
	private Produto produto;

	private Double qnt;

	private Double vrUnitario;

	private Double vrDesconto;

	private Cfop cfop;

	private Cst crt;

	private Classificacao classificacao;

	private List<Tributacao> tributosList;

	/**
	 * Default constructor.
	 */
	public NotaFiscalItens()
	{
		super();
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the idNota
	 */
	public Integer getIdNota()
	{
		return IdNota;
	}

	/**
	 * @param idNota the idNota to set
	 */
	public void setIdNota(Integer idNota)
	{
		IdNota = idNota;
	}

	/**
	 * @return the produto
	 */
	public Produto getProduto()
	{
		return produto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}

	/**
	 * @return the qnt
	 */
	public Double getQnt()
	{
		return qnt;
	}

	/**
	 * @param qnt the qnt to set
	 */
	public void setQnt(Double qnt)
	{
		this.qnt = qnt;
	}

	/**
	 * @return the vrUnitario
	 */
	public Double getVrUnitario()
	{
		return vrUnitario;
	}

	/**
	 * @param vrUnitario the vrUnitario to set
	 */
	public void setVrUnitario(Double vrUnitario)
	{
		this.vrUnitario = vrUnitario;
	}

	/**
	 * @return the vrDesconto
	 */
	public Double getVrDesconto()
	{
		return vrDesconto;
	}

	/**
	 * @param vrDesconto the vrDesconto to set
	 */
	public void setVrDesconto(Double vrDesconto)
	{
		this.vrDesconto = vrDesconto;
	}

	/**
	 * @return the cfop
	 */
	public Cfop getCfop()
	{
		return cfop;
	}

	/**
	 * @param cfop the cfop to set
	 */
	public void setCfop(Cfop cfop)
	{
		this.cfop = cfop;
	}

	/**
	 * @return the crt
	 */
	public Cst getCrt()
	{
		return crt;
	}

	/**
	 * @param crt the crt to set
	 */
	public void setCrt(Cst crt)
	{
		this.crt = crt;
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
	 * @return the tributosList
	 */
	public List<Tributacao> getTributosList()
	{
		return tributosList;
	}

	/**
	 * @param tributosList the tributosList to set
	 */
	public void setTributosList(List<Tributacao> tributosList)
	{
		this.tributosList = tributosList;
	}

	@Override
	public String toString()
	{
		return "NotaFiscalItens [getId()=" + getId() + ", getIdNota()=" + getIdNota() + ", getProduto()="
				+ getProduto() + ", getQnt()=" + getQnt() + ", getVrUnitario()=" + getVrUnitario()
				+ ", getVrDesconto()=" + getVrDesconto() + ", getCfop()=" + getCfop() + ", getCrt()=" + getCrt()
				+ ", getClassificacao()=" + getClassificacao() + ", getTributosList()=" + getTributosList()
				+ ", toString()=" + super.toString() + "]";
	}

}
