package com.prosperitasglobal.cbof.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.dac.IContactDAC;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Contact;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class ContactDACImpl extends SqlSessionDaoSupport implements IContactDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "ContactMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateContact";

	/** The Constant CONTACT_STMT_UPDATE_PHONE. */
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "updatePhone";

	/** The Constant CONTACT_STMT_UPDATE_EMAIL. */
	private static final String CONTACT_STMT_UPDATE_EMAIL = CONTACT_NAMESPACE + "updateEmail";

	/** The Constant CONTACT_STMT_UPDATE_ADDRESS. */
	private static final String CONTACT_STMT_UPDATE_ADDRESS = CONTACT_NAMESPACE + "updateAddress";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE + "deleteBusinessContact";

	/** The Constant CONTACT_STMT_DELETE_PERSON_CONTACT. */
	private static final String CONTACT_STMT_DELETE_PERSON_CONTACT = CONTACT_NAMESPACE + "deletePersonContact";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertContact";

	/** The Constant CONTACT_STMT_INSERT_PHONE. */
	private static final String CONTACT_STMT_INSERT_PHONE = CONTACT_NAMESPACE + "insertPhone";

	/** The Constant CONTACT_STMT_INSERT_EMAIL. */
	private static final String CONTACT_STMT_INSERT_EMAIL = CONTACT_NAMESPACE + "insertEmail";

	/** The Constant CONTACT_STMT_INSERT_ADDRESS. */
	private static final String CONTACT_STMT_INSERT_ADDRESS = CONTACT_NAMESPACE + "insertAddress";

	/** The Constant CONTACT_STMT_FETCH_BY_BUSINESS_ID. */
	private static final String CONTACT_STMT_FETCH_BY_BUSINESS_ID = CONTACT_NAMESPACE + "fetchContactsByBusinessId";

	/** The Constant CONTACT_STMT_FETCH_BY_PERSON_ID. */
	private static final String CONTACT_STMT_FETCH_BY_PERSON_ID = CONTACT_NAMESPACE + "fetchContactsByPersonId";

	/** The Constant CONTACT_STMT_FETCH_BY_ID. */
	private static final String CONTACT_STMT_FETCH_BY_ID = CONTACT_NAMESPACE + "fetchContactsById";

	/** The Constant CONTACT_STMT_FETCH_EMAIL_VERSION. */
	private static final String CONTACT_STMT_FETCH_EMAIL_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberEmail";

	/** The Constant CONTACT_STMT_FETCH_PHONE_VERSION. */
	private static final String CONTACT_STMT_FETCH_PHONE_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberPhone";

	/** The Constant CONTACT_STMT_FETCH_ADDRESS_VERSION. */
	private static final String CONTACT_STMT_FETCH_ADDRESS_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberAddress";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ContactDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertContact(com.prosperitasglobal.cbof.model.Contact,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertContact(Contact contact, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root contact data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, contact, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
					    .doInsert(getSqlSession(), statementName, contact, response);

		// Next insert the sub-type
		switch (contact.getContactType())
		{
			case OTHER:
			case PHONE_WORK:
			case MOBILE:
				insertCount +=
						QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT_PHONE, contact, response);
				break;
			case EMAIL_PERSONAL:
			case EMAIL_WORK:
				insertCount +=
						QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT_EMAIL, contact, response);
				break;
			case POSTAL_HOME:
			case POSTAL_WORK:
				insertCount +=
						QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT_ADDRESS, contact, response);
				break;
			default:
				break;
		}

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IContactDAC#deleteBusinessContact(com.prosperitasglobal.cbof.model.Contact,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteBusinessContact(Contact contact, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, contact, response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IContactDAC#deletePersonContact(com.prosperitasglobal.cbof.model.Contact,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deletePersonContact(Contact contact, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_PERSON_CONTACT, contact, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateContact(com.prosperitasglobal.cbof.model.Contact,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateContact(Contact contact, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(contact.getModelAction())
				&& (contact.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, contact, response);

			if (updateCount == 1)
			{
				contact.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		// Next update the sub-type
		switch (contact.getContactType())
		{
			case OTHER:
			case PHONE_WORK:
			case MOBILE:
				updateCount +=
						QATMyBatisDacHelper.doUpdateOL(getSqlSession(), CONTACT_STMT_UPDATE_PHONE, contact,
								CONTACT_STMT_FETCH_PHONE_VERSION, response);
				break;
			case EMAIL_PERSONAL:
			case EMAIL_WORK:
				updateCount +=
						QATMyBatisDacHelper.doUpdateOL(getSqlSession(), CONTACT_STMT_UPDATE_EMAIL, contact,
								CONTACT_STMT_FETCH_EMAIL_VERSION, response);
				break;
			case POSTAL_HOME:
			case POSTAL_WORK:
				updateCount +=
						QATMyBatisDacHelper.doUpdateOL(getSqlSession(), CONTACT_STMT_UPDATE_ADDRESS, contact,
								CONTACT_STMT_FETCH_ADDRESS_VERSION, response);
				break;
			default:
				break;
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchContactByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<Contact> fetchContactByParent(Integer parentId, BusinessTypeEnum parentType)
	{
		InternalResultsResponse<Contact> response = new InternalResultsResponse<Contact>();

		switch (parentType)
		{
			case ORGANIZATION:
				QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_BUSINESS_ID, parentId,
						response);
				break;

			case LOCATION:
				QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_BUSINESS_ID, parentId,
						response);
				break;

			case MEMBER:
				QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_PERSON_ID, parentId,
						response);
				break;
			case UNKNOWN:
				break;
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("parentType for Contact missing!");
				}
				break;

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchContactById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Contact> fetchContactById(Integer id)
	{
		InternalResultsResponse<Contact> response = new InternalResultsResponse<Contact>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IContactDAC#maintainContactAssociations(java.util.List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer maintainContactAssociations(List<Contact> contactList, Integer parentId, String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Contacts
		if (ValidationUtil.isNullOrEmpty(contactList))
		{
			return count;
		}
		// For Each Contact...
		for (Contact contact : contactList)
		{
			// Make sure we set the parent key
			contact.setParentKey(parentId);

			if (ValidationUtil.isNull(contact.getModelAction()))
			{
				continue;
			}
			switch (contact.getModelAction())
			{
				case INSERT:
					count += insertContact(contact, associateStatement, response);
					break;
				case UPDATE:
					count += updateContact(contact, response);
					break;
				case DELETE:
					count += deletePersonContact(contact, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Organization missing!");
					}
					break;
			}
		}
		return count;
	}
}
