/** create by system gera-java version 1.0.0 09/08/2016 20:35 : 32*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Empresa.IEmpresaBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Site.ISiteBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.site.model.PlanoByEmpresa;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class PlanoByEmpresaBARD extends SqlSessionDaoSupport
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
	public static Integer maintainPlanoByEmpresaAssociations(List<PlanoByEmpresa> planobyempresaList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ISiteBAR iSiteBAR, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(planobyempresaList))
		{
			return 0;
		}
		// For Each Contact...
		for (PlanoByEmpresa planobyempresa : planobyempresaList)
		{
			// Make sure we set the parent key
			planobyempresa.setParentId(parentId);
			planobyempresa.setTabelaEnum(tabelaEnum);
			planobyempresa.setProcessId(processId);

			if (ValidationUtil.isNull(planobyempresa.getModelAction()))
			{
				continue;
			}
			switch (planobyempresa.getModelAction())
			{
				case INSERT:
					count = iSiteBAR.insertPlanoByEmpresa(planobyempresa).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.PLANOBYEMPRESA, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = iSiteBAR.updatePlanoByEmpresa(planobyempresa).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(planobyempresa.getStatusList(), response, planobyempresa.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.PLANOBYEMPRESA, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = iSiteBAR.deletePlanoByEmpresaById(planobyempresa).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, planobyempresa.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.PLANOBYEMPRESA, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}
		}
		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}

	public static Integer maintainPlanoByEmpresaAssociations(PlanoByEmpresa planobyempresa,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ISiteBAR iSiteBAR, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(planobyempresa))
		{
			return 0;
		}

			// Make sure we set the parent key
			planobyempresa.setParentId(parentId);
			planobyempresa.setTabelaEnum(tabelaEnum);
			planobyempresa.setProcessId(processId);

			switch (planobyempresa.getModelAction())
			{
				case INSERT:
					count = iSiteBAR.insertPlanoByEmpresa(planobyempresa).hasSystemError();

					break;
				case UPDATE:
					count = iSiteBAR.updatePlanoByEmpresa(planobyempresa).hasSystemError();

					break;
				case DELETE:
					count = iSiteBAR.deletePlanoByEmpresaById(planobyempresa).hasSystemError();
					break;
			}

		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}
}
