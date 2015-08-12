package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Socio extends Pessoa
{

	/** The description. */
	private String cota;

	/** The description. */
	private String porcentagem;

	/**
	 * Default constructor.
	 */
	public Socio()
	{
		super();
	}

	/**
	 * Gets the cota.
	 *
	 * @return the cota
	 */
	public String getCota()
	{
		return cota;
	}

	/**
	 * Sets the cota.
	 *
	 * @param cota the cota to set
	 */
	public void setCota(String cota)
	{
		this.cota = cota;
	}

	/**
	 * Gets the porcentagem.
	 *
	 * @return the porcentagem
	 */
	public String getPorcentagem()
	{
		return porcentagem;
	}

	/**
	 * Sets the porcentagem.
	 *
	 * @param porcentagem the porcentagem to set
	 */
	public void setPorcentagem(String porcentagem)
	{
		this.porcentagem = porcentagem;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Socio [getCota()=" + getCota() + ", getPorcentagem()=" + getPorcentagem()
				+ ", getPessoaTypeEnumValue()=" + getPessoaTypeEnumValue() + ", getId()=" + getId() + ", getNome()="
				+ getNome() + ", getNomePai()=" + getNomePai() + ", getNomeMae()=" + getNomeMae()
				+ ", getNomeConjugue()=" + getNomeConjugue() + ", getEstadoCivil()=" + getEstadoCivil()
				+ ", getDatanasc()=" + getDatanasc() + ", getFoto()=" + getFoto() + ", getPessoaTypeEnum()="
				+ getPessoaTypeEnum() + ", getContatoList()=" + getContatoList() + ", getSexo()=" + getSexo()
				+ ", getEnderecos()=" + getEnderecos() + ", getDocumentos()=" + getDocumentos() + ", getEmails()="
				+ getEmails() + ", getTelefones()=" + getTelefones() + ", getBancos()=" + getBancos()
				+ ", getFormaPagamentoList()=" + getFormaPagamentoList() + ", getCondPagList()=" + getCondPagList()
				+ ", toString()=" + super.toString() + ", getTabelaEnumValue()=" + getTabelaEnumValue()
				+ ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue()
				+ ", getParentId()=" + getParentId() + ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType()
				+ ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()="
				+ getEmprId() + ", getSite()=" + getSite() + ", getProcessId()=" + getProcessId() + ", getUserId()="
				+ getUserId() + ", getNotes()=" + getNotes() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()=" + getCreateDateUTC()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()=" + getModifyDateUTC()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
