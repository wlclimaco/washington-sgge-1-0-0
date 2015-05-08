package com.prosperitasglobal.sendsolv.sdn.integrationtest.bac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.Address;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.ContactTypeEnum;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.CountryUsage;
import com.prosperitasglobal.sendsolv.model.CountryUsageEnum;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.sdn.bac.impl.SdnCheckerBACImpl;
import com.prosperitasglobal.sendsolv.sdn.model.Name;
import com.prosperitasglobal.sendsolv.sdn.model.SdnFieldEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatch;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchField;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchRecord;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.qat.framework.model.request.Request;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/sdn/unittest/bac/conf/sdnbacimpltest.xml"})
public class SdnCheckerBACImplTest extends AbstractJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(SdnCheckerBACImplTest.class);

	@Resource
	private SdnCheckerBACImpl sdnValidator;

	@Test
	public void testGenerateNameList()
	{
		// P(n,r) = n! / (n-r)!

		// One first, two middle, one last name

		SdnMatch sdnMatch = new SdnMatch();
		sdnMatch.setFirstName("Peter");
		sdnMatch.setMiddleName("Spider Man");
		sdnMatch.setLastName("Parker");

		Set<Name> nameList = getSdnValidator().generateNameListFromRequest(sdnMatch);

		assertNotNull(nameList);
		assertEquals(12, nameList.size());

		// One first, one middle, one last name
		sdnMatch.setFirstName("Peter");
		sdnMatch.setMiddleName("Spider");
		sdnMatch.setLastName("Parker");
		nameList = getSdnValidator().generateNameListFromRequest(sdnMatch);

		assertNotNull(nameList);
		assertEquals(6, nameList.size());

		// One first, two middle, one last name
		sdnMatch.setFirstName("A");
		sdnMatch.setMiddleName("B");
		sdnMatch.setLastName("C");

		nameList = getSdnValidator().generateNameListFromRequest(sdnMatch);
		assertNotNull(nameList);
		assertEquals(6, nameList.size());

		// No first, two middle, one last name
		sdnMatch = new SdnMatch();
		sdnMatch.setMiddleName("B C");
		sdnMatch.setLastName("D");

		nameList = getSdnValidator().generateNameListFromRequest(sdnMatch);

		assertNotNull(nameList);
		assertEquals(6, nameList.size());

		// No first, two middle, one last name
		sdnMatch = new SdnMatch();
		sdnMatch.setFirstName("Sam");
		sdnMatch.setLastName("Nabors");

		nameList = getSdnValidator().generateNameListFromRequest(sdnMatch);

		assertNotNull(nameList);
		assertEquals(2, nameList.size());
	}

	@Test
	public void testSdnValidatorForIndividual_1()
	{
		getSdnValidator().setSimilarityThreshold(0.85d);

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();

		Member member = CommonTestRoutines.createDummyMember();
		member.setFirstName("Mary");
		member.setLastName("Abdelnur");
		member.setMiddleName("");
		member.getNameList().clear();
		for (Contact contact : member.getContactList())
		{
			if (ContactTypeEnum.POSTAL_WORK.equals(contact.getContactType()))
			{
				((Address)contact).getCountry().setDescription("Panama");
			}
		}

		request.setPersonOrBusiness(member);

		InternalResultsResponse<SdnStatusHistory> response = getSdnValidator().checkMemberSdn(request);

		assertFalse("response is in error ", response.isInError());
		assertNotNull("response.getResultsList() is Null ", response.getResultsList());

		assertEquals("Should return 1 history", 1, response.getResultsList().size());
		assertEquals("Should return 0 matches", 0, response.getFirstResult().getSdnMatchRecordList().size());

		assertMatchRecord(response.getFirstResult().getSdnMatchRecordList());

		printSdnFields(response.getFirstResult().getSdnMatchRecordList());

	}

	@Test
	public void testSdnValidatorForIndividual_2()
	{
		getSdnValidator().setSimilarityThreshold(0.9d);

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();

		Member member = CommonTestRoutines.createDummyMember();
		member.setFirstName("Miguel de los Santos");
		member.setLastName("PENA TORRES");
		member.setMiddleName("");
		member.getNameList().clear();

		for (Contact contact : member.getContactList())
		{
			if (ContactTypeEnum.POSTAL_WORK.equals(contact.getContactType()))
			{
				((Address)contact).getCountry().setDescription("Colombia");
				((Address)contact).setCityName("Paratebueno");
			}
		}

		for (Document document : member.getDocumentList())
		{
			document.setValue("5549825");
		}

		Date dateOfBirth;
		try
		{
			dateOfBirth = new SimpleDateFormat("dd-MM-yyyy").parse("06-06-1941");
			member.setDateOfBirth(dateOfBirth.getTime());
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setPersonOrBusiness(member);

		InternalResultsResponse<SdnStatusHistory> response = getSdnValidator().checkMemberSdn(request);

		assertFalse("response is in error ", response.isInError());
		assertNotNull("response.getResultsList() is Null ", response.getResultsList());

		assertEquals("Should return 1 history", 1, response.getResultsList().size());
		assertEquals("Should return 1 match", 4, response.getFirstResult().getSdnMatchRecordList().size());

		assertMatchRecord(response.getFirstResult().getSdnMatchRecordList());

		printSdnFields(response.getFirstResult().getSdnMatchRecordList());
	}

	private void assertMatchRecord(List<SdnMatchRecord> sdnMatchRecordList)
	{
		for (SdnMatchRecord sdnMatchRecord : sdnMatchRecordList)
		{
			assertTrue("Record should have at least one field", sdnMatchRecord.getSdnMatchFieldList().size() > 0);

			for (SdnMatchField sdnMatchField : sdnMatchRecord.getSdnMatchFieldList())
			{
				if (!SdnFieldEnum.FIRST_LAST_NAME.equals(sdnMatchField.getSdnField()) &&
						!SdnFieldEnum.FIRST_NAME.equals(sdnMatchField.getSdnField())
						&& !SdnFieldEnum.LAST_NAME.equals(sdnMatchField.getSdnField()))
				{
					assertTrue("Should return uid", sdnMatchField.getSdnUid() > 0);
				}
			}
		}
	}

	@Test
	public void testSdnValidatorForIndividual_3()
	{
		getSdnValidator().setSimilarityThreshold(0.9d);

		/*
		 * try
		 * {
		 * getSdnValidator().setSdnFileUrl(new File("c:/sdn1/test.xml").toURI().toURL().toString());
		 * }
		 * catch (MalformedURLException e)
		 * {
		 * // TODO Auto-generated catch block
		 * e.printStackTrace();
		 * }
		 */

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();

		Member member = CommonTestRoutines.createDummyMember();

		member.setFirstName("Gilberto");
		member.setLastName("Godina");

		member.setMiddleName("");
		member.getNameList().clear();

		Country country = new Country();
		country.setCode("MEX");
		country.setDescription("Mexico");

		CountryUsage countryUsage = new CountryUsage();
		countryUsage.setCountry(country);
		countryUsage.setUsage(CountryUsageEnum.CITIZENSHIP);

		member.setCountryUsageList(null);
		member.getCountryUsageList().add(countryUsage);

		request.setPersonOrBusiness(member);

		InternalResultsResponse<SdnStatusHistory> response = getSdnValidator().checkMemberSdn(request);

		assertFalse("response is in error ", response.isInError());
		assertNotNull("response.getResultsList() is Null ", response.getResultsList());

		assertEquals("Should return 1 history", 1, response.getResultsList().size());
		assertEquals("Should return 1 match", 1, response.getFirstResult().getSdnMatchRecordList().size());

		assertMatchRecord(response.getFirstResult().getSdnMatchRecordList());

		printSdnFields(response.getFirstResult().getSdnMatchRecordList());

	}

	// Test with multiple documents
	@Test
	public void testSdnValidatorForIndividual_4()
	{
		getSdnValidator().setSimilarityThreshold(0.9d);

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();

		Member member = CommonTestRoutines.createDummyMember();
		member.setFirstName("Aleksey Alekseyevich");
		member.setLastName("ZAYTSEV");
		member.setMiddleName("");
		member.getNameList().clear();

		member.setDocumentList(new ArrayList<Document>());
		member.getDocumentList().add(generateDocument("H2029462"));
		member.getDocumentList().add(generateDocument("4103417473"));
		member.getDocumentList().add(generateDocument("63-4604880"));

		request.setPersonOrBusiness(member);

		InternalResultsResponse<SdnStatusHistory> response = getSdnValidator().checkMemberSdn(request);

		assertFalse("response is in error ", response.isInError());
		assertNotNull("response.getResultsList() is Null ", response.getResultsList());

		assertEquals("Should return 1 history", 1, response.getResultsList().size());
		assertEquals("Should return 1 match", 1, response.getFirstResult().getSdnMatchRecordList().size());
		assertEquals("Should return 19 fields", 19, response.getFirstResult().getSdnMatchRecordList().get(0)
				.getSdnMatchFieldList().size());

		// 4 of the 9 should be Ids
		int numberOfIdMatches = 0;
		for (SdnMatchField sdnMatchField : response.getFirstResult().getSdnMatchRecordList().get(0)
				.getSdnMatchFieldList())
		{
			if (SdnFieldEnum.ID.equals(sdnMatchField.getSdnField()))
			{
				numberOfIdMatches++;
			}
		}

		assertEquals("Should return 3 id matches", 3, numberOfIdMatches);

		assertMatchRecord(response.getFirstResult().getSdnMatchRecordList());

		printSdnFields(response.getFirstResult().getSdnMatchRecordList());

	}

	@Test
	public void testSdnValidatorForIndividual_5()
	{
		getSdnValidator().setSimilarityThreshold(0.9d);

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();

		Member member = CommonTestRoutines.createDummyMember();
		member.setFirstName("Onecimo Antonio");
		member.setLastName("AMAYA ALEMAN");
		for (Contact contact : member.getContactList())
		{
			if (ContactTypeEnum.POSTAL_WORK.equals(contact.getContactType()))
			{
				((Address)contact).getCountry().setDescription("Mexico");
			}
		}

		for (Document document : member.getDocumentList())
		{
			document.setValue("AAAO650225HNLMLN03");
		}

		request.setPersonOrBusiness(member);

		InternalResultsResponse<SdnStatusHistory> response = getSdnValidator().checkMemberSdn(request);

		assertFalse("response is in error ", response.isInError());
		assertNotNull("response.getResultsList() is Null ", response.getResultsList());

		assertEquals("Should return 1 history", 1, response.getResultsList().size());
		assertEquals("Should return 1 match", 1, response.getFirstResult().getSdnMatchRecordList().size());

		assertMatchRecord(response.getFirstResult().getSdnMatchRecordList());

		printSdnFields(response.getFirstResult().getSdnMatchRecordList());
	}

	@Test
	public void testSdnValidatorForIndividual_6()
	{
		getSdnValidator().setSimilarityThreshold(0.9d);

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();

		Member member = CommonTestRoutines.createDummyMember();
		member.setFirstName("Sam");
		member.setLastName("Nabors");
		member.setMiddleName("");
		member.getNameList().clear();

		request.setPersonOrBusiness(member);

		InternalResultsResponse<SdnStatusHistory> response = getSdnValidator().checkMemberSdn(request);

		assertFalse("response is in error ", response.isInError());
		assertNotNull("response.getResultsList() is Null ", response.getResultsList());

		assertEquals("Should return 1 history", 1, response.getResultsList().size());
		assertEquals("Should return 0 matches", 0, response.getFirstResult().getSdnMatchRecordList().size());

		assertMatchRecord(response.getFirstResult().getSdnMatchRecordList());

		printSdnFields(response.getFirstResult().getSdnMatchRecordList());

	}

	@Test
	public void testSdnValidatorForIndividual_7()
	{
		LOG.debug("*********** Test 1 ************");
		testSdnValidatorForGenericIndividual("Javier", "Hernandez", "Mexico", CountryUsageEnum.CITIZENSHIP, null, 1, 1);

		LOG.debug("*********** Test 2 ************");
		testSdnValidatorForGenericIndividual("Luis Eduardo", "Mendez", "Mexico", CountryUsageEnum.CITIZENSHIP, null, 1,
				1);
		LOG.debug("*********** Test 3 ************");
		testSdnValidatorForGenericIndividual("Alfonso", "Mendez", "Colombia", CountryUsageEnum.CITIZENSHIP, null, 1, 1);

		LOG.debug("*********** Test 4 ************");
		testSdnValidatorForGenericIndividual("Jeronimo", "Landeros", "Mexico", CountryUsageEnum.BIRTH, null, 1, 1);

		LOG.debug("*********** Test 5 ************");
		testSdnValidatorForGenericIndividual("Mario", "Garcia", "Mexico", CountryUsageEnum.BIRTH, null, 1, 3);

		LOG.debug("*********** Test 6 ************");
		testSdnValidatorForGenericIndividual("Mario", "Zambada", "Mexico", CountryUsageEnum.BIRTH, null, 1, 2);

		LOG.debug("*********** Test 7 ************");
		testSdnValidatorForGenericIndividual("abbas", "Hussein", "Colombia", CountryUsageEnum.RESIDENCE, null, 1, 2);

		LOG.debug("*********** Test 8 ************");
		testSdnValidatorForGenericIndividual("Fuad", "Georges", "Brazil", CountryUsageEnum.RESIDENCE, null, 1, 1);

		LOG.debug("*********** Test 9 ************");
		testSdnValidatorForGenericIndividual("Gipsy", "Casarez", "Mexico", CountryUsageEnum.RESIDENCE, null, 1, 1);

		LOG.debug("*********** Test 10 ************");
		testSdnValidatorForGenericIndividual("Jose", "Luis", null, null, "01-01-1945", 1, 1);

		LOG.debug("*********** Test 11 ************");
		testSdnValidatorForGenericIndividual("Ahmed", "Garbaya", null, null, "01-01-1963", 1, 1);

		LOG.debug("*********** Test 12 ************");
		testSdnValidatorForGenericIndividual("Juan Ramon", "Lopez", null, null, "01-01-1978", 1, 1);

		LOG.debug("*********** Test 13 ************");
		testSdnValidatorForGenericIndividual("Jose", "Ricardo", null, null, "01-01-1955", 1, 1);

	}

	@Test
	public void testSdnValidatorForIds()
	{
		LOG.debug("*********** Test 1 ************");
		testSdnValidatorForGenericIds("Li", "ZZZZ", "027684733", 1, 1);

		LOG.debug("*********** Test 2 ************");
		testSdnValidatorForGenericIds("Li", "ZZZZ", "601623570", 1, 1);

		LOG.debug("*********** Test 3 ************");
		testSdnValidatorForGenericIds("Li", "ZZZZ", "0801009914", 1, 1);

		LOG.debug("*********** Test 4 ************");
		testSdnValidatorForGenericIds("Li", "ZZZZ", "32480630", 1, 1);

		LOG.debug("*********** Test 5 ************");
		testSdnValidatorForSsn("Li", "ZZZZ", "589-17-6824", 1, 1);

	}

	private void testSdnValidatorForGenericIds(String firstName, String lastName, String docNumber, int historyNumber,
			int matchNumber)
	{
		getSdnValidator().setSimilarityThreshold(0.9d);

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();

		// assertTrue(loadTestSdnFile("c:/sdn1/testSSN.xml"));

		Member member = CommonTestRoutines.createDummyMember();
		member.setFirstName(firstName);
		member.setLastName(lastName);

		for (Document document : member.getDocumentList())
		{
			document.setValue(docNumber);
		}

		request.setPersonOrBusiness(member);

		InternalResultsResponse<SdnStatusHistory> response = getSdnValidator().checkMemberSdn(request);

		assertFalse("response is in error ", response.isInError());
		assertNotNull("response.getResultsList() is Null ", response.getResultsList());

		assertEquals("Should return " + historyNumber + " history", historyNumber, response.getResultsList().size());
		assertEquals("Should return " + matchNumber + " match", matchNumber, response.getFirstResult()
				.getSdnMatchRecordList().size());

		assertMatchRecord(response.getFirstResult().getSdnMatchRecordList());

		printSdnFields(response.getFirstResult().getSdnMatchRecordList());

	}

	private void testSdnValidatorForSsn(String firstName, String lastName, String ssnNumber, int historyNumber,
			int matchNumber)
	{
		getSdnValidator().setSimilarityThreshold(0.9d);

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();

		// assertTrue(loadTestSdnFile("c:/sdn1/testSSN.xml"));

		Member member = CommonTestRoutines.createDummyMember();
		member.setFirstName(firstName);
		member.setLastName(lastName);

		member.setSocialSecurityNumber(ssnNumber);

		request.setPersonOrBusiness(member);

		InternalResultsResponse<SdnStatusHistory> response = getSdnValidator().checkMemberSdn(request);

		assertFalse("response is in error ", response.isInError());
		assertNotNull("response.getResultsList() is Null ", response.getResultsList());

		assertEquals("Should return " + historyNumber + " history", historyNumber, response.getResultsList().size());
		assertEquals("Should return " + matchNumber + " match", matchNumber, response.getFirstResult()
				.getSdnMatchRecordList().size());

		assertMatchRecord(response.getFirstResult().getSdnMatchRecordList());

		printSdnFields(response.getFirstResult().getSdnMatchRecordList());

	}

	private boolean loadTestSdnFile(String fileNameAndPath)
	{
		try
		{
			getSdnValidator().setSdnFileUrl(new File(fileNameAndPath).toURI().toURL().toString());
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public void testSdnValidatorForGenericIndividual(String firstName, String lastName, String countryName,
			CountryUsageEnum countryUsageEnum, String dateOfBirthAsString,
			int historyNumber, int matchNumber)
	{
		getSdnValidator().setSimilarityThreshold(0.9d);

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();

		// assertTrue(loadTestSdnFile("c:/sdn1/testJeronimoLanderos.xml"));

		Member member = CommonTestRoutines.createDummyMember();

		member.setFirstName(firstName);
		member.setLastName(lastName);

		member.setMiddleName("");
		member.getNameList().clear();

		if (countryUsageEnum != null)
		{
			if (CountryUsageEnum.RESIDENCE.equals(countryUsageEnum))
			{
				for (Contact contact : member.getContactList())
				{
					if (ContactTypeEnum.POSTAL_WORK.equals(contact.getContactType()))
					{
						((Address)contact).getCountry().setDescription(countryName);
					}
				}
			}
			else
			{
				Country country = new Country();
				country.setCode("XXX");
				country.setDescription(countryName);

				CountryUsage countryUsage = new CountryUsage();
				countryUsage.setCountry(country);
				countryUsage.setUsage(countryUsageEnum);

				member.setCountryUsageList(null);
				member.getCountryUsageList().add(countryUsage);
			}
		}

		if (dateOfBirthAsString != null)
		{
			Date dateOfBirth;
			try
			{
				dateOfBirth = new SimpleDateFormat("MM-dd-yyyy").parse(dateOfBirthAsString);
				member.setDateOfBirth(dateOfBirth.getTime());
			}
			catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		request.setPersonOrBusiness(member);

		InternalResultsResponse<SdnStatusHistory> response = getSdnValidator().checkMemberSdn(request);

		assertFalse("response is in error ", response.isInError());
		assertNotNull("response.getResultsList() is Null ", response.getResultsList());

		assertEquals("Should return " + historyNumber + " history", historyNumber, response.getResultsList().size());
		assertEquals("Should return " + matchNumber + " match", matchNumber, response.getFirstResult()
				.getSdnMatchRecordList().size());

		assertMatchRecord(response.getFirstResult().getSdnMatchRecordList());

		printSdnFields(response.getFirstResult().getSdnMatchRecordList());

	}

	@Test
	public void testSdnValidatorForEntity()
	{
		getSdnValidator().setSimilarityThreshold(0.9d);

		SdnCheckerRequest<Business> request = new SdnCheckerRequest<Business>();

		Business business = CommonTestRoutines.createDummyOrganization();

		business.setName("AEROCARIBBEAN");

		for (Contact contact : business.getContactList())
		{
			if (ContactTypeEnum.POSTAL_WORK.equals(contact.getContactType()))
			{
				((Address)contact).getCountry().setDescription("Cuba");
				((Address)contact).setCityName("Havana");
			}
		}

		request.setPersonOrBusiness(business);

		InternalResultsResponse<SdnStatusHistory> response = getSdnValidator().checkBusinessSdn(request);

		assertFalse("response is in error ", response.isInError());
		assertNotNull("response.getResultsList() is Null ", response.getResultsList());
		assertEquals("", 1, response.getResultsList().size());
		assertEquals("", 1, response.getFirstResult().getSdnMatchRecordList().size());
		assertEquals("", 6, response.getFirstResult().getSdnMatchRecordList().get(0).getSdnMatchFieldList().size());

		printSdnFields(response.getFirstResult().getSdnMatchRecordList());
	}

	@Test
	public void testCheckNewSdnFile()
	{
		// Please start with an sdn file on the c:\\sdn1\ folder.

		getSdnValidator().setSdnFileUrl("http://www.qat.com/sdn.xml");
		InternalResponse response = getSdnValidator().checkForNewSdnFile(new Request());
		assertNotNull(response);
		assertEquals(Status.ExceptionError, response.getStatus());

		getSdnValidator().setSdnFileUrl("http://www.treasury.gov/ofac/downloads/sdn.xml");
		response = getSdnValidator().checkForNewSdnFile(new Request());
		assertNotNull(response);
		// no difference in sdn file
		assertEquals(Status.NoRowsUpdatedError, response.getStatus());

		// delete the file
		FileUtils.deleteQuietly(new File("c:\\sdn1\\sdn.xml"));
		response = getSdnValidator().checkForNewSdnFile(new Request());
		assertNotNull(response);
		assertEquals(Status.OperationSuccess, response.getStatus());

	}

	@Test
	public void testJaroWinkler()
	{
		// Test from class documentation
		// SdnMatchField sdnMatchField =
		// getSdnValidator().isMatch("MARTHA", "MARHTA", SdnFieldEnum.FIRST_LAST_NAME);

		SdnMatchField sdnMatchField =
				getSdnValidator().isMatch("Miguel Morfin Rodriguez", "Miguel Rodriguez",
						SdnFieldEnum.FIRST_LAST_NAME);

		assertNotNull("sdnMatchField 1 is Null", sdnMatchField);
		assertEquals("Error on testJaroWinkler 1", 0.9141d, sdnMatchField.getMatchProximity().doubleValue(), 0.001d);

		// Test from class documentation
		getSdnValidator().setSimilarityThreshold(0.8d);
		sdnMatchField = getSdnValidator().isMatch("JONES", "JOHNSON", SdnFieldEnum.FIRST_LAST_NAME);
		assertNotNull("sdnMatchField 2 is Null", sdnMatchField);
		assertEquals("Error on testJaroWinkler 2", 0.832d, sdnMatchField.getMatchProximity().doubleValue(), 0.001d);

		// Perfect Match
		sdnMatchField = getSdnValidator().isMatch("JOHN DOE", "JOHN DOE", SdnFieldEnum.FIRST_LAST_NAME);
		assertNotNull("sdnMatchField 3 is Null", sdnMatchField);
		assertEquals("Error on testJaroWinkler 3", 1.0d, sdnMatchField.getMatchProximity().doubleValue(), 0.0d);
	}

	private Document generateDocument(String value)
	{
		Document document = new Document();
		document.setValue(value);

		return document;
	}

	private void printSdnFields(List<SdnMatchRecord> fieldList)
	{
		for (int i = 0; i < fieldList.size(); i++)
		{
			LOG.debug("******************");
			LOG.debug("Match number: " + (i + 1));
			assertTrue("Should return at least one field", fieldList.get(i).getSdnMatchFieldList().size() > 0);

			int z = 1;
			for (SdnMatchField sdnMatchField : fieldList.get(i).getSdnMatchFieldList())
			{
				LOG.debug("******************");
				LOG.debug("Field number: " + z);
				LOG.debug("Field Value:" + sdnMatchField.getSdnFieldValue());
				LOG.debug("SDN Uid:" + sdnMatchField.getSdnUid());
				LOG.debug("SDN Value:" + sdnMatchField.getSdnValue());
				LOG.debug("PGSi Value:" + sdnMatchField.getSystemValue());
				LOG.debug("Proximity:" + sdnMatchField.getMatchProximity());
				z++;
			}
		}
	}

	public SdnCheckerBACImpl getSdnValidator()
	{
		return sdnValidator;
	}

	public void setSdnValidator(SdnCheckerBACImpl sdnValidator)
	{
		this.sdnValidator = sdnValidator;
	}
}
