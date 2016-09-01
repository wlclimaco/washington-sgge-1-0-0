/** create by system gera-java version 1.0.0 01/09/2016 12:56 : 31*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Ipi.IIpiBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Ipi;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class IpiBARD extends SqlSessionDaoSupport
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
	public static Integer maintainIpiAssociations(List<Ipi> ipiList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IIpiBAR ipiDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(ipiList))
		{
			return 0;
		}
		// For Each Contact...
		for (Ipi ipi : ipiList)
		{
			// Make sure we set the parent key
			ipi.setParentId(parentId);
			ipi.setTabelaEnum(tabelaEnum);
			ipi.setProcessId(processId);

			if (ValidationUtil.isNull(ipi.getModelAction()))
			{
				continue;
			}
			switch (ipi.getModelAction())
			{
				case INSERT:
					count = ipiDAC.insertIpi(ipi).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.IPI, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = ipiDAC.updateIpi(ipi).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(ipi.getStatusList(), response, ipi.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.IPI, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = ipiDAC.deleteIpiById(ipi).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, ipi.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.IPI, statusDAC, historicoDAC,
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
