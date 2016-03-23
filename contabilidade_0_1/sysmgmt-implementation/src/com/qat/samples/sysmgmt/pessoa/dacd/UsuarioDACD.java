package com.qat.samples.sysmgmt.pessoa.dacd;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.entidade.Usuario;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.pessoa.dac.IUsuarioDAC;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.Status;
import com.qat.samples.sysmgmt.util.TabelaEnum;
import com.qat.samples.sysmgmt.util.TypeEnum;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;
import com.qat.samples.sysmgmt.util.dac.IStatusDAC;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class UsuarioDACD extends SqlSessionDaoSupport
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
	public static Integer maintainUsuarioAssociations(List<Usuario> contatoList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IUsuarioDAC contatoDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(contatoList))
		{
			return count;
		}
		// For Each Contact...
		for (Usuario contato : contatoList)
		{
			// Make sure we set the parent key
			contato.setParentId(parentId);
			contato.setProcessId(processId);

			if (ValidationUtil.isNull(contato.getModelAction()))
			{
				continue;
			}
			switch (contato.getModelAction())
			{
				case INSERT:
					count = contatoDAC.insertUsuario(contato, null, null);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.USUARIO, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = contatoDAC.updateUsuario(contato, null);
					if (count > 0)
					{
						count =
								StatusDACD.maintainStatusAssociations(contato.getStatusList(), response,
										contato.getId(), null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.USUARIO,
										statusDAC, historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:

					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, contato.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.USUARIO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}
		}

		return count;
	}
}
