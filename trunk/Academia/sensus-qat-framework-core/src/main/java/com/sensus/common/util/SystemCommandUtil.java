package com.sensus.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.SystemCommandRequest;
import com.sensus.common.model.response.SystemCommandResponse;
import com.sensus.common.model.response.SystemCommandResponse.SystemCommandStatus;

/**
 * Utility type class used to execute and manage a given system command.
 * This can be used to execute a system command like sort, merge etc.
 * The {@link com.qat.framework.model.request.SystemCommandRequest} object is used to encapsulate the system command and
 * various options used to manage its execution. The command property is the only non-null/required property.
 * The {@link com.qat.framework.model.response.SystemCommandResponse} object is used to encapsulate the results from the
 * execution.<br/>
 * Need to be careful with some commands on the windows platform. Results can be unpredictable, especially if a timeout
 * occurs. Terminating a process on the windows platform from java does not always work.
 */
public final class SystemCommandUtil
{
	private static final Logger LOG = LoggerFactory.getLogger(SystemCommandUtil.class);

	private SystemCommandUtil()
	{
	}

	/**
	 * Run a system command based on an instance of the {@link com.qat.framework.model.request.SystemCommandRequest}
	 * which contains the various options and parameters. The command is first validated to insure everything makes
	 * sense and then the command is executed using a child thread so it can be managed and terminated based on the
	 * setting within the SystemCommandRequest.
	 * 
	 * @param systemCommand An instance of SystemCommand.
	 * @return An instance of {@link com.qat.framework.model.response.SystemCommandResponse}
	 */
	public static SystemCommandResponse runSystemCommand(final SystemCommandRequest systemCommand)
	{
		final SystemCommandResponse resp = new SystemCommandResponse();

		// Validate the command throwing exceptions if something is wrong.
		validate(systemCommand);

		// create task to execute
		final FutureTask<Integer> systemCommandTask = createTask(systemCommand, resp);

		// create executor for the task.
		Executor taskExecutor = createTaskExecutor(systemCommand);

		// Need a stop watch so I can periodically check task/command.
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// Start the task/command.
		taskExecutor.execute(systemCommandTask);

		// Now just wait for the task to terminate or timeout.
		waitForTask(systemCommand, resp, systemCommandTask, stopWatch);

		return resp;
	}

	/**
	 * Wait for task periodically checking to see if its done or has timed-out.
	 */
	private static void waitForTask(final SystemCommandRequest systemCommand, final SystemCommandResponse response,
			final FutureTask<Integer> systemCommandTask, StopWatch stopWatch)
	{
		try
		{
			// Wait for the task to complete, timeout or interrupted.
			while (true)
			{
				// Snooze while the task/command does its thing...
				Thread.sleep(systemCommand.getCheckInterval());

				// Did the task complete?
				if (systemCommandTask.isDone())
				{
					Integer rc = systemCommandTask.get();

					response.setCommandExitValue(rc);

					response.setCommandStatus(SystemCommandStatus.TerminatedNormal);
					break;
				}
				// Did we time-out waiting for the task to complete?
				else if (stopWatch.getTime() > systemCommand.getTimeout())
				{
					cancelTask(systemCommand, response, systemCommandTask);
					break;
				}
			}
		}
		catch (InterruptedException e)
		{
			response.setException(e);
			response.setCommandStatus(SystemCommandStatus.TerminatedInterrupted);
			response.setCommandExitValue(1);
		}
		catch (Exception e)
		{
			response.setException(e);
			response.setCommandStatus(SystemCommandStatus.TerminatedException);
			response.setCommandExitValue(1);
		}
		finally
		{
			stopWatch.stop();
		}
	}

	private static void cancelTask(final SystemCommandRequest systemCommand, final SystemCommandResponse response,
			final FutureTask<Integer> systemCommandTask)
	{
		systemCommand.getLOG().info(
				"Cancelling process becuase of timeout, " + systemCommand.getTimeout() + "ms");
		boolean canceled = systemCommandTask.cancel(systemCommand.isInterruptOnCancel());
		systemCommand.getLOG().info("Process cancelled(" + canceled + ")");
		if (response.getProcess() != null)
		{
			response.getProcess().destroy();
		}
		response.setCommandStatus(SystemCommandStatus.TerminatedTimeout);
		response.setCommandExitValue(1);
	}

