package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.DocumentType;
import com.prosperitasglobal.sendsolv.model.request.DocumentTypeRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IDocumentTypeDAC.
 */
public interface IDocumentTypeDAC
{

	/**
	 * Fetch document type by request.
	 *
	 * @param request the request
	 * @return the internal results response< document type>
	 */
	public InternalResultsResponse<DocumentType> fetchDocumentTypeByRequest(DocumentTypeRequest request);

}
