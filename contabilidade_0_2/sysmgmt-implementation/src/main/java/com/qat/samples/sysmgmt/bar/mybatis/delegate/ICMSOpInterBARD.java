/** create by system gera-java version 1.0.0 09/11/2016 20:4 : 34*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Produto.IProdutoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.produto.model.ICMSOpInter;
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
public final class ICMSOpInterBARD extends SqlSessionDaoSupport
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
	public static Integer maintainICMSOpInterAssociations(ICMSOpInter icmsopinter,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoTypeEnum insert,
			TabelaEnum tabelaEnum, IProdutoBAR icmsopinterDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(icmsopinter))
		{
			return 0;
		}
		// For Each Contact...
			// Make sure we set the parent key
			icmsopinter.setParentId(parentId);
			icmsopinter.setTabelaEnum(tabelaEnum);
			icmsopinter.setProcessId(processId);

		//	if (ValidationUtil.isNull(icmsopinter.getModelAction()))
		//	{
		//		continue;
		//	}
			switch (icmsopinter.getModelAction())
			{
				case INSERT:
					count = icmsopinterDAC.insertICMSOpInter(icmsopinter).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.ICMSOPINTER, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = icmsopinterDAC.updateICMSOpInter(icmsopinter).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(icmsopinter.getStatusList(), response, icmsopinter.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.ICMSOPINTER, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = icmsopinterDAC.deleteICMSOpInterById(icmsopinter).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, icmsopinter.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.ICMSOPINTER, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}
		
		if(count == true ){
			return 1;
		}else{
			return 0;
		}
		
	}
}
