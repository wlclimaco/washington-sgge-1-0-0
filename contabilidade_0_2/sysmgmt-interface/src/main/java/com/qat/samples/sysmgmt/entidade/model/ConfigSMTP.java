package com.qat.samples.sysmgmt.entidade.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ConfigSMTP extends ModelCosmeDamiao
{
	private Integer id;
	private Integer ativSMTP;
	private String servSMTP;
	private String porta;
	private String endEmail;
	private String usuario;
	private String senha;
	private DoisValores seguranca;



	public ConfigSMTP()
	{
		super();
	}

	public ConfigSMTP(Integer id)
	{
		setId(id);
	}

	public ConfigSMTP(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServSMTP() {
		return servSMTP;
	}

	public void setServSMTP(String servSMTP) {
		this.servSMTP = servSMTP;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getEndEmail() {
		return endEmail;
	}

	public void setEndEmail(String endEmail) {
		this.endEmail = endEmail;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public DoisValores getSeguranca() {
		return seguranca;
	}

	public void setSeguranca(DoisValores seguranca) {
		this.seguranca = seguranca;
	}

	@Override
	public String toString() {
		return "ConfigSMTP [getId()=" + getId() + ", getServSMTP()=" + getServSMTP() + ", getPorta()=" + getPorta()
				+ ", getEndEmail()=" + getEndEmail() + ", getUsuario()=" + getUsuario() + ", getSenha()=" + getSenha()
				+ ", getSeguranca()=" + getSeguranca() + ", toString()=" + super.toString() + "]";
	}




}
