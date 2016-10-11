/** create by system gera-java version 1.0.0 27/07/2016 12:53 : 25*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Cadastros.ICadastrosBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class PessoaBARD extends SqlSessionDaoSupport
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
	public static Integer maintainClienteAssociations(List<Cliente> clienteList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ICadastrosBAR clienteDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(clienteList))
		{
			return 0;
		}
		// For Each Contact...
		for (Cliente cliente : clienteList)
		{
			// Make sure we set the parent key
			cliente.setParentId(parentId);
			cliente.setTabelaEnum(tabelaEnum);
			cliente.setProcessId(processId);

			if (ValidationUtil.isNull(cliente.getModelAction()))
			{
				continue;
			}
			switch (cliente.getModelAction())
			{
				case INSERT:
					count = clienteDAC.insertCliente(cliente).hasSystemError();

					break;
				case UPDATE:
					count = clienteDAC.updateCliente(cliente).hasSystemError();

					break;
				case DELETE:
					count = clienteDAC.deleteClienteById(cliente).hasSystemError();

					break;
			}
		}
		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}

	public static Integer maintainClienteAssociation(Cliente cliente,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ICadastrosBAR clienteDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(cliente))
		{
			return 0;
		}
		// For Each Contact...

			// Make sure we set the parent key
			cliente.setParentId(parentId);
			cliente.setTabelaEnum(tabelaEnum);
			cliente.setProcessId(processId);

			switch (cliente.getModelAction())
			{
				case INSERT:
					count = clienteDAC.insertCliente(cliente).hasSystemError();

					break;
				case UPDATE:
					count = clienteDAC.updateCliente(cliente).hasSystemError();

					break;
				case DELETE:
					count = clienteDAC.deleteClienteById(cliente).hasSystemError();

					break;
			}

		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}
}
