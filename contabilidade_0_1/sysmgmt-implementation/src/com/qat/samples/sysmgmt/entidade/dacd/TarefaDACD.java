package com.qat.samples.sysmgmt.entidade.dacd;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.entidade.TarefaEnt;
import com.qat.samples.sysmgmt.entidade.dac.ITarefaDAC;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.TabelaEnum;
import com.qat.samples.sysmgmt.util.Tarefa;
import com.qat.samples.sysmgmt.util.TypeEnum;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;
import com.qat.samples.sysmgmt.util.dac.IStatusDAC;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class TarefaDACD extends SqlSessionDaoSupport
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
	public static Integer maintainTarefaAssociations(List<TarefaEnt> tarefaList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ITarefaDAC tarefaDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// // First Maintain Empresa
		// if (ValidationUtil.isNullOrEmpty(tarefaList))
		// {
		// return count;
		// }
		// // For Each Contact...
		// for (TarefaEnt tarefa : tarefaList)
		// {
		// // Make sure we set the parent key
		// tarefa.setParentId(parentId);
		// tarefa.setProcessId(processId);
		// tarefa.setTabelaEnum(tabelaEnum);
		//
		// if (ValidationUtil.isNull(tarefa.getModelAction()))
		// {
		// continue;
		// }
		// switch (tarefa.getModelAction())
		// {
		// case INSERT:
		// count =
		// maintainTarefaAssociationsA(tarefa.getIdTarefa(), response, null, null,
		// null,
		// TabelaEnum.PESSOA, tarefaDAC, statusDAC, historicoDAC,
		// tarefa.getEmprId(),
		// tarefa.getCreateUser(), processId, historicoId);
		//
		// count = tarefaDAC.insertTarefaEnt(tarefa);
		// if (count > 0)
		// {
		// Status status = new Status();
		// status.setStatus(CdStatusTypeEnum.ATIVO);
		// List<Status> statusList = new ArrayList<Status>();
		// count =
		// StatusDACD.maintainStatusAssociations(statusList, response, count, null,
		// AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
		// processId, historicoId);
		// }
		// break;
		// case UPDATE:
		//
		// count =
		// maintainTarefaAssociationsA(tarefa.getIdTarefa(), response, null, null,
		// null,
		// TabelaEnum.PESSOA, tarefaDAC, statusDAC, historicoDAC,
		// tarefa.getEmprId(),
		// tarefa.getCreateUser(), processId, historicoId);
		//
		// count = tarefaDAC.updateTarefaEnt(tarefa);
		// if (count > 0)
		// {
		// count =
		// StatusDACD
		// .maintainStatusAssociations(tarefa.getStatusList(), response, tarefa.getId(),
		// null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
		// historicoDAC, processId, historicoId);
		// }
		// break;
		// case DELETE:
		// count = tarefaDAC.deleteTarefaEnt(tarefa);
		// Status status = new Status();
		// status.setStatus(CdStatusTypeEnum.DELETADO);
		// List<Status> statusList = new ArrayList<Status>();
		// count =
		// StatusDACD.maintainStatusAssociations(statusList, response, tarefa.getId(), null,
		// AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
		// processId, historicoId);
		//
		// break;
		// case NONE:
		// count = tarefaDAC.insertTarefaEnt(tarefa);
		// if (count > 0)
		// {
		// status = new Status();
		// status.setStatus(CdStatusTypeEnum.ATIVO);
		// statusList = new ArrayList<Status>();
		// count =
		// StatusDACD.maintainStatusAssociations(statusList, response, count, null,
		// AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
		// processId, historicoId);
		// }
		// break;
		// }
		// }

		return count;
	}

	public static Integer maintainTarefaAssociationsA(Tarefa tarefa,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ITarefaDAC tarefaDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{

		Integer count = 0;
		// // First Maintain Empresa
		// if (ValidationUtil.isNull(tarefa))
		// {
		// return count;
		// }
		//
		// // Make sure we set the parent key
		// tarefa.setParentId(parentId);
		// tarefa.setProcessId(processId);
		//
		// switch (tarefa.getModelAction())
		// {
		// case INSERT:
		// count = tarefaDAC.insertTarefa(tarefa);
		// if (count > 0)
		// {
		// Status status = new Status();
		// status.setStatus(CdStatusTypeEnum.ATIVO);
		// List<Status> statusList = new ArrayList<Status>();
		// count =
		// StatusDACD.maintainStatusAssociations(statusList, response, count, null,
		// AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
		// processId, historicoId);
		// }
		// break;
		// case UPDATE:
		// count = tarefaDAC.updateTarefa(tarefa);
		// if (count > 0)
		// {
		// count =
		// StatusDACD
		// .maintainStatusAssociations(tarefa.getStatusList(), response, tarefa.getId(),
		// null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
		// historicoDAC, processId, historicoId);
		// }
		// break;
		// case DELETE:
		// count = tarefaDAC.deleteTarefa(tarefa);
		// Status status = new Status();
		// status.setStatus(CdStatusTypeEnum.DELETADO);
		// List<Status> statusList = new ArrayList<Status>();
		// count =
		// StatusDACD.maintainStatusAssociations(statusList, response, tarefa.getId(), null,
		// AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
		// processId, historicoId);
		//
		// break;
		// }

		return count;
	}
}
