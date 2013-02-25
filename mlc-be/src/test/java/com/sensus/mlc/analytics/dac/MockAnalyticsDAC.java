package com.sensus.mlc.analytics.dac;

import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;

import java.util.HashMap;
import java.util.Map;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsGroupAlarm;
import com.sensus.mlc.analytics.model.AnalyticsGroupCarbonCredits;
import com.sensus.mlc.analytics.model.AnalyticsGroupColumns;
import com.sensus.mlc.analytics.model.AnalyticsGroupEcoMode;
import com.sensus.mlc.analytics.model.AnalyticsGroupTypeLight;
import com.sensus.mlc.analytics.model.AnalyticsGroupWarning;
import com.sensus.mlc.analytics.model.DashboardViewTypeEnum;
import com.sensus.mlc.analytics.model.AnalyticsTypeEnum;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class MockAnalyticsDAC.
 */
public class MockAnalyticsDAC extends AbstractMockBase implements IAnalyticsDAC
{

	/** The Constant ANALYTICS_GROUP_340_0. */
	private static final double ANALYTICS_GROUP_340_0 = 340.0;

	/** The Constant ANALYTICS_GROUP_100_0. */
	private static final double ANALYTICS_GROUP_100_0 = 100.0;

	/** The Constant ANALYTICS_GROUP_40_0. */
	private static final double ANALYTICS_GROUP_40_0 = 40.0;

	/** The Constant ANALYTICS_GROUP_500_0. */
	private static final double ANALYTICS_GROUP_500_0 = 500.0;

	/** The Constant ANALYTICS_GROUP_300_0. */
	private static final double ANALYTICS_GROUP_300_0 = 300.0;

	/** The Constant ANALYTICS_GROUP_70_0. */
	private static final double ANALYTICS_GROUP_70_0 = 70.0;

	/** The Constant ANALYTICS_GROUP_330_0. */
	private static final double ANALYTICS_GROUP_330_0 = 330.0;

	/** The Constant ANALYTICS_GROUP_220_0. */
	private static final double ANALYTICS_GROUP_220_0 = 220.0;

	/** The Constant ANALYTICS_GROUP_55_0. */
	private static final double ANALYTICS_GROUP_55_0 = 55.0;

	/** The Constant GROUP_7. */
	private static final Integer GROUP_7 = 7;

	/** The Constant GROUP_6. */
	private static final Integer GROUP_6 = 6;

	/** The Constant GROUP_5. */
	private static final Integer GROUP_5 = 5;

	/** The Constant GROUP_4. */
	private static final Integer GROUP_4 = 4;

	/** The Constant GROUP_3. */
	private static final Integer GROUP_3 = 3;

	/** The Constant ANALYTICS_GROUP_16_0. */
	private static final double ANALYTICS_GROUP_16_0 = 16.0;

	/** The Constant ANALYTICS_GROUP_2_0. */
	private static final double ANALYTICS_GROUP_2_0 = 2.0;

	/** The Constant ANALYTICS_GROUP_6_0. */
	private static final double ANALYTICS_GROUP_6_0 = 6.0;

	/** The Constant ANALYTICS_GROUP_22_0. */
	private static final double ANALYTICS_GROUP_22_0 = 22.0;

	/** The Constant ANALYTICS_GROUP_14_0. */
	private static final double ANALYTICS_GROUP_14_0 = 14.0;

	/** The Constant ANALYTICS_GROUP_13_0. */
	private static final double ANALYTICS_GROUP_13_0 = 13.0;

	/** The Constant ANALYTICS_GROUP_12_0. */
	private static final double ANALYTICS_GROUP_12_0 = 12.0;

	/** The Constant ANALYTICS_GROUP_20_0. */
	private static final double ANALYTICS_GROUP_20_0 = 20.0;

	/** The Constant ANALYTICS_GROUP_9_0. */
	private static final double ANALYTICS_GROUP_9_0 = 9.0;

	/** The Constant ANALYTICS_GROUP_21_0. */
	private static final double ANALYTICS_GROUP_21_0 = 21.0;

