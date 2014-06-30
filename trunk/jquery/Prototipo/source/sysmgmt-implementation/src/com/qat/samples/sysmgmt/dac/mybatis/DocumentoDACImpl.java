package com.qat.samples.sysmgmt.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dac.IDocumentoDAC;
import com.qat.samples.sysmgmt.documento.model.Documento;
import com.qat.samples.sysmgmt.model.response.InternalResponseLocal;

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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IDocumentoDAC#insertDocumento(com.qat.samples.sysmgmt.base.model.Documento)
	 */
	@Override
	public InternalResponseLocal insertDocumento(Documento documento)
	{
		InternalResponseLocal response = new InternalResponseLocal();

		Integer academiaId =
				(Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_INSERT, documento);
		response.setId(academiaId);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IDocumentoDAC#updateDocumento(com.qat.samples.sysmgmt.base.model.Documento)
	 */
	@Override
	public InternalResponseLocal updateDocumento(Documento documento)
	{
		InternalResponseLocal response = new InternalResponseLocal();

		Integer academiaId =
				(Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_UPDATE, documento);
		response.setId(academiaId);
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

}
