package com.sensus.mlc.wui.light.unittest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.ecomode.model.LightTypeEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.DataTypeEnum;
import com.sensus.mlc.smartpoint.model.Filter;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.LightStateEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SmartPointColumnEnum;
import com.sensus.mlc.smartpoint.model.SmartPointFilterEnum;
import com.sensus.mlc.smartpoint.model.SmartPointTypeEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.CountLightResponse;
import com.sensus.mlc.smartpoint.model.response.CurrentAlarmWarningMessageResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryCustomSearchResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.smartpoint.model.response.LightingConfigurationsResponse;
import com.sensus.mlc.smartpoint.model.response.PropertyValidValuesResponse;
import com.sensus.mlc.smartpoint.model.response.StatusMessageResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

public class SmartPointAccessorBCFMockImpl extends BaseMockImpl implements ISmartPointAccessorBCF
{


	@Override
	public InquiryLightResponse fetchAllLights(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();

		return response;

	}

	@Override
	public CountLightResponse countLights(LightingControlRequest request)
	{
		CountLightResponse response = new CountLightResponse();
		return response;

	}

	@Override
	public LightResponse fetchLightById(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();

			return response;
	}

	@Override
	public LightResponse fetchLightIdByRniId(LightRequest lightRequest)
	{
		return null;
	}

	@Override
	public PropertyValidValuesResponse fetchPropertyValidValues(PropertyValidValuesRequest request)
	{
		PropertyValidValuesResponse response = new PropertyValidValuesResponse();
		return response;
	}

	@Override
	public LightResponse fetchUpdateLightStatus(ProcessRequest processRequest)
	{
		return null;
	}


	@Override
	public InquiryCustomSearchResponse fetchAllCustomSearch(InquiryPaginationRequest inquiryPaginationRequest)
	{
		InquiryCustomSearchResponse response = new InquiryCustomSearchResponse();
		return response;
	}

	@Override
	public StatusMessageResponse fetchStatusMessageByLightID(LightRequest request)
	{
		return null;
	}

	@Override
	public LightingConfigurationsResponse fetchLigthingConfigurationsByPartNumber(LightingConfigurationRequest request)
	{
		return null;
	}

	@Override
	public InquiryLightResponse fetchLightHistory(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();

		return response;
	}

	@Override
	public LightResponse fetchLightHistoryHeader(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();

		return response;
	}

	@Override
	public ColumnFilterResponse fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		ColumnFilterResponse response = new ColumnFilterResponse();

