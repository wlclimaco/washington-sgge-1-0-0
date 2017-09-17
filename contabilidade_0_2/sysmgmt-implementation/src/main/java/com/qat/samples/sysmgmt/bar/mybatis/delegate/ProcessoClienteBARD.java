/** create by system gera-java version 1.0.0 16/09/2017 22:9 : 46*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.advocacia.ProcessoCliente;
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
public final class ProcessoClienteBARD extends SqlSessionDaoSupport
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
	public static Integer maintainProcessoClienteAssociations(ProcessoCliente processocliente,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IAdvocaciaBAR processoclienteDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(processocliente))
		{
			return 0;
		}
		// For Each Contact...
			// Make sure we set the parent key
			processocliente.setParentId(parentId);
			processocliente.setTabelaEnum(tabelaEnum);
			processocliente.setProcessId(processId);

		//	if (ValidationUtil.isNull(processocliente.getModelAction()))
		//	{
		//		continue;
		//	}
			switch (processocliente.getModelAction())
			{
				case INSERT:
					count = processoclienteDAC.insertProcessoCliente(processocliente).hasSystemError();

					break;
				case UPDATE:
					count = processoclienteDAC.updateProcessoCliente(processocliente).hasSystemError();

					break;
				case DELETE:
					count = processoclienteDAC.deleteProcessoClienteById(processocliente).hasSystemError();


					break;
			}

		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}
@SuppressWarnings("unchecked")
	public static Integer maintainProcessoClienteAssociations(List<ProcessoCliente> processoclienteList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IAdvocaciaBAR processoclienteDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain ProcessoCliente
		if (ValidationUtil.isNullOrEmpty(processoclienteList))
		{
			return 0;
		}
		// For Each ProcessoCliente...
		for (ProcessoCliente processocliente : processoclienteList)
		{
			// Make sure we set the parent key
			processocliente.setParentId(parentId);
			processocliente.setTabelaEnum(tabelaEnum);
			processocliente.setProcessId(processId);

			if (ValidationUtil.isNull(processocliente.getModelAction()))
			{
				continue;
			}
         switch (processocliente.getModelAction())
			{
				case INSERT:
					count = processoclienteDAC.insertProcessoCliente(processocliente).hasSystemError();

					break;
				case UPDATE:
					count = processoclienteDAC.updateProcessoCliente(processocliente).hasSystemError();

					break;
				case DELETE:
					count = processoclienteDAC.deleteProcessoClienteById(processocliente).hasSystemError();


					break;
			}
		}

		return 1;
	}
}
