package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum PropertyEnum.
 *
 * @author - Alex Barros - QAT
 */
public enum PropertyEnum implements IIntegerEnum
{

	/** The sunrise time. */
	SUNRISE_TIME(1),

	/** The sunrise offset. */
	SUNRISE_OFFSET(2),

	/** The sunset time. */
	SUNSET_TIME(3),

	/** The sunset offset. */
	SUNSET_OFFSET(4),

	/** The light driver serial number. */
	LIGHT_DRIVER_SERIAL_NUMBER(5),

	/** The lamp type. */
	LAMP_TYPE(6),

	/** The wattage rating. */
	WATTAGE_RATING(7),

	/** The input voltage range. */
	INPUT_VOLTAGE_RANGE(8),

	/** The color temperature. */
	COLOR_TEMPERATURE(9),

	/** The manufacturer. */
	MANUFACTURER(10),

	/** The sensus part number. */
	SENSUS_PART_NUMBER(11),

	/** The pode id. */
	POLE_ID(12),

	/** The date added. */
	DATE_ADDED(13),

	/** The date installed. */
	DATE_INSTALLED(14),

	/** The firmware version. */
	FIRMWARE_VERSION(15),

	/** The latitude. */
	LATITUDE(16),

	/** The longitude. */
	LONGITUDE(17),

	/** The lamp type wattage dimmable. */
	LAMP_TYPE_WATTAGE_DIMMABLE(18),

	/** The ac voltage. */
	AC_VOLTAGE(19),

	/** The ac current. */
	AC_CURRENT(20),

	/** The group id. */
	GROUP_ID(21),

	/** The schedule id. */
	SCHEDULE_ID(22),

	/** The light intensity. */
	LIGHT_INTENSITY(23),

	/** The protected. */
	PROTECTED(24),

	/** The tag id. */
	TAG_ID(25),

	/** The status message id. */
	STATUS_MESSAGE_ID(26),

	/** The status subtype id. */
	STATUS_SUBTYPE_ID(27),

	/** The street name. */
	STREET_NAME(28),

	/** The city name. */
	CITY_NAME(29),

	/** The country name. */
	COUNTY_NAME(30),

	/** The state name. */
	STATE_NAME(31),

	/** The zip code. */
	ZIP_CODE(32),

	/** The schedule name. */
	SCHEDULE_NAME(33),

	/** The group name. */
	GROUP_NAME(34),

	/** The tag name. */
	TAG_NAME(35),

	/** The flexnet id. */
	FLEXNET_ID(36),

	/** The language. */
	LANGUAGE(37),

	/** The time zone. */
	TIME_ZONE(38),

	/** The dsate format. */
	DATE_FORMAT(39),

	/** The monitor request. */
	MONITOR_REQUEST(40),

	/** The event schedule. */
	EVENT_SCHEDULE(42),

	/** The offset schedule. */
	OFFSET_SCHEDULE(43),

	/** The model number. */
	MODEL_NUMBER(44),

	/** The lower assembly serial number. */
	LOWER_ASSEMBLY_SERIAL_NUMBER(45),

	/** The upper assembly serial number. */
	UPPER_ASSEMBLY_SERIAL_NUMBER(46),

	/** The bulb serial number. */
	BULB_SERIAL_NUMBER(47),

	/** The ballast serial number. */
	BALLAST_SERIAL_NUMBER(48),

	/** The customer serial number. */
	CUSTOMER_SERIAL_NUMBER(49),

	/** The frequency. */
	FREQUENCY(50),

	/** The light source. */
	LIGHT_SOURCE(51),

	/** The light detail type. */
	LIGHT_DETAIL_TYPE(52),

	/** The file name. */
	FILE_NAME(53),

	/** The convert unit of energy. */
	CONVERT_ENERGY_UNIT(54),

	/** The DIMMABLE. */
	DIMMABLE(55),

	/** The HOUSING. */
	HOUSING(56),

	/** The HARDWAR e_ settin g_ configuratio n_1. */
	HARDWARE_SETTING_CONFIGURATION_1(57),

	/** The CURREN t_ scal e_ configuratio n_1. */
	CURRENT_SCALE_CONFIGURATION_1(58),

	/** The FUL l_ o n_ require d_ configuratio n_1. */
	FULL_ON_REQUIRED_CONFIGURATION_1(59),

	/** The HARDWAR e_ settin g_ configuratio n_2. */
	HARDWARE_SETTING_CONFIGURATION_2(60),

	/** The CURREN t_ scal e_ configuratio n_2. */
	CURRENT_SCALE_CONFIGURATION_2(61),

	/** The FUL l_ o n_ require d_ configuratio n_2. */
	FULL_ON_REQUIRED_CONFIGURATION_2(62),

	/** The HARDWAR e_ settin g_ configuratio n_3. */
	HARDWARE_SETTING_CONFIGURATION_3(63),

	/** The CURREN t_ scal e_ configuratio n_3. */
	CURRENT_SCALE_CONFIGURATION_3(64),

	/** The FUL l_ o n_ require d_ configuratio n_3. */
	FULL_ON_REQUIRED_CONFIGURATION_3(65),

