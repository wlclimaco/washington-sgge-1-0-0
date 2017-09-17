/** create by system gera-java version 1.0.0 15/09/2017 22:9 : 49*/
package com.qat.samples.sysmgmt.util.model;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class ClienteCompromisso extends ModelCosmeDamiao {

	/** The econtabil id for the ClienteCompromisso. */
	private Integer id;

	/** The econtabil clienteId for the ClienteCompromisso. */
	private Integer clienteId;

	/** The econtabil compromissoId for the ClienteCompromisso. */
	private Integer compromissoId;

	/**
	 * Default constructor.
	 */
	public ClienteCompromisso() {
		super();
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
	 * Gets the cliente id.
	 *
	 * @return the cliente id
	 */
	public Integer getClienteId() {
		return clienteId;
	}

	/**
	 * Sets the cliente id.
	 *
	 * @param clienteId
	 *            the new cliente id
	 */
	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	/**
	 * Gets the compromisso id.
	 *
	 * @return the compromisso id
	 */
	public Integer getCompromissoId() {
		return compromissoId;
	}

	/**
	 * Sets the compromisso id.
	 *
	 * @param compromissoId
	 *            the new compromisso id
	 */
	public void setCompromissoId(Integer compromissoId) {
		this.compromissoId = compromissoId;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao#toString()
	 */
	@Override
	public String toString() {
		return "ClienteCompromisso [getId()=" + getId() + ", getClienteId()=" + getClienteId() + ", getCompromissoId()="
				+ getCompromissoId() + ", toString()=" + super.toString() + "]";
	}

}
