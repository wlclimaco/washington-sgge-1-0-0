package com.qat.samples.sysmgmt.nf.dacd;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.conta.Conta;
import com.qat.samples.sysmgmt.nf.dac.IContasDAC;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.TabelaEnum;
import com.qat.samples.sysmgmt.util.TypeEnum;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;
import com.qat.samples.sysmgmt.util.dac.IStatusDAC;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ContasDACD extends SqlSessionDaoSupport
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
	public static Integer maintainContasAssociations(List<Conta> list,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IContasDAC contasDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(list))
		{
			return count;
		}
		// For Each Contact...
		for (Conta contas : list)
		{
			// Make sure we set the parent key
			contas.setParentId(parentId);
			contas.setProcessId(processId);

			if (ValidationUtil.isNull(contas.getModelAction()))
			{
				continue;
			}
			switch (contas.getModelAction())
			{
			// case INSERT:
			// count = contasDAC.insertContas(contas,
			// "insertContas", response);
			// if (count > 0)
			// {
			// Status status = new Status();
			// status.setStatus(CdStatusTypeEnum.ATIVO);
			// List<Status> statusList = new ArrayList<Status>();
			// count =
			// StatusDACD.maintainStatusAssociations(statusList, response, count, null,
			// AcaoEnum.INSERT, UserId, empId, TabelaEnum.PROFISSAO, statusDAC, historicoDAC,
			// processId, historicoId);
			// }
			// break;
			// case UPDATE:
			// count = contasDAC.updateContas(contas, response);
			// if (count > 0)
			// {
			// count =
			// StatusDACD.maintainStatusAssociations(contas.getStatusList(), response,
			// contas.getId(),
			// null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.PROFISSAO, statusDAC,
			// historicoDAC, processId, historicoId);
			// }
			// break;
			// case DELETE:
			// count = contasDAC.deleteContas(contas, response);
			// Status status = new Status();
			// status.setStatus(CdStatusTypeEnum.DELETADO);
			// List<Status> statusList = new ArrayList<Status>();
			// count =
			// StatusDACD.maintainStatusAssociations(statusList, response, contas.getId(), null,
			// AcaoEnum.DELETE, UserId, empId, TabelaEnum.PROFISSAO, statusDAC, historicoDAC,
			// processId, historicoId);
			//
			// break;
			}
		}

		return count;
	}
}
