package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC;
import com.prosperitasglobal.sendsolv.dac.ICnaeDAC;
import com.prosperitasglobal.sendsolv.dac.IEmailDAC;
import com.prosperitasglobal.sendsolv.dac.IEnderecoDAC;
import com.prosperitasglobal.sendsolv.dac.IEventoDAC;
import com.prosperitasglobal.sendsolv.dac.ISociosDAC;
import com.prosperitasglobal.sendsolv.dac.ITelefoneDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.Beneficios;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class BeneficiosDACImpl.
 */
public class BeneficiosDACImpl extends SqlSessionDaoSupport implements IBeneficiosDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "BeneficiosMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchBeneficiosRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllBeneficiossByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchBeneficiosById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertBeneficios";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateBeneficiosWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateBeneficios";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteBeneficiosById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateBeneficiosDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateBeneficiosWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteBeneficiosDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BeneficiosDACImpl.class);

	/** The valid sort fields for an beneficios inquiry. Will be injected by Spring. */
	private Map<String, String> beneficiosInquiryValidSortFields;

	private IEnderecoDAC enderecoDAC;

	private IEmailDAC emailDAC;

	private ITelefoneDAC telefoneDAC;

	private ICnaeDAC cnaeDAC;

	private ISociosDAC socioDAC;

	private IEventoDAC eventosDAC;

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
	public IEventoDAC getEventosDAC()
	{
		return eventosDAC;
	}

	/**
	 * @param eventosDAC the eventosDAC to set
	 */
	public void setEventosDAC(IEventoDAC eventosDAC)
	{
		this.eventosDAC = eventosDAC;
	}

	/**
	 * Get the valid sort fields for the beneficios inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the beneficios inquiry.
	 */
	public Map<String, String> getBeneficiosInquiryValidSortFields()
	{
		return beneficiosInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the beneficios inquiry. Attribute injected by Spring.
	 *
	 * @param beneficiosInquiryValidSortFields The valid sort fields for the beneficios inquiry to set.
	 */
	public void setBeneficiosInquiryValidSortFields(Map<String, String> beneficiosInquiryValidSortFields)
	{
		this.beneficiosInquiryValidSortFields = beneficiosInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC#insertBeneficios(com.prosperitasglobal.sendsolv.model
	 * .Beneficios)
	 */
	@Override
	public InternalResultsResponse<Beneficios> insertBeneficios(Beneficios beneficios)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, beneficios, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainBeneficiosAssociations(beneficios, response);

		// Finally, if something was inserted then add the Beneficios to the result.
		if (insertCount > 0)
		{
			response.addResult(beneficios);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC#updateBeneficios(com.prosperitasglobal.sendsolv.model
	 * .Beneficios)
	 */
	@Override
	public InternalResultsResponse<Beneficios> updateBeneficios(Beneficios beneficios)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(beneficios.getModelAction())
				&& (beneficios.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, beneficios,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainBeneficiosAssociations(beneficios, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(beneficios);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC#deleteBeneficios(com.prosperitasglobal.sendsolv.model
	 * .Beneficios)
	 */
	@Override
	public InternalResponse deleteBeneficios(Beneficios beneficios)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, beneficios, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC#fetchBeneficiosById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Beneficios> fetchBeneficiosById(FetchByIdRequest request)
	{
		InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC#fetchBeneficiosByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Beneficios> fetchBeneficiosByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getBeneficiosInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain beneficios associations.
	 *
	 * @param beneficios the beneficios
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainBeneficiosAssociations(Beneficios beneficios,
			InternalResultsResponse<Beneficios> response)
	{
		Integer count = 0;

		return count;
	}

	private Integer maintainBeneficiosAssociationsDocs(Beneficios beneficios,
			InternalResultsResponse<Beneficios> response)
	{
		Integer count = 0;

		return count;
	}

	@Override
	public InternalResultsResponse<Beneficios> fetchAllBeneficioss()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
