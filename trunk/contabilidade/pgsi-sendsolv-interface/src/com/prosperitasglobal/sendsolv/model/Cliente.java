package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Cliente extends Pessoa
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The horarios. */
	private List<Profissao> profissao;

	private List<Convenio> convenioList;

	/**
	 * Default constructor.
	 */
	public Cliente()
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
	 * @return the profissao
	 */
	public List<Profissao> getProfissao()
	{
		return profissao;
	}

	/**
	 * @param profissao the profissao to set
	 */
	public void setProfissao(List<Profissao> profissao)
	{
		this.profissao = profissao;
	}

	/**
	 * @return the convenioList
	 */
	public List<Convenio> getConvenioList()
	{
		return convenioList;
	}

	/**
	 * @param convenioList the convenioList to set
	 */
	public void setConvenioList(List<Convenio> convenioList)
	{
		this.convenioList = convenioList;
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
		return "Cliente [getId()=" + getId() + ", getProfissao()=" + getProfissao() + ", getConvenioList()="
				+ getConvenioList() + ", getPessoaTypeEnumValue()=" + getPessoaTypeEnumValue() + ", getNome()="
				+ getNome() + ", getNomePai()=" + getNomePai() + ", getNomeMae()=" + getNomeMae()
				+ ", getNomeConjugue()=" + getNomeConjugue() + ", getEstadoCivil()=" + getEstadoCivil()
				+ ", getDatanasc()=" + getDatanasc() + ", getFoto()=" + getFoto() + ", getPessoaTypeEnum()="
				+ getPessoaTypeEnum() + ", getFormaPagamentoList()=" + getFormaPagamentoList() + ", getCondPagList()="
				+ getCondPagList() + ", getContatoList()=" + getContatoList() + ", getSexo()=" + getSexo()
				+ ", getEnderecos()=" + getEnderecos() + ", getDocumentos()=" + getDocumentos() + ", getEmails()="
				+ getEmails() + ", getTelefones()=" + getTelefones() + ", getNotes()=" + getNotes() + ", getBancos()="
				+ getBancos() + ", toString()=" + super.toString() + ", getTabelaEnumValue()=" + getTabelaEnumValue()
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
