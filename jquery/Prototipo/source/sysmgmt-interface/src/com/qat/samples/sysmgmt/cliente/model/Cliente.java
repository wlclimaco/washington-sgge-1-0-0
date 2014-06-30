package com.qat.samples.sysmgmt.cliente.model;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.documento.model.Documento;
import com.qat.samples.sysmgmt.endereco.model.Endereco;
import com.qat.samples.sysmgmt.listaCompras.model.ListaCompras;
import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Cliente", propOrder = {"clienteid", "type", "nome", "sobrenome", "usuario", "senha",
		"email", "documentos", "enderecos", "listaCompras"})
public class Cliente extends Util
{

	/** The id. */
	private Integer clienteid;

	/** The code. */
	private ClienteTypeEnum type;

	/** The description. */
	private String nome;

	/** The price. */
	private String sobrenome;

	/** The usuario. */
	private String usuario;

	/** The senha. */
	private String senha;

	/** The email. */
	private String email;

	/** The documentos. */
	private List<Documento> documentos;

	/** The enderecos. */
	private List<Endereco> enderecos;

	private List<ListaCompras> listaCompras;

	/**
	 * Instantiates a new bundle.
	 */
	public Cliente()
	{

	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 */

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public ClienteTypeEnum getType()
	{
		return type;
	}

	public Integer getClienteid()
	{
		return clienteid;
	}

	public void setClienteid(Integer clienteid)
	{
		this.clienteid = clienteid;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type the type to set
	 */
	public void setType(ClienteTypeEnum type)
	{
		this.type = type;
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
	 * Gets the sobrenome.
	 * 
	 * @return the sobrenome
	 */
	public String getSobrenome()
	{
		return sobrenome;
	}

	/**
	 * Sets the sobrenome.
	 * 
	 * @param sobrenome the sobrenome to set
	 */
	public void setSobrenome(String sobrenome)
	{
		this.sobrenome = sobrenome;
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

	public List<ListaCompras> getListaCompras()
	{
		return listaCompras;
	}

	public void setListaCompras(List<ListaCompras> listaCompras)
	{
		this.listaCompras = listaCompras;
	}

	/**
	 * Methods that follow the naming pattern get.....Value() provide convenience for returning the primitive value of
	 * an enum. For example, database mapping of an enum to a database column could make use of this method.
	 * 
	 * @return
	 */
	public Integer getClienteTypeEnumValue()
	{
		return type.getValue();
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 * 
	 * @return
	 */
	public void setClienteTypeEnumValue(Integer tableTypeEnumValue)
	{
		type = ClienteTypeEnum.enumForValue(tableTypeEnumValue);
	}

	@Override
	public String toString()
	{
		return "Cliente [getType()=" + getType() + ", getClienteid()=" + getClienteid() + ", getNome()=" + getNome()
				+ ", getSobrenome()=" + getSobrenome() + ", getUsuario()=" + getUsuario() + ", getSenha()="
				+ getSenha() + ", getEmail()=" + getEmail() + ", getDocumentos()=" + getDocumentos()
				+ ", getEnderecos()=" + getEnderecos() + ", getListaCompras()=" + getListaCompras()
				+ ", getClienteTypeEnumValue()=" + getClienteTypeEnumValue() + ", toString()=" + super.toString() + "]";
	}

}
