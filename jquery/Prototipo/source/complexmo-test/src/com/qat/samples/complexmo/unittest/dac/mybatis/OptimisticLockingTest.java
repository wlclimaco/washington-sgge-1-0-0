package com.qat.samples.complexmo.unittest.dac.mybatis;

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
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.complexmo.dac.IPersonDAC;
import com.qat.samples.complexmo.model.Person;

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
public class OptimisticLockingTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(OptimisticLockingTest.class);
	private IPersonDAC personDAC;

	public IPersonDAC getPersonDAC()
	{
		return personDAC;
	}

	@Resource
	public void setpersonDAC(IPersonDAC personDAC)
	{
		this.personDAC = personDAC;
	}

	/**
	 * This first test simple retrieve the Person complex model object.<br/>
	 * As you run this test log messages will be printed explaining what is happening.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testOptimisticLockUpdate1() throws InterruptedException
	{
		LOG.info("Starting testOptimisticLock1");
		Person person_req = new Person();
		person_req.setId(199);
		Person p = getPersonDAC().fetchPersonById(person_req);
		Assert.assertNotNull("Person object should not be null here", p);
		p.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		// Set version to one which won't match the table so we should fall into the optimistic locking logic.
		p.setVersion(1);

		InternalResultsResponse<Person> response = getPersonDAC().updatePerson(p);
		LOG.info("Status=" + response.getStatus());
		Assert.assertTrue("Should have set an optimisitc locking error/status from the update.", response.getStatus()
				.equals(InternalResponse.Status.OptimisticLockingError));

		Assert.assertTrue("Results from Update should be null.", !response.hasResults());
	}

	@Test
	public void testOptimisticLockUpdate2() throws InterruptedException
	{
		LOG.info("\nStarting testOptimisticLock2");
		Person person_req = new Person();
		person_req.setId(199);
		Person p = getPersonDAC().fetchPersonById(person_req);
		Assert.assertNotNull("Person object should not be null here", p);
		p.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		// Set the ID to 9 so the where clause will fail based on the key not matching.
		p.setId(9);

		InternalResultsResponse<Person> response = getPersonDAC().updatePerson(p);
		LOG.info("Status=" + response.getStatus());
		Assert.assertTrue("Should have set an Verstion Not Found Error/status from the update.", response.getStatus()
				.equals(InternalResponse.Status.VersionNotFoundError));

		Assert.assertTrue("Results from Update should be null.", !response.hasResults());
	}

	@Test
	public void testOptimisticLockDelete1() throws InterruptedException
	{
		LOG.info("\nStarting testOptimisticLock3");
		Person person_req = new Person();
		person_req.setId(199);
		Person p = getPersonDAC().fetchPersonById(person_req);
		Assert.assertNotNull("Person object should not be null here", p);

		p.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		// Set version to one which won't match the table so we should fall into the optimistic locking logic.
		p.setVersion(1);

		InternalResponse deleteResp = getPersonDAC().deletePerson(p);
		LOG.info("Status=" + deleteResp.getStatus());
		Assert.assertTrue("Should have set an optimisitc locking error/status from the update.", deleteResp.getStatus()
				.equals(InternalResponse.Status.OptimisticLockingError));

	}

	@Test
	public void testOptimisticLockDelete2() throws InterruptedException
	{
		LOG.info("\nStarting testOptimisticLock4");

		Person person_req = new Person();
		person_req.setId(199);
		Person p = getPersonDAC().fetchPersonById(person_req);
		Assert.assertNotNull("Person object should not be null here", p);

		p.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		// Set the key to 9 so the where clause will fail based on the key not matching.
		p.setId(9);

		InternalResponse deleteResp = getPersonDAC().deletePerson(p);
		LOG.info("Status=" + deleteResp.getStatus());
		Assert.assertTrue("Should have set an Version Not Found error/status from the delete.", deleteResp.getStatus()
				.equals(InternalResponse.Status.VersionNotFoundError));
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/complexmo/unittest/dac/mybatis/conf/insertComplexMO.sql", false);
	}
}
