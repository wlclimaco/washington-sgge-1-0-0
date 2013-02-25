package com.sensus.mlc.ecomode.csv;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.mlc.base.util.LCConvertUtil.convertWattageToKWattage;
import static com.sensus.mlc.base.util.LCDateUtil.DEFAULT_DATE_FORMAT;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sensus.common.util.CSVDataSource;
import com.sensus.common.util.SensusConvertUtil;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class EcoModeCSVDataSource.
 */
public class EcoModeCSVDataSource extends CSVDataSource<LightConsumption>
{
	/** The Constant DATE. */
	private static final String DATE = "Date";

	/** The Constant MEASURED_CONSUMPTION. */
	private static final String MEASURED_CONSUMPTION = "Measured Consumption (kWh)";

	/** The Constant ECOMODE. */
	private static final String ECOMODE = "Eco-Mode";

	/** The Constant COLUMN_0. */
	private static final int COLUMN_0 = 0;

	/** The Constant COLUMN_1. */
	private static final int COLUMN_1 = 1;

	/** The Constant COLUMN_2. */
	private static final int COLUMN_2 = 2;

	/** The Constant COLUMN_3. */
	private static final int COLUMN_3 = 3;

	/** The baseline. */
	private static String BASELINE = "Baseline %s Watt %s in (kWh)";

	/** The undefined. */
	private static String UNDEFINED = "--";

	/** The timezone. */
	private String datePattern = DEFAULT_DATE_FORMAT;

	/** The eco mode. */
	private Double ecoMode;

	/**
	 * Eco mode.
	 *
	 * @return the double
	 */
	protected Double ecoMode()
	{
		return ecoMode;
	}

	/**
	 * Sets the eco mode.
	 *
	 * @param ecoMode the new eco mode
	 */
	protected void setEcoMode(Double ecoMode)
	{
		this.ecoMode = ecoMode;
	}

	/**
	 * Instantiates a new eco mode csv data source.
	 *
	 * @param request the request
	 */
	public EcoModeCSVDataSource(InquiryEcoModeRequest request)
	{
		super(request);
		List<LightConsumption> lightConsumptions = request.getLightConsumptions();
		if (isNullOrEmpty(lightConsumptions))
		{
			addColumns(DATE, MEASURED_CONSUMPTION, getBaselineTitle(null), ECOMODE);
			return;
		}

		// Get one light to set title



		this.addData(lightConsumptions);
		setDatePattern(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.util.CSVDataSource#getDataForColumn(java.lang.Object, int)
	 */
	@Override
	protected String getDataForColumn(LightConsumption consumption, int column)
	{
		switch (column)
		{
			case COLUMN_0:

				return String.valueOf(SensusConvertUtil.toDateString(consumption.getDay(), datePattern));

			case COLUMN_1:

				if (!isNull(consumption.getConsumption()))
				{
					return String.valueOf(convertWattageToKWattage(consumption.getConsumption()));
				}
				return UNDEFINED;

			case COLUMN_2:

				if (!isNull(consumption.getEcomodeBaseline()))
				{
					return String.valueOf(convertWattageToKWattage(consumption.getEcomodeBaseline()));
				}
				return UNDEFINED;

			case COLUMN_3:

				if (!isNull(consumption) && !isNull(consumption.getEcoMode()))
				{
					BigDecimal ecoModeNumber = new BigDecimal(consumption.getEcoMode());
					return ecoModeNumber.setScale(0, RoundingMode.HALF_EVEN).intValue() + "%";
				}
				return UNDEFINED;

			default:
				return null;
		}
	}

	/**
	 * Sets the date pattern.
	 *
	 * @param request the new date pattern
	 */
	private void setDatePattern(InquiryEcoModeRequest request)
	{
		if (!isNull(request.getDatePattern()))
		{
			datePattern = StringUtils.replace(request.getDatePattern(), "mm", "MM");
		}
	}

	/**
	 * Gets the baseline title.
	 *
	 * @param light the light
	 * @return the baseline title
	 */
	private String getBaselineTitle(Light light)
	{
		String lightType = UNDEFINED;
		String lightConsumption = UNDEFINED;

		if (!isNull(light) && !isNull(light.getEcoModeBaseline()))
		{
			EcoModeBaseline ecoModeBaseline = light.getEcoModeBaseline();
			lightType = ecoModeBaseline.getReplacedType().name();
			lightConsumption = String.valueOf(ecoModeBaseline.getReplacedWattage());
		}

		return String.format(BASELINE, lightConsumption, lightType);
	}
}
