package com.qat.samples.sysmgmt.arquivo.dacd;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ArquivoDACD extends SqlSessionDaoSupport
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
	// public static Integer maintainArquivoAssociationsA(Arquivo banco,
	// InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
	// TabelaEnum tabelaEnum, IArquivoDAC bancoDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
	// Integer empId,
	// String UserId, Integer processId, Integer historicoId)
	// {
	//
	// InternalResultsResponse<Arquivo> count = 0;
	// // First Maintain Empresa
	// if (ValidationUtil.isNull(banco))
	// {
	// return count;
	// }
	//
	// // Make sure we set the parent key
	// banco.setParentId(parentId);
	// banco.setProcessId(processId);
	//
	// switch (banco.getModelAction())
	// {
	// case INSERT:
	// count = bancoDAC.insertArquivo(banco);
	// if (count > 0)
	// {
	// Status status = new Status();
	// status.setStatus(CdStatusTypeEnum.ATIVO);
	// List<Status> statusList = new ArrayList<Status>();
	// count =
	// StatusDACD.maintainStatusAssociations(statusList, response, count, null,
	// AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
	// processId, historicoId);
	// }
	// break;
	// case UPDATE:
	// count = bancoDAC.updateArquivo(banco);
	// if (count > 0)
	// {
	// count =
	// StatusDACD
	// .maintainStatusAssociations(banco.getStatusList(), response, banco.getId(),
	// null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
	// historicoDAC, processId, historicoId);
	// }
	// break;
	// case DELETE:
	// count = bancoDAC.deleteArquivo(banco);
	// Status status = new Status();
	// status.setStatus(CdStatusTypeEnum.DELETADO);
	// List<Status> statusList = new ArrayList<Status>();
	// count =
	// StatusDACD.maintainStatusAssociations(statusList, response, banco.getId(), null,
	// AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC,
	// processId, historicoId);
	//
	// break;
	// }
	//
	// return count;
	// }
}
