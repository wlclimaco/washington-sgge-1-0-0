package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Site.ISiteBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.site.model.ServicoAndPlano;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ServicoAndPlanoBARD extends SqlSessionDaoSupport
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
	public static Integer maintainServicoAndPlanoAssociations(List<ServicoAndPlano> servicoAndPlanoList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ISiteBAR servicoAndPlanoDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(servicoAndPlanoList))
		{
			return 0;
		}
		// For Each Contact...
		for (ServicoAndPlano servicoAndPlano : servicoAndPlanoList)
		{
			// Make sure we set the parent key
			servicoAndPlano.setParentId(parentId);
			servicoAndPlano.setTabelaEnum(tabelaEnum);
			servicoAndPlano.setProcessId(processId);

			if (ValidationUtil.isNull(servicoAndPlano.getModelAction()))
			{
				continue;
			}
			switch (servicoAndPlano.getModelAction())
			{
				case INSERT:
					count = servicoAndPlanoDAC.insertServicoAndPlano(servicoAndPlano).hasSystemError();
					if (count == true)
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
					count = servicoAndPlanoDAC.updateServicoAndPlano(servicoAndPlano).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(servicoAndPlano.getStatusList(), response, servicoAndPlano.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = servicoAndPlanoDAC.deleteServicoAndPlanoById(servicoAndPlano).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, servicoAndPlano.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}
		}

		return 1;
	}
}
