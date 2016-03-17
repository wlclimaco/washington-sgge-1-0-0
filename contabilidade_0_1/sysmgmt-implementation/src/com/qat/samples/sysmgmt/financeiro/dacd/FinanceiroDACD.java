package com.qat.samples.sysmgmt.financeiro.dacd;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.agencia.Agencia;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.pessoa.dac.IAgenciaDAC;
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
public final class FinanceiroDACD extends SqlSessionDaoSupport
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
	// public static Integer maintainAgenciaAssociations(Agencia agencia,
	// InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
	// TabelaEnum tabelaEnum, IAgenciaDAC agenciaDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
	// Integer empId,
	// String UserId, Integer processId, Integer historicoId)
	// {
	// Integer count = 0;
	// // First Maintain Empresa
	// if (ValidationUtil.isNull(agencia))
	// {
	// return count;
	// }
	//
	// // Make sure we set the parent key
	// agencia.setParentId(parentId);
	// agencia.setProcessId(processId);
	// agencia.setTabelaEnum(tabelaEnum);
	//
	// if (ValidationUtil.isNull(agencia.getModelAction()))
	// {
	// return count;
	// }
	// switch (agencia.getModelAction())
	// {
	// case INSERT:
	// count = agenciaDAC.insertAgencia(agencia);
	// if (count > 0)
	// {
	// Status status = new Status();
	// status.setStatus(CdStatusTypeEnum.ATIVO);
	// List<Status> statusList = new ArrayList<Status>();
	// count =
	// StatusDACD.maintainStatusAssociations(statusList, response, count, null,
	// AcaoEnum.INSERT, UserId, empId, TabelaEnum.AGENCIA, statusDAC, historicoDAC,
	// processId, historicoId);
	// }
	// break;
	// case UPDATE:
	// count = agenciaDAC.updateAgencia(agencia);
	// if (count > 0)
	// {
	// count =
	// StatusDACD
	// .maintainStatusAssociations(agencia.getStatusList(), response, agencia.getId(),
	// null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.AGENCIA, statusDAC,
	// historicoDAC, processId, historicoId);
	// }
	// break;
	// case DELETE:
	// count = agenciaDAC.deleteAgencia(agencia);
	// Status status = new Status();
	// status.setStatus(CdStatusTypeEnum.DELETADO);
	// List<Status> statusList = new ArrayList<Status>();
	// count =
	// StatusDACD.maintainStatusAssociations(statusList, response, agencia.getId(), null,
	// AcaoEnum.DELETE, UserId, empId, TabelaEnum.AGENCIA, statusDAC, historicoDAC,
	// processId, historicoId);
	//
	// break;
	// case NONE:
	// count = 0;
	// break;
	// }
	//
	// return count;
	// }
}
