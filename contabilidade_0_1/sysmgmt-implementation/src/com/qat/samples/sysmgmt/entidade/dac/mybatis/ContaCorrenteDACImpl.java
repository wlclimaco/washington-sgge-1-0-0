package com.qat.samples.sysmgmt.entidade.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.conta.ContaCorrente;
import com.qat.samples.sysmgmt.entidade.dac.IContaCorrenteDAC;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class ContaCorrenteDACImpl extends SqlSessionDaoSupport implements IContaCorrenteDAC
{
	// /** The Constant CONTACORRENTE_NAMESPACE. */
	// private static final String CONTACORRENTE_NAMESPACE = "ContaCorrenteMap.";
	//
	// /** The Constant CONTACORRENTE_STMT_UPDATE. */
	// private static final String CONTACORRENTE_STMT_UPDATE = CONTACORRENTE_NAMESPACE + "updateContaCorrente";
	//
	// /** The Constant CONTACORRENTE_STMT_DELETE_BUSINESS_CONTACORRENTE. */
	// private static final String CONTACORRENTE_STMT_DELETE_BUSINESS_CONTACORRENTE = CONTACORRENTE_NAMESPACE
	// + "deleteBusinessContaCorrente";
	//
	// /** The Constant CONTACORRENTE_STMT_INSERT. */
	// private static final String CONTACORRENTE_STMT_INSERT = CONTACORRENTE_NAMESPACE + "insertContaCorrente";
	//
	// /** The Constant CONTACORRENTE_STMT_FETCH_BY_ID. */
	// private static final String CONTACORRENTE_STMT_FETCH_BY_ID = CONTACORRENTE_NAMESPACE + "fetchContaCorrentesById";
	//
	// /** The Constant LOG. */
	// private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ContaCorrenteDACImpl.class);
	//
	// private static final String CNAE_EMPRESA_STMT_UPDATE = CONTACORRENTE_NAMESPACE + "updateContaCorrenteEmpresa";
	//
	// private static final String CNAE_EMPRESA_STMT_DELETE = CONTACORRENTE_NAMESPACE +
	// "deleteContaCorrenteEmpresaById";
	//
	// private static final String CNAE_EMPRESA_STMT_INSERT = CONTACORRENTE_NAMESPACE + "insertContaCorrenteEmpresa";
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertContaCorrente(com.prosperitasglobal.cbof.model.
	// * ContaCorrente,
	// * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	// */
	// @Override
	// public Integer insertContaCorrente(ContaCorrente contaCorrente)
	// {
	// InternalResultsResponse<ContaCorrente> response = new InternalResultsResponse<ContaCorrente>();
	// Integer insertCount = 0;
	// // First insert the root contaCorrente data
	// insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACORRENTE_STMT_INSERT, contaCorrente, response);
	//
	// return insertCount;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.cbof.dac.IContaCorrenteDAC#deleteBusinessContaCorrente(com.prosperitasglobal.cbof.model.
	// * ContaCorrente,
	// * com.qat.framework.model.response.InternalResultsResponse)
	// */
	// @Override
	// public Integer deleteContaCorrente(ContaCorrente contaCorrente)
	// {
	// InternalResponse response = new InternalResponse();
	// return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACORRENTE_STMT_DELETE_BUSINESS_CONTACORRENTE,
	// contaCorrente,
	// response);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateContaCorrente(com.prosperitasglobal.cbof.model.
	// * ContaCorrente,
	// * com.qat.framework.model.response.InternalResultsResponse)
	// */
	// @Override
	// public Integer updateContaCorrente(ContaCorrente contaCorrente)
	// {
	// Integer updateCount = 0;
	// InternalResultsResponse<ContaCorrente> response = new InternalResultsResponse<ContaCorrente>();
	// // First update the root if necessary.
	// if (!ValidationUtil.isNull(contaCorrente.getModelAction())
	// && (contaCorrente.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
	// {
	// updateCount =
	// QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACORRENTE_STMT_UPDATE, contaCorrente, response);
	//
	// if (updateCount == 1)
	// {
	// contaCorrente.setModelAction(QATModel.PersistanceActionEnum.NONE);
	// }
	// }
	//
	// return updateCount;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchContaCorrenteById(java.lang.Integer)
	// */
	// @Override
	// public InternalResultsResponse<ContaCorrente> fetchContaCorrenteById(Integer id)
	// {
	// InternalResultsResponse<ContaCorrente> response = new InternalResultsResponse<ContaCorrente>();
	//
	// QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACORRENTE_STMT_FETCH_BY_ID, id, response);
	//
	// return response;
	// }
	//
	// @Override
	// public Integer insertContaCorrenteEmpresa(ContaCorrenteEmpresa contaCorrente)
	// {
	// Integer insertCount = 0;
	// InternalResultsResponse<ContaCorrenteEmpresa> response = new InternalResultsResponse<ContaCorrenteEmpresa>();
	// // First insert the root contaCorrente data
	// insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CNAE_EMPRESA_STMT_INSERT, contaCorrente, response);
	//
	// return insertCount;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.cbof.dac.IContaCorrenteDAC#deleteBusinessContaCorrente(com.prosperitasglobal.cbof.model.
	// * ContaCorrente,
	// * com.qat.framework.model.response.InternalResultsResponse)
	// */
	// @Override
	// public Integer deleteContaCorrenteEmpresa(ContaCorrenteEmpresa contaCorrente)
	// {
	// InternalResponse response = new InternalResponse();
	// return QATMyBatisDacHelper.doRemove(getSqlSession(), CNAE_EMPRESA_STMT_DELETE, contaCorrente, response);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateContaCorrente(com.prosperitasglobal.cbof.model.
	// * ContaCorrente,
	// * com.qat.framework.model.response.InternalResultsResponse)
	// */
	// @Override
	// public Integer updateContaCorrenteEmpresa(ContaCorrenteEmpresa contaCorrente)
	// {
	// Integer updateCount = 0;
	// InternalResultsResponse<ContaCorrenteEmpresa> response = new InternalResultsResponse<ContaCorrenteEmpresa>();
	// // First update the root if necessary.
	// if (!ValidationUtil.isNull(contaCorrente.getModelAction())
	// && (contaCorrente.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
	// {
	// updateCount =
	// QATMyBatisDacHelper.doUpdate(getSqlSession(), CNAE_EMPRESA_STMT_UPDATE, contaCorrente, response);
	//
	// if (updateCount == 1)
	// {
	// contaCorrente.setModelAction(QATModel.PersistanceActionEnum.NONE);
	// }
	// }
	//
	// return updateCount;
	// }
	//
	// @Override
	// public InternalResultsResponse<ContaCorrente> fetchContaCorrenteByRequest(PagedInquiryRequest request)
	// {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public Integer maintainContaCorrenteAssociations(List<ContaCorrente> contaCorrenteList, Integer parentId,
			String associateStatement, InternalResultsResponse<?> response)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateContaCorrente(ContaCorrente contaCorrente, InternalResultsResponse<?> response)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertContaCorrente(ContaCorrente contaCorrente, String statementName,
			InternalResultsResponse<?> response)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteBusinessContaCorrente(ContaCorrente contaCorrente, InternalResultsResponse<?> response)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteContaCorrente(ContaCorrente contaCorrente, InternalResultsResponse<?> response)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<ContaCorrente> fetchContaCorrenteById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<ContaCorrente> fetchContaCorrenteByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
