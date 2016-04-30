package com.qat.samples.sysmgmt.batch;

import java.security.Permission;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.qat.framework.batch.CustomCommandLineJobRunner;

/**
 * The Class is used to assist with the testing of Spring batch jobs.
 * The biggest challenge with testing a batch job is the fact that the actual Spring batch command line job runner class
 * uses System.Exit() which causes problems with automated testing. As a result this class overcomes this difficulty
 * and provides the actual value from the system.exit(rc) call so it can be used within an assertion.<br/>
 * Unit tests should extend from this class and call the {@link runJob(String[])} method.<br/>
 * Note the runJob method will <b>always</b> fire an exception as a result of the System.exit being trapped within this
 * class.
 * sub-classes should code their tests as follows:
 *
 * <pre>
 * <code>
 *     // Define the arguments to be passed into the job runner
 *     String[] args = new String[] {
 *         -jobConfig=com/qat/samples/sysmgmt/base/batch/job/sysmgmt-d-load-01-job.xml",
 *        "-jobName=qatcSysMgmtDLoad01Job"};
 * 
 *      // Wrap the call to runJob so we can get the return code status
 *      try
 *      {
 *          // Run the job
 *          super.runJob(args);
 *      }
 *      catch (ExitException e)
 *      {
 *          Assert.isTrue(e.getStatus() == 0,"Status from batch job should be zero, instead it is=" + e.getStatus()) ;
 *      }
 * </code>
 * </pre>
 */
public abstract class BatchJobTestBase
{
	public interface IHandleBatchJobReturn
	{
		long handleReturn(ExitException ex);
	}

	/**
	 * Setup the security manager to catch the exit.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUp() throws Exception
	{
		System.setSecurityManager(new NoExitSecurityManager());
	}

	/**
	 * Return the security manager to null.
	 *
	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDown() throws Exception
	{
		System.setSecurityManager(null); // or save and restore original
	}

	/**
	 * Run the job based on the arguments.
	 *
	 * @param args the args
	 * @return The actual return code from the job, typically a zero indicating everything worked or a non-zero
	 *         indicating some type of error.
	 * @throws Exception
	 */
	public long runJob(String[] args, IHandleBatchJobReturn handler) throws Exception
	{
		try
		{
			CustomCommandLineJobRunner.main(args);
		}
		catch (ExitException ex)
		{
			handler.handleReturn(ex);
		}

		return 0;
	}

	/**
	 * Used to catch the exit method.
	 */
	private static class NoExitSecurityManager extends SecurityManager
	{

		/*
		 * (non-Javadoc)
		 * @see java.lang.SecurityManager#checkPermission(java.security.Permission)
		 */
		@Override
		public void checkPermission(Permission perm)
		{
			// allow anything.
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.SecurityManager#checkPermission(java.security.Permission, java.lang.Object)
		 */
		@Override
		public void checkPermission(Permission perm, Object context)
		{
			// allow anything.
		}

		/*
		 * This is the one that matters...
		 * (non-Javadoc)
		 * @see java.lang.SecurityManager#checkExit(int)
		 */
		@Override
		public void checkExit(int status)
		{
			super.checkExit(status);
			throw new ExitException(status);
		}
	}

	/**
	 * Used to encapsulate the actual return from the job.
	 */
	@SuppressWarnings("serial")
	protected static class ExitException extends SecurityException
	{
		/** The status. */
		private int status;

		/**
		 * Instantiates a new exit exception.
		 *
		 * @param status the status
		 */
		public ExitException(int newStatus)
		{
			super("The TestBatchJob.runJob method will always throw this exception. "
					+ "Read the Javadoc for more information on how to handle this.");
			setStatus(newStatus);
		}

		/**
		 * Gets the status returned from the job.
		 *
		 * @return the status
		 */
		public int getStatus()
		{
			return status;
		}

		/**
		 * Sets the status.
		 *
		 * @param status the new status
		 */
		private void setStatus(int status)
		{
			this.status = status;
		}
	}

}
