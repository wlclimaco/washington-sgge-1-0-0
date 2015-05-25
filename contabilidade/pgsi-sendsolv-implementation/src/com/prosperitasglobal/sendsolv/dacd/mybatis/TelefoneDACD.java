package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class TelefoneDACD extends SqlSessionDaoSupport
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
	public static void maintainTelefoneAssociations(List<Telefone> telefoneList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(telefoneList))
		{
			return count;
		}
		// For Each Contact...
		for (Telefone telefone : telefoneList)
		{
			// Make sure we set the parent key
			telefone.setParentId(parentId);

			if (ValidationUtil.isNull(telefone.getModelAction()))
			{
				continue;
			}
			switch (telefone.getModelAction())
			{
				case INSERT:
					count = getTelefoneDAC().insertTelefone(telefone,
							"insertTelefone", response);
					break;
				case UPDATE:
					count = getTelefoneDAC().updateTelefone(telefone, response);
					break;
				case DELETE:
					count = getTelefoneDAC().deleteTelefone(telefone, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Telefone missing!");
					}
					break;
			}
		}
		if (count > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, telefoneList, tabelaEnum);
		}

		return count;
	}
}
