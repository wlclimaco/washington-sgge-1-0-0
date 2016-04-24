package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Documentos.IDocumentoBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Documento;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class DocumentosBARD extends SqlSessionDaoSupport
{
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
	public static Integer maintainDocumentoAssociations(List<Documento> documentoList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IDocumentoBAR documentoDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(documentoList))
		{
			return 0;
		}
		// For Each Contact...
		for (Documento documento : documentoList)
		{
			// Make sure we set the parent key
			documento.setParentId(parentId);
			documento.setTabelaEnum(tabelaEnum);
			documento.setProcessId(processId);

			if (ValidationUtil.isNull(documento.getModelAction()))
			{
				continue;
			}
			switch (documento.getModelAction())
			{
				case INSERT:
					count = documentoDAC.insertDocumento(documento).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.DOCUMENTO, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = documentoDAC.updateDocumento(documento).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(documento.getStatusList(), response,
										documento.getId(), null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.DOCUMENTO,
										statusDAC, historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					count = documentoDAC.deleteDocumentoById(documento).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, documento.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.DOCUMENTO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}
		}

		return 1;
	}
}
