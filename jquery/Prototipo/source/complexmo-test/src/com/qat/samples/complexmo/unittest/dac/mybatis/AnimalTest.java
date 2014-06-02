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
import com.qat.framework.query.SearchString;
import com.qat.samples.complexmo.dac.IAnimalDAC;
import com.qat.samples.complexmo.model.Address;
import com.qat.samples.complexmo.model.Animal;
import com.qat.samples.complexmo.model.Contact;
import com.qat.samples.complexmo.model.ContactTypeEnum;
import com.qat.samples.complexmo.model.Country;
import com.qat.samples.complexmo.model.County;
import com.qat.samples.complexmo.model.EFT;
import com.qat.samples.complexmo.model.Email;
import com.qat.samples.complexmo.model.Phone;
import com.qat.samples.complexmo.model.State;
import com.qat.samples.complexmo.model.request.AnimalFancyRequest;
import com.qat.samples.complexmo.model.request.AnimalRequest;

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
public class AnimalTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(AnimalTest.class);

	private IAnimalDAC animalDAC;

	public IAnimalDAC getAnimalDAC()
	{
		return animalDAC;
	}

	@Resource
	public void setAnimalDAC(IAnimalDAC animalDAC)
	{
		this.animalDAC = animalDAC;
	}

	/**
	 * This first test simple retrieve the Animal complex model object.<br/>
	 * As you run this test log messages will be printed explaining what is happening.
	 */
	@Test
	public void testAnimalFetch()
	{
		LOG.info("\nStarting testAnimalFetch");
		InternalResultsResponse<Animal> response = getAnimalDAC().fetchAllAnimals();
		assertTrue("List should not be null from fetchAllAnimals", response.hasResults());
		assertTrue("List should contain at least 1 Animal", response.getResultsList().size() > 0);
		LOG.info("Animal list contains " + response.getResultsList().size());
		LOG.info("Note you don't see any SQL for Contact !!! Just Animal");
		LOG.info("Now we'll \"access\" the contact list which will in turn invoke SQL");

		Country country = response.getFirstResult().getCountryOfOrigin();
		assertNotNull("Contact list should not be null from fetchAllAnimals", country);
		LOG.info("Contact list contains " + country);
	}

	/**
	 * This first test simple retrieve the Animal complex model object.<br/>
	 * As you run this test log messages will be printed explaining what is happening.
	 */
	@Test
	public void testAnimalById()
	{
		LOG.info("\nStarting testAnimalFetch");
		Animal animal = new Animal();
		animal.setId(2);
		Animal response = getAnimalDAC().fetchAnimalById(animal);
		assertNotNull("List should not be null from fetchAllAnimals", response);

		LOG.info("Note you don't see any SQL for Contact !!! Just Animal");
		LOG.info("Now we'll \"access\" the contact list which will in turn invoke SQL");
	}

	/*
	 * This next test retrieves data using a "request" object.<br/> This demonstrates the use of a Request object and
	 * how the iBatis SQL should be constructed.
	 */
	@Test
	public void testAnimalFetchByRequest()
	{
		LOG.info("\nStarting testAnimalFetchByRequest");

		AnimalRequest animalRequest = new AnimalRequest();
		Animal animal = new Animal();
		animal.setId(2);
		animalRequest.setAnimal(animal);
		LOG.info("animalRequest:" + animalRequest);
		InternalResultsResponse<Animal> response = getAnimalDAC().fetchAnimalByRequest(animalRequest);
		assertTrue("Cannot find Animal with id=2", response.hasResults());
		LOG.info("Animal with id=2 was retrieved. Notice the SQL where clause, it changes based on input...");
		// List<Contact> contactList = response.getFirstResult().getContactList();
		// LOG.info("Animal with id=2 was  ContactLists." + contactList);

		animalRequest = new AnimalRequest();
		animal = new Animal();
		// animal.setFirstName("Richard");
		// animal.setMiddleName("Allen");
		animalRequest.setAnimal(animal);

		response = getAnimalDAC().fetchAnimalByRequest(animalRequest);
		assertTrue("Cannot find Animal with with name and middle name", response.hasResults());
		LOG.info("Animal with name and middle name was retrieved. Notice the SQL where clause, it changes based on input...");

		animalRequest = new AnimalRequest();
		animal = new Animal();
		// animal.setLastName("Ba%");
		animalRequest.setAnimal(animal);
		// animalRequest.setLastNameSoundsLike(true);

		response = getAnimalDAC().fetchAnimalByRequest(animalRequest);
		assertTrue("Cannot find Animal with with last name like Ba", response.hasResults());
		LOG.info("Animals with last name Ba are retrieved. Notice the SQL where clause...");
	}

	@Test
	public void testAnimalFetchByFancyRequest()
	{
		LOG.info("\nStarting testAnimalFetchByFancyRequest");

		AnimalFancyRequest animalFancyRequest = new AnimalFancyRequest();

		animalFancyRequest.setName(new SearchString("Tucan", SearchString.SearchType.STARTS_WITH, true));

		InternalResultsResponse<Animal> response = getAnimalDAC().fetchAnimalByFancyRequest(animalFancyRequest);

		assertTrue("Should find 3 animals with last name start with "
				+ animalFancyRequest.getName().getSearchValue(), response.getResultsList().size() == 1);

		LOG.info("Results size as expected=" + response.getResultsList().size());

		animalFancyRequest.setName(null);
		// animalFancyRequest.setFirstName(new SearchString("Rich", SearchString.SearchType.STARTS_WITH, true));
		response = getAnimalDAC().fetchAnimalByFancyRequest(animalFancyRequest);
		assertTrue("Should find 2 animals with first name start with "
				+ 0, response.getResultsList().size() == 3);
		LOG.info("Results size as expected=" + response.getResultsList().size());

	}

	/**
	 * This test retrieves a Animal model object and add's a new EFT association to it.
	 */
	@Test
	public void testEFTInsertUpdate()
	{
		LOG.info("\nStarting testEFTInsertUpdate");
		Animal animal_req = new Animal();
		animal_req.setId(99);
		Animal animal = getAnimalDAC().fetchAnimalById(animal_req);
		org.junit.Assert.assertEquals(animal.getId(), animal_req.getId());

		EFT eft = new EFT();
		eft.setAccountNumber("1234567890");
		eft.setBank("QAT");
		eft.setEffectiveStartDate(new Date());
		eft.setEffectiveEndDate(new Date());
		eft.setModelAction(QATModel.PersistanceActionEnum.INSERT);

		// animal.setEft(eft);

		InternalResultsResponse<Animal> response = getAnimalDAC().updateAnimal(animal);
		assertTrue("First Update failed", response.hasResults());
		LOG.info("We Updated the Animal and only the EFT association was impacted as a result of the new EFT instance being added.");

		eft.setBank("Omaha");
		eft.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		response = getAnimalDAC().updateAnimal(animal);
		assertTrue("Second Update failed", response.hasResults());

		LOG.info("We Updated the Animal again and the EFT association should have been the only thing updated on the Animal.");
	}

	/**
	 * With this test we update one of the Contact entries for a Animal.
	 */
	@Test
	public void testContactUpdate()
	{

		LOG.info("\nStarting testContactUpdate pay attention to the SQL");
		Animal animal_req = new Animal();
		animal_req.setId(299);
		Animal animal = getAnimalDAC().fetchAnimalById(animal_req);
		Assert.assertEquals(animal.getId(), animal_req.getId());

		// List<Contact> contactList = animal.getContactList();
		// assertTrue("Contact list should contain at least 6 ContactAnimal", contactList.size() == 6);

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

		InternalResultsResponse<Animal> response = getAnimalDAC().updateAnimal(animal);
		assertTrue("Contact Update failed", response.hasResults());

		LOG.info("We Updated one of the Animal's contact so the Animal's contact collection is "
				+ "iterated through and only the one that was updated based on the PersistanceActionEnum will actually be update on the database");
		assertTrue("Update count should be greater than zero", response.hasResults());
	}

	/**
	 * With this test we insert Contact entries for a Animal.
	 */
	@Test
	public void testContactInsert()
	{
		LOG.info("\nStarting testContactInsert");
		Animal animal_req = new Animal();
		animal_req.setId(99);
		Animal animal = getAnimalDAC().fetchAnimalById(animal_req);
		Assert.assertEquals(animal.getId(), animal_req.getId());

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
		// add all contacts to the animal
		// animal.setContactList(contactList);
		// getAnimalDAC().updateAnimal(animal);
		animal = getAnimalDAC().fetchAnimalById(animal_req);
		// Assert.assertTrue("", animal.getContactList().size() == 6);
		LOG.info("\nEnd testContactInsert:Review SQL carefully to follow test case");
	}

	/**
	 * With this test we take an existing Animal and copy them, changing the name and adding a single Contact and an EFT
	 * association.
	 */
	@Test
	public void testAnimalInsert()
	{
		LOG.info("\nStarting testAnimalInsert");

		InternalResultsResponse<Animal> response = getAnimalDAC().fetchAllAnimals();
		assertTrue("List should contain at least 1 Animal", response.hasResults());

		Animal animal = response.getFirstResult();
		// animal.setFirstName("Fred");
		animal.setModelAction(QATModel.PersistanceActionEnum.INSERT);

		// List<Contact> contactList = animal.getContactList();
		// Contact contact = contactList.get(1);
		// contact.setModelAction(QATModel.PersistanceActionEnum.INSERT);

		EFT eft = new EFT();
		eft.setAccountNumber("1234567890");
		eft.setBank("QAT");
		eft.setEffectiveStartDate(new Date());
		eft.setEffectiveEndDate(new Date());
		eft.setModelAction(QATModel.PersistanceActionEnum.INSERT);
		// animal.setEft(eft);

		LOG.info("We copied an existing Animal, along with a single contact, marked them for Insert, create an new EFT and invoked the Animal.Insert operation on the DAC.");
		LOG.info("You should see 4 insert statements");

		response = getAnimalDAC().insertAnimal(animal);
		assertTrue("The insert failed", response.hasResults());

		LOG.info("The Insert worked and a new Unique ID was assigned from the database, id="
				+ response.getFirstResult().getId());
	}

	@Test
	public void testAnimalUpdate()
	{
		LOG.info("\nStarting testAnimalUpdate");

		InternalResultsResponse<Animal> response = getAnimalDAC().fetchAllAnimals();
		assertTrue("List should not be null from fetchAllAnimals", response.hasResults());

		Animal animal = response.getFirstResult();

		// animal.setEmployeeNumber(6046000);
		animal.setModelAction(QATModel.PersistanceActionEnum.UPDATE);

		response = getAnimalDAC().updateAnimal(animal);
		assertTrue("Results from Update should not be null.", response.hasResults());

	}

	@Test
	public void testAnimalDelete()
	{
		LOG.info("\nStarting testAnimalDelete");
		Animal animal_req = new Animal();
		animal_req.setId(99);
		Animal animal = getAnimalDAC().fetchAnimalById(animal_req);
		assertEquals(animal.getId(), animal_req.getId());
		getAnimalDAC().deleteAnimal(animal_req);
		animal = getAnimalDAC().fetchAnimalById(animal_req);
		assertNull(animal);
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/complexmo/unittest/dac/mybatis/conf/insertComplexMO.sql", false);
	}

}
