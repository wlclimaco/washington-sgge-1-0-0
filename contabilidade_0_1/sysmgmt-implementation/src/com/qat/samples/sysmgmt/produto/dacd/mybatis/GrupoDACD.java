package com.qat.samples.sysmgmt.produto.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.produto.dac.IGrupoDAC;
import com.qat.samples.sysmgmt.produto.model.GrupoProd;
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
public final class GrupoDACD extends SqlSessionDaoSupport
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
	public static Integer maintainGrupoProdAssociations(GrupoProd grupo,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IGrupoDAC grupoDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC, Integer empId,
			String UserId, Integer processId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNull(grupo))
		{
			return count;
		}

		// Make sure we set the parent key
		grupo.setParentId(parentId);

		switch (grupo.getModelAction())
		{
			case INSERT:
			
				count =
				maintainSubGrupoAssociations(Grupo.getIdCnae(), response, null, null,
					null,
					TabelaEnum.PRODUTO, cnaeDAC, statusDAC, historicoDAC,
					cnae.getEmprId(),
					cnae.getCreateUser(), processId, historicoId);
			
				count = grupoDAC.insertGrupoProd(grupo,
						"insertGrupo", response);
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
				maintainSubGrupoAssociations(Grupo.getIdCnae(), response, null, null,
					null,
					TabelaEnum.PRODUTO, cnaeDAC, statusDAC, historicoDAC,
					cnae.getEmprId(),
					cnae.getCreateUser(), processId, historicoId);
					
				count = grupoDAC.updateGrupoProd(grupo, response);
				if (count > 0)
				{
					count =
							StatusDACD
									.maintainStatusAssociations(grupo.getStatusList(), response, grupo.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
											historicoDAC, processId, null);
				}
				break;
			case DELETE:
				count = grupoDAC.deleteGrupoProd(grupo, response);
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				count =
						StatusDACD.maintainStatusAssociations(statusList, response, grupo.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC, processId,
								null);

				break;
			case NOME:
			
				count = grupoDAC.insertGrupoProd(grupo,
						"insertGrupo", response);
						
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
	
	public static Integer maintainGrupoAssociations(Grupo grupo,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IGrupoDAC grupoDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC, Integer empId,
			String UserId, Integer processId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNull(grupo))
		{
			return count;
		}

		// Make sure we set the parent key
		grupo.setParentId(parentId);

		switch (grupo.getModelAction())
		{
			case INSERT:
					
				count = grupoDAC.insertGrupo(grupo,
						"insertGrupo", response);
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
				count = grupoDAC.updateGrupo(grupo, response);
				if (count > 0)
				{
					count =
							StatusDACD
									.maintainStatusAssociations(grupo.getStatusList(), response, grupo.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
											historicoDAC, processId, null);
				}
				break;
			case DELETE:
				count = grupoDAC.deleteGrupoProd(grupo, response);
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				count =
						StatusDACD.maintainStatusAssociations(statusList, response, grupo.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC, processId,
								null);

				break;
		}

		return count;
	}
}
