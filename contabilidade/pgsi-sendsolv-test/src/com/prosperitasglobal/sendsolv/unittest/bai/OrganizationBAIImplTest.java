package com.prosperitasglobal.sendsolv.unittest.bai;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.dac.IIndustryClassificationDAC;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.ContactTypeEnum;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.IOrganizationBAC;
import com.prosperitasglobal.sendsolv.bai.IOrganizationBAI;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.PriorityEnum;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.OrganizationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.DocumentResponse;
import com.prosperitasglobal.sendsolv.model.response.OrganizationResponse;
import com.prosperitasglobal.sendsolv.model.response.RiskResponse;
import com.prosperitasglobal.sendsolv.validation.PagedInquiryRequestValidator;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class OrganizationBAITest.
 */
@ContextConfiguration(locations =
{
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/organizationbaiimpltest.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/sendsolv-dac-context.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/pgsi-sendsolv-validators-context-test.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"
})
public class OrganizationBAIImplTest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(OrganizationBAIImplTest.class);

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAME_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAME_REQUIRED =
			"sendsolv.base.businessvalidator.name.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAME_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAME_TOO_BIG =
			"sendsolv.base.businessvalidator.name.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_DBANAME_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_DBANAME_REQUIRED =
			"sendsolv.base.organizationvalidator.dbaname.required";

	/** The Constant PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_DBANAME_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_DBANAME_TOO_BIG =
			"sendsolv.base.organizationvalidator.dbaname.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED =
			"sendsolv.base.businessvalidator.status.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_EMPLOYERIDENTIFICATIONNUMBER_INVALID. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_EMPLOYERIDENTIFICATIONNUMBER_INVALID =
			"sendsolv.base.businessvalidator.employeridentificationnumber.invalid";

	/** The Constant CBOF_BASE_CONTACTVALIDATOR_NEED_PRIMARY_PHONE_NUMBER. */
	private static final String CBOF_BASE_CONTACTVALIDATOR_NEED_PRIMARY_PHONE_NUMBER =
			"cbof.base.contactvalidator.phone.primary.required";

	/** The Constant CBOF_BASE_CONTACTVALIDATOR_NEED_ONLY_ONE_PRIMARY_PHONE_NUMBER. */
	private static final String CBOF_BASE_CONTACTVALIDATOR_NEED_ONLY_ONE_PRIMARY_PHONE_NUMBER =
			"cbof.base.contactvalidator.phone.primary.only.one.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_NAICS. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_NAICS =
			"sendsolv.base.businessvalidator.invalid.naics";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_SIC. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_SIC =
			"sendsolv.base.businessvalidator.invalid.sic";

	/** The Constant PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_NAICSC_OR_SICC_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_NAICSC_OR_SICC_REQUIRED =
			"sendsolv.base.businessvalidator.naicsc.or.sicc.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_ID_REQUIRED =
			"sendsolv.base.documentvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_FILED_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_FILED_REQUIRED =
			"sendsolv.base.documentvalidator.filed.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_TYPE_REQUIRED =
			"sendsolv.base.documentvalidator.document.type.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_TYPE_REQUIRED =
			"sendsolv.base.documentvalidator.parent.key.type.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_REQUIRED =
			"sendsolv.base.documentvalidator.parent.key.required";

	/** The Constant SENDSOLV_BASE_RISKVALIDATOR_RISKLEVEL_REQUIRED. */
	private static final String SENDSOLV_BASE_RISKVALIDATOR_RISKLEVEL_REQUIRED =
			"sendsolv.base.riskvalidator.risklevel.required";

	/** The Constant SENDSOLV_BASE_VALIDATOR_VERSION_REQUIRED. */
	private static final String SENDSOLV_BASE_VALIDATOR_VERSION_REQUIRED = "sendsolv.base.validator.version.required";

	/** The Constant NEW_RECORD_SHOULD_NOT_COME_BACK. */
	private static final String NEW_RECORD_SHOULD_NOT_COME_BACK = "New record should not come back.";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant BAC_KEY. */
	private static final String BAC_KEY = "sendsolv.base.organizationvalidator.id.required";

	/** The Constant MESSAGE_LIST_SHOULD_BE_EMPTY. */
	private static final String MESSAGE_LIST_SHOULD_BE_EMPTY = "Message list should be empty";

	/** The Constant NEW_RECORD_SHOULD_COME_BACK. */
	private static final String NEW_RECORD_SHOULD_COME_BACK = "New record should come back.";

	/** The Constant TWO_HUNDRED_FIFTY_SIX. */
	private static final Integer TWO_HUNDRED_FIFTY_SIX = 256;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The organization bai. */
	private IOrganizationBAI organizationBAI;

	/** The mock organization bac. */
	private IOrganizationBAC mockOrganizationBAC;

	/** The mock organization dac. */
	private IOrganizationDAC mockOrganizationDAC;

	// error response object to be used in several tests.
	/** The err response. */
	private InternalResultsResponse<Organization> errResponse;

	/**
	 * Gets the err response.
	 *
	 * @return the err response
	 */
	public InternalResultsResponse<Organization> getErrResponse()
	{
		return errResponse;
	}

	/**
	 * Sets the err response.
	 *
	 * @param errResponse the err response
	 */
	public void setErrResponse(InternalResultsResponse<Organization> errResponse)
	{
		this.errResponse = errResponse;
	}

	/** The mock industry classification dac. */
	private IIndustryClassificationDAC mockIndustryClassificationDAC;

	/**
	 * Gets the organization bai.
	 *
	 * @return the organization bai
	 */
	public IOrganizationBAI getOrganizationBAI()
	{
		return organizationBAI;
	}

	/**
	 * Sets the organization bai.
	 *
	 * @param organizationBAI the organization bai
	 */
	@Resource
	public void setOrganizationBAI(IOrganizationBAI organizationBAI)
	{
		this.organizationBAI = organizationBAI;
	}

	/**
	 * Gets the mock organization bac.
	 *
	 * @return the mock organization bac
	 */
	public IOrganizationBAC getMockOrganizationBAC()
	{
		return mockOrganizationBAC;
	}

	/**
	 * Sets the mock organization bac.
	 *
	 * @param mockOrganizationBAC the mock organization bac
	 */
	@Resource
	public void setMockOrganizationBAC(IOrganizationBAC mockOrganizationBAC)
	{
		this.mockOrganizationBAC = mockOrganizationBAC;
	}

	/**
	 * Gets the mock industry classification dac.
	 *
	 * @return the mock industry classification dac
	 */
	public IIndustryClassificationDAC getMockIndustryClassificationDAC()
	{
		return mockIndustryClassificationDAC;
	}

	/**
	 * Sets the mock industry classification dac.
	 *
	 * @param mockIndustryClassificationDAC the mock industry classification dac
	 */
	@Resource
	public void setMockIndustryClassificationDAC(IIndustryClassificationDAC mockIndustryClassificationDAC)
	{
		this.mockIndustryClassificationDAC = mockIndustryClassificationDAC;
	}

	/**
	 * Gets the mock organization dac.
	 *
	 * @return the mock organization dac
	 */
	public IOrganizationDAC getMockOrganizationDAC()
	{
		return mockOrganizationDAC;
	}

	/**
	 * Sets the mock organization dac.
	 *
	 * @param mockOrganizationDAC the mock organization dac
	 */
	@Resource
	public void setMockOrganizationDAC(IOrganizationDAC mockOrganizationDAC)
	{
		this.mockOrganizationDAC = mockOrganizationDAC;
	}

	/**
	 * The Constructor.
	 */
	public OrganizationBAIImplTest()
	{
		// setup the error response object.
		setErrResponse(new InternalResultsResponse<Organization>());
		getErrResponse().addMessage(BAC_KEY, MessageSeverity.Error, MessageLevel.Field);
		getErrResponse().setStatus(Status.UnspecifiedError);
	}

	/**
	 * Test insert location.
	 */
	@Test
	public void testInsertOrganization()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setModelAction(PersistanceActionEnum.INSERT);

		request.setOrganization(organization);

		mockFetchCodeValueByCode();

		Mockito.when(getMockOrganizationBAC().insertOrganization(request)).thenReturn(
				returnInsert(organization));

		OrganizationResponse response = getOrganizationBAI().insertOrganization(request);

		CommonTestRoutines.assertResponse(response);

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockOrganizationBAC()).insertOrganization(request);

	}

	/**
	 * Test insert organization without name.
	 */
	@Test
	public void testInsertOrganizationWithoutName()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setModelAction(PersistanceActionEnum.INSERT);
		organization.setName(null);

		request.setOrganization(organization);
		mockFetchCodeValueByCode();

		Mockito.when(getMockOrganizationBAC().insertOrganization(request)).thenReturn(
				returnInsert(organization));

		OrganizationResponse response = getOrganizationBAI().insertOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAME_REQUIRED);

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).insertOrganization(request);

	}

	/**
	 * Test insert organization with name too big.
	 */
	@Test
	public void testInsertOrganizationWithNameTooBig()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setModelAction(PersistanceActionEnum.INSERT);
		organization.setName("N" + createInvalidName());

		request.setOrganization(organization);

		mockFetchCodeValueByCode();

		Mockito.when(getMockOrganizationBAC().insertOrganization(request)).thenReturn(
				returnInsert(organization));

		OrganizationResponse response = getOrganizationBAI().insertOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAME_TOO_BIG);

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).insertOrganization(request);

	}

	/**
	 * Test insert organization no dba name.
	 */
	@Test
	public void testInsertOrganizationNoDbaName()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setDbaName(null);
		organization.setModelAction(PersistanceActionEnum.INSERT);

		request.setOrganization(organization);

		mockFetchCodeValueByCode();

		Mockito.when(getMockOrganizationBAC().insertOrganization(request)).thenReturn(
				new InternalResultsResponse<Organization>());

		OrganizationResponse response = getOrganizationBAI().insertOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_DBANAME_REQUIRED);

		// Ensures the mock's insertOrganization was not called, due to validation error.
		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).insertOrganization(request);
	}

	/**
	 * Test insert organization dba name to big.
	 */
	@Test
	public void testInsertOrganizationDbaNameToBig()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setModelAction(PersistanceActionEnum.INSERT);
		organization.setDbaName("B" + createInvalidName());
		request.setOrganization(organization);

		mockFetchCodeValueByCode();

		Mockito.when(getMockOrganizationBAC().insertOrganization(request)).thenReturn(
				new InternalResultsResponse<Organization>());

		OrganizationResponse response = getOrganizationBAI().insertOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG,
				PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_DBANAME_TOO_BIG);

		// Ensures the mock's insertOrganization was not called, due to validation error.
		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).insertOrganization(request);
	}

	/**
	 * Test insert organization bad ein.
	 */
	@Test
	public void testInsertOrganizationBadEIN()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setEmployerIdentificationNumber("1234567");
		organization.setModelAction(PersistanceActionEnum.INSERT);

		request.setOrganization(organization);

		mockFetchCodeValueByCode();

		Mockito.when(getMockOrganizationBAC().insertOrganization(request)).thenReturn(
				new InternalResultsResponse<Organization>());

		OrganizationResponse response = getOrganizationBAI().insertOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG,
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_EMPLOYERIDENTIFICATIONNUMBER_INVALID);

		organization.setEmployerIdentificationNumber("12345678A");
		response = getOrganizationBAI().insertOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG,
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_EMPLOYERIDENTIFICATIONNUMBER_INVALID);

		// Ensures the mock's insertOrganization was not called, due to validation error.
		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).insertOrganization(request);
	}

	/**
	 * Test insert organization with invalid naicsc.
	 */
	@Test
	public void testInsertOrganizationWithInvalidNaicsc()
	{

		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setModelAction(PersistanceActionEnum.INSERT);
		CodeValue code = new CodeValue();
		code.setCode("12345");
		organization.setNorthAmericanIndustryClassificationSystemCode(code);
		organization.setStandardIndustrialClassificationCode(code);

		request.setOrganization(organization);

		Mockito.when(getMockIndustryClassificationDAC().fetchCodeValueByCode(Matchers.any(CodeValueRequest.class)))
				.thenReturn(new InternalResultsResponse<CodeValue>());

		OrganizationResponse response = getOrganizationBAI().insertOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_NAICS,
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_SIC);

		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).insertOrganization(request);

	}

	/**
	 * Test insert organization with naicsc and sicc null.
	 */
	@Test
	public void testInsertOrganizationWithNaicscAndSiccNull()
	{

		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		Organization organization = CommonTestRoutines.createDummyOrganization();
		CommonTestRoutines.createDummyUserContext(request);

		organization.setNorthAmericanIndustryClassificationSystemCode(null);
		organization.setStandardIndustrialClassificationCode(null);
		organization.setModelAction(PersistanceActionEnum.INSERT);

		request.setOrganization(organization);

		mockFetchCodeValueByCode();

		Mockito.when(getMockOrganizationBAC().insertOrganization(request)).thenReturn(
				new InternalResultsResponse<Organization>());

		OrganizationResponse response = getOrganizationBAI().insertOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG,
				PROSPERITASGLOBAL_BASE_ORGANIZATIONVALIDATOR_NAICSC_OR_SICC_REQUIRED);

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).insertOrganization(request);

	}

	/**
	 * Test insert organization with naicsc and sicc codes null.
	 */
	@Test
	public void testInsertOrganizationWithNaicscAndSiccCodesNull()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setNorthAmericanIndustryClassificationSystemCode(null);
		organization.setStandardIndustrialClassificationCode(null);
		organization.setModelAction(PersistanceActionEnum.INSERT);

		request.setOrganization(organization);

		mockFetchCodeValueByCode();

		Mockito.when(getMockOrganizationBAC().insertOrganization(request)).thenReturn(
				new InternalResultsResponse<Organization>());

		OrganizationResponse response = getOrganizationBAI().insertOrganization(request);

		Assert.assertEquals(1, response.getMessageList().size());

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).insertOrganization(request);
	}

	/**
	 * Test insert organization with bad Phone (no Primary).
	 */
	@Test
	public void testInsertOrganizationBadPhone()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockOrganizationBAC().insertOrganization(request)).thenReturn(
				new InternalResultsResponse<Organization>());

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setModelAction(PersistanceActionEnum.INSERT);

		// Set more than one phones as primary - Should fail
		for (Contact contact : organization.getContactList())
		{
			if (ContactTypeEnum.MOBILE.equals(contact.getContactType())
					|| ContactTypeEnum.OTHER.equals(contact.getContactType())
					|| ContactTypeEnum.PHONE_WORK.equals(contact.getContactType()))
			{
				Phone phone = (Phone)contact;
				phone.setPriority(PriorityEnum.PRIMARY);
			}
		}

		request.setOrganization(organization);

		mockFetchCodeValueByCode();

		OrganizationResponse response = getOrganizationBAI().insertOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG,
				CBOF_BASE_CONTACTVALIDATOR_NEED_ONLY_ONE_PRIMARY_PHONE_NUMBER);

		// Now set all phones as secondary - Should fail
		for (Contact contact : organization.getContactList())
		{
			if (ContactTypeEnum.MOBILE.equals(contact.getContactType())
					|| ContactTypeEnum.OTHER.equals(contact.getContactType())
					|| ContactTypeEnum.PHONE_WORK.equals(contact.getContactType()))
			{
				Phone phone = (Phone)contact;
				phone.setPriority(PriorityEnum.SECONDARY);
			}
		}

		request.setOrganization(organization);

		response = getOrganizationBAI().insertOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG,
				CBOF_BASE_CONTACTVALIDATOR_NEED_PRIMARY_PHONE_NUMBER);

		// Ensures the mock's insertOrganization was not called, due to validation error.
		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).insertOrganization(request);
	}

	/**
	 * Test insert organization with exception.
	 */
	@Test
	public void testInsertOrganizationWithException()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setModelAction(PersistanceActionEnum.INSERT);
		request.setOrganization(organization);

		mockFetchCodeValueByCode();

		// Throws an exception when mock's insertOrganization is called.
		Mockito.when(getMockOrganizationBAC().insertOrganization(request)).thenThrow(new RuntimeException());

		OrganizationResponse response = getOrganizationBAI().insertOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockOrganizationBAC()).insertOrganization(request);
	}

	/**
	 * Test update organization no id.
	 */
	@Test
	public void testUpdateOrganizationNoId()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setId(null);
		organization.setModelAction(PersistanceActionEnum.UPDATE);
		request.setOrganization(organization);

		mockFetchCodeValueByCode();

		OrganizationResponse response = getOrganizationBAI().updateOrganization(request);

		Assert.assertTrue(response.getMessageList().size() == 1);

		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).updateOrganization(request);
	}

	/**
	 * Test update organization.
	 */
	@Test
	public void testUpdateOrganization()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setId(1);
		organization.setModelAction(PersistanceActionEnum.UPDATE);

		request.setOrganization(organization);

		mockFetchCodeValueByCode();

		Mockito.when(getMockOrganizationBAC().updateOrganization(request)).thenReturn(
				new InternalResultsResponse<Organization>());

		OrganizationResponse response = getOrganizationBAI().updateOrganization(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockOrganizationBAC()).updateOrganization(request);
	}

	/**
	 * Test update organization exception.
	 */
	@Test
	public void testUpdateOrganizationException()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setId(1);
		organization.setModelAction(PersistanceActionEnum.UPDATE);

		request.setOrganization(organization);

		mockFetchCodeValueByCode();

		Mockito.when(getMockOrganizationBAC().updateOrganization(request)).thenThrow(new RuntimeException());

		OrganizationResponse response = getOrganizationBAI().updateOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockOrganizationBAC()).updateOrganization(request);
	}

	/**
	 * Test delete organization.
	 */
	@Test
	public void testDeleteOrganization()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		Organization organization = new Organization();
		organization.setId(THREE);
		request.setOrganization(organization);

		Mockito.when(getMockOrganizationBAC().deleteOrganization(request)).thenReturn(
				new InternalResultsResponse<Organization>());

		OrganizationResponse response = getOrganizationBAI().deleteOrganization(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockOrganizationBAC()).deleteOrganization(request);
	}

	/**
	 * Test delete organization exception.
	 */
	@Test
	public void testDeleteOrganizationException()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		Organization organization = new Organization();
		organization.setId(THREE);
		request.setOrganization(organization);

		Mockito.when(getMockOrganizationBAC().deleteOrganization(request)).thenThrow(new RuntimeException());

		OrganizationResponse response = getOrganizationBAI().deleteOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockOrganizationBAC()).deleteOrganization(request);
	}

	/**
	 * Test delete organization failure.
	 */
	@Test
	public void testDeleteOrganizationFailure()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();

		Organization organization = new Organization();
		organization.setId(THREE);
		request.setOrganization(organization);

		// returns the error object when deleteOrganization is called.
		Mockito.when(getMockOrganizationBAC().deleteOrganization(request)).thenReturn(getErrResponse());

		OrganizationResponse response = getOrganizationBAI().deleteOrganization(request);

		CommonTestRoutines.assertMessages(response, LOG, BAC_KEY);

		Mockito.verify(getMockOrganizationBAC()).deleteOrganization(request);
	}

	/**
	 * Test fetch organization by id.
	 */
	@Test
	public void testFetchOrganizationById()
	{
		FetchByIdRequest request = new FetchByIdRequest(1);
		Mockito.when(getMockOrganizationBAC().fetchOrganizationById(request)).thenReturn(createFetchResponse());

		OrganizationResponse response = getOrganizationBAI().fetchOrganizationById(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockOrganizationBAC()).fetchOrganizationById(request);
	}

	/**
	 * Test fetch organization by id exception.
	 */
	@Test
	public void testFetchOrganizationByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(1);

		Mockito.when(getMockOrganizationBAC().fetchOrganizationById(request)).thenThrow(new RuntimeException());

		OrganizationResponse response = getOrganizationBAI().fetchOrganizationById(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockOrganizationBAC()).fetchOrganizationById(request);
	}

	/**
	 * Test fetch organization by id no organization id.
	 */
	@Test
	public void testFetchOrganizationByIdNoOrganizationId()
	{
		FetchByIdRequest request = new FetchByIdRequest();
		OrganizationResponse response = getOrganizationBAI().fetchOrganizationById(request);

		CommonTestRoutines.assertMessages(response, LOG, BAC_KEY);

		// Ensures the mock's fetchOrganizationById was not called, due to validation error.
		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).fetchOrganizationById(request);
	}

	/**
	 * Test fetch organization by request.
	 */
	@Test
	public void testFetchOrganizationByRequest()
	{
		new PagedInquiryRequest();

		Mockito.when(getMockOrganizationBAC().fetchOrganizationByRequest(Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(
						createFetchResponse());

		OrganizationResponse response = getOrganizationBAI().fetchOrganizationByRequest(new PagedInquiryRequest());

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockOrganizationBAC()).fetchOrganizationByRequest(Matchers.any(PagedInquiryRequest.class));
	}

	/**
	 * Test fetch member by request.
	 */
	@Test
	public void testFetchOrganizationByRequestInvalidSortColumn()
	{
		Mockito.when(getMockOrganizationBAC().fetchOrganizationByRequest(Matchers.any(PagedInquiryRequest.class)))
		.thenReturn(
				createFetchResponse());

		PagedInquiryRequest request = new PagedInquiryRequest();
		request.addSortExpressions(new SortExpression("badField", Direction.Ascending));
		OrganizationResponse response = getOrganizationBAI().fetchOrganizationByRequest(request);

		CommonTestRoutines.assertMessages(response, LOG, PagedInquiryRequestValidator.INVALID_SORT_FIELD);
	}

	/**
	 * Test fetch organization by request with error.
	 */
	@Test
	public void testFetchOrganizationByRequestWithError()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPageSize(null);
		request.setStartPage(null);
		InternalResultsResponse<Organization> internalResponse =
				new InternalResultsResponse<Organization>(CommonTestRoutines.createDummyOrganization());

		Mockito.when(getMockOrganizationBAC().fetchOrganizationByRequest(request)).thenReturn(internalResponse);

		OrganizationResponse response = getOrganizationBAI().fetchOrganizationByRequest(request);

		CommonTestRoutines.assertMessages(response, LOG, PagedInquiryRequestValidator.PAGING_PARAMETERS_REQUIRED);

		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).fetchOrganizationByRequest(request);
	}

	/**
	 * Test fetch member by request.
	 */
	@Test
	public void testFetchOrganizationByRequestNull()
	{
		Mockito.when(getMockOrganizationBAC().fetchOrganizationByRequest(Matchers.any(PagedInquiryRequest.class)))
		.thenReturn(
				createFetchResponse());

		PagedInquiryRequest request = null;
		OrganizationResponse response = getOrganizationBAI().fetchOrganizationByRequest(request);

		CommonTestRoutines.assertMessages(response, LOG, PagedInquiryRequestValidator.PAGED_INQUIRY_REQUEST_REQUIRED);
	}

	/**
	 * Test fetch organization by request exception.
	 */
	@Test
	public void testFetchOrganizationByRequestException()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();

		Mockito.when(getMockOrganizationBAC().fetchOrganizationByRequest(request)).thenThrow(new RuntimeException());

		OrganizationResponse response = getOrganizationBAI().fetchOrganizationByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockOrganizationBAC()).fetchOrganizationByRequest(request);
	}

	/**
	 * Return insert.
	 *
	 * @return the internal results response< organization>
	 */
	/**
	 * Test insert document.
	 */
	@Test
	public void testInsertDocument()
	{
		// Test Insert Success
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.ORGANIZATION);
		document.setModelAction(PersistanceActionEnum.INSERT);
		request.setDocument(document);

		InternalResultsResponse<Document> internalResponse = new InternalResultsResponse<Document>();
		internalResponse.addResult(CommonTestRoutines.createDummyDocument(BusinessTypeEnum.ORGANIZATION));

		Mockito.when(getMockOrganizationBAC().insertDocument(request)).thenReturn(internalResponse);

		DocumentResponse response = getOrganizationBAI().insertDocument(request);

		Assert.assertTrue(MESSAGE_LIST_SHOULD_BE_EMPTY, response.getMessageList().isEmpty());

		// Make sure the row inserted comes back
		Assert.assertTrue(NEW_RECORD_SHOULD_COME_BACK, response.getDocumentList().size() == 1);

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockOrganizationBAC()).insertDocument(request);

	}

	/**
	 * Test update document.
	 */
	@Test
	public void testUpdateDocument()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.ORGANIZATION);
		document.setModelAction(PersistanceActionEnum.UPDATE);
		document.setId(THREE);
		request.setDocument(document);

		Mockito.when(getMockOrganizationBAC().updateDocument(request)).thenReturn(
				new InternalResultsResponse<Document>());

		DocumentResponse response = getOrganizationBAI().updateDocument(request);

		Assert.assertTrue("Message List should be empty", response.getMessageList().isEmpty());

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockOrganizationBAC()).updateDocument(request);

	}

	/**
	 * Test delete document.
	 */
	@Test
	public void testDeleteDocument()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.ORGANIZATION);
		document.setId(THREE);
		request.setDocument(document);

		Mockito.when(getMockOrganizationBAC().deleteDocument(request)).thenReturn(
				new InternalResultsResponse<Document>());

		DocumentResponse response = getOrganizationBAI().deleteDocument(request);

		CommonTestRoutines.assertResponse(response);

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockOrganizationBAC()).deleteDocument(request);

	}

	/**
	 * Test insert document with error.
	 */
	@Test
	public void testInsertDocumentWithError()
	{
		// Test Insert Success
		InternalResultsResponse<Document> internalResultsResponse = new InternalResultsResponse<Document>();

		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Document document = new Document();
		document.setModelAction(PersistanceActionEnum.INSERT);
		request.setDocument(document);

		Mockito.when(getMockOrganizationBAC().insertDocument(request)).thenReturn(internalResultsResponse);

		DocumentResponse response = getOrganizationBAI().insertDocument(request);

		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_REQUIRED,
				PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_TYPE_REQUIRED,
				PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_TYPE_REQUIRED,
				PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_FILED_REQUIRED);

		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).insertDocument(request);
	}

	/**
	 * Test update document with error.
	 */
	@Test
	public void testUpdateDocumentWithError()
	{

		InternalResultsResponse<Document> internalResultsResponse = new InternalResultsResponse<Document>();
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Document document = new Document();
		document.setModelAction(PersistanceActionEnum.UPDATE);
		request.setDocument(document);

		Mockito.when(getMockOrganizationBAC().updateDocument(request)).thenReturn(internalResultsResponse);

		DocumentResponse response = getOrganizationBAI().updateDocument(request);

		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_REQUIRED,
				PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_TYPE_REQUIRED,
				PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_TYPE_REQUIRED,
				PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_FILED_REQUIRED,
				PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_ID_REQUIRED);

		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).updateDocument(request);
	}

	/**
	 * Test delete document with error.
	 */
	@Test
	public void testDeleteDocumentWithError()
	{
		InternalResultsResponse<Document> internalResultsResponse = new InternalResultsResponse<Document>();
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Document document = new Document();
		document.setModelAction(PersistanceActionEnum.DELETE);
		request.setDocument(document);

		Mockito.when(getMockOrganizationBAC().deleteDocument(request)).thenReturn(internalResultsResponse);

		DocumentResponse response = getOrganizationBAI().deleteDocument(request);

		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_ID_REQUIRED);

		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).deleteDocument(request);
	}

	/**
	 * Test insert document exception.
	 */
	@Test
	public void testInsertDocumentException()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.ORGANIZATION);
		document.setModelAction(PersistanceActionEnum.INSERT);
		request.setDocument(document);

		Mockito.when(getMockOrganizationBAC().insertDocument(request)).thenThrow(new RuntimeException());

		DocumentResponse response = getOrganizationBAI().insertDocument(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockOrganizationBAC()).insertDocument(request);
	}

	/**
	 * Test update document exception.
	 */
	@Test
	public void testUpdateDocumentException()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.ORGANIZATION);
		document.setModelAction(PersistanceActionEnum.UPDATE);
		document.setId(2);
		document.setModelAction(PersistanceActionEnum.UPDATE);
		request.setDocument(document);

		Mockito.when(getMockOrganizationBAC().updateDocument(request)).thenThrow(new RuntimeException());

		DocumentResponse response = getOrganizationBAI().updateDocument(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockOrganizationBAC()).updateDocument(request);
	}

	/**
	 * Test delete document exception.
	 */
	@Test
	public void testDeleteDocumentException()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.ORGANIZATION);
		document.setId(1);
		document.setModelAction(PersistanceActionEnum.DELETE);
		request.setDocument(document);

		Mockito.when(getMockOrganizationBAC().deleteDocument(request)).thenThrow(new RuntimeException());

		DocumentResponse response = getOrganizationBAI().deleteDocument(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockOrganizationBAC()).deleteDocument(request);
	}

	/**
	 * Test insert risk.
	 */
	@Test
	public void testInsertRisk()
	{
		// Test Insert Success
		RiskMaintenanceRequest request = new RiskMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setModelAction(PersistanceActionEnum.INSERT);
		request.setRisk(risk);

		InternalResultsResponse<Risk> internalResponse = new InternalResultsResponse<Risk>();
		internalResponse.addResult(CommonTestRoutines.createDummyRisk());

		Mockito.when(getMockOrganizationBAC().updateRisk(request)).thenReturn(internalResponse);

		RiskResponse response = getOrganizationBAI().updateRisk(request);

		Assert.assertTrue(MESSAGE_LIST_SHOULD_BE_EMPTY, response.getMessageList().isEmpty());

		// Make sure the row inserted comes back
		Assert.assertTrue(NEW_RECORD_SHOULD_COME_BACK, response.getRiskList().size() == 1);

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockOrganizationBAC()).updateRisk(request);

	}

	/**
	 * Test insert risk note too big.
	 */
	@Test
	public void testInsertRiskNoteTooBig()
	{
		// Test Insert Success
		RiskMaintenanceRequest request = new RiskMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setModelAction(PersistanceActionEnum.UPDATE);

		risk.setRiskLevelNote("a");
		for (int i = 0; i < TWO_HUNDRED_FIFTY_SIX; i++)
		{
			risk.setRiskLevelNote(risk.getRiskLevelNote().concat("b"));
		}

		request.setRisk(risk);

		InternalResultsResponse<Risk> internalResponse = new InternalResultsResponse<Risk>();
		internalResponse.addResult(CommonTestRoutines.createDummyRisk());

		Mockito.when(getMockOrganizationBAC().updateRisk(request)).thenReturn(internalResponse);

		RiskResponse response = getOrganizationBAI().updateRisk(request);

		// Should fail because note is too big
		Assert.assertTrue(response.getMessageList().size() == 1);

		// Make sure the row inserted comes back
		Assert.assertTrue(NEW_RECORD_SHOULD_NOT_COME_BACK, response.getRiskList().size() == 0);

	}

	/**
	 * Test insert risk fields missing.
	 */
	@Test
	public void testInsertRiskFieldsMissing()
	{
		// Test Insert Success
		RiskMaintenanceRequest request = new RiskMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setModelAction(PersistanceActionEnum.UPDATE);

		risk.setRiskLevel(null);
		risk.setVersion(null);

		request.setRisk(risk);

		InternalResultsResponse<Risk> internalResponse = new InternalResultsResponse<Risk>();
		internalResponse.addResult(CommonTestRoutines.createDummyRisk());

		Mockito.when(getMockOrganizationBAC().updateRisk(request)).thenReturn(internalResponse);

		RiskResponse response = getOrganizationBAI().updateRisk(request);

		CommonTestRoutines.assertMessages(response, LOG, SENDSOLV_BASE_VALIDATOR_VERSION_REQUIRED,
				SENDSOLV_BASE_RISKVALIDATOR_RISKLEVEL_REQUIRED);

		// Make sure the row inserted comes back
		Assert.assertTrue(NEW_RECORD_SHOULD_NOT_COME_BACK, response.getRiskList().size() == 0);

	}

	/**
	 * Test apply status.
	 */
	@Test
	public void testApplyStatus()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();

		CommonTestRoutines.createDummyUserContext(request);
		Organization organization = new Organization();
		organization.setId(CommonTestRoutines.getRandomInt());
		organization.setModelAction(PersistanceActionEnum.UPDATE);
		organization.setStatus(StatusEnum.INACTIVE);
		organization.setBusinessType(BusinessTypeEnum.ORGANIZATION);
		request.setOrganization(organization);

		InternalResultsResponse<Organization> internalResponse = new InternalResultsResponse<Organization>();
		internalResponse.setStatus(Status.OperationSuccess);

		mockFetchOrganizationById();

		Mockito.when(getMockOrganizationBAC().applyOrganizationStatus(request)).thenReturn(internalResponse);

		OrganizationResponse response = getOrganizationBAI().applyStatus(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockOrganizationBAC()).applyOrganizationStatus(request);
	}

	/**
	 * Test apply status with error.
	 */
	@Test
	public void testApplyStatusWithError()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();

		CommonTestRoutines.createDummyUserContext(request);
		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setModelAction(PersistanceActionEnum.UPDATE);
		organization.setStatus(null);
		request.setOrganization(organization);

		mockFetchOrganizationById();

		Mockito.when(getMockOrganizationBAC().applyOrganizationStatus(request))
				.thenReturn(createFetchResponse());

		OrganizationResponse response = getOrganizationBAI().applyStatus(request);
		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED);

		Mockito.verify(getMockOrganizationBAC(), Mockito.never()).applyOrganizationStatus(request);
	}

	/**
	 * Return insert.
	 *
	 * @param organization the organization
	 * @return the internal results response< organization>
	 */
	private InternalResultsResponse<Organization> returnInsert(Organization organization)
	{
		InternalResultsResponse<Organization> response = new InternalResultsResponse<Organization>();
		response.addResult(organization);

		return response;
	}

	/**
	 * Creates the fetch response.
	 *
	 * @return the internal results response< organization>
	 */
	private InternalResultsResponse<Organization> createFetchResponse()
	{
		InternalResultsResponse<Organization> response = new InternalResultsResponse<Organization>();
		response.addResult(new Organization());

		return response;
	}

	/**
	 * Creates the invalid name.
	 *
	 * @return the string
	 */
	private String createInvalidName()
	{
		String name = new String();
		for (int i = 0; i < TWO_HUNDRED_FIFTY_SIX; i++)
		{
			name += "A";
		}
		return name;
	}

	/**
	 * Mock fetch code value by code.
	 */
	private void mockFetchCodeValueByCode()
	{
		InternalResultsResponse<CodeValue> internalResponse = new InternalResultsResponse<CodeValue>();
		internalResponse.setStatus(Status.OperationSuccess);
		CodeValue code = new CodeValue();
		code.setCode("999999");
		internalResponse.addResult(code);

		Mockito.when(getMockIndustryClassificationDAC().fetchCodeValueByCode(Matchers.any(CodeValueRequest.class)))
				.thenReturn(internalResponse);
	}

	/**
	 * Mock fetch organization by id.
	 */
	private void mockFetchOrganizationById()
	{
		InternalResultsResponse<Organization> internalResponse = new InternalResultsResponse<Organization>();
		internalResponse.setStatus(Status.OperationSuccess);
		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setStatus(StatusEnum.SETUP);
		internalResponse.addResult(organization);

		Mockito.when(getMockOrganizationDAC().fetchOrganizationById(Matchers.any(FetchByIdRequest.class))).thenReturn(
				internalResponse);
	}
}
