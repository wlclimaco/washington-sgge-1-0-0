package com.qat.samples.complexmo.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.query.SearchNumberRange;
import com.qat.framework.query.SearchString;
import com.qat.samples.complexmo.dac.IPersonDAC;
import com.qat.samples.complexmo.model.Address;
import com.qat.samples.complexmo.model.Contact;
import com.qat.samples.complexmo.model.ContactTypeEnum;
import com.qat.samples.complexmo.model.Country;
import com.qat.samples.complexmo.model.County;
import com.qat.samples.complexmo.model.EFT;
import com.qat.samples.complexmo.model.Email;
import com.qat.samples.complexmo.model.Person;
import com.qat.samples.complexmo.model.Phone;
import com.qat.samples.complexmo.model.State;
import com.qat.samples.complexmo.model.request.PersonFancyRequest;
import com.qat.samples.complexmo.model.request.PersonRequest;

/**
 * JUnit class used to demonstrate complex model objects.<br/>
 * Tests include retrieving, updating and inserting complex model objects.
 * 
 */
@ContextConfiguration(locations = {
		"classpath:com/qat/samples/complexmo/unittest/dac/mybatis/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/complexmo/unittest/dac/mybatis/conf/complexmo-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ComplexMOTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(ComplexMOTest.class);

	private IPersonDAC personDAC;

	public IPersonDAC getPersonDAC()
	{
		return personDAC;
	}

	@Resource
	public void setPersonDAC(IPersonDAC personDAC)
	{
		this.personDAC = personDAC;
	}

	/**
	 * This first test simple retrieve the Person complex model object.<br/>
	 * As you run this test log messages will be printed explaining what is happening.
	 */
	@Test
	public void testPersonFetch()
	{
		LOG.info("\nStarting testPersonFetch");
		InternalResultsResponse<Person> response = getPersonDAC().fetchAllPersons();
		assertTrue("List should not be null from fetchAllPersons", response.hasResults());
		assertTrue("List should contain at least 1 Person", response.getResultsList().size() > 0);
		LOG.info("Person list contains " + response.getResultsList().size());
		LOG.info("Note you don't see any SQL for Contact !!! Just Person");
		LOG.info("Now we'll \"access\" the contact list which will in turn invoke SQL");

		List<Contact> contactList = response.getFirstResult().getContactList();
		assertNotNull("Contact list should not be null from fetchAllPersons", contactList);
		assertTrue("Contact list should contain at least 1 ContactPerson", contactList.size() > 0);
		LOG.info("Contact list contains " + contactList.size());
	}

	/*
	 * This next test retrieves data using a "request" object.<br/> This demonstrates the use of a Request object and
	 * how the iBatis SQL should be constructed.
	 */
	@Test
	public void testPersonFetchByRequest()
	{
		LOG.info("\nStarting testPersonFetchByRequest");

		PersonRequest personRequest = new PersonRequest();
		Person person = new Person();
		person.setId(2);
		personRequest.setPerson(person);
		LOG.info("personRequest:" + personRequest);
		InternalResultsResponse<Person> response = getPersonDAC().fetchPersonByRequest(personRequest);
		assertTrue("Cannot find Person with id=2", response.hasResults());
		LOG.info("Person with id=2 was retrieved. Notice the SQL where clause, it changes based on input...");
		List<Contact> contactList = response.getFirstResult().getContactList();
		LOG.info("Person with id=2 was  ContactLists." + contactList);

		personRequest = new PersonRequest();
		person = new Person();
		person.setFirstName("Richard");
		person.setMiddleName("Allen");
		personRequest.setPerson(person);

		response = getPersonDAC().fetchPersonByRequest(personRequest);
		assertTrue("Cannot find Person with with name and middle name", response.hasResults());
		LOG.info("Person with name and middle name was retrieved. Notice the SQL where clause, it changes based on input...");

		personRequest = new PersonRequest();
		person = new Person();
		person.setLastName("Ba%");
		personRequest.setPerson(person);
		personRequest.setLastNameSoundsLike(true);

		response = getPersonDAC().fetchPersonByRequest(personRequest);
		assertTrue("Cannot find Person with with last name like Ba", response.hasResults());
		LOG.info("Persons with last name Ba are retrieved. Notice the SQL where clause...");
	}

	@Test
	public void testPersonFetchByFancyRequest()
	{
		LOG.info("\nStarting testPersonFetchByFancyRequest");

		PersonFancyRequest personFancyRequest = new PersonFancyRequest();

		personFancyRequest.setLastName(new SearchString("Bar", SearchString.SearchType.STARTS_WITH, true));

		InternalResultsResponse<Person> response = getPersonDAC().fetchPersonByFancyRequest(personFancyRequest);

		assertTrue("Should find 3 persons with last name start with "
				+ personFancyRequest.getLastName().getSearchValue(), response.getResultsList().size() == 3);

		LOG.info("Results size as expected=" + response.getResultsList().size());

		personFancyRequest.setLastName(null);
		personFancyRequest.setFirstName(new SearchString("Rich", SearchString.SearchType.STARTS_WITH, true));
		response = getPersonDAC().fetchPersonByFancyRequest(personFancyRequest);
		assertTrue("Should find 2 persons with first name start with "
				+ personFancyRequest.getFirstName().getSearchValue(), response.getResultsList().size() == 2);
		LOG.info("Results size as expected=" + response.getResultsList().size());

		// personFancyRequest.setFirstName(null);
		personFancyRequest.setEmployeeNumber(new SearchNumberRange(0L, 100L,
				SearchNumberRange.SearchType.BETWEEN));

		response = getPersonDAC().fetchPersonByFancyRequest(personFancyRequest);

		assertTrue("Should find 1 person with in the range 0 to 100, but instead we found "
				+ response.getResultsList().size() + "rows.", response.getResultsList().size() == 1);

		LOG.info("Results size as expected=" + response.getResultsList().size());
	}

	/**
	 * This test retrieves a Person model object and add's a new EFT association to it.
	 */
	@Test
	public void testEFTInsertUpdate()
	{
		LOG.info("\nStarting testEFTInsertUpdate");
		Person person_req = new Person();
		person_req.setId(99);
		Person person = getPersonDAC().fetchPersonById(person_req);
		org.junit.Assert.assertEquals(person.getId(), person_req.getId());

		EFT eft = new EFT();
		eft.setAccountNumber("1234567890");
		eft.setBank("QAT");
		eft.setEffectiveStartDate(new Date());
		eft.setEffectiveEndDate(new Date());
		eft.setModelAction(QATModel.PersistanceActionEnum.INSERT);

		person.setEft(eft);

		InternalResultsResponse<Person> response = getPersonDAC().updatePerson(person);
		assertTrue("First Update failed", response.hasResults());
		LOG.info("We Updated the Person and only the EFT association was impacted as a result of the new EFT instance being added.");

		eft.setBank("Omaha");
		eft.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		response = getPersonDAC().updatePerson(person);
		assertTrue("Second Update failed", response.hasResults());

		LOG.info("We Updated the Person again and the EFT association should have been the only thing updated on the Person.");
	}

	/**
	 * With this test we update one of the Contact entries for a Person.
	 */
	@Test
	public void testContactUpdate()
	{

		LOG.info("\nStarting testContactUpdate pay attention to the SQL");
		Person person_req = new Person();
		person_req.setId(299);
		Person person = getPersonDAC().fetchPersonById(person_req);
		Assert.assertEquals(person.getId(), person_req.getId());

		List<Contact> contactList = person.getContactList();
		assertTrue("Contact list should contain at least 6 ContactPerson", contactList.size() == 6);

		// Update the contacts
		for (Contact contact : contactList)
		{
			switch (contact.getContactType())
			{
				case PHONE_HOME:
					Phone phoneHome = (Phone)contact;
					phoneHome.setVerified(false);
					phoneHome.setNumber("1234567");
					phoneHome.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
					break;
				case PHONE_WORK:
					Phone phoneWork = (Phone)contact;
					phoneWork.setVerified(false);
					phoneWork.setNumber("7777777");
					phoneWork.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
					break;
				case EMAIL_HOME:
					Email emailHome = (Email)contact;
					emailHome.setVerified(false);
					emailHome.setEmailAddress("home@home.com");
					emailHome.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
					break;
				case EMAIL_WORK:
					Email emailWork = (Email)contact;
					emailWork.setVerified(false);
					emailWork.setEmailAddress("work@work.com");
					emailWork.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
					break;
				case POSTAL_HOME:
					Address postalHome = (Address)contact;
					postalHome.setVerified(false);
					postalHome.setNote("Change Home Address");
					postalHome.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
					break;
				case POSTAL_WORK:
					Address postalWork = (Address)contact;
					postalWork.setVerified(false);
					postalWork.setNote("Change Work Address");
					postalWork.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
					break;
			}
		}

		InternalResultsResponse<Person> response = getPersonDAC().updatePerson(person);
		assertTrue("Contact Update failed", response.hasResults());

		LOG.info("We Updated one of the Person's contact so the Person's contact collection is "
				+ "iterated through and only the one that was updated based on the PersistanceActionEnum will actually be update on the database");
		assertTrue("Update count should be greater than zero", response.hasResults());
	}

	/**
	 * With this test we insert Contact entries for a Person.
	 */
	@Test
	public void testContactInsert()
	{
		LOG.info("\nStarting testContactInsert");
		Person person_req = new Person();
		person_req.setId(99);
		Person person = getPersonDAC().fetchPersonById(person_req);
		Assert.assertEquals(person.getId(), person_req.getId());

		List<Contact> contactList = new ArrayList<Contact>();
		// add home email
		Email contactEmail = new Email();
		contactEmail.setParentKey(99);
		contactEmail.setContactType(ContactTypeEnum.EMAIL_HOME);
		contactEmail.setPriority(9);
		contactEmail.setEmailAddress("testhome@gmail.com");
		contactEmail.setEffectiveStartDate(new Date());
		contactEmail.setEffectiveEndDate(new Date());
		contactEmail.setModelAction(QATModel.PersistanceActionEnum.INSERT);
		contactList.add(contactEmail);
		// add work email
		contactEmail = new Email();
		contactEmail.setParentKey(99);
		contactEmail.setContactType(ContactTypeEnum.EMAIL_WORK);
		contactEmail.setPriority(9);
		contactEmail.setEmailAddress("testwork@gmail.com");
		contactEmail.setEffectiveStartDate(new Date());
		contactEmail.setEffectiveEndDate(new Date());
		contactEmail.setModelAction(QATModel.PersistanceActionEnum.INSERT);
		contactList.add(contactEmail);
		// add home phone
		Phone contactPhone = new Phone();
		contactPhone.setParentKey(99);
		contactPhone.setContactType(ContactTypeEnum.PHONE_HOME);
		contactPhone.setPriority(9);
		contactPhone.setCountryCode("1");
		contactPhone.setAreaCode("305");
		contactPhone.setNumber("1234567");
		contactPhone.setExtension("");
		contactPhone.setEffectiveStartDate(new Date());
		contactPhone.setEffectiveEndDate(new Date());
		contactPhone.setModelAction(QATModel.PersistanceActionEnum.INSERT);
		contactList.add(contactPhone);
		// add work phone
		contactPhone = new Phone();
		contactPhone.setParentKey(99);
		contactPhone.setContactType(ContactTypeEnum.PHONE_WORK);
		contactPhone.setPriority(9);
		contactPhone.setCountryCode("1");
		contactPhone.setAreaCode("305");
		contactPhone.setNumber("7654321");
		contactPhone.setExtension("");
		contactPhone.setEffectiveStartDate(new Date());
		contactPhone.setEffectiveEndDate(new Date());
		contactPhone.setModelAction(QATModel.PersistanceActionEnum.INSERT);
		contactList.add(contactPhone);
		// add Home address
		Address contactAddress = new Address();
		contactAddress.setParentKey(99);
		contactAddress.setContactType(ContactTypeEnum.POSTAL_HOME);
		contactAddress.setPriority(9);
		contactAddress.setAddressline1("Home Street 1");
		contactAddress.setAddressline2("");
		contactAddress.setCity("Omaha");
		contactAddress.setState(new State("NE"));
		contactAddress.setCounty(new County("DO"));
		contactAddress.setCountry(new Country("US"));
		contactAddress.setPostalCode("68144");
		contactAddress.setNote("Home Address Note");
		contactAddress.setEffectiveStartDate(new Date());
		contactAddress.setEffectiveEndDate(new Date());
		contactAddress.setModelAction(QATModel.PersistanceActionEnum.INSERT);
		contactList.add(contactAddress);
		// add Work address
		contactAddress = new Address();
		contactAddress.setParentKey(99);
		contactAddress.setContactType(ContactTypeEnum.POSTAL_WORK);
		contactAddress.setPriority(9);
		contactAddress.setAddressline1("Work Street 1");
		contactAddress.setAddressline2("");
		contactAddress.setCity("Omaha");
		contactAddress.setState(new State("NE"));
		contactAddress.setCounty(new County("SP"));
		contactAddress.setCountry(new Country("US"));
		contactAddress.setPostalCode("68104");
		contactAddress.setNote("Work Address Note");
		contactAddress.setEffectiveStartDate(new Date());
		contactAddress.setEffectiveEndDate(new Date());
		contactAddress.setModelAction(QATModel.PersistanceActionEnum.INSERT);
		contactList.add(contactAddress);
		// add all contacts to the person
		person.setContactList(contactList);
		getPersonDAC().updatePerson(person);
		person = getPersonDAC().fetchPersonById(person_req);
		Assert.assertTrue("", person.getContactList().size() == 6);
		LOG.info("\nEnd testContactInsert:Review SQL carefully to follow test case");
	}

	/**
	 * With this test we take an existing Person and copy them, changing the name and adding a single Contact and an EFT
	 * association.
	 */
	@Test
	public void testPersonInsert()
	{
		LOG.info("\nStarting testPersonInsert");

		InternalResultsResponse<Person> response = getPersonDAC().fetchAllPersons();
		assertTrue("List should contain at least 1 Person", response.hasResults());

		Person person = response.getFirstResult();
		person.setFirstName("Fred");
		person.setModelAction(QATModel.PersistanceActionEnum.INSERT);

		List<Contact> contactList = person.getContactList();
		Contact contact = contactList.get(1);
		contact.setModelAction(QATModel.PersistanceActionEnum.INSERT);

		EFT eft = new EFT();
		eft.setAccountNumber("1234567890");
		eft.setBank("QAT");
		eft.setEffectiveStartDate(new Date());
		eft.setEffectiveEndDate(new Date());
		eft.setModelAction(QATModel.PersistanceActionEnum.INSERT);
		person.setEft(eft);

		LOG.info("We copied an existing Person, along with a single contact, marked them for Insert, create an new EFT and invoked the Person.Insert operation on the DAC.");
		LOG.info("You should see 4 insert statements");

		response = getPersonDAC().insertPerson(person);
		assertTrue("The insert failed", response.hasResults());

		LOG.info("The Insert worked and a new Unique ID was assigned from the database, id="
				+ response.getFirstResult().getId());
	}

	@Test
	public void testPersonUpdate()
	{
		LOG.info("\nStarting testPersonUpdate");

		InternalResultsResponse<Person> response = getPersonDAC().fetchAllPersons();
		assertTrue("List should not be null from fetchAllPersons", response.hasResults());

		Person person = response.getFirstResult();

		person.setEmployeeNumber(6046000);
		person.setModelAction(QATModel.PersistanceActionEnum.UPDATE);

		response = getPersonDAC().updatePerson(person);
		assertTrue("Results from Update should not be null.", response.hasResults());

	}

	@Test
	public void testPersonDelete()
	{
		LOG.info("\nStarting testPersonDelete");
		Person person_req = new Person();
		person_req.setId(99);
		Person person = getPersonDAC().fetchPersonById(person_req);
		assertEquals(person.getId(), person_req.getId());
		getPersonDAC().deletePerson(person_req);
		person = getPersonDAC().fetchPersonById(person_req);
		assertNull(person);
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/complexmo/unittest/dac/mybatis/conf/insertComplexMO.sql", false);
	}

}
