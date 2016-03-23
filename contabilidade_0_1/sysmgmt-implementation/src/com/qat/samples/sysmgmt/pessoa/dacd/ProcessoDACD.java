package com.qat.samples.sysmgmt.pessoa.dacd;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.clinica.Exame;
import com.qat.samples.sysmgmt.clinica.ExamePessoa;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.pessoa.dac.IExameDAC;
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
public final class ProcessoDACD extends SqlSessionDaoSupport
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
	public static Integer maintainExameAssociations(List<ExamePessoa> exameList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IExameDAC exameDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(exameList))
		{
			return count;
		}
		// For Each Contact...
		for (ExamePessoa exame : exameList)
		{
			// Make sure we set the parent key
			exame.setParentId(parentId);
			exame.setProcessId(processId);
			exame.setTabelaEnum(tabelaEnum);

			if (ValidationUtil.isNull(exame.getModelAction()))
			{
				continue;
			}
			switch (exame.getModelAction())
			{
				case INSERT:
					count = exameDAC.insertExamePessoa(exame, null, null);
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
					count = exameDAC.updateExamePessoa(exame, null);
					if (count > 0)
					{
						count =
								StatusDACD
										.maintainStatusAssociations(exame.getStatusList(), response, exame.getId(),
												null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
												historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					count = exameDAC.deleteExamePessoa(exame, null);
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, exame.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
				case NONE:
					count =
							maintainExameAssociationsA(exame.getExame(), response, exame.getPaciente().getId(), null,
									AcaoEnum.INSERT, TabelaEnum.CONSULTA, exameDAC, statusDAC, historicoDAC, empId,
									UserId, processId, historicoId);
					break;
			}

		}

		return count;
	}

	public static Integer maintainExameAssociationsA(Exame exame2,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IExameDAC exameDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		if (ValidationUtil.isNull(exame2))
		{
			return count;
		}
		// For Each Contact...

		switch (exame2.getModelAction())
		{
			case INSERT:
				count = exameDAC.insertExame(exame2, null, null);
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
				count = exameDAC.updateExame(exame2, null);
				if (count > 0)
				{
					count =
							StatusDACD
									.maintainStatusAssociations(exame2.getStatusList(), response, exame2.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
											historicoDAC, processId, historicoId);
				}
				break;
			case DELETE:
				count = exameDAC.deleteExame(exame2, null);
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				count =
						StatusDACD.maintainStatusAssociations(statusList, response, exame2.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
								processId, historicoId);

				break;
		}

		return count;
	}
}