		//if (MODE_SUCCESS.equals(this.getMode()))
		{
			// BEGIN ..:COLUMNS:.. DEFINITION
			List<Column> columns = new ArrayList<Column>();

			columns.add(setColumn(SmartPointColumnEnum.POLE_ID, "Pole ID"));
			columns.add(setColumn(SmartPointColumnEnum.RNI_ID, "FlexNet ID"));
			columns.add(setColumn(SmartPointColumnEnum.LAMP_TYPE, "Light Type"));
			columns.add(setColumn(SmartPointColumnEnum.DATE_ADDED, "Date Added"));
			columns.add(setColumn(SmartPointColumnEnum.CITY_NAME, "City"));
			columns.add(setColumn(SmartPointColumnEnum.MAP_IT, "Map It"));
			columns.add(setColumn(SmartPointColumnEnum.PROTECTED, "Protected"));
			columns.add(setColumn(SmartPointColumnEnum.ECOMODE, "Eco-Mode"));
			columns.add(setColumn(SmartPointColumnEnum.STATUS, "Status"));

			response.setListColumn(columns);

			List<Column> allColumns = new ArrayList<Column>();

			allColumns.add(setColumn(SmartPointColumnEnum.FIRMWARE_VERSION, "Firmware Version"));
			allColumns.add(setColumn(SmartPointColumnEnum.MODEL_NUMBER, "Model Number"));
			allColumns.add(setColumn(SmartPointColumnEnum.COLOR_TEMPERATURE, "Color Temperature"));
			allColumns.add(setColumn(SmartPointColumnEnum.HOUSING_COLOR, "Housing Color"));
			allColumns.add(setColumn(SmartPointColumnEnum.INPUT_VOLTAGE_RANGE, "Voltage Range"));
			allColumns.add(setColumn(SmartPointColumnEnum.BULB_SERIAL_NUMBER, "Bulb Serial Number"));
			allColumns.add(setColumn(SmartPointColumnEnum.SERIAL_NUMBER, "Light Driver Serial Number"));
			allColumns
					.add(setColumn(SmartPointColumnEnum.LOWER_ASSEMBLY_SERIAL_NUMBER,
							"Lower Assembly Serial Number"));
			allColumns
					.add(this.setColumn(SmartPointColumnEnum.UPPER_ASSEMBLY_SERIAL_NUMBER,
							"Upper Assembly Serial Number"));

			response.setAdditionalColumns(allColumns);
			// END COLUMNS DEFINITION

			// BEGIN ..:FILTER:.. DEFINITION
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(setFilter(SmartPointFilterEnum.GROUPS));
			filters.add(setFilter(SmartPointFilterEnum.STATUS));
			filters.add(setFilter(SmartPointFilterEnum.ALARM_TYPE));
			filters.add(setFilter(SmartPointFilterEnum.WARNING_TYPE));
			filters.add(setFilter(SmartPointFilterEnum.LIGHT_TYPES));
			filters.add(setFilter(SmartPointFilterEnum.EVENT_SCHEDULE));
			filters.add(setFilter(SmartPointFilterEnum.OFFSET_SCHEDULE));
			filters.add(setFilter(SmartPointFilterEnum.TAGS));
			filters.add(setFilter(SmartPointFilterEnum.ADDRESS));
			filters.add(setFilter(SmartPointFilterEnum.CONFIGURATION));
			filters.add(setFilter(SmartPointFilterEnum.ECOMODE));

			response.setFilters(filters);

			List<Filter> additionalFilters = new ArrayList<Filter>();

			additionalFilters.add(setFilter(SmartPointFilterEnum.FIRMWARE_VERSION));
			additionalFilters.add(setFilter(SmartPointFilterEnum.DATE_ADDED));
			additionalFilters.add(setFilter(SmartPointFilterEnum.MODEL_NUMBER));
			additionalFilters.add(setFilter(SmartPointFilterEnum.COLOR_TEMPERATURE));
			additionalFilters.add(setFilter(SmartPointFilterEnum.HOUSING_COLOR));
			additionalFilters.add(setFilter(SmartPointFilterEnum.VOLTAGE_RANGE));
			additionalFilters.add(setFilter(SmartPointFilterEnum.BULB_SERIAL_NUMBER));
			additionalFilters.add(setFilter(SmartPointFilterEnum.LIGHT_DRIVER_SERIAL_NUMBER));
			additionalFilters.add(setFilter(SmartPointFilterEnum.LOWER_ASSEMBLY_SERIAL_NUMBER));
			additionalFilters.add(setFilter(SmartPointFilterEnum.UPPER_ASSEMBLY_SERIAL_NUMBER));

			response.setAdditionalFilters(additionalFilters);
			// END ..:FILTER:.. DEFINITION

			return response;
		}

	}

	@Override
	public CurrentAlarmWarningMessageResponse fetchCurrentAlarmStatusMessagesByLight(LightRequest lightRequest)
	{
		CurrentAlarmWarningMessageResponse response = new CurrentAlarmWarningMessageResponse();

		return response;
	}

	@Override
	public InquiryLightResponse generateFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();
		return response;
	}


	/**
	 * Populate param.
	 *
	 * @param i the i
	 * @return the list
	 */
	private List<LightParameter> populateParam(int i)
	{

		LightParameter latitude = new LightParameter();
		latitude.setValue("41.25816656");
		latitude.setDataTypeEnum(DataTypeEnum.DOUBLE);
		latitude.setPropertyEnum(PropertyEnum.LATITUDE);
		latitude.setLabelKey("");

		LightParameter longitude = new LightParameter();
		longitude.setValue("-95.93688238");
		longitude.setDataTypeEnum(DataTypeEnum.DOUBLE);
		longitude.setPropertyEnum(PropertyEnum.LONGITUDE);
		longitude.setLabelKey("");

		LightParameter firmware = new LightParameter();
		firmware.setValue(String.valueOf(2605 + (i * 3)));
		firmware.setDataTypeEnum(DataTypeEnum.INTEGER);
		firmware.setPropertyEnum(PropertyEnum.FIRMWARE_VERSION);
		firmware.setLabelKey("");

		LightParameter poleId = new LightParameter();
		poleId.setValue(String.valueOf(i));
		poleId.setDataTypeEnum(DataTypeEnum.STRING);
		poleId.setPropertyEnum(PropertyEnum.POLE_ID);
		poleId.setLabelKey("");

		LightParameter serial = new LightParameter();
		serial.setValue(String.valueOf(14587 + i));
		serial.setDataTypeEnum(DataTypeEnum.INTEGER);
		serial.setPropertyEnum(PropertyEnum.LIGHT_DRIVER_SERIAL_NUMBER);
		serial.setLabelKey("");

		LightParameter sensusPartNumber = new LightParameter();
		sensusPartNumber.setValue(String.valueOf(14700 + i));
		sensusPartNumber.setDataTypeEnum(DataTypeEnum.INTEGER);
		sensusPartNumber.setPropertyEnum(PropertyEnum.SENSUS_PART_NUMBER);
		sensusPartNumber.setLabelKey("");

		LightParameter lampType = new LightParameter();
		lampType.setValue("lampType" + i);
		lampType.setDataTypeEnum(DataTypeEnum.STRING);
		lampType.setPropertyEnum(PropertyEnum.LAMP_TYPE);
		lampType.setLabelKey("");

		LightParameter wattage = new LightParameter();
		wattage.setValue("w" + i + i);
		wattage.setDataTypeEnum(DataTypeEnum.STRING);
		wattage.setPropertyEnum(PropertyEnum.WATTAGE_RATING);
		wattage.setLabelKey("");

		LightParameter inputVoltageRange = new LightParameter();
		inputVoltageRange.setValue("ivr" + i);
		inputVoltageRange.setDataTypeEnum(DataTypeEnum.STRING);
		inputVoltageRange.setPropertyEnum(PropertyEnum.INPUT_VOLTAGE_RANGE);
		inputVoltageRange.setLabelKey("");

		LightParameter manufacturer = new LightParameter();
		manufacturer.setValue("manufacturer " + i);
		manufacturer.setDataTypeEnum(DataTypeEnum.STRING);
		manufacturer.setPropertyEnum(PropertyEnum.MANUFACTURER);
		manufacturer.setLabelKey("");

		LightParameter colorTemperature = new LightParameter();
		colorTemperature.setValue("7500");
		colorTemperature.setDataTypeEnum(DataTypeEnum.STRING);
		colorTemperature.setPropertyEnum(PropertyEnum.COLOR_TEMPERATURE);
		colorTemperature.setLabelKey("");

		LightParameter dateAdded = new LightParameter();
		dateAdded.setValue("10/15/2010");
		dateAdded.setDataTypeEnum(DataTypeEnum.DATE);
		dateAdded.setPropertyEnum(PropertyEnum.DATE_ADDED);
		dateAdded.setLabelKey("");

		LightParameter dateInstalled = new LightParameter();
		dateInstalled.setValue("10/15/2010");
		dateInstalled.setDataTypeEnum(DataTypeEnum.DATE);
		dateInstalled.setPropertyEnum(PropertyEnum.DATE_INSTALLED);
		dateInstalled.setLabelKey("");

		LightParameter sunriseTime = new LightParameter();
		sunriseTime.setValue("10/15/2010");
		sunriseTime.setDataTypeEnum(DataTypeEnum.DATE);
		sunriseTime.setPropertyEnum(PropertyEnum.SUNRISE_TIME);
		sunriseTime.setLabelKey("");

		LightParameter sunriseOffset = new LightParameter();
		sunriseOffset.setValue("-10");
		sunriseOffset.setDataTypeEnum(DataTypeEnum.STRING);
		sunriseOffset.setPropertyEnum(PropertyEnum.SUNRISE_OFFSET);
		sunriseOffset.setLabelKey("");

		LightParameter sunsetOffset = new LightParameter();
		sunsetOffset.setValue("-15");
		sunsetOffset.setDataTypeEnum(DataTypeEnum.STRING);
		sunsetOffset.setPropertyEnum(PropertyEnum.SUNSET_OFFSET);
		sunsetOffset.setLabelKey("");

		LightParameter sunsetTime = new LightParameter();
		sunsetTime.setValue("10/15/2010");
		sunsetTime.setDataTypeEnum(DataTypeEnum.DATE);
		sunsetTime.setPropertyEnum(PropertyEnum.SUNSET_TIME);
		sunsetTime.setLabelKey("");

		LightParameter city = new LightParameter();
		city.setValue("Beaverton");
		city.setDataTypeEnum(DataTypeEnum.STRING);
		city.setPropertyEnum(PropertyEnum.CITY_NAME);
		city.setLabelKey("");

		LightParameter modelNumber = new LightParameter();
		modelNumber.setValue("5393490123131");
		modelNumber.setDataTypeEnum(DataTypeEnum.STRING);
		modelNumber.setPropertyEnum(PropertyEnum.MODEL_NUMBER);
		modelNumber.setLabelKey("");

		LightParameter housingColor = new LightParameter();
		housingColor.setValue("Housing Color");
		housingColor.setDataTypeEnum(DataTypeEnum.STRING);
		housingColor.setPropertyEnum(PropertyEnum.HOUSING_COLOR);
		housingColor.setLabelKey("");

		LightParameter bulbSerialNumber = new LightParameter();
		bulbSerialNumber.setValue("Bulb Serial Number");
		bulbSerialNumber.setDataTypeEnum(DataTypeEnum.STRING);
		bulbSerialNumber.setPropertyEnum(PropertyEnum.BULB_SERIAL_NUMBER);
		bulbSerialNumber.setLabelKey("");

		LightParameter lowerAssemblySerialNumber = new LightParameter();
		lowerAssemblySerialNumber.setValue("Lower Assembly Serial Number");
		lowerAssemblySerialNumber.setDataTypeEnum(DataTypeEnum.STRING);
		lowerAssemblySerialNumber.setPropertyEnum(PropertyEnum.LOWER_ASSEMBLY_SERIAL_NUMBER);
		lowerAssemblySerialNumber.setLabelKey("");

		LightParameter upperAssemblySerialNumber = new LightParameter();
		upperAssemblySerialNumber.setValue("Upper Assembly Serial Number");
		upperAssemblySerialNumber.setDataTypeEnum(DataTypeEnum.STRING);
		upperAssemblySerialNumber.setPropertyEnum(PropertyEnum.UPPER_ASSEMBLY_SERIAL_NUMBER);
		upperAssemblySerialNumber.setLabelKey("");

		LightParameter dimmable = new LightParameter();
		dimmable.setValue("True");
		dimmable.setDataTypeEnum(DataTypeEnum.STRING);
		dimmable.setPropertyEnum(PropertyEnum.DIMMABLE);
		dimmable.setLabelKey("");

		LightParameter current = new LightParameter();
		current.setValue("3131");
		current.setDataTypeEnum(DataTypeEnum.INTEGER);
		current.setPropertyEnum(PropertyEnum.AC_CURRENT);
		current.setLabelKey("");

		LightParameter currentmin = new LightParameter();
		currentmin.setValue("3131");
		currentmin.setDataTypeEnum(DataTypeEnum.INTEGER);
		currentmin.setPropertyEnum(PropertyEnum.AC_CURRENT_MIN);
		currentmin.setLabelKey("");

		LightParameter currentmax = new LightParameter();
		currentmax.setValue("100131");
		currentmax.setDataTypeEnum(DataTypeEnum.INTEGER);
		currentmax.setPropertyEnum(PropertyEnum.AC_CURRENT_MAX);
		currentmax.setLabelKey("");

		LightParameter consumption = new LightParameter();
		consumption.setValue("3131");
		consumption.setDataTypeEnum(DataTypeEnum.INTEGER);
		consumption.setPropertyEnum(PropertyEnum.CONSUMPTION);
		consumption.setLabelKey("");

		LightParameter consumptionmin = new LightParameter();
		consumptionmin.setValue("3131");
		consumptionmin.setDataTypeEnum(DataTypeEnum.INTEGER);
		consumptionmin.setPropertyEnum(PropertyEnum.CONSUMPTION_MIN);
		consumptionmin.setLabelKey("");

		LightParameter consumptionmax = new LightParameter();
		consumptionmax.setValue("100131");
		consumptionmax.setDataTypeEnum(DataTypeEnum.INTEGER);
		consumptionmax.setPropertyEnum(PropertyEnum.CONSUMPTION_MAX);

		LightParameter voltage = new LightParameter();
		voltage.setValue("3131");
		voltage.setDataTypeEnum(DataTypeEnum.INTEGER);
		voltage.setPropertyEnum(PropertyEnum.AC_VOLTAGE);
		voltage.setLabelKey("");

		LightParameter voltagemin = new LightParameter();
		voltagemin.setValue("3131");
		voltagemin.setDataTypeEnum(DataTypeEnum.INTEGER);
		voltagemin.setPropertyEnum(PropertyEnum.AC_VOLTAGE_MIN);
		voltagemin.setLabelKey("");

		LightParameter voltagemax = new LightParameter();
		voltagemax.setValue("100131");
		voltagemax.setDataTypeEnum(DataTypeEnum.INTEGER);
		voltagemax.setPropertyEnum(PropertyEnum.AC_VOLTAGE_MAX);
		voltagemax.setLabelKey("");

		List<LightParameter> list = new ArrayList<LightParameter>();

		list.add(poleId);
		list.add(lampType);
		list.add(dateAdded);
		list.add(city);
		list.add(firmware);
		list.add(modelNumber);
		list.add(colorTemperature);
		list.add(housingColor);
		list.add(inputVoltageRange);
		list.add(bulbSerialNumber);
		list.add(lowerAssemblySerialNumber);
		list.add(upperAssemblySerialNumber);
		list.add(latitude);
		list.add(longitude);
		list.add(dimmable);
		list.add(consumption);
		list.add(consumptionmin);
		list.add(consumptionmax);
		list.add(sunsetTime);
		list.add(sunsetOffset);
		list.add(sunriseOffset);
		list.add(sunriseTime);
		list.add(dateInstalled);
		list.add(manufacturer);
		list.add(wattage);
		list.add(sensusPartNumber);
		list.add(serial);
		list.add(voltage);
		list.add(voltagemin);
		list.add(voltagemax);
		list.add(current);
		list.add(currentmin);
		list.add(currentmax);

		return list;
	}

	/**
	 * Sets the column.
	 *
	 * @param propertyEnum the property enum
	 * @param text the text
	 * @return the column
	 */
	private Column setColumn(SmartPointColumnEnum propertyEnum, String text)
	{
		Column column = new Column();
		column.setColumnEnum(propertyEnum);
		column.setFieldName(text);
		column.setOrdered(true);

		return column;
	}

	/**
	 * Sets the filter.
	 *
	 * @param filterEnum the filter enum
	 * @return the filter
	 */
	private Filter setFilter(SmartPointFilterEnum filterEnum)
	{
		Filter filter = new Filter();

		filter.setFilterEnum(filterEnum);

		return filter;
	}

	@Override
	public InquiryLightResponse generateLightHistoryFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		return new InquiryLightResponse();
	}

	@Override
	public InquiryLightResponse fetchSmartpointsToMap(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse inquiryLightResponse = new InquiryLightResponse();
		List<Light> lightHistory = new ArrayList<Light>();

		if (!ValidationUtil.isNullOrZero(inquiryLightRequest.getBottomLeftLat()) &&
				!ValidationUtil.isNullOrZero(inquiryLightRequest.getBottomLeftLon()) &&
				!ValidationUtil.isNullOrZero(inquiryLightRequest.getTopRightLat()) &&
				!ValidationUtil.isNullOrZero(inquiryLightRequest.getTopRightLon()) &&
				!ValidationUtil.isNullOrZero(inquiryLightRequest.getMaxSmartpointCount()) &&
				!ValidationUtil.isNullOrEmpty(inquiryLightRequest.getTimezone()))
		{

			for (int i = 0; i < inquiryLightRequest.getMaxSmartpointCount(); i++)
			{
				Light light = new Light();

				light.setId(i);
				light.setLightStateEnum(LightStateEnum.ON);
				light.setLightStateEnumValue(1);
				light.setSmartPointTypeEnum(SmartPointTypeEnum.LIGHT);
				light.setSmartPointTypeValue(1);
				lightHistory.add(light);

			}
			inquiryLightRequest.setLights(lightHistory);

			inquiryLightResponse.setOperationSuccess(true);
			inquiryLightResponse.setResponseTime(new Date());
		}
		else
		{

			inquiryLightResponse.setOperationSuccess(false);
		}

		return inquiryLightResponse;
	}

	@Override
	public StatusMessageResponse fetchStatusMessageById(LightRequest lightRequest)
	{
		return new StatusMessageResponse();
	}

	public static LightTypeEnum getLightTypeRandom()
	{
		int i = new Random().nextInt(3);
		return LightTypeEnum.enumForValue(i + 1);
	}

	/**
	 * Gets the wattage random.
	 *
	 * @return the wattage random
	 */
	public static Double getWattageRandom()
	{
		return new Double(new Random().nextInt(150));
	}

}
