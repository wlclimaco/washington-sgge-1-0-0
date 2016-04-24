package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Fiscal.IFiscalBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.cnae.model.Cnae;
import com.qat.samples.sysmgmt.cnae.model.CnaeEmpresa;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class CnaeBARD extends SqlSessionDaoSupport
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
	public static Integer maintainCnaeAssociations(List<CnaeEmpresa> cnaeList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IFiscalBAR cnaeDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean countSucess = false;
		Integer count = ZERO;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(cnaeList))
		{
			return count;
		}
		// For Each Contact...
		for (CnaeEmpresa cnae : cnaeList)
		{
			// Make sure we set the parent key
			cnae.setParentId(parentId);
			cnae.setProcessId(processId);
			cnae.setTabelaEnum(tabelaEnum);

			if (ValidationUtil.isNull(cnae.getModelAction()))
			{
				continue;
			}
			switch (cnae.getModelAction())
			{
				case INSERT:
					countSucess =
							maintainCnaeAssociationsA(cnae.getIdCnae(), response, null, null,
									null,
									TabelaEnum.PESSOA, cnaeDAC, statusDAC, historicoDAC,
									cnae.getEmprId(),
									cnae.getCreateUser(), processId, historicoId);

					countSucess = cnaeDAC.insertCnaeEmpresa(cnae).hasSystemError();
					if (countSucess == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						countSucess =
								StatusBARD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:

					countSucess =
							maintainCnaeAssociationsA(cnae.getIdCnae(), response, null, null,
									null,
									TabelaEnum.PESSOA, cnaeDAC, statusDAC, historicoDAC,
									cnae.getEmprId(),
									cnae.getCreateUser(), processId, historicoId);

					countSucess = cnaeDAC.updateCnaeEmpresa(cnae).hasSystemError();
					if (countSucess == true)
					{
						countSucess =
								StatusBARD
										.maintainStatusAssociations(cnae.getStatusList(), response, cnae.getId(),
												null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
												historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					countSucess = cnaeDAC.deleteCnaeEmpresaById(cnae).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					countSucess =
							StatusBARD.maintainStatusAssociations(statusList, response, cnae.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, historicoId);

					break;
				case NONE:
					countSucess = cnaeDAC.insertCnaeEmpresa(cnae).hasSystemError();
					if (count > 0)
					{
						status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						statusList = new ArrayList<Status>();
						countSucess =
								StatusBARD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
			}
		}

		return count;
	}

	public static Boolean maintainCnaeAssociationsA(Cnae cnae,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IFiscalBAR cnaeDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{

		Boolean countSucess = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(cnae))
		{
			return false;
		}

		// Make sure we set the parent key
		cnae.setParentId(parentId);
		cnae.setProcessId(processId);

		switch (cnae.getModelAction())
		{
			case INSERT:
				countSucess = cnaeDAC.insertCnae(cnae).hasSystemError();
				if (countSucess == true)
				{
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.ATIVO);
					List<Status> statusList = new ArrayList<Status>();
					countSucess =
							StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
									AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
									processId, historicoId);
				}
				break;
			case UPDATE:
				countSucess = cnaeDAC.updateCnae(cnae).hasSystemError();
				if (countSucess == true)
				{
					countSucess =
							StatusBARD
									.maintainStatusAssociations(cnae.getStatusList(), response, cnae.getId(),
											null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
											historicoDAC, processId, historicoId);
				}
				break;
			case DELETE:
				countSucess = cnaeDAC.deleteCnaeById(cnae).hasSystemError();
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.DELETADO);
				List<Status> statusList = new ArrayList<Status>();
				countSucess =
						StatusBARD.maintainStatusAssociations(statusList, response, cnae.getId(), null,
								AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
								processId, historicoId);

				break;
		}

		return countSucess;
	}
}
