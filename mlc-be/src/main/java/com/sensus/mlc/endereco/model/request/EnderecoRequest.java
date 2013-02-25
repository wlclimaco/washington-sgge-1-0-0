package com.sensus.mlc.endereco.model.request;

import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.empresa.model.Empresa;
import com.sensus.mlc.endereco.model.Endereco;

// TODO: Auto-generated Javadoc
/**
 * The Model Object Action.
 * 
 * @author - QAT Brazil
 */
public class EnderecoRequest extends LightSelectionRequest
{

	/** The action. */
	private Endereco endereco;

	/** The parent retry process. */
	private Integer parentRetry;

	/**
	 * Gets the endereco.
	 *
	 * @return the endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * Sets the endereco.
	 *
	 * @param endereco the new endereco
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	/**
	 * Gets the parent retry.
	 *
	 * @return the parent retry
	 */
	public Integer getParentRetry() {
		return parentRetry;
	}

	/**
	 * Sets the parent retry.
	 *
	 * @param parentRetry the new parent retry
	 */
	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}



}
