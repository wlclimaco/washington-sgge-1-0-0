package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class EmailDACD extends SqlSessionDaoSupport
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
	public static void maintainEmailAssociations(List<Email> emailList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(emailList))
		{
			return count;
		}
		// For Each Contact...
		for (Email email : emailList)
		{
			// Make sure we set the parent key
			email.setParentId(parentId);

			if (ValidationUtil.isNull(email.getModelAction()))
			{
				continue;
			}
			switch (email.getModelAction())
			{
				case INSERT:
					count = getEmailDAC().insertEmail(email,
							"insertEmail", response);
					break;
				case UPDATE:
					count = getEmailDAC().updateEmail(email, response);
					break;
				case DELETE:
					count = getEmailDAC().deleteEmail(email, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Email missing!");
					}
					break;
			}
		}
		if (count > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, emailList, tabelaEnum);
		}

		return count;
	}
}
