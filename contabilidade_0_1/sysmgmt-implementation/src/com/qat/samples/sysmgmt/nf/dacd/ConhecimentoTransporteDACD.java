package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IConhecimentoTransporteDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum;
import com.prosperitasglobal.sendsolv.model.ConhecimentoTransporte;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ConhecimentoTransporteDACD extends SqlSessionDaoSupport
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
	public static Integer maintainConecimentoTransporteAssociations(ConhecimentoTransporte conhecimentoTransporte,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IConhecimentoTransporteDAC conecimentoTransporteDAC, IStatusDAC statusDAC,
			IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNull(conhecimentoTransporte))
		{
			return count;
		}
		else
		{
			// Make sure we set the parent key
			conhecimentoTransporte.setParentId(parentId);
			conhecimentoTransporte.setProcessId(processId);
			conhecimentoTransporte.setIdNota(parentId);

			switch (conhecimentoTransporte.getModelAction())
			{
				case INSERT:
					count = conecimentoTransporteDAC.insertConhecimentoTransporte(conhecimentoTransporte,
							"insertConhecimentoTransporte", response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.PROFISSAO, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = conecimentoTransporteDAC.updateConhecimentoTransporte(conhecimentoTransporte, response);
					if (count > 0)
					{
						count =
								StatusDACD.maintainStatusAssociations(conhecimentoTransporte.getStatusList(), response,
										conhecimentoTransporte.getId(),
										null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.PROFISSAO, statusDAC,
										historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:

					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, conhecimentoTransporte.getId(),
									null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.PROFISSAO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}
		}

		return count;
	}
}
