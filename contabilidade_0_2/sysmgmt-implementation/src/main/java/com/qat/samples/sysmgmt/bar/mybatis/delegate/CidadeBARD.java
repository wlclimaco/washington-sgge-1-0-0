package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Cadastros.ICadastrosBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Cidade;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class CidadeBARD extends SqlSessionDaoSupport
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
			TabelaEnum tabelaEnum, ICadastrosBAR CidadeDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean countSucess = false;
		Integer count = ZERO;
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
				countSucess = CidadeDAC.insertCidade(cidadeList).hasSystemError();
				if (countSucess = true)
				{
					count = count + 1;
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.ATIVO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					countSucess =
							StatusBARD.maintainStatusAssociations(cidadeList.getStatusList(), response,
									cidadeList.getId(),
									null,
									AcaoEnum.INSERT, UserId, empId, TabelaEnum.CIDADE, statusDAC, historicoDAC,
									processId, historicoId);
				}
				break;
			case UPDATE:
				countSucess = CidadeDAC.updateCidade(cidadeList).hasSystemError();;
				if (countSucess = true)
				{
					count = count + 1;
					countSucess =
							StatusBARD.maintainStatusAssociations(cidadeList.getStatusList(), response,
									cidadeList.getId(),
									null,
									AcaoEnum.UPDATE, UserId, empId, TabelaEnum.CIDADE, statusDAC, historicoDAC,
									processId, historicoId);
				}
				break;
			case DELETE:

				countSucess = CidadeDAC.deleteCidadeById(cidadeList).hasSystemError();
				if (countSucess = true)
				{
					count = count + 1;
				}
				break;
		}

		return count;
	}
}
