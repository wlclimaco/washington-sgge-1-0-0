package com.qat.samples.complexmo.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.complexmo.dac.IPersonDAC;
import com.qat.samples.complexmo.model.Contact;
import com.qat.samples.complexmo.model.EFT;
import com.qat.samples.complexmo.model.Person;
import com.qat.samples.complexmo.model.request.PersonFancyRequest;
import com.qat.samples.complexmo.model.request.PersonRequest;

/**
 * The Class PersonDACImpl.
 */
public class PersonDACImpl extends SqlSessionDaoSupport implements IPersonDAC
{
	private static final String PERSON_NAMESPACE = "PersonMap.";
	private static final String PERSON_STMT_FETCH_ALL = PERSON_NAMESPACE + "fetchAllPersons";
	private static final String PERSON_STMT_FETCH_REQUEST = PERSON_NAMESPACE + "fetchPersonByRequest";
	private static final String PERSON_STMT_FETCH_FANCY_REQUEST = PERSON_NAMESPACE + "fetchPersonByFancyRequest";
	private static final String PERSON_STMT_FETCH_ID = PERSON_NAMESPACE + "fetchPersonById";
	private static final String PERSON_STMT_INSERT = PERSON_NAMESPACE + "InsertPerson";
	private static final String PERSON_STMT_UPDATE = PERSON_NAMESPACE + "UpdatePerson";
	private static final String PERSON_STMT_DELETE = PERSON_NAMESPACE + "DeletePerson";
	private static final String PERSON_STMT_VERSION = PERSON_NAMESPACE + "fetchVersionNumber";
	private static final String CONTACT_NAMESPACE = "ContactMap.";
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "UpdateContact";
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "UpdatePhone";
	private static final String CONTACT_STMT_UPDATE_EMAIL = CONTACT_NAMESPACE + "UpdateEmail";
	private static final String CONTACT_STMT_UPDATE_ADDRESS = CONTACT_NAMESPACE + "UpdateAddress";
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "InsertContact";
	private static final String CONTACT_STMT_INSERT_PHONE = CONTACT_NAMESPACE + "InsertPhone";
	private static final String CONTACT_STMT_INSERT_EMAIL = CONTACT_NAMESPACE + "InsertEmail";
	private static final String CONTACT_STMT_INSERT_ADDRESS = CONTACT_NAMESPACE + "InsertAddress";
	private static final String EFT_NAMESPACE = "EFTMap.";
	private static final String EFT_STMT_INSERT = EFT_NAMESPACE + "InsertEft";
	private static final String EFT_STMT_UPDATE = EFT_NAMESPACE + "UpdateEft";

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(PersonDACImpl.class);

