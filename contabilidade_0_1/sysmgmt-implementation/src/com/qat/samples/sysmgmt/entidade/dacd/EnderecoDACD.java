package com.qat.samples.sysmgmt.entidade.dacd;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.dac.IEnderecoDAC;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.Endereco;
import com.qat.samples.sysmgmt.util.Status;
import com.qat.samples.sysmgmt.util.TabelaEnum;
import com.qat.samples.sysmgmt.util.TypeEnum;
import com.qat.samples.sysmgmt.util.dac.ICidadeDAC;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;
import com.qat.samples.sysmgmt.util.dac.IStatusDAC;

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
			Integer empId, String UserId, Integer processId, Integer historicoId, ICidadeDAC cidadeDAC)
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
				case NONE:
					CidadeDACD.maintainCidadeAssociations(endereco.getCidade(), response, parentId, type, acaoType,
							tabelaEnum, cidadeDAC, statusDAC, historicoDAC, empId, UserId, processId, historicoId);

					break;
				default:

			}
		}

		return count;
	}
}
