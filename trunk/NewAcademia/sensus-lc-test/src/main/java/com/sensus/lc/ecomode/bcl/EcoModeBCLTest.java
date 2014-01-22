package com.sensus.lc.ecomode.bcl;

import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createEcoModeRequest;
import static com.sensus.lc.base.TestBaseUtil.createInquiryEcoModeRequest;
import static com.sensus.lc.base.TestBaseUtil.createLightWithEcoModeBaseline;
import static com.sensus.lc.base.TestBaseUtil.createTag;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.CSVDataSource;
import com.sensus.common.util.CSVFileGenerator;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.ecomode.dac.IEcoModeDAC;
import com.sensus.lc.ecomode.model.request.EcoModeCSVRequest;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.response.CSVInternalResponse;
import com.sensus.lc.process.bcl.IProcessBCL;
import com.sensus.lc.tag.bcl.ITagBCL;

/**
 * The Class EcoModeBCLTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/ecomode/ecomodebclimpltest.xml"})
public class EcoModeBCLTest extends AbstractTestBaseBusiness
{
	/** The Constant TEN. */
	private static final int TEN = 10;

	/** The Constant TIME_ZONE. */
	private static final String TIME_ZONE = "US/Eastern";

	/** The eco mode bcl. */
	private IEcoModeBCL ecoModeBCL;

	/**
	 * Gets the eco mode bcl.
	 * 
	 * @return the eco mode bcl
	 */
	public IEcoModeBCL getEcoModeBCL()
	{
		return ecoModeBCL;
	}

	/**
	 * Sets the eco mode bcl.
	 * 
	 * @param ecoModeBCL the new eco mode bcl
	 */
	@Resource(name = "ecoModeBCLTarget")
	public void setEcoModeBCL(IEcoModeBCL ecoModeBCL)
	{
		this.ecoModeBCL = ecoModeBCL;
	}

	/**
	 * Sets the eco mode area.
	 */
	public void setEcoModeArea()
	{
		setArea(getEcoModeBCL(), LCAreaEnum.ECOMODE);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setEcoModeArea();
	}

	/**
	 * Removes the eco mode area.
	 */
	@After
	public void resetMocksToEcoModeArea()
	{
		resetMocksData(getEcoModeBCL());
		setEcoModeArea();
	}

	/**
	 * Test upsert eco mode.
	 */
	@Test
	public void testUpsertEcoMode()
	{
		// Success situation
		Light light = createLightWithEcoModeBaseline();

		EcoModeRequest ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(light);

		InternalResultsResponse<Light> response = getEcoModeBCL().upsertEcoMode(ecoModeRequest);
		assertResultResponse(response);
		assertMessages(response, SUCCESS_UPD_ECOMODE_BY_POLE_ID);
		assertEcoModeBaseline(response.getResultsList());

		// Success situation
		light = createLightWithEcoModeBaseline();
		light.getEcoModeBaseline().setCalculateRetroactiveEcomode(true);

		ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(light);

		response = getEcoModeBCL().upsertEcoMode(ecoModeRequest);
		assertResultResponse(response);
		assertMessages(response, SUCCESS_UPD_ECOMODE_BY_POLE_ID);
		assertEcoModeBaseline(response.getResultsList());

		// Errors situations
		// EcoModeDAC
		setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IEcoModeDAC.class);
		response = getEcoModeBCL().upsertEcoMode(ecoModeRequest);
		assertMessages(response, ERROR_CODE, ERROR_UPD_ECOMODE_BY_POLE_ID);

		setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IEcoModeDAC.class, "upsertEcoMode");
		response = getEcoModeBCL().upsertEcoMode(ecoModeRequest);
		assertMessages(response, ERROR_CODE, ERROR_UPD_ECOMODE_BY_POLE_ID);
		resetMocksToEcoModeArea();

		// ProcessBCL
		setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IProcessBCL.class);
		response = getEcoModeBCL().upsertEcoMode(ecoModeRequest);
		assertMessages(response, ERROR_CODE, SUCCESS_UPD_ECOMODE_BY_POLE_ID);
	}

	/**
	 * Test fetch light consumptions by light id.
	 */
	@Test
	public void testFetchLightConsumptionsByLightId()
	{
		// Success situation
		InquiryEcoModeRequest ecoModeRequest = setDataToFetchLightConsumptions();
		InternalResultsResponse<Consumption> response =
				getEcoModeBCL().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertResultResponse(response);
		assertLightConsumption(response.getResultsList());

		// Errors situations
		setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IEcoModeDAC.class);
		response = getEcoModeBCL().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch light consumptions to chart.
	 */
	@Test
	public void testFetchLightConsumptionsToChart()
	{
		// Success situations
		InquiryEcoModeRequest ecoModeRequest = setDataToFetchLightConsumptions();
		InternalResultsResponse<Consumption> response =
				getEcoModeBCL().fetchLightConsumptionsToChart(ecoModeRequest);
		assertResultResponse(response);
		assertLightConsumption(response.getResultsList());

		// Errors situations
		setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IEcoModeDAC.class);
		response = getEcoModeBCL().fetchLightConsumptionsToChart(ecoModeRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSV()
	{
		// Success situation
		EcoModeCSVRequest ecoModeCSVRequest = new EcoModeCSVRequest();
		InquiryEcoModeRequest ecoModeRequest = setDataToFetchLightConsumptions();
		ecoModeCSVRequest.setInquiryEcoModeRequest(ecoModeRequest);
		ecoModeCSVRequest.setUserContext(ecoModeRequest.getUserContext());
		ecoModeCSVRequest.getUserContext().setLocaleString(TIME_ZONE);
		ecoModeCSVRequest.setProcessId(new Integer(1));
		ecoModeCSVRequest.setFileName(System.getProperty("java.io.tmpdir") + LCDateUtil.getNewDateUTC().getTime()
				+ ".csv");
		CSVInternalResponse response = getEcoModeBCL().generateFileCSV(ecoModeCSVRequest);
		assertTrue(response.getStatus().equals(Status.OperationSuccess));
		assertNotNull(response.getFileName());

		// Errors situations
		// IEcoModeDAC
		setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IEcoModeDAC.class);
		response = getEcoModeBCL().generateFileCSV(ecoModeCSVRequest);
		assertFalse(response.getStatus().equals(Status.OperationSuccess));
		InternalResponse internalResponse = new InternalResponse();
		internalResponse.addMessages(response.getMessageInfoList());
		assertMessages(internalResponse, ERROR_CODE);
		resetMocksToEcoModeArea();

	}

	/**
	 * Test import eco mode baseline from file csv.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testImportEcoModeBaselineFromFileCSV() throws IOException
	{
		int amount = TEN;
		List<Light> lights = new ArrayList<>();

		for (int i = 0; i < amount; i++)
		{
			lights.add(createLightWithEcoModeBaseline());
		}

		// Success situation
		// Import without tag
		EcoModeRequest ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.setLights(lights);

		File ecoModeCSVImport = File.createTempFile("csvtmp", ".csv");
		ecoModeCSVImport.deleteOnExit();

		EcoModeCSVDataSource ds = new EcoModeCSVDataSource(ecoModeRequest);
		InternalResponse internalResponse = new InternalResponse();
		assertTrue(CSVFileGenerator.create(ecoModeCSVImport.toString(), ds, internalResponse));
		assertResponse(internalResponse);

		ecoModeRequest.getLights().clear();
		ecoModeRequest.setEcoModeCSVImport(ecoModeCSVImport);
		internalResponse = getEcoModeBCL().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertMessages(internalResponse, INCORRECT_IMPORT_ECOMODE, SUCCESS_IMPORT_ECOMODE);

		// Import with tag
		ecoModeRequest.setTags(Arrays.asList(createTag()));
		internalResponse = getEcoModeBCL().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertMessages(internalResponse, INCORRECT_IMPORT_ECOMODE, SUCCESS_IMPORT_ECOMODE);

		// Errors situations
		// EcoModeDAC
		setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IEcoModeDAC.class);
		internalResponse = getEcoModeBCL().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertMessages(internalResponse, INCORRECT_IMPORT_ECOMODE);
		resetMocksToEcoModeArea();

		// ProcessBCL
		setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IProcessBCL.class);
		internalResponse = getEcoModeBCL().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertMessages(internalResponse, ERROR_CODE, INCORRECT_IMPORT_ECOMODE, SUCCESS_IMPORT_ECOMODE);

		// TagBCL
		setSituation(getEcoModeBCL(), SituationsEnum.ERROR, ITagBCL.class);
		internalResponse = getEcoModeBCL().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertMessages(internalResponse, ERROR_CODE, ERROR_CODE, INCORRECT_IMPORT_ECOMODE, SUCCESS_IMPORT_ECOMODE);
	}

	@Test
	public void testFetchCountAllLightsToCalculateEcoMode()
	{
		InquiryEcoModeRequest request = TestBaseUtil.createInquiryEcoModeRequest();
		Integer result = getEcoModeBCL().fetchCountAllLightsToCalculateEcoMode(request);
		assertNotNull(result);
	}

	@Test
	public void testFetchAllLightsToCalculateEcoMode()
	{
		InquiryEcoModeRequest request = TestBaseUtil.createInquiryEcoModeRequest();
		InternalResultsResponse<Light> response = getEcoModeBCL().fetchAllLightsToCalculateEcoMode(request);
		TestBaseUtil.assertResultResponse(response);
	}

	@Test
	public void testUpdateAnalyticsData()
	{
		getEcoModeBCL().updateAnalyticsData();
	}

	/**
	 * Assert eco mode baseline.
	 * 
	 * @param ecoModeList the eco mode list
	 */
	private void assertEcoModeBaseline(List<Light> lights)
	{
		assertNotNull("Light list should not be null.", lights);
		assertTrue("Light list should not be empty.", lights.size() > 0);

		for (Light light : lights)
		{
			assertNotNull("Light should not be null ", light);
			assertNotNull("Light identifier should not be null ", light.getId());
			assertNotNull("Light eco-mode should not be null ", light.getEcoMode());
		}
	}

	/**
	 * Assert light consumption.
	 * 
	 * @param lightConsumptions the light consumptions
	 */
	private void assertLightConsumption(List<Consumption> lightConsumptions)
	{
		assertNotNull("Light consumptions should not be null.", lightConsumptions);
		assertTrue("Light consumptions should not be empty.", lightConsumptions.size() > 0);

		for (Consumption lightConsumption : lightConsumptions)
		{
			assertNotNull("Consumption day should not be null", lightConsumption.getDay());
			assertNotNull("Consumption should not be null", lightConsumption.getConsumption());
			assertNotNull("Calculated baseline should not be null", lightConsumption.getEcomodeBaseline());
		}
	}

	/**
	 * Sets the data to fetch light consumptions.
	 * 
	 * @return the inquiry eco mode request
	 */
	private InquiryEcoModeRequest setDataToFetchLightConsumptions()
	{
		// Success situation
		int amount = TEN;
		int lightId = 1;

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 0);
		Date endDate = calendar.getTime();

		calendar.set(Calendar.DATE, amount);
		Date initialDate = calendar.getTime();

		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(lightId));
		ecoModeRequest.setInitialDate(initialDate);
		ecoModeRequest.setEndDate(endDate);
		ecoModeRequest.setPageSize(amount);
		return ecoModeRequest;
	}
}

class EcoModeCSVDataSource extends CSVDataSource<Light>
{
	private static final String POLE_ID = "Pole id";
	private static final String WATTAGE = "Replaced wattage";
	private static final String LAMP_TYPE = "Replaced type";

	public EcoModeCSVDataSource(EcoModeRequest request)
	{
		super(request);

		List<Light> lights = request.getLights();
		if (isNullOrEmpty(lights))
		{
			return;
		}

		addColumns(POLE_ID, WATTAGE, LAMP_TYPE);
		addData(lights);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.util.CSVDataSource#getDataForColumn(java.lang.Object, int)
	 */
	@Override
	protected String getDataForColumn(Light light, int column)
	{
		switch (column)
		{
			case 0:

				return String.valueOf(light.getPoleId());

			case 1:

				return String.valueOf(light.getEcoModeBaseline().getReplacedWattage());

			case 2:

				return String.valueOf(light.getEcoModeBaseline().getReplacedType());

			default:
				return null;
		}
	}
}