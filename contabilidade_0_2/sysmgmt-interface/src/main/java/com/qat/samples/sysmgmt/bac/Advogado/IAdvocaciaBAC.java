/** create by system gera-java version 1.0.0 28/04/2016 14:1 : 44*/
package com.qat.samples.sysmgmt.bac.Advogado;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.Audiencia;
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IAdvocaciaBAC. (Business Area Component - BAC)
 */
public interface IAdvocaciaBAC
{



//===================================### ADVOGADO ####======================================
	/**

	/**
	 * Insert advogado.
	 *
* @param request the advogado maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Advogado> insertAdvogado(AdvogadoMaintenanceRequest request);

	/**
* Update advogado.
*
* @param request the advogado maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Advogado> updateAdvogado(AdvogadoMaintenanceRequest request);

	/**
* Delete advogado.
*
* @param request the advogado maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Advogado> deleteAdvogado(AdvogadoMaintenanceRequest request);

	/**
* Refresh advogados.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Advogado> refreshAdvogados(RefreshRequest request);

	/**
* Fetch advogado by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advogado> fetchAdvogadoById(FetchByIdRequest request);

	/**
* Fetch all advogados.
*
* @return the internal results response< advogado>
*/
	public InternalResultsResponse<Advogado> fetchAllAdvogados(Advogado  advogado);

	/**
* Fetch advogados by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advogado> fetchAdvogadosByRequest(AdvogadoInquiryRequest request);


//===================================### AUDIENCIA ####======================================
	/**

	/**
	 * Insert audiencia.
	 *
* @param request the audiencia maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Audiencia> insertAudiencia(AudienciaMaintenanceRequest request);

	/**
* Update audiencia.
*
* @param request the audiencia maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Audiencia> updateAudiencia(AudienciaMaintenanceRequest request);

	/**
* Delete audiencia.
*
* @param request the audiencia maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Audiencia> deleteAudiencia(AudienciaMaintenanceRequest request);

	/**
* Refresh audiencias.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Audiencia> refreshAudiencias(RefreshRequest request);

	/**
* Fetch audiencia by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Audiencia> fetchAudienciaById(FetchByIdRequest request);

	/**
* Fetch all audiencias.
*
* @return the internal results response< audiencia>
*/
	public InternalResultsResponse<Audiencia> fetchAllAudiencias(Audiencia  audiencia);

	/**
* Fetch audiencias by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Audiencia> fetchAudienciasByRequest(AudienciaInquiryRequest request);


//===================================### PROCESSO ####======================================
	/**

	/**
	 * Insert processo.
	 *
* @param request the processo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Processo> insertProcesso(ProcessoMaintenanceRequest request);

	/**
* Update processo.
*
* @param request the processo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Processo> updateProcesso(ProcessoMaintenanceRequest request);

	/**
* Delete processo.
*
* @param request the processo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Processo> deleteProcesso(ProcessoMaintenanceRequest request);

	/**
* Refresh processos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Processo> refreshProcessos(RefreshRequest request);

	/**
* Fetch processo by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Processo> fetchProcessoById(FetchByIdRequest request);

	/**
* Fetch all processos.
*
* @return the internal results response< processo>
*/
	public InternalResultsResponse<Processo> fetchAllProcessos(Processo  processo);

	/**
* Fetch processos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Processo> fetchProcessosByRequest(ProcessoInquiryRequest request);

}
