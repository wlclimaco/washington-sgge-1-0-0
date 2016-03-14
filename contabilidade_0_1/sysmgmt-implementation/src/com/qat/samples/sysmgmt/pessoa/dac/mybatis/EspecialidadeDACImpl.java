package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.clinica.Especialidade;
import com.qat.samples.sysmgmt.clinica.EspecialidadePessoa;
import com.qat.samples.sysmgmt.dac.IEnderecoDAC;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IEspecialidadeDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IEventosDAC;
import com.qat.samples.sysmgmt.util.dac.ICnaeDAC;
import com.qat.samples.sysmgmt.util.dac.IEmailDAC;
import com.qat.samples.sysmgmt.util.dac.ISociosDAC;
import com.qat.samples.sysmgmt.util.dac.ITelefoneDAC;

/**
 * The Class EspecialidadeDACImpl.
 */
public class EspecialidadeDACImpl extends SqlSessionDaoSupport implements IEspecialidadeDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "EspecialidadeMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchEspecialidadeRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllEspecialidadesByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchEspecialidadeById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertEspecialidade";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateEspecialidade";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteEspecialidadeById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EspecialidadeDACImpl.class);

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
	public Map<String, String> getEspecialidadeInquiryValidSortFields()
	{
		return bancoInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the banco inquiry. Attribute injected by Spring.
	 * 
	 * @param bancoInquiryValidSortFields The valid sort fields for the banco inquiry to set.
	 */
	public void setEspecialidadeInquiryValidSortFields(Map<String, String> bancoInquiryValidSortFields)
	{
		this.bancoInquiryValidSortFields = bancoInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEspecialidadeDAC#insertEspecialidade(com.prosperitasglobal.sendsolv.model
	 * .Especialidade)
	 */
	@Override
	public Integer insertEspecialidade(Especialidade banco)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Especialidade> response = new InternalResultsResponse<Especialidade>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, banco, response);

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was inserted then add the Especialidade to the result.
		if (insertCount > 0)
		{
			response.addResult(banco);
		}

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEspecialidadeDAC#updateEspecialidade(com.prosperitasglobal.sendsolv.model
	 * .Especialidade)
	 */
	@Override
	public Integer updateEspecialidade(Especialidade banco)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Especialidade> response = new InternalResultsResponse<Especialidade>();

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
	 * com.prosperitasglobal.sendsolv.dac.IEspecialidadeDAC#deleteEspecialidade(com.prosperitasglobal.sendsolv.model
	 * .Especialidade)
	 */
	@Override
	public Integer deleteEspecialidade(Especialidade banco)
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
	 * @see com.prosperitasglobal.sendsolv.dac.IEspecialidadeDAC#fetchEspecialidadeById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Especialidade> fetchEspecialidadeById(FetchByIdRequest request)
	{
		InternalResultsResponse<Especialidade> response = new InternalResultsResponse<Especialidade>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEspecialidadeDAC#fetchEspecialidadeByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Especialidade> fetchEspecialidadeByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Especialidade> response = new InternalResultsResponse<Especialidade>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEspecialidadeInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Especialidade> fetchAllEspecialidades()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateEspecialidadePessoa(EspecialidadePessoa banco)
	{
		Integer updateCount = 0;
		InternalResultsResponse<EspecialidadePessoa> response = new InternalResultsResponse<EspecialidadePessoa>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(banco.getModelAction())
				&& (banco.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "EspecialidadeMap.updateEspecialidadePessoa", banco,
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
	public Integer insertEspecialidadePessoa(EspecialidadePessoa banco)
	{
		Integer insertCount = 0;
		InternalResultsResponse<EspecialidadePessoa> response = new InternalResultsResponse<EspecialidadePessoa>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "EspecialidadeMap.insertEspecialidade",
						banco.getEspecialidade(), response);

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "EspecialidadeMap.insertEspecialidadePessoa", banco,
						response);

		if (response.isInError())
		{
			return null;
		}
		// Finally, if something was inserted then add the Especialidade to the result.
		if (insertCount > 0)
		{
			response.addResult(banco);
		}

		return insertCount;
	}

	@Override
	public Integer deleteEspecialidadePessoa(EspecialidadePessoa banco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "EspecialidadeMap.deleteEspecialidadePessoa", banco, response);
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
