package com.qat.samples.sysmgmt.bar.mybatis.Documentos;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Documentos.IDocumentoBAR;
import com.qat.samples.sysmgmt.util.model.Documento;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class DocumentoBARImpl extends SqlSessionDaoSupport implements IDocumentoBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### DOCUMENTO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_DOCUMENTO = "DocumentoMap.";

/** The Constant STMT_INSERT_DOCUMENTO. */
private static final String STMT_INSERT_DOCUMENTO = NAMESPACE_DOCUMENTO + "insertDocumento";

/** The Constant STMT_UPDATE_DOCUMENTO. */
private static final String STMT_UPDATE_DOCUMENTO = NAMESPACE_DOCUMENTO + "updateDocumento";

/** The Constant STMT_DELETE_DOCUMENTO. */
private static final String STMT_DELETE_DOCUMENTO = NAMESPACE_DOCUMENTO + "deleteDocumentoById";

	/** The Constant STMT_DELETE_DOCUMENTO_ALL. */
	private static final String STMT_DELETE_DOCUMENTO_ALL = NAMESPACE_DOCUMENTO + "deleteAllDocumentos";

	/** The Constant STMT_FETCH_DOCUMENTO. */
	private static final String STMT_FETCH_DOCUMENTO = NAMESPACE_DOCUMENTO + "fetchDocumentoById";

	/** The Constant STMT_FETCH_DOCUMENTO_ALL. */
	private static final String STMT_FETCH_DOCUMENTO_ALL = NAMESPACE_DOCUMENTO + "fetchAllDocumentos";

	/** The Constant STMT_FETCH_DOCUMENTO_COUNT. */
	private static final String STMT_FETCH_DOCUMENTO_COUNT = NAMESPACE_DOCUMENTO + "fetchDocumentoRowCount";

	/** The Constant STMT_FETCH_DOCUMENTO_ALL_REQUEST. */
	private static final String STMT_FETCH_DOCUMENTO_ALL_REQUEST = NAMESPACE_DOCUMENTO + "fetchAllDocumentosRequest";

//===================================### DOCUMENTO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDocumentoBAR#insertDocumento(com.qat.samples.sysmgmt.base.model.Documento)
 */
@Override
public InternalResponse insertDocumento(Documento documento)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_DOCUMENTO, documento, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDocumentoBAR#updateDocumento(com.qat.samples.sysmgmt.base.model.Documento)
 */
@Override
public InternalResponse updateDocumento(Documento documento)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_DOCUMENTO, documento, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDocumentoBAR#deleteDocumento(com.qat.samples.sysmgmt.base.model.Documento)
 */
@Override
public InternalResponse deleteDocumentoById(Documento documento)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_DOCUMENTO, documento, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDocumentoBAR#deleteAllDocumentos()
 */
@Override
public InternalResponse deleteAllDocumentos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_DOCUMENTO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IDocumentoBAR#fetchDocumentoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Documento fetchDocumentoById(FetchByIdRequest request)
{
return (Documento)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_DOCUMENTO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDocumentoBAR#fetchAllDocumentosCache()
 */
@Override
public InternalResultsResponse<Documento> fetchAllDocumentos(Documento documento)
{
	InternalResultsResponse<Documento> response = new InternalResultsResponse<Documento>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_DOCUMENTO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IDocumentoBAR#fetchDocumentosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Documento> fetchDocumentosByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<Documento> response = new InternalResultsResponse<Documento>();
	fetchDocumentosByRequest(getSqlSession(), request, STMT_FETCH_DOCUMENTO_COUNT, STMT_FETCH_DOCUMENTO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchDocumentosByRequest ####======================================

public static void fetchDocumentosByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

}
