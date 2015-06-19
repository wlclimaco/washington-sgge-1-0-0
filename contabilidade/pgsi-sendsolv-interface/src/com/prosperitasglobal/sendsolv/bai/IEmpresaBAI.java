package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.EmpresaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EmpresaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.EmpresaResponse;

/**
 * The Interface IEmpresaBAI.
 */
public interface IEmpresaBAI
{

	/**
	 * Insert empresa.
	 *
	 * @param request the request
	 * @return the empresa response
	 */
	public EmpresaResponse insertEmpresa(EmpresaMaintenanceRequest request);

	/**
	 * Update empresa.
	 *
	 * @param request the request
	 * @return the empresa response
	 */
	public EmpresaResponse updateEmpresa(EmpresaMaintenanceRequest request);

	/**
	 * Delete empresa.
	 *
	 * @param request the request
	 * @return the empresa response
	 */
	public EmpresaResponse deleteEmpresa(EmpresaMaintenanceRequest request);

	/**
	 * Fetch empresa by id.
	 *
	 * @param request the request
	 * @return the empresa response
	 */
	public EmpresaResponse fetchEmpresaById(FetchByIdRequest request);

	/**
	 * Fetch empresa by request.
	 *
	 * @param request the request
	 * @return the empresa response
	 */
	public EmpresaResponse fetchEmpresaByRequest(EmpresaInquiryRequest request);

}