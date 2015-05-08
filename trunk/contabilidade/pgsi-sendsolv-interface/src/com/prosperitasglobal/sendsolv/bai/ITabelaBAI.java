package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TabelaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.TabelaResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface ITabelaBAI.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:24:55 AM
 */
public interface ITabelaBAI
{

	/**
	 * Insert location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public TabelaResponse insertTabela(TabelaMaintenanceRequest request);

	/**
	 * Update location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public TabelaResponse updateTabela(TabelaMaintenanceRequest request);

	/**
	 * Delete location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public TabelaResponse deleteTabela(TabelaMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public TabelaResponse fetchTabelaById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public TabelaResponse fetchTabelaByRequest(PagedInquiryRequest request);

}