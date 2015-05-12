package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ICnaeDAC;
import com.prosperitasglobal.sendsolv.dac.IEmailDAC;
import com.prosperitasglobal.sendsolv.dac.IEmpresaDAC;
import com.prosperitasglobal.sendsolv.dac.IEnderecoDAC;
import com.prosperitasglobal.sendsolv.dac.IEventoDAC;
import com.prosperitasglobal.sendsolv.dac.ISociosDAC;
import com.prosperitasglobal.sendsolv.dac.ITelefoneDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.Cnae;
import com.prosperitasglobal.sendsolv.model.Documento;
import com.prosperitasglobal.sendsolv.model.Email;
import com.prosperitasglobal.sendsolv.model.Empresa;
import com.prosperitasglobal.sendsolv.model.Endereco;
import com.prosperitasglobal.sendsolv.model.Telefone;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class EmpresaDACImpl.
 */
public class EmpresaDACImpl extends SqlSessionDaoSupport implements IEmpresaDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "EmpresaMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchEmpresaRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllEmpresasByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchEmpresaById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertEmpresa";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateEmpresaWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateEmpresa";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteEmpresaById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateEmpresaDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateEmpresaWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteEmpresaDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EmpresaDACImpl.class);

	/** The valid sort fields for an empresa inquiry. Will be injected by Spring. */
	private Map<String, String> empresaInquiryValidSortFields;

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
	 * Get the valid sort fields for the empresa inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the empresa inquiry.
	 */
	public Map<String, String> getEmpresaInquiryValidSortFields()
	{
		return empresaInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the empresa inquiry. Attribute injected by Spring.
	 *
	 * @param empresaInquiryValidSortFields The valid sort fields for the empresa inquiry to set.
	 */
	public void setEmpresaInquiryValidSortFields(Map<String, String> empresaInquiryValidSortFields)
	{
		this.empresaInquiryValidSortFields = empresaInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#insertEmpresa(com.prosperitasglobal.sendsolv.model
	 * .Empresa)
	 */
	@Override
	public InternalResultsResponse<Empresa> insertEmpresa(Empresa empresa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, empresa, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainEmpresaAssociations(empresa, response);

		insertCount += maintainEmpresaAssociationsCnae(empresa, response);

		insertCount += maintainEmpresaAssociationsEmail(empresa, response);

		insertCount += maintainEmpresaAssociationsTelefone(empresa, response);

		// Finally, if something was inserted then add the Empresa to the result.
		if (insertCount > 0)
		{
			response.addResult(empresa);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#updateEmpresa(com.prosperitasglobal.sendsolv.model
	 * .Empresa)
	 */
	@Override
	public InternalResultsResponse<Empresa> updateEmpresa(Empresa empresa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(empresa.getModelAction())
				&& (empresa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, empresa,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainEmpresaAssociations(empresa, response);

		updateCount += maintainEmpresaAssociationsCnae(empresa, response);

		updateCount += maintainEmpresaAssociationsEmail(empresa, response);

		updateCount += maintainEmpresaAssociationsTelefone(empresa, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(empresa);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#deleteEmpresa(com.prosperitasglobal.sendsolv.model
	 * .Empresa)
	 */
	@Override
	public InternalResponse deleteEmpresa(Empresa empresa)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, empresa, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#fetchEmpresaById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request)
	{
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#fetchEmpresaByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchEmpresaByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain empresa associations.
	 *
	 * @param empresa the empresa
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainEmpresaAssociations(Empresa empresa,
			InternalResultsResponse<Empresa> response)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(empresa.getEnderecos()))
		{
			return count;
		}
		// For Each Contact...
		for (Endereco endereco : empresa.getEnderecos())
		{
			// Make sure we set the parent key
			endereco.setParentKey(empresa.getId());

			if (ValidationUtil.isNull(endereco.getModelAction()))
			{
				continue;
			}
			// switch (endereco.getModelAction())
			// {
			// case INSERT:
			// count = getEnderecoDAC().insertEndereco(endereco) Endereco(endereco,
			// EMPRESA_STMT_ASSOC_ORG_TO_CONTACT, response);
			// break;
			// case UPDATE:
			// count = getEnderecoDAC().updateEndereco(endereco, response);
			// break;
			// case DELETE:
			// count = getEnderecoDAC().deleteEndereco(endereco, response);
			// break;
			// default:
			// if (LOG.isDebugEnabled())
			// {
			// LOG.debug("ModelAction for Empresa missing!");
			// }
			// break;
			// }
		}
		return count;
	}

	private Integer maintainEmpresaAssociationsCnae(Empresa empresa,
			InternalResultsResponse<Empresa> response)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(empresa.getCnaes()))
		{
			return count;
		}
		// For Each Contact...
		for (Cnae cnae : empresa.getCnaes())
		{
			// Make sure we set the parent key
			cnae.setParentKey(empresa.getId());

			if (ValidationUtil.isNull(cnae.getModelAction()))
			{
				continue;
			}
			// switch (cnae.getModelAction())
			// {
			// case INSERT:
			// count = getCnaeDAC().insertCnae(cnae,
			// EMPRESA_STMT_ASSOC_ORG_TO_CONTACT, response);
			// break;
			// case UPDATE:
			// count = getCnaeDAC().updateCnae(cnae, response);
			// break;
			// case DELETE:
			// count = getCnaeDAC().deleteCnae(cnae, response);
			// break;
			// default:
			// if (LOG.isDebugEnabled())
			// {
			// LOG.debug("ModelAction for Empresa missing!");
			// }
			// break;
			// }
		}
		return count;
	}

	private Integer maintainEmpresaAssociationsTelefone(Empresa empresa,
			InternalResultsResponse<Empresa> response)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(empresa.getCnaes()))
		{
			return count;
		}
		// For Each Contact...
		for (Telefone telefone : empresa.getTelefones())
		{
			// Make sure we set the parent key
			// contact.setParentKey(empresa.getId());
			//
			// if (ValidationUtil.isNull(empresa.getModelAction()))
			// {
			// continue;
			// }
			// switch (empresa.getModelAction())
			// {
			// case INSERT:
			// count = getTelefoneDAC().insertTelefone(telefone,
			// EMPRESA_STMT_ASSOC_ORG_TO_CONTACT, response);
			// break;
			// case UPDATE:
			// count = getTelefoneDAC().updateTelefone(telefone, response);
			// break;
			// case DELETE:
			// count = getTelefoneDAC().deleteTelefone(telefone, response);
			// break;
			// default:
			// if (LOG.isDebugEnabled())
			// {
			// LOG.debug("ModelAction for Empresa missing!");
			// }
			// break;
			// }
		}
		return count;
	}

	private Integer maintainEmpresaAssociationsEmail(Empresa empresa,
			InternalResultsResponse<Empresa> response)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(empresa.getEmails()))
		{
			return count;
		}
		// For Each Contact...
		for (Email email : empresa.getEmails())
		{
			// Make sure we set the parent key
			email.setParentKey(empresa.getId());

			if (ValidationUtil.isNull(empresa.getModelAction()))
			{
				continue;
			}
			// switch (email.getModelAction())
			// {
			// case INSERT:
			// count = getEmailDAC().insertEmail(email,
			// EMPRESA_STMT_ASSOC_ORG_TO_CONTACT, response);
			// break;
			// case UPDATE:
			// count = getEmailDAC().updateEmail(email, response);
			// break;
			// case DELETE:
			// count = getEmailDAC().deleteEmail(email, response);
			// break;
			// default:
			// if (LOG.isDebugEnabled())
			// {
			// LOG.debug("ModelAction for Empresa missing!");
			// }
			// break;
			// }
		}
		return count;
	}

	private Integer maintainEmpresaAssociationsDocs(Empresa empresa,
			InternalResultsResponse<Empresa> response)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(empresa.getDocumentos()))
		{
			return count;
		}
		// For Each Contact...
		for (Documento documentos : empresa.getDocumentos())
		{
			// Make sure we set the parent key
			documentos.setParentKey(empresa.getId());

			if (ValidationUtil.isNull(documentos.getModelAction()))
			{
				continue;
			}
			// switch (documentos.getDocumentos())
			// {
			// case INSERT:
			// count = getDocumentoDAC().insertDocumento(documentos,
			// EMPRESA_STMT_ASSOC_ORG_TO_CONTACT, response);
			// break;
			// case UPDATE:
			// count = getDocumentoDAC().updateDocumento(documentos, response);
			// break;
			// case DELETE:
			// count = getDocumentoDAC().deleteDocumento(documentos, response);
			// break;
			// default:
			// if (LOG.isDebugEnabled())
			// {
			// LOG.debug("ModelAction for Empresa missing!");
			// }
			// break;
			// }
		}
		return count;
	}

	@Override
	public InternalResultsResponse<Empresa> fetchAllEmpresas()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
