package com.sensus.mlc.endereco.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.endereco.model.Endereco;


// TODO: Auto-generated Javadoc
/**
 * The Class ActionResponse.
 *
 * @author - QAT Brazil.
 */
public class EnderecoResponse extends Response
{

	/** The actions. */
	private List<Endereco> enderecos;

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
	 * @param enderecos the new enderecos
	 */
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EnderecoResponse [enderecos=" + enderecos + ", getEnderecos()="
				+ getEnderecos() + "]";
	}



}
