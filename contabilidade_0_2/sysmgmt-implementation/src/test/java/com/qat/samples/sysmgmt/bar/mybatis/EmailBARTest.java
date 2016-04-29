package com.qat.samples.sysmgmt.bar.mybatis;


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

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Email.IEmailBAR;
import com.qat.samples.sysmgmt.util.model.Email;
import com.qat.samples.sysmgmt.util.model.EmailTypeEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/emailbartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class EmailBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(EmailBARTest.class);
private IEmailBAR emailBAR; // injected by Spring through @Resource

@Resource
public void setEmailBAR(IEmailBAR emailBAR)
{
	this.emailBAR = emailBAR;
}

public IEmailBAR getEmailBAR()
{
	return emailBAR;
}


//===================================### EMAIL ####======================================


@Test
	public void testDeleteEmail()
	{
		Email email = new Email(4, "wlclimaco@gmail.com", EmailTypeEnum.COMPRAS, PersistenceActionEnum.NONE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Email emailResponse = getEmailBAR().fetchEmailById(request);
		Assert.assertEquals(emailResponse, null);
		getEmailBAR().insertEmail(email);
		emailResponse = getEmailBAR().fetchEmailById(request);
		Assert.assertEquals(email.getId(), emailResponse.getId());
		getEmailBAR().deleteEmailById(email);
		emailResponse = getEmailBAR().fetchEmailById(request);
		Assert.assertEquals(emailResponse, null);
	}

	@Test
	public void testFetchAllEmails()
	{
	Email email = new Email();
		List<Email> response = getEmailBAR().fetchAllEmails(email).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllEmails()
	{
		getEmailBAR().deleteAllEmails();
	Email email = new Email();
		List<Email> response = getEmailBAR().fetchAllEmails(new Email(1, "wlclimaco@gmail.com", EmailTypeEnum.COMPRAS, PersistenceActionEnum.NONE)).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateEmail()
	{
		Email email = new Email(1, "wlclimaco@gmail.com.br", EmailTypeEnum.COMPRAS, PersistenceActionEnum.NONE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Email emailResponse = getEmailBAR().fetchEmailById(request);
		Assert.assertEquals(emailResponse.getEmail(), "email_4");
		getEmailBAR().updateEmail(email);
		emailResponse = getEmailBAR().fetchEmailById(request);
		Assert.assertEquals(emailResponse.getEmail(), "wlclimaco@gmail.com.br");
	}

	@Test
	public void testFetchEmailsByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Email> response = getEmailBAR().fetchEmailsByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getEmailBAR().fetchEmailsByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Email> response2 = getEmailBAR().fetchEmailsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getEmailBAR().deleteAllEmails();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Email> response3 = getEmailBAR().fetchEmailsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertEmail.sql", false);
	}

}
