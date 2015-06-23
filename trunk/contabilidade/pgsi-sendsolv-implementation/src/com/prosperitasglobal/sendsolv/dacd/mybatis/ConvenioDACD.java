package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IConvenioDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.Convenio;
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
public final class ConvenioDACD extends SqlSessionDaoSupport
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
	public static Integer maintainConvenioAssociations(List<Convenio> convenioList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IConvenioDAC convenioDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(convenioList))
		{
			return count;
		}
		// For Each Contact...
		for (Convenio convenio : convenioList)
		{
			// Make sure we set the parent key
			convenio.setParentId(parentId);

			if (ValidationUtil.isNull(convenio.getModelAction()))
			{
				continue;
			}
			switch (convenio.getModelAction())
			{
				case INSERT:
					count = convenioDAC.insertConvenio(convenio,
							"insertConvenio", response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(StatusEnum.ACTIVE);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.CONVENIO, statusDAC, historicoDAC);
					}
					break;
				case UPDATE:
					count = convenioDAC.updateConvenio(convenio, response);
					if (count > 0)
					{
						count =
								StatusDACD.maintainStatusAssociations(convenio.getStatusList(), response,
										convenio.getId(),
										null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.CONVENIO, statusDAC,
										historicoDAC);
					}
					break;
				case DELETE:

					Status status = new Status();
					status.setStatus(StatusEnum.INACTIVE);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, convenio.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.CONVENIO, statusDAC, historicoDAC);

					break;
			}
		}

		return count;
	}
}
