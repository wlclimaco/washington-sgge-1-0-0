package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Produto.IProdutoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.produto.model.Estoque;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.AcaoTypeEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class EstoqueBARD extends SqlSessionDaoSupport
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
	public static Integer maintainEstoqueAssociations(List<Estoque> estoqueList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoTypeEnum insert,
			TabelaEnum tabelaEnum, IProdutoBAR estoqueDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(estoqueList))
		{
			return 0;
		}
		// For Each Contact...
		for (Estoque estoque : estoqueList)
		{
			// Make sure we set the parent key
			estoque.setParentId(parentId);
			estoque.setTabelaEnum(tabelaEnum);
			estoque.setProcessId(processId);

			if (ValidationUtil.isNull(estoque.getModelAction()))
			{
				continue;
			}
			switch (estoque.getModelAction())
			{
				case INSERT:
					count = estoqueDAC.insertEstoque(estoque).hasSystemError();
					if (count == false)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = estoqueDAC.updateEstoque(estoque).hasSystemError();
					if (count == false)
					{
						count =
								StatusBARD.maintainStatusAssociations(estoque.getStatusList(), response, estoque.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = estoqueDAC.deleteEstoqueById(estoque).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, estoque.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}
		}

		return 1;
	}
}
