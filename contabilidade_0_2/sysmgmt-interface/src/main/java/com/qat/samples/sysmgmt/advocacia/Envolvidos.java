package com.qat.samples.sysmgmt.advocacia;

import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */
@SuppressWarnings("serial")
public class Envolvidos extends ModelCosmeDamiao {
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Cliente cliente;

	/** The tipo envolvido. */
	private DoisValores tipoEnvolvido;

	/** The envolvimento. */
	private DoisValores envolvimento;

	private Integer bCliente;

	/**
	 * Instantiates a new envolvidos.
	 */
	public Envolvidos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new envolvidos.
	 *
	 * @param i
	 *            the i
	 * @param string
	 *            the string
	 */
	public Envolvidos(int i, String string) {
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
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the tipo envolvido.
	 *
	 * @return the tipo envolvido
	 */
	public DoisValores getTipoEnvolvido() {
		return tipoEnvolvido;
	}

	/**
	 * Sets the tipo envolvido.
	 *
	 * @param tipoEnvolvido
	 *            the new tipo envolvido
	 */
	public void setTipoEnvolvido(DoisValores tipoEnvolvido) {
		this.tipoEnvolvido = tipoEnvolvido;
	}

	/**
	 * Gets the envolvimento.
	 *
	 * @return the envolvimento
	 */
	public DoisValores getEnvolvimento() {
		return envolvimento;
	}

	/**
	 * Sets the envolvimento.
	 *
	 * @param envolvimento
	 *            the new envolvimento
	 */
	public void setEnvolvimento(DoisValores envolvimento) {
		this.envolvimento = envolvimento;
	}

	public Integer getbCliente() {
		return bCliente;
	}

	public void setbCliente(Integer bCliente) {
		this.bCliente = bCliente;
	}

	@Override
	public String toString() {
		return "Envolvidos [getId()=" + getId() + ", getCliente()=" + getCliente() + ", getTipoEnvolvido()="
				+ getTipoEnvolvido() + ", getEnvolvimento()=" + getEnvolvimento() + ", getbCliente()=" + getbCliente()
				+ ", toString()=" + super.toString() + "]";
	}

}
