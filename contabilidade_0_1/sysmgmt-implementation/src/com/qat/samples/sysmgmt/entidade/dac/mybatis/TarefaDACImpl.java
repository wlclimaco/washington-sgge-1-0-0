package com.qat.samples.sysmgmt.entidade.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.entidade.dac.ITarefaDAC;
import com.qat.samples.sysmgmt.util.Tarefa;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class TarefaDACImpl extends SqlSessionDaoSupport implements ITarefaDAC
{
	/** The Constant TAREFA_NAMESPACE. */
	private static final String TAREFA_NAMESPACE = "TarefaMap.";

	/** The Constant TAREFA_STMT_UPDATE. */
	private static final String TAREFA_STMT_UPDATE = TAREFA_NAMESPACE + "updateTarefa";

	/** The Constant TAREFA_STMT_DELETE_BUSINESS_TAREFA. */
	private static final String TAREFA_STMT_DELETE_BUSINESS_TAREFA = TAREFA_NAMESPACE + "deleteBusinessTarefa";

	/** The Constant TAREFA_STMT_INSERT. */
	private static final String TAREFA_STMT_INSERT = TAREFA_NAMESPACE + "insertTarefa";

	/** The Constant TAREFA_STMT_FETCH_BY_ID. */
	private static final String TAREFA_STMT_FETCH_BY_ID = TAREFA_NAMESPACE + "fetchTarefasById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TarefaDACImpl.class);

	private static final String CNAE_EMPRESA_STMT_UPDATE = TAREFA_NAMESPACE + "updateTarefaEmpresa";

	private static final String CNAE_EMPRESA_STMT_DELETE = TAREFA_NAMESPACE + "deleteTarefaEmpresaById";

	private static final String CNAE_EMPRESA_STMT_INSERT = TAREFA_NAMESPACE + "insertTarefaEmpresa";

	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertTarefa(com.prosperitasglobal.cbof.model.Tarefa,
	// * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	// */
	// @Override
	// public Integer insertTarefa(Tarefa tarefa)
	// {
	// InternalResultsResponse<Tarefa> response = new InternalResultsResponse<Tarefa>();
	// Integer insertCount = 0;
	// // First insert the root tarefa data
	// insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), TAREFA_STMT_INSERT, tarefa, response);
	//
	// return insertCount;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.cbof.dac.ITarefaDAC#deleteBusinessTarefa(com.prosperitasglobal.cbof.model.Tarefa,
	// * com.qat.framework.model.response.InternalResultsResponse)
	// */
	// @Override
	// public Integer deleteTarefa(Tarefa tarefa)
	// {
	// InternalResponse response = new InternalResponse();
	// return QATMyBatisDacHelper.doRemove(getSqlSession(), TAREFA_STMT_DELETE_BUSINESS_TAREFA, tarefa, response);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateTarefa(com.prosperitasglobal.cbof.model.Tarefa,
	// * com.qat.framework.model.response.InternalResultsResponse)
	// */
	// @Override
	// public Integer updateTarefa(Tarefa tarefa)
	// {
	// Integer updateCount = 0;
	// InternalResultsResponse<Tarefa> response = new InternalResultsResponse<Tarefa>();
	// // First update the root if necessary.
	// if (!ValidationUtil.isNull(tarefa.getModelAction())
	// && (tarefa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
	// {
	// updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), TAREFA_STMT_UPDATE, tarefa, response);
	//
	// if (updateCount == 1)
	// {
	// tarefa.setModelAction(QATModel.PersistanceActionEnum.NONE);
	// }
	// }
	//
	// return updateCount;
	// }

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchTarefaById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Tarefa> fetchTarefaById(Integer id)
	{
		InternalResultsResponse<Tarefa> response = new InternalResultsResponse<Tarefa>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), TAREFA_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	// @Override
	// public Integer insertTarefaEmpresa(TarefaEmpresa tarefa)
	// {
	// Integer insertCount = 0;
	// InternalResultsResponse<TarefaEmpresa> response = new InternalResultsResponse<TarefaEmpresa>();
	// // First insert the root tarefa data
	// insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CNAE_EMPRESA_STMT_INSERT, tarefa, response);
	//
	// return insertCount;
	// }

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ITarefaDAC#deleteBusinessTarefa(com.prosperitasglobal.cbof.model.Tarefa,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	// @Override
	// public Integer deleteTarefaEmpresa(TarefaEmpresa tarefa)
	// {
	// InternalResponse response = new InternalResponse();
	// return QATMyBatisDacHelper.doRemove(getSqlSession(), CNAE_EMPRESA_STMT_DELETE, tarefa, response);
	// }

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateTarefa(com.prosperitasglobal.cbof.model.Tarefa,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	// @Override
	// public Integer updateTarefaEmpresa(TarefaEmpresa tarefa)
	// {
	// Integer updateCount = 0;
	// InternalResultsResponse<TarefaEmpresa> response = new InternalResultsResponse<TarefaEmpresa>();
	// // First update the root if necessary.
	// if (!ValidationUtil.isNull(tarefa.getModelAction())
	// && (tarefa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
	// {
	// updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CNAE_EMPRESA_STMT_UPDATE, tarefa, response);
	//
	// if (updateCount == 1)
	// {
	// tarefa.setModelAction(QATModel.PersistanceActionEnum.NONE);
	// }
	// }
	//
	// return updateCount;
	// }
	// @Override
	// public InternalResultsResponse<Tarefa> fetchTarefaByRequest(PagedInquiryRequest request)
	// {
	// // TODO Auto-generated method stub
	// return null;
	// }
	// @Override
	// public Integer updateTarefa(Tarefa tarefa, InternalResultsResponse<?> response)
	// {
	// // TODO Auto-generated method stub
	// return null;
	// }
	@Override
	public Integer insertTarefa(Tarefa tarefa, String statementName, InternalResultsResponse<?> response)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteBusinessTarefa(Tarefa tarefa, InternalResultsResponse<?> response)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteTarefa(Tarefa tarefa, InternalResultsResponse<?> response)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer maintainTarefaAssociations(List<Tarefa> tarefaList, Integer parentId, String associateStatement,
			InternalResultsResponse<?> response)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateTarefa(Tarefa tarefa, InternalResultsResponse<?> response)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
