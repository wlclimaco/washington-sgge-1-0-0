/** create by system gera-java version 1.0.0 27/07/2016 12:53 : 25*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Configuracao.IConfiguracaoBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.entidade.model.ConfigGeral;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ConfigGeralBARD extends SqlSessionDaoSupport
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
	public static Integer maintainConfigGeralAssociations(ConfigGeral configgeral,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IConfiguracaoBAR configgeralDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(configgeral))
		{
			return 0;
		}

			// Make sure we set the parent key
			configgeral.setParentId(parentId);
			configgeral.setTabelaEnum(tabelaEnum);
			configgeral.setProcessId(processId);


			switch (configgeral.getModelAction())
			{
				case INSERT:
					count = configgeralDAC.insertConfigGeral(configgeral).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.CONFIGGERAL, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = configgeralDAC.updateConfigGeral(configgeral).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(configgeral.getStatusList(), response, configgeral.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.CONFIGGERAL, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = configgeralDAC.deleteConfigGeralById(configgeral).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, configgeral.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.CONFIGGERAL, statusDAC, historicoDAC,
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