	/**
	 * Maintain person.
	 * 
	 * @param person the person
	 * 
	 * @return the person
	 * 
	 * @see com.qat.samples.IPersonDAC.complexmoupdate.DAC.IPersonDAC#maintainPerson(com.qat.samples.complexmoupdate.model.Person)
	 */
	@Override
	public InternalResultsResponse<Person> insertPerson(Person person)
	{
		int insertCount = 0;
		InternalResultsResponse<Person> response = new InternalResultsResponse<Person>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), PERSON_STMT_INSERT, person, response);

		if (!response.isInError())
		{
			// Next traverse the object graph and "maintain" the associations
			insertCount += maintainPersonAssociations(person, response);

			// Finally, if something was inserted then add the Person to the result.
			if (insertCount > 0)
			{
				response.addResult(person);
			}
		}
		return response;
	}

	/**
	 * Update person.
	 * 
	 * @param person the person
	 * 
	 * @return the person
	 */
	@Override
	public InternalResultsResponse<Person> updatePerson(Person person)
	{
		int updateCount = 0;
		InternalResultsResponse<Person> response = new InternalResultsResponse<Person>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(person.getModelAction())
				&& (person.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdateOL(getSqlSession(), PERSON_STMT_UPDATE, person, PERSON_STMT_VERSION,
							response);

			// If the update failed because the keys have changed, which is indicated by the NoRowsUpdatedError status,
			// then we should probably provide some context. Logging of information is optional it just depends on how
			// critical it that the updated failed to actually update any rows.
			// information.
			if (response.getStatus() == InternalResponse.Status.NoRowsUpdatedError)
			{
				LOG.error("NoRowsUpdatedError has occured for Person model object with keys[" + person.getId() + ", "
						+ person.getVersion() + "]");
			}
		}

		if (!response.isInError())
		{
			// Next traverse the object graph and "maintain" the associations
			updateCount += maintainPersonAssociations(person, response);

			// Finally, if something was updated then add the Person to the result.
			if (updateCount > 0)
			{
				response.addResult(person);
			}
		}
		return response;
	}

	/**
	 * Maintain person associations.
	 * 
	 * @param person the person
	 * @param response the response
	 * @return the int
	 */
	private int maintainPersonAssociations(Person person, InternalResultsResponse<Person> response)
	{
		int count = 0;
		// First Maintain Contacts
		if (!ValidationUtil.isNullOrEmpty(person.getContactList()))
		{
			// For Each Contact...
			for (Contact contact : person.getContactList())
			{
				// Make sure we set the parent key
				contact.setParentKey(person.getId());

				if (!ValidationUtil.isNull(contact.getModelAction()))
				{
					switch (contact.getModelAction())
					{
						case UPDATE:
							count = updateContact(contact, response);
							break;
						case INSERT:
							count = insertContact(contact, response);
							break;
						default:
							break;
					}
				}
			}
		}

		// Next Maintain other associations

		// Make sure we set the parent key on the other associations so we are sure it will match in case there is an
		// insert.
		if (!ValidationUtil.isNull(person.getEft()) && !ValidationUtil.isNull(person.getEft().getModelAction()))
		{
			// Set the parent key value
			EFT eft = person.getEft();
			eft.setParentKey(person.getId());

			if (!ValidationUtil.isNull(eft.getModelAction()))
			{
				switch (eft.getModelAction())
				{
					case UPDATE:
						count = updateEft(eft, response);
						break;
					case INSERT:
						count = insertEft(eft, response);
						break;
					default:
						break;
				}
			}
		}

		return count;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.complexmoupdate.DAC.IPersonDAC#fetchPersonByRequest(com.qat.samples.complexmoupdate
	 * .model.Person)
	 */
	@Override
	public InternalResultsResponse<Person> fetchPersonByRequest(PersonRequest personRequest)
	{
		InternalResultsResponse<Person> response = new InternalResultsResponse<Person>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PERSON_STMT_FETCH_REQUEST, personRequest, response);

		return response;
	}

	@Override
	public InternalResultsResponse<Person> fetchPersonByFancyRequest(PersonFancyRequest personRequest)
	{
		InternalResultsResponse<Person> response = new InternalResultsResponse<Person>();

		// Need to "prepare" the dynamic search strings to make sure their properties render correctly in the SQL.
		QATMyBatisDacHelper.prepareDynamicSearchString(personRequest.getFirstName());
		QATMyBatisDacHelper.prepareDynamicSearchString(personRequest.getLastName());

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PERSON_STMT_FETCH_FANCY_REQUEST, personRequest, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.complexmo.DAC.IPersonDAC#fetchPersonById(com.qat.samples.complexmo.model.Person)
	 */
	@Override
	public Person fetchPersonById(Person person)
	{
		return (Person)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), PERSON_STMT_FETCH_ID, person);
	}

	/**
	 * Fetch all persons.
	 * 
	 * @return the list
	 * @see com.qat.samples.IPersonDAC.DAC.IPersonDAC#getAllCounties()
	 */
	@Override
	public InternalResultsResponse<Person> fetchAllPersons()
	{
		InternalResultsResponse<Person> response = new InternalResultsResponse<Person>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PERSON_STMT_FETCH_ALL, response);

		return response;
	}

	/**
	 * Update contact.
	 * 
	 * @param contact the contact
	 * @param response the response
	 * @return the int
	 */
	private int updateContact(Contact contact, InternalResultsResponse<Person> response)
	{
		int updateCount = 0;

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
			case PHONE_HOME:
				updateCount =
						QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_PHONE, contact, response);
				break;
			case EMAIL_HOME:
				updateCount =
						QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_EMAIL, contact, response);
				break;
			case POSTAL_HOME:
				updateCount =
						QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_ADDRESS, contact, response);
				break;
			case PHONE_WORK:
				updateCount =
						QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_PHONE, contact, response);
				break;
			case EMAIL_WORK:
				updateCount =
						QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_EMAIL, contact, response);
				break;
			case POSTAL_WORK:
				updateCount =
						QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_ADDRESS, contact, response);
				break;
			default:
				break;
		}

		// Next traverse the Contact object graph and
		maintainContactAssociations(contact, response);

		return updateCount;
	}

	/**
	 * Insert contact.
	 * 
	 * @param contact the contact
	 * @param resp the resp
	 * @return the counter
	 */
	private int insertContact(Contact contact, InternalResultsResponse<Person> resp)
	{
		int insertCount = 0;
		// First insert the root contact data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, contact, resp);

		// Next insert the sub-type
		switch (contact.getContactType())
		{
			case PHONE_HOME:
				insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT_PHONE, contact, resp);
				break;
			case EMAIL_HOME:
				insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT_EMAIL, contact, resp);
				break;
			case POSTAL_HOME:
				insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT_ADDRESS, contact, resp);
				break;
			case PHONE_WORK:
				insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT_PHONE, contact, resp);
				break;
			case EMAIL_WORK:
				insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT_EMAIL, contact, resp);
				break;
			case POSTAL_WORK:
				insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT_ADDRESS, contact, resp);
				break;
			default:
				break;
		}

		// Then the associations.
		// We can use the "maintain" method for this.(empty right now)
		maintainContactAssociations(contact, resp);

		return insertCount;
	}

	/**
	 * Maintain contact associations.
	 * 
	 * @param contact the contact
	 * @param response the response
	 * @return the counter
	 */
	private int maintainContactAssociations(Contact contact, InternalResultsResponse<Person> response)
	{
		int count = 0;

		// Make sure we set the parent key on the other associations so we are sure it will match in case there is an
		// insert.
		// Never finished the code for State, County and Country on purpose...this is a sample but it would look like
		// eft code

		return count;
	}

	/**
	 * Insert eft.
	 * 
	 * @param eft the eft
	 * @param response the response
	 */
	private int insertEft(EFT eft, InternalResultsResponse<Person> response)
	{
		return QATMyBatisDacHelper.doInsert(getSqlSession(), EFT_STMT_INSERT, eft, response);
	}

	/**
	 * Update eft.
	 * 
	 * @param eft the eft
	 * @param response the response
	 * @return the int
	 */
	private int updateEft(EFT eft, InternalResultsResponse<Person> response)
	{
		int updateCount = 0;

		if ((eft.getModelAction() != null) && (eft.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), EFT_STMT_UPDATE, eft, response);
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.complexmo.DAC.IPersonDAC#deletePerson(com.qat.samples.complexmo.model.Person)
	 */
	@Override
	public InternalResponse deletePerson(Person person)
	{
		InternalResponse response = new InternalResultsResponse<Person>();

		QATMyBatisDacHelper.doRemoveOL(getSqlSession(), PERSON_STMT_DELETE, person, PERSON_STMT_VERSION, response);

		return response;
	}

}
