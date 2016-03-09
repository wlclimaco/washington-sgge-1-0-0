package com.qat.samples.sysmgmt.pessoa.dacd;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.dp.Salario;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.pessoa.dac.IProfissaoDAC;
import com.qat.samples.sysmgmt.pessoa.dac.ISalariosDAC;
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
public final class SalarioDACD extends SqlSessionDaoSupport
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
	public static Integer maintainSalarioAssociations(List<Salario> salarioList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ISalariosDAC salarioDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historiaId, IProfissaoDAC profissaoDAC)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(salarioList))
		{
			return count;
		}
		// For Each Contact...
		for (Salario salario : salarioList)
		{
			// Make sure we set the parent key
			salario.setParentId(parentId);

			if (ValidationUtil.isNull(salario.getModelAction()))
			{
				continue;
			}
			ProfissaoDACD.maintainProfissaoAssociations(salario.getProfissao(), response, empId,
					null,
					null,
					null, profissaoDAC, statusDAC, historicoDAC, empId,
					UserId, processId, historiaId);
			switch (salario.getModelAction())
			{
				case INSERT:
					count = salarioDAC.insertSalario(salario,
							"insertSalario", response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.SALARIO, statusDAC, historicoDAC,
										processId, historiaId);
					}
					break;
				case UPDATE:
					count = salarioDAC.updateSalario(salario, response);
					if (count > 0)
					{
						count =
								StatusDACD.maintainStatusAssociations(salario.getStatusList(), response,
										salario.getId(), null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.SALARIO,
										statusDAC, historicoDAC, processId, historiaId);
					}
					break;
				case DELETE:
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, salario.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.SALARIO, statusDAC, historicoDAC,
									processId, historiaId);
					break;

			}

		}

		return count;
	}
}
