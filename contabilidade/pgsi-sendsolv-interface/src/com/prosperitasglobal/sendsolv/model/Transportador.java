package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Transportador extends Pessoa
{

	/**
	 * Default constructor.
	 */
	public Transportador()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Transportador [getId()=" + getId() + ", getCdEmpr()=" + getCdEmpr() + ", getNome()=" + getNome()
				+ ", getNomePai()=" + getNomePai() + ", getNomeMae()=" + getNomeMae() + ", getNomeConjugue()="
				+ getNomeConjugue() + ", getEstadoCivil()=" + getEstadoCivil() + ", getDatanasc()=" + getDatanasc()
				+ ", getFoto()=" + getFoto() + ", getSexo()=" + getSexo() + ", getEnderecos()=" + getEnderecos()
				+ ", getDocumentos()=" + getDocumentos() + ", getEmails()=" + getEmails() + ", getTelefones()="
				+ getTelefones() + ", getNotes()=" + getNotes() + ", getBancos()=" + getBancos() + ", toString()="
				+ super.toString() + ", getParentId()=" + getParentId() + ", getType()=" + getType()
				+ ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()="
				+ getStatusList() + ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
