package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.EmpresaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.EmpresaResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface IEmpresaBAI.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:24:55 AM
 */
public interface IEmpresaBAI
{

	/**
	 * Insert location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public EmpresaResponse insertEmpresa(EmpresaMaintenanceRequest request);

	/**
	 * Update location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public EmpresaResponse updateEmpresa(EmpresaMaintenanceRequest request);

	/**
	 * Delete location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public EmpresaResponse deleteEmpresa(EmpresaMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public EmpresaResponse fetchEmpresaById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public EmpresaResponse fetchEmpresaByRequest(PagedInquiryRequest request);

	/**
	 * Fetch location by organization.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public EmpresaResponse fetchEmpresaByOrganization(PagedInquiryRequest request);

}