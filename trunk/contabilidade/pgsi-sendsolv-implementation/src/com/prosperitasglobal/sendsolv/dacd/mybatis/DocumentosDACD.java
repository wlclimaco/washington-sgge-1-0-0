package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.dac.IDocumentoDAC;
import com.prosperitasglobal.sendsolv.dac.IEmailDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;

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
	public static Integer maintainDocumentoAssociations(List<Documento> documentoList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum,IDocumentoDAC documentoDAC,IStatusDAC statusDAC,IHistoricoDAC historicoDAC,Integer empId,String UserId)
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
					count = documentoDAC.insertDocumento(documento,
							"insertDocumento", response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(StatusEnum.ACTIVE);
						List<Status> statusList = new new ArrayList<Status>();
						count = StatusDACD.maintainStatusAssociations(statusList, response, count, null, AcaoEnum.INSERT , UserId, empId, TabelaEnum.DOCUMENTO, statusDAC, historicoDAC);
					}
					break;
				case UPDATE:
					count = documentoDAC.updateDocumento(documento, response);
					if (count > 0)
					{
						count = StatusDACD.maintainStatusAssociations(documento.getStatus(), response, documento.getId(), null, AcaoEnum.UPDATE , UserId, empId, TabelaEnum.DOCUMENTO, statusDAC, historicoDAC);
					}
					break;
				case DELETE:

						Status status = new Status();
						status.setStatus(StatusEnum.INACTIVE);
						List<Status> statusList = new new ArrayList<Status>();
						count = StatusDACD.maintainStatusAssociations(statusList, response, documento.getId(), null, AcaoEnum.DELETE , UserId, empId, TabelaEnum.DOCUMENTO, statusDAC, historicoDAC);

					break;
			}
		}


		return count;
	}
}
