package com.qat.samples.sysmgmt.produto.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.supermercado.model.Supermercado;
import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Tabelapreco", propOrder = {"precoid", "idProduto", "supermercadoid", "preco", "dataCreate",
		"precopromo", "promocao", "dateIni", "dateFim"})
public class Tabelapreco extends Util
{

	private Integer precoid;
	/** The id. */
	private Integer idProduto;

	private Supermercado supermercadoid;

	/** The description. */
	private Double preco;

	private Date dataCreate;

	private Double precopromo;

	private Integer promocao;

	/** The date ini. */
	private Date dateIni;

	/** The date fim. */
	private Date dateFim;

	/**
	 * Instantiates a new bundle.
	 */
	public Tabelapreco()
	{

	}

	public Tabelapreco(Integer precoid, Integer idProduto, Supermercado supermercadoid, PrecoTypeEnum type, Double preco)
	{
		super();
		this.precoid = precoid;
		this.idProduto = idProduto;
		this.supermercadoid = supermercadoid;
		this.preco = preco;
	}

	public Integer getPrecoid()
	{
		return precoid;
	}

	public void setPrecoid(Integer precoid)
	{
		this.precoid = precoid;
	}

	public Integer getIdProduto()
	{
		return idProduto;
	}

	public void setIdProduto(Integer idProduto)
	{
		this.idProduto = idProduto;
	}

	public Supermercado getSupermercadoid()
	{
		return supermercadoid;
	}

	public void setSupermercadoid(Supermercado supermercadoid)
	{
		this.supermercadoid = supermercadoid;
	}

	public Double getPreco()
	{
		return preco;
	}

	public void setPreco(Double preco)
	{
		this.preco = preco;
	}

	public Double getPrecopromo()
	{
		return precopromo;
	}

	public void setPrecopromo(Double precopromo)
	{
		this.precopromo = precopromo;
	}

	public Integer getPromocao()
	{
		return promocao;
	}

	public void setPromocao(Integer promocao)
	{
		this.promocao = promocao;
	}

	public Date getDateIni()
	{
		return dateIni;
	}

	public void setDateIni(Date dateIni)
	{
		this.dateIni = dateIni;
	}

	public Date getDateFim()
	{
		return dateFim;
	}

	public void setDateFim(Date dateFim)
	{
		this.dateFim = dateFim;
	}

	public Date getDataCreate()
	{
		return dataCreate;
	}

	public void setDataCreate(Date dataCreate)
	{
		this.dataCreate = dataCreate;
	}

	@Override
	public String toString()
	{
		return "Tabelapreco [getPrecoid()=" + getPrecoid() + ", getIdProduto()=" + getIdProduto()
				+ ", getSupermercadoid()=" + getSupermercadoid() + ", getPreco()=" + getPreco() + ", getPrecopromo()="
				+ getPrecopromo() + ", getPromocao()=" + getPromocao() + ", getDateIni()=" + getDateIni()
				+ ", getDateFim()=" + getDateFim() + ", getDataCreate()=" + getDataCreate() + ", toString()="
				+ super.toString() + "]";
	}

}
