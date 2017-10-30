/** create by system gera-java version 1.0.0 29/10/2017 19:27 : 27*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Advogado.IAdvocaciaBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.DiasHoras;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class DiasHorasBARD extends SqlSessionDaoSupport
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
	public static Integer maintainDiasHorasAssociations(DiasHoras diashoras,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IAdvocaciaBAR diashorasDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(diashoras))
		{
			return 0;
		}
		// For Each Contact...
			// Make sure we set the parent key
			diashoras.setParentId(parentId);
			diashoras.setTabelaEnum(tabelaEnum);
			diashoras.setProcessId(processId);

		//	if (ValidationUtil.isNull(diashoras.getModelAction()))
		//	{
		//		continue;
		//	}
			switch (diashoras.getModelAction())
			{
				case INSERT:
					count = diashorasDAC.insertDiasHoras(diashoras).hasSystemError();

					break;
				case UPDATE:
					count = diashorasDAC.updateDiasHoras(diashoras).hasSystemError();

					break;
				case DELETE:
					count = diashorasDAC.deleteDiasHorasById(diashoras).hasSystemError();


					break;
			}

		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}
@SuppressWarnings("unchecked")
	public static Integer maintainDiasHorasAssociations(List<DiasHoras> diashorasList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IAdvocaciaBAR diashorasDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain DiasHoras
		if (ValidationUtil.isNullOrEmpty(diashorasList))
		{
			return 0;
		}
		// For Each DiasHoras...
		for (DiasHoras diashoras : diashorasList)
		{
			// Make sure we set the parent key
			diashoras.setParentId(parentId);
			diashoras.setTabelaEnum(tabelaEnum);
			diashoras.setProcessId(processId);

			if (ValidationUtil.isNull(diashoras.getModelAction()))
			{
				continue;
			}
         switch (diashoras.getModelAction())
			{
				case INSERT:
					count = diashorasDAC.insertDiasHoras(diashoras).hasSystemError();

					break;
				case UPDATE:
					count = diashorasDAC.updateDiasHoras(diashoras).hasSystemError();

					break;
				case DELETE:
					count = diashorasDAC.deleteDiasHorasById(diashoras).hasSystemError();


					break;
			}
		}

		return 1;
	}
}
