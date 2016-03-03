package com.qat.samples.sysmgmt.entidade;

import com.qat.samples.sysmgmt.pessoa.Pessoa;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Usuario extends Pessoa
{

	private String login;

	private String senha;

	private String pergunta;

	private String role;

	private String language;

	private Long ultAcesso;

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

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public Long getUltAcesso()
	{
		return ultAcesso;
	}

	public void setUltAcesso(Long ultAcesso)
	{
		this.ultAcesso = ultAcesso;
	}

	@Override
	public String toString()
	{
		return "Usuario [getSenha()=" + getSenha() + ", getPergunta()=" + getPergunta() + ", getRole()=" + getRole()
				+ ", getLanguage()=" + getLanguage() + ", getLogin()=" + getLogin() + ", getUltAcesso()="
				+ getUltAcesso() + ", toString()=" + super.toString() + "]";
	}

}
