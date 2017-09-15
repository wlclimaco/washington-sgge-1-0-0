package com.qat.samples.sysmgmt.util.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */
@SuppressWarnings("serial")
public class ParticipanteExterno extends ModelCosmeDamiao {
	/** The SendSolv id for the account. */
	private Integer id;

	/** The dias semanas. */
	private String nome;

	/** The hora inicio. */
	private List<Email>  email;

	/** The hora final. */
	private List<Telefone> telefones;

	/**
	 * Default constructor.
	 */
	public ParticipanteExterno() {
		super();
	}

	/**
	 * Instantiates a new dias horas.
	 *
	 * @param i
	 *            the i
	 * @param string
	 *            the string
	 */
	public ParticipanteExterno(int i, String string) {
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
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome
	 *            the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public List<Email> getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(List<Email> email) {
		this.email = email;
	}

	/**
	 * Gets the telefones.
	 *
	 * @return the telefones
	 */
	public List<Telefone> getTelefones() {
		return telefones;
	}

	/**
	 * Sets the telefones.
	 *
	 * @param telefones
	 *            the new telefones
	 */
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao#toString()
	 */
	@Override
	public String toString() {
		return "ParticipanteExterno [getId()=" + getId() + ", getNome()=" + getNome() + ", getEmail()=" + getEmail()
				+ ", getTelefones()=" + getTelefones() + ", toString()=" + super.toString() + "]";
	}

}
