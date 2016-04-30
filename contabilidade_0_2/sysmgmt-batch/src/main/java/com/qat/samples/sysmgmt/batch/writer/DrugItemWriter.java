package com.qat.samples.sysmgmt.batch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.qat.framework.batch.item.ModelItemWrapper;
import com.qat.samples.sysmgmt.bar.IDrugBAR;
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

	private IDrugBAR drugBAR; // injected thru Spring Setter

	/**
	 * Spring Sets the drug BAR instance. This is invoked by Spring.
	 *
	 * @param drugBAR the drug BAR
	 */
	public void setDrugBAR(IDrugBAR drugBAR)
	{
		this.drugBAR = drugBAR;
	}

	/**
	 * Gets the drug BAR.
	 *
	 * @return the drug BAR
	 */
	public IDrugBAR getDrugBAR()
	{
		return drugBAR;
	}

	/**
	 * Write the Drug using the injected BAR.
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
			getDrugBAR().insertDrug(wrapper.getModelObject());
		}

		if (LOG.isInfoEnabled())
		{
			LOG.info("DrugItemWriter ending," + wrappers.size() + " drugs inserted.");
		}
	}

}
