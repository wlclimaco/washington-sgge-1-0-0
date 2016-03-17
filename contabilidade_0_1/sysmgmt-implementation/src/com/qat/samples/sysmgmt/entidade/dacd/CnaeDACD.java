package com.qat.samples.sysmgmt.entidade.dacd;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.cnae.Cnae;
import com.qat.samples.sysmgmt.cnae.CnaeEmpresa;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.Status;
import com.qat.samples.sysmgmt.util.TabelaEnum;
import com.qat.samples.sysmgmt.util.TypeEnum;
import com.qat.samples.sysmgmt.util.dac.ICnaeDAC;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;
import com.qat.samples.sysmgmt.util.dac.IStatusDAC;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class CnaeDACD extends SqlSessionDaoSupport
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
	public static Integer maintainCnaeAssociations(List<CnaeEmpresa> cnaeList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ICnaeDAC cnaeDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(cnaeList))
		{
			return count;
		}
		// For Each Contact...
		for (CnaeEmpresa cnae : cnaeList)
		{
			// Make sure we set the parent key
			cnae.setParentId(parentId);
			cnae.setProcessId(processId);
			cnae.setTabelaEnum(tabelaEnum);

			if (ValidationUtil.isNull(cnae.getModelAction()))
			{
				continue;
			}
			switch (cnae.getModelAction())
			{
				case INSERT:
					count =
							maintainCnaeAssociationsA(cnae.getIdCnae(), response, null, null,
									null,
									TabelaEnum.PESSOA, cnaeDAC, statusDAC, historicoDAC,
									cnae.getEmprId(),
									cnae.getCreateUser(), processId, historicoId);
									
					count = cnaeDAC.insertCnaeEmpresa(cnae);
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
					
					count =
							maintainCnaeAssociationsA(cnae.getIdCnae(), response, null, null,
									null,
									TabelaEnum.PESSOA, cnaeDAC, statusDAC, historicoDAC,
									cnae.getEmprId(),
									cnae.getCreateUser(), processId, historicoId);
									
					count = cnaeDAC.updateCnaeEmpresa(cnae);
					if (count > 0)
					{
						count =
								StatusDACD
										.maintainStatusAssociations(cnae.getStatusList(), response, cnae.getId(),
												null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
												historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					count = cnaeDAC.deleteCnaeEmpresa(cnae);
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, cnae.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
				case NONE:
					count = cnaeDAC.insertCnaeEmpresa(cnae);
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
			}
		}

		return count;
	}

	public static Integer maintainCnaeAssociationsA(Cnae cnae,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ICnaeDAC cnaeDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{

		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNull(cnae))
		{
			return count;
		}

		// Make sure we set the parent key
		cnae.setParentId(parentId);
		cnae.setProcessId(processId);

		switch (cnae.getModelAction())
		{
			case INSERT:
				count = cnaeDAC.insertCnae(cnae);
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
				count = cnaeDAC.updateCnae(cnae);
				if (count > 0)
				{
					count =
							StatusDACD
									.maintainStatusAssociations(cnae.getStatusList(), response, cnae.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
											historicoDAC, processId, historicoId);
				}
				break;
			case DELETE:
				count = cnaeDAC.deleteCnae(cnae);
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				count =
						StatusDACD.maintainStatusAssociations(statusList, response, cnae.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
								processId, historicoId);

				break;
		}

		return count;
	}
}
