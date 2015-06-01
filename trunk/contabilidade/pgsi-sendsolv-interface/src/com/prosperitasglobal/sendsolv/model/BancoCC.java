package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class BancoCC extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Banco banco;

	/** The type of an account. */
	private String numeroConta;

	private double saldo;

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario()
	{
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public Integer getEmpresa()
	{
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa the new empresa
	 */
	public void setEmpresa(Integer empresa)
	{
		this.empresa = empresa;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.model.ModelCosmeDamiao#toString()
	 */
	@Override
	public String toString()
	{
		return "Grupo [getId()=" + getId() + ", getType()=" + getType() + ", getTabela()=" + getTabela()
				+ ", getAcao()=" + getAcao() + ", getRegistro()=" + getRegistro() + ", getData()=" + getData()
				+ ", getUsuario()=" + getUsuario() + ", getEmpresa()=" + getEmpresa() + ", getParentId()="
				+ getParentId() + ", toString()=" + super.toString() + "]";
	}

}
