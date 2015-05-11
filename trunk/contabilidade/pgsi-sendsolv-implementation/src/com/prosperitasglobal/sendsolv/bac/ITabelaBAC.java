package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Tabela;
import com.prosperitasglobal.sendsolv.model.request.TabelaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TabelaMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ITabelaBAC.
 */
public interface ITabelaBAC
{

	/**
	 * Insert location.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Tabela> insertTabela(TabelaMaintenanceRequest request);

	/**
	 * Update location.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Tabela> updateTabela(TabelaMaintenanceRequest request);

	/**
	 * Delete location.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteTabela(TabelaMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Tabela> fetchTabelaById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Tabela> fetchTabelaByRequest(TabelaInquiryRequest request);

}
