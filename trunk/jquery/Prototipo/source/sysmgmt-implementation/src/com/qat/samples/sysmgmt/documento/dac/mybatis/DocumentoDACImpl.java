package com.qat.samples.sysmgmt.documento.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.documento.dac.IDocumentoDAC;
import com.qat.samples.sysmgmt.documento.model.Documento;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Class DocumentoDACImpl. (Data Access Component - DAC)
 */
public class DocumentoDACImpl extends SqlSessionDaoSupport implements IDocumentoDAC
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "DocumentoMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertDocumento";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE = NAMESPACE + "updateDocumento";

	/** The Constant STMT_VERSION. */
	private static final String STMT_VERSION = NAMESPACE + "fetchVersionNumber";

	/** The Constant STMT_DELETE. */
	private static final String STMT_DELETE = NAMESPACE + "deleteDocumentoById";

	/** The Constant STMT_DELETE_ALL. */
	private static final String STMT_DELETE_ALL = NAMESPACE + "deleteAllDocumentos";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchDocumentoById";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllDocumentos";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchDocumentoRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllDocumentosRequest";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IDocumentoDAC#insertDocumento(com.qat.samples.sysmgmt.base.model.Documento)
	 */
	@Override
	public InternalResponse insertDocumento(Documento documento)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doInsert(getSqlSession(), STMT_INSERT, documento, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IDocumentoDAC#updateDocumento(com.qat.samples.sysmgmt.base.model.Documento)
	 */
	@Override
	public InternalResponse updateDocumento(Documento documento)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdateOL(getSqlSession(), STMT_UPDATE, documento, STMT_VERSION, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IDocumentoDAC#deleteDocumento(com.qat.samples.sysmgmt.base.model.Documento)
	 */
	@Override
	public InternalResponse deleteDocumento(Documento documento)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE, documento, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IDocumentoDAC#deleteAllDocumentos()
	 */
	@Override
	public InternalResponse deleteAllDocumentos()
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IDocumentoDAC#fetchDocumentoById
	 * (com.qat.samples.sysmgmt.base.model.Documento
	 * )
	 */
	@Override
	public Documento fetchDocumentoById(FetchByIdRequest request)
	{
		return (Documento)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IDocumentoDAC#fetchAllDocumentos()
	 */
	@Override
	public List<Documento> fetchAllDocumentos()
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dac.IDocumentoDAC#fetchDocumentosByRequest(com.qat.samples.sysmgmt.model.request.
	 * DocumentoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Documento> fetchDocumentosByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Documento> response = new InternalResultsResponse<Documento>();
		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST,
				response);
		return response;
	}
}
