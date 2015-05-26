package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.dac.IEmailDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.ISociosDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;

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
	public static Integer maintainSocioAssociations(List<Socio> socioList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum,ISociosDAC socioDAC,IStatusDAC statusDAC,IHistoricoDAC historicoDAC,Integer empId,String UserId)
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
					count = socioDAC.insertSocio(socio,
							"insertSocio", response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(StatusEnum.ACTIVE);
						List<Status> statusList = new new ArrayList<Status>();
						count = StatusDACD.maintainStatusAssociations(statusList, response, count, null, AcaoEnum.INSERT , UserId, empId, TabelaEnum.SOCIO, statusDAC, historicoDAC);
					}
					break;
				case UPDATE:
					count = socioDAC.updateSocio(socio, response);
					if (count > 0)
					{
						count = StatusDACD.maintainStatusAssociations(socio.getStatus(), response, socio.getId(), null, AcaoEnum.UPDATE , UserId, empId, TabelaEnum.SOCIO, statusDAC, historicoDAC);
					}
					break;
				case DELETE:

						Status status = new Status();
						status.setStatus(StatusEnum.ACTIVE);
						List<Status> statusList = new new ArrayList<Status>();
						count = StatusDACD.maintainStatusAssociations(statusList, response, socio.getId(), null, AcaoEnum.DELETE , UserId, empId, TabelaEnum.SOCIO, statusDAC, historicoDAC);

					break;
			}
		}
		return count;
	}
}
