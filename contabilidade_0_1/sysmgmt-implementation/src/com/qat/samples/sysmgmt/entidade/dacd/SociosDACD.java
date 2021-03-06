package com.qat.samples.sysmgmt.entidade.dacd;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.pessoa.Socio;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.Status;
import com.qat.samples.sysmgmt.util.TabelaEnum;
import com.qat.samples.sysmgmt.util.TypeEnum;
import com.qat.samples.sysmgmt.util.dac.IDocumentoDAC;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;
import com.qat.samples.sysmgmt.util.dac.ISociosDAC;
import com.qat.samples.sysmgmt.util.dac.IStatusDAC;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class SociosDACD extends SqlSessionDaoSupport
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
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ISociosDAC socioDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId, IDocumentoDAC documentoDAC)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(socioList))
		{
			return count;
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
					count = socioDAC.insertSocio(socio,
							"insertSocio", response);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.SOCIO, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = socioDAC.updateSocio(socio, response);
					if (count > 0)
					{
						count =
								StatusDACD
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
							StatusDACD.maintainStatusAssociations(statusList, response, socio.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.SOCIO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}

			if (!ValidationUtil.isNullOrEmpty(socio.getDocumentos()))
			{
				DocumentosDACD.maintainDocumentoAssociations(socio.getDocumentos(), response, socio.getId(),
						null,
						null,
						TabelaEnum.SOCIO, documentoDAC, statusDAC, historicoDAC, empId,
						socio.getCreateUser(), processId, historicoId);
			}

		}
		return count;
	}
}
