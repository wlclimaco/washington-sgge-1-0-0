/** create by system gera-java version 1.0.0 29/10/2017 19:27 : 27*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.Util.IDoisValorBAR;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.DoisValoresParent;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class DoisValoresParentBARD extends SqlSessionDaoSupport
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
	public static Integer maintainDoisValoresParentAssociations(DoisValoresParent doisValoresParent,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IDoisValorBAR doisValoresParentDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(doisValoresParent))
		{
			return 0;
		}
		// For Each Contact...
			// Make sure we set the parent key
			doisValoresParent.setParentId(parentId);
			doisValoresParent.setTabelaEnum(tabelaEnum);
			doisValoresParent.setProcessId(processId);

		//	if (ValidationUtil.isNull(doisValoresParent.getModelAction()))
		//	{
		//		continue;
		//	}
			switch (doisValoresParent.getModelAction())
			{
				case INSERT:
					count = doisValoresParentDAC.insertDoisValoresParent(doisValoresParent).hasSystemError();

					break;
				case UPDATE:
					count = doisValoresParentDAC.updateDoisValoresParent(doisValoresParent).hasSystemError();

					break;
				case DELETE:
					count = doisValoresParentDAC.deleteDoisValoresParentById(doisValoresParent).hasSystemError();


					break;
			}

		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}
@SuppressWarnings("unchecked")
	public static Integer maintainDoisValoresParentAssociations(List<DoisValoresParent> doisValoresParentList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IDoisValorBAR doisValoresParentDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain DoisValoresParent
		if (ValidationUtil.isNullOrEmpty(doisValoresParentList))
		{
			return 0;
		}
		// For Each DoisValoresParent...
		for (DoisValoresParent doisValoresParent : doisValoresParentList)
		{
			// Make sure we set the parent key
			doisValoresParent.setParentId(parentId);
			doisValoresParent.setTabelaEnum(tabelaEnum);
			doisValoresParent.setProcessId(processId);

			if (ValidationUtil.isNull(doisValoresParent.getModelAction()))
			{
				continue;
			}
         switch (doisValoresParent.getModelAction())
			{
				case INSERT:
					count = doisValoresParentDAC.insertDoisValoresParent(doisValoresParent).hasSystemError();

					break;
				case UPDATE:
					count = doisValoresParentDAC.updateDoisValoresParent(doisValoresParent).hasSystemError();

					break;
				case DELETE:
					count = doisValoresParentDAC.deleteDoisValoresParentById(doisValoresParent).hasSystemError();


					break;
			}
		}

		return 1;
	}
}
