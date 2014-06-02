package com.qat.samples.complexmo.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
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
import com.qat.samples.complexmo.dac.IZooDAC;
import com.qat.samples.complexmo.model.Address;
import com.qat.samples.complexmo.model.Contact;
import com.qat.samples.complexmo.model.ContactTypeEnum;
import com.qat.samples.complexmo.model.Country;
import com.qat.samples.complexmo.model.County;
import com.qat.samples.complexmo.model.EFT;
import com.qat.samples.complexmo.model.Email;
import com.qat.samples.complexmo.model.Phone;
import com.qat.samples.complexmo.model.State;
import com.qat.samples.complexmo.model.Zoo;
import com.qat.samples.complexmo.model.request.ZooFancyRequest;
import com.qat.samples.complexmo.model.request.ZooRequest;

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
public class ZooTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(ZooTest.class);

	private IZooDAC zooDAC;

	public IZooDAC getZooDAC()
	{
		return zooDAC;
	}

	@Resource
	public void setZooDAC(IZooDAC zooDAC)
	{
		this.zooDAC = zooDAC;
	}

	/**
	 * This first test simple retrieve the Zoo complex model object.<br/>
	 * As you run this test log messages will be printed explaining what is happening.
	 */
	@Test
	public void testZooFetch()
	{
		LOG.info("\nStarting testZooFetch");
		InternalResultsResponse<Zoo> response = getZooDAC().fetchAllZoos();
		assertTrue("List should not be null from fetchAllZoos", response.hasResults());
		assertTrue("List should contain at least 1 Zoo", response.getResultsList().size() > 0);
		LOG.info("Zoo list contains " + response.getResultsList().size());
		LOG.info("Note you don't see any SQL for Contact !!! Just Zoo");
		LOG.info("Now we'll \"access\" the contact list which will in turn invoke SQL");

		// List<Contact> contactList = response.getFirstResult().getContactList();
		// assertNotNull("Contact list should not be null from fetchAllZoos", contactList);
		// assertTrue("Contact list should contain at least 1 ContactZoo", contactList.size() > 0);
		// LOG.info("Contact list contains " + contactList.size());
	}

	/*
	 * This next test retrieves data using a "request" object.<br/> This demonstrates the use of a Request object and
	 * how the iBatis SQL should be constructed.
	 */
	@Test
	public void testZooFetchByRequest()
	{
		LOG.info("\nStarting testZooFetchByRequest");

		ZooRequest zooRequest = new ZooRequest();
		Zoo zoo = new Zoo();
		// zoo.setId(2);
		zooRequest.setZoo(zoo);
		LOG.info("zooRequest:" + zooRequest);
		InternalResultsResponse<Zoo> response = getZooDAC().fetchZooByRequest(zooRequest);
		assertTrue("Cannot find Zoo with id=2", response.hasResults());
		LOG.info("Zoo with id=2 was retrieved. Notice the SQL where clause, it changes based on input...");
		// List<Contact> contactList = response.getFirstResult().getContactList();
		// LOG.info("Zoo with id=2 was  ContactLists." + contactList);

		zooRequest = new ZooRequest();
		zoo = new Zoo();
		// zoo.setFirstName("Richard");
		// zoo.setMiddleName("Allen");
		zooRequest.setZoo(zoo);

		response = getZooDAC().fetchZooByRequest(zooRequest);
		assertTrue("Cannot find Zoo with with name and middle name", response.hasResults());
		LOG.info("Zoo with name and middle name was retrieved. Notice the SQL where clause, it changes based on input...");

		zooRequest = new ZooRequest();
		zoo = new Zoo();
		// zoo.setLastName("Ba%");
		zooRequest.setZoo(zoo);
		// zooRequest.setLastNameSoundsLike(true);

		response = getZooDAC().fetchZooByRequest(zooRequest);
		assertTrue("Cannot find Zoo with with last name like Ba", response.hasResults());
		LOG.info("Zoos with last name Ba are retrieved. Notice the SQL where clause...");
	}

	@Test
	public void testZooFetchByFancyRequest()
	{
		LOG.info("\nStarting testZooFetchByFancyRequest");

		ZooFancyRequest zooFancyRequest = new ZooFancyRequest();

		zooFancyRequest.setLastName(new SearchString("Bar", SearchString.SearchType.STARTS_WITH, true));

		InternalResultsResponse<Zoo> response = getZooDAC().fetchZooByFancyRequest(zooFancyRequest);

		assertTrue("Should find 3 zoos with last name start with "
				+ zooFancyRequest.getLastName().getSearchValue(), response.getResultsList().size() == 3);

		LOG.info("Results size as expected=" + response.getResultsList().size());

		zooFancyRequest.setLastName(null);
		zooFancyRequest.setFirstName(new SearchString("Rich", SearchString.SearchType.STARTS_WITH, true));
		response = getZooDAC().fetchZooByFancyRequest(zooFancyRequest);
		assertTrue("Should find 2 zoos with first name start with "
				+ zooFancyRequest.getFirstName().getSearchValue(), response.getResultsList().size() == 2);
		LOG.info("Results size as expected=" + response.getResultsList().size());

		// zooFancyRequest.setFirstName(null);
		zooFancyRequest.setEmployeeNumber(new SearchNumberRange(0L, 100L,
				SearchNumberRange.SearchType.BETWEEN));

		response = getZooDAC().fetchZooByFancyRequest(zooFancyRequest);

		assertTrue("Should find 1 zoo with in the range 0 to 100, but instead we found "
				+ response.getResultsList().size() + "rows.", response.getResultsList().size() == 1);

		LOG.info("Results size as expected=" + response.getResultsList().size());
	}

	/**
	 * This test retrieves a Zoo model object and add's a new EFT association to it.
	 */
	@Test
	public void testEFTInsertUpdate()
	{
		LOG.info("\nStarting testEFTInsertUpdate");
		Zoo zoo_req = new Zoo();
		// zoo_req.setId(99);
		Zoo zoo = getZooDAC().fetchZooById(zoo_req);
		org.junit.Assert.assertEquals(zoo.getId(), zoo_req.getId());

		EFT eft = new EFT();
		eft.setAccountNumber("1234567890");
		eft.setBank("QAT");
		eft.setEffectiveStartDate(new Date());
		eft.setEffectiveEndDate(new Date());
		eft.setModelAction(QATModel.PersistanceActionEnum.INSERT);

		// zoo.setEft(eft);

		InternalResultsResponse<Zoo> response = getZooDAC().updateZoo(zoo);
		assertTrue("First Update failed", response.hasResults());
		LOG.info("We Updated the Zoo and only the EFT association was impacted as a result of the new EFT instance being added.");

		eft.setBank("Omaha");
		eft.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		response = getZooDAC().updateZoo(zoo);
		assertTrue("Second Update failed", response.hasResults());

		LOG.info("We Updated the Zoo again and the EFT association should have been the only thing updated on the Zoo.");
	}

	/**
	 * With this test we update one of the Contact entries for a Zoo.
	 */
	@Test
	public void testContactUpdate()
	{

		LOG.info("\nStarting testContactUpdate pay attention to the SQL");
		Zoo zoo_req = new Zoo();
		// zoo_req.setId(299);
		Zoo zoo = getZooDAC().fetchZooById(zoo_req);
		Assert.assertEquals(zoo.getId(), zoo_req.getId());

		// List<Contact> contactList = zoo.getContactList();
		// assertTrue("Contact list should contain at least 6 ContactZoo", contactList.size() == 6);

		// Update the contacts
		// for (Contact contact : contactList)
		// {
		// switch (contact.getContactType())
		// {
		// case PHONE_HOME:
		// Phone phoneHome = (Phone)contact;
		// phoneHome.setVerified(false);
		// phoneHome.setNumber("1234567");
		// phoneHome.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		// break;
		// case PHONE_WORK:
		// Phone phoneWork = (Phone)contact;
		// phoneWork.setVerified(false);
		// phoneWork.setNumber("7777777");
		// phoneWork.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		// break;
		// case EMAIL_HOME:
		// Email emailHome = (Email)contact;
		// emailHome.setVerified(false);
		// emailHome.setEmailAddress("home@home.com");
		// emailHome.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		// break;
		// case EMAIL_WORK:
		// Email emailWork = (Email)contact;
		// emailWork.setVerified(false);
		// emailWork.setEmailAddress("work@work.com");
		// emailWork.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		// break;
		// case POSTAL_HOME:
		// Address postalHome = (Address)contact;
		// postalHome.setVerified(false);
		// postalHome.setNote("Change Home Address");
		// postalHome.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		// break;
		// case POSTAL_WORK:
		// Address postalWork = (Address)contact;
		// postalWork.setVerified(false);
		// postalWork.setNote("Change Work Address");
		// postalWork.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		// break;
		// }
		// }

		InternalResultsResponse<Zoo> response = getZooDAC().updateZoo(zoo);
		assertTrue("Contact Update failed", response.hasResults());

		LOG.info("We Updated one of the Zoo's contact so the Zoo's contact collection is "
				+ "iterated through and only the one that was updated based on the PersistanceActionEnum will actually be update on the database");
		assertTrue("Update count should be greater than zero", response.hasResults());
	}

	/**
	 * With this test we insert Contact entries for a Zoo.
	 */
	@Test
	public void testContactInsert()
	{
		LOG.info("\nStarting testContactInsert");
		Zoo zoo_req = new Zoo();
		// zoo_req.setId(99);
		Zoo zoo = getZooDAC().fetchZooById(zoo_req);
		Assert.assertEquals(zoo.getId(), zoo_req.getId());

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
		// add all contacts to the zoo
		// zoo.setContactList(contactList);
		getZooDAC().updateZoo(zoo);
		zoo = getZooDAC().fetchZooById(zoo_req);
		// Assert.assertTrue("", zoo.getContactList().size() == 6);
		LOG.info("\nEnd testContactInsert:Review SQL carefully to follow test case");
	}

	/**
	 * With this test we take an existing Zoo and copy them, changing the name and adding a single Contact and an EFT
	 * association.
	 */
	@Test
	public void testZooInsert()
	{
		LOG.info("\nStarting testZooInsert");

		InternalResultsResponse<Zoo> response = getZooDAC().fetchAllZoos();
		assertTrue("List should contain at least 1 Zoo", response.hasResults());

		Zoo zoo = response.getFirstResult();
		// zoo.setFirstName("Fred");
		zoo.setModelAction(QATModel.PersistanceActionEnum.INSERT);

		// List<Contact> contactList = zoo.getContactList();
		// Contact contact = contactList.get(1);
		// contact.setModelAction(QATModel.PersistanceActionEnum.INSERT);

		EFT eft = new EFT();
		eft.setAccountNumber("1234567890");
		eft.setBank("QAT");
		eft.setEffectiveStartDate(new Date());
		eft.setEffectiveEndDate(new Date());
		eft.setModelAction(QATModel.PersistanceActionEnum.INSERT);
		// zoo.setEft(eft);

		LOG.info("We copied an existing Zoo, along with a single contact, marked them for Insert, create an new EFT and invoked the Zoo.Insert operation on the DAC.");
		LOG.info("You should see 4 insert statements");

		response = getZooDAC().insertZoo(zoo);
		assertTrue("The insert failed", response.hasResults());

		LOG.info("The Insert worked and a new Unique ID was assigned from the database, id="
				+ response.getFirstResult().getId());
	}

	@Test
	public void testZooUpdate()
	{
		LOG.info("\nStarting testZooUpdate");

		InternalResultsResponse<Zoo> response = getZooDAC().fetchAllZoos();
		assertTrue("List should not be null from fetchAllZoos", response.hasResults());

		Zoo zoo = response.getFirstResult();

		// zoo.setEmployeeNumber(6046000);
		zoo.setModelAction(QATModel.PersistanceActionEnum.UPDATE);

		response = getZooDAC().updateZoo(zoo);
		assertTrue("Results from Update should not be null.", response.hasResults());

	}

	@Test
	public void testZooDelete()
	{
		LOG.info("\nStarting testZooDelete");
		Zoo zoo_req = new Zoo();
		// zoo_req.setId(99);
		Zoo zoo = getZooDAC().fetchZooById(zoo_req);
		assertEquals(zoo.getId(), zoo_req.getId());
		getZooDAC().deleteZoo(zoo_req);
		zoo = getZooDAC().fetchZooById(zoo_req);
		assertNull(zoo);
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/complexmo/unittest/dac/mybatis/conf/insertComplexMO.sql", false);
	}

}
