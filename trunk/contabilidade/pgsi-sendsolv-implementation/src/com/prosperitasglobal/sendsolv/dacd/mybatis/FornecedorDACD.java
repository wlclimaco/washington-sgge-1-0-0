package com.prosperitasglobal.sendsolv.dacd.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class FornecedorDACD extends SqlSessionDaoSupport
{

	/** The Constant ZERO. */
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
	// public static Integer maintainFornecedorAssociations(List<Fornecedor> fornecedorList,
	// InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
	// TabelaEnum tabelaEnum, IPessoaDAC fornecedorDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
	// Integer empId,
	// String UserId)
	// {
	// Integer count = 0;
	// // First Maintain Empresa
	// if (ValidationUtil.isNullOrEmpty(fornecedorList))
	// {
	// return count;
	// }
	// // For Each Contact...
	// for (Fornecedor fornecedor : fornecedorList)
	// {
	// // Make sure we set the parent key
	// fornecedor.setParentId(parentId);
	//
	// if (ValidationUtil.isNull(fornecedor.getModelAction()))
	// {
	// continue;
	// }
	// switch (fornecedor.getModelAction())
	// {
	// case INSERT:
	// count = fornecedorDAC.insertFornecedor(fornecedor,
	// "insertFornecedor", response);
	// if (count > 0)
	// {
	// Status status = new Status();
	// status.setStatus(StatusEnum.ACTIVE);
	// List<Status> statusList = new ArrayList<Status>();
	// count =
	// StatusDACD.maintainStatusAssociations(statusList, response, count, null,
	// AcaoEnum.INSERT, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC);
	// }
	// break;
	// case UPDATE:
	// count = fornecedorDAC.updateFornecedor(fornecedor, response);
	// if (count > 0)
	// {
	// count =
	// StatusDACD
	// .maintainStatusAssociations(fornecedor.getStatusList(), response,
	// fornecedor.getId(),
	// null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.BANCO, statusDAC,
	// historicoDAC);
	// }
	// break;
	// case DELETE:
	//
	// Status status = new Status();
	// status.setStatus(StatusEnum.INACTIVE);
	// List<Status> statusList = new ArrayList<Status>();
	// count =
	// StatusDACD.maintainStatusAssociations(statusList, response, fornecedor.getId(), null,
	// AcaoEnum.DELETE, UserId, empId, TabelaEnum.BANCO, statusDAC, historicoDAC);
	//
	// break;
	// }
	// }
	//
	// return count;
	// }
}
