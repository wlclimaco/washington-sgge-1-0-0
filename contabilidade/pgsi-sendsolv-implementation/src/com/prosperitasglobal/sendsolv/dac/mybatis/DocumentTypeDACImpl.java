package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IDocumentTypeDAC;
import com.prosperitasglobal.sendsolv.model.DocumentType;
import com.prosperitasglobal.sendsolv.model.request.DocumentTypeRequest;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

/**
 * The Class DocumentTypeDACImpl.
 */
public class DocumentTypeDACImpl extends SqlSessionDaoSupport implements IDocumentTypeDAC
{
	/** The Constant DocumentType_NAMESPACE. */
	private static final String DOCUMENT_TYPE_NAMESPACE = "documentTypeMap.";

	private static final String DOCUMENT_TYPE_STMT_FETCH_DOC_TYPE_BY_REQUEST = DOCUMENT_TYPE_NAMESPACE
			+ "fetchDocumentTypeByRequest";

	@Override
	public InternalResultsResponse<DocumentType> fetchDocumentTypeByRequest(DocumentTypeRequest request)
	{
		InternalResultsResponse<DocumentType> response = new InternalResultsResponse<DocumentType>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), DOCUMENT_TYPE_STMT_FETCH_DOC_TYPE_BY_REQUEST, request,
				response);

		return response;
	}
}
