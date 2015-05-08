package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.prosperitasglobal.sendsolv.model.criteria.SarCriteria;
import com.prosperitasglobal.sendsolv.model.request.SarInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.SarMaintenanceRequest;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class SuspiciousActivityDACTest.
 */
public class SuspiciousActivityDACTest extends AbstractTestBaseDAC
{

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		executeSqlScript("com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/deleteSuspiciousActivity.sql",
				false);
	}

	/**
	 * Test insert suspicious activity for business.
	 */
	@Test
	public void testInsertSuspiciousActivityForBusiness()
	{
		SuspiciousActivity suspiciousActivity = insertSuspiciusActivity(BusinessTypeEnum.ORGANIZATION);

		FetchByIdRequest request = new FetchByIdRequest(suspiciousActivity.getBusinessList().get(0).getId());

		InternalResultsResponse<Organization> orgResponse =
				getOrganizationDAC().fetchOrganizationById(request);
		CommonTestRoutines.assertResultResponse(orgResponse);

		Assert.assertNotNull(orgResponse.getFirstResult().getSuspiciousActivityIdList());
	}

	/**
	 * Test insert suspicious activity for person.
	 */
	@Test
	public void testInsertSuspiciousActivityForPerson()
	{
		SuspiciousActivity suspiciousActivity = insertSuspiciusActivity(BusinessTypeEnum.MEMBER);

		FetchByIdRequest request = new FetchByIdRequest(suspiciousActivity.getPersonList().get(0).getId());

		InternalResultsResponse<Member> memberResponse =
				getPersonDAC().fetchMemberById(request);
		CommonTestRoutines.assertResultResponse(memberResponse);

		Assert.assertNotNull(memberResponse.getFirstResult().getSuspiciousActivityIdList());
	}

	/**
	 * Test delete suspicious activity.
	 */
	@Test
	public void testDeleteSuspiciousActivity()
	{
		SarMaintenanceRequest request = new SarMaintenanceRequest();

		SuspiciousActivity suspiciousActivity = insertSuspiciusActivity(BusinessTypeEnum.MEMBER);

		request.setSuspiciousActivity(suspiciousActivity);

		InternalResponse deleteResponse = getSuspiciousActivityDAC().deleteSuspiciousActivity(request);
		CommonTestRoutines.assertResponse(deleteResponse);

		FetchByIdRequest fetchByIdRequest = new FetchByIdRequest(suspiciousActivity.getPersonList().get(0).getId());

		InternalResultsResponse<Member> memberResponse =
				getPersonDAC().fetchMemberById(fetchByIdRequest);
		CommonTestRoutines.assertResultResponse(memberResponse);

		Assert.assertTrue("The list of suspicious activity shoud bring none record", memberResponse.getFirstResult()
				.getSuspiciousActivityIdList().size() == 0);
	}

	/**
	 * Test fetch suspicious activity by request.
	 */
	@Test
	public void testFetchSuspiciousActivityByRequest()
	{
		// Insert a suspicious to a member
		SuspiciousActivity personSuspiciousActivity = insertSuspiciusActivity(BusinessTypeEnum.MEMBER);

		// Insert a suspicious to a organization
		SuspiciousActivity businessSuspiciousActivity = insertSuspiciusActivity(BusinessTypeEnum.ORGANIZATION);

		SarInquiryRequest request = new SarInquiryRequest();

		request.addSortExpressions(new SortExpression("type", Direction.Ascending));

		// Bring all records
		InternalResultsResponse<SuspiciousActivity> sarResponse =
				getSuspiciousActivityDAC().fetchSuspiciousActivityByRequest(request);
		CommonTestRoutines.assertResultResponse(sarResponse);

		Assert.assertTrue("The list of suspicious activity shoud bring two record",
				sarResponse.getResultsList().size() == 2);
		Assert.assertEquals("The first record suspicious shoud be an organization type",
				businessSuspiciousActivity.getType(), sarResponse.getResultsList().get(0)
				.getType());
		Assert.assertEquals("The second record suspicious shoud be an member type",
				personSuspiciousActivity.getType(), sarResponse.getResultsList().get(1)
				.getType());

	}

	/**
	 * Test fetch suspicious activity by request with criteria.
	 */
	@Test
	public void testFetchSuspiciousActivityByRequestWithCriteria()
	{
		// Insert a suspicious to a member
		SuspiciousActivity personSuspiciousActivity = insertSuspiciusActivity(BusinessTypeEnum.MEMBER);

		// Insert a suspicious to a organization
		insertSuspiciusActivity(BusinessTypeEnum.ORGANIZATION);

		SarInquiryRequest sarRequest = new SarInquiryRequest();
		SarCriteria criteria = new SarCriteria();
		criteria.setId(personSuspiciousActivity.getId());
		criteria.setBusinessKey(personSuspiciousActivity.getBusinessKey());
		criteria.setBeginningRangeDateTime(personSuspiciousActivity.getActivityStartDateTimeUTC());
		criteria.setEndingRangeDateTime(personSuspiciousActivity.getActivityStopDateTimeUTC());

		sarRequest.setCriteria(criteria);

		InternalResultsResponse<SuspiciousActivity> sarResponse =
				getSuspiciousActivityDAC().fetchSuspiciousActivityByRequest(sarRequest);
		CommonTestRoutines.assertResultResponse(sarResponse);

		Assert.assertTrue("The list of suspicious activity shoud bring one record",
				sarResponse.getResultsList().size() == 1);
		Assert.assertEquals("Shoud bring just a suspicious for member.",
				personSuspiciousActivity.getId(), sarResponse.getFirstResult().getId());
	}

	/**
	 * Test fetch suspicious activity by id request.
	 */
	@Test
	public void testFetchSuspiciousActivityByIdRequest()
	{
		// Insert a suspicious to a member
		SuspiciousActivity personSuspiciousActivity = insertSuspiciusActivity(BusinessTypeEnum.MEMBER);

		// Insert a suspicious to a organization
		SuspiciousActivity businessSuspiciousActivity = insertSuspiciusActivity(BusinessTypeEnum.ORGANIZATION);

		FetchByIdRequest fetchRequest = new FetchByIdRequest();
		fetchRequest.setId(businessSuspiciousActivity.getId());

		InternalResultsResponse<SuspiciousActivity> sarResponse =
				getSuspiciousActivityDAC().fetchSuspiciousActivityByIdRequest(fetchRequest);
		CommonTestRoutines.assertResultResponse(sarResponse);

		Assert.assertEquals("Shoud bring just a suspicious for organization.",
				businessSuspiciousActivity.getId(), sarResponse.getFirstResult().getId());

		fetchRequest = new FetchByIdRequest();
		fetchRequest.setStringId(personSuspiciousActivity.getBusinessKey());

		sarResponse = getSuspiciousActivityDAC().fetchSuspiciousActivityByIdRequest(fetchRequest);
		CommonTestRoutines.assertResultResponse(sarResponse);

		Assert.assertEquals("The business key should be the same of person.",
				personSuspiciousActivity.getBusinessKey().trim(), sarResponse.getFirstResult().getBusinessKey().trim());

	}

	/**
	 * Test fetch business suspicious activity by id request.
	 */
	@Test
	public void testFetchBusinessSuspiciousActivityByIdRequest()
	{
		// Insert a suspicious to a organization
		SuspiciousActivity businessSuspiciousActivity = insertSuspiciusActivity(BusinessTypeEnum.ORGANIZATION);

		FetchByIdRequest fetchRequest = new FetchByIdRequest();
		fetchRequest.setId(businessSuspiciousActivity.getId());

		InternalResultsResponse<SuspiciousActivity> sarResponse =
				getSuspiciousActivityDAC().fetchBusinessSuspiciousActivityByIdRequest(fetchRequest);
		CommonTestRoutines.assertResultResponse(sarResponse);

		Assert.assertEquals("Shoud bring just a suspicious for organization",
				businessSuspiciousActivity.getId(), sarResponse.getFirstResult().getId());

	}

}
