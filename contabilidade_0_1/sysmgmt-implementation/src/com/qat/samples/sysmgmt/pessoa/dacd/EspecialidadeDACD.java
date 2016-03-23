package com.qat.samples.sysmgmt.pessoa.dacd;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.clinica.Especialidade;
import com.qat.samples.sysmgmt.clinica.EspecialidadePessoa;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.pessoa.dac.IEspecialidadeDAC;
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
public final class EspecialidadeDACD extends SqlSessionDaoSupport
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
	public static Integer maintainEspecialidadeAssociations(List<EspecialidadePessoa> especialidadeList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoTypeEnum insert,
			TabelaEnum tabelaEnum, IEspecialidadeDAC especialidadeDAC, IStatusDAC statusDAC,
			IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(especialidadeList))
		{
			return count;
		}
		// For Each Contact...
		for (EspecialidadePessoa especialidade : especialidadeList)
		{
			// Make sure we set the parent key
			especialidade.setParentId(parentId);
			especialidade.setProcessId(processId);
			especialidade.setTabelaEnum(tabelaEnum);

			if (ValidationUtil.isNull(especialidade.getModelAction()))
			{
				continue;
			}
			switch (especialidade.getModelAction())
			{
				case INSERT:
					count = especialidadeDAC.insertEspecialidadePessoa(especialidade, null, null);
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
					count = especialidadeDAC.updateEspecialidadePessoa(especialidade, null);
					if (count > 0)
					{
						count =
								StatusDACD
										.maintainStatusAssociations(especialidade.getStatusList(), response,
												especialidade.getId(),
												null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
												historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					count = especialidadeDAC.deleteEspecialidadePessoa(especialidade, null);
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, especialidade.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
				case NONE:
					count = maintainEspecialidadeAssociationsA(especialidade.getEspecialidade(), response, null, null,
							null,
							TabelaEnum.PESSOA, especialidadeDAC, statusDAC, historicoDAC, especialidade.getEmprId(),
							especialidade.getCreateUser(), processId, historicoId);
					break;
			}
		}

		return count;
	}

	public static Integer maintainEspecialidadeAssociationsA(Especialidade especialidade,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IEspecialidadeDAC especialidadeDAC, IStatusDAC statusDAC,
			IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{

		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNull(especialidade))
		{
			return count;
		}

		// Make sure we set the parent key
		especialidade.setParentId(parentId);
		especialidade.setProcessId(processId);

		switch (especialidade.getModelAction())
		{
			case INSERT:
				count = especialidadeDAC.insertEspecialidade(especialidade, null, null);
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
				count = especialidadeDAC.updateEspecialidade(especialidade, null);
				if (count > 0)
				{
					count =
							StatusDACD
									.maintainStatusAssociations(especialidade.getStatusList(), response,
											especialidade.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
											historicoDAC, processId, historicoId);
				}
				break;
			case DELETE:
				count = especialidadeDAC.deleteEspecialidade(especialidade, null);
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				count =
						StatusDACD.maintainStatusAssociations(statusList, response, especialidade.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
								processId, historicoId);

				break;
		}

		return count;
	}
}
