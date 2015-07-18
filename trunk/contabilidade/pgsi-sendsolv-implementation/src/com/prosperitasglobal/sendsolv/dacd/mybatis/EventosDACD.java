package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IEventosDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum;
import com.prosperitasglobal.sendsolv.model.EventoPessoa;
import com.prosperitasglobal.sendsolv.model.Eventos;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

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
	public static Integer maintainEventoPessoaAssociations(List<EventoPessoa> eventoList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IEventosDAC eventoDac, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer HistoriId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(eventoList))
		{
			return count;
		}
		// For Each Contact...
		for (EventoPessoa evento : eventoList)
		{
			// Make sure we set the parent key
			evento.setParentId(parentId);

			if (ValidationUtil.isNull(evento.getModelAction()))
			{
				continue;
			}
			switch (evento.getModelAction())
			{
				case INSERT:
					count = eventoDac.insertEventoPessoa(evento);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.EVENTOS, statusDAC, historicoDAC,
										processId, HistoriId);
					}

					break;
				case UPDATE:
					count = eventoDac.updateEventoPessoa(evento);
					if (count > 0)
					{
						count =
								StatusDACD.maintainStatusAssociations(evento.getStatusList(), response, evento.getId(),
										null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.EVENTOS, statusDAC,
										historicoDAC, processId, HistoriId);
					}
					break;
				case DELETE:

					count = eventoDac.deleteEventoPessoa(evento);
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, evento.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.EVENTOS, statusDAC, historicoDAC,
									processId, HistoriId);

					break;
				case NONE:
					count =
					maintainBancoAssociationsA(evento.getIdEvent(), response, parentId, null,
									null,
									TabelaEnum.PESSOA, eventoDac, statusDAC, historicoDAC,
									evento.getEmprId(),
									evento.getCreateUser(), processId, HistoriId);
					break;
			}
		}

		return count;
	}

	public static Integer maintainBancoAssociationsA(Eventos evento,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IEventosDAC eventoDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{

		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNull(evento))
		{
			return count;
		}

		// Make sure we set the parent key
		evento.setParentId(parentId);
		evento.setProcessId(processId);

		switch (evento.getModelAction())
		{
			case INSERT:
				count = eventoDAC.insertEvento(evento);
				if (count > 0)
				{
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.ATIVO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, count, null,
									AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, historicoId);
				}
				break;
			case UPDATE:
				count = eventoDAC.updateEvento(evento);
				if (count > 0)
				{
					count =
							StatusDACD
									.maintainStatusAssociations(evento.getStatusList(), response, evento.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
											historicoDAC, processId, historicoId);
				}
				break;
			case DELETE:
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				count =
						StatusDACD.maintainStatusAssociations(statusList, response, evento.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
								processId, historicoId);

				break;
		}

		return count;
	}
}
