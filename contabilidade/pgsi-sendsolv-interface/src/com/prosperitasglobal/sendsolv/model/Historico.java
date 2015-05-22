package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Historico extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The bairro. */
	private String registro;

	/** The numero. */
	private Integer data;

	/** The cep. */
	private String usuario;

	/** The tipo endereco. */
	private Integer empresa;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getRegistro()
	{
		return registro;
	}

	public void setRegistro(String registro)
	{
		this.registro = registro;
	}

	public Integer getData()
	{
		return data;
	}

	public void setData(Integer data)
	{
		this.data = data;
	}

	public String getUsuario()
	{
		return usuario;
	}

	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}

	public Integer getEmpresa()
	{
		return empresa;
	}

	public void setEmpresa(Integer empresa)
	{
		this.empresa = empresa;
	}

	@Override
	public String toString()
	{
		return "Historico [getId()=" + getId() + ", getRegistro()=" + getRegistro() + ", getData()=" + getData()
				+ ", getUsuario()=" + getUsuario() + ", getEmpresa()=" + getEmpresa() + ", getParentId()="
				+ getParentId() + ", toString()=" + super.toString() + "]";
	}

}
