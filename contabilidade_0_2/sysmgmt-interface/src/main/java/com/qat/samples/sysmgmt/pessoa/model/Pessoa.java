package com.qat.samples.sysmgmt.pessoa.model;

import java.util.List;

import com.qat.samples.sysmgmt.banco.model.BancoPessoa;
import com.qat.samples.sysmgmt.condpag.model.CondPagPessoa;
import com.qat.samples.sysmgmt.condpag.model.FormaPgPessoa;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.entidade.model.Entidade;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Pessoa extends Entidade
{
	/** The SendSolv id for the account. */
	private Integer id;

	private String codigo;

	private String nomePai;

	private String nomeMae;

	private String nomeConjugue;

	private Integer estadoCivil;

	private Long datanasc;

	private String foto;

	/** The type of an account. */
	private List<PessoaTipo> pessoaTipo;

	/** The sexo. */
	private Integer sexo;

	private List<BancoPessoa> bancos;

	private List<FormaPgPessoa> formaPagamentoList;

	private List<CondPagPessoa> condPagList;

	private List<Contato> contatoList;

	/**
	 * Default constructor.
	 */
	public Pessoa()
	{
		super();
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the nomePai
	 */
	public String getNomePai()
	{
		return nomePai;
	}

	/**
	 * @param nomePai the nomePai to set
	 */
	public void setNomePai(String nomePai)
	{
		this.nomePai = nomePai;
	}

	/**
	 * @return the nomeMae
	 */
	public String getNomeMae()
	{
		return nomeMae;
	}

	/**
	 * @param nomeMae the nomeMae to set
	 */
	public void setNomeMae(String nomeMae)
	{
		this.nomeMae = nomeMae;
	}

	/**
	 * @return the nomeConjugue
	 */
	public String getNomeConjugue()
	{
		return nomeConjugue;
	}

	/**
	 * @param nomeConjugue the nomeConjugue to set
	 */
	public void setNomeConjugue(String nomeConjugue)
	{
		this.nomeConjugue = nomeConjugue;
	}

	/**
	 * @return the estadoCivil
	 */
	public Integer getEstadoCivil()
	{
		return estadoCivil;
	}

	/**
	 * @param estadoCivil the estadoCivil to set
	 */
	public void setEstadoCivil(Integer estadoCivil)
	{
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the datanasc
	 */
	public Long getDatanasc()
	{
		return datanasc;
	}

	/**
	 * @param datanasc the datanasc to set
	 */
	public void setDatanasc(Long datanasc)
	{
		this.datanasc = datanasc;
	}

	/**
	 * @return the foto
	 */
	public String getFoto()
	{
		return foto;
	}

	/**
	 * @param foto the foto to set
	 */
	public void setFoto(String foto)
	{
		this.foto = foto;
	}


	public List<PessoaTipo> getPessoaTipo() {
		return pessoaTipo;
	}


	public void setPessoaTipo(List<PessoaTipo> pessoaTipo) {
		this.pessoaTipo = pessoaTipo;
	}


	/**
	 * @return the contatoList
	 */
	public List<Contato> getContatoList()
	{
		return contatoList;
	}

	/**
	 * @param contatoList the contatoList to set
	 */
	public void setContatoList(List<Contato> contatoList)
	{
		this.contatoList = contatoList;
	}

	/**
	 * @return the sexo
	 */
	public Integer getSexo()
	{
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(Integer sexo)
	{
		this.sexo = sexo;
	}


	/**
	 * @return the bancos
	 */
	public List<BancoPessoa> getBancos()
	{
		return bancos;
	}

	/**
	 * @param bancos the bancos to set
	 */
	public void setBancos(List<BancoPessoa> bancos)
	{
		this.bancos = bancos;
	}

	/**
	 * @return the formaPagamentoList
	 */
	public List<FormaPgPessoa> getFormaPagamentoList()
	{
		return formaPagamentoList;
	}

	/**
	 * @param formaPagamentoList the formaPagamentoList to set
	 */
	public void setFormaPagamentoList(List<FormaPgPessoa> formaPagamentoList)
	{
		this.formaPagamentoList = formaPagamentoList;
	}

	/**
	 * @return the condPagList
	 */
	public List<CondPagPessoa> getCondPagList()
	{
		return condPagList;
	}

	/**
	 * @param condPagList the condPagList to set
	 */
	public void setCondPagList(List<CondPagPessoa> condPagList)
	{
		this.condPagList = condPagList;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	@Override
	public String toString() {
		return "Pessoa [getId()=" + getId() + ", getNomePai()=" + getNomePai() + ", getNomeMae()=" + getNomeMae()
				+ ", getNomeConjugue()=" + getNomeConjugue() + ", getEstadoCivil()=" + getEstadoCivil()
				+ ", getDatanasc()=" + getDatanasc() + ", getFoto()=" + getFoto() + ", getPessoaTipo()="
				+ getPessoaTipo() + ", getContatoList()=" + getContatoList() + ", getSexo()=" + getSexo()
				+ ", getBancos()=" + getBancos() + ", getFormaPagamentoList()=" + getFormaPagamentoList()
				+ ", getCondPagList()=" + getCondPagList() + ", getCodigo()=" + getCodigo() + ", toString()="
				+ super.toString() + "]";
	}
}
