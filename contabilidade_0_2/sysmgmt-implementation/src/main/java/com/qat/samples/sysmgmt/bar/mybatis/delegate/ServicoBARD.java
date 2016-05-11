package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Site.ISiteBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.contabilidade.model.PlanoByServico;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ServicoBARD extends SqlSessionDaoSupport
{

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;
	private static final String INSERT = null;
	private static final String UPDATE = null;
	private static final String DELETE = null;

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
	public static Integer maintainServicoByPlanoAssociations(List<PlanoByServico> list,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ISiteBAR emailDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(list))
		{
			return 0;
		}
		// For Each Contact...
		for (PlanoByServico email : list)
		{
			// Make sure we set the parent key
			email.setParentId(parentId);
			email.setTabelaEnum(tabelaEnum);
			email.setProcessId(processId);

			if (ValidationUtil.isNull(email.getModelAction()))
			{
				continue;
			}
			switch (email.getModelAction())
			{
				case INSERT:
					count = emailDAC.insertServicoByPlano(email).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = emailDAC.updateServicoByPlano(email).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(email.getStatusList(), response, email.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = emailDAC.deleteServicoByPlanoById(email).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, email.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}
		}

		return 1;
	}

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
	public static Integer maintainServicoAssociations(List<Servico> list,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ISiteBAR emailDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(list))
		{
			return 0;
		}
		// For Each Contact...
		for (Servico email : list)
		{
			// Make sure we set the parent key
			email.setParentId(parentId);
			email.setTabelaEnum(tabelaEnum);
			email.setProcessId(processId);

			if (ValidationUtil.isNull(email.getModelAction()))
			{
				continue;
			}
			switch (email.getModelAction())
			{
				case INSERT:
					count = emailDAC.insertServico(email).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = emailDAC.updateServico(email).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(email.getStatusList(), response, email.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = emailDAC.deleteServicoById(email).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, email.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}
		}

		return 1;
	}

}