	/** The Constant ANALYTICS_GROUP_3_0. */
	private static final double ANALYTICS_GROUP_3_0 = 3.0;

	/** The Constant ANALYTICS_GROUP_7_0. */
	private static final double ANALYTICS_GROUP_7_0 = 7.0;

	/** The Constant ANALYTICS_GROUP_11_0. */
	private static final double ANALYTICS_GROUP_11_0 = 11.0;

	/** The Constant ANALYTICS_GROUP_26_0. */
	private static final double ANALYTICS_GROUP_26_0 = 26.0;

	/** The Constant ANALYTICS_GROUP_1_0. */
	private static final double ANALYTICS_GROUP_1_0 = 1.0;

	/** The Constant ANALYTICS_GROUP_15_0. */
	private static final double ANALYTICS_GROUP_15_0 = 15.0;

	/** The Constant ANALYTICS_GROUP_25_0. */
	private static final double ANALYTICS_GROUP_25_0 = 25.0;

	/** The Constant ANALYTICS_GROUP_5_0. */
	private static final double ANALYTICS_GROUP_5_0 = 5.0;

	/** The Constant ANALYTICS_GROUP_10_0. */
	private static final double ANALYTICS_GROUP_10_0 = 10.0;

	/** The Constant ANALYTICS_GROUP_132. */
	private static final Integer ANALYTICS_GROUP_132 = 132;

	/** The Constant ANALYTICS_GROUP_122. */
	private static final Integer ANALYTICS_GROUP_122 = 122;

	/** The Constant ANALYTICS_GROUP_102. */
	private static final Integer ANALYTICS_GROUP_102 = 102;

	/** The Constant ANALYTICS_GROUP_113. */
	private static final Integer ANALYTICS_GROUP_113 = 113;

	/** The Constant ANALYTICS_GROUP_111. */
	private static final Integer ANALYTICS_GROUP_111 = 111;

	/** The Constant ANALYTICS_GROUP_112. */
	private static final Integer ANALYTICS_GROUP_112 = 112;

	/** The Constant ANALYTICS_GROUP_110. */
	private static final Integer ANALYTICS_GROUP_110 = 110;

	/** The Constant ANALYTICS_GROUP_13. */
	private static final Integer ANALYTICS_GROUP_13 = 13;

	/** The Constant ANALYTICS_GROUP_11. */
	private static final Integer ANALYTICS_GROUP_11 = 11;

	/** The Constant ANALYTICS_GROUP_12. */
	private static final Integer ANALYTICS_GROUP_12 = 12;

	/** The Constant ANALYTICS_GROUP_69. */
	private static final Integer ANALYTICS_GROUP_69 = 69;

	/** The Constant ANALYTICS_GROUP_39. */
	private static final Integer ANALYTICS_GROUP_39 = 39;

	/** The Constant ANALYTICS_GROUP_30. */
	private static final Integer ANALYTICS_GROUP_30 = 30;

	/** The Constant ANALYTICS_GROUP_49. */
	private static final Integer ANALYTICS_GROUP_49 = 49;

	/** The Constant ANALYTICS_GROUP_20. */
	private static final Integer ANALYTICS_GROUP_20 = 20;

	/** The Constant ANALYTICS_GROUP_29. */
	private static final Integer ANALYTICS_GROUP_29 = 29;

	/** The Constant ANALYTICS_GROUP_252. */
	private static final Integer ANALYTICS_GROUP_252 = 252;

	/** The Constant ANALYTICS_GROUP_131. */
	private static final Integer ANALYTICS_GROUP_131 = 131;

	/** The Constant ANALYTICS_GROUP_121. */
	private static final Integer ANALYTICS_GROUP_121 = 121;

	/** The Constant ANALYTICS_GROUP_4. */
	private static final Integer ANALYTICS_GROUP_4 = 4;

	/** The Constant ANALYTICS_GROUP_5. */
	private static final Integer ANALYTICS_GROUP_5 = 5;

	/** The Constant ANALYTICS_GROUP_19. */
	private static final Integer ANALYTICS_GROUP_19 = 19;

	/** The Constant ANALYTICS_GROUP_9. */
	private static final Integer ANALYTICS_GROUP_9 = 9;

