/** create by system gera-java version 1.0.0 05/12/2016 22:20 : 14*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Produto.IProdutoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.AcaoTypeEnum;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class UniMedBARD extends SqlSessionDaoSupport
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
	public static Integer maintainUniMedAssociations(UniMed uniMed,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoTypeEnum update,
			TabelaEnum tabelaEnum, IProdutoBAR uniMedDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(uniMed))
		{
			return 0;
		}
		// For Each Contact...
			// Make sure we set the parent key
			uniMed.setParentId(parentId);
			uniMed.setTabelaEnum(tabelaEnum);
			uniMed.setProcessId(processId);

		//	if (ValidationUtil.isNull(uniMed.getModelAction()))
		//	{
		//		continue;
		//	}
			switch (uniMed.getModelAction())
			{
				case INSERT:
					count = uniMedDAC.insertUniMed(uniMed).hasSystemError();

					break;
				case UPDATE:
					count = uniMedDAC.updateUniMed(uniMed).hasSystemError();

					break;
				case DELETE:
					count = uniMedDAC.deleteUniMedById(uniMed).hasSystemError();

					break;
			}

		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}
}
