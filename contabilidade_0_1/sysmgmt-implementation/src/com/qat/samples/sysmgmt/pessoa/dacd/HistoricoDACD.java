package com.qat.samples.sysmgmt.pessoa.dacd;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.TabelaEnum;
import com.qat.samples.sysmgmt.util.TypeEnum;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;
import com.qat.samples.sysmgmt.util.dac.IStatusDAC;

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

	@SuppressWarnings("unchecked")
	public static Integer inserthistoricoItens(Integer id, String userId, InternalResultsResponse<?> response,
			TabelaEnum tabelaEnum, AcaoEnum acaoType, Integer historico, IHistoricoDAC historicoDAC)
	{
		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(historico);
		historicoItens.setProcessId(historico);
		historicoItens.setParentId(id);
		historicoItens.setTabelaEnum(tabelaEnum);
		historicoItens.setAcaoType(acaoType);

		historicoDAC.insertHistoricoItens(historicoItens,
				"insertHistorico", response);

		return historicoItens.getId();
	}

	@SuppressWarnings("unchecked")
	public static Integer inserthistorico(Integer id, Integer emprId, String userId,
			InternalResultsResponse<?> response,
			TabelaEnum tabelaEnum,
			AcaoEnum acaoType, IHistoricoDAC historicoDAC)
	{
		Historico historico = new Historico();
		historico.setParentId(id);
		historico.setEmprId(emprId);
		historico.setUserId(userId);
		historico.setAcaoType(acaoType);
		historico.setTabelaEnum(tabelaEnum);
		historico.setProcessId(0);
		Date a = new Date();
		historico.setData(a.getTime());
		historicoDAC.insertHistorico(historico,
				"insertHistorico", response);

		return historico.getId();
	}
}
