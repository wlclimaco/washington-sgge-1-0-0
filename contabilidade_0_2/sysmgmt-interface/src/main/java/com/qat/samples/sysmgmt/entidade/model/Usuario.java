package com.qat.samples.sysmgmt.entidade.model;

import java.util.List;

import com.qat.samples.sysmgmt.pessoa.model.Pessoa;
import com.qat.samples.sysmgmt.util.model.Email;

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

	private List<Email> emails;

	public Usuario(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
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
	public List<Email> getEmails()
	{
		return emails;
	}

	@Override
	public void setEmails(List<Email> emails)
	{
		this.emails = emails;
	}

	@Override
	public String toString()
	{
		return "Usuario [getSenha()=" + getSenha() + ", getPergunta()=" + getPergunta() + ", getRole()=" + getRole()
				+ ", getLanguage()=" + getLanguage() + ", getLogin()=" + getLogin() + ", getUltAcesso()="
				+ getUltAcesso() + ", getEmails()=" + getEmails() + ", toString()=" + super.toString() + "]";
	}

}
