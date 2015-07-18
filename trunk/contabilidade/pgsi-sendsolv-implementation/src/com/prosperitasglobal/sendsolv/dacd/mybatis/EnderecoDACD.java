package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IEnderecoDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum;
import com.prosperitasglobal.sendsolv.model.Endereco;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class EnderecoDACD extends SqlSessionDaoSupport
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
	public static Integer maintainEnderecoAssociations(List<Endereco> enderecoList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IEnderecoDAC enderecoDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(enderecoList))
		{
			return count;
		}
		// For Each Contact...
		for (Endereco endereco : enderecoList)
		{
			// Make sure we set the parent key
			endereco.setParentId(parentId);
			endereco.setTabelaEnum(tabelaEnum);
			endereco.setProcessId(processId);

			if (ValidationUtil.isNull(endereco.getModelAction()))
			{
				continue;
			}
			switch (endereco.getModelAction())
			{
				case INSERT:
					count = enderecoDAC.insertEndereco(endereco,
							"insertEndereco", response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.ENDERECO, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = enderecoDAC.updateEndereco(endereco, response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusDACD.maintainStatusAssociations(statusList, response,
										endereco.getId(), null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.ENDERECO,
										statusDAC, historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:

					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, endereco.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.ENDERECO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
				default:

			}
		}

		return count;
	}
}
