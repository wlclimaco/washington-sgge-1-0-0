package com.qat.samples.sysmgmt.produto.model;

import java.sql.Date;

import javax.xml.bind.annotation.XmlType;
import javax.xml.crypto.Data;

import com.qat.samples.sysmgmt.supermercado.model.Supermercado;
import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Tabelapreco", propOrder = {"idProduto", "type", "preco", "dateIni", "dateFim"})
public class Tabelapreco extends Util
{

	private Integer precoid;
	/** The id. */
	private Integer idProduto;

	private Supermercado supermercadoid;

	private Data data;

	/** The code. */
	private PrecoTypeEnum type;

	/** The description. */
	private Double preco;

	private Double precopromo;

	private Boolean promocao;

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

	public Tabelapreco(Integer precoid, Integer idProduto, Supermercado supermercadoid, Data data, PrecoTypeEnum type,
			Double preco, Double precopromo, Boolean promocao, Date dateIni, Date dateFim)
	{
		super();
		this.precoid = precoid;
		this.idProduto = idProduto;
		this.supermercadoid = supermercadoid;
		this.data = data;
		this.type = type;
		this.preco = preco;
		this.precopromo = precopromo;
		this.promocao = promocao;
		this.dateIni = dateIni;
		this.dateFim = dateFim;
	}

	public Tabelapreco(Integer precoid, Integer idProduto, Supermercado supermercadoid, PrecoTypeEnum type, Double preco)
	{
		super();
		this.precoid = precoid;
		this.idProduto = idProduto;
		this.supermercadoid = supermercadoid;
		this.type = type;
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

	public Data getData()
	{
		return data;
	}

	public void setData(Data data)
	{
		this.data = data;
	}

	public PrecoTypeEnum getType()
	{
		return type;
	}

	public void setType(PrecoTypeEnum type)
	{
		this.type = type;
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

	public Boolean getPromocao()
	{
		return promocao;
	}

	public void setPromocao(Boolean promocao)
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

	@Override
	public String toString()
	{
		return "Tabelapreco [getPrecoid()=" + getPrecoid() + ", getIdProduto()=" + getIdProduto()
				+ ", getSupermercadoid()=" + getSupermercadoid() + ", getData()=" + getData() + ", getType()="
				+ getType() + ", getPreco()=" + getPreco() + ", getPrecopromo()=" + getPrecopromo()
				+ ", getPromocao()=" + getPromocao() + ", getDateIni()=" + getDateIni() + ", getDateFim()="
				+ getDateFim() + ", toString()=" + super.toString() + "]";
	}

}
