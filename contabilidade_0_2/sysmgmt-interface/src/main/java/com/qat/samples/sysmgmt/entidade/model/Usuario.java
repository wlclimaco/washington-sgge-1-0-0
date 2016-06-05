package com.qat.samples.sysmgmt.entidade.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.pessoa.model.Pessoa;
import com.qat.samples.sysmgmt.util.model.Documento;
import com.qat.samples.sysmgmt.util.model.Email;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Usuario extends Pessoa
{

	private String email;

	private String senha;

	private Documento cpf;

	private String pergunta;

	private String role;

	private String telefone;

	private String language;



	private Long ultAcesso;

	private List<Email> emails;



	public Usuario(Integer id,String role) {
		super();
		this.role = role;
		setId(id);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Documento getCpf() {
		return cpf;
	}

	public void setCpf(Documento cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [getSenha()=" + getSenha() + ", getPergunta()=" + getPergunta() + ", getRole()=" + getRole()
				+ ", getLanguage()=" + getLanguage() + ", getUltAcesso()=" + getUltAcesso() + ", getEmails()="
				+ getEmails() + ", getTelefone()=" + getTelefone() + ", getCpf()=" + getCpf() + ", getEmail()="
				+ getEmail() + ", toString()=" + super.toString() + "]";
	}

}
