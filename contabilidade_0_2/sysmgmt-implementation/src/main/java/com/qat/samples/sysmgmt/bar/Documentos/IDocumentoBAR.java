package com.qat.samples.sysmgmt.bar.Documentos;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.util.model.Documento;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface DocumentoBAR.. (Data Access Component - DAC)
 */
public interface IDocumentoBAR
{

	/**
	 * Fetch documento by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Documento fetchDocumentoById(FetchByIdRequest request);

	/**
* Insert documento.
*
* @param documento the documento
*
* @return the internal response
*/
	public InternalResponse insertDocumento(Documento documento);

	/**
* Update documento.
*
* @param documento the documento
*
* @return the internal response
*/
	public InternalResponse updateDocumento(Documento documento);

	/**
* Delete documento.
*
* @param documento the documento
*
* @return the internal response
*/
	public InternalResponse deleteDocumentoById(Documento documento);

	/**
* Delete all documentos.
*
* @return the internal response
*/
	public InternalResponse deleteAllDocumentos();

	/**
* Fetch all documentos.
*
* @return the list< documento>
*/
	public InternalResultsResponse<Documento> fetchAllDocumentos(Documento  documento);

	/**
* Fetch documentos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Documento> fetchDocumentosByRequest(PagedInquiryRequest request);

}
