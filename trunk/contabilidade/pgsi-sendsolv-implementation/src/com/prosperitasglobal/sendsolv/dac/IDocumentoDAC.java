package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Documento;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IDocumentoDAC.
 */
public interface IDocumentoDAC
{

	/**
	 * Update documento.
	 *
	 * @param documento the documento
	 * @return the internal results response< documento>
	 */
	public InternalResultsResponse<Documento> updateDocumento(Documento documento);

	/**
	 * Insert documento.
	 *
	 * @param documento the documento
	 * @return the internal results response< documento>
	 */
	public InternalResultsResponse<Documento> insertDocumento(Documento documento);

	/**
	 * Delete documento.
	 *
	 * @param documento the documento
	 * @return the internal response
	 */
	public InternalResponse deleteDocumento(Documento documento);

	/**
	 * Fetch documento by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Documento> fetchDocumentoById(FetchByIdRequest request);

	/**
	 * Fetch all documentos.
	 *
	 * @return the internal results response< documento>
	 */
	public InternalResultsResponse<Documento> fetchAllDocumentos();

	/**
	 * Fetch documento by request.
	 *
	 * @param request the request
	 * @return the internal results response< documento>
	 */
	public InternalResultsResponse<Documento> fetchDocumentoByRequest(PagedInquiryRequest request);

}
