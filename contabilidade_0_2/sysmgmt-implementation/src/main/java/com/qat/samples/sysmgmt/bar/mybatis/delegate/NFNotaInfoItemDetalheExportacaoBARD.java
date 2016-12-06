/** create by system gera-java version 1.0.0 05/12/2016 22:20 : 14*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Nfe.INFNotaInfoItemBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemDetalheExportacao;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class NFNotaInfoItemDetalheExportacaoBARD extends SqlSessionDaoSupport
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
	public static Integer maintainNFNotaInfoItemDetalheExportacaoAssociations(List<NFNotaInfoItemDetalheExportacao> list,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, INFNotaInfoItemBAR nfnotainfoitemdetalheexportacaoDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(list))
		{
			return 0;
		}
		// For Each Contact...
		for (NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao : list)
		{
			// Make sure we set the parent key
			nfnotainfoitemdetalheexportacao.setParentId(parentId);
			nfnotainfoitemdetalheexportacao.setTabelaEnum(tabelaEnum);
			nfnotainfoitemdetalheexportacao.setProcessId(processId);

			if (ValidationUtil.isNull(nfnotainfoitemdetalheexportacao.getModelAction()))
			{
				continue;
			}
			switch (nfnotainfoitemdetalheexportacao.getModelAction())
			{
				case INSERT:
					count = nfnotainfoitemdetalheexportacaoDAC.insertNFNotaInfoItemDetalheExportacao(nfnotainfoitemdetalheexportacao).hasSystemError();

					break;
				case UPDATE:
					count = nfnotainfoitemdetalheexportacaoDAC.updateNFNotaInfoItemDetalheExportacao(nfnotainfoitemdetalheexportacao).hasSystemError();
					if (count == true)

					break;
				case DELETE:
					count = nfnotainfoitemdetalheexportacaoDAC.deleteNFNotaInfoItemDetalheExportacaoById(nfnotainfoitemdetalheexportacao).hasSystemError();

					break;
			}
		}
		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}
}
