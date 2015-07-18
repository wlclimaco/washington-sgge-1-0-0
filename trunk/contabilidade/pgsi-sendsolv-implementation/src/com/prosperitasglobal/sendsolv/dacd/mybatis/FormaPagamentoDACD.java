package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IFormaPagamentoDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum;
import com.prosperitasglobal.sendsolv.model.FormaPg;
import com.prosperitasglobal.sendsolv.model.FormaPgPessoa;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class FormaPagamentoDACD extends SqlSessionDaoSupport
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
	public static Integer maintainFormaPgAssociations(List<FormaPgPessoa> formaPgList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IFormaPagamentoDAC formaPgDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(formaPgList))
		{
			return count;
		}
		// For Each Contact...
		for (FormaPgPessoa formaPg : formaPgList)
		{
			// Make sure we set the parent key
			formaPg.setParentId(parentId);
			formaPg.setProcessId(processId);

			if (ValidationUtil.isNull(formaPg.getModelAction()))
			{
				continue;
			}
			switch (formaPg.getModelAction())
			{
				case INSERT:
					count = formaPgDAC.insertFormaPgPessoa(formaPg);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
										processId, null);
					}
					break;
				case UPDATE:
					count = formaPgDAC.updateFormaPgPessoa(formaPg);
					if (count > 0)
					{
						count =
								StatusDACD
										.maintainStatusAssociations(formaPg.getStatusList(), response, formaPg.getId(),
												null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
												historicoDAC, processId, null);
					}
					break;
				case DELETE:
					count = formaPgDAC.deleteFormaPgPessoa(formaPg);
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, formaPg.getParentId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, null);

					break;
				case NONE:
					count = maintainBancoAssociationsA(formaPg.getFormaPgId(), response, null, null,
							null,
							TabelaEnum.PESSOA, formaPgDAC, statusDAC, historicoDAC, formaPg.getEmprId(),
							formaPg.getCreateUser(), processId, historicoId);
					break;
			}
		}

		return count;
	}

	public static Integer maintainBancoAssociationsA(FormaPg formaPg,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IFormaPagamentoDAC formaPgDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{

		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNull(formaPg))
		{
			return count;
		}

		// Make sure we set the parent key
		formaPg.setParentId(parentId);
		formaPg.setProcessId(processId);

		switch (formaPg.getModelAction())
		{
			case INSERT:
				count = formaPgDAC.insertFormaPg(formaPg);
				if (count > 0)
				{
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.ATIVO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, formaPg.getId(), null,
									AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, historicoId);
				}
				break;
			case UPDATE:
				count = formaPgDAC.updateFormaPg(formaPg);
				if (count > 0)
				{
					count =
							StatusDACD
									.maintainStatusAssociations(formaPg.getStatusList(), response, formaPg.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
											historicoDAC, processId, historicoId);
				}
				break;
			case DELETE:
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				count =
						StatusDACD.maintainStatusAssociations(statusList, response, formaPg.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
								processId, historicoId);

				break;
		}

		return count;
	}
}
