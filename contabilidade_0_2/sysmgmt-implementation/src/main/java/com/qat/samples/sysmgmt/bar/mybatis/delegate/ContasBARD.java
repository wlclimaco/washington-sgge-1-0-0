package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Financeiro.IFinanceiroBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.AcaoTypeEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Email;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ContasBARD extends SqlSessionDaoSupport
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
	public static Integer maintainContasPagarAssociations(ContasPagar cidadeList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IFinanceiroBAR ContasPagarDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
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
				countSucess = ContasPagarDAC.insertContasPagar(cidadeList).hasSystemError();
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
				countSucess = ContasPagarDAC.updateContasPagar(cidadeList).hasSystemError();;
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

				countSucess = ContasPagarDAC.deleteContasPagarById(cidadeList).hasSystemError();
				if (countSucess = true)
				{
					count = count + 1;
				}
				break;
		}

		return count;
	}



	@SuppressWarnings("unchecked")
	public static Integer maintainContasReceberAssociations(ContasReceber cidadeList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IFinanceiroBAR ContasReceberDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
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
				countSucess = ContasReceberDAC.insertContasReceber(cidadeList).hasSystemError();
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
				countSucess = ContasReceberDAC.updateContasReceber(cidadeList).hasSystemError();;
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

				countSucess = ContasReceberDAC.deleteContasReceberById(cidadeList).hasSystemError();
				if (countSucess = true)
				{
					count = count + 1;
				}
				break;
		}

		return count;
	}



	public static Integer maintainContaAssociationsA(List<Conta> contaList, InternalResponse response, Integer id,
			TypeEnum high, AcaoTypeEnum insert, TabelaEnum agencia, IFinanceiroBAR financeiroBAR, IStatusBAR statusBAR,
			IHistoricoBAR historicoBAR, Integer emprId, String userId, Integer historicoId, Integer historicoId2) {
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(contaList))
		{
			return 0;
		}
		// For Each Contact...
		for (Conta email : contaList)
		{
			// Make sure we set the parent key
			email.setParentId(id);
			email.setTabelaEnum(agencia);
			email.setProcessId(historicoId);

			if (ValidationUtil.isNull(email.getModelAction()))
			{
				continue;
			}
			switch (email.getModelAction())
			{
				case INSERT:
					count = financeiroBAR.insertConta(email).hasSystemError();

					break;
				case UPDATE:
					count = financeiroBAR.updateConta(email).hasSystemError();

					break;
				case DELETE:
					count = financeiroBAR.deleteContaById(email).hasSystemError();

					break;
			}
		}

		return 1;

	}
}
