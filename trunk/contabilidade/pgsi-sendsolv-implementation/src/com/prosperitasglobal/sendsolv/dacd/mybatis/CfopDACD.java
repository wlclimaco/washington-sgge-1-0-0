package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.ICfopDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum;
import com.prosperitasglobal.sendsolv.model.Cfop;
import com.prosperitasglobal.sendsolv.model.CfopPessoa;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class CfopDACD extends SqlSessionDaoSupport
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
	public static Integer maintainCfopAssociations(List<CfopPessoa> cfopList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ICfopDAC cfopDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(cfopList))
		{
			return count;
		}
		// For Each Contact...
		for (CfopPessoa cfop : cfopList)
		{
			// Make sure we set the parent key
			cfop.setParentId(parentId);
			cfop.setProcessId(processId);

			if (ValidationUtil.isNull(cfop.getModelAction()))
			{
				continue;
			}
			switch (cfop.getModelAction())
			{
				case INSERT:
					count = cfopDAC.insertCfopPessoa(cfop);
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
					count = cfopDAC.updateCfopPessoa(cfop);
					if (count > 0)
					{
						count =
								StatusDACD
										.maintainStatusAssociations(cfop.getStatusList(), response, cfop.getId(),
												null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
												historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					count = cfopDAC.deleteCfopPessoa(cfop);
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, cfop.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
				case NONE:
					count =
					maintainCfopAssociationsA(cfop.getIdCfop(), response, null, null,
							null,
							TabelaEnum.PESSOA, cfopDAC, statusDAC, historicoDAC,
							cfop.getEmprId(),
							cfop.getCreateUser(), processId, historicoId);
					break;
			}
		}

		return count;
	}

	public static Integer maintainCfopAssociationsA(Cfop cfop,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ICfopDAC cfopDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{

		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNull(cfop))
		{
			return count;
		}

		// Make sure we set the parent key
		cfop.setParentId(parentId);
		cfop.setProcessId(processId);

		switch (cfop.getModelAction())
		{
			case INSERT:
				count = cfopDAC.insertCfop(cfop);
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
				count = cfopDAC.updateCfop(cfop);
				if (count > 0)
				{
					count =
							StatusDACD
							.maintainStatusAssociations(cfop.getStatusList(), response, cfop.getId(),
									null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
									historicoDAC, processId, historicoId);
				}
				break;
			case DELETE:
				count = cfopDAC.deleteCfop(cfop);
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				count =
						StatusDACD.maintainStatusAssociations(statusList, response, cfop.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
								processId, historicoId);

				break;
		}

		return count;
	}
}
