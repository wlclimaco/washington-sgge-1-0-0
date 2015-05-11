package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.dac.IContactDAC;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.IFuncionarioDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEvent;
import com.prosperitasglobal.sendsolv.model.Funcionario;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class FuncionarioDACImpl.
 */
public class FuncionarioDACImpl extends SqlSessionDaoSupport implements IFuncionarioDAC
{

	/** The Constant FUNCIONARIO_NAMESPACE. */
	private static final String FUNCIONARIO_NAMESPACE = "FuncionarioMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant FUNCIONARIO_STMT_FETCH_COUNT. */
	private static final String FUNCIONARIO_STMT_FETCH_COUNT = FUNCIONARIO_NAMESPACE + "fetchFuncionarioRowCount";

	/** The Constant FUNCIONARIO_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String FUNCIONARIO_STMT_FETCH_ALL_BY_REQUEST = FUNCIONARIO_NAMESPACE
			+ "fetchAllFuncionariosByRequest";

	/** The Constant FUNCIONARIO_STMT_FETCH_BY_ID. */
	private static final String FUNCIONARIO_STMT_FETCH_BY_ID = FUNCIONARIO_NAMESPACE + "fetchFuncionarioById";

	/** The Constant FUNCIONARIO_STMT_INSERT. */
	private static final String FUNCIONARIO_STMT_INSERT = FUNCIONARIO_NAMESPACE + "insertFuncionario";

	/** The Constant FUNCIONARIO_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String FUNCIONARIO_STMT_ASSOC_ORG_TO_CONTACT = FUNCIONARIO_NAMESPACE
			+ "associateFuncionarioWithContact";

	/** The Constant FUNCIONARIO_STMT_UPDATE. */
	private static final String FUNCIONARIO_STMT_UPDATE = FUNCIONARIO_NAMESPACE + "updateFuncionario";

	/** The Constant FUNCIONARIO_STMT_DELETE. */
	private static final String FUNCIONARIO_STMT_DELETE = FUNCIONARIO_NAMESPACE + "deleteFuncionarioById";

	/** The Constant FUNCIONARIO_DOCUMENT_STMT_UPDATE. */
	private static final String FUNCIONARIO_DOCUMENT_STMT_UPDATE = FUNCIONARIO_NAMESPACE
			+ "updateFuncionarioDocument";

	/** The Constant FUNCIONARIO_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String FUNCIONARIO_STMT_ASSOC_ORG_TO_DOCUMENT = FUNCIONARIO_NAMESPACE
			+ "associateFuncionarioWithDocument";

	/** The Constant FUNCIONARIO_STMT_DELETE_DOCUMENT. */
	private static final String FUNCIONARIO_STMT_DELETE_DOCUMENT = FUNCIONARIO_NAMESPACE
			+ "deleteFuncionarioDocument";

	/** The Constant STMT_VERSION. */
	private static final String FUNCIONARIO_STMT_VERSION = FUNCIONARIO_NAMESPACE + "fetchVersionNumber";

	/** The Constant FUNCIONARIO_STMT_UPDATE_FUNCIONARIO_STATUS. */
	private static final String FUNCIONARIO_STMT_UPDATE_FUNCIONARIO_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FuncionarioDACImpl.class);

	/** The contact dac. */
	private IContactDAC contactDAC;

	/** The valid sort fields for an funcionario inquiry. Will be injected by Spring. */
	private Map<String, String> funcionarioInquiryValidSortFields;

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
	 * Get the valid sort fields for the funcionario inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the funcionario inquiry.
	 */
	public Map<String, String> getFuncionarioInquiryValidSortFields()
	{
		return funcionarioInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the funcionario inquiry. Attribute injected by Spring.
	 *
	 * @param funcionarioInquiryValidSortFields The valid sort fields for the funcionario inquiry to set.
	 */
	public void setFuncionarioInquiryValidSortFields(Map<String, String> funcionarioInquiryValidSortFields)
	{
		this.funcionarioInquiryValidSortFields = funcionarioInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IFuncionarioDAC#insertFuncionario(com.prosperitasglobal.sendsolv.model
	 * .Funcionario)
	 */
	@Override
	public InternalResultsResponse<Funcionario> insertFuncionario(Funcionario funcionario)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), FUNCIONARIO_STMT_INSERT, funcionario, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainFuncionarioAssociations(funcionario, response);

		// Finally, if something was inserted then add the Funcionario to the result.
		if (insertCount > 0)
		{
			response.addResult(funcionario);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IFuncionarioDAC#updateFuncionario(com.prosperitasglobal.sendsolv.model
	 * .Funcionario)
	 */
	@Override
	public InternalResultsResponse<Funcionario> updateFuncionario(Funcionario funcionario)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(funcionario.getModelAction())
				&& (funcionario.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), FUNCIONARIO_STMT_UPDATE, funcionario, response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainFuncionarioAssociations(funcionario, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(funcionario);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IFuncionarioDAC#deleteFuncionario(com.prosperitasglobal.sendsolv.model
	 * .Funcionario)
	 */
	@Override
	public InternalResponse deleteFuncionario(Funcionario funcionario)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), FUNCIONARIO_STMT_DELETE, funcionario, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IFuncionarioDAC#fetchFuncionarioById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request)
	{
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), FUNCIONARIO_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IFuncionarioDAC#fetchFuncionarioByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Funcionario> fetchFuncionarioByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getFuncionarioInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, FUNCIONARIO_STMT_FETCH_COUNT,
				FUNCIONARIO_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain funcionario associations.
	 *
	 * @param funcionario the funcionario
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainFuncionarioAssociations(Funcionario funcionario,
			InternalResultsResponse<Funcionario> response)
	{
		Integer count = 0;
		// // First Maintain Contacts
		// if (ValidationUtil.isNullOrEmpty(funcionario.getContactList()))
		// {
		// return count;
		// }
		// // For Each Contact...
		// for (Contact contact : funcionario.getContactList())
		// {
		// // Make sure we set the parent key
		// contact.setParentKey(funcionario.getId());
		//
		// if (ValidationUtil.isNull(contact.getModelAction()))
		// {
		// continue;
		// }
		// switch (contact.getModelAction())
		// {
		// case INSERT:
		// count = getContactDAC().insertContact(contact,
		// FUNCIONARIO_STMT_ASSOC_ORG_TO_CONTACT, response);
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
		// LOG.debug("ModelAction for Funcionario missing!");
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
	public InternalResultsResponse<Funcionario> fetchAllFuncionarios()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
