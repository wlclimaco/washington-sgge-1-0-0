package com.qat.samples.sysmgmt.arquivo.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.arquivo.Arquivo;
import com.qat.samples.sysmgmt.arquivo.dac.IArquivoDAC;
import com.qat.samples.sysmgmt.dac.IEnderecoDAC;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IEventosDAC;
import com.qat.samples.sysmgmt.util.dac.ICnaeDAC;
import com.qat.samples.sysmgmt.util.dac.IEmailDAC;
import com.qat.samples.sysmgmt.util.dac.ISociosDAC;
import com.qat.samples.sysmgmt.util.dac.ITelefoneDAC;

/**
 * The Class ArquivoDACImpl.
 */
public class ArquivoDACImpl extends SqlSessionDaoSupport implements IArquivoDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "ArquivoMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchArquivoRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllArquivosByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchArquivoById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertArquivo";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateArquivoWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateArquivo";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteArquivoById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateArquivoDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateArquivoWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteArquivoDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ArquivoDACImpl.class);

	/** The valid sort fields for an arquivo inquiry. Will be injected by Spring. */
	private Map<String, String> arquivoInquiryValidSortFields;

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
	 * Get the valid sort fields for the arquivo inquiry. Attribute injected by Spring.
	 * 
	 * @return The valid sort fields for the arquivo inquiry.
	 */
	public Map<String, String> getArquivoInquiryValidSortFields()
	{
		return arquivoInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the arquivo inquiry. Attribute injected by Spring.
	 * 
	 * @param arquivoInquiryValidSortFields The valid sort fields for the arquivo inquiry to set.
	 */
	public void setArquivoInquiryValidSortFields(Map<String, String> arquivoInquiryValidSortFields)
	{
		this.arquivoInquiryValidSortFields = arquivoInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IArquivoDAC#insertArquivo(com.prosperitasglobal.sendsolv.model
	 * .Arquivo)
	 */
	@Override
	public InternalResultsResponse<Arquivo> insertArquivo(Arquivo arquivo)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Arquivo> response = new InternalResultsResponse<Arquivo>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, arquivo, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainArquivoAssociations(arquivo, response);

		// Finally, if something was inserted then add the Arquivo to the result.
		if (insertCount > 0)
		{
			response.addResult(arquivo);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IArquivoDAC#updateArquivo(com.prosperitasglobal.sendsolv.model
	 * .Arquivo)
	 */
	@Override
	public InternalResultsResponse<Arquivo> updateArquivo(Arquivo arquivo)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Arquivo> response = new InternalResultsResponse<Arquivo>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(arquivo.getModelAction())
				&& (arquivo.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, arquivo,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainArquivoAssociations(arquivo, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(arquivo);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IArquivoDAC#deleteArquivo(com.prosperitasglobal.sendsolv.model
	 * .Arquivo)
	 */
	@Override
	public InternalResponse deleteArquivo(Arquivo arquivo)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, arquivo, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IArquivoDAC#fetchArquivoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Arquivo> fetchArquivoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Arquivo> response = new InternalResultsResponse<Arquivo>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IArquivoDAC#fetchArquivoByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Arquivo> fetchArquivoByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Arquivo> response = new InternalResultsResponse<Arquivo>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getArquivoInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain arquivo associations.
	 * 
	 * @param arquivo the arquivo
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainArquivoAssociations(Arquivo arquivo,
			InternalResultsResponse<Arquivo> response)
	{
		Integer count = 0;

		return count;
	}

	@Override
	public InternalResultsResponse<Arquivo> fetchAllArquivos()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