	/**
	 * Creates the task executor which will eventually run the actual system command.
	 */
	private static Executor createTaskExecutor(final SystemCommandRequest systemCommand)
	{
		// Required in order to wrap the task/command.
		Executor taskExecutor = new Executor()
		{
			@Override
			public void execute(Runnable runable)
			{
				// Start the main process and output handler threads.
				Thread myThread = new Thread(runable);
				myThread.start();

				systemCommand.getLOG().info(
						"Thread started for command[" + systemCommand.getCommand() + "], Timeout="
								+ systemCommand.getTimeout() + "ms");
				systemCommand.getLOG().info(
						"Thread information: ID=" + myThread.getId() + ", Name=" + myThread.getName() + ", priority="
								+ myThread.getPriority() + ", State=" + myThread.getState());
			}
		};
		return taskExecutor;
	}

	/**
	 * Creates a FutureTask instance which will be run in a separate thread.
	 */
	private static FutureTask<Integer> createTask(final SystemCommandRequest systemCommand,
			final SystemCommandResponse response)
	{
		// The actual command is wrapped in this FutureTask to help with managing the task.
		final FutureTask<Integer> systemCommandTask = new FutureTask<Integer>(new Callable<Integer>()
		{
			@Override
			public Integer call() throws Exception
			{
				// Define the process/command to run.
				final Process process = Runtime.getRuntime().exec(systemCommand.getCommand(),
						systemCommand.getEnvironmentParams(),
						systemCommand.getWorkingDirectory());

				// Thread outputGobblerThread = createOutputGobblerThread(systemCommand, resp, process);
				Thread outputGobblerThread =
						createGobblerThread(systemCommand, false, response.getStdOutBuffer(), process.getInputStream());
				outputGobblerThread.start();

				Thread errorGobblerThread =
						createGobblerThread(systemCommand, true, response.getStdErrorBuffer(), process.getErrorStream());
				// Thread errorGobblerThread = createErrorGobblerThread(systemCommand, resp, process);
				errorGobblerThread.start();

				response.setProcess(process);

				return process.waitFor();
			}

			/**
			 * Creates the gobbler thread which blocks and reads output and then writes the output to a log or
			 * StringBuilder.
			 */
			private Thread createGobblerThread(final SystemCommandRequest systemCommand, final boolean error,
					final StringBuilder stringBuilder, final java.io.InputStream inputStream)
			{
				// Create thread to read output.
				Thread thread = new Thread()
				{
					@Override
					public void run()
					{
						try
						{
							InputStreamReader isr = new InputStreamReader(inputStream);
							BufferedReader br = new BufferedReader(isr);
							String line = null;
							while ((line = br.readLine()) != null)
							{
								if (systemCommand.isCaptureOutput())
								{
									stringBuilder.append(line).append(System.getProperty("line.separator"));
								}
								else
								{
									if (error)
									{
										systemCommand.getLOG().error("Standard Error From System Command:" + line);
									}
									else
									{
										systemCommand.getLOG().info("Standard Output From System Command:" + line);
									}
								}
							}
						}
						catch (IOException ioe)
						{
							ioe.printStackTrace();
						}
					}
				};
				return thread;
			}
		});
		return systemCommandTask;
	}

	/**
	 * Validate the command object.
	 */
	private static void validate(final SystemCommandRequest systemCommand)
	{
		if (systemCommand == null)
		{
			throw new IllegalArgumentException(
					"The internal system command argument cannot be null when calling the SystemCommandUtil.runSystemCommand method.");
		}
		if (systemCommand.getCommand() == null)
		{
			throw new IllegalArgumentException(
					"The embeded 'command' property cannot be null when calling the SystemCommandUtil.runSystemCommand method.");
		}

		// If the log is null set to this classes log instance.
		if (systemCommand.getLOG() == null)
		{
			systemCommand.setLOG(LOG);
		}
	}
}
