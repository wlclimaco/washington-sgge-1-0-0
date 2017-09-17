/** create by system gera-java version 1.0.0 16/09/2017 21:20 : 49*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.advocacia.ProcessoUsuarios;
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
public final class ProcessoUsuariosBARD extends SqlSessionDaoSupport
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
	public static Integer maintainProcessoUsuariosAssociations(ProcessoUsuarios processousuarios,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IAdvocaciaBAR processousuariosDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(processousuarios))
		{
			return 0;
		}
		// For Each Contact...
			// Make sure we set the parent key
			processousuarios.setParentId(parentId);
			processousuarios.setTabelaEnum(tabelaEnum);
			processousuarios.setProcessId(processId);

		//	if (ValidationUtil.isNull(processousuarios.getModelAction()))
		//	{
		//		continue;
		//	}
			switch (processousuarios.getModelAction())
			{
				case INSERT:
					count = processousuariosDAC.insertProcessoUsuarios(processousuarios).hasSystemError();

					break;
				case UPDATE:
					count = processousuariosDAC.updateProcessoUsuarios(processousuarios).hasSystemError();

					break;
				case DELETE:
					count = processousuariosDAC.deleteProcessoUsuariosById(processousuarios).hasSystemError();


					break;
			}

		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}
@SuppressWarnings("unchecked")
	public static Integer maintainProcessoUsuariosAssociations(List<ProcessoUsuarios> processousuariosList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IAdvocaciaBAR processousuariosDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain ProcessoUsuarios
		if (ValidationUtil.isNullOrEmpty(processousuariosList))
		{
			return 0;
		}
		// For Each ProcessoUsuarios...
		for (ProcessoUsuarios processousuarios : processousuariosList)
		{
			// Make sure we set the parent key
			processousuarios.setParentId(parentId);
			processousuarios.setTabelaEnum(tabelaEnum);
			processousuarios.setProcessId(processId);

			if (ValidationUtil.isNull(processousuarios.getModelAction()))
			{
				continue;
			}
         switch (processousuarios.getModelAction())
			{
				case INSERT:
					count = processousuariosDAC.insertProcessoUsuarios(processousuarios).hasSystemError();

					break;
				case UPDATE:
					count = processousuariosDAC.updateProcessoUsuarios(processousuarios).hasSystemError();

					break;
				case DELETE:
					count = processousuariosDAC.deleteProcessoUsuariosById(processousuarios).hasSystemError();


					break;
			}
		}

		return 1;
	}
}
