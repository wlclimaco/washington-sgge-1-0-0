package com.qat.samples.sysmgmt.pessoa.dacd;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.clinica.Consulta;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.pessoa.dac.IConsultaDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IExameDAC;
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
public final class ConsultaDACD extends SqlSessionDaoSupport
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
	public static Integer maintainConsultaAssociations(List<Consulta> consultaList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoTypeEnum insert,
			TabelaEnum tabelaEnum, IConsultaDAC consultaDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId, IExameDAC exameDAC)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(consultaList))
		{
			return count;
		}
		// For Each Contact...
		for (Consulta consulta : consultaList)
		{
			// Make sure we set the parent key
			consulta.setParentId(parentId);
			consulta.setProcessId(processId);
			consulta.setTabelaEnum(tabelaEnum);

			if (ValidationUtil.isNull(consulta.getModelAction()))
			{
				continue;
			}
			switch (consulta.getModelAction())
			{
				case INSERT:
					count = consultaDAC.insertConsulta(consulta);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, tabelaEnum, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = consultaDAC.updateConsulta(consulta);
					if (count > 0)
					{
						count =
								StatusDACD
										.maintainStatusAssociations(consulta.getStatusList(), response,
												consulta.getId(),
												null, AcaoEnum.UPDATE, UserId, empId, tabelaEnum, statusDAC,
												historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					count = consultaDAC.deleteConsulta(consulta);
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, consulta.getId(), null,
									AcaoEnum.DELETE, UserId, empId, tabelaEnum, statusDAC, historicoDAC,
									processId, historicoId);

					break;
				case NONE:
					count = 0;
					break;
			}

			ExameDACD.maintainExameAssociations(consulta.getExameList(), response, 0,
					null,
					null,
					tabelaEnum, exameDAC, statusDAC, historicoDAC, empId,
					consulta.getCreateUser(), processId, historicoId);
		}

		return count;
	}

}
