package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Clinica.IClinicaBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.clinica.model.Consulta;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ConsultaBARD extends SqlSessionDaoSupport
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
	public static Integer maintainConsultaAssociations(List<Consulta> consultaList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IClinicaBAR consultaDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(consultaList))
		{
			return 0;
		}
		// For Each Contact...
		for (Consulta consulta : consultaList)
		{
			// Make sure we set the parent key
			consulta.setParentId(parentId);
			consulta.setTabelaEnum(tabelaEnum);

			if (ValidationUtil.isNull(consulta.getModelAction()))
			{
				continue;
			}
			switch (consulta.getModelAction())
			{
				case INSERT:
					count = consultaDAC.insertConsulta(consulta).hasSystemError();

					break;
				case UPDATE:
					count = consultaDAC.updateConsulta(consulta).hasSystemError();

					break;
				case DELETE:

					count = consultaDAC.deleteConsultaById(consulta).hasSystemError();

					break;
			}
		}

		return 1;
	}
}
