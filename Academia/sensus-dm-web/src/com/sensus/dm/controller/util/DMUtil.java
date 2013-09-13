package com.sensus.dm.controller.util;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DeviceTypeModelPermissions;
import com.sensus.dm.common.base.model.DeviceTypePermissions;
import com.sensus.dm.common.base.model.ServicesTypePermissions;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMTypeEnum;

/**
 * The Class DMUtil.
 */
@Controller
@RequestMapping("/api/util")
public class DMUtil
{
	/** The Constant COLON. */
	private static final String COLON = ":";

	/** The HEXBASE. */
	private static int HEXBASE = 16;

	/** The MA x_ bits. */
	private static int MAX_BITS = 64;

	/** The HEXDIGITS. */
	private static int HEXDIGITS = MAX_BITS / 4;

	/** The Constant SERVER_TIME. */
	private static final String SERVER_TIME = "serverTime";

	/**
	 * Now time.
	 * 
	 * @return the date
	 */
	@RequestMapping(value = SERVER_TIME, method = RequestMethod.POST)
	@ResponseBody
	public Date serverTime()
	{
		return DMConvertUtil.convertDateToDefaultUTC(new Date());
	}

	/**
	 * Convert mac address.
	 * 
	 * @param device the device
	 * @return the string
	 */
	public static String convertMacAddress(Device device)
	{

		BigInteger macAddress = device.getRadio().getFlexNetId();

		// Entek Device
		if (DeviceTypeEnum.LCM.equals(device.getDeviceType()))
		{
			LCM lcm = (LCM)device;
			if (LCMTypeEnum.FLEXNET_LCM.equals(lcm.getLcmTypeEnum()))
			{
				return macAddress.toString();
			}
		}

		return convertBigInteger(macAddress).toString();
	}

	/**
	 * Convert eletric mac address.
	 * 
	 * @param electricDevice the electric device
	 */
	public static void convertEletricMacAddress(Device electricDevice)
	{

		if (!ValidationUtil.isNull(electricDevice.getRadio())
				&& !ValidationUtil.isNull(electricDevice.getRadio().getFlexNetId()))
		{

			if (DeviceTypeEnum.LCM.equals(electricDevice.getDeviceType())
					&& (!LCMTypeEnum.FLEXNET_LCM.equals(((LCM)electricDevice).getLcmTypeEnum())))
			{
				LCM lcm = (LCM)electricDevice;
				lcm.setMacAddress(convertBigInteger(lcm.getRadio().getFlexNetId()));
			}
			else if (DeviceTypeEnum.HAN_DEVICE.equals(electricDevice.getDeviceType()))
			{
				HanDevice hanDevice = (HanDevice)electricDevice;
				hanDevice.setMacAddress(convertBigInteger(hanDevice.getRadio().getFlexNetId()));
			}
		}
	}

	/**
	 * Convert big integer.
	 * 
	 * @param mac the mac
	 * @return the string
	 */
	public static String convertBigInteger(BigInteger mac)
	{
		BigInteger macNumber = convertBigIntegerToInternalRepresentation(mac);

		String strRep = macNumber.toString(HEXBASE);

		// Add the 0 at the left to complete the string.
		if (strRep.length() < HEXDIGITS)
		{

			StringBuffer zeroStr = new StringBuffer();

			for (int i = 0; i < (HEXDIGITS - strRep.length()); i++)
			{
				zeroStr.append("0"); //$NON-NLS-1$
			}
			strRep = new String(zeroStr + strRep);
		}

		// For each pair of character, add a : character, except for the last one
		StringBuffer hexRep = new StringBuffer();
		for (int i = 0; i < (HEXDIGITS - 2); i = i + 2)
		{
			hexRep.append(strRep.substring(i, i + 2) + COLON);
		}
		hexRep.append(strRep.substring(HEXDIGITS - 2, HEXDIGITS));

		return hexRep.toString();
	}

	/**
	 * Convert big integer to internal representation.
	 * 
	 * @param macNumber the mac number
	 * @return the big integer
	 */
	private static BigInteger convertBigIntegerToInternalRepresentation(BigInteger macNumber)
	{
		if (macNumber.signum() < 0)
		{
			// macNumber cannot be negative. Set it to a positive number
			macNumber = macNumber.negate();
		}

		if (macNumber.bitLength() > MAX_BITS)
		{
			// Mac number cannot be greater than 2 ^48, then convert to a 48 bit number
			String bigMacStr = macNumber.toString(HEXBASE);
			String macStr = bigMacStr.substring(bigMacStr.length() - 1 - HEXDIGITS, bigMacStr.length() - 1);
			macNumber = new BigInteger(macStr, HEXBASE);
		}
		return macNumber;
	}

