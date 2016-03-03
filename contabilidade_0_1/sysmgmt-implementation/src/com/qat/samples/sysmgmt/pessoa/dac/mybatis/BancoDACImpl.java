package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.banco.Banco;
import com.qat.samples.sysmgmt.banco.BancoPessoa;
import com.qat.samples.sysmgmt.dac.IEnderecoDAC;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IBancoDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IEventosDAC;
import com.qat.samples.sysmgmt.util.dac.ICnaeDAC;
import com.qat.samples.sysmgmt.util.dac.IEmailDAC;
import com.qat.samples.sysmgmt.util.dac.ISociosDAC;
import com.qat.samples.sysmgmt.util.dac.ITelefoneDAC;

/**
 * The Class BancoDACImpl.
 */
public class BancoDACImpl extends SqlSessionDaoSupport implements IBancoDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "BancoMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchBancoRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllBancosByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchBancoById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertBanco";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateBanco";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteBancoById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BancoDACImpl.class);

	/** The valid sort fields for an banco inquiry. Will be injected by Spring. */
	private Map<String, String> bancoInquiryValidSortFields;

	private IEnderecoDAC enderecoDAC;

	private IEmailDAC emailDAC;

	private ITelefoneDAC telefoneDAC;

	private ICnaeDAC cnaeDAC;

	private ISociosDAC socioDAC;

	private IEventosDAC eventosDAC;

	/**
	 * @return the enderecoDAC
	 */
	public IEnderecoDAC getEnderecoDAC()
	{
		return enderecoDAC;
	}

	/**
	 * @param enderecoDAC the enderecoDAC to set
	 */
	public void setEnderecoDAC(IEnderecoDAC enderecoDAC)
	{
		this.enderecoDAC = enderecoDAC;
	}

	/**
	 * @return the emailDAC
	 */
	public IEmailDAC getEmailDAC()
	{
		return emailDAC;
	}

	/**
	 * @param emailDAC the emailDAC to set
	 */
	public void setEmailDAC(IEmailDAC emailDAC)
	{
		this.emailDAC = emailDAC;
	}

	/**
	 * @return the telefoneDAC
	 */
	public ITelefoneDAC getTelefoneDAC()
	{
		return telefoneDAC;
	}

	/**
	 * @param telefoneDAC the telefoneDAC to set
	 */
	public void setTelefoneDAC(ITelefoneDAC telefoneDAC)
	{
		this.telefoneDAC = telefoneDAC;
	}

	/**
	 * @return the cnaeDAC
	 */
	public ICnaeDAC getCnaeDAC()
	{
		return cnaeDAC;
	}

	/**
	 * @param cnaeDAC the cnaeDAC to set
	 */
	public void setCnaeDAC(ICnaeDAC cnaeDAC)
	{
		this.cnaeDAC = cnaeDAC;
	}

	/**
	 * @return the socioDAC
	 */
	public ISociosDAC getSocioDAC()
	{
		return socioDAC;
	}

	/**
	 * @param socioDAC the socioDAC to set
	 */
	public void setSocioDAC(ISociosDAC socioDAC)
	{
		this.socioDAC = socioDAC;
	}

	/**
	 * @return the eventosDAC
	 */
	public IEventosDAC getEventosDAC()
	{
		return eventosDAC;
	}

	/**
	 * @param eventosDAC the eventosDAC to set
	 */
	public void setEventosDAC(IEventosDAC eventosDAC)
	{
		this.eventosDAC = eventosDAC;
	}

	/**
	 * Get the valid sort fields for the banco inquiry. Attribute injected by Spring.
	 * 
	 * @return The valid sort fields for the banco inquiry.
	 */
	public Map<String, String> getBancoInquiryValidSortFields()
	{
		return bancoInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the banco inquiry. Attribute injected by Spring.
	 * 
	 * @param bancoInquiryValidSortFields The valid sort fields for the banco inquiry to set.
	 */
	public void setBancoInquiryValidSortFields(Map<String, String> bancoInquiryValidSortFields)
	{
		this.bancoInquiryValidSortFields = bancoInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBancoDAC#insertBanco(com.prosperitasglobal.sendsolv.model
	 * .Banco)
	 */
	@Override
	public Integer insertBanco(Banco banco)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, banco, response);

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was inserted then add the Banco to the result.
		if (insertCount > 0)
		{
			response.addResult(banco);
		}

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBancoDAC#updateBanco(com.prosperitasglobal.sendsolv.model
	 * .Banco)
	 */
	@Override
	public Integer updateBanco(Banco banco)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(banco.getModelAction())
				&& (banco.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, banco,
							response);
		}

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(banco);
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBancoDAC#deleteBanco(com.prosperitasglobal.sendsolv.model
	 * .Banco)
	 */
	@Override
	public Integer deleteBanco(Banco banco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, banco, response);
		if (response.isInError())
		{
			return null;
		}
		else
		{
			return 1;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IBancoDAC#fetchBancoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Banco> fetchBancoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBancoDAC#fetchBancoByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Banco> fetchBancoByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getBancoInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain banco associations.
	 * 
	 * @param banco the banco
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainBancoAssociations(Banco banco,
			InternalResultsResponse<Banco> response)
	{
		Integer count = 0;

		return count;
	}

	@Override
	public InternalResultsResponse<Banco> fetchAllBancos()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateBancoPessoa(BancoPessoa banco)
	{
		Integer updateCount = 0;
		InternalResultsResponse<BancoPessoa> response = new InternalResultsResponse<BancoPessoa>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(banco.getModelAction())
				&& (banco.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "BancoMap.updateBancoPessoa", banco,
							response);
		}

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(banco);
		}

		return updateCount;
	}

	@Override
	public Integer insertBancoPessoa(BancoPessoa banco)
	{
		Integer insertCount = 0;
		InternalResultsResponse<BancoPessoa> response = new InternalResultsResponse<BancoPessoa>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), "BancoMap.insertBancoPessoa", banco, response);

		if (response.isInError())
		{
			return null;
		}
		// Finally, if something was inserted then add the Banco to the result.
		if (insertCount > 0)
		{
			response.addResult(banco);
		}

		return insertCount;
	}

	@Override
	public Integer deleteBancoPessoa(BancoPessoa banco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "BancoMap.deleteBancoPessoa", banco, response);
		if (response.isInError())
		{
			return null;
		}
		else
		{
			return 1;
		}
	}
}
