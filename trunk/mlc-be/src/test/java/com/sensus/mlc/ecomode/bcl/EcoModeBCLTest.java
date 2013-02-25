package com.sensus.mlc.ecomode.bcl;

import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.mlc.base.TestBaseUtil.createEcoModeBaseline;
import static com.sensus.mlc.base.TestBaseUtil.createInquiryEcoModeRequest;
import static com.sensus.mlc.base.TestBaseUtil.createTag;
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
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.CSVDataSource;
import com.sensus.common.util.CSVFileGenerator;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.ecomode.dac.IEcoModeDAC;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.tag.bcl.ITagBCL;

/**
 * The Class EcoModeBCLTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/ecomode/ecomodebclimpltest.xml"})
public class EcoModeBCLTest extends AbstractTestBaseBusiness
{
	/** The Constant TEN. */
	private static final int TEN = 10;

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
		EcoModeBaseline baseline = createEcoModeBaseline();

		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.addEcoModeBaseline(baseline);

		InternalResultsResponse<EcoModeBaseline> response = getEcoModeBCL().upsertEcoMode(ecoModeRequest);
		assertResultResponse(response);
		this.assertMessages(response, SUCCESS_UPD_ECOMODE_BY_POLE_ID);
		assertEcoModeBaseline(response.getResultsList());

		// Errors situations
		// EcoModeDAC
		this.setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IEcoModeDAC.class);
		response = getEcoModeBCL().upsertEcoMode(ecoModeRequest);
		this.assertMessages(response, ERROR_CODE, ERROR_UPD_ECOMODE_BY_POLE_ID);

		this.setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IEcoModeDAC.class, "upsertEcoMode");
		response = getEcoModeBCL().upsertEcoMode(ecoModeRequest);
		this.assertMessages(response, ERROR_CODE, ERROR_UPD_ECOMODE_BY_POLE_ID);
		resetMocksToEcoModeArea();

		// ProcessBCL
		this.setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IProcessBCL.class);
		response = getEcoModeBCL().upsertEcoMode(ecoModeRequest);
		this.assertMessages(response, ERROR_CODE, SUCCESS_UPD_ECOMODE_BY_POLE_ID);
	}

	/**
	 * Test fetch light consumptions by light id.
	 */
	@Test
	public void testFetchLightConsumptionsByLightId()
	{
		// Success situation
		InquiryEcoModeRequest ecoModeRequest = setDataToFetchLightConsumptions();
		InternalResultsResponse<LightConsumption> response =
				getEcoModeBCL().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertResultResponse(response);
		assertLightConsumption(response.getResultsList());

		// Errors situations
		this.setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IEcoModeDAC.class);
		response = getEcoModeBCL().fetchLightConsumptionsByLightId(ecoModeRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch light consumptions to chart.
	 */
	@Test
	public void testFetchLightConsumptionsToChart()
	{
		// Success situations
		InquiryEcoModeRequest ecoModeRequest = setDataToFetchLightConsumptions();
		InternalResultsResponse<LightConsumption> response =
				getEcoModeBCL().fetchLightConsumptionsToChart(ecoModeRequest);
		assertResultResponse(response);
		assertLightConsumption(response.getResultsList());

		// Errors situations
		this.setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IEcoModeDAC.class);
		response = getEcoModeBCL().fetchLightConsumptionsToChart(ecoModeRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSV()
	{
		// Success situation
		InquiryEcoModeRequest ecoModeRequest = setDataToFetchLightConsumptions();
		InquiryEcoModeResponse response = getEcoModeBCL().generateFileCSV(ecoModeRequest);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getFileName());

		// Errors situations
		// IEcoModeDAC
		this.setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IEcoModeDAC.class);
		response = getEcoModeBCL().generateFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		InternalResponse internalResponse = new InternalResponse();
		internalResponse.addMessages(response.getMessageInfoList());
		this.assertMessages(internalResponse, ERROR_CODE);
		resetMocksToEcoModeArea();

		this.setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IEcoModeDAC.class, "generateFileCSV");
		response = getEcoModeBCL().generateFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		internalResponse = new InternalResponse();
		internalResponse.addMessages(response.getMessageInfoList());
		this.assertMessages(internalResponse, ERROR_CODE);
		resetMocksToEcoModeArea();

		// IProcessBCL
		this.setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IProcessBCL.class);
		response = getEcoModeBCL().generateFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		internalResponse = new InternalResponse();
		internalResponse.addMessages(response.getMessageInfoList());
		this.assertMessages(internalResponse, ERROR_CODE);
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
		List<EcoModeBaseline> baselineList = new ArrayList<>();

		for (int i = 0; i < amount; i++)
		{
			baselineList.add(createEcoModeBaseline());
		}

		// Success situation
		// Import without tag
		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setEcoModeBaselineList(baselineList);

		File ecoModeCSVImport = File.createTempFile("csvtmp", ".csv");
		ecoModeCSVImport.deleteOnExit();

		EcoModeCSVDataSource ds = new EcoModeCSVDataSource(ecoModeRequest);
		InternalResponse internalResponse = new InternalResponse();
		assertTrue(CSVFileGenerator.create(ecoModeCSVImport.toString(), ds, internalResponse));
		assertResponse(internalResponse);

		ecoModeRequest.getEcoModeBaselineList().clear();
		ecoModeRequest.setEcoModeCSVImport(ecoModeCSVImport);
		internalResponse = getEcoModeBCL().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		this.assertMessages(internalResponse, INCORRECT_IMPORT_ECOMODE, SUCCESS_IMPORT_ECOMODE);

		// Import with tag
		ecoModeRequest.setTags(Arrays.asList(createTag()));
		internalResponse = getEcoModeBCL().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		this.assertMessages(internalResponse, INCORRECT_IMPORT_ECOMODE, SUCCESS_IMPORT_ECOMODE);

		// Errors situations
		// EcoModeDAC
		this.setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IEcoModeDAC.class);
		internalResponse = getEcoModeBCL().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		this.assertMessages(internalResponse, INCORRECT_IMPORT_ECOMODE);
		resetMocksToEcoModeArea();

		// ProcessBCL
		this.setSituation(getEcoModeBCL(), SituationsEnum.ERROR, IProcessBCL.class);
		internalResponse = getEcoModeBCL().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		this.assertMessages(internalResponse, ERROR_CODE, INCORRECT_IMPORT_ECOMODE, SUCCESS_IMPORT_ECOMODE);

		// TagBCL
		this.setSituation(getEcoModeBCL(), SituationsEnum.ERROR, ITagBCL.class);
		internalResponse = getEcoModeBCL().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		this.assertMessages(internalResponse, ERROR_CODE, ERROR_CODE, INCORRECT_IMPORT_ECOMODE, SUCCESS_IMPORT_ECOMODE);
	}

	/**
	 * Assert eco mode baseline.
	 *
	 * @param ecoModeList the eco mode list
	 */
	private void assertEcoModeBaseline(List<EcoModeBaseline> ecoModeList)
	{
		assertNotNull("Eco-Mode list should not be null.", ecoModeList);
		assertTrue("Eco-Mode list should not be empty.", ecoModeList.size() > 0);

		for (EcoModeBaseline ecoModeBaseline : ecoModeList)
		{
			assertNotNull("Light should not be null ", ecoModeBaseline.getLightId());
			assertNotNull("Light eco-mode should not be null ", ecoModeBaseline.getLightId().getEcoMode());
		}
	}

	/**
	 * Assert light consumption.
	 *
	 * @param lightConsumptions the light consumptions
	 */
	private void assertLightConsumption(List<LightConsumption> lightConsumptions)
	{
		assertNotNull("Light consumptions should not be null.", lightConsumptions);
		assertTrue("Light consumptions should not be empty.", lightConsumptions.size() > 0);

		for (LightConsumption lightConsumption : lightConsumptions)
		{
			assertNotNull("Light should not be null", lightConsumption.getLightId());
			assertNotNull("Light eco-mode should not be null", lightConsumption.getLightId().getEcoMode());
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

class EcoModeCSVDataSource extends CSVDataSource<EcoModeBaseline>
{
	private static final String POLE_ID = "Pole id";
	private static final String WATTAGE = "Replaced wattage";
	private static final String LAMP_TYPE = "Replaced type";

	public EcoModeCSVDataSource(InquiryEcoModeRequest request)
	{
		super(request);

		List<EcoModeBaseline> baselineList = request.getEcoModeBaselineList();
		if (isNullOrEmpty(baselineList))
		{
			return;
		}

		addColumns(POLE_ID, WATTAGE, LAMP_TYPE);
		this.addData(baselineList);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.util.CSVDataSource#getDataForColumn(java.lang.Object, int)
	 */
	@Override
	protected String getDataForColumn(EcoModeBaseline baseline, int column)
	{
		switch (column)
		{
			case 0:

				return String.valueOf(baseline.getPoleId());

			case 1:

				return String.valueOf(baseline.getReplacedWattage());

			case 2:

				return String.valueOf(baseline.getReplacedType());

			default:
				return null;
		}
	}
}