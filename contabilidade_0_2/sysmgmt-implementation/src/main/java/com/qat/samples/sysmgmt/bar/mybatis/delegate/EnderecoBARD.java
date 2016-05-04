package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Cadastros.ICadastrosBAR;
import com.qat.samples.sysmgmt.bar.Endereco.IEnderecoBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class EnderecoBARD extends SqlSessionDaoSupport
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
			TabelaEnum tabelaEnum, IEnderecoBAR enderecoDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId, ICadastrosBAR cidadeDAC)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(enderecoList))
		{
			return 0;
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
					count = enderecoDAC.insertEndereco(endereco).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.ENDERECO, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = enderecoDAC.updateEndereco(endereco).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response,
										endereco.getId(), null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.ENDERECO,
										statusDAC, historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					count = enderecoDAC.deleteEnderecoById(endereco).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, endereco.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.ENDERECO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
				case NONE:
					CidadeBARD.maintainCidadeAssociations(endereco.getCidade(), response, parentId, type, acaoType,
							tabelaEnum, cidadeDAC, statusDAC, historicoDAC, empId, UserId, processId, historicoId);

					break;
				default:

			}
		}
		if(count){
			return 1;
		}else{
			return 0;
		}
	}
}
