package com.qat.samples.sysmgmt.cidade.model;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Cidade", propOrder = {"cidadeId", "estado", "cidade"})
public class Cidade extends Util
{

	/** The id. */

	/** The code. */
	private String estado;

	/** The description. */
	private String cidade;

	private Integer cidadeId;

	public Integer getCidadeId()
	{
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId)
	{
		this.cidadeId = cidadeId;
	}

	public String getEstado()
	{
		return estado;
	}

	public void setEstado(String estado)
	{
		this.estado = estado;
	}

	public String getCidade()
	{
		return cidade;
	}

	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}

	public Cidade(Integer cidadeId)
	{
		super();
		this.cidadeId = cidadeId;
	}

	public Cidade(String cidade)
	{
		super();
		this.cidade = cidade;
	}

	public Cidade(String estado, String cidade, Integer cidadeId)
	{
		super();
		this.estado = estado;
		this.cidade = cidade;
		this.cidadeId = cidadeId;
	}

	public Cidade()
	{
		super();
	}

	@Override
	public String toString()
	{
		return "Cidade [getCidadeId()=" + getCidadeId() + ", getEstado()=" + getEstado() + ", getCidade()="
				+ getCidade() + ", toString()=" + super.toString() + "]";
	}

}
