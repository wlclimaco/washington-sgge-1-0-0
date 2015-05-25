package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

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
	public static void maintainCnaeAssociations(List<Cnae> cnaeList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(cnaeList))
		{
			return count;
		}
		// For Each Contact...
		for (Cnae cnae : cnaeList)
		{
			// Make sure we set the parent key
			cnae.setParentId(parentId);

			if (ValidationUtil.isNull(cnae.getModelAction()))
			{
				continue;
			}
			switch (cnae.getModelAction())
			{
				case INSERT:
					count = getCnaeDAC().insertCnae(cnae,
							"insertCnae", response);
					break;
				case UPDATE:
					count = getCnaeDAC().updateCnae(cnae, response);
					break;
				case DELETE:
					count = getCnaeDAC().deleteCnae(cnae, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Cnae missing!");
					}
					break;
			}
		}
		if (count > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, cnaeList, tabelaEnum);
		}

		return count;
	}
}