	/** The Constant ANALYTICS_GROUP_10. */
	private static final Integer ANALYTICS_GROUP_10 = 10;

	/** The Constant ANALYTICS_GROUP_875. */
	private static final Integer ANALYTICS_GROUP_875 = 875;

	/** The Constant ANALYTICS_GROUP_3214. */
	private static final Integer ANALYTICS_GROUP_3214 = 3214;

	/** The Constant ANALYTICS_GROUP_2351. */
	private static final Integer ANALYTICS_GROUP_2351 = 2351;

	/** The Constant ANALYTICS_GROUP_138. */
	private static final Integer ANALYTICS_GROUP_138 = 138;

	/** The Constant ANALYTICS_GROUP_184. */
	private static final Integer ANALYTICS_GROUP_184 = 184;

	/** The Constant ANALYTICS_GROUP_154. */
	private static final Integer ANALYTICS_GROUP_154 = 154;

	/** The Constant ANALYTICS_GROUP_1520. */
	private static final Integer ANALYTICS_GROUP_1520 = 1520;

	/** The Constant ANALYTICS_GROUP_523. */
	private static final Integer ANALYTICS_GROUP_523 = 523;

	/** The Constant ANALYTICS_GROUP_321. */
	private static final Integer ANALYTICS_GROUP_321 = 321;

	/** The Constant ANALYTICS_GROUP_745. */
	private static final Integer ANALYTICS_GROUP_745 = 745;

	/** The Constant SIZE_11. */
	private static final Integer SIZE_11 = 11;

	/** The Constant SIZE_101. */
	private static final Integer SIZE_101 = 101;

	/** The Constant SIZE_10. */
	private static final Integer SIZE_10 = 10;

	/** The Constant SIZE_100. */
	private static final Integer SIZE_100 = 100;

	/** The Constant LIGHT_3. */
	private static final Integer LIGHT_3 = 3;

	/** The Constant INDUCTION. */
	private static final String INDUCTION = "Induction";

	/** The Constant LED. */
	private static final String LED = "LED";

	/** The Constant I5. */
	private static final String I5 = "I5";

	/** The Constant OTHERS. */
	private static final String OTHERS = "Others";

	/** The Constant ALL. */
	private static final String ALL = "All";

	/** The Constant BEAVERTON. */
	private static final String BEAVERTON = "Beaverton";

