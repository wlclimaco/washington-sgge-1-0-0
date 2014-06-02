package com.qat.samples.complexmo.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.complexmo.dac.IZooDAC;
import com.qat.samples.complexmo.model.Contact;
import com.qat.samples.complexmo.model.EFT;
import com.qat.samples.complexmo.model.Zoo;
import com.qat.samples.complexmo.model.request.ZooFancyRequest;
import com.qat.samples.complexmo.model.request.ZooRequest;

/**
 * The Class ZooDACImpl.
 */
public class ZooDACImpl extends SqlSessionDaoSupport implements IZooDAC
{
	private static final String PERSON_NAMESPACE = "ZooMap.";
	private static final String PERSON_STMT_FETCH_ALL = PERSON_NAMESPACE + "fetchAllZoos";
	private static final String PERSON_STMT_FETCH_REQUEST = PERSON_NAMESPACE + "fetchZooByRequest";
	private static final String PERSON_STMT_FETCH_FANCY_REQUEST = PERSON_NAMESPACE + "fetchZooByFancyRequest";
	private static final String PERSON_STMT_FETCH_ID = PERSON_NAMESPACE + "fetchZooById";
	private static final String PERSON_STMT_INSERT = PERSON_NAMESPACE + "InsertZoo";
	private static final String PERSON_STMT_UPDATE = PERSON_NAMESPACE + "UpdateZoo";
	private static final String PERSON_STMT_DELETE = PERSON_NAMESPACE + "DeleteZoo";
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
	private static final Logger LOG = LoggerFactory.getLogger(ZooDACImpl.class);

