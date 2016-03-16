package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.INotaFiscalItensDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.dac.ITributacaoDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum;
import com.prosperitasglobal.sendsolv.model.NotaFiscalItens;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class NotaFiscalItensDACD extends SqlSessionDaoSupport
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
	public static Integer maintainNotaFiscalItensAssociations(List<NotaFiscalItens> notaFiscalItensList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, INotaFiscalItensDAC notaFiscalItensDAC, IStatusDAC statusDAC,
			IHistoricoDAC historicoDAC, Integer empId,
			String UserId, Integer processId, ITributacaoDAC tributacaoDAC)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(notaFiscalItensList))
		{
			return count;
		}
		// For Each Contact...
		for (NotaFiscalItens notaFiscalItens : notaFiscalItensList)
		{
			// Make sure we set the parent key
			notaFiscalItens.setParentId(parentId);

			if (ValidationUtil.isNull(notaFiscalItens.getModelAction()))
			{
				continue;
			}
			switch (notaFiscalItens.getModelAction())
			{
				case INSERT:
					count =
					notaFiscalItensDAC
					.insertNotaFiscalItens(notaFiscalItens, "insertNotaFiscalItens", response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.CNAE, statusDAC, historicoDAC,
										processId, null);
					}
					break;
				case UPDATE:
					count = notaFiscalItensDAC.updateNotaFiscalItens(notaFiscalItens, response);
					if (count > 0)
					{
						count =
								StatusDACD.maintainStatusAssociations(notaFiscalItens.getStatusList(), response,
										notaFiscalItens.getId(),
										null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.CNAE, statusDAC, historicoDAC,
										processId, null);
					}
					break;
				case DELETE:
					count = notaFiscalItensDAC.deleteNotaFiscalItens(notaFiscalItens, response);
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD
							.maintainStatusAssociations(statusList, response, notaFiscalItens.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.CNAE, statusDAC, historicoDAC,
									processId, null);

					break;
			}
			// Tributacao
			count +=
					TributacaoDACD.maintainTributacaoAssociations(notaFiscalItens.getTributosList().get(0), response,
							notaFiscalItens.getId(),
							null,
							null,
							null, tributacaoDAC, statusDAC, historicoDAC, notaFiscalItens.getEmprId(),
							notaFiscalItens.getCreateUser(), processId);
		}

		return count;
	}
}
