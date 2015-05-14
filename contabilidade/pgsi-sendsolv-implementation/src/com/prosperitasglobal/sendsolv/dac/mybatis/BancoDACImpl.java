package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import com.prosperitasglobal.sendsolv.dac.IBancoDAC;
import com.prosperitasglobal.sendsolv.dac.ICnaeDAC;
import com.prosperitasglobal.sendsolv.dac.IEmailDAC;
import com.prosperitasglobal.sendsolv.dac.IEnderecoDAC;
import com.prosperitasglobal.sendsolv.dac.IEventoDAC;
import com.prosperitasglobal.sendsolv.dac.ISociosDAC;
import com.prosperitasglobal.sendsolv.dac.ITelefoneDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;

/**
 * The Class BancoDACImpl.
 */
public class BancoDACImpl extends SqlSessionDaoSupport implements IBancoDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "BancoMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchBancoRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllBancosByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchBancoById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertBanco";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateBancoWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateBanco";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteBancoById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateBancoDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateBancoWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteBancoDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BancoDACImpl.class);

	/** The valid sort fields for an banco inquiry. Will be injected by Spring. */
	private Map<String, String> bancoInquiryValidSortFields;

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
	public InternalResultsResponse<Banco> insertBanco(Banco banco)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, banco, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainBancoAssociations(banco, response);

		insertCount += maintainBancoAssociationsCnae(banco, response);

		insertCount += maintainBancoAssociationsEmail(banco, response);

		insertCount += maintainBancoAssociationsTelefone(banco, response);

		// Finally, if something was inserted then add the Banco to the result.
		if (insertCount > 0)
		{
			response.addResult(banco);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBancoDAC#updateBanco(com.prosperitasglobal.sendsolv.model
	 * .Banco)
	 */
	@Override
	public InternalResultsResponse<Banco> updateBanco(Banco banco)
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
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainBancoAssociations(banco, response);

		updateCount += maintainBancoAssociationsCnae(banco, response);

		updateCount += maintainBancoAssociationsEmail(banco, response);

		updateCount += maintainBancoAssociationsTelefone(banco, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(banco);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBancoDAC#deleteBanco(com.prosperitasglobal.sendsolv.model
	 * .Banco)
	 */
	@Override
	public InternalResponse deleteBanco(Banco banco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, banco, response);

		return response;
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
		QATMyBatisDacHelper.translateSortFields(request, getBancoInquiryValidSortFields());

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
		// First Maintain Banco
		if (ValidationUtil.isNullOrEmpty(banco.getEnderecos()))
		{
			return count;
		}
		// For Each Contact...
		for (Endereco endereco : banco.getEnderecos())
		{
			// Make sure we set the parent key
			endereco.setParentKey(banco.getId());

			if (ValidationUtil.isNull(endereco.getModelAction()))
			{
				continue;
			}
			switch (endereco.getModelAction())
			{
				case INSERT:
					count = getEnderecoDAC().insertEndereco(endereco,
							EMPRESA_STMT_ASSOC_ORG_TO_CONTACT, response);
					break;
				case UPDATE:
					count = getEnderecoDAC().updateEndereco(endereco, response);
					break;
				case DELETE:
					count = 1; // getEnderecoDAC().deleteEndereco(endereco, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Banco missing!");
					}
					break;
			}
		}
		return count;
	}

	private Integer maintainBancoAssociationsCnae(Banco banco,
			InternalResultsResponse<Banco> response)
	{
		Integer count = 0;
		// First Maintain Banco
		if (ValidationUtil.isNullOrEmpty(banco.getCnaes()))
		{
			return count;
		}
		// For Each Contact...
		for (Cnae cnae : banco.getCnaes())
		{
			// Make sure we set the parent key
			cnae.setParentKey(banco.getId());

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
			// LOG.debug("ModelAction for Banco missing!");
			// }
			// break;
			// }
		}
		return count;
	}

	private Integer maintainBancoAssociationsTelefone(Banco banco,
			InternalResultsResponse<Banco> response)
	{
		Integer count = 0;
		// First Maintain Banco
		if (ValidationUtil.isNullOrEmpty(banco.getCnaes()))
		{
			return count;
		}
		// For Each Contact...
		for (Telefone telefone : banco.getTelefones())
		{
			// Make sure we set the parent key
			// contact.setParentKey(banco.getId());
			//
			// if (ValidationUtil.isNull(banco.getModelAction()))
			// {
			// continue;
			// }
			// switch (banco.getModelAction())
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
			// LOG.debug("ModelAction for Banco missing!");
			// }
			// break;
			// }
		}
		return count;
	}

	private Integer maintainBancoAssociationsEmail(Banco banco,
			InternalResultsResponse<Banco> response)
	{
		Integer count = 0;
		// First Maintain Banco
		if (ValidationUtil.isNullOrEmpty(banco.getEmails()))
		{
			return count;
		}
		// For Each Contact...
		for (Email email : banco.getEmails())
		{
			// Make sure we set the parent key
			email.setParentKey(banco.getId());

			if (ValidationUtil.isNull(banco.getModelAction()))
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
			// LOG.debug("ModelAction for Banco missing!");
			// }
			// break;
			// }
		}
		return count;
	}

	private Integer maintainBancoAssociationsDocs(Banco banco,
			InternalResultsResponse<Banco> response)
	{
		Integer count = 0;
		// First Maintain Banco
		if (ValidationUtil.isNullOrEmpty(banco.getDocumentos()))
		{
			return count;
		}
		// For Each Contact...
		for (Documento documentos : banco.getDocumentos())
		{
			// Make sure we set the parent key
			documentos.setParentKey(banco.getId());

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
			// LOG.debug("ModelAction for Banco missing!");
			// }
			// break;
			// }
		}
		return count;
	}

	@Override
	public InternalResultsResponse<Banco> fetchAllBancos()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
