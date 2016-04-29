/** create by system gera-java version 1.0.0 28/04/2016 19:51 : 33*/
package com.qat.samples.sysmgmt.bac.Historico;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoInquiryRequest;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IHistoricoBAC. (Business Area Component - BAC)
 */
public interface IHistoricoBAC
{



//===================================### HISTORICO ####======================================
	/**

	/**
	 * Insert historico.
	 *
* @param request the historico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Historico> insertHistorico(HistoricoMaintenanceRequest request);

	/**
* Update historico.
*
* @param request the historico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Historico> updateHistorico(HistoricoMaintenanceRequest request);

	/**
* Delete historico.
*
* @param request the historico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Historico> deleteHistorico(HistoricoMaintenanceRequest request);

	/**
* Refresh historicos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Historico> refreshHistoricos(RefreshRequest request);

	/**
* Fetch historico by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Historico> fetchHistoricoById(FetchByIdRequest request);

	/**
* Fetch all historicos.
*
* @return the internal results response< historico>
*/
	public InternalResultsResponse<Historico> fetchAllHistoricos(Historico  historico);

	/**
* Fetch historicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Historico> fetchHistoricosByRequest(HistoricoInquiryRequest request);

}