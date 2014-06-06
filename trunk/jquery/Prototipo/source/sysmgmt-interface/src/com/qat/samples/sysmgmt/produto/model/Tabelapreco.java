package com.qat.samples.sysmgmt.produto.model;

import java.sql.Date;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.QATModelOL;

// TODO: Auto-generated Javadoc
/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Tabelapreco", propOrder = {"idProduto", "type", "preco", "dateIni", "dateFim"})
public class Tabelapreco extends QATModelOL
{

	/** The id. */
	private Integer idProduto;

	/** The code. */
	private PrecoTypeEnum type;

	/** The description. */
	private Double preco;

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

	/**
	 * Gets the id produto.
	 * 
	 * @return the idProduto
	 */
	public Integer getIdProduto()
	{
		return idProduto;
	}

	/**
	 * Sets the id produto.
	 * 
	 * @param idProduto the idProduto to set
	 */
	public void setIdProduto(Integer idProduto)
	{
		this.idProduto = idProduto;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public PrecoTypeEnum getType()
	{
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type the type to set
	 */
	public void setType(PrecoTypeEnum type)
	{
		this.type = type;
	}

	/**
	 * Gets the preco.
	 * 
	 * @return the preco
	 */
	public Double getPreco()
	{
		return preco;
	}

	/**
	 * Sets the preco.
	 * 
	 * @param preco the preco to set
	 */
	public void setPreco(Double preco)
	{
		this.preco = preco;
	}

	/**
	 * Gets the date ini.
	 * 
	 * @return the dateIni
	 */
	public Date getDateIni()
	{
		return dateIni;
	}

	/**
	 * Sets the date ini.
	 * 
	 * @param dateIni the dateIni to set
	 */
	public void setDateIni(Date dateIni)
	{
		this.dateIni = dateIni;
	}

	/**
	 * Gets the date fim.
	 * 
	 * @return the dateFim
	 */
	public Date getDateFim()
	{
		return dateFim;
	}

	/**
	 * Sets the date fim.
	 * 
	 * @param dateFim the dateFim to set
	 */
	public void setDateFim(Date dateFim)
	{
		this.dateFim = dateFim;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Tabelapreco [getIdProduto()=" + getIdProduto() + ", getType()=" + getType() + ", getPreco()="
				+ getPreco() + ", getDateIni()=" + getDateIni() + ", getDateFim()=" + getDateFim() + ", toString()="
				+ super.toString() + "]";
	}

}
