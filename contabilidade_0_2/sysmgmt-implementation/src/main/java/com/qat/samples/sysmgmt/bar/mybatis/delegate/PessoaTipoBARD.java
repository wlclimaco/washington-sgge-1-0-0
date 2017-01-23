/** create by system gera-java version 1.0.0 01/09/2016 12:56 : 31*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Pessoa.IPessoaBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItem;
import com.qat.samples.sysmgmt.pessoa.model.PessoaTipo;
import com.qat.samples.sysmgmt.util.model.AcaoTypeEnum;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class PessoaTipoBARD extends SqlSessionDaoSupport
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
	public static Integer maintainPessoaTipoAssociations(List<PessoaTipo> lists,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoTypeEnum insert,
			TabelaEnum tabelaEnum, IPessoaBAR cofinsDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(lists))
		{
			return 0;
		}

		// For Each Contact...
		for (PessoaTipo cofins : lists)
		{
			// Make sure we set the parent key
			cofins.setParentId(parentId);
			cofins.setTabelaEnum(tabelaEnum);
			cofins.setProcessId(processId);

			switch (cofins.getModelAction())
			{
				case INSERT:
					count = cofinsDAC.insertTipoPessoa(cofins).hasSystemError();

					break;
				case UPDATE:
					count = cofinsDAC.updateTipoPessoa(cofins).hasSystemError();

					break;
				case DELETE:
					count = cofinsDAC.deleteTipoPessoaById(cofins).hasSystemError();

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
