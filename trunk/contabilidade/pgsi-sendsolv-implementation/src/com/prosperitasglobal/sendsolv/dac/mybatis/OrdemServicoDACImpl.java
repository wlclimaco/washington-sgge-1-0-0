package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.dac.IContactDAC;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.IOrdemServicoDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEvent;
import com.prosperitasglobal.sendsolv.model.OrdemServico;
import com.prosperitasglobal.sendsolv.model.request.OrdemServicoInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class OrdemServicoDACImpl.
 */
public class OrdemServicoDACImpl extends SqlSessionDaoSupport implements IOrdemServicoDAC
{

	/** The Constant ORDEMSERVICO_NAMESPACE. */
	private static final String ORDEMSERVICO_NAMESPACE = "OrdemServicoMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant ORDEMSERVICO_STMT_FETCH_COUNT. */
	private static final String ORDEMSERVICO_STMT_FETCH_COUNT = ORDEMSERVICO_NAMESPACE + "fetchOrdemServicoRowCount";

	/** The Constant ORDEMSERVICO_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String ORDEMSERVICO_STMT_FETCH_ALL_BY_REQUEST = ORDEMSERVICO_NAMESPACE
			+ "fetchAllOrdemServicosByRequest";

	/** The Constant ORDEMSERVICO_STMT_FETCH_BY_ID. */
	private static final String ORDEMSERVICO_STMT_FETCH_BY_ID = ORDEMSERVICO_NAMESPACE + "fetchOrdemServicoById";

	/** The Constant ORDEMSERVICO_STMT_INSERT. */
	private static final String ORDEMSERVICO_STMT_INSERT = ORDEMSERVICO_NAMESPACE + "insertOrdemServico";

	/** The Constant ORDEMSERVICO_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String ORDEMSERVICO_STMT_ASSOC_ORG_TO_CONTACT = ORDEMSERVICO_NAMESPACE
			+ "associateOrdemServicoWithContact";

	/** The Constant ORDEMSERVICO_STMT_UPDATE. */
	private static final String ORDEMSERVICO_STMT_UPDATE = ORDEMSERVICO_NAMESPACE + "updateOrdemServico";

	/** The Constant ORDEMSERVICO_STMT_DELETE. */
	private static final String ORDEMSERVICO_STMT_DELETE = ORDEMSERVICO_NAMESPACE + "deleteOrdemServicoById";

	/** The Constant ORDEMSERVICO_DOCUMENT_STMT_UPDATE. */
	private static final String ORDEMSERVICO_DOCUMENT_STMT_UPDATE = ORDEMSERVICO_NAMESPACE
			+ "updateOrdemServicoDocument";

	/** The Constant ORDEMSERVICO_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String ORDEMSERVICO_STMT_ASSOC_ORG_TO_DOCUMENT = ORDEMSERVICO_NAMESPACE
			+ "associateOrdemServicoWithDocument";

	/** The Constant ORDEMSERVICO_STMT_DELETE_DOCUMENT. */
	private static final String ORDEMSERVICO_STMT_DELETE_DOCUMENT = ORDEMSERVICO_NAMESPACE
			+ "deleteOrdemServicoDocument";

	/** The Constant STMT_VERSION. */
	private static final String ORDEMSERVICO_STMT_VERSION = ORDEMSERVICO_NAMESPACE + "fetchVersionNumber";

	/** The Constant ORDEMSERVICO_STMT_UPDATE_ORDEMSERVICO_STATUS. */
	private static final String ORDEMSERVICO_STMT_UPDATE_ORDEMSERVICO_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(OrdemServicoDACImpl.class);

	/** The contact dac. */
	private IContactDAC contactDAC;

	/** The valid sort fields for an ordemServico inquiry. Will be injected by Spring. */
	private Map<String, String> ordemServicoInquiryValidSortFields;

	/**
	 * Gets the contact dac.
	 *
	 * @return the contact dac
	 */
	public IContactDAC getContactDAC()
	{
		return contactDAC;
	}

