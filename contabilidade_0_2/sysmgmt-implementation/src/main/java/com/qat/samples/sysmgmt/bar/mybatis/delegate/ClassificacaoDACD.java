package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Classificacao.IClassificacaoBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.fiscal.model.Classificacao;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ClassificacaoDACD extends SqlSessionDaoSupport
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
	public static Integer maintainClassificacaoAssociations(Classificacao classificacao,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IClassificacaoBAR classificacaoDAC, IStatusBAR statusDAC,
			IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId)
	{
		Boolean countSucess = false;
		Integer count = ZERO;
		// First Maintain Empresa
		if (ValidationUtil.isNull(classificacao))
		{
			return count;
		}
		// For Each Contact...
		// Make sure we set the parent key
		classificacao.setParentId(parentId);

		if (ValidationUtil.isNull(classificacao.getModelAction()))
		{
			return null;
		}
		switch (classificacao.getModelAction())
		{
			case INSERT:
				countSucess = classificacaoDAC.insertClassificacao(classificacao).hasSystemError();
				if (count > 0)
				{
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.ATIVO);
					List<Status> statusList = new ArrayList<Status>();
					countSucess =
							StatusDACD.maintainStatusAssociations(statusList, response, count, null,
									AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, null);
				}
				break;
			case UPDATE:
				countSucess = classificacaoDAC.updateClassificacao(classificacao).hasSystemError();
				if (count > 0)
				{
					countSucess =
							StatusDACD
									.maintainStatusAssociations(classificacao.getStatusList(), response,
											classificacao.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
											historicoDAC, processId, null);
				}
				break;
			case DELETE:

				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				countSucess =
						StatusDACD.maintainStatusAssociations(statusList, response, classificacao.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC, processId,
								null);

				break;
		}

		return count;
	}
}
