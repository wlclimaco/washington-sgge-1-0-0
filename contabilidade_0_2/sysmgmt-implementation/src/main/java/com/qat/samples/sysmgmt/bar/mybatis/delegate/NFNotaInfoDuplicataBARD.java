/** create by system gera-java version 1.0.0 29/09/2016 21:9 : 13*/


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
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoDuplicata;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class NFNotaInfoDuplicataBARD extends SqlSessionDaoSupport
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
	public static Integer maintainNFNotaInfoDuplicataAssociations(List<NFNotaInfoDuplicata> nfnotainfoduplicatas,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, INFeBAR nfnotainfoduplicataDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(nfnotainfoduplicatas))
		{
			return 0;
		}
		for (NFNotaInfoDuplicata nfnotainfoduplicata : nfnotainfoduplicatas)
		{
			if (ValidationUtil.isNull(nfnotainfoduplicata.getModelAction()))
			{
				continue;
			}
		// For Each Contact...
			// Make sure we set the parent key
			nfnotainfoduplicata.setParentId(parentId);
			nfnotainfoduplicata.setTabelaEnum(tabelaEnum);
			nfnotainfoduplicata.setProcessId(processId);

			if (ValidationUtil.isNull(nfnotainfoduplicata.getModelAction()))
			{
				continue;
			}
			switch (nfnotainfoduplicata.getModelAction())
			{
				case INSERT:
					count = nfnotainfoduplicataDAC.insertNFNotaInfoDuplicata(nfnotainfoduplicata).hasSystemError();
					break;
				case UPDATE:
					count = nfnotainfoduplicataDAC.updateNFNotaInfoDuplicata(nfnotainfoduplicata).hasSystemError();

					break;
				case DELETE:
					count = nfnotainfoduplicataDAC.deleteNFNotaInfoDuplicataById(nfnotainfoduplicata).hasSystemError();

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
