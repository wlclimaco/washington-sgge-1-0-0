package com.qat.samples.sysmgmt.entidade.dacd;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.Status;
import com.qat.samples.sysmgmt.util.TabelaEnum;
import com.qat.samples.sysmgmt.util.TypeEnum;
import com.qat.samples.sysmgmt.util.dac.ICidadeDAC;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;
import com.qat.samples.sysmgmt.util.dac.IStatusDAC;

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
