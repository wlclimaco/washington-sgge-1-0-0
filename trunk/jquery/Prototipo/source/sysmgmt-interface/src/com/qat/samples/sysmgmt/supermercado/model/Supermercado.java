package com.qat.samples.sysmgmt.supermercado.model;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.documento.model.Documento;
import com.qat.samples.sysmgmt.endereco.model.Endereco;
import com.qat.samples.sysmgmt.util.Util;

// TODO: Auto-generated Javadoc
/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Supermercado", propOrder = {"id", "code", "description", "price"})
public class Supermercado extends Util
{

	/** The code. */
	private List<Endereco> enderecos;

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

	/** The id. */
	private Integer id;

	/**
	 * Instantiates a new supermercado.
	 * 
	 * @param id the id
	 */
	public Supermercado(Integer id)
	{
		super();
		this.id = id;
	}

	/**
	 * Instantiates a new supermercado.
	 * 
	 * @param id the id
	 * @param enderecos the enderecos
	 * @param usuario the usuario
	 * @param senha the senha
	 * @param email the email
	 * @param site the site
	 * @param documentos the documentos
	 */
	public Supermercado(Integer id, List<Endereco> enderecos, String usuario, String senha, String email, String site,
			List<Documento> documentos)
	{
		super();
		this.id = id;
		this.enderecos = enderecos;
		this.usuario = usuario;
		this.senha = senha;
		this.email = email;
		this.site = site;
		this.documentos = documentos;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.util.Util#toString()
	 */
	@Override
	public String toString()
	{
		return "Supermercado [getId()=" + getId() + ", getEnderecos()=" + getEnderecos() + ", getUsuario()="
				+ getUsuario() + ", getSenha()=" + getSenha() + ", getEmail()=" + getEmail() + ", getSite()="
				+ getSite() + ", getDocumentos()=" + getDocumentos() + ", toString()=" + super.toString() + "]";
	}

}