	/**
	 * Sets the contact dac.
	 *
	 * @param contactDAC the contact dac
	 */
	public void setContactDAC(IContactDAC contactDAC)
	{
		this.contactDAC = contactDAC;
	}

	/**
	 * Get the valid sort fields for the ordemServico inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the ordemServico inquiry.
	 */
	public Map<String, String> getOrdemServicoInquiryValidSortFields()
	{
		return ordemServicoInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the ordemServico inquiry. Attribute injected by Spring.
	 *
	 * @param ordemServicoInquiryValidSortFields The valid sort fields for the ordemServico inquiry to set.
	 */
	public void setOrdemServicoInquiryValidSortFields(Map<String, String> ordemServicoInquiryValidSortFields)
	{
		this.ordemServicoInquiryValidSortFields = ordemServicoInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrdemServicoDAC#insertOrdemServico(com.prosperitasglobal.sendsolv.model
	 * .OrdemServico)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> insertOrdemServico(OrdemServico ordemServico)
	{
		Integer insertCount = 0;
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), ORDEMSERVICO_STMT_INSERT, ordemServico, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainOrdemServicoAssociations(ordemServico, response);

		// Finally, if something was inserted then add the OrdemServico to the result.
		if (insertCount > 0)
		{
			response.addResult(ordemServico);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrdemServicoDAC#updateOrdemServico(com.prosperitasglobal.sendsolv.model
	 * .OrdemServico)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> updateOrdemServico(OrdemServico ordemServico)
	{
		Integer updateCount = 0;
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(ordemServico.getModelAction())
				&& (ordemServico.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), ORDEMSERVICO_STMT_UPDATE, ordemServico, response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainOrdemServicoAssociations(ordemServico, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(ordemServico);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrdemServicoDAC#deleteOrdemServico(com.prosperitasglobal.sendsolv.model
	 * .OrdemServico)
	 */
	@Override
	public InternalResponse deleteOrdemServico(OrdemServico ordemServico)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), ORDEMSERVICO_STMT_DELETE, ordemServico, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IOrdemServicoDAC#fetchOrdemServicoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request)
	{
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), ORDEMSERVICO_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrdemServicoDAC#fetchOrdemServicoByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoByRequest(OrdemServicoInquiryRequest request)
	{
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getOrdemServicoInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, ORDEMSERVICO_STMT_FETCH_COUNT,
				ORDEMSERVICO_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain ordemServico associations.
	 *
	 * @param ordemServico the ordemServico
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainOrdemServicoAssociations(OrdemServico ordemServico,
			InternalResultsResponse<OrdemServico> response)
	{
		Integer count = 0;
		// First Maintain Contacts
		// if (ValidationUtil.isNullOrEmpty(ordemServico.getContactList()))
		// {
		// return count;
		// }
		// // For Each Contact...
		// for (Contact contact : ordemServico.getContactList())
		// {
		// // Make sure we set the parent key
		// contact.setParentKey(ordemServico.getId());
		//
		// if (ValidationUtil.isNull(contact.getModelAction()))
		// {
		// continue;
		// }
		// switch (contact.getModelAction())
		// {
		// case INSERT:
		// count = getContactDAC().insertContact(contact,
		// ORDEMSERVICO_STMT_ASSOC_ORG_TO_CONTACT, response);
		// break;
		// case UPDATE:
		// count = getContactDAC().updateContact(contact, response);
		// break;
		// case DELETE:
		// count = getContactDAC().deleteBusinessContact(contact, response);
		// break;
		// default:
		// if (LOG.isDebugEnabled())
		// {
		// LOG.debug("ModelAction for OrdemServico missing!");
		// }
		// break;
		// }
		// }
		return count;
	}

	@Override
	public InternalResultsResponse<FrequencyBasedEvent> fetchFrequencyBasedEventById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
