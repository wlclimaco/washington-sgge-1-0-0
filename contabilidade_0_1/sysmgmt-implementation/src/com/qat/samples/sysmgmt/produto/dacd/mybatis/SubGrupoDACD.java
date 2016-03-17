package com.qat.samples.sysmgmt.produto.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.produto.dac.ISubGrupoDAC;
import com.qat.samples.sysmgmt.produto.model.SubGrupoProd;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.Status;
import com.qat.samples.sysmgmt.util.TabelaEnum;
import com.qat.samples.sysmgmt.util.TypeEnum;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;
import com.qat.samples.sysmgmt.util.dac.IStatusDAC;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class SubGrupoDACD extends SqlSessionDaoSupport
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
	public static Integer maintainSubGrupoProdAssociations(SubGrupoProd subGrupo,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ISubGrupoDAC subGrupoDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNull(subGrupo))
		{
			return count;
		}

		// Make sure we set the parent key
		subGrupo.setParentId(parentId);

		switch (subGrupo.getModelAction())
		{
			case INSERT:
			count =
				maintainSubGrupoAssociations(subGrupo.getIdCnae(), response, null, null,
					null,
					TabelaEnum.PRODUTO, cnaeDAC, statusDAC, historicoDAC,
					cnae.getEmprId(),
					cnae.getCreateUser(), processId, historicoId);
									
				count = subGrupoDAC.insertSubGrupoProd(subGrupo,
						"insertSubGrupo", response);
				if (count > 0)
				{
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.ATIVO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, count, null,
									AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, null);
				}
				break;
			case UPDATE:
			
				count =
					maintainSubGrupoAssociations(subGrupo.getIdCnae(), response, null, null,
					null,
					TabelaEnum.PRODUTO, cnaeDAC, statusDAC, historicoDAC,
					cnae.getEmprId(),
					cnae.getCreateUser(), processId, historicoId);
					
				count = subGrupoDAC.updateSubGrupoProd(subGrupo, response);
				if (count > 0)
				{
					count =
							StatusDACD
									.maintainStatusAssociations(subGrupo.getStatusList(), response,
											subGrupo.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
											historicoDAC, processId, null);
				}
				break;
			case DELETE:
				count = subGrupoDAC.deleteSubGrupoProd(subGrupo, response);
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				count =
						StatusDACD.maintainStatusAssociations(statusList, response, subGrupo.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC, processId,
								null);

				break;
			case NONE:
				count = subGrupoDAC.insertSubGrupoProd(subGrupo,
						"insertSubGrupo", response);
				if (count > 0)
				{
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.ATIVO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, count, null,
									AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, null);
				}
				break;
		}

		return count;
	}
	
	public static Integer maintainSubGrupoAssociations(SubGrupo subGrupo,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, ISubGrupoDAC subGrupoDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNull(subGrupo))
		{
			return count;
		}

		// Make sure we set the parent key
		subGrupo.setParentId(parentId);

		switch (subGrupo.getModelAction())
		{
			case INSERT:
				count = subGrupoDAC.insertSubGrupo(subGrupo,
						"insertSubGrupo", response);
				if (count > 0)
				{
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.ATIVO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, count, null,
									AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, null);
				}
				break;
			case UPDATE:
				count = subGrupoDAC.updateSubGrupo(subGrupo, response);
				if (count > 0)
				{
					count =
							StatusDACD
									.maintainStatusAssociations(subGrupo.getStatusList(), response,
											subGrupo.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
											historicoDAC, processId, null);
				}
				break;
			case DELETE:
				count = subGrupoDAC.deleteSubGrupo(subGrupo, response);
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				count =
						StatusDACD.maintainStatusAssociations(statusList, response, subGrupo.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC, processId,
								null);

				break;
		}

		return count;
	}
	
}
