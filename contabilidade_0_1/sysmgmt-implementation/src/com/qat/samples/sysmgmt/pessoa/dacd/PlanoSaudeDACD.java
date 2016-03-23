package com.qat.samples.sysmgmt.pessoa.dacd;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.clinica.PlanoSaude;
import com.qat.samples.sysmgmt.clinica.PlanoSaudePessoa;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.pessoa.dac.IPlanoSaudeDAC;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.AcaoTypeEnum;
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
public final class PlanoSaudeDACD extends SqlSessionDaoSupport
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
	public static Integer maintainPlanoSaudeAssociations(List<PlanoSaudePessoa> planoSaudeList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoTypeEnum update,
			TabelaEnum tabelaEnum, IPlanoSaudeDAC planoSaudeDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(planoSaudeList))
		{
			return count;
		}
		// For Each Contact...
		for (PlanoSaudePessoa planoSaude : planoSaudeList)
		{
			// Make sure we set the parent key
			planoSaude.setParentId(parentId);
			planoSaude.setProcessId(processId);
			planoSaude.setTabelaEnum(tabelaEnum);

			if (ValidationUtil.isNull(planoSaude.getModelAction()))
			{
				continue;
			}
			switch (planoSaude.getModelAction())
			{
				case INSERT:
					count = planoSaudeDAC.insertPlanoSaudePessoa(planoSaude, null, null);
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
					count = planoSaudeDAC.updatePlanoSaudePessoa(planoSaude, null);
					if (count > 0)
					{
						count =
								StatusDACD
										.maintainStatusAssociations(planoSaude.getStatusList(), response,
												planoSaude.getId(),
												null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
												historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					count = planoSaudeDAC.deletePlanoSaudePessoa(planoSaude, null);
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, planoSaude.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
				case NONE:
					count = maintainPlanoSaudeAssociationsA(planoSaude.getPlanoId(), response, null, null,
							null,
							TabelaEnum.PESSOA, planoSaudeDAC, statusDAC, historicoDAC, planoSaude.getEmprId(),
							planoSaude.getCreateUser(), processId, historicoId);
					break;
			}

		}

		return count;
	}

	public static Integer maintainPlanoSaudeAssociationsA(PlanoSaude planoSaude,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IPlanoSaudeDAC planoSaudeDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{

		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNull(planoSaude))
		{
			return count;
		}

		// Make sure we set the parent key
		planoSaude.setParentId(parentId);
		planoSaude.setProcessId(processId);

		switch (planoSaude.getModelAction())
		{
			case INSERT:
				count = planoSaudeDAC.insertPlanoSaude(planoSaude, null, null);
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
				count = planoSaudeDAC.updatePlanoSaude(planoSaude, null);
				if (count > 0)
				{
					count =
							StatusDACD
									.maintainStatusAssociations(planoSaude.getStatusList(), response,
											planoSaude.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
											historicoDAC, processId, historicoId);
				}
				break;
			case DELETE:
				count = planoSaudeDAC.deletePlanoSaude(planoSaude, null);
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				count =
						StatusDACD.maintainStatusAssociations(statusList, response, planoSaude.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
								processId, historicoId);

				break;
		}

		return count;
	}
}
