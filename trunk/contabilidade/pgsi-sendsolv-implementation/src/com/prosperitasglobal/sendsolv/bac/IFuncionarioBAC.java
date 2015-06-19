package com.prosperitasglobal.sendsolv.bac;


// TODO: Auto-generated Javadoc
/**
 * The Interface IFuncionarioBAC.
 */
public interface IFuncionarioBAC
{

	/**
	 * Insert funcionario.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Funcionario> insertFuncionario(FuncionarioMaintenanceRequest request);

	/**
	 * Update funcionario.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Funcionario> updateFuncionario(FuncionarioMaintenanceRequest request);

	/**
	 * Delete funcionario.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteFuncionario(FuncionarioMaintenanceRequest request);

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
	 * @return the internal results response
	 */
	public InternalResultsResponse<Funcionario> fetchFuncionarioByRequest(FuncionarioInquiryRequest request);

}
