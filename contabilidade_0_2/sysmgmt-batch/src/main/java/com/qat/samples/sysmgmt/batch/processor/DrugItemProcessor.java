package com.qat.samples.sysmgmt.batch.processor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.batch.item.ModelItemWrapper;
import com.qat.framework.batch.item.ModelItemWrapper.ProcessingIndicator;
import com.qat.framework.batch.processor.ModelItemWrapperValidatingProcessor;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.samples.sysmgmt.model.Drug;

/**
 * The Class DrugItemProcessor.
 */
public class DrugItemProcessor extends ModelItemWrapperValidatingProcessor<ModelItemWrapper<Drug>>
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DrugItemProcessor.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.framework.batch.processor.QATModelValidatingProcessor#beforeProcessing(com.qat.framework.model.BaseModel)
	 * In this context the model action is always INSERT.
	 */
	@Override
	protected void beforeProcessing(ModelItemWrapper<Drug> wrapper)
	{
		// Checking for validation error here because the reader does a pre-validation check
		// so that the batch program doesn't crash for any invalid input that causes NPE
		if (wrapper.getProcessingIndicator() != ProcessingIndicator.VALIDATION_ERROR)
		{
			wrapper.getModelObject().setModelAction(PersistenceActionEnum.INSERT);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.framework.batch.processor.QATModelValidatingProcessor#createValidationContext(com.qat.framework.model
	 * .QATModel)
	 * In the context of how this item processor is used the validation context is always INSERT.
	 */
	@Override
	protected ValidationContext createValidationContext(ModelItemWrapper<Drug> wrapper)
	{
		return new ValidationContext(wrapper.getModelObject().getClass().getSimpleName(), wrapper.getModelObject(),
				ValidationContextIndicator.INSERT);
	}

	/**
	 * logError is called when domain or business validation fails.
	 * 
	 * @param objectInError - the BaseModel object in error
	 * @param msgList - the List containing messages explaining the reason for the error.
	 */
	@Override
	protected void logValidationError(ModelItemWrapper<Drug> objectInError, List<MessageInfo> msgList)
	{
		if (LOG.isErrorEnabled())
		{
			LOG.error("DrugItemProcessor Validation error: " + objectInError + ", Messages: " + msgList);
		}
	}

	/**
	 * If this object is skipped, log a message so we know.
	 * 
	 * @param objectToSkip the object to skip
	 */
	@Override
	protected void logSkip(ModelItemWrapper<Drug> wrapper)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info("DrugItemProcessor Skipping: " + wrapper);
		}
	}

}
