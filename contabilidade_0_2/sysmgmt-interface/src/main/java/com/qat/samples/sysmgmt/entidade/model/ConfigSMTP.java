package com.qat.samples.sysmgmt.entidade.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ConfigSMTP extends ModelCosmeDamiao
{

	/** The id. */
	private Integer id;

	/** The ativ SMTP. */
	private Integer ativSMTP;

	/** The serv SMTP. */
	private String servSMTP;

	/** The porta. */
	private String porta;

	/** The end email. */
	private String endEmail;

	/** The usuario. */
	private String usuario;

	/** The senha. */
	private String senha;

	/** The seguranca. */
	private DoisValores seguranca;



	/**
	 * Instantiates a new config SMTP.
	 */
	public ConfigSMTP()
	{
		super();
	}

	/**
	 * Instantiates a new config SMTP.
	 *
	 * @param id the id
	 */
	public ConfigSMTP(Integer id)
	{
		setId(id);
	}

	/**
	 * Instantiates a new config SMTP.
	 *
	 * @param i the i
	 * @param string the string
	 */
	public ConfigSMTP(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the ativ SMTP.
	 *
	 * @return the ativ SMTP
	 */
	public Integer getAtivSMTP() {
		return ativSMTP;
	}

	/**
	 * Sets the ativ SMTP.
	 *
	 * @param ativSMTP the new ativ SMTP
	 */
	public void setAtivSMTP(Integer ativSMTP) {
		this.ativSMTP = ativSMTP;
	}

	/**
	 * Gets the serv SMTP.
	 *
	 * @return the serv SMTP
	 */
	public String getServSMTP() {
		return servSMTP;
	}

	/**
	 * Sets the serv SMTP.
	 *
	 * @param servSMTP the new serv SMTP
	 */
	public void setServSMTP(String servSMTP) {
		this.servSMTP = servSMTP;
	}

	/**
	 * Gets the porta.
	 *
	 * @return the porta
	 */
	public String getPorta() {
		return porta;
	}

	/**
	 * Sets the porta.
	 *
	 * @param porta the new porta
	 */
	public void setPorta(String porta) {
		this.porta = porta;
	}

	/**
	 * Gets the end email.
	 *
	 * @return the end email
	 */
	public String getEndEmail() {
		return endEmail;
	}

	/**
	 * Sets the end email.
	 *
	 * @param endEmail the new end email
	 */
	public void setEndEmail(String endEmail) {
		this.endEmail = endEmail;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the senha.
	 *
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Sets the senha.
	 *
	 * @param senha the new senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Gets the seguranca.
	 *
	 * @return the seguranca
	 */
	public DoisValores getSeguranca() {
		return seguranca;
	}

	/**
	 * Sets the seguranca.
	 *
	 * @param seguranca the new seguranca
	 */
	public void setSeguranca(DoisValores seguranca) {
		this.seguranca = seguranca;
	}

	/* (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao#toString()
	 */
	@Override
	public String toString() {
		return "ConfigSMTP [getId()=" + getId() + ", getAtivSMTP()=" + getAtivSMTP() + ", getServSMTP()="
				+ getServSMTP() + ", getPorta()=" + getPorta() + ", getEndEmail()=" + getEndEmail() + ", getUsuario()="
				+ getUsuario() + ", getSenha()=" + getSenha() + ", getSeguranca()=" + getSeguranca() + ", toString()="
				+ super.toString() + "]";
	}


}
