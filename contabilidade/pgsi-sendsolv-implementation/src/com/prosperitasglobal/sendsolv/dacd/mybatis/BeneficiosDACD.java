package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class BeneficiosDACD extends SqlSessionDaoSupport
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
	public static Integer maintainBeneficiosAssociations(List<Beneficios> cnaeList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IBeneficiosDAC cnaeDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId,
			String UserId, Integer processId, Integer historiId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(cnaeList))
		{
			return count;
		}
		// For Each Contact...
		for (Beneficios cnae : cnaeList)
		{
			// Make sure we set the parent key
			cnae.setParentId(parentId);

			if (ValidationUtil.isNull(cnae.getModelAction()))
			{
				continue;
			}
			switch (cnae.getModelAction())
			{
				case INSERT:
					count = cnaeDAC.insertBeneficios(cnae);
					if (count > 0)
					{
						Status status = new Status();
						status.setStatus(StatusEnum.ACTIVE);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, count, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.BENEFICIOS, statusDAC, historicoDAC,
										processId, historiId);
					}

					BeneficioPessoa beneficioPessoa = new BeneficioPessoa();

					beneficioPessoa.setBenefId(cnae.getId());
					beneficioPessoa.setPessoaId(parentId);
					beneficioPessoa.setProcessId(processId);

					count = cnaeDAC.insertBeneficioPesso(beneficioPessoa);

					break;
				case UPDATE:
					count = cnaeDAC.updateBeneficios(cnae);
					if (count > 0)
					{
						count =
								StatusDACD.maintainStatusAssociations(cnae.getStatusList(), response, cnae.getId(),
										null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BENEFICIOS, statusDAC,
										historicoDAC, processId, historiId);
					}

					BeneficioPessoa beneficioPessoa = new BeneficioPessoa();

					beneficioPessoa.setBenefId(cnae.getId());
					beneficioPessoa.setPessoaId(parentId);
					beneficioPessoa.setProcessId(processId);

					count = cnaeDAC.updateBeneficioPesso(beneficioPessoa);

					break;
				case DELETE:

					Status status = new Status();
					status.setStatus(StatusEnum.INACTIVE);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, cnae.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.BENEFICIOS, statusDAC, historicoDAC,
									processId, historiId);

					break;
					count = cnaeDAC.deleteBeneficioPesso(beneficioPessoa);
			}
		}

		return count;
	}
}
