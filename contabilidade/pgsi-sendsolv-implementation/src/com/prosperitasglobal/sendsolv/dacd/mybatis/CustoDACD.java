package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.ICustoDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.Custo;
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
public final class CustoDACD extends SqlSessionDaoSupport
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
	public static Integer maintainCustoAssociations(List<Custo> custoList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ICustoDAC custoDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC, Integer empId,
			String UserId, Integer processId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(custoList))
		{
			return count;
		}
		// For Each Contact...
		for (Custo custo : custoList)
		{
			// Make sure we set the parent key
			custo.setParentId(parentId);

			if (ValidationUtil.isNull(custo.getModelAction()))
			{
				continue;
			}
			switch (custo.getModelAction())
			{
				case INSERT:
					count = custoDAC.insertCusto(custo,
							"insertCusto", response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(StatusEnum.ACTIVE);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.CUSTO, statusDAC, historicoDAC,
										processId, null);
					}
					break;
				case UPDATE:
					count = custoDAC.updateCusto(custo, response);
					if (count > 0)
					{
						count =
								StatusDACD
										.maintainStatusAssociations(custo.getStatusList(), response, custo.getId(),
												null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.CUSTO, statusDAC,
												historicoDAC, processId, null);
					}
					break;
				case DELETE:

					Status status = new Status();
					status.setStatus(StatusEnum.INACTIVE);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, custo.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.CUSTO, statusDAC, historicoDAC,
									processId, null);

					break;
			}
		}

		return count;
	}
}
