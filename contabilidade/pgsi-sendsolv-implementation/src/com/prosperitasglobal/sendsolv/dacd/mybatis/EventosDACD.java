package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.dac.IEventosDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class EventosDACD extends SqlSessionDaoSupport
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
	public static Integer maintainEventosAssociations(List<Eventos> cnaeList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IEventosDAC cnaeDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer HistoriId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(cnaeList))
		{
			return count;
		}
		// For Each Contact...
		for (Eventos cnae : cnaeList)
		{
			// Make sure we set the parent key
			cnae.setParentId(parentId);

			if (ValidationUtil.isNull(cnae.getModelAction()))
			{
				continue;
			}
			switch (cnae.getModelAction())
			{
				case INSERT:
					count = cnaeDAC.insertEvento(cnae,
							"insertEventos", response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(StatusEnum.ACTIVE);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.EVENTOS, statusDAC, historicoDAC,
										processId, HistoriId);
					}

					EventoPessoa eventoPessoa = new EventoPessoa();
					Date a = new Date();
					eventoPessoa.setData(a.getTime());
					eventoPessoa.setIdEvent(cnae.getId());
					eventoPessoa.setIdFunc(parentId);
					eventoPessoa.setProcessId(processId);

					count = cnaeDAC.insertEventoPessoa(eventoPessoa);

					break;
				case UPDATE:
					count = cnaeDAC.updateEvento(cnae, response);
					if (count > 0)
					{
						count =
								StatusDACD.maintainStatusAssociations(cnae.getStatusList(), response, cnae.getId(),
										null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.EVENTOS, statusDAC,
										historicoDAC, processId, HistoriId);
					}
					EventoPessoa eventoPessoa = new EventoPessoa();
					Date a = new Date();
					eventoPessoa.setData(a.getTime());
					eventoPessoa.setIdEvent(cnae.getId());
					eventoPessoa.setIdFunc(parentId);
					eventoPessoa.setProcessId(processId);

					count = cnaeDAC.updateEventoPessoa(eventoPessoa);
					break;
				case DELETE:

					Status status = new Status();
					status.setStatus(StatusEnum.INACTIVE);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, cnae.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.EVENTOS, statusDAC, historicoDAC,
									processId, HistoriId);

					EventoPessoa eventoPessoa = new EventoPessoa();
					Date a = new Date();
					eventoPessoa.setData(a.getTime());
					eventoPessoa.setIdEvent(cnae.getId());
					eventoPessoa.setIdFunc(parentId);
					eventoPessoa.setProcessId(processId);

					count = cnaeDAC.deleteEventoPessoa(eventoPessoa);
					break;
			}
		}

		return count;
	}
}
