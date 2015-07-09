package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Empresa extends Entidade
{

	@Override
	public String toString()
	{
		return "Empresa [getId()=" + getId() + ", getNome()=" + getNome() + ", getEnderecos()=" + getEnderecos()
				+ ", getDocumentos()=" + getDocumentos() + ", getEmails()=" + getEmails() + ", getTelefones()="
				+ getTelefones() + ", getSocios()=" + getSocios() + ", getCnaes()=" + getCnaes() + ", getRegime()="
				+ getRegime() + ", toString()=" + super.toString() + "]";
	}

}
