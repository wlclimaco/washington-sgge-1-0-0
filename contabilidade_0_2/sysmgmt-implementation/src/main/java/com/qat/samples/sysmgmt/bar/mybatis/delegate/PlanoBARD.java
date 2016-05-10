package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Produto.IPrecoBAR;
import com.qat.samples.sysmgmt.bar.Site.ISiteBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contabilidade.model.PlanoBySite;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class PlanoBARD extends SqlSessionDaoSupport
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
	public static Integer maintainPlanoBySiteAssociations(List<PlanoBySite> list,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ISiteBAR iPrecoBAR, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(list))
		{
			return 0;
		}
		// For Each Contact...
		for (PlanoBySite plano : list)
		{
			// Make sure we set the parent key
			plano.setParentId(parentId);
			plano.setEmprId(empId);

			if (ValidationUtil.isNull(plano.getModelAction()))
			{
				continue;
			}
			switch (plano.getModelAction())
			{
				case INSERT:
					count = iPrecoBAR.insertPlanoBySite(plano).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, (InternalResultsResponse<?>) response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.SOCIO, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = iPrecoBAR.updatePlanoBySite(plano).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD
										.maintainStatusAssociations(plano.getStatusList(), (InternalResultsResponse<?>) response, plano.getId(),
												null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.SOCIO, statusDAC,
												historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					count = iPrecoBAR.deletePlanoBySiteById(plano).hasSystemError();

					break;
			}
		}
		return 1;
	}

	public static Integer maintainPlanoAssociations(List<Plano> list,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ISiteBAR iPrecoBAR, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(list))
		{
			return 0;
		}
		// For Each Contact...
		for (Plano plano : list)
		{
			// Make sure we set the parent key
			plano.setParentId(parentId);
			plano.setEmprId(empId);

			if (ValidationUtil.isNull(plano.getModelAction()))
			{
				continue;
			}
			switch (plano.getModelAction())
			{
				case INSERT:
					count = iPrecoBAR.insertPlano(plano).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, (InternalResultsResponse<?>) response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.SOCIO, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = iPrecoBAR.updatePlano(plano).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD
										.maintainStatusAssociations(plano.getStatusList(), (InternalResultsResponse<?>) response, plano.getId(),
												null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.SOCIO, statusDAC,
												historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					count = iPrecoBAR.deletePlanoById(plano).hasSystemError();

					break;
			}
		}
		return 1;
	}
}
