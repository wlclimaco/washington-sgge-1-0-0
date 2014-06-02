package com.qat.samples.sysmgmt.documento.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.documento.model.Documento;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IDocumentoDAC. (Data Access Component - DAC)
 */
public interface IDocumentoDAC
{

	/**
	 * Insert documento.
	 * 
	 * @param documento the documento
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
	public InternalResponse deleteDocumento(Documento documento);

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
	public List<Documento> fetchAllDocumentos();

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public Documento fetchDocumentoById(FetchByIdRequest request);

	/**
	 * Fetch documentos by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Documento> fetchDocumentosByRequest(PagedInquiryRequest request);

}
