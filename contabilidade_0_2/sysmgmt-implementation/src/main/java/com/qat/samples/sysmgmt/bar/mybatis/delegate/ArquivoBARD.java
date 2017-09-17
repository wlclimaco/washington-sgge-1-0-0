/** create by system gera-java version 1.0.0 16/09/2017 20:9 : 48*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.arquivo.model.Arquivo;
import com.qat.samples.sysmgmt.bar.Advogado.IAdvocaciaBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ArquivoBARD extends SqlSessionDaoSupport
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
	public static Integer maintainArquivoAssociations(Arquivo arquivo,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IAdvocaciaBAR arquivoDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(arquivo))
		{
			return 0;
		}
		// For Each Contact...
			// Make sure we set the parent key
			arquivo.setParentId(parentId);
			arquivo.setTabelaEnum(tabelaEnum);
			arquivo.setProcessId(processId);

		//	if (ValidationUtil.isNull(arquivo.getModelAction()))
		//	{
		//		continue;
		//	}
			switch (arquivo.getModelAction())
			{
				case INSERT:
					count = arquivoDAC.insertArquivo(arquivo).hasSystemError();

					break;
				case UPDATE:
					count = arquivoDAC.updateArquivo(arquivo).hasSystemError();

					break;
				case DELETE:
					count = arquivoDAC.deleteArquivoById(arquivo).hasSystemError();


					break;
			}

		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}
@SuppressWarnings("unchecked")
	public static Integer maintainArquivoAssociations(List<Arquivo> arquivoList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IAdvocaciaBAR arquivoDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Arquivo
		if (ValidationUtil.isNullOrEmpty(arquivoList))
		{
			return 0;
		}
		// For Each Arquivo...
		for (Arquivo arquivo : arquivoList)
		{
			// Make sure we set the parent key
			arquivo.setParentId(parentId);
			arquivo.setTabelaEnum(tabelaEnum);
			arquivo.setProcessId(processId);

			if (ValidationUtil.isNull(arquivo.getModelAction()))
			{
				continue;
			}
         switch (arquivo.getModelAction())
			{
				case INSERT:
					count = arquivoDAC.insertArquivo(arquivo).hasSystemError();

					break;
				case UPDATE:
					count = arquivoDAC.updateArquivo(arquivo).hasSystemError();

					break;
				case DELETE:
					count = arquivoDAC.deleteArquivoById(arquivo).hasSystemError();


					break;
			}
		}

		return 1;
	}
}
