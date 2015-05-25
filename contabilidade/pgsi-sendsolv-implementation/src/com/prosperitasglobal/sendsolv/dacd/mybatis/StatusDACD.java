package com.prosperitasglobal.sendsolv.dacd.mybatis;


/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class StatusDACD extends SqlSessionDaoSupport
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
	public static void maintainStatusAssociations(List<Status> statusList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(statusList))
		{
			return count;
		}
		// For Each Contact...
		for (Status status : statusList)
		{
			// Make sure we set the parent key
			status.setParentId(parentId);

			if (ValidationUtil.isNull(status.getModelAction()))
			{
				continue;
			}
			switch (status.getModelAction())
			{
				case INSERT:
					count = getStatusDAC().insertStatus(status,
							"insertStatus", response);
					break;
				case UPDATE:
					count = getStatusDAC().updateStatus(status, response);
					break;
				case DELETE:
					count = getStatusDAC().deleteStatus(status, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Status missing!");
					}
					break;
			}
		}
		return count;
	}
}
