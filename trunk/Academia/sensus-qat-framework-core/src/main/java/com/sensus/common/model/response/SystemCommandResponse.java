package com.sensus.common.model.response;

/**
 * This class is used in conjunction with the SystemCommandUtil class when executing various system commands.
 * This class encapsulates the results of the given command.
 * Note the output from the command may be routed to a log or to an internal string buffer based on property settings.
 */
public class SystemCommandResponse
{
	/**
	 * The Enum Status returned from the runSystemCommand method.
	 */
	public enum SystemCommandStatus
	{
		/** Command Terminated normal. */
		TerminatedNormal,
		/** Command Terminated as a result of an timeout. */
		TerminatedTimeout,
		/** Command Terminated as a result of an interrupted. */
		TerminatedInterrupted,
		/** Command Terminated with an exception. */
		TerminatedException;
	}

	/** The command status. */
	private SystemCommandStatus commandStatus;

	/** The command exit value. */
	private Integer commandExitValue;

	/** The exception. */
	private Exception exception;

	/** The std out buffer. */
	private StringBuilder stdOutBuffer = new StringBuilder();

	/** The std error buffer. */
	private StringBuilder stdErrorBuffer = new StringBuilder();

	/** The process. */
	private Process process;

	/**
	 * Gets the command status. This value will tell you how the command terminated, normally, timeout etc...
	 * 
	 * @return the command status
	 */
	public SystemCommandStatus getCommandStatus()
	{
		return commandStatus;
	}

	/**
	 * Sets the command status.
	 * 
	 * @param commandStatus the new command status
	 */
	public void setCommandStatus(SystemCommandStatus commandStatus)
	{
		this.commandStatus = commandStatus;
	}

	/**
	 * Gets the command exit value. Typically when this is zero the command terminated successfully.
	 * When it's not zero the command failed in some way and you should look at the CommandStatus property
	 * and possible the standard output or standard error output if you indicated it should be captured.
	 * 
	 * @return the command exit value
	 */
	public Integer getCommandExitValue()
	{
		return commandExitValue;
	}

	/**
	 * Sets the command exit value.
	 * 
	 * @param commandExitValue the new command exit value
	 */
	public void setCommandExitValue(Integer commandExitValue)
	{
		this.commandExitValue = commandExitValue;
	}

	/**
	 * Gets the exception, if any.
	 * 
	 * @return the exception
	 */
	public Exception getException()
	{
		return exception;
	}

	/**
	 * Sets the exception.
	 * 
	 * @param exception the new exception
	 */
	public void setException(Exception exception)
	{
		this.exception = exception;
	}

	/**
	 * Gets the std out buffer.
	 * Note this will be empty unless you turned-on output capture on the request.
	 * 
	 * @return the std out buffer
	 */
	public StringBuilder getStdOutBuffer()
	{
		return stdOutBuffer;
	}

	/**
	 * Sets the std out buffer.
	 * 
	 * @param stdOutBuffer the new std out buffer
	 */
	public void setStdOutBuffer(StringBuilder stdOutBuffer)
	{
		this.stdOutBuffer = stdOutBuffer;
	}

	/**
	 * Gets the std error buffer.
	 * Note this will be empty unless you turned-on output capture on the request.
	 * 
	 * @return the std error buffer
	 */
	public StringBuilder getStdErrorBuffer()
	{
		return stdErrorBuffer;
	}

	/**
	 * Sets the std error buffer.
	 * 
	 * @param stdErrorBuffer the new std error buffer
	 */
	public void setStdErrorBuffer(StringBuilder stdErrorBuffer)
	{
		this.stdErrorBuffer = stdErrorBuffer;
	}

	/**
	 * Gets the process. Used internally and should not be used by caller/invoker.
	 * 
	 * @return the process
	 */
	public Process getProcess()
	{
		return process;
	}

	/**
	 * Sets the process. Used internally and should not be used by caller/invoker.
	 * 
	 * @param process the new process
	 */
	public void setProcess(Process process)
	{
		this.process = process;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SystemCommandResponse [getCommandStatus()=" + getCommandStatus() + ", getCommandExitValue()="
				+ getCommandExitValue() + ", getException()=" + getException() + ", getStdOutBuffer()="
				+ getStdOutBuffer() + ", getStdErrorBuffer()=" + getStdErrorBuffer() + "]";
	}

}
