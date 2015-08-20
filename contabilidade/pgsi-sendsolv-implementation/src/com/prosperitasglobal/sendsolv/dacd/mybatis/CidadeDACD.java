package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.ICidadeDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum;
import com.prosperitasglobal.sendsolv.model.Cidade;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class CidadeDACD extends SqlSessionDaoSupport
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
	public static Integer maintainCidadeAssociations(Cidade cidadeList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ICidadeDAC CidadeDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNull(cidadeList))
		{
			return count;
		}

		// Make sure we set the parent key
		cidadeList.setParentId(parentId);
		cidadeList.setTabelaEnum(tabelaEnum);
		cidadeList.setProcessId(processId);

		switch (cidadeList.getModelAction())
		{
			case INSERT:
				count = CidadeDAC.insertCidade(cidadeList,
						"insertCidade", response);
				if (count > 0)
				{
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.ATIVO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, count, null,
									AcaoEnum.INSERT, UserId, empId, TabelaEnum.CIDADE, statusDAC, historicoDAC,
									processId, historicoId);
				}
				break;
			case UPDATE:
				count = CidadeDAC.updateCidade(cidadeList, response);
				if (count > 0)
				{
					count =
							StatusDACD.maintainStatusAssociations(cidadeList.getStatusList(), response,
									cidadeList.getId(),
									null,
									AcaoEnum.UPDATE, UserId, empId, TabelaEnum.CIDADE, statusDAC, historicoDAC,
									processId, historicoId);
				}
				break;
			case DELETE:

				count = CidadeDAC.deleteCidade(cidadeList, response);

				break;
		}

		return count;
	}
}
