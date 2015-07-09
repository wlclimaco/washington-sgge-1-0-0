package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.List;

import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class StatusDACD extends SqlSessionDaoSupport
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
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Integer maintainStatusAssociations(List<Status> statusList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			String usuario,
			Integer empresa,
			TabelaEnum tabelaEnum, IStatusDAC statusDAC, IHistoricoDAC historicoDAC, Integer processId,
			Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(statusList))
		{
			return count;
		}

		// For Each Contact...
		for (Status status : statusList)
		{
			// Make sure we set the parent key
			status.setParentId(parentId);

			status.setModelAction(PersistanceActionEnum.INSERT);

			HistoricoItens historicoItens = new HistoricoItens();

			if (ValidationUtil.isNull(status.getModelAction()))
			{
				continue;
			}
			switch (status.getModelAction())
			{
				case INSERT:
					count = statusDAC.insertStatus(status,
							"insertStatus", response);
					if (count > 0)
					{
						historicoItens = new HistoricoItens();
						historicoItens.setIdHist(historicoId);
						historicoItens.setProcessId(processId);
						historicoItens.setTabelaEnum(tabelaEnum);
						historicoItens.setAcaoType(status.getModelAction());
						historicoDAC.insertHistoricoItens(historicoItens, "insertHistorico", response);
					}
					break;
				case UPDATE:
					count = statusDAC.updateStatus(status, response);
					if (count > 0)
					{
						historicoItens = new HistoricoItens();
						historicoItens.setIdHist(historicoId);
						historicoItens.setProcessId(processId);
						historicoItens.setTabelaEnum(tabelaEnum);
						historicoItens.setAcaoType(status.getModelAction());
						historicoDAC.insertHistoricoItens(historicoItens, "insertHistorico", response);
					}
					break;
				case DELETE:
					historicoItens = new HistoricoItens();
					historicoItens.setIdHist(historicoId);
					historicoItens.setProcessId(processId);
					historicoItens.setTabelaEnum(tabelaEnum);
					historicoItens.setAcaoType(status.getModelAction());
					historicoDAC.insertHistoricoItens(historicoItens, "insertHistorico", response);

					break;
			}

		}

		if (response.isInError())
		{
			return 0;
		}
		return count;
	}
}
