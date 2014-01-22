package com.sensus.lc.api.service.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.api.service.model.LCLight;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.response.FetchAllResponse;

/**
 * The Class APIUtils.
 */
public class APIUtils
{

	/** The Constant NUMBER_TWO. */
	private static final int NUMBER_TWO = 2;

	/**
	 * Handle light list.
	 * 
	 * @param fetchAllResponse the fetch all response
	 * @return the list
	 */
	public static List<LCLight> handleLightList(FetchAllResponse fetchAllResponse)
	{
		if (ValidationUtil.isNull(fetchAllResponse) || !fetchAllResponse.isOperationSuccess())
		{
			return null;
		}

		List<LCLight> lightList = new ArrayList<LCLight>();
		List<Light> lightListResponse = fetchAllResponse.getLightList();
		for (Light light : lightListResponse)
		{
			LCLight lcLight = new LCLight();

			lcLight.setFlexnetId(light.getRadio().getFlexNetId());
			lcLight.setLifeCycleState(light.getLifeCycleState().name());
			lcLight.setPoleId(light.getPoleId());
			lcLight.setProtect(light.getProtect());

			if (!ValidationUtil.isNull(light.getConfiguration()))
			{
				lcLight.setBallastSerialNumber(light.getConfiguration().getBallastSerialNumber());
				lcLight.setBulbSerialNumber(light.getConfiguration().getBulbSerialNumber());
				lcLight.setColorTemperature(light.getConfiguration().getColorTemperature());
				lcLight.setCustomerSerialNumber(light.getConfiguration().getCustomerSerialNumber());

				// Apply light TZ
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date(light.getConfiguration().getDateAdded().getTime()));
				calendar.add(Calendar.MILLISECOND, light.getRadio().getLocation().getTimeZoneInfo().getTimeZone()
						.getOffset(new Date().getTime()));

				// Format date
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				lcLight.setDateAdded(sdf.format(calendar.getTime()));

				lcLight.setDimmable(light.getConfiguration().getDimmable());
				lcLight.setFirmwareVersion(light.getConfiguration().getFirmwareVersion());
				lcLight.setHousing(light.getConfiguration().getHousing());
				lcLight.setHousingColor(light.getConfiguration().getHousingColor());
				lcLight.setInputVoltageRange(light.getConfiguration().getInputVoltageRange());
				lcLight.setLightDriverSerialNumber(light.getConfiguration().getLightDriverSerialNumber());
				lcLight.setLightType(light.getConfiguration().getLampTypeWattageDimmable());
				lcLight.setLowerAssemblySerial(light.getConfiguration().getLowerAssemblySerial());
				lcLight.setManufacturer(light.getConfiguration().getManufacturer());
				lcLight.setModelNumber(light.getConfiguration().getModelNumber());
				lcLight.setUpperAssemblySerial(light.getConfiguration().getUpperAssemblySerial());
				lcLight.setWattageRating(light.getConfiguration().getWattageRating());
			}

			if (!ValidationUtil.isNull(light.getEcoModeBaseline()))
			{
				lcLight.setReplacedType(light.getEcoModeBaseline().getReplacedType().name());
				lcLight.setReplacedWattage(light.getEcoModeBaseline().getReplacedWattage());
			}

			lightList.add(lcLight);
		}

		return lightList;

	}

	/**
	 * Handle alert list.
	 * 
	 * @param fetchAllResponse the fetch all response
	 * @return the map
	 */
	public static Map<String, List<Long[]>> handleAlertList(FetchAllResponse fetchAllResponse)
	{
		if (ValidationUtil.isNull(fetchAllResponse) || !fetchAllResponse.isOperationSuccess())
		{
			return null;
		}

		Map<String, List<Long[]>> alertList = new HashMap<String, List<Long[]>>();
		List<Light> lightList = fetchAllResponse.getLightList();
		for (Light light : lightList)
		{
			NotificationHistory notification = light.getLastNotificationHistory();
			AlertClassification alertClassification = notification.getAlertClassifications().get(0);
			AlertSubTypeEnum subtype = alertClassification.getAlertSubType();
			String key = StringUtils.lowerCase(subtype.name() + "_" + subtype.getAlertType().name());

			if (ValidationUtil.isNull(alertList.get(key)))
			{
				alertList.put(key, new ArrayList<Long[]>());
			}

			putLightInformation(light, alertClassification, alertList.get(key));
		}

		return alertList;

	}

	/**
	 * Put light information.
	 * 
	 * @param light the light
	 * @param alertClassification the alert classification
	 * @param lightInformations the light informations
	 */
	private static void putLightInformation(Light light, AlertClassification alertClassification,
			List<Long[]> lightInformations)
	{
		// Get epoch time
		Date alertDate = alertClassification.getMessageDate();
		TimeZone timezone = TimeZone.getDefault();
		int serverOffset = timezone.getRawOffset();
		if (timezone.inDaylightTime(new Date()))
		{
			serverOffset += timezone.getDSTSavings();
		}
		Long alertLongDate = alertDate.getTime() + serverOffset;

		Long flexnetId = light.getRadio().getFlexNetId().longValue();
		lightInformations.add(Arrays.asList(flexnetId, alertLongDate).toArray(new Long[NUMBER_TWO]));
	}

}