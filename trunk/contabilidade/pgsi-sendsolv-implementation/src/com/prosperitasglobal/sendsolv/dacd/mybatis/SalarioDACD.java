package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.ISalariosDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.Salario;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

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
			Integer empId, String UserId, Integer processId, Integer historiaId)
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
			switch (salario.getModelAction())
			{
				case INSERT:
					count = salarioDAC.insertSalario(salario,
							"insertSalario", response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(StatusEnum.ACTIVE);
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
					status.setStatus(StatusEnum.INACTIVE);
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
