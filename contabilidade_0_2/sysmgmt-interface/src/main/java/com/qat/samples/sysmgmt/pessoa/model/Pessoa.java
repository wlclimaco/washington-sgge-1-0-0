package com.qat.samples.sysmgmt.pessoa.model;

import java.util.List;

import com.qat.samples.sysmgmt.banco.model.BancoPessoa;
import com.qat.samples.sysmgmt.condpag.model.CondPagPessoa;
import com.qat.samples.sysmgmt.condpag.model.FormaPgPessoa;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.util.model.Documento;
import com.qat.samples.sysmgmt.util.model.Email;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.Telefone;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Pessoa extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private String nome;

	private String nomePai;

	private String nomeMae;

	private String nomeConjugue;

	private Integer estadoCivil;

	private Integer tipoPessoa;

	private Long datanasc;

	private String foto;

	/** The type of an account. */
	private PessoaTypeEnum pessoaTypeEnum;

	/** The sexo. */
	private Integer sexo;

	/** The enderecos. */
	private List<Endereco> enderecos;

	/** The documentos. */
	private List<Documento> documentos;

	/** The emails. */
	private List<Email> emails;

	/** The Telefones. */
	private List<Telefone> Telefones;

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

	public Integer getPessoaTypeEnumValue()
	{
		if (pessoaTypeEnum != null)
		{
			return pessoaTypeEnum.getValue();
		}
		return null;
	}

	public void setPessoaTypeEnumValue(Integer acaoTypeValue)
	{
		pessoaTypeEnum = PessoaTypeEnum.enumForValue(acaoTypeValue);
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
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
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

	/**
	 * @return the pessoaTypeEnum
	 */
	public PessoaTypeEnum getPessoaTypeEnum()
	{
		return pessoaTypeEnum;
	}

	/**
	 * @param pessoaTypeEnum the pessoaTypeEnum to set
	 */
	public void setPessoaTypeEnum(PessoaTypeEnum pessoaTypeEnum)
	{
		this.pessoaTypeEnum = pessoaTypeEnum;
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
	 * @return the enderecos
	 */
	public List<Endereco> getEnderecos()
	{
		return enderecos;
	}

	/**
	 * @param enderecos the enderecos to set
	 */
	public void setEnderecos(List<Endereco> enderecos)
	{
		this.enderecos = enderecos;
	}

	/**
	 * @return the documentos
	 */
	public List<Documento> getDocumentos()
	{
		return documentos;
	}

	/**
	 * @param documentos the documentos to set
	 */
	public void setDocumentos(List<Documento> documentos)
	{
		this.documentos = documentos;
	}

	/**
	 * @return the emails
	 */
	public List<Email> getEmails()
	{
		return emails;
	}

	/**
	 * @param emails the emails to set
	 */
	public void setEmails(List<Email> emails)
	{
		this.emails = emails;
	}

	/**
	 * @return the telefones
	 */
	public List<Telefone> getTelefones()
	{
		return Telefones;
	}

	/**
	 * @param telefones the telefones to set
	 */
	public void setTelefones(List<Telefone> telefones)
	{
		Telefones = telefones;
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

	/**
	 * @return the tipoPessoa
	 */
	public Integer getTipoPessoa()
	{
		return tipoPessoa;
	}

	/**
	 * @param tipoPessoa the tipoPessoa to set
	 */
	public void setTipoPessoa(Integer tipoPessoa)
	{
		this.tipoPessoa = tipoPessoa;
	}

	@Override
	public String toString()
	{
		return "Pessoa [getPessoaTypeEnumValue()=" + getPessoaTypeEnumValue() + ", getId()=" + getId() + ", getNome()="
				+ getNome() + ", getNomePai()=" + getNomePai() + ", getNomeMae()=" + getNomeMae()
				+ ", getNomeConjugue()=" + getNomeConjugue() + ", getEstadoCivil()=" + getEstadoCivil()
				+ ", getDatanasc()=" + getDatanasc() + ", getFoto()=" + getFoto() + ", getPessoaTypeEnum()="
				+ getPessoaTypeEnum() + ", getContatoList()=" + getContatoList() + ", getSexo()=" + getSexo()
				+ ", getEnderecos()=" + getEnderecos() + ", getDocumentos()=" + getDocumentos() + ", getEmails()="
				+ getEmails() + ", getTelefones()=" + getTelefones() + ", getBancos()=" + getBancos()
				+ ", getFormaPagamentoList()=" + getFormaPagamentoList() + ", getCondPagList()=" + getCondPagList()
				+ ", getTipoPessoa()=" + getTipoPessoa() + ", toString()=" + super.toString() + "]";
	}

}
