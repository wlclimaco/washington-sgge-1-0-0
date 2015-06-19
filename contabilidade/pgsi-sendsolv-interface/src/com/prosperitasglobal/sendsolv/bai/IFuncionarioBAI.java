package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.FuncionarioResponse;

/**
 * The Interface IFuncionarioBAI.
 */
public interface IFuncionarioBAI
{

	/**
	 * Insert funcionario.
	 *
	 * @param request the request
	 * @return the funcionario response
	 */
	public FuncionarioResponse insertFuncionario(FuncionarioMaintenanceRequest request);

	/**
	 * Update funcionario.
	 *
	 * @param request the request
	 * @return the funcionario response
	 */
	public FuncionarioResponse updateFuncionario(FuncionarioMaintenanceRequest request);

	/**
	 * Delete funcionario.
	 *
	 * @param request the request
	 * @return the funcionario response
	 */
	public FuncionarioResponse deleteFuncionario(FuncionarioMaintenanceRequest request);

	/**
	 * Fetch funcionario by id.
	 *
	 * @param request the request
	 * @return the funcionario response
	 */
	public FuncionarioResponse fetchFuncionarioById(FetchByIdRequest request);

	/**
	 * Fetch funcionario by request.
	 *
	 * @param request the request
	 * @return the funcionario response
	 */
	public FuncionarioResponse fetchFuncionarioByRequest(FuncionarioInquiryRequest request);

}