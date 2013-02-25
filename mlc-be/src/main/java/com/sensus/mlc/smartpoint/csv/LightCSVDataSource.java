package com.sensus.mlc.smartpoint.csv;

import com.sensus.common.util.CSVDataSource;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;

/**
 * This class is the CSVDataSource for the Light model object.
 *
 * @author rpulley
 */
public class LightCSVDataSource extends CSVDataSource<Light>
{
	// These should be localized.
	private static final String LIGHT_ID = "FlexNet Id";
	private static final String STATUS_LIGHT = "Status light";

	public LightCSVDataSource(InquiryLightRequest request)
	{
		super(request);

		addColumns(LIGHT_ID, STATUS_LIGHT);

		addData(request.getLights());
	}

	@Override
	protected String getDataForColumn(Light light, int column)
	{
		switch (column)
		{
			case 0:

				return String.valueOf(light.getRniId());

			case 1:

				if (ValidationUtil.isNull(light.getCurrentStatusMessage()))
				{
					return "N/A";
				}

				return light.getCurrentStatusMessage().getLightStatusEnum().name();
			default:
				break;
		}

		return null;
	}
}
