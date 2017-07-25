package com.qat.samples.sysmgmt.agencia.model;

import java.util.List;

import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.util.model.Email;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.Telefone;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */
@SuppressWarnings("serial")
public class Agencia extends ModelCosmeDamiao {
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	/** The enderecos. */
	private List<Endereco> enderecos;

	/** The emails. */
	private List<Email> emails;

	/** The telefones. */
	private List<Telefone> telefones;

	/** The gerente. */
	private String gerente;

	/** The responsavel conta. */
	private String responsavelConta;

	/** The numero conta. */
	private List<Conta> numeroConta;

	/** The banco. */
	private Banco banco;

	/** The gerente. */
	private String observacao;

	/**
	 * Instantiates a new agencia.
	 */
	public Agencia() {

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
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the enderecos.
	 *
	 * @return the enderecos
	 */
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	/**
	 * Sets the enderecos.
	 *
	 * @param enderecos
	 *            the enderecos to set
	 */
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	/**
	 * Gets the emails.
	 *
	 * @return the emails
	 */
	public List<Email> getEmails() {
		return emails;
	}

	/**
	 * Sets the emails.
	 *
	 * @param emails
	 *            the emails to set
	 */
	public void setEmails(List<Email> emails) {
		this.emails = emails;
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
	 *            the telefones to set
	 */
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	/**
	 * Gets the gerente.
	 *
	 * @return the gerente
	 */
	public String getGerente() {
		return gerente;
	}

	/**
	 * Sets the gerente.
	 *
	 * @param gerente
	 *            the gerente to set
	 */
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

	/**
	 * Gets the responsavel conta.
	 *
	 * @return the responsavelConta
	 */
	public String getResponsavelConta() {
		return responsavelConta;
	}

	/**
	 * Sets the responsavel conta.
	 *
	 * @param responsavelConta
	 *            the responsavelConta to set
	 */
	public void setResponsavelConta(String responsavelConta) {
		this.responsavelConta = responsavelConta;
	}

	/**
	 * Gets the numero conta.
	 *
	 * @return the numeroConta
	 */
	public List<Conta> getNumeroConta() {
		return numeroConta;
	}

	/**
	 * Sets the numero conta.
	 *
	 * @param numeroConta
	 *            the numeroConta to set
	 */
	public void setNumeroConta(List<Conta> numeroConta) {
		this.numeroConta = numeroConta;
	}

	/**
	 * Gets the banco.
	 *
	 * @return the banco
	 */
	public Banco getBanco() {
		return banco;
	}

	/**
	 * Sets the banco.
	 *
	 * @param banco
	 *            the new banco
	 */
	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	/**
	 * Gets the observacao.
	 *
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * Sets the observacao.
	 *
	 * @param observacao
	 *            the new observacao
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao#toString()
	 */
	@Override
	public String toString() {
		return "Agencia [getId()=" + getId() + ", getNome()=" + getNome() + ", getEnderecos()=" + getEnderecos()
				+ ", getEmails()=" + getEmails() + ", getTelefones()=" + getTelefones() + ", getGerente()="
				+ getGerente() + ", getResponsavelConta()=" + getResponsavelConta() + ", getNumeroConta()="
				+ getNumeroConta() + ", getBanco()=" + getBanco() + ", getObservacao()=" + getObservacao()
				+ ", toString()=" + super.toString() + "]";
	}

}
