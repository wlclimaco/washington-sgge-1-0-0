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
import com.qat.samples.sysmgmt.nfe.model.NFPessoaAutorizadaDownloadNFe;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class NFPessoaAutorizadaDownloadNFeBARD extends SqlSessionDaoSupport
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
	public static Integer maintainNFPessoaAutorizadaDownloadNFeAssociations(List<NFPessoaAutorizadaDownloadNFe> nfpessoaautorizadadownloadnfeList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, INFeBAR nfpessoaautorizadadownloadnfeDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;

		if (ValidationUtil.isNullOrEmpty(nfpessoaautorizadadownloadnfeList))
		{
			return 0;
		}
		for (NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfe : nfpessoaautorizadadownloadnfeList)
		{
			if (ValidationUtil.isNull(nfpessoaautorizadadownloadnfe.getModelAction()))
			{
				continue;
			}

		// For Each Contact...
			// Make sure we set the parent key
			nfpessoaautorizadadownloadnfe.setParentId(parentId);
			nfpessoaautorizadadownloadnfe.setTabelaEnum(tabelaEnum);
			nfpessoaautorizadadownloadnfe.setProcessId(processId);

		//	if (ValidationUtil.isNull(nfpessoaautorizadadownloadnfe.getModelAction()))
		//	{
		//		continue;
		//	}
			switch (nfpessoaautorizadadownloadnfe.getModelAction())
			{
				case INSERT:
					count = nfpessoaautorizadadownloadnfeDAC.insertNFPessoaAutorizadaDownloadNFe(nfpessoaautorizadadownloadnfe).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.NFPESSOAAUTORIZADADOWNLOADNFE, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = nfpessoaautorizadadownloadnfeDAC.updateNFPessoaAutorizadaDownloadNFe(nfpessoaautorizadadownloadnfe).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(nfpessoaautorizadadownloadnfe.getStatusList(), response, nfpessoaautorizadadownloadnfe.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.NFPESSOAAUTORIZADADOWNLOADNFE, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = nfpessoaautorizadadownloadnfeDAC.deleteNFPessoaAutorizadaDownloadNFeById(nfpessoaautorizadadownloadnfe).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, nfpessoaautorizadadownloadnfe.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.NFPESSOAAUTORIZADADOWNLOADNFE, statusDAC, historicoDAC,
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
