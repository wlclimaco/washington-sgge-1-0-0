package com.sensus.common.model.request;

import java.io.File;
import java.util.Arrays;

import org.slf4j.Logger;

/**
 * The Class encapsulates a system command to be executed. There are various options and settings that can be specified
 * to control and manage the execution of the command. Review the properties for more information.
 * Note as a default the checkInterval is set to 1000ms and the timeout is set to 10000ms.
 * In addition the interrupOnCancel is set to true and captureOutput is set to false.
 * Check out the properties for more information.
 */
public class SystemCommandRequest
{
	// [start] private fields

	/** The command to execute including parameters. */
	private String command;

	/** The check interval. */
	private long checkInterval = 1000; // Default to checking every 1 second.

	/** The timeout. */
	private long timeout = 10000; // Default to 10 seconds.

	/** The interrupt on cancel. */
	private boolean interruptOnCancel = true;

	/** The working directory. */
	private File workingDirectory;

	/** The environment parameters. */
	private String[] environmentParams;

	/** The LOG. */
	private Logger log;

	/** The capture output. */
	private boolean captureOutput = false;

	// [end] private fields

	// [start] public constructor

	/**
	 * Instantiates a new system command request.
	 */
	public SystemCommandRequest()
	{
	}

	/**
	 * Instantiates a new system command request instance with the given command.
	 * 
	 * @param command the command
	 */
	public SystemCommandRequest(String command)
	{
		setCommand(command);
	}

	// [end] public constructor

	// [start] public properties

	/**
	 * Gets the actual command to be executed.
	 * 
	 * @return the command
	 */
	public String getCommand()
	{
		return command;
	}

	/**
	 * Sets the actual command to be executed. This should include any parameters etc as if you where executing the
	 * command from a console. You must now your environment. For example if you want to run a Windows command
	 * you'll need a command-prompt. IE. 'cmd /c ping localhost' <br/>
	 * If you are running on UNIX then simply 'ping localhost' will work.
	 * 
	 * @param command the new command
	 */
	public void setCommand(String command)
	{
		this.command = command;
	}

	/**
	 * Gets the check interval.
	 * 
	 * @return the check interval
	 */
	public long getCheckInterval()
	{
		return checkInterval;
	}

	/**
	 * Used to set how often, in milliseconds, the system command/task should be checked to see if it completed
	 * or timed-out.
	 * 
	 * @param checkInterval the new check interval
	 */
	public void setCheckInterval(long checkInterval)
	{
		this.checkInterval = checkInterval;
	}

	/**
	 * Gets the timeout.
	 * 
	 * @return the timeout
	 */
	public long getTimeout()
	{
		return timeout;
	}

	/**
	 * The timeout values is the maximum allowed number of milliseconds the command/task will be allowed to execute.
	 * after this time the command/task will be terminated.
	 * 
	 * @param timeout the new timeout
	 */
	public void setTimeout(long timeout)
	{
		this.timeout = timeout;
	}

	/**
	 * Checks if is interrupt on cancel.
	 * 
	 * @return true, if is interrupt on cancel
	 */
	public boolean isInterruptOnCancel()
	{
		return interruptOnCancel;
	}

	/**
	 * When terminating a command/task because of a timeout this indicator specifies that the command/task should be
	 * interrupted as opposed to allowing the command/task to terminate.
	 * 
	 * @param interruptOnCancel the new interrupt on cancel
	 */
	public void setInterruptOnCancel(boolean interruptOnCancel)
	{
		this.interruptOnCancel = interruptOnCancel;
	}

	/**
	 * Gets the working directory.
	 * 
	 * @return the working directory
	 */
	public File getWorkingDirectory()
	{
		return workingDirectory;
	}

	/**
	 * Sets the working directory from which the command/task will be executed..
	 * 
	 * @param workingDirectory the new working directory
	 */
	public void setWorkingDirectory(File workingDirectory)
	{
		this.workingDirectory = workingDirectory;
	}

	/**
	 * Gets the environment params.
	 * 
	 * @return the environment params
	 */
	public String[] getEnvironmentParams()
	{
		return environmentParams;
	}

	/**
	 * Sets any environment parameters that should be used during the execution of the command/task.
	 * 
	 * @param environmentParams the new environment params
	 */
	public void setEnvironmentParams(String[] environmentParams)
	{
		this.environmentParams = environmentParams;
	}

	/**
	 * Sets the log instance that should be used by the command/task executor.
	 * 
	 * @param lOG the new lOG
	 */
	public void setLOG(Logger newLog)
	{
		log = newLog;
	}

	/**
	 * Gets the lOG.
	 * 
	 * @return the lOG
	 */
	public Logger getLOG()
	{
		return log;
	}

	/**
	 * Checks if output capture is turned on.
	 * 
	 * @return true, if is capture output
	 */
	public boolean isCaptureOutput()
	{
		return captureOutput;
	}

	/**
	 * Sets the capture output property.
	 * If true the output will be written to the response object properties.
	 * If false, the default, the output will be written to a log.
	 * 
	 * @param captureOutput the new capture output
	 */
	public void setCaptureOutput(boolean captureOutput)
	{
		this.captureOutput = captureOutput;
	}

	@Override
	public String toString()
	{
		return "SystemCommandRequest [getCommand()=" + getCommand() + ", getCheckInterval()=" + getCheckInterval()
				+ ", getTimeout()=" + getTimeout() + ", isInterruptOnCancel()=" + isInterruptOnCancel()
				+ ", getWorkingDirectory()=" + getWorkingDirectory() + ", getEnvironmentParams()="
				+ Arrays.toString(getEnvironmentParams()) + ", getLOG()=" + getLOG() + ", isCaptureOutput()="
				+ isCaptureOutput() + "]";
	}

	// [end] public properties

}
