package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class HistoricoDACD extends SqlSessionDaoSupport
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
	public static void maintainHistoricoAssociations(List<Status> statusList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(historicoList))
		{
			return count;
		}
		// For Each Contact...
		for (Historico historico : historicoList)
		{
			// Make sure we set the parent key
			historico.setParentId(parentId);

			if (ValidationUtil.isNull(historico.getModelAction()))
			{
				continue;
			}
			switch (historico.getModelAction())
			{
				case INSERT:
					count = getHistoricoDAC().insertHistorico(historico,
							"insertHistorico", response);
					break;
				case UPDATE:
					count = getHistoricoDAC().updateHistorico(historico, response);
					break;
				case DELETE:
					count = getHistoricoDAC().deleteHistorico(historico, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Historico missing!");
					}
					break;
			}
		}
		if (count > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, historicoList, tabelaEnum);
		}
		return count;
	}
}
