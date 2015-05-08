package com.prosperitasglobal.cbof.unittest.dac.mybatis;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.Address;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.ContactTypeEnum;
import com.prosperitasglobal.cbof.model.Email;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.PriorityEnum;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class CommonBusinessObjectsDACTest.
 */
public class ContactDACTest extends AbstractTestBaseDAC
{

	/** The Constant ALL_CONTACTS_MUST_HAVE_VERSION_0_WHEN_CREATED. */
	private static final String ALL_CONTACTS_MUST_HAVE_VERSION_0_WHEN_CREATED =
			"All Contacts must have version = 0 when created";

	/** The Constant BAD_VERSION_NUMBER. */
	private static final Integer BAD_VERSION_NUMBER = 99;

	/** The Constant UPDATED_ADDRESS_LINE_1. */
	private static final String UPDATED_ADDRESS_LINE_1 = "New Address Line 1";

	/** The Constant UPDATED_EMAIL. */
	private static final String UPDATED_EMAIL = "updated@email.com";

	/** The Constant UPDATE_COUNT_SHOULD_BE_0. */
	private static final String UPDATE_COUNT_SHOULD_NOT_BE_0 = "update count should be > 0 ";

	/** The Constant ORGANIZATION_NAMESPACE. */
	private static final String ORGANIZATION_NAMESPACE = "OrganizationMap.";

	/** The Constant ORGANIZATION_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String ORGANIZATION_STMT_ASSOC_ORG_TO_CONTACT = ORGANIZATION_NAMESPACE
			+ "associateOrganizationWithContact";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ContactDACTest.class);

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant FIVE. */
	private static final Integer FIVE = 5;

	/**
	 * Test insert contact.
	 */
	@Test
	public void testInsertContact()
	{
		// This creates a dummy organization object with 6 contacts already (2 addresses, 2 email, 2 phone)
		Organization organization = CommonTestRoutines.createDummyOrganization();

		// Adds to DB
		InternalResultsResponse<Organization> response = getOrganizationDAC().insertOrganization(organization);

		// Create additional email
		Contact email = CommonTestRoutines.createDummyEmail(ContactTypeEnum.EMAIL_PERSONAL);
		email.setParentKey(response.getFirstResult().getId());

		InternalResultsResponse<Contact> internalResponse = new InternalResultsResponse<Contact>();

		Integer insertCount =
				getContactDAC().insertContact(email, ORGANIZATION_STMT_ASSOC_ORG_TO_CONTACT,
						internalResponse);

		Assert.assertTrue(insertCount > 0);
		Assert.assertTrue(email.getId() > 0);
	}

	/**
	 * Test delete business contact.
	 */
	@Test
	public void testDeleteBusinessContact()
	{
		// This creates a dummy organization object with 6 contacts already (2 addresses, 2 email, 2 phone)
		Organization organization = CommonTestRoutines.createDummyOrganization();

		// Adds to DB
		InternalResultsResponse<Organization> response = getOrganizationDAC().insertOrganization(organization);
		CommonTestRoutines.assertResultResponse(response);

		InternalResultsResponse<Contact> internalResponse = new InternalResultsResponse<Contact>();
		Integer deleteCount =
				getContactDAC().deleteBusinessContact(organization.getContactList().get(THREE), internalResponse);
		Assert.assertTrue(deleteCount > 0);

		InternalResultsResponse<Contact> contactResponse =
				getContactDAC().fetchContactByParent(organization.getId(), BusinessTypeEnum.ORGANIZATION);
		CommonTestRoutines.assertResultResponse(contactResponse);

		Assert.assertTrue("The contact list shoud be 5", contactResponse.getResultsList().size() == FIVE);
	}

