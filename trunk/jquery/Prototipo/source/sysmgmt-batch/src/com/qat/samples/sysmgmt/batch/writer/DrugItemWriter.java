package com.qat.samples.sysmgmt.batch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.qat.framework.batch.item.ModelItemWrapper;
import com.qat.samples.sysmgmt.dac.IDrugDAC;
import com.qat.samples.sysmgmt.model.Drug;

/**
 * This class is used as the writer within a chunk process. It is responsible for writing the drug object to the
 * database using the DrugDAC. <br/>
 * 
 * @param <T> the type used for this writer.
 */
public class DrugItemWriter<T> implements ItemWriter<ModelItemWrapper<Drug>>
{

	private static final Logger LOG = LoggerFactory.getLogger(DrugItemWriter.class);

	private IDrugDAC drugDAC; // injected thru Spring Setter

	/**
	 * Spring Sets the drug DAC instance. This is invoked by Spring.
	 * 
	 * @param drugDAC the drug dac
	 */
	public void setDrugDAC(IDrugDAC drugDAC)
	{
		this.drugDAC = drugDAC;
	}

	/**
	 * Gets the drug dac.
	 * 
	 * @return the drug dac
	 */
	public IDrugDAC getDrugDAC()
	{
		return drugDAC;
	}

	/**
	 * Write the Drug using the injected DAC.
	 * 
	 * @param drugs the drugs
	 * 
	 * @throws Exception the exception
	 * 
	 * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
	 */
	@Override
	public void write(List<? extends ModelItemWrapper<Drug>> wrappers) throws Exception
	{
		for (ModelItemWrapper<Drug> wrapper : wrappers)
		{
			getDrugDAC().insertDrug(wrapper.getModelObject());
		}

		if (LOG.isInfoEnabled())
		{
			LOG.info("DrugItemWriter ending," + wrappers.size() + " drugs inserted.");
		}
	}

}
