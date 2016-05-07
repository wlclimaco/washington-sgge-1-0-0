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