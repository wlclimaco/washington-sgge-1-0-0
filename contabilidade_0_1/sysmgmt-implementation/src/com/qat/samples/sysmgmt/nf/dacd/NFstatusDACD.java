package com.qat.samples.sysmgmt.nf.dacd;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.nf.dac.INFStatusDAC;
import com.qat.samples.sysmgmt.nf.model.NFStatus;
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
public final class NFstatusDACD extends SqlSessionDaoSupport
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
	public static Integer maintainNFStatusAssociations(List<NFStatus> nFStatusList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, INFStatusDAC nFStatusDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(nFStatusList))
		{
			return count;
		}
		// For Each Contact...
		for (NFStatus nFStatus : nFStatusList)
		{
			// Make sure we set the parent key
			nFStatus.setParentId(parentId);

			if (ValidationUtil.isNull(nFStatus.getModelAction()))
			{
				continue;
			}
			switch (nFStatus.getModelAction())
			{
				case INSERT:
					count = nFStatusDAC.insertNFStatus(nFStatus,
							"insertNFStatus", response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.CNAE, statusDAC, historicoDAC,
										processId, null);
					}
					break;
				case UPDATE:
					count = nFStatusDAC.updateNFStatus(nFStatus, response);
					if (count > 0)
					{
						count =
								StatusDACD.maintainStatusAssociations(nFStatus.getStatusList(), response,
										nFStatus.getId(),
										null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.CNAE, statusDAC, historicoDAC,
										processId, null);
					}
					break;
				case DELETE:
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD
									.maintainStatusAssociations(statusList, response, nFStatus.getId(), null,
											AcaoEnum.DELETE, UserId, empId, TabelaEnum.CNAE, statusDAC, historicoDAC,
											processId, null);

					break;
			}
		}

		return count;
	}
}
