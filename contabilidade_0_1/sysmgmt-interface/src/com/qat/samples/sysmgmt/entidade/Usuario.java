package com.qat.samples.sysmgmt.entidade;

import com.qat.samples.sysmgmt.pessoa.Pessoa;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Usuario extends Pessoa
{

	private String usuario;

	private String senha;

	private String pergunta;

	private String role;

	private String language;

	public String getUsuario()
	{
		return usuario;
	}

	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public String getPergunta()
	{
		return pergunta;
	}

	public void setPergunta(String pergunta)
	{
		this.pergunta = pergunta;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	@Override
	public String toString()
	{
		return "Usuario [getUsuario()=" + getUsuario() + ", getSenha()=" + getSenha() + ", getPergunta()="
				+ getPergunta() + ", getRole()=" + getRole() + ", getLanguage()=" + getLanguage() + ", toString()="
				+ super.toString() + "]";
	}

}
