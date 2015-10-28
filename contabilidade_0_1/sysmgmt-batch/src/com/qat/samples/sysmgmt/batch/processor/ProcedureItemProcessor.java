package com.qat.samples.sysmgmt.batch.processor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.batch.item.ModelItemWrapper;
import com.qat.framework.batch.processor.ModelItemWrapperValidatingProcessor;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.samples.sysmgmt.model.Procedure;

/**
 * The Class ProcedureItemProcessor.
 */
public class ProcedureItemProcessor extends ModelItemWrapperValidatingProcessor<ModelItemWrapper<Procedure>>
{
	private static final Logger LOG = LoggerFactory.getLogger(ProcedureItemProcessor.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.framework.batch.processor.QATModelValidatingProcessor#beforeProcessing(com.qat.framework.model.QATModel)
	 * In this context the model action is always INSERT.
	 */
	@Override
	protected void beforeProcessing(ModelItemWrapper<Procedure> wrapper)
	{
		wrapper.getModelObject().setModelAction(PersistanceActionEnum.INSERT);
	}

	@Override
	public ModelItemWrapper<Procedure> process(ModelItemWrapper<Procedure> wrapper) throws Exception
	{
		super.process(wrapper);

		// Logic for skipping the record using QAT Global Skipwriter not the traditional Spring way
		if (wrapper.getProcessingIndicator() != ModelItemWrapper.ProcessingIndicator.VALIDATION_ERROR)
		{
			if (wrapper.getModelObject().getCode().equalsIgnoreCase(System.getProperty("system.skiprec")))
			{
				wrapper.setProcessingIndicator(ModelItemWrapper.ProcessingIndicator.SKIP);
			}
		}

		return wrapper;
	}

	@Override
	protected ValidationContext createValidationContext(ModelItemWrapper<Procedure> wrapper)
	{
		ValidationContext context =
				new ValidationContext(Procedure.class.getSimpleName(), wrapper.getModelObject(),
						ValidationContextIndicator.INSERT);

		return context;
	}

	/**
	 * logError is called when domain or business validation fails.
	 * 
	 * @param objectInError - the QATModel object in error
	 * @param msgList - the List containing messages explaining the reason for the error.
	 */
	@Override
	protected void logValidationError(ModelItemWrapper<Procedure> objectInError, List<MessageInfo> msgList)
	{
		if (LOG.isErrorEnabled())
		{
			LOG.error("ProcedureItemProcessor Validation error: " + objectInError + ", Messages: " + msgList);
		}
	}

	/**
	 * If this object is skipped, log a message so we know.
	 */
	@Override
	protected void logSkip(ModelItemWrapper<Procedure> wrapper)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info("ProcedureItemProcessor Skipping: " + wrapper);
		}
	}

}
