package com.prosperitasglobal.sendsolv.dac;


/**
 * The Interface IFuncionarioDAC.
 */
public interface IFuncionarioDAC
{

	/**
	 * Update funcionario.
	 *
	 * @param funcionario the funcionario
	 * @return the internal results response< funcionario>
	 */
	public InternalResultsResponse<Funcionario> updateFuncionario(Funcionario funcionario);

	/**
	 * Insert funcionario.
	 *
	 * @param funcionario the funcionario
	 * @return the internal results response< funcionario>
	 */
	public InternalResultsResponse<Funcionario> insertFuncionario(Funcionario funcionario);

	/**
	 * Delete funcionario.
	 *
	 * @param funcionario the funcionario
	 * @return the internal response
	 */
	public InternalResponse deleteFuncionario(Funcionario funcionario);

	/**
	 * Fetch funcionario by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request);

	/**
	 * Fetch funcionario by request.
	 *
	 * @param request the request
	 * @return the internal results response< funcionario>
	 */
	public InternalResultsResponse<Funcionario> fetchFuncionarioByRequest(PagedInquiryRequest request);

}
