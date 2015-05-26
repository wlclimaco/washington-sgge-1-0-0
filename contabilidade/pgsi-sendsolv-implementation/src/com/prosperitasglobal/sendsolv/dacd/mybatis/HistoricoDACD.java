package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.Historico;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class HistoricoDACD extends SqlSessionDaoSupport
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
	public static Integer maintainHistoricoAssociations(List<Historico> historicoList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IHistoricoDAC historicoDAC, IStatusDAC statusDAC)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(historicoList))
		{
			return count;
		}
		// For Each Contact...
		for (Historico historico : historicoList)
		{
			// Make sure we set the parent key
			historico.setParentId(parentId);

			if (ValidationUtil.isNull(historico.getModelAction()))
			{
				continue;
			}
			switch (historico.getModelAction())
			{
				case INSERT:
					count = historicoDAC.insertHistorico(historico,
							"insertHistorico", response);

					break;
				case UPDATE:
					count = historicoDAC.updateHistorico(historico, response);

					break;
				case DELETE:
					count = historicoDAC.deleteHistorico(historico, response);

					break;

			}
		}

		return count;
	}
}
