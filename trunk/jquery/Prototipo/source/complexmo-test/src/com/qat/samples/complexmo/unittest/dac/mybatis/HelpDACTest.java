package com.qat.samples.complexmo.unittest.dac.mybatis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.samples.complexmo.dac.IHelpDAC;
import com.qat.samples.complexmo.model.Help;
import com.qat.samples.complexmo.model.request.HelpInquiryRequest;

/**
 * Test class for demonstrating paging techniques with iBatis.<br/>
 * There are two techniques for supporting paging using iBatis.<br/>
 * The first one makes use of the iBatis API and the second technique uses SQL.
 * The SQL technique is more efficient and should be used.
 */
@ContextConfiguration(locations = {
		"classpath:com/qat/samples/complexmo/unittest/dac/mybatis/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/complexmo/unittest/dac/mybatis/conf/complexmo-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class HelpDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(HelpDACTest.class);
	private IHelpDAC helpDAC;

	public IHelpDAC getHelpDAC()
	{
		return helpDAC;
	}

	@Resource
	public void setHelpDAC(IHelpDAC helpDAC)
	{
		this.helpDAC = helpDAC;
	}

	/**
	 * This first test uses the myBatis API.
	 */
	@Test
	public void testHelpPagedmyBatis()
	{
		LOG.info("Start testHelpPagedmyBatis");
		long start = System.currentTimeMillis();
		List<Help> list = getHelpDAC().fetchAllHelp(0, 800);
		long end = System.currentTimeMillis();
		LOG.info("List size=" + list.size());
		LOG.info("Execution time using myBatis API for paging= " + (end - start) + "ms");
	}

	/**
	 * The second test uses SQL in order to support paging.
	 */
	@Test
	public void testHelpPaged()
	{
		LOG.info("Start testHelpPaged");

		List<Help> list = new ArrayList<Help>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 6; i++)
		{
			HelpInquiryRequest req = new HelpInquiryRequest();
			req.setPageSize(800);
			req.setStartPage(i);
			list.addAll(getHelpDAC().fetchHelpByPage(req));
			LOG.info("List size=" + list.size());
		}
		long end = System.currentTimeMillis();

		LOG.info("List size final=" + list.size());
		LOG.info("Execution time using SQL technique for paging= " + (end - start) + "ms");
	}

	@Test
	public void testfetchAllHelp()

	{
		LOG.info("Start FetchAllHelp");
		long start = System.currentTimeMillis();
		List<Help> list = getHelpDAC().fetchAllHelp();
		long end = System.currentTimeMillis();
		LOG.info("List size=" + list.size());
		LOG.info("Execution time using no paging = " + (end - start) + "ms");
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/complexmo/unittest/dac/mybatis/conf/insertHelp.sql", true);
	}

}