	/**
	 * Test delete person contact.
	 */
	@Test
	public void testDeletePersonContact()
	{
		Member member = insertMember();

		InternalResultsResponse<Contact> internalResponse = new InternalResultsResponse<Contact>();
		Integer deleteCount =
				getContactDAC().deletePersonContact(member.getContactList().get(THREE), internalResponse);
		Assert.assertTrue(deleteCount > 0);

		InternalResultsResponse<Contact> contactResponse =
				getContactDAC().fetchContactByParent(member.getId(), BusinessTypeEnum.MEMBER);
		CommonTestRoutines.assertResultResponse(contactResponse);

		Assert.assertTrue("The contact list shoud be 5.", contactResponse.getResultsList().size() == FIVE);
	}

	/**
	 * Test fetch contact by parent.
	 */
	@Test
	public void testFetchContactByParent()
	{
		// This creates a dummy organization object with 6 contacts already (2 addresses, 2 email, 2 phone)
		Organization organization = CommonTestRoutines.createDummyOrganization();

		// Adds to DB
		InternalResultsResponse<Organization> response = getOrganizationDAC().insertOrganization(organization);

		// Read it back as part of the Organization
		InternalResultsResponse<Contact> internalResponse =
				getContactDAC().fetchContactByParent(response.getFirstResult().getId(),
						BusinessTypeEnum.ORGANIZATION);

		CommonTestRoutines.assertResponse(internalResponse);
	}

	/**
	 * Test update contact.
	 */
	@Test
	public void testUpdateContact()
	{
		// This creates a dummy organization object with 6 contacts already (2 addresses, 2 email, 2 phone)
		Organization organization = CommonTestRoutines.createDummyOrganization();

		// Adds to DB
		InternalResultsResponse<Organization> response = getOrganizationDAC().insertOrganization(organization);

		// Create additional email
		Contact email = CommonTestRoutines.createDummyEmail(ContactTypeEnum.EMAIL_PERSONAL);
		email.setParentKey(response.getFirstResult().getId());

		// Create additional phone
		Contact phone = CommonTestRoutines.createDummyPhone(ContactTypeEnum.OTHER, PriorityEnum.SECONDARY);
		phone.setParentKey(response.getFirstResult().getId());

		InternalResultsResponse<Contact> internalResponse = new InternalResultsResponse<Contact>();

		// Insert the new email
		Integer insertCount =
				getContactDAC().insertContact(email, ORGANIZATION_STMT_ASSOC_ORG_TO_CONTACT,
						internalResponse);

		Assert.assertTrue(insertCount > 0);
		Assert.assertTrue(email.getId() > 0);

		// Insert the new phone
		insertCount =
				getContactDAC().insertContact(phone, ORGANIZATION_STMT_ASSOC_ORG_TO_CONTACT,
						internalResponse);

		Assert.assertTrue(insertCount > 0);
		Assert.assertTrue(phone.getId() > 0);

		// Now change email fields
		((Email)email).setEmailAddress(UPDATED_EMAIL);
		email.setModelAction(PersistanceActionEnum.UPDATE);
		Integer updateCount = getContactDAC().updateContact(email, internalResponse);
		Assert.assertTrue(UPDATE_COUNT_SHOULD_NOT_BE_0, updateCount > 0);

		// Now change phone fields
		((Phone)phone).setNumber("1234567");
		((Phone)phone).setContactType(ContactTypeEnum.MOBILE);
		phone.setModelAction(PersistanceActionEnum.UPDATE);
		updateCount = getContactDAC().updateContact(phone, internalResponse);
		Assert.assertTrue(UPDATE_COUNT_SHOULD_NOT_BE_0, updateCount > 0);

	}

	/**
	 * Test update main contact.
	 */
	@Test
	public void testUpdateMainContact()
	{
		// This creates a dummy organization object with 6 contacts already (2 addresses, 2 email, 2 phone)
		Organization organization = CommonTestRoutines.createDummyOrganization();

		// Adds to DB
		InternalResultsResponse<Organization> response = getOrganizationDAC().insertOrganization(organization);

		Contact contact = response.getFirstResult().getContactList().get(0);

		contact.setPriority(PriorityEnum.PRIMARY);
		contact.setModelAction(PersistanceActionEnum.UPDATE);

		Integer updateCount = getContactDAC().updateContact(contact, response);
		Assert.assertTrue(updateCount > 0);
	}

