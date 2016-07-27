/** create by system gera-java version 1.0.0 27/07/2016 12:53 : 25*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Configuracao.IConfiguracaoBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.entidade.model.ConfigCarne;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ConfigCarneBARD extends SqlSessionDaoSupport
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
	public static Integer maintainConfigCarneAssociations(ConfigCarne configcarne,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IConfiguracaoBAR configcarneDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(configcarne))
		{
			return 0;
		}

			// Make sure we set the parent key
			configcarne.setParentId(parentId);
			configcarne.setTabelaEnum(tabelaEnum);
			configcarne.setProcessId(processId);

			switch (configcarne.getModelAction())
			{
				case INSERT:
					count = configcarneDAC.insertConfigCarne(configcarne).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.CONFIGCARNE, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = configcarneDAC.updateConfigCarne(configcarne).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(configcarne.getStatusList(), response, configcarne.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.CONFIGCARNE, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = configcarneDAC.deleteConfigCarneById(configcarne).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, configcarne.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.CONFIGCARNE, statusDAC, historicoDAC,
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