	/**
	 * Maintain zoo.
	 * 
	 * @param zoo the zoo
	 * 
	 * @return the zoo
	 * 
	 * @see com.qat.samples.IZooDAC.complexmoupdate.DAC.IZooDAC#maintainZoo(com.qat.samples.complexmoupdate.model.Zoo)
	 */
	@Override
	public InternalResultsResponse<Zoo> insertZoo(Zoo zoo)
	{
		int insertCount = 0;
		InternalResultsResponse<Zoo> response = new InternalResultsResponse<Zoo>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), PERSON_STMT_INSERT, zoo, response);

		if (!response.isInError())
		{
			// Next traverse the object graph and "maintain" the associations
			insertCount += maintainZooAssociations(zoo, response);

			// Finally, if something was inserted then add the Zoo to the result.
			if (insertCount > 0)
			{
				response.addResult(zoo);
			}
		}
		return response;
	}

	/**
	 * Update zoo.
	 * 
	 * @param zoo the zoo
	 * 
	 * @return the zoo
	 */
	@Override
	public InternalResultsResponse<Zoo> updateZoo(Zoo zoo)
	{
		int updateCount = 0;
		InternalResultsResponse<Zoo> response = new InternalResultsResponse<Zoo>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(zoo.getModelAction())
				&& (zoo.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdateOL(getSqlSession(), PERSON_STMT_UPDATE, zoo, PERSON_STMT_VERSION,
							response);

			// If the update failed because the keys have changed, which is indicated by the NoRowsUpdatedError status,
			// then we should probably provide some context. Logging of information is optional it just depends on how
			// critical it that the updated failed to actually update any rows.
			// information.
			if (response.getStatus() == InternalResponse.Status.NoRowsUpdatedError)
			{
				LOG.error("NoRowsUpdatedError has occured for Zoo model object with keys[" + zoo.getId() + ", "
						+ zoo.getVersion() + "]");
			}
		}

		if (!response.isInError())
		{
			// Next traverse the object graph and "maintain" the associations
			updateCount += maintainZooAssociations(zoo, response);

			// Finally, if something was updated then add the Zoo to the result.
			if (updateCount > 0)
			{
				response.addResult(zoo);
			}
		}
		return response;
	}

	/**
	 * Maintain zoo associations.
	 * 
	 * @param zoo the zoo
	 * @param response the response
	 * @return the int
	 */
	private int maintainZooAssociations(Zoo zoo, InternalResultsResponse<Zoo> response)
	{
		int count = 0;
		// First Maintain Contacts
		// if (!ValidationUtil.isNullOrEmpty(zoo.getContactList()))
		// {
		// // For Each Contact...
		// for (Contact contact : zoo.getContactList())
		// {
		// // Make sure we set the parent key
		// contact.setParentKey(zoo.getId());
		//
		// if (!ValidationUtil.isNull(contact.getModelAction()))
		// {
		// switch (contact.getModelAction())
		// {
		// case UPDATE:
		// count = updateContact(contact, response);
		// break;
		// case INSERT:
		// count = insertContact(contact, response);
		// break;
		// default:
		// break;
		// }
		// }
		// }
		// }

		// Next Maintain other associations

		// Make sure we set the parent key on the other associations so we are sure it will match in case there is an
		// insert.
		// if (!ValidationUtil.isNull(zoo.getEft()) && !ValidationUtil.isNull(zoo.getEft().getModelAction()))
		// {
		// // Set the parent key value
		// EFT eft = zoo.getEft();
		// eft.setParentKey(zoo.getId());
		//
		// if (!ValidationUtil.isNull(eft.getModelAction()))
		// {
		// switch (eft.getModelAction())
		// {
		// case UPDATE:
		// count = updateEft(eft, response);
		// break;
		// case INSERT:
		// count = insertEft(eft, response);
		// break;
		// default:
		// break;
		// }
		// }
		// }

		return count;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.complexmoupdate.DAC.IZooDAC#fetchZooByRequest(com.qat.samples.complexmoupdate
	 * .model.Zoo)
	 */
	@Override
	public InternalResultsResponse<Zoo> fetchZooByRequest(ZooRequest zooRequest)
	{
		InternalResultsResponse<Zoo> response = new InternalResultsResponse<Zoo>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PERSON_STMT_FETCH_REQUEST, zooRequest, response);

		return response;
	}

	@Override
	public InternalResultsResponse<Zoo> fetchZooByFancyRequest(ZooFancyRequest zooRequest)
	{
		InternalResultsResponse<Zoo> response = new InternalResultsResponse<Zoo>();

		// Need to "prepare" the dynamic search strings to make sure their properties render correctly in the SQL.
		QATMyBatisDacHelper.prepareDynamicSearchString(zooRequest.getFirstName());
		QATMyBatisDacHelper.prepareDynamicSearchString(zooRequest.getLastName());

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PERSON_STMT_FETCH_FANCY_REQUEST, zooRequest, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.complexmo.DAC.IZooDAC#fetchZooById(com.qat.samples.complexmo.model.Zoo)
	 */
	@Override
	public Zoo fetchZooById(Zoo zoo)
	{
		return (Zoo)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), PERSON_STMT_FETCH_ID, zoo);
	}

	/**
	 * Fetch all zoos.
	 * 
	 * @return the list
	 * @see com.qat.samples.IZooDAC.DAC.IZooDAC#getAllCounties()
	 */
	@Override
	public InternalResultsResponse<Zoo> fetchAllZoos()
	{
		InternalResultsResponse<Zoo> response = new InternalResultsResponse<Zoo>();

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
	private int updateContact(Contact contact, InternalResultsResponse<Zoo> response)
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
	private int insertContact(Contact contact, InternalResultsResponse<Zoo> resp)
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
	private int maintainContactAssociations(Contact contact, InternalResultsResponse<Zoo> response)
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
	private int insertEft(EFT eft, InternalResultsResponse<Zoo> response)
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
	private int updateEft(EFT eft, InternalResultsResponse<Zoo> response)
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
	 * @see com.qat.samples.complexmo.DAC.IZooDAC#deleteZoo(com.qat.samples.complexmo.model.Zoo)
	 */
	@Override
	public InternalResponse deleteZoo(Zoo zoo)
	{
		InternalResponse response = new InternalResultsResponse<Zoo>();

		QATMyBatisDacHelper.doRemoveOL(getSqlSession(), PERSON_STMT_DELETE, zoo, PERSON_STMT_VERSION, response);

		return response;
	}

}
