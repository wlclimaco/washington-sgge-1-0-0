package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Documentos.IDocumentoBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Socios.ISociosBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class SociosBARD extends SqlSessionDaoSupport
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
	public static Integer maintainSocioAssociations(List<Socio> socioList,
			InternalResponse responsew, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ISociosBAR socioDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId, IDocumentoBAR documentoDAC)
	{
		Boolean count = false;
		// First Maintain Empresa
		InternalResultsResponse<Empresa> response  = new InternalResultsResponse<Empresa>();
		if (ValidationUtil.isNullOrEmpty(socioList))
		{
			return 0;
		}
		// For Each Contact...
		for (Socio socio : socioList)
		{
			// Make sure we set the parent key
			socio.setParentId(parentId);

			if (ValidationUtil.isNull(socio.getModelAction()))
			{
				continue;
			}
			switch (socio.getModelAction())
			{
				case INSERT:
					count = socioDAC.insertSocio(socio).hasSystemError();
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
					count = socioDAC.updateSocio(socio).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD
										.maintainStatusAssociations(socio.getStatusList(), response, socio.getId(),
												null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.SOCIO, statusDAC,
												historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:

					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.ATIVO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, socio.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.SOCIO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}

			if (!ValidationUtil.isNullOrEmpty(socio.getDocumentos()))
			{
				DocumentosBARD.maintainDocumentoAssociations(socio.getDocumentos(), response, socio.getId(),
						null,
						null,
						TabelaEnum.SOCIO, documentoDAC, statusDAC, historicoDAC, empId,
						socio.getCreateUser(), processId, historicoId);
			}

		}
		return 1;
	}
}
