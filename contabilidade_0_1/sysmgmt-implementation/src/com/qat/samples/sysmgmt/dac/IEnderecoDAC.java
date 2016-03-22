package com.qat.samples.sysmgmt.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.util.Endereco;

/**
 * The Interface IEnderecoDAC. (Data Access Component - DAC)
 */
public interface IEnderecoDAC
{

	/**
	 * Insert endereco.
	 * 
	 * @param endereco the endereco
	 * @param response
	 * @param string
	 * @return the internal response
	 */
	public Integer insertEndereco(Endereco endereco, String string, InternalResultsResponse<?> response);

	/**
	 * Update endereco.
	 * 
	 * @param endereco the endereco
	 * @param response
	 * 
	 * @return the internal response
	 */
	public Integer updateEndereco(Endereco endereco, InternalResultsResponse<?> response);

	/**
	 * Delete endereco.
	 * 
	 * @param endereco the endereco
	 * 
	 * @return the internal response
	 */
	public Integer deleteEndereco(Endereco endereco, InternalResultsResponse<?> response);

}
