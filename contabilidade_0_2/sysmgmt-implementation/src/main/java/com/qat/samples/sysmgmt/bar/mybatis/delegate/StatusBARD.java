package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class StatusBARD extends SqlSessionDaoSupport
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
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Boolean  maintainStatusAssociations(List<Status> statusList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			String usuario,
			Integer empresa,
			TabelaEnum tabelaEnum, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer processId,
			Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if ((ValidationUtil.isNullOrEmpty(statusList)) || (ValidationUtil.isNull(statusList.get(0))))
		{
			return false;
		}

		// For Each Contact...
		for (Status status : statusList)
		{
			// Make sure we set the parent key
			status.setParentId(parentId);
			status.setTabelaEnum(tabelaEnum);
			status.setAcaoType(acaoType);
			status.setModelAction(PersistenceActionEnum.INSERT);

			HistoricoItens historicoItens = new HistoricoItens();

			if (ValidationUtil.isNull(status.getModelAction()))
			{
				continue;
			}
			switch (status.getModelAction())
			{
				case INSERT:
					count = statusDAC.insertStatus(status).hasSystemError();
					if (count = true)
					{

						if (!ValidationUtil.isNullOrZero(historicoId))
						{
							historicoItens = new HistoricoItens();
							historicoItens.setIdHist(historicoId);
							historicoItens.setProcessId(processId);
							historicoItens.setTabelaEnum(tabelaEnum);
							historicoItens.setParentId(parentId);
							historicoItens.setAcaoType(AcaoEnum.INSERT);
							historicoDAC.insertHistoricoItens(historicoItens);
						}
					}
					break;
				case UPDATE:
					count = statusDAC.updateStatus(status).hasSystemError();
					if (count == true)
					{
						if (!ValidationUtil.isNullOrZero(historicoId))
						{
							historicoItens = new HistoricoItens();
							historicoItens.setIdHist(historicoId);
							historicoItens.setProcessId(processId);
							historicoItens.setTabelaEnum(tabelaEnum);
							historicoItens.setAcaoType(AcaoEnum.UPDATE);
							historicoDAC.insertHistoricoItens(historicoItens);
						}
					}
					break;
				case DELETE:
					if (!ValidationUtil.isNullOrZero(historicoId))
					{
						historicoItens = new HistoricoItens();
						historicoItens.setIdHist(historicoId);
						historicoItens.setProcessId(processId);
						historicoItens.setTabelaEnum(tabelaEnum);
						historicoItens.setAcaoType(AcaoEnum.DELETE);
						historicoDAC.insertHistoricoItens(historicoItens);
					}

					break;
			}

		}
		return count;
	}
}
