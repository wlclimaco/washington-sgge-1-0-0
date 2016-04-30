package com.qat.samples.sysmgmt.batch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.qat.framework.batch.item.ModelItemWrapper;
import com.qat.samples.sysmgmt.bar.IProcedureBAR;
import com.qat.samples.sysmgmt.model.Procedure;

/**
 * This class is used as the writer within a chunk process. It is responsible for writing the procedure object to the
 * database using the Procedure DAC. <br/>
 *
 * @param <T> the type used for this writer.
 */
public class ProcedureItemWriter<T> implements ItemWriter<ModelItemWrapper<Procedure>>
{

	private static final Logger LOG = LoggerFactory.getLogger(ProcedureItemWriter.class);

	private IProcedureBAR procedureBAR; // injected thru Spring Setter

	/**
	 * Gets the procedure BAR.
	 *
	 * @return the procedure BAR
	 */
	public IProcedureBAR getProcedureBAR()
	{
		return procedureBAR;
	}

	/**
	 * Spring Sets the procedure BAR instance. This is invoked by Spring.
	 *
	 * @param procedureBAR the new procedureBAR
	 */
	public void setProcedureBAR(IProcedureBAR procedureBAR)
	{
		this.procedureBAR = procedureBAR;
	}

	/**
	 * Write the Procedure using the injected BAR.
	 *
	 * @param procedures the procedures
	 *
	 * @throws Exception the exception
	 *
	 * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
	 */
	@Override
	public void write(List<? extends ModelItemWrapper<Procedure>> wrappers) throws Exception
	{
		for (ModelItemWrapper<Procedure> wrapper : wrappers)
		{
			insertProcedure(wrapper.getModelObject());
		}

		if (LOG.isInfoEnabled())
		{
			LOG.info("ProcedureItemWriter ending," + wrappers.size() + " procedures inserted.");
		}
	}

	/**
	 * Insert procedure.
	 *
	 * @param procedure the Procedure
	 */
	private void insertProcedure(Procedure procedure)
	{
		try
		{
			getProcedureBAR().insertProcedure(procedure);
		}
		catch (Exception exception)
		{
			// Log the error if the insert fails.
			if (LOG.isWarnEnabled())
			{
				formatLogMessage(procedure, ":: Insert Failed.");
			}
			throw new RuntimeException(exception);
		}
	}

	/**
	 * Format log message.
	 *
	 * @param Procedure the procedure
	 * @param message the error message
	 */
	private void formatLogMessage(Procedure procedure, String message)
	{
		LOG.warn("ProcedureItemWriter: " + procedure + message);
	}

}
