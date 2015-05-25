package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class SociosDACD extends SqlSessionDaoSupport
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
	public static void maintainSocioAssociations(List<Socio> socioList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(socioList))
		{
			return count;
		}
		// For Each Contact...
		for (Socio socio : socioList)
		{
			// Make sure we set the parent key
			socio.setParentId(parentId);

			if (ValidationUtil.isNull(socio.getModelAction()))
			{
				continue;
			}
			switch (socio.getModelAction())
			{
				case INSERT:
					count = getSocioDAC().insertSocio(socio,
							"insertSocio", response);
					break;
				case UPDATE:
					count = getSocioDAC().updateSocio(socio, response);
					break;
				case DELETE:
					count = getSocioDAC().deleteSocio(socio, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Socio missing!");
					}
					break;
			}
		}
		if (count > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, socioList, tabelaEnum);
		}

		return count;
	}
}
