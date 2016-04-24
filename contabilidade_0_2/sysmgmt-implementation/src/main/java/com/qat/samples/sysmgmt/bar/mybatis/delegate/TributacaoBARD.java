package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.Tributacao.ITributacaoBAR;
import com.qat.samples.sysmgmt.fiscal.model.Tributacao;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class TributacaoBARD extends SqlSessionDaoSupport
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
	public static Integer maintainTributacaoAssociations(Tributacao tributacao,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ITributacaoBAR tributacaoDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
			Integer empId,
			String UserId, Integer processId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(tributacao))
		{
			return 0;
		}
		// Make sure we set the parent key
		tributacao.setParentId(parentId);

		switch (tributacao.getModelAction())
		{
			case INSERT:
				count = tributacaoDAC.insertTributacao(tributacao).hasSystemError();
				if (count == true)
				{
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.ATIVO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
									AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, null);
				}
				break;
			case UPDATE:
				count = tributacaoDAC.updateTributacao(tributacao).hasSystemError();
				if (count == true)
				{
					count =
							StatusBARD
									.maintainStatusAssociations(tributacao.getStatusList(), response,
											tributacao.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
											historicoDAC, processId, null);
				}
				break;
			case DELETE:
				count = tributacaoDAC.deleteTributacaoById(tributacao).hasSystemError();

				break;
		}

		return 1;
	}
}
