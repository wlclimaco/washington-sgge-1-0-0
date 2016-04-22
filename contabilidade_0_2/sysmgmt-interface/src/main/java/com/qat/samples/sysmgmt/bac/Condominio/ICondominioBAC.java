package com.qat.samples.sysmgmt.bac.Condominio;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.condominio.model.Avisos;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;
import com.qat.samples.sysmgmt.condominio.model.Sindico;
import com.qat.samples.sysmgmt.condominio.model.request.AvisoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.AvisoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface ICondominioBAC. (Business Area Component - BAC)
 */
public interface ICondominioBAC
{



//===================================### SINDICO ####======================================
	/**

	/**
	 * Insert sindico.
	 *
* @param request the sindico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Sindico> insertSindico(SindicoMaintenanceRequest request);

	/**
* Update sindico.
*
* @param request the sindico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Sindico> updateSindico(SindicoMaintenanceRequest request);

	/**
* Delete sindico.
*
* @param request the sindico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Sindico> deleteSindico(SindicoMaintenanceRequest request);

	/**
* Refresh sindicos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Sindico> refreshSindicos(RefreshRequest request);

	/**
* Fetch sindico by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Sindico> fetchSindicoById(FetchByIdRequest request);

	/**
* Fetch all sindicos.
*
* @return the internal results response< sindico>
*/
	public InternalResultsResponse<Sindico> fetchAllSindicos(Sindico  sindico);

	/**
* Fetch sindicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Sindico> fetchSindicosByRequest(SindicoInquiryRequest request);


//===================================### INQUILINO ####======================================
	/**

	/**
	 * Insert inquilino.
	 *
* @param request the inquilino maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Inquilino> insertInquilino(InquilinoMaintenanceRequest request);

	/**
* Update inquilino.
*
* @param request the inquilino maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Inquilino> updateInquilino(InquilinoMaintenanceRequest request);

	/**
* Delete inquilino.
*
* @param request the inquilino maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Inquilino> deleteInquilino(InquilinoMaintenanceRequest request);

	/**
* Refresh inquilinos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Inquilino> refreshInquilinos(RefreshRequest request);

	/**
* Fetch inquilino by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Inquilino> fetchInquilinoById(FetchByIdRequest request);

	/**
* Fetch all inquilinos.
*
* @return the internal results response< inquilino>
*/
	public InternalResultsResponse<Inquilino> fetchAllInquilinos(Inquilino  inquilino);

	/**
* Fetch inquilinos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Inquilino> fetchInquilinosByRequest(InquilinoInquiryRequest request);


//===================================### AVISOS ####======================================
	/**

	/**
	 * Insert avisos.
	 *
* @param request the avisos maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Avisos> insertAvisos(AvisoMaintenanceRequest request);

	/**
* Update avisos.
*
* @param request the avisos maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Avisos> updateAvisos(AvisoMaintenanceRequest request);

	/**
* Delete avisos.
*
* @param request the avisos maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Avisos> deleteAvisos(AvisoMaintenanceRequest request);

	/**
* Refresh avisoss.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Avisos> refreshAvisos(RefreshRequest request);

	/**
* Fetch avisos by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Avisos> fetchAvisosById(FetchByIdRequest request);

	/**
* Fetch all avisoss.
*
* @return the internal results response< avisos>
*/
	public InternalResultsResponse<Avisos> fetchAllAvisoss(Avisos  avisos);

	/**
* Fetch avisoss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Avisos> fetchAvisosByRequest(AvisoInquiryRequest request);

}
