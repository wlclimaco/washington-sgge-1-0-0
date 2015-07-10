package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Empresa extends Entidade
{

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Empresa [getEntidadeEnumValue()=" + getEntidadeEnumValue() + ", getId()=" + getId() + ", getNome()="
				+ getNome() + ", getEnderecos()=" + getEnderecos() + ", getDocumentos()=" + getDocumentos()
				+ ", getEmails()=" + getEmails() + ", getTelefones()=" + getTelefones() + ", getSocios()="
				+ getSocios() + ", getCnaes()=" + getCnaes() + ", getRegime()=" + getRegime() + ", getEntidadeEnum()="
				+ getEntidadeEnum() + ", getConfiguracao()=" + getConfiguracao() + ", getFilialList()="
				+ getFilialList() + ", getDepositoList()=" + getDepositoList() + ", getEntidadeId()=" + getEntidadeId()
				+ ", toString()=" + super.toString() + ", getTabelaEnumValue()=" + getTabelaEnumValue()
				+ ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue()
				+ ", getParentId()=" + getParentId() + ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType()
				+ ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()="
				+ getEmprId() + ", getSite()=" + getSite() + ", getProcessId()=" + getProcessId() + ", getUserId()="
				+ getUserId() + ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
