package com.qat.samples.sysmgmt.bar.Condominio;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.condominio.model.Avisos;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;
import com.qat.samples.sysmgmt.condominio.model.Sindico;
import com.qat.samples.sysmgmt.condominio.model.request.AvisoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface CondominioBAR.. (Data Access Component - DAC)
 */
public interface ICondominioBAR
{

	/**
	 * Fetch sindico by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Sindico> fetchSindicoById(FetchByIdRequest request);

	/**
* Insert sindico.
*
* @param sindico the sindico
*
* @return the internal response
*/
	public InternalResponse insertSindico(Sindico sindico);

	/**
* Update sindico.
*
* @param sindico the sindico
*
* @return the internal response
*/
	public InternalResponse updateSindico(Sindico sindico);

	/**
* Delete sindico.
*
* @param sindico the sindico
*
* @return the internal response
*/
	public InternalResponse deleteSindicoById(Sindico sindico);

	/**
* Delete all sindicos.
*
* @return the internal response
*/
	public InternalResponse deleteAllSindicos();

	/**
* Fetch all sindicos.
*
* @return the list< sindico>
*/
	public InternalResultsResponse<Sindico> fetchAllSindicos(Sindico  sindico);

	/**
* Fetch sindicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Sindico> fetchSindicosByRequest(SindicoInquiryRequest request);

	/**
	 * Fetch inquilino by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Inquilino> fetchInquilinoById(FetchByIdRequest request);

	/**
* Insert inquilino.
*
* @param inquilino the inquilino
*
* @return the internal response
*/
	public InternalResponse insertInquilino(Inquilino inquilino);

	/**
* Update inquilino.
*
* @param inquilino the inquilino
*
* @return the internal response
*/
	public InternalResponse updateInquilino(Inquilino inquilino);

	/**
* Delete inquilino.
*
* @param inquilino the inquilino
*
* @return the internal response
*/
	public InternalResponse deleteInquilinoById(Inquilino inquilino);

	/**
* Delete all inquilinos.
*
* @return the internal response
*/
	public InternalResponse deleteAllInquilinos();

	/**
* Fetch all inquilinos.
*
* @return the list< inquilino>
*/
	public InternalResultsResponse<Inquilino> fetchAllInquilinos(Inquilino  inquilino);

	/**
* Fetch inquilinos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Inquilino> fetchInquilinosByRequest(InquilinoInquiryRequest request);

	/**
	 * Fetch avisos by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Avisos> fetchAvisosById(FetchByIdRequest request);

	/**
* Insert avisos.
*
* @param avisos the avisos
*
* @return the internal response
*/
	public InternalResponse insertAvisos(Avisos avisos);

	/**
* Update avisos.
*
* @param avisos the avisos
*
* @return the internal response
*/
	public InternalResponse updateAvisos(Avisos avisos);

	/**
* Delete avisos.
*
* @param avisos the avisos
*
* @return the internal response
*/
	public InternalResponse deleteAvisosById(Avisos avisos);

	/**
* Delete all avisoss.
*
* @return the internal response
*/
	public InternalResponse deleteAllAvisoss();

	/**
* Fetch all avisoss.
*
* @return the list< avisos>
*/
	public InternalResultsResponse<Avisos> fetchAllAvisos(Avisos  avisos);

	/**
* Fetch avisoss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Avisos> fetchAvisosByRequest(Avisos request);

	public InternalResultsResponse<Avisos> fetchAvisosByRequest(AvisoInquiryRequest request);

}
