/** create by system gera-java version 1.0.0 15/09/2017 22:36 : 52*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Advogado.IAdvocaciaBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.ClienteCompromisso;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ClienteCompromissoBARD extends SqlSessionDaoSupport
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
	public static Integer maintainClienteCompromissoAssociations(ClienteCompromisso clientecompromisso,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IAdvocaciaBAR clientecompromissoDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(clientecompromisso))
		{
			return 0;
		}
		// For Each Contact...
			// Make sure we set the parent key
			clientecompromisso.setParentId(parentId);
			clientecompromisso.setTabelaEnum(tabelaEnum);
			clientecompromisso.setProcessId(processId);

		//	if (ValidationUtil.isNull(clientecompromisso.getModelAction()))
		//	{
		//		continue;
		//	}
			switch (clientecompromisso.getModelAction())
			{
				case INSERT:
					count = clientecompromissoDAC.insertClienteCompromisso(clientecompromisso).hasSystemError();

					break;
				case UPDATE:
					count = clientecompromissoDAC.updateClienteCompromisso(clientecompromisso).hasSystemError();

					break;
				case DELETE:
					count = clientecompromissoDAC.deleteClienteCompromissoById(clientecompromisso).hasSystemError();


					break;
			}

		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}
@SuppressWarnings("unchecked")
	public static Integer maintainClienteCompromissoAssociations(List<ClienteCompromisso> clientecompromissoList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IAdvocaciaBAR clientecompromissoDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain ClienteCompromisso
		if (ValidationUtil.isNullOrEmpty(clientecompromissoList))
		{
			return 0;
		}
		// For Each ClienteCompromisso...
		for (ClienteCompromisso clientecompromisso : clientecompromissoList)
		{
			// Make sure we set the parent key
			clientecompromisso.setParentId(parentId);
			clientecompromisso.setTabelaEnum(tabelaEnum);
			clientecompromisso.setProcessId(processId);

			if (ValidationUtil.isNull(clientecompromisso.getModelAction()))
			{
				continue;
			}
         switch (clientecompromisso.getModelAction())
			{
				case INSERT:
					count = clientecompromissoDAC.insertClienteCompromisso(clientecompromisso).hasSystemError();

					break;
				case UPDATE:
					count = clientecompromissoDAC.updateClienteCompromisso(clientecompromisso).hasSystemError();

					break;
				case DELETE:
					count = clientecompromissoDAC.deleteClienteCompromissoById(clientecompromisso).hasSystemError();


					break;
			}
		}

		return 1;
	}
}
