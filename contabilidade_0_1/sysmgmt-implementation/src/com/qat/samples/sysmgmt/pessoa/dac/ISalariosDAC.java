package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.dp.Salario;

public interface ISalariosDAC
{
	/**
	 * Update salarios.
	 * 
	 * @param salarios the salarios
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateSalario(Salario salarios, InternalResultsResponse<?> response);

	/**
	 * Insert salarios.
	 * 
	 * @param salarios the salarios
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertSalario(Salario salarios, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business salarios.
	 * 
	 * @param salarios the salarios
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteSalario(Salario salarios, InternalResultsResponse<?> response);

}