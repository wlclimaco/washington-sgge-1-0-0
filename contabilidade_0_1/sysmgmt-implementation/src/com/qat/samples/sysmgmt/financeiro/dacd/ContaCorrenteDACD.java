package com.qat.samples.sysmgmt.financeiro.dacd;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ContaCorrenteDACD extends SqlSessionDaoSupport
{

	// /** The Constant ZERO. */
	// private static final Integer ZERO = 0;
	//
	// /**
	// * Fetch objects by request.
	// *
	// * @param sqlSession the sql session
	// * @param request the request
	// * @param countStatement the count statement
	// * @param fetchPagedStatement the fetch paged statement
	// * @param response the response
	// */
	// @SuppressWarnings("unchecked")
	// public static Integer maintainPlanoAssociations(List<Plano> planoList,
	// InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
	// TabelaEnum tabelaEnum, IPlanoDAC planoDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
	// Integer empId, String UserId, Integer processId, Integer historicoId)
	// {
	// Integer count = 0;
	// // First Maintain Empresa
	// if (ValidationUtil.isNullOrEmpty(planoList))
	// {
	// return count;
	// }
	// // For Each Contact...
	// for (Plano plano : planoList)
	// {
	// // Make sure we set the parent key
	// plano.setParentId(parentId);
	// plano.setEmprId(empId);
	//
	// if (ValidationUtil.isNull(plano.getModelAction()))
	// {
	// continue;
	// }
	// switch (plano.getModelAction())
	// {
	// case INSERT:
	// count = planoDAC.insertPlano(plano,
	// "insertPlano", response);
	// if (count > 0)
	// {
	// Status status = new Status();
	// status.setStatus(CdStatusTypeEnum.ATIVO);
	// List<Status> statusList = new ArrayList<Status>();
	// statusList.add(status);
	// count =
	// StatusDACD.maintainStatusAssociations(statusList, response, count, null,
	// AcaoEnum.INSERT, UserId, empId, TabelaEnum.SOCIO, statusDAC, historicoDAC,
	// processId, historicoId);
	// }
	// break;
	// case UPDATE:
	// count = planoDAC.updatePlano(plano, response);
	// if (count > 0)
	// {
	// count =
	// StatusDACD
	// .maintainStatusAssociations(plano.getStatusList(), response, plano.getId(),
	// null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.SOCIO, statusDAC,
	// historicoDAC, processId, historicoId);
	// }
	// break;
	// case DELETE:
	// count = planoDAC.deletePlano(plano, response);
	// Status status = new Status();
	// status.setStatus(CdStatusTypeEnum.ATIVO);
	// List<Status> statusList = new ArrayList<Status>();
	// statusList.add(status);
	// count =
	// StatusDACD.maintainStatusAssociations(statusList, response, plano.getId(), null,
	// AcaoEnum.DELETE, UserId, empId, TabelaEnum.SOCIO, statusDAC, historicoDAC,
	// processId, historicoId);
	//
	// break;
	// }
	// }
	// return count;
	// }
}