	/**
	 * Convert mac address.
	 * 
	 * @param macString the mac string
	 * @return the big integer
	 */
	public static BigInteger convertMacAddress(String macString)
	{
		String trimmedMacString = macString.trim();
		boolean isValidMac = trimmedMacString.matches("^(\\p{XDigit}{2}:){7}\\p{XDigit}{2}$"); //$NON-NLS-1$

		if (!isValidMac)
		{
			try
			{
				return new BigInteger(macString);
			}
			catch (NumberFormatException e)
			{
				return null;
			}
		}

		// Remove all ':' from the String
		String validMacString = trimmedMacString.replaceAll(COLON, ""); //replace(':', '\0');  //$NON-NLS-1$

		// Convert to the internal representation
		return new BigInteger(validMacString, HEXBASE);
	}

	/**
	 * Convert time zone to minutes.
	 * 
	 * @param device the device
	 */
	public static void convertTimeZoneToMinutes(Device device)
	{
		// Validate if exist Display Name information at Device Object
		if (!ValidationUtil.isNull(device.getRadio()) && !ValidationUtil.isNull(device.getRadio().getLocation())
				&& !ValidationUtil.isNull(device.getRadio().getLocation().getTimeZoneInfo())
				&& !ValidationUtil.isNull(device.getRadio().getLocation().getTimeZoneInfo().getDisplayName()))
		{
			// Convert to minutes
			String displayMinutes =
					PreferencesSessionUtil.getTimeZoneMinutes(device.getRadio().getLocation().getTimeZoneInfo()
							.getDisplayName());

			// Set timeZone of the Device in minutes
			device.getRadio().getLocation().getTimeZoneInfo().setDisplayMinutes(displayMinutes);
		}

	}

	/**
	 * Gets the service type permissions.
	 * 
	 * @param servicesTypePermissions the services type permissions
	 * @param serviceTypeEnum the service type enum
	 * @return the service type permissions
	 */
	@SuppressWarnings("unchecked")
	public static Collection<ServicesTypePermissions> getServiceTypePermissions(
			List<ServicesTypePermissions> servicesTypePermissions,
			final ServiceTypeEnum serviceTypeEnum)
	{

		return CollectionUtils.select(servicesTypePermissions, new Predicate()
		{
			@Override
			public boolean evaluate(Object o)
			{
				ServicesTypePermissions s = (ServicesTypePermissions)o;
				return serviceTypeEnum.equals(s.getServiceTypeEnum());
			}
		});
	}

	/**
	 * Gets the device type permissions.
	 * 
	 * @param deviceTypePermissions the device type permissions
	 * @param deviceTypeEnum the device type enum
	 * @return the device type permissions
	 */
	@SuppressWarnings("unchecked")
	public static Collection<DeviceTypePermissions> getDeviceTypePermissions(
			List<DeviceTypePermissions> deviceTypePermissions,
			final DeviceTypeEnum deviceTypeEnum)
	{

		return CollectionUtils.select(deviceTypePermissions, new Predicate()
		{
			@Override
			public boolean evaluate(Object o)
			{
				DeviceTypePermissions d = (DeviceTypePermissions)o;
				return deviceTypeEnum.equals(d.getDeviceType());
			}
		});
	}

	/**
	 * Gets the device type models.
	 * 
	 * @param deviceTypeModels the device type models
	 * @param name the name
	 * @return the device type models
	 */
	@SuppressWarnings("unchecked")
	public static Collection<DeviceTypeModelPermissions> getDeviceTypeModels(
			List<DeviceTypeModelPermissions> deviceTypeModels,
			final String common, final String name)
	{

		return CollectionUtils.select(deviceTypeModels, new Predicate()
		{
			@Override
			public boolean evaluate(Object o)
			{
				DeviceTypeModelPermissions d = (DeviceTypeModelPermissions)o;
				return common.equals(d.getName()) || name.equals(d.getName());
			}
		});
	}

}
