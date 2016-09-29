/** create by system gera-java version 1.0.0 29/09/2016 16:23 : 28*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Nfe.INFeBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.nfe.model.NFInfoReferenciada;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class NFInfoReferenciadaBARD extends SqlSessionDaoSupport
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
	public static Integer maintainNFInfoReferenciadaAssociations(List<NFInfoReferenciada> nfinforeferenciadaList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, INFeBAR nfinforeferenciadaDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(nfinforeferenciadaList))
		{
			return 0;
		}
		for (NFInfoReferenciada nfinforeferenciada : nfinforeferenciadaList)
		{
			if (ValidationUtil.isNull(nfinforeferenciada.getModelAction()))
			{
				continue;
			}
		// For Each Contact...
			// Make sure we set the parent key
			nfinforeferenciada.setParentId(parentId);
			nfinforeferenciada.setTabelaEnum(tabelaEnum);
			nfinforeferenciada.setProcessId(processId);

		//	if (ValidationUtil.isNull(nfinforeferenciada.getModelAction()))
		//	{
		//		continue;
		//	}
			switch (nfinforeferenciada.getModelAction())
			{
				case INSERT:
					count = nfinforeferenciadaDAC.insertNFInfoReferenciada(nfinforeferenciada).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.NFINFOREFERENCIADA, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = nfinforeferenciadaDAC.updateNFInfoReferenciada(nfinforeferenciada).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(nfinforeferenciada.getStatusList(), response, nfinforeferenciada.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.NFINFOREFERENCIADA, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = nfinforeferenciadaDAC.deleteNFInfoReferenciadaById(nfinforeferenciada).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, nfinforeferenciada.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.NFINFOREFERENCIADA, statusDAC, historicoDAC,
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