	/**
	 * Test update contact ol.
	 */
	@Test
	public void testUpdateContactOL()
	{
		// This creates a dummy organization object with 6 contacts already (2 addresses, 2 email, 2 phone)
		Organization organization = CommonTestRoutines.createDummyOrganization();

		// Adds to DB
		InternalResultsResponse<Organization> response = getOrganizationDAC().insertOrganization(organization);

		for (Contact contact : organization.getContactList())
		{
			// Make sure everybody has version numbers
			Assert.assertTrue(ALL_CONTACTS_MUST_HAVE_VERSION_0_WHEN_CREATED, contact.getVersion() == 0);

			InternalResultsResponse<Contact> internalResponse = new InternalResultsResponse<Contact>();

			Integer updateCount = 0;
			// Now make changes to all Contacts
			switch (contact.getContactType())
			{
				case EMAIL_WORK:
				case EMAIL_PERSONAL:
					Email email = (Email)contact;
					email.setEmailAddress(UPDATED_EMAIL);
					email.setModelAction(PersistanceActionEnum.UPDATE);
					updateCount = getContactDAC().updateContact(email, internalResponse);
					Assert.assertTrue(UPDATE_COUNT_SHOULD_NOT_BE_0, updateCount > 0);
					break;

				case PHONE_WORK:
				case MOBILE:
				case OTHER:
					Phone phone = (Phone)contact;
					updateCount = getContactDAC().updateContact(phone, internalResponse);
					Assert.assertTrue(UPDATE_COUNT_SHOULD_NOT_BE_0, updateCount > 0);
					break;

				case POSTAL_WORK:
				case POSTAL_HOME:
					Address address = (Address)contact;
					address.setAddressLine1(UPDATED_ADDRESS_LINE_1);
					updateCount = getContactDAC().updateContact(address, internalResponse);
					Assert.assertTrue(UPDATE_COUNT_SHOULD_NOT_BE_0, updateCount > 0);
					break;
				default:
					break;
			}
		}

		response = getOrganizationDAC().fetchOrganizationById(new FetchByIdRequest(organization.getId()));

		for (Contact contact : response.getFirstResult().getContactList())
		{
			// Make sure everybody has version numbers > 0
			Assert.assertTrue("All Contacts must have version > when updated", contact.getVersion() > 0);
			LOG.debug("Contact version is: " + contact.getVersion());
		}
	}

	/**
	 * Test update contact ol error.
	 */
	@Test
	public void testUpdateContactOLError()
	{
		// This creates a dummy organization object with 6 contacts already (2 addresses, 2 email, 2 phone)
		Organization organization = CommonTestRoutines.createDummyOrganization();

		// Adds to DB
		InternalResultsResponse<Organization> response = getOrganizationDAC().insertOrganization(organization);

		response = getOrganizationDAC().fetchOrganizationById(new FetchByIdRequest(organization.getId()));

		for (Contact contact : response.getFirstResult().getContactList())
		{
			// Make sure everybody has version numbers
			Assert.assertTrue(ALL_CONTACTS_MUST_HAVE_VERSION_0_WHEN_CREATED, contact.getVersion() == 0);

			InternalResultsResponse<Contact> internalResponse = new InternalResultsResponse<Contact>();

			Integer updateCount = 0;
			// Now make changes to all Contacts
			switch (contact.getContactType())
			{
				case EMAIL_WORK:
				case EMAIL_PERSONAL:

					Email email = (Email)contact;
					email.setEmailAddress(UPDATED_EMAIL);

					// Set a different version number on purpose to trigger the error
					email.setVersion(BAD_VERSION_NUMBER);
					email.setModelAction(PersistanceActionEnum.UPDATE);
					updateCount = getContactDAC().updateContact(email, internalResponse);
					Assert.assertTrue(UPDATE_COUNT_SHOULD_NOT_BE_0, updateCount == 0);
					Assert.assertEquals("Need to receive OL error", Status.OptimisticLockingError,
							internalResponse.getStatus());
					break;
				default:
					break;

			}
		}
	}
}
