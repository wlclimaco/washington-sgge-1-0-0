package com.prosperitasglobal.sendsolv.model;

import java.util.List;

import com.prosperitasglobal.cbof.model.Note;

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

	private Integer datanasc;

	private String foto;

	/** The type of an account. */
	private PessoaTypeEnum type;

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

	private List<Note> notes;

	private List<Banco> bancos;

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
	public Integer getDatanasc()
	{
		return datanasc;
	}

	/**
	 * @param datanasc the datanasc to set
	 */
	public void setDatanasc(Integer datanasc)
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
	 * @param type the type to set
	 */
	public void setType(PessoaTypeEnum type)
	{
		this.type = type;
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
	 * @return the notes
	 */
	public List<Note> getNotes()
	{
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(List<Note> notes)
	{
		this.notes = notes;
	}

	/**
	 * @return the bancos
	 */
	public List<Banco> getBancos()
	{
		return bancos;
	}

	/**
	 * @param bancos the bancos to set
	 */
	public void setBancos(List<Banco> bancos)
	{
		this.bancos = bancos;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Pessoa [getId()=" + getId() + ", getNome()=" + getNome() + ", getNomePai()=" + getNomePai()
				+ ", getNomeMae()=" + getNomeMae() + ", getNomeConjugue()=" + getNomeConjugue() + ", getEstadoCivil()="
				+ getEstadoCivil() + ", getDatanasc()=" + getDatanasc() + ", getFoto()=" + getFoto() + ", getType()="
				+ getType() + ", getSexo()=" + getSexo() + ", getEnderecos()=" + getEnderecos() + ", getDocumentos()="
				+ getDocumentos() + ", getEmails()=" + getEmails() + ", getTelefones()=" + getTelefones()
				+ ", getNotes()=" + getNotes() + ", getBancos()=" + getBancos() + ", getParentId()=" + getParentId()
				+ ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()="
				+ getStatusList() + ", toString()=" + super.toString() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()=" + getCreateDateUTC()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()=" + getModifyDateUTC()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