	/** The Constant BUCKMAN. */
	private static final String BUCKMAN = "Buckman";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAnalyticsAlarmsByStatusId(com.sensus.mlc.analytics.model.request
	 * .AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAnalyticsAlarmsByStatusId(AnalyticsRequest analyticsRequest)
	{

		InternalResultsResponse<Light> irr = new InternalResultsResponse<Light>();
		irr.addResult(new Light(1));
		irr.addResult(new Light(2));
		irr.addResult(new Light(LIGHT_3));

		return irr;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAnalyticsAlertsByType(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<Map<String, Integer>> fetchAnalyticsAlertsByType(
			AnalyticsRequest analyticsRequest)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("1", SIZE_100);
		map.put("2", SIZE_10);
		map.put("6", SIZE_101);
		map.put("7", SIZE_11);

		InternalResultsResponse<Map<String, Integer>> internalResultsResponse =
				new InternalResultsResponse<Map<String, Integer>>();
		internalResultsResponse.getResultsList().add(map);
		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsInstalledByGroup(com.sensus.mlc.analytics.model.request
	 * .InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupTypeLight> fetchAllAnalyticsInstalledByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupTypeLight> response =
				new InternalResultsResponse<AnalyticsGroupTypeLight>();

		AnalyticsGroupTypeLight analyticsGroupTypeLight = new AnalyticsGroupTypeLight();

		analyticsGroupTypeLight.setId(1);
		analyticsGroupTypeLight.setName(BEAVERTON);
		analyticsGroupTypeLight.setInduction(new Double(ANALYTICS_GROUP_745));
		analyticsGroupTypeLight.setLed(new Double(ANALYTICS_GROUP_321));
		analyticsGroupTypeLight.setOther(new Double(ANALYTICS_GROUP_523));
		analyticsGroupTypeLight.setTotal(new Double(ANALYTICS_GROUP_1520));

		response.getResultsList().add(analyticsGroupTypeLight);
		analyticsGroupTypeLight = new AnalyticsGroupTypeLight();

		analyticsGroupTypeLight.setId(2);
		analyticsGroupTypeLight.setName(BUCKMAN);
		analyticsGroupTypeLight.setInduction(new Double(ANALYTICS_GROUP_154));
		analyticsGroupTypeLight.setLed(new Double(ANALYTICS_GROUP_184));
		analyticsGroupTypeLight.setOther(new Double(ANALYTICS_GROUP_138));
		analyticsGroupTypeLight.setTotal(new Double(ANALYTICS_GROUP_2351));

		response.getResultsList().add(analyticsGroupTypeLight);

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsConsumptionByGroup(com.sensus.mlc.analytics.model
	 * .request.InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupTypeLight> fetchAllAnalyticsConsumptionByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupTypeLight> response =
				new InternalResultsResponse<AnalyticsGroupTypeLight>();

		AnalyticsGroupTypeLight analyticsGroupTypeLight = new AnalyticsGroupTypeLight();

		analyticsGroupTypeLight.setId(1);
		analyticsGroupTypeLight.setName(BEAVERTON);
		analyticsGroupTypeLight.setInduction(new Double(ANALYTICS_GROUP_154));
		analyticsGroupTypeLight.setLed(new Double(ANALYTICS_GROUP_184));
		analyticsGroupTypeLight.setOther(new Double(ANALYTICS_GROUP_138));
		analyticsGroupTypeLight.setTotal(new Double(ANALYTICS_GROUP_3214));
		response.getResultsList().add(analyticsGroupTypeLight);

		analyticsGroupTypeLight = new AnalyticsGroupTypeLight();

		analyticsGroupTypeLight.setId(2);
		analyticsGroupTypeLight.setName(BUCKMAN);
		analyticsGroupTypeLight.setInduction(new Double(ANALYTICS_GROUP_154));
		analyticsGroupTypeLight.setLed(new Double(ANALYTICS_GROUP_184));
		analyticsGroupTypeLight.setOther(new Double(ANALYTICS_GROUP_138));
		analyticsGroupTypeLight.setTotal(new Double(ANALYTICS_GROUP_875));
		response.getResultsList().add(analyticsGroupTypeLight);

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsAlarmsByGroup(com.sensus.mlc.analytics.model.request
	 * .InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupAlarm> fetchAllAnalyticsAlarmsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupAlarm> response = new InternalResultsResponse<AnalyticsGroupAlarm>();

		AnalyticsGroupAlarm ag = new AnalyticsGroupAlarm();
		ag.setId(1);
		ag.setName(BEAVERTON);
		ag.setPowerFailure(ANALYTICS_GROUP_10);
		ag.setLampFailure(ANALYTICS_GROUP_9);
		ag.setTotal(ANALYTICS_GROUP_19);
		response.addResult(ag);

		ag = new AnalyticsGroupAlarm();
		ag.setId(1);
		ag.setName(I5);
		ag.setPowerFailure(ANALYTICS_GROUP_5);
		ag.setLampFailure(ANALYTICS_GROUP_4);
		ag.setTotal(ANALYTICS_GROUP_9);
		response.addResult(ag);

		ag = new AnalyticsGroupAlarm();
		ag.setId(1);
		ag.setName("WoodStock1");
		ag.setPowerFailure(ANALYTICS_GROUP_121);
		ag.setLampFailure(ANALYTICS_GROUP_131);
		ag.setTotal(ANALYTICS_GROUP_252);
		response.addResult(ag);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsWarningsByGroup(com.sensus.mlc.analytics.model.request
	 * .InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupWarning> fetchAllAnalyticsWarningsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupWarning> response =
				new InternalResultsResponse<AnalyticsGroupWarning>();

		AnalyticsGroupWarning ag = new AnalyticsGroupWarning();
		ag.setId(1);
		ag.setName(BEAVERTON);
		ag.setBrownoutDetected(ANALYTICS_GROUP_10);
		ag.setPowerSurge(ANALYTICS_GROUP_19);
		ag.setTotal(ANALYTICS_GROUP_29);
		response.addResult(ag);

		ag = new AnalyticsGroupWarning();
		ag.setId(1);
		ag.setName(I5);
		ag.setBrownoutDetected(ANALYTICS_GROUP_20);
		ag.setPowerSurge(ANALYTICS_GROUP_29);
		ag.setTotal(ANALYTICS_GROUP_49);
		response.addResult(ag);

		ag = new AnalyticsGroupWarning();
		ag.setId(1);
		ag.setName("WoodStock");
		ag.setBrownoutDetected(ANALYTICS_GROUP_30);
		ag.setPowerSurge(ANALYTICS_GROUP_39);
		ag.setTotal(ANALYTICS_GROUP_69);
		response.addResult(ag);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsCarbonCreditsByGroup(com.sensus.mlc.analytics.model
	 * .request.InquiryAnalyticsRequest, java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupCarbonCredits> fetchAllAnalyticsCarbonCreditsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest, Double carbonCreditsFactor, Double barrelsOfOilFactor,
			Double metricOfCOFactor)
	{

		InternalResultsResponse<AnalyticsGroupCarbonCredits> response =
				new InternalResultsResponse<AnalyticsGroupCarbonCredits>();

		AnalyticsGroupCarbonCredits ag = new AnalyticsGroupCarbonCredits();
		ag.setId(1);
		ag.setName(BEAVERTON);
		ag.setCreditsCreated(new Double(ANALYTICS_GROUP_10));
		ag.setEnergySaved(new Double(ANALYTICS_GROUP_12));
		ag.setBarrelsOfOilSaved(new Double(ANALYTICS_GROUP_11));
		ag.setTonsOfCOSaved(new Double(ANALYTICS_GROUP_13));
		response.addResult(ag);

		ag = new AnalyticsGroupCarbonCredits();
		ag.setId(2);
		ag.setName(I5);
		ag.setCreditsCreated(new Double(ANALYTICS_GROUP_110));
		ag.setEnergySaved(new Double(ANALYTICS_GROUP_112));
		ag.setBarrelsOfOilSaved(new Double(ANALYTICS_GROUP_111));
		ag.setTonsOfCOSaved(new Double(ANALYTICS_GROUP_113));

		response.addResult(ag);

		ag = new AnalyticsGroupCarbonCredits();
		ag.setId(1);
		ag.setName("Woodstock");
		ag.setCreditsCreated(new Double(ANALYTICS_GROUP_102));
		ag.setEnergySaved(new Double(ANALYTICS_GROUP_122));
		ag.setBarrelsOfOilSaved(new Double(ANALYTICS_GROUP_112));
		ag.setTonsOfCOSaved(new Double(ANALYTICS_GROUP_132));

		response.addResult(ag);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsInstalledByDate(com.sensus.mlc.analytics.model.request
	 * .AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsInstalledByDate(
			AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response =
				new InternalResultsResponse<AnalyticsGroupColumns>();

		response.addResult(new AnalyticsGroupColumns(INDUCTION, ANALYTICS_GROUP_10_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(LED, ANALYTICS_GROUP_5_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(OTHERS, ANALYTICS_GROUP_10_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(ALL, ANALYTICS_GROUP_25_0, getNewDateUTC()));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsAlarmsByDate(com.sensus.mlc.analytics.model.request
	 * .AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsAlarmsByDate(
			AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response =
				new InternalResultsResponse<AnalyticsGroupColumns>();

		response.addResult(new AnalyticsGroupColumns(INDUCTION, ANALYTICS_GROUP_10_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(LED, ANALYTICS_GROUP_15_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(OTHERS, ANALYTICS_GROUP_1_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(ALL, ANALYTICS_GROUP_26_0, getNewDateUTC()));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsWarningsByDate(com.sensus.mlc.analytics.model.request
	 * .AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsWarningsByDate(
			AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response =
				new InternalResultsResponse<AnalyticsGroupColumns>();

		response.addResult(new AnalyticsGroupColumns(INDUCTION, ANALYTICS_GROUP_11_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(LED, ANALYTICS_GROUP_7_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(OTHERS, ANALYTICS_GROUP_3_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(ALL, ANALYTICS_GROUP_21_0, getNewDateUTC()));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsConsumptionByDate(com.sensus.mlc.analytics.model.
	 * request.AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsConsumptionByDate(
			AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response =
				new InternalResultsResponse<AnalyticsGroupColumns>();

		response.addResult(new AnalyticsGroupColumns(INDUCTION, ANALYTICS_GROUP_9_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(LED, ANALYTICS_GROUP_1_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(OTHERS, ANALYTICS_GROUP_10_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(ALL, ANALYTICS_GROUP_20_0, getNewDateUTC()));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsCarbonCreditsByDate(com.sensus.mlc.analytics.model
	 * .request.AnalyticsRequest, java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsCarbonCreditsByDate(
			AnalyticsRequest analyticsRequest, Double carbonCreditsFactor, Double barrelsOfOilFactor,
			Double metricOfCOFactor)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response =
				new InternalResultsResponse<AnalyticsGroupColumns>();

		response.addResult(new AnalyticsGroupColumns(INDUCTION, ANALYTICS_GROUP_9_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(LED, ANALYTICS_GROUP_1_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(OTHERS, ANALYTICS_GROUP_10_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns(ALL, ANALYTICS_GROUP_20_0, getNewDateUTC()));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchDashboarHeader(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest, java.lang.Double)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchDashboarHeader(AnalyticsRequest analyticsRequest,
			Double carbonCreditFactor)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response =
				new InternalResultsResponse<AnalyticsGroupColumns>();

		response.addResult(new AnalyticsGroupColumns("Total Installed", ANALYTICS_GROUP_10_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns("Total Alarms", ANALYTICS_GROUP_11_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns("Total Warnings", ANALYTICS_GROUP_12_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns("Total Consumption", ANALYTICS_GROUP_13_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns("Total Energy Savings", ANALYTICS_GROUP_14_0, getNewDateUTC()));
		response.addResult(new AnalyticsGroupColumns("Total Carbon Credits", ANALYTICS_GROUP_15_0, getNewDateUTC()));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchDashboardResume(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest, java.lang.Double)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchDashboardResume(AnalyticsRequest analyticsRequest,
			Double metricOfCOFactor)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response =
				new InternalResultsResponse<AnalyticsGroupColumns>();

		response.addResult(new AnalyticsGroupColumns(AnalyticsTypeEnum.LIGHTING_ALARM, ANALYTICS_GROUP_10_0, ANALYTICS_GROUP_12_0, ANALYTICS_GROUP_12_0,
				DashboardViewTypeEnum.TODAY));
		response.addResult(new AnalyticsGroupColumns(AnalyticsTypeEnum.LIGHTING_WARNING, ANALYTICS_GROUP_12_0, ANALYTICS_GROUP_22_0, ANALYTICS_GROUP_10_0,
				DashboardViewTypeEnum.MONTH));
		response.addResult(new AnalyticsGroupColumns(AnalyticsTypeEnum.LIGHTING_INSTALLED, ANALYTICS_GROUP_13_0, ANALYTICS_GROUP_13_0, ANALYTICS_GROUP_20_0,
				DashboardViewTypeEnum.WEEK));
		response.addResult(new AnalyticsGroupColumns(AnalyticsTypeEnum.ECOMODE_CONSUMPTION, ANALYTICS_GROUP_14_0, ANALYTICS_GROUP_12_0, ANALYTICS_GROUP_11_0,
				DashboardViewTypeEnum.TODAY));
		response.addResult(new AnalyticsGroupColumns(AnalyticsTypeEnum.LIGHTING_ECOMODE, ANALYTICS_GROUP_11_0, ANALYTICS_GROUP_1_0, ANALYTICS_GROUP_6_0,
				DashboardViewTypeEnum.MONTH));
		response.addResult(new AnalyticsGroupColumns(AnalyticsTypeEnum.ECOMODE_CARBON_CREDITS, ANALYTICS_GROUP_12_0, ANALYTICS_GROUP_2_0, ANALYTICS_GROUP_16_0,
				DashboardViewTypeEnum.WEEK));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.dao.IAnalyticsDAO#generateFileCSV(com.sensus.mlc.analytics.model.request.
	 * InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResponse generateFileCSV(InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = generateFileCSVFromAnalytics(inquiryAnalyticsRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dao.IAnalyticsDAO#fetchAllAnalyticsGroup(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroup> fetchAllAnalyticsGroup(AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroup> response = new InternalResultsResponse<AnalyticsGroup>();

		AnalyticsGroup group = new AnalyticsGroup();
		group.setId(2);
		group.setName(BEAVERTON);
		response.addResult(group);

		group = new AnalyticsGroup();
		group.setId(GROUP_3);
		group.setName(BUCKMAN);
		response.addResult(group);

		group = new AnalyticsGroup();
		group.setId(GROUP_4);
		group.setName("Downtown");
		response.addResult(group);

		group = new AnalyticsGroup();
		group.setId(GROUP_5);
		group.setName("Concordia");
		response.addResult(group);

		group = new AnalyticsGroup();
		group.setId(GROUP_6);
		group.setName("Cully");
		response.addResult(group);

		group = new AnalyticsGroup();
		group.setId(GROUP_7);
		group.setName("Kully");
		response.addResult(group);

		return response;
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsEcoModeGroup(com.sensus.mlc.analytics.model.request
	 * .InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupEcoMode> fetchAllAnalyticsEcoModeGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupEcoMode> response =
				new InternalResultsResponse<AnalyticsGroupEcoMode>();

		response.addResult(new AnalyticsGroupEcoMode(1, "Eco-Mode Percent", ANALYTICS_GROUP_55_0, ANALYTICS_GROUP_220_0, ANALYTICS_GROUP_330_0));
		response.addResult(new AnalyticsGroupEcoMode(1, "Eco-Mode Measured", ANALYTICS_GROUP_70_0, ANALYTICS_GROUP_300_0, ANALYTICS_GROUP_500_0));
		response.addResult(new AnalyticsGroupEcoMode(1, "Eco-Mode Baseline", ANALYTICS_GROUP_40_0, ANALYTICS_GROUP_100_0, ANALYTICS_GROUP_220_0));

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsEcoModeByDate(com.sensus.mlc.analytics.model.request.AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsEcoModeByDate(
			AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response =
				new InternalResultsResponse<AnalyticsGroupColumns>();

		response.addResult(new AnalyticsGroupColumns("Measured Consumption", ANALYTICS_GROUP_340_0));
		response.addResult(new AnalyticsGroupColumns("Baseline Consumption", ANALYTICS_GROUP_220_0));
		return response;
	}

	/**
	 * Generate file csv from analytics.
	 *
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @param response the response
	 * @return the internal response
	 */
	private InternalResponse generateFileCSVFromAnalytics(
			InquiryAnalyticsRequest inquiryAnalyticsRequest,
			InternalResponse response)
	{
		if (getAreaEnum() != LCAreaEnum.ANALYTICS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			inquiryAnalyticsRequest.setFileName(TestBaseUtil.FILE_NAME);
			return getInternalResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.analytics.dac.IAnalyticsDAC#calculateDashboardResume(java.lang.Double, com.sensus.mlc.tenant.model.Tenant, com.sensus.mlc.analytics.model.AnalyticsGroup)
	 */
	@Override
	public InternalResultsResponse<String> calculateDashboardResume(Double carbonCreditsFactor, Tenant tenant,
			AnalyticsGroup analyticsGroup)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllGroupsByTenantForDashboard(com.sensus.mlc.tenant.model.Tenant)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroup> fetchAllGroupsByTenantForDashboard(Tenant tenant)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.analytics.dac.IAnalyticsDAC#deleteDashboarResumeByTenant(com.sensus.mlc.tenant.model.Tenant)
	 */
	@Override
	public InternalResponse deleteDashboarResumeByTenant(Tenant tenant)
	{
		// TODO Auto-generated method stub
		return null;
	}

}