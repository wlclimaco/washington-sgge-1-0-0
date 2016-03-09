package com.qat.samples.sysmgmt.pessoa.dacd;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.dp.EventoPessoa;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.pessoa.dac.IEventosDAC;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.Status;
import com.qat.samples.sysmgmt.util.TabelaEnum;
import com.qat.samples.sysmgmt.util.TypeEnum;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;
import com.qat.samples.sysmgmt.util.dac.IStatusDAC;

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
			evento.setIdFunc(parentId);

			if (ValidationUtil.isNull(evento.getModelAction()))
			{
				continue;
			}
			switch (evento.getModelAction())
			{
				case INSERT:
					count = eventoDac.insertEvento(evento.getIdEvent());
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
					count = eventoDac.updateEvento(evento.getIdEvent());
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

					count = eventoDac.insertEventoPessoa(evento);
					if (count > 0)
					{
						count =
								StatusDACD.maintainStatusAssociations(evento.getStatusList(), response, evento.getId(),
										null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.EVENTOS, statusDAC,
										historicoDAC, processId, HistoriId);
					}
					break;
			}
		}

		return count;
	}
}
