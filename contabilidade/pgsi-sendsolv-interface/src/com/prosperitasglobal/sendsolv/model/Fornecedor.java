package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Fornecedor extends Pessoa
{
	/** The SendSolv id for the account. */
	private Integer id;

	private List<Cfop> listCfops;

	private List<Produto> listProdutos;

	/**
	 * Default constructor.
	 */
	public Fornecedor()
	{
		super();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Override
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	@Override
	public void setId(Integer id)
	{
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Fornecedor [getId()=" + ", getNome()=" + getNome()
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
