/** create by system gera-java version 1.0.0 01/09/2016 12:56 : 31*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Produto.IProdutoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.produto.model.Cofins;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.AcaoTypeEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class CofinsBARD extends SqlSessionDaoSupport
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
	public static Integer maintainCofinsAssociations(Cofins cofins,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoTypeEnum insert,
			TabelaEnum tabelaEnum, IProdutoBAR cofinsDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;

			// Make sure we set the parent key
			cofins.setParentId(parentId);
			cofins.setTabelaEnum(tabelaEnum);
			cofins.setProcessId(processId);

			switch (cofins.getModelAction())
			{
				case INSERT:
					count = cofinsDAC.insertCofins(cofins).hasSystemError();
					if (count == false)
					{
						HistoricoItens historicoItens = new HistoricoItens();
						historicoItens = new HistoricoItens();
						historicoItens.setIdHist(historicoId);
						historicoItens.setProcessId(processId);
						historicoItens.setTabelaEnum(tabelaEnum);
						historicoItens.setParentId(parentId);
						historicoItens.setAcaoType(AcaoEnum.INSERT);
						historicoDAC.insertHistoricoItens(historicoItens);
					}
					break;
				case UPDATE:
					count = cofinsDAC.updateCofins(cofins).hasSystemError();
					if (count == false)
					{
						HistoricoItens historicoItens = new HistoricoItens();
						historicoItens = new HistoricoItens();
						historicoItens.setIdHist(historicoId);
						historicoItens.setProcessId(processId);
						historicoItens.setTabelaEnum(tabelaEnum);
						historicoItens.setParentId(parentId);
						historicoItens.setAcaoType(AcaoEnum.UPDATE);
						historicoDAC.insertHistoricoItens(historicoItens);
					}
					break;
				case DELETE:
					count = cofinsDAC.deleteCofinsById(cofins).hasSystemError();
					if (count == false)
					{
						HistoricoItens historicoItens = new HistoricoItens();
						historicoItens = new HistoricoItens();
						historicoItens.setIdHist(historicoId);
						historicoItens.setProcessId(processId);
						historicoItens.setTabelaEnum(tabelaEnum);
						historicoItens.setParentId(parentId);
						historicoItens.setAcaoType(AcaoEnum.DELETE);
						historicoDAC.insertHistoricoItens(historicoItens);
					}
					break;
			}

		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}
}
