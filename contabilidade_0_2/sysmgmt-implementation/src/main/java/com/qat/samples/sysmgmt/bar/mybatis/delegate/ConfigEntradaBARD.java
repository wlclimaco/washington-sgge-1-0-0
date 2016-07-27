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
import com.qat.samples.sysmgmt.entidade.model.ConfigEntrada;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ConfigEntradaBARD extends SqlSessionDaoSupport
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
	public static Integer maintainConfigEntradaAssociations(ConfigEntrada configentrada,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IConfiguracaoBAR configentradaDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(configentrada))
		{
			return 0;
		}

			// Make sure we set the parent key
			configentrada.setParentId(parentId);
			configentrada.setTabelaEnum(tabelaEnum);
			configentrada.setProcessId(processId);


			switch (configentrada.getModelAction())
			{
				case INSERT:
					count = configentradaDAC.insertConfigEntrada(configentrada).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.CONFIGENTRADA, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = configentradaDAC.updateConfigEntrada(configentrada).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(configentrada.getStatusList(), response, configentrada.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.CONFIGENTRADA, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = configentradaDAC.deleteConfigEntradaById(configentrada).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, configentrada.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.CONFIGENTRADA, statusDAC, historicoDAC,
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
