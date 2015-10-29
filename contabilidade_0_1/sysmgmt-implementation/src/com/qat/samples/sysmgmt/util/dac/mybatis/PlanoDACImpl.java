package com.qat.samples.sysmgmt.util.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.contabilidade.Plano;
import com.qat.samples.sysmgmt.fiscal.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.dac.IProdutoDAC;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;
import com.qat.samples.sysmgmt.util.dac.IPlanoDAC;
import com.qat.samples.sysmgmt.util.dac.IStatusDAC;

/**
 * The Class PlanoDACImpl.
 */
public class PlanoDACImpl extends SqlSessionDaoSupport implements IPlanoDAC
{
	/** The Constant PLANO_NAMESPACE. */
	private static final String PLANO_NAMESPACE = "PlanoMap.";

	/** The Constant PLANO_STMT_UPDATE. */
	private static final String PLANO_STMT_UPDATE = PLANO_NAMESPACE + "updatePlano";

	/** The Constant PLANO_STMT_DELETE_BUSINESS_PLANO. */
	private static final String PLANO_STMT_DELETE = PLANO_NAMESPACE + "deletePlano";

	/** The Constant PLANO_STMT_INSERT. */
	private static final String PLANO_STMT_INSERT = PLANO_NAMESPACE + "insertPlano";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PlanoDACImpl.class);

	// private ITabPrecoDAC tabPrecoDAC;
	private IHistoricoDAC historicoDAC;
	private IStatusDAC statusDAC;
	private IProdutoDAC produtoDAC;

	/**
	 * @return the produtoDAC
	 */
	public IProdutoDAC getProdutoDAC()
	{
		return produtoDAC;
	}

	/**
	 * @param produtoDAC the produtoDAC to set
	 */
	public void setProdutoDAC(IProdutoDAC produtoDAC)
	{
		this.produtoDAC = produtoDAC;
	}

	// /**
	// * @return the tabPrecoDAC
	// */
	// public ITabPrecoDAC getTabPrecoDAC()
	// {
	// return tabPrecoDAC;
	// }
	//
	// /**
	// * @param tabPrecoDAC the tabPrecoDAC to set
	// */
	// public void setTabPrecoDAC(ITabPrecoDAC tabPrecoDAC)
	// {
	// this.tabPrecoDAC = tabPrecoDAC;
	// }

	/**
	 * @return the historicoDAC
	 */
	public IHistoricoDAC getHistoricoDAC()
	{
		return historicoDAC;
	}

	/**
	 * @param historicoDAC the historicoDAC to set
	 */
	public void setHistoricoDAC(IHistoricoDAC historicoDAC)
	{
		this.historicoDAC = historicoDAC;
	}

	/**
	 * @return the statusDAC
	 */
	public IStatusDAC getStatusDAC()
	{
		return statusDAC;
	}

	/**
	 * @param statusDAC the statusDAC to set
	 */
	public void setStatusDAC(IStatusDAC statusDAC)
	{
		this.statusDAC = statusDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertPlano(com.prosperitasglobal.cbof.model.Plano,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertPlano(Plano plano, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root plano data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), PLANO_STMT_INSERT, plano, response);

		// ProdutoPessoa pessoa = new ProdutoPessoa();
		//
		// pessoa.setParentId(plano.getId());
		// pessoa.setProcessId(plano.getProcessId());
		// pessoa.setProdId(plano.getProduto());
		// Date a = new Date();
		// pessoa.setCreateDateUTC(a.getTime());
		// pessoa.setCreateUser(plano.getUserId());
		//
		// insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), "ProdutoMap.insertProdutoPessoa", pessoa,
		// response);

		// insertCount +=
		// PrecoDACD.maintainTabPrecoAssociations(plano.getPreco(), response, insertCount, null,
		// null,
		// null, getTabPrecoDAC(), getStatusDAC(), getHistoricoDAC(), plano.getEmprId(),
		// plano.getCreateUser(), plano.getProcessId());

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updatePlano(com.prosperitasglobal.cbof.model.Plano,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updatePlano(Plano plano, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(plano.getModelAction())
				&& (plano.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), PLANO_STMT_UPDATE, plano, response);

			if (updateCount == 1)
			{
				plano.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}

			// updateCount +=
			// PrecoDACD.maintainTabPrecoAssociations(plano.getPreco(), response, plano.getId(), null,
			// null,
			// TabelaEnum.PLANO, getTabPrecoDAC(), getStatusDAC(), getHistoricoDAC(), plano.getId(),
			// plano.getCreateUser(), plano.getProcessId());
		}

		return updateCount;
	}

	@Override
	public Integer deletePlano(Plano plano, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(plano.getModelAction())
				&& (plano.getModelAction() == QATModel.PersistanceActionEnum.DELETE))
		{
			updateCount = QATMyBatisDacHelper.doRemove(getSqlSession(), PLANO_STMT_DELETE, plano, response);

			if (updateCount == 1)
			{
				plano.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public InternalResultsResponse<Plano> fetchPlanoById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Plano> fetchPlanoByRequest(PlanoInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
