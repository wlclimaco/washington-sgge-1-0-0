package com.qat.samples.sysmgmt.batch.reader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import com.qat.framework.batch.item.ModelItemWrapper;
import com.qat.framework.batch.item.ModelItemWrapper.ProcessingIndicator;
import com.qat.framework.batch.reader.ModelItemWrapperFieldSetMapper;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.samples.sysmgmt.model.Drug;
import com.qat.samples.sysmgmt.model.DrugPrice;

/**
 * The Class DrugFieldSetMapper.
 */
public class DrugFieldSetMapper extends ModelItemWrapperFieldSetMapper<Drug> implements
		FieldSetMapper<ModelItemWrapper<Drug>>
{
	private static final Logger LOG = LoggerFactory.getLogger(DrugFieldSetMapper.class);
	private static final String FIELD_SET_ERROR = "sysmgmt.base.drugbatch.defaultexception";

	/**
	 * (non-Javadoc).
	 * 
	 * @param fieldSet the fs
	 * 
	 * @return the drug
	 * 
	 * @see org.springframework.batch.item.file.mapping.FieldSetMapper#mapFieldSet
	 *      (org.springframework.batch.item.file.transform.FieldSet)
	 */
	@Override
	public ModelItemWrapper<Drug> mapFieldSet(FieldSet fieldSet)
	{
		Drug drug = new Drug();
		ModelItemWrapper<Drug> wrapper = new ModelItemWrapper<Drug>(drug);
		try
		{
			if (fieldSet == null)
			{
				return null;
			}

			drug.setModelAction(PersistenceActionEnum.INSERT);
			drug.setCode(fieldSet.readString("ndcCode"));
			drug.setDescription(fieldSet.readString("ndcDesc"));
			List<DrugPrice> drugPriceList = new ArrayList<DrugPrice>();
			DrugPrice drugPrice1 = createDrugPrice(fieldSet, "1");
			drugPriceList.add(drugPrice1);
			DrugPrice drugPrice2 = createDrugPrice(fieldSet, "2");
			drugPriceList.add(drugPrice2);
			drug.setDrugPrices(drugPriceList);
		}
		catch (Exception e)
		{
			// If anything goes wrong, set that this as an error so it doesn't
			// crash the batch process.
			wrapper.setProcessingIndicator(ProcessingIndicator.VALIDATION_ERROR);
			wrapper.getModelObject().setModelAction(PersistenceActionEnum.ERROR);
			wrapper.getErrorMessages().add(MessageInfo.createFieldValidationError(FIELD_SET_ERROR, e.getMessage()));
		}

		return wrapper;
	}

	private DrugPrice createDrugPrice(FieldSet fieldSet, String index)
	{
		DrugPrice drugPrice = new DrugPrice();
		drugPrice.setModelAction(PersistenceActionEnum.INSERT);
		drugPrice.setPriceSexIndicatorValue(fieldSet.readString("ndcSInd" + index));
		drugPrice.setDrugPrice(BigDecimal.valueOf(Double.parseDouble(fieldSet.readString("ndcDPrice" + index))));
		try
		{
			drugPrice.setEffectiveDateUTC(fieldSet.readDate("ndcEffDate" + index, "MM/dd/yyyy").getTime());
		}
		catch (Exception ex)
		{
			LOG.error("Effective Date" + index + " Invalid");
		}
		return drugPrice;
	}

}
