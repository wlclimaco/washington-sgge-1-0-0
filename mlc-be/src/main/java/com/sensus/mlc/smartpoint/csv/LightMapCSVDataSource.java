package com.sensus.mlc.smartpoint.csv;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.sensus.common.util.CSVDataSource;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;

public class LightMapCSVDataSource extends CSVDataSource<HashMap<String, String>>
{
	// These should be localized.
	private static final String INPUT_VOLTAGE_RANGE_HEADER = "Voltage Range";
	private static final String UPPER_ASSEMBLY_SERIAL_HEADER = "Upper Assembly Serial Number";
	private static final String MODEL_NUMBER_HEADER = "Model Number";
	private static final String LOWER_ASSEMBLY_SERIAL_HEADER = "Lower Assembly Serial Number";
	private static final String SERIAL_NUMBER_HEADER = "Light Driver Serial Number";
	private static final String HOUSING_COLOR_HEADER = "Housing Color";
	private static final String COLOR_TEMPERATURE_HEADER = "Color Temperature";
	private static final String BULB_SERIAL_HEADER = "Bulb Serial Number";
	private static final String POLE_ID_HEADER = "Pole ID";
	private static final String PROTECTED_HEADER = "Protected";
	private static final String CITY_NAME_HEADER = "City";
	private static final String LAMP_TYPE_HEADER = "Light Type";
	private static final String DATE_ADDED_HEADER = "Date Added";
	private static final String STATUS_HEADER = "Status";
	private static final String FLEXNET_ID_HEADER = "FlexNet ID";
	private static final String FIRMWARE_VERSION_HEADER = "Firmware Version";
	private static final String ECO_MODE_COLUMNM = "Eco Mode";
	private static final String ECO_MODE = "ecomode";

	/** The undefined. */
	private static String UNDEFINED = "N/A";

	// These are for mapping the smartpoint column enum to columns and data.
	private static Map<String, String> COLUMNMAP = new HashMap<String, String>();

	private InquiryLightRequest getInqRequest()
	{
		return (InquiryLightRequest)getRequest();
	}

	static
	{
		// Map of enum to column names.
		COLUMNMAP.put("rni_id", FLEXNET_ID_HEADER);
		COLUMNMAP.put("status", STATUS_HEADER);
		COLUMNMAP.put("date_added", DATE_ADDED_HEADER);
		COLUMNMAP.put("lamp_type", LAMP_TYPE_HEADER);
		COLUMNMAP.put("city_name", CITY_NAME_HEADER);
		COLUMNMAP.put("protected", PROTECTED_HEADER);
		COLUMNMAP.put("pole_id", POLE_ID_HEADER);
		COLUMNMAP.put("bulb_serial_number", BULB_SERIAL_HEADER);
		COLUMNMAP.put("color_temperature", COLOR_TEMPERATURE_HEADER);
		COLUMNMAP.put("firmware_version", FIRMWARE_VERSION_HEADER);
		COLUMNMAP.put("housing_color", HOUSING_COLOR_HEADER);
		COLUMNMAP.put("serial_number", SERIAL_NUMBER_HEADER);
		COLUMNMAP.put("lower_assembly_serial_number", LOWER_ASSEMBLY_SERIAL_HEADER);
		COLUMNMAP.put("model_number", MODEL_NUMBER_HEADER);
		COLUMNMAP.put("upper_assembly_serial_number", UPPER_ASSEMBLY_SERIAL_HEADER);
		COLUMNMAP.put("input_voltage_range", INPUT_VOLTAGE_RANGE_HEADER);
		COLUMNMAP.put(ECO_MODE, ECO_MODE_COLUMNM);
	}

	public LightMapCSVDataSource(InquiryLightRequest request)
	{
		super(request);

		for (Column column : request.getListColumn())
		{
			addColumn(COLUMNMAP.get(column.getFieldName()));
		}

		addData(request.getLightsToCSV());
	}

	@Override
	protected String getDataForColumn(HashMap<String, String> light, int column)
	{
		Column lightColumn = getInqRequest().getListColumn().get(column);
		String columnName = lightColumn.getFieldName();

		if (ValidationUtil.isNull(light.get(columnName)))
		{
			return UNDEFINED;
		}

		if (ECO_MODE.equals(columnName))
		{
			return new BigDecimal(String.valueOf(light.get(columnName))).setScale(0, RoundingMode.HALF_EVEN).intValue()
					+ "%";
		}

		return String.valueOf(light.get(columnName));
	}
}
