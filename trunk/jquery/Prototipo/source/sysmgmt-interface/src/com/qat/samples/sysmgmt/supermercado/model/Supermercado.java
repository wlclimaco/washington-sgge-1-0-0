package com.qat.samples.sysmgmt.supermercado.model;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.QATModelOL;
import com.qat.samples.sysmgmt.documento.model.Documento;
import com.qat.samples.sysmgmt.endereco.model.Endereco;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Supermercado", propOrder = {"id", "code", "description", "price"})
public class Supermercado extends QATModelOL
{

	/** The id. */
	private Integer id;

	/** The code. */
	private List<Endereco> enderecos;

	/** The description. */
	private String nome;

	/** The price. */
	private String razaoSocial;

	/** The usuario. */
	private String usuario;

	/** The senha. */
	private String senha;

	/** The email. */
	private String email;

	/** The site. */
	private String site;

	/** The documentos. */
	private List<Documento> documentos;

	/**
	 * Instantiates a new bundle.
	 */
	public Supermercado()
	{

	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 */
	public Supermercado(Integer id)
	{
		this.id = id;
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
	 * Gets the enderecos.
	 * 
	 * @return the enderecos
	 */
	public List<Endereco> getEnderecos()
	{
		return enderecos;
	}

	/**
	 * Sets the enderecos.
	 * 
	 * @param enderecos the enderecos to set
	 */
	public void setEnderecos(List<Endereco> enderecos)
	{
		this.enderecos = enderecos;
	}

	/**
	 * Gets the nome.
	 * 
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * Sets the nome.
	 * 
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Gets the razao social.
	 * 
	 * @return the razaoSocial
	 */
	public String getRazaoSocial()
	{
		return razaoSocial;
	}

	/**
	 * Sets the razao social.
	 * 
	 * @param razaoSocial the razaoSocial to set
	 */
	public void setRazaoSocial(String razaoSocial)
	{
		this.razaoSocial = razaoSocial;
	}

	/**
	 * Gets the usuario.
	 * 
	 * @return the usuario
	 */
	public String getUsuario()
	{
		return usuario;
	}

	/**
	 * Sets the usuario.
	 * 
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}

	/**
	 * Gets the senha.
	 * 
	 * @return the senha
	 */
	public String getSenha()
	{
		return senha;
	}

	/**
	 * Sets the senha.
	 * 
	 * @param senha the senha to set
	 */
	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Gets the site.
	 * 
	 * @return the site
	 */
	public String getSite()
	{
		return site;
	}

	/**
	 * Sets the site.
	 * 
	 * @param site the site to set
	 */
	public void setSite(String site)
	{
		this.site = site;
	}

	/**
	 * Gets the documentos.
	 * 
	 * @return the documentos
	 */
	public List<Documento> getDocumentos()
	{
		return documentos;
	}

	/**
	 * Sets the documentos.
	 * 
	 * @param documentos the documentos to set
	 */
	public void setDocumentos(List<Documento> documentos)
	{
		this.documentos = documentos;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Supermercado [getId()=" + getId() + ", getEnderecos()=" + getEnderecos() + ", getNome()=" + getNome()
				+ ", getRazaoSocial()=" + getRazaoSocial() + ", getUsuario()=" + getUsuario() + ", getSenha()="
				+ getSenha() + ", getEmail()=" + getEmail() + ", getSite()=" + getSite() + ", getDocumentos()="
				+ getDocumentos() + ", toString()=" + super.toString() + "]";
	}

}
