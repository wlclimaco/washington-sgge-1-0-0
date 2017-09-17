/** create by system gera-java version 1.0.0 16/09/2017 19:49 : 5*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Advogado.IAdvocaciaBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.Compromisso;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class CompromissoBARD extends SqlSessionDaoSupport
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
	public static Integer maintainCompromissoAssociations(Compromisso compromisso,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IAdvocaciaBAR compromissoDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(compromisso))
		{
			return 0;
		}
		// For Each Contact...
			// Make sure we set the parent key
			compromisso.setParentId(parentId);
			compromisso.setTabelaEnum(tabelaEnum);
			compromisso.setProcessId(processId);

		//	if (ValidationUtil.isNull(compromisso.getModelAction()))
		//	{
		//		continue;
		//	}
			switch (compromisso.getModelAction())
			{
				case INSERT:
					count = compromissoDAC.insertCompromisso(compromisso).hasSystemError();

					break;
				case UPDATE:
					count = compromissoDAC.updateCompromisso(compromisso).hasSystemError();

					break;
				case DELETE:
					count = compromissoDAC.deleteCompromissoById(compromisso).hasSystemError();


					break;
			}

		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}
@SuppressWarnings("unchecked")
	public static Integer maintainCompromissoAssociations(List<Compromisso> compromissoList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IAdvocaciaBAR compromissoDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Compromisso
		if (ValidationUtil.isNullOrEmpty(compromissoList))
		{
			return 0;
		}
		// For Each Compromisso...
		for (Compromisso compromisso : compromissoList)
		{
			// Make sure we set the parent key
			compromisso.setParentId(parentId);
			compromisso.setTabelaEnum(tabelaEnum);
			compromisso.setProcessId(processId);

			if (ValidationUtil.isNull(compromisso.getModelAction()))
			{
				continue;
			}
         switch (compromisso.getModelAction())
			{
				case INSERT:
					count = compromissoDAC.insertCompromisso(compromisso).hasSystemError();

					break;
				case UPDATE:
					count = compromissoDAC.updateCompromisso(compromisso).hasSystemError();

					break;
				case DELETE:
					count = compromissoDAC.deleteCompromissoById(compromisso).hasSystemError();


					break;
			}
		}

		return 1;
	}
}
