package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.Telefone.ITelefoneBAR;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.Telefone;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class TelefoneDACD extends SqlSessionDaoSupport
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
	public static Integer maintainTelefoneAssociations(List<Telefone> telefoneList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ITelefoneBAR telefoneDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(telefoneList))
		{
			return 0;
		}
		// For Each Contact...
		for (Telefone telefone : telefoneList)
		{
			// Make sure we set the parent key
			telefone.setParentId(parentId);
			telefone.setTabelaEnum(tabelaEnum);

			if (ValidationUtil.isNull(telefone.getModelAction()))
			{
				continue;
			}
			switch (telefone.getModelAction())
			{
				case INSERT:
					count = telefoneDAC.insertTelefone(telefone).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.TELEFONE, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = telefoneDAC.updateTelefone(telefone).hasSystemError();
					if (count == true)
					{
						count =
								StatusDACD.maintainStatusAssociations(telefone.getStatusList(), response,
										telefone.getId(), null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.TELEFONE,
										statusDAC, historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:

					count = telefoneDAC.deleteTelefoneById(telefone).hasSystemError();

					break;
			}
		}

		return 1;
	}
}
