package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

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
	public static void maintainSalarioAssociations(List<Salario> salarioList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum)
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
					count = getSalarioDAC().insertSalario(salario,
							"insertSalario", response);
					break;
				case UPDATE:
					count = getSalarioDAC().updateSalario(salario, response);
					break;
				case DELETE:
					count = getSalarioDAC().deleteSalario(salario, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Salario missing!");
					}
					break;
			}
		}
		if (count > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, salarioList, tabelaEnum);
		}

		return count;
	}
}
