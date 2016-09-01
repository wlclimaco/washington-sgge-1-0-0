/** create by system gera-java version 1.0.0 01/09/2016 12:56 : 31*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Icms.IIcmsBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Icms;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class IcmsBARD extends SqlSessionDaoSupport
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
	public static Integer maintainIcmsAssociations(List<Icms> icmsList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IIcmsBAR icmsDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(icmsList))
		{
			return 0;
		}
		// For Each Contact...
		for (Icms icms : icmsList)
		{
			// Make sure we set the parent key
			icms.setParentId(parentId);
			icms.setTabelaEnum(tabelaEnum);
			icms.setProcessId(processId);

			if (ValidationUtil.isNull(icms.getModelAction()))
			{
				continue;
			}
			switch (icms.getModelAction())
			{
				case INSERT:
					count = icmsDAC.insertIcms(icms).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.ICMS, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = icmsDAC.updateIcms(icms).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(icms.getStatusList(), response, icms.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.ICMS, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = icmsDAC.deleteIcmsById(icms).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, icms.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.ICMS, statusDAC, historicoDAC,
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
}
