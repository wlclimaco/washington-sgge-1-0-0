package com.prosperitasglobal.sendsolv.model;


/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Contador extends Pessoa
{

	private String crc;

	/**
	 * Default constructor.
	 */
	public Contador()
	{
		super();
	}

	/**
	 * @return the crc
	 */
	public String getCrc()
	{
		return crc;
	}

	/**
	 * @param crc the crc to set
	 */
	public void setCrc(String crc)
	{
		this.crc = crc;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Contador [getCrc()=" + getCrc() + ", getPessoaTypeEnumValue()=" + getPessoaTypeEnumValue()
				+ ", getId()=" + getId() + ", getNome()=" + getNome() + ", getNomePai()=" + getNomePai()
				+ ", getNomeMae()=" + getNomeMae() + ", getNomeConjugue()=" + getNomeConjugue() + ", getEstadoCivil()="
				+ getEstadoCivil() + ", getDatanasc()=" + getDatanasc() + ", getFoto()=" + getFoto()
				+ ", getPessoaTypeEnum()=" + getPessoaTypeEnum() + ", getContatoList()=" + getContatoList()
				+ ", getSexo()=" + getSexo() + ", getEnderecos()=" + getEnderecos() + ", getDocumentos()="
				+ getDocumentos() + ", getEmails()=" + getEmails() + ", getTelefones()=" + getTelefones()
				+ ", getBancos()=" + getBancos() + ", getFormaPagamentoList()=" + getFormaPagamentoList()
				+ ", getCondPagList()=" + getCondPagList() + ", toString()=" + super.toString()
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
