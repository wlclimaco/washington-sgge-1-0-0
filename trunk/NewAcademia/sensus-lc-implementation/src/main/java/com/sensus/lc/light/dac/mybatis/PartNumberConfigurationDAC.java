package com.sensus.lc.light.dac.mybatis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusAppContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.light.dac.IPartNumberConfigurationDAC;
import com.sensus.lc.light.model.PartNumber;
import com.sensus.lc.light.model.PartNumberConfiguration;

/**
 *
 */
public class PartNumberConfigurationDAC implements IPartNumberConfigurationDAC
{
	/** The Constant SENSUS_PART_NUMBER_LIST_BEAN. */
	private static final String SENSUS_PART_NUMBER_LIST_BEAN = "sensusPartNumberList";
	private static final String MODEL_NUMBER_INVALID = "sensus.mlc.lightvalidator.light.model.number.invalid";
	private static final String PART_NUMBER_LIST_BEAN_NOT_FOUND =
			"sensus.mlc.lightvalidator.light.part.number.list.bean.not.found";

	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<PartNumber> fetchAllPartNumbers()
	{
		InternalResultsResponse<PartNumber> response = new InternalResultsResponse<PartNumber>();
		response.addResults((List<PartNumber>)
				SensusAppContext.getApplicationContext().getBean(SENSUS_PART_NUMBER_LIST_BEAN));
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<PartNumberConfiguration> fetchPartNumberConfigurationsByModelNumber(
			String modelNumber)
	{
		InternalResultsResponse<PartNumberConfiguration> response = new InternalResultsResponse<>();
		InternalResultsResponse<PartNumber> partNumberResponse = fetchAllPartNumbers();
		PartNumber sensusPartNumber = null;

		for (PartNumber partNumber : partNumberResponse.getResultsList())
		{
			if (checkMaskModelNumber(partNumber, modelNumber))
			{
				sensusPartNumber = partNumber;
				break;
			}
		}

		if (ValidationUtil.isNull(sensusPartNumber))
		{
			response.setStatus(Status.ValidationError);
			response.addFieldErrorMessage(MODEL_NUMBER_INVALID);
			return response;
		}

		List<PartNumberConfiguration> partNumberConfigurationList =
				(List<PartNumberConfiguration>)SensusAppContext.getApplicationContext().getBean(
						sensusPartNumber.getDimTable());

		if (ValidationUtil.isNull(partNumberConfigurationList))
		{
			response.setStatus(Status.ValidationError);
			response.addFieldErrorMessage(PART_NUMBER_LIST_BEAN_NOT_FOUND);
			return response;
		}

		response.addResults(partNumberConfigurationList);
		return response;
	}

	private boolean checkMaskModelNumber(PartNumber partNumber, String modelNumber)
	{
		Map<Integer, String> productModelMask = partNumber.getProductModelMask();
		if (ValidationUtil.isNull(productModelMask)
				|| CollectionUtils.isEmpty(productModelMask.keySet()))
		{
			return false;
		}

		Set<Integer> keys = productModelMask.keySet();
		int length = modelNumber.length();
		for (Integer key : keys)
		{
			String value = productModelMask.get(key);

			// The position is not absolute
			if ((key > length) || !value.equals(String.valueOf(modelNumber.charAt(key - 1))))
			{
				return false;
			}
		}

		return true;
	}

}
