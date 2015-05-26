package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.ISalariosDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;

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
			Integer empId, String UserId)
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
						List<Status> statusList = new new ArrayList<Status>();
						count = StatusDACD.maintainStatusAssociations(statusList, response, count, null, AcaoEnum.INSERT, UserId, empId, TabelaEnum.SALARIO, statusDAC, historicoDAC);
					}
					break;
				case UPDATE:
					count = salarioDAC.updateSalario(salario, response);
					if (count > 0)
					{
						count = StatusDACD.maintainStatusAssociations(salario.getStatus(), response, salario.getId(), null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.SALARIO, statusDAC, historicoDAC);
					}
					break;
				case DELETE:
					Status status = new Status();
					status.setStatus(StatusEnum.INACTIVE);
					List<Status> statusList = new new ArrayList<Status>();
					count = StatusDACD.maintainStatusAssociations(salario.getStatus(), response, salario.getId(), null, AcaoEnum.DELETE, UserId, empId, TabelaEnum.SALARIO, statusDAC, historicoDAC);
					break;

			}
		}

		return count;
	}
}
