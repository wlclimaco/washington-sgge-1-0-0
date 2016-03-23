package com.qat.samples.sysmgmt.produto.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.cfop.Cfop;
import com.qat.samples.sysmgmt.cfop.CfopPessoa;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.produto.dac.ICfopDAC;
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
					count = cfopDAC.insertCfopPessoa(cfop, null, null);
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
					count = cfopDAC.updateCfopPessoa(cfop, null);
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
					count = cfopDAC.deleteCfopPessoa(cfop, null);
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
				count = cfopDAC.insertCfop(cfop, null, null);
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
				count = cfopDAC.updateCfop(cfop, null);
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
				count = cfopDAC.deleteCfop(cfop, null);
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