	/** The HARDWAR e_ settin g_ configuratio n_4. */
	HARDWARE_SETTING_CONFIGURATION_4(66),

	/** The CURREN t_ scal e_ configuratio n_4. */
	CURRENT_SCALE_CONFIGURATION_4(67),

	/** The FUL l_ o n_ require d_ configuratio n_4. */
	FULL_ON_REQUIRED_CONFIGURATION_4(68),

	/** The HARDWAR e_ settin g_ configuratio n_5. */
	HARDWARE_SETTING_CONFIGURATION_5(68),

	/** The CURREN t_ scal e_ configuratio n_5. */
	CURRENT_SCALE_CONFIGURATION_5(70),

	/** The FUL l_ o n_ require d_ configuratio n_5. */
	FULL_ON_REQUIRED_CONFIGURATION_5(71),

	/** The HARDWAR e_ settin g_ configuratio n_6. */
	HARDWARE_SETTING_CONFIGURATION_6(72),

	/** The CURREN t_ scal e_ configuratio n_6. */
	CURRENT_SCALE_CONFIGURATION_6(73),

	/** The FUL l_ o n_ require d_ configuratio n_6. */
	FULL_ON_REQUIRED_CONFIGURATION_6(74),

	/** The HOUSIN g_ color. */
	HOUSING_COLOR(75),

	/** The SUNRIS e_ before. */
	SUNRISE_BEFORE(76),

	/** The SUNSE t_ before. */
	SUNSET_BEFORE(77),

	/** The DAY s_ o f_ week. */
	DAYS_OF_WEEK(78),

	/** The SCHEDUL e_ even t_ time. */
	SCHEDULE_EVENT_TIME(79),

	/** The DI m_ o n_ delay. */
	DIM_ON_DELAY(80),

	/** The CURREN t_ ligh t_ status. */
	CURRENT_LIGHT_STATUS(81),

	/** The CURREN t_ alar m_ warnin g_ statu s_ subtype. */
	CURRENT_ALARM_WARNING_STATUS_SUBTYPE(82),

	/** The A c_ voltag e_ min. */
	AC_VOLTAGE_MIN(83),

	/** The A c_ voltag e_ max. */
	AC_VOLTAGE_MAX(84),

	/** The A c_ curren t_ min. */
	AC_CURRENT_MIN(85),

	/** The A c_ curren t_ max. */
	AC_CURRENT_MAX(86),

	/** The CONSUMPTION. */
	CONSUMPTION(87),

	/** The CONSUMPTIO n_ min. */
	CONSUMPTION_MIN(88),

	/** The CONSUMPTIO n_ max. */
	CONSUMPTION_MAX(89),

	/** The PAG e_ size. */
	PAGE_SIZE(90),

	/** The DAT e_ adde d_ after. */
	DATE_ADDED_AFTER(91),

	/** The DAT e_ adde d_ before. */
	DATE_ADDED_BEFORE(92),

	/** The SORT. */
	SORT(93),

	/** The AL l_ groups. */
	ALL_GROUPS(94),

	/** The AL l_ status. */
	ALL_STATUS(95),

	/** The AL l_ alarms. */
	ALL_ALARMS(96),

	/** The AL l_ warnings. */
	ALL_WARNINGS(97),

	/** The AL l_ events. */
	ALL_EVENTS(98),

	/** The AL l_ offsets. */
	ALL_OFFSETS(99),

	/** The AL l_ tags. */
	ALL_TAGS(100),

	/** The PAG e_ siz e_ sho w_ dialog. */
	PAGE_SIZE_SHOW_DIALOG(101),

	/** The USE r_ id. */
	USER_ID(102),

	/** The USE r_ name. */
	USER_NAME(103),

	/** The Smartpoint column. */
	SMARTPOINT_COLUMN(104),

	/** The Smartpoint filter. */
	SMARTPOINT_FILTER(105),

	/** The Order by. */
	ORDER_BY(106),

	/** The light blink. */
	LIGHT_BLINK(107),

	/** The override. */
	OVERRIDE(108),

	/** The override per date. */
	OVERRIDE_PER_DATE(109),

	/** The active. */
	ACTIVE(110),

	/** The deactivated. */
	DEACTIVATED(111),

	/** The maintenance. */
	MAINTENANCE(112),

	/** The page size list. */
	PAGE_SIZE_LIST(113),

	/** The edit eco mode. */
	ECOMODE(114),

	/** The polygon dialog. */
	SHOW_DIALOG_POLYGON(115),

	EMPRESA_ID(116);

	/** The property Id. */
	private Integer propertyId;

	/**
	 * Instantiates a new property enum.
	 *
	 * @param propertyIdParam the property id param
	 */
	private PropertyEnum(Integer propertyIdParam)
	{
		propertyId = propertyIdParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return propertyId;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the property enum
	 */
	public static PropertyEnum enumForValue(Integer value)
	{
		for (PropertyEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values.
	 *
	 * @return the valid values
	 */
	public static String getValidValues()
	{
		PropertyEnum[] enums = PropertyEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (PropertyEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
