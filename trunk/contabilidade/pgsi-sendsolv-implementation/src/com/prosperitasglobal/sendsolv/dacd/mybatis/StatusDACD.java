package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.Historico;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class StatusDACD extends SqlSessionDaoSupport
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
	public static Integer maintainStatusAssociations(List<Status> statusList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			String usuario,
			Integer empresa,
			TabelaEnum tabelaEnum, IStatusDAC statusDAC, IHistoricoDAC historicoDAC, Integer processId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(statusList))
		{
			return count;
		}
		Historico historico = new Historico();

		historico.setParentId(parentId);
		historico.setRegistro("");
		historico.setAcaoType(acaoType);
		historico.setTabelaEnum(tabelaEnum);
		historico.setStatusList(new ArrayList<Status>());
		historico.setUsuario(usuario);
		historico.setEmpresa(empresa);
		historico.setProcessId(processId);
		Status statuss = new Status();
		statuss.setStatus(StatusEnum.ACTIVE);
		historico.getStatusList().add(statuss);

		// For Each Contact...
		for (Status status : statusList)
		{
			// Make sure we set the parent key
			status.setParentId(parentId);

			status.setModelAction(PersistanceActionEnum.INSERT);

			if (ValidationUtil.isNull(status.getModelAction()))
			{
				continue;
			}
			switch (status.getModelAction())
			{
				case INSERT:
					count = statusDAC.insertStatus(status,
							"insertStatus", response);
					if (count > 0)
					{
						historicoDAC.insertHistorico(historico, "insertHistorico", response);
					}
					break;
				case UPDATE:
					count = statusDAC.updateStatus(status, response);
					if (count > 0)
					{
						historicoDAC.insertHistorico(historico, "insertHistorico", response);
					}
					break;
				case DELETE:
					historicoDAC.insertHistorico(historico,
							"insertHistorico", response);

					break;
			}
		}
		return count;
	}
}
