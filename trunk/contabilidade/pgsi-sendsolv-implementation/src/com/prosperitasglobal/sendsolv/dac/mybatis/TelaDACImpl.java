package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.dac.IContactDAC;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ITelaDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.Tela;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class TelaDACImpl.
 */
public class TelaDACImpl extends SqlSessionDaoSupport implements ITelaDAC
{

	/** The Constant TELA_NAMESPACE. */
	private static final String TELA_NAMESPACE = "TelaMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant TELA_STMT_FETCH_COUNT. */
	private static final String TELA_STMT_FETCH_COUNT = TELA_NAMESPACE + "fetchTelaRowCount";

	/** The Constant TELA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String TELA_STMT_FETCH_ALL_BY_REQUEST = TELA_NAMESPACE
			+ "fetchAllTelasByRequest";

	/** The Constant TELA_STMT_FETCH_BY_ID. */
	private static final String TELA_STMT_FETCH_BY_ID = TELA_NAMESPACE + "fetchTelaById";

	/** The Constant TELA_STMT_INSERT. */
	private static final String TELA_STMT_INSERT = TELA_NAMESPACE + "insertTela";

	/** The Constant TELA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String TELA_STMT_ASSOC_ORG_TO_CONTACT = TELA_NAMESPACE
			+ "associateTelaWithContact";

	/** The Constant TELA_STMT_UPDATE. */
	private static final String TELA_STMT_UPDATE = TELA_NAMESPACE + "updateTela";

	/** The Constant TELA_STMT_DELETE. */
	private static final String TELA_STMT_DELETE = TELA_NAMESPACE + "deleteTelaById";

	/** The Constant TELA_DOCUMENT_STMT_UPDATE. */
	private static final String TELA_DOCUMENT_STMT_UPDATE = TELA_NAMESPACE
			+ "updateTelaDocument";

	/** The Constant TELA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String TELA_STMT_ASSOC_ORG_TO_DOCUMENT = TELA_NAMESPACE
			+ "associateTelaWithDocument";

	/** The Constant TELA_STMT_DELETE_DOCUMENT. */
	private static final String TELA_STMT_DELETE_DOCUMENT = TELA_NAMESPACE
			+ "deleteTelaDocument";

	/** The Constant STMT_VERSION. */
	private static final String TELA_STMT_VERSION = TELA_NAMESPACE + "fetchVersionNumber";

	/** The Constant TELA_STMT_UPDATE_TELA_STATUS. */
	private static final String TELA_STMT_UPDATE_TELA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TelaDACImpl.class);

	/** The contact dac. */
	private IContactDAC contactDAC;

	/** The valid sort fields for an tela inquiry. Will be injected by Spring. */
	private Map<String, String> telaInquiryValidSortFields;

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
	 * Get the valid sort fields for the tela inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the tela inquiry.
	 */
	public Map<String, String> getTelaInquiryValidSortFields()
	{
		return telaInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the tela inquiry. Attribute injected by Spring.
	 *
	 * @param telaInquiryValidSortFields The valid sort fields for the tela inquiry to set.
	 */
	public void setTelaInquiryValidSortFields(Map<String, String> telaInquiryValidSortFields)
	{
		this.telaInquiryValidSortFields = telaInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITelaDAC#insertTela(com.prosperitasglobal.sendsolv.model
	 * .Tela)
	 */
	@Override
	public InternalResultsResponse<Tela> insertTela(Tela tela)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Tela> response = new InternalResultsResponse<Tela>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), TELA_STMT_INSERT, tela, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainTelaAssociations(tela, response);

		// Finally, if something was inserted then add the Tela to the result.
		if (insertCount > 0)
		{
			response.addResult(tela);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITelaDAC#updateTela(com.prosperitasglobal.sendsolv.model
	 * .Tela)
	 */
	@Override
	public InternalResultsResponse<Tela> updateTela(Tela tela)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Tela> response = new InternalResultsResponse<Tela>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(tela.getModelAction())
				&& (tela.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), TELA_STMT_UPDATE, tela, response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainTelaAssociations(tela, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(tela);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITelaDAC#deleteTela(com.prosperitasglobal.sendsolv.model
	 * .Tela)
	 */
	@Override
	public InternalResponse deleteTela(Tela tela)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), TELA_STMT_DELETE, tela, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ITelaDAC#fetchTelaById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Tela> fetchTelaById(FetchByIdRequest request)
	{
		InternalResultsResponse<Tela> response = new InternalResultsResponse<Tela>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), TELA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITelaDAC#fetchTelaByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Tela> fetchTelaByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Tela> response = new InternalResultsResponse<Tela>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getTelaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, TELA_STMT_FETCH_COUNT,
				TELA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain tela associations.
	 *
	 * @param tela the tela
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainTelaAssociations(Tela tela,
			InternalResultsResponse<Tela> response)
	{
		Integer count = 0;
		// First Maintain Contacts
		// if (ValidationUtil.isNullOrEmpty(tela.getContactList()))
		// {
		// return count;
		// }
		// // For Each Contact...
		// for (Contact contact : tela.getContactList())
		// {
		// // Make sure we set the parent key
		// contact.setParentKey(tela.getId());
		//
		// if (ValidationUtil.isNull(contact.getModelAction()))
		// {
		// continue;
		// }
		// switch (contact.getModelAction())
		// {
		// case INSERT:
		// count = getContactDAC().insertContact(contact,
		// TELA_STMT_ASSOC_ORG_TO_CONTACT, response);
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
		// LOG.debug("ModelAction for Tela missing!");
		// }
		// break;
		// }
		// }
		return count;
	}
}
