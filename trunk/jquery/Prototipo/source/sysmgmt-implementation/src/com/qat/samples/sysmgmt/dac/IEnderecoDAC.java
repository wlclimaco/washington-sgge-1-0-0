package com.qat.samples.sysmgmt.dac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.samples.sysmgmt.endereco.model.Endereco;
import com.qat.samples.sysmgmt.model.response.InternalResponseLocal;

/**
 * The Interface IEnderecoDAC. (Data Access Component - DAC)
 */
public interface IEnderecoDAC
{

	/**
	 * Insert endereco.
	 * 
	 * @param endereco the endereco
	 * @return the internal response
	 */
	public InternalResponseLocal insertEndereco(Endereco endereco);

	/**
	 * Update endereco.
	 * 
	 * @param endereco the endereco
	 * 
	 * @return the internal response
	 */
	public InternalResponseLocal updateEndereco(Endereco endereco);

	/**
	 * Delete endereco.
	 * 
	 * @param endereco the endereco
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteEndereco(Endereco endereco);

}
