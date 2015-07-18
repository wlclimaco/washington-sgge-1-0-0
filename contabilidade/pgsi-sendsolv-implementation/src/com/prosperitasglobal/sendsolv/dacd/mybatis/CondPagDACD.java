package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.ICondPagDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum;
import com.prosperitasglobal.sendsolv.model.CondPag;
import com.prosperitasglobal.sendsolv.model.CondPagPessoa;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TipoPag;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class CondPagDACD extends SqlSessionDaoSupport
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
	public static Integer maintainCondPagAssociations(List<CondPagPessoa> condPagList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ICondPagDAC condPagDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(condPagList))
		{
			return count;
		}
		// For Each Contact...
		for (CondPagPessoa condPag : condPagList)
		{
			// Make sure we set the parent key
			condPag.setParentId(parentId);
			condPag.setProcessId(processId);
			condPag.setTabelaEnum(tabelaEnum);

			if (ValidationUtil.isNull(condPag.getModelAction()))
			{
				continue;
			}
			switch (condPag.getModelAction())
			{
				case INSERT:
					count = condPagDAC.insertCondPagPessoa(condPag);
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
					count = condPagDAC.updateCondPagPessoa(condPag);
					if (count > 0)
					{
						count =
								StatusDACD
								.maintainStatusAssociations(condPag.getStatusList(), response, condPag.getId(),
										null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
										historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					count = condPagDAC.deleteCondPagPessoa(condPag);
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, condPag.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
				case NONE:
					count = maintainCondPagAssociationsA(condPag.getCondPagId(), response, null, null,
							null,
							TabelaEnum.PESSOA, condPagDAC, statusDAC, historicoDAC, condPag.getEmprId(),
							condPag.getCreateUser(), processId, historicoId);
					break;
			}
		}

		return count;
	}

	public static Integer maintainCondPagAssociationsA(CondPag condPag,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ICondPagDAC condPagDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{

		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNull(condPag))
		{
			return count;
		}

		// Make sure we set the parent key
		condPag.setParentId(parentId);
		condPag.setProcessId(processId);

		switch (condPag.getModelAction())
		{
			case INSERT:
				count = condPagDAC.insertCondPag(condPag);
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
				count = condPagDAC.updateCondPag(condPag);
				if (count > 0)
				{
					count =
							StatusDACD
							.maintainStatusAssociations(condPag.getStatusList(), response, condPag.getId(),
									null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
									historicoDAC, processId, historicoId);
				}
				break;
			case DELETE:
				count = condPagDAC.deleteCondPag(condPag);
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				count =
						StatusDACD.maintainStatusAssociations(statusList, response, condPag.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
								processId, historicoId);

				break;
			case NONE:
				count = maintainCondPagAssociationsTipo(condPag.getListTipoPag(), response, null, null,
						null,
						TabelaEnum.PESSOA, condPagDAC, statusDAC, historicoDAC, condPag.getEmprId(),
						condPag.getCreateUser(), processId, historicoId);
				break;
		}

		return count;
	}

	public static Integer maintainCondPagAssociationsTipo(List<TipoPag> condPagList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ICondPagDAC condPagDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{

		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(condPagList))
		{
			return count;
		}
		// For Each Contact...
		for (TipoPag condPag : condPagList)
		{

			// Make sure we set the parent key
			condPag.setParentId(parentId);
			condPag.setProcessId(processId);

			switch (condPag.getModelAction())
			{
				case INSERT:
					count = condPagDAC.insertTipoPag(condPag);
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
					count = condPagDAC.updateTipoPag(condPag);
					if (count > 0)
					{
						count =
								StatusDACD
								.maintainStatusAssociations(condPag.getStatusList(), response, condPag.getId(),
										null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
										historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					count = condPagDAC.deleteTipoPag(condPag);
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, condPag.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}
		}
		return count;
	}
}
