package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IContatoDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.Contato;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ContatoDACD extends SqlSessionDaoSupport
{
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
	public static Integer maintainContatoAssociations(List<Contato> contatoList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IContatoDAC contatoDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(contatoList))
		{
			return count;
		}
		// For Each Contact...
		for (Contato contato : contatoList)
		{
			// Make sure we set the parent key
			contato.setParentId(parentId);

			if (ValidationUtil.isNull(contato.getModelAction()))
			{
				continue;
			}
			switch (contato.getModelAction())
			{
				case INSERT:
					count = contatoDAC.insertContato(contato,
							"insertContato", response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(StatusEnum.ACTIVE);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.CONTATO, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = contatoDAC.updateContato(contato, response);
					if (count > 0)
					{
						count =
								StatusDACD.maintainStatusAssociations(contato.getStatusList(), response,
										contato.getId(), null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.CONTATO,
										statusDAC, historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:

					Status status = new Status();
					status.setStatus(StatusEnum.INACTIVE);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, contato.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.CONTATO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}
		}

		return count;
	}
}
