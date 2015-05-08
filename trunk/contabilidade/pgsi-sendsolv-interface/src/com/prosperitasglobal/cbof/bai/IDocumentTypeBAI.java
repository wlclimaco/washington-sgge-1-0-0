package com.prosperitasglobal.cbof.bai;

import com.prosperitasglobal.sendsolv.model.request.DocumentTypeRequest;
import com.prosperitasglobal.sendsolv.model.response.DocumentTypeResponse;

/**
 * The Interface IDocumentTypeBAI.
 */
public interface IDocumentTypeBAI
{

	/**
	 * Fetch document type by request.
	 *
	 * @param request the request
	 * @return the document type response
	 */
	public DocumentTypeResponse fetchDocumentTypeByRequest(DocumentTypeRequest request);

}
