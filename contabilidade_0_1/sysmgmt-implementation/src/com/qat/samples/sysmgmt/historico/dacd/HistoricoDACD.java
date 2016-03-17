package com.qat.samples.sysmgmt.historico.dacd;

import java.util.Date;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.TabelaEnum;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;

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
	public static Integer maintainHistoricoAssociations(Integer emprId, String userId, Integer processId,
			TabelaEnum tabela, IHistoricoDAC historicoDAC)
	{
		Integer count = 0;
		Date a = new Date();
		InternalResultsResponse<Historico> response = new InternalResultsResponse<Historico>();
		Historico historico = new Historico();
		historico.setEmprId(emprId);
		historico.setUserId(userId);
		historico.setProcessId(processId);
		historico.setTabelaEnum(tabela);
		historico.setCreateDataUTC(a.getTime());

		historicoDAC.insertHistorico(historico, "", response);

		return historico.getId();
	}

	public static Integer maintainHistoricoItensAssociations(Integer historicoId, Integer parentId, String userId,
			Integer processId,
			TabelaEnum tabela, AcaoEnum acao, IHistoricoDAC historicoDAC)
	{
		Integer count = 0;
		InternalResultsResponse<HistoricoItens> response = new InternalResultsResponse<HistoricoItens>();
		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(historicoId);
		historicoItens.setProcessId(historicoId);
		historicoItens.setParentId(parentId);
		historicoItens.setTabelaEnum(tabela);
		historicoItens.setAcaoType(acao);

		count =
				historicoDAC.insertHistoricoItens(historicoItens, "", response);

		return historicoItens.getId();
	}
}
