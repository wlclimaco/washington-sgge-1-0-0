/** create by system gera-java version 1.0.0 16/09/2017 22:9 : 46*/
package com.qat.samples.sysmgmt.advocacia;

import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class ProcessoCliente extends ModelCosmeDamiao {

	/** The econtabil id for the ProcessoCliente. */
	private Integer id;

	/** The econtabil clienteId for the ProcessoCliente. */
	private Cliente clienteId;

	/** The econtabil processoId for the ProcessoCliente. */
	private Integer processoId;

	/**
	 * Default constructor.
	 */
	public ProcessoCliente() {
		super();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
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

	public Cliente getClienteId() {
		return clienteId;
	}

	public void setClienteId(Cliente clienteId) {
		this.clienteId = clienteId;
	}

	public Integer getProcessoId() {
		return processoId;
	}

	public void setProcessoId(Integer processoId) {
		this.processoId = processoId;
	}

	@Override
	public String toString() {
		return "ProcessoCliente [getId()=" + getId() + ", getClienteId()=" + getClienteId() + ", getProcessoId()="
				+ getProcessoId() + ", toString()=" + super.toString() + "]";
	}


}
