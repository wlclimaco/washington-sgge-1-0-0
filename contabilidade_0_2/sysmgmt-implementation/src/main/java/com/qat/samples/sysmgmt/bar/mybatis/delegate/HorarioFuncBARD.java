package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Dp.IDpBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.dp.model.HorarioFunc;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class HorarioFuncBARD extends SqlSessionDaoSupport
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
	public static Integer maintainHorarioFuncAssociations(List<HorarioFunc> horarioFuncList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IDpBAR horarioFuncDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(horarioFuncList))
		{
			return 0;
		}
		// For Each Contact...
		for (HorarioFunc horarioFunc : horarioFuncList)
		{
			// Make sure we set the parent key
			horarioFunc.setParentId(parentId);
			horarioFunc.setTabelaEnum(tabelaEnum);

			if (ValidationUtil.isNull(horarioFunc.getModelAction()))
			{
				continue;
			}
			switch (horarioFunc.getModelAction())
			{
				case INSERT:
					count = horarioFuncDAC.insertHorafunc(horarioFunc).hasSystemError();

					break;
				case UPDATE:
					count = horarioFuncDAC.updateHorafunc(horarioFunc).hasSystemError();

					break;
				case DELETE:

					count = horarioFuncDAC.deleteHorafuncById(horarioFunc).hasSystemError();

					break;
			}
		}

		return 1;
	}
}
