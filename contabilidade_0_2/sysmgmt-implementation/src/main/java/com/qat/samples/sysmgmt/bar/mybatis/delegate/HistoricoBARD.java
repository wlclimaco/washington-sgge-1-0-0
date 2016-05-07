package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class HistoricoBARD extends SqlSessionDaoSupport
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
			TabelaEnum tabelaEnum, IHistoricoBAR historicoDAC, IStatusBAR statusDAC)
	{
//		Boolean count = false;
//		// First Maintain Empresa
//		if (ValidationUtil.isNullOrEmpty(historicoList))
//		{
//			return 0;
//		}
//		// For Each Contact...
//		for (Historico historico : historicoList)
//		{
//			// Make sure we set the parent key
//			historico.setParentId(parentId);
//
//			if (ValidationUtil.isNull(historico.getModelAction()))
//			{
//				continue;
//			}
//			switch (historico.getModelAction())
//			{
//				case INSERT:
//					count = historicoDAC.insertHistorico(historico).hasSystemError();;
//
//					break;
//				case UPDATE:
//					count = historicoDAC.updateHistorico(historico).hasSystemError();;
//
//					break;
//				case DELETE:
//					count = historicoDAC.deleteHistoricoById(historico).hasSystemError();;
//
//					break;
//
//			}
//		}
//
//		return 1;
//	}
//
//	@SuppressWarnings("unchecked")
//	public static Integer inserthistoricoItens(Integer id, String userId, InternalResultsResponse<?> response,
//			TabelaEnum tabelaEnum, AcaoEnum acaoType, Integer historico, IHistoricoBAR historicoDAC)
//	{
//		HistoricoItens historicoItens = new HistoricoItens();
//		historicoItens.setIdHist(historico);
//		historicoItens.setProcessId(historico);
//		historicoItens.setParentId(id);
//		historicoItens.setTabelaEnum(tabelaEnum);
//		historicoItens.setAcaoType(acaoType);
//
//		historicoDAC.insertHistoricoItens(historicoItens);
//
//		return historicoItens.getId();
//	}
//
//	@SuppressWarnings("unchecked")
//	public static Integer inserthistorico(Integer id, Integer emprId, String userId,
//			InternalResultsResponse<?> response,
//			TabelaEnum tabelaEnum,
//			AcaoEnum acaoType, IHistoricoBAR historicoDAC)
//	{
//		Historico historico = new Historico();
//		historico.setParentId(id);
//		historico.setEmprId(emprId);
//		historico.setUserId(userId);
//		historico.setAcaoType(acaoType);
//		historico.setTabelaEnum(tabelaEnum);
//		historico.setProcessId(0);
//		Date a = new Date();
//		historico.setData(a.getTime());
//		historicoDAC.insertHistorico(historico);

		return 1;
	}
}
