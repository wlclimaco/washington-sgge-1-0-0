package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.Vendas.IVendasBAR;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalItens;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class NotaFiscalItensBARD extends SqlSessionDaoSupport
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
	public static Integer maintainNotaFiscalItensAssociations(List<NotaFiscalItens> cidadeList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IVendasBAR NotaFiscalItensDAC, IStatusBAR statusBAR, IHistoricoBAR historicoBAR,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(cidadeList))
		{
			return 0;
		}
		for (NotaFiscalItens contaCorrente : cidadeList)
		{
		// Make sure we set the parent key
		contaCorrente.setParentId(parentId);
		contaCorrente.setProcessId(processId);

		switch (contaCorrente.getModelAction())
		{
			case INSERT:
				count = NotaFiscalItensDAC.insertNotaFiscalItens(contaCorrente).hasSystemError();
				if (count == false)
				{
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.ATIVO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusBARD.maintainStatusAssociations(statusList, (InternalResultsResponse<?>) response, parentId, null,
									AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusBAR, historicoBAR,
									processId, historicoId);
				}
				break;
			case UPDATE:
				count = NotaFiscalItensDAC.updateNotaFiscalItens(contaCorrente).hasSystemError();
				if (count == true)
				{
					count =
							StatusBARD
									.maintainStatusAssociations(contaCorrente.getStatusList(), (InternalResultsResponse<?>) response,
											contaCorrente.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusBAR,
											historicoBAR, processId, historicoId);
				}
				break;
			case DELETE:
				count = NotaFiscalItensDAC.deleteNotaFiscalItensById(contaCorrente).hasSystemError();
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				count =
						StatusBARD.maintainStatusAssociations(statusList, (InternalResultsResponse<?>) response, contaCorrente.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusBAR, historicoBAR,
								processId, historicoId);

				break;
		}
		}
		return 1;

	}
}
