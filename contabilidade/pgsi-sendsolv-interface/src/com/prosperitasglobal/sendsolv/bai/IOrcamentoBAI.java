package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.OrcamentoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.OrcamentoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.OrcamentoResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface IOrcamentoBAI.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:24:55 AM
 */
public interface IOrcamentoBAI
{

	/**
	 * Insert location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public OrcamentoResponse insertOrcamento(OrcamentoMaintenanceRequest request);

	/**
	 * Update location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public OrcamentoResponse updateOrcamento(OrcamentoMaintenanceRequest request);

	/**
	 * Delete location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public OrcamentoResponse deleteOrcamento(OrcamentoMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public OrcamentoResponse fetchOrcamentoById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public OrcamentoResponse fetchOrcamentoByRequest(OrcamentoInquiryRequest request);

}