package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.Date;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class InsertHistBARD extends SqlSessionDaoSupport
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
	public static Integer maintainInsertHistorico(TabelaEnum parentId,IHistoricoBAR historicoBAR,InternalResponse response)
	{
		Integer count = 0;
		if (!ValidationUtil.isNull(parentId))
		{
			Historico historico = new Historico();
			historico.setTabelaEnum(parentId);
			historico.setModifyDateUTC((new Date()).getTime());
			historico.setModifyUser("system");
			count = historicoBAR.insertHistorico(historico).getId();

		}

		return count;

	}
	public static Integer maintainInsertHistoricoItens(TabelaEnum tabela,AcaoEnum acao ,Integer historicoId,IHistoricoBAR historicoBAR,InternalResponse response,Integer parendId)
	{
		Integer count = 0;
		if (!ValidationUtil.isNullOrZero(historicoId))
		{
			HistoricoItens historico = new HistoricoItens();
			historico.setIdHist(historicoId);
			historico.setTabelaEnum(tabela);
			historico.setAcaoType(acao);
			historico.setParentId(parendId);
			historico.setModifyDateUTC((new Date()).getTime());
			historico.setModifyUser("system");
			count = historicoBAR.insertHistoricoItens(historico).getId();

		}

		return count;

	}
}
