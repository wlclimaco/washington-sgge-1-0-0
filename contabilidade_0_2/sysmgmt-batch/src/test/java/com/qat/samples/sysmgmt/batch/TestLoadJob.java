package com.qat.samples.sysmgmt.batch;

import org.junit.Assert;
import org.junit.Test;

/**
 * Batch Job Test.
 */
public class TestLoadJob extends BatchJobTestBase
{
	/**
	 * Un-comment this next @Test attribute in order to run this batch test under JUnit.
	 * Note this test will commit to the database so you'll have to reset the database afterwards.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testJob1() throws Exception
	{
		String[] args = new String[] {
				"-jobConfig=com/qat/samples/sysmgmt/batch/job/qat-sysmgmt-d-load-01-job.xml",
				"-jobName=qatSysMgmtDLoad01Job", "-spring.profiles.active=postgres,job-repository-in-memory"};

		// Run the job
		super.runJob(args, new IHandleBatchJobReturn()
		{
			@Override
			public long handleReturn(ExitException ex)
			{
				Assert.assertTrue("Status from batch job should be zero, instead it is=" + ex.getStatus(),
						ex.getStatus() == 0);
				return 0;
			}
		});
	}

}