package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class DocumentosDACD extends SqlSessionDaoSupport
{

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/**
	 * Fetch objects by request.
	 *
	 * @param sqlSession the sql session
	 * @param request the request
	 * @param countStatement the count statement
	 * @param fetchPagedStatement the fetch paged statement
	 * @param response the response
	 */
	@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	public static void maintainDocumentoAssociations(List<Documento> documentoList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(documentoList))
		{
			return count;
		}
		// For Each Contact...
		for (Documento documento : documentoList)
		{
			// Make sure we set the parent key
			documento.setParentId(parentId);

			if (ValidationUtil.isNull(documento.getModelAction()))
			{
				continue;
			}
			switch (documento.getModelAction())
			{
				case INSERT:
					count = getDocumentoDAC().insertDocumento(documento,
							"insertDocumento", response);
					break;
				case UPDATE:
					count = getDocumentoDAC().updateDocumento(documento, response);
					break;
				case DELETE:
					count = getDocumentoDAC().deleteDocumento(documento, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Documento missing!");
					}
					break;
			}
		}
		if (count > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, documentoList, tabelaEnum);
		}

		return count;
	}
}
