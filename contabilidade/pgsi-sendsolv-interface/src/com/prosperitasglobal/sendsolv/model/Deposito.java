package com.prosperitasglobal.sendsolv.model;


/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Deposito extends Entidade
{

	/**
	 * Default constructor.
	 */
	public Deposito()
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
		return "Deposito [getEntidadeEnumValue()=" + getEntidadeEnumValue() + ", getId()=" + getId() + ", getNome()="
				+ getNome() + ", getEnderecos()=" + getEnderecos() + ", getDocumentos()=" + getDocumentos()
				+ ", getEmails()=" + getEmails() + ", getTelefones()=" + getTelefones() + ", getCnaes()=" + getCnaes()
				+ ", getRegime()=" + getRegime() + ", getEntidadeEnum()=" + getEntidadeEnum() + ", getConfiguracao()="
				+ getConfiguracao() + ", getEntidadeId()=" + getEntidadeId() + ", toString()=" + super.toString()
				+ ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()=" + getTypeValue()
				+ ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId() + ", getType()="
				+ getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum()
				+ ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId() + ", getSite()=" + getSite()
				+ ", getProcessId()=" + getProcessId() + ", getUserId()=" + getUserId() + ", getNotes()=" + getNotes()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
