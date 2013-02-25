package com.sensus.mlc.ecomode.bcf;

import static com.sensus.mlc.base.TestBaseUtil.createEcoModeBaseline;
import static com.sensus.mlc.base.TestBaseUtil.createInquiryEcoModeRequest;
import static com.sensus.mlc.base.TestBaseUtil.createLight;
import static com.sensus.mlc.base.TestBaseUtil.createProcessRequest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.util.SensusDateUtil;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.ecomode.bcl.IEcoModeBCL;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class EcoModeBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/ecomode/ecomodebcfimpltest.xml"})
public class EcoModeBCFTest extends AbstractTestBaseBusiness
{
	/** The Constant NINE_FOUR_TIMES. */
	private static final int NINE_FOUR_TIMES = 9999;

	/** The Constant SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION. */
	private static final String SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION =
			"sensus.mlc.ecomodebclimpl.defaultexception";

	/** The Constant SENSUS_MLC_CSVFILEVALIDATOR_FILE_REQUIRED. */
	private static final String SENSUS_MLC_CSVFILEVALIDATOR_FILE_REQUIRED =
			"sensus.mlc.csvfilevalidator.file.required";

	/** The Constant SENSUS_MLC_VALIDATOR_POLEID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_POLEID_REQUIRED =
			"sensus.mlc.validator.poleid.required";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED =
			"sensus.mlc.ecomodevalidator.replacedtype.required";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED =
			"sensus.mlc.ecomodevalidator.replacedwattage.required";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_INVALID. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_INVALID =
			"sensus.mlc.ecomodevalidator.replacedwattage.invalid";

	/** The Constant SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED =
			"sensus.mlc.validator.selection.pagination.id.required";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED =
			"sensus.mlc.ecomodevalidator.rangedate.required";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_INVALID. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_INVALID =
			"sensus.mlc.ecomodevalidator.rangedate.invalid";

	/** The Constant SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED = "sensus.mlc.page.size.required";

	/** The eco mode bcf. */
	private IEcoModeBCF ecoModeBCF;

	/**
	 * Gets the eco mode bcf.
	 * 
	 * @return the eco mode bcf
	 */
	public IEcoModeBCF getEcoModeBCF()
	{
		return this.ecoModeBCF;
	}

	/**
	 * Sets the eco mode bcf.
	 * 
	 * @param ecoModeBCF the new eco mode bcf
	 */
	@Resource(name = "ecoModeBCFTarget")
	public void setEcoModeBCF(IEcoModeBCF ecoModeBCF)
	{
		this.ecoModeBCF = ecoModeBCF;
	}

	/**
	 * Sets the eco mode area.
	 */
	public void setEcoModeArea()
	{
		setArea(getEcoModeBCF(), LCAreaEnum.ECOMODE);
	}

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
		resetMocksData(getEcoModeBCF());
		setEcoModeArea();
	}

	@Test
	public void testUpsertEcoMode()
	{
		// Success situation
		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.addEcoModeBaseline(createEcoModeBaseline());
		InquiryEcoModeResponse response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertTrue(response.isOperationSuccess());
		assertMessages(response, SUCCESS_UPD_ECOMODE_BY_POLE_ID);

		// Error situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.ERROR, IEcoModeBCL.class);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.addEcoModeBaseline(createEcoModeBaseline());
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.EXCEPTION, IEcoModeBCL.class);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.addEcoModeBaseline(createEcoModeBaseline());
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION);

		// Validation situation
		resetMocksToEcoModeArea();

		// EcoModeBaseline empty
		ecoModeRequest = createInquiryEcoModeRequest();
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response,
				SENSUS_MLC_VALIDATOR_POLEID_REQUIRED,
				SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED,
				SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED);

		// EcoModeBaseline - poleId empty
		EcoModeBaseline baseline = createEcoModeBaseline();
		baseline.getLightId().setPoleId(null);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.addEcoModeBaseline(baseline);
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_VALIDATOR_POLEID_REQUIRED);

		baseline.setLightId(null);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.addEcoModeBaseline(baseline);
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_VALIDATOR_POLEID_REQUIRED);

		// EcoModeBaseline - replacedType empty
		baseline = createEcoModeBaseline();
		baseline.setReplacedType(null);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.addEcoModeBaseline(baseline);
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED);

		// EcoModeBaseline - replacedWattage empty
		baseline = createEcoModeBaseline();
		baseline.setReplacedWattage(null);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.addEcoModeBaseline(baseline);
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED);

		// EcoModeBaseline - replacedWattage less that 0
		baseline = createEcoModeBaseline();
		baseline.setReplacedWattage(-1D);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.addEcoModeBaseline(baseline);
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_INVALID);

		// Test user context and tenant
		ecoModeRequest = new InquiryEcoModeRequest();
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);
	}

	@Test
	public void testFetchLightConsumptionsByLightId()
	{
		// Success situation
		Light light = createLight();
		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		InquiryEcoModeResponse response = getEcoModeBCF().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getLightConsumptions());
		assertTrue(response.getLightConsumptions().size() > 0);

		// Error situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.ERROR, IEcoModeBCL.class);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		response = getEcoModeBCF().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.EXCEPTION, IEcoModeBCL.class);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		response = getEcoModeBCF().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION);

		// Validation situation
		resetMocksToEcoModeArea();

		// Values empty
		ecoModeRequest = createInquiryEcoModeRequest();
		response = getEcoModeBCF().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		response = getEcoModeBCF().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED);

		// lightId empty
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		response = getEcoModeBCF().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		// range date empty
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		response = getEcoModeBCF().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED);

		// range date invalid
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMaxDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMinDate());
		response = getEcoModeBCF().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_INVALID);

		// Test user context and tenant
		ecoModeRequest = new InquiryEcoModeRequest();
		response = getEcoModeBCF().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test page size
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setPageSize(NINE_FOUR_TIMES);
		response = getEcoModeBCF().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED);
	}

	@Test
	public void testGenerateFileCSV()
	{
		// Success situation
		Light light = createLight();
		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		InquiryEcoModeResponse response = getEcoModeBCF().generateFileCSV(ecoModeRequest);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getLightConsumptions());
		assertTrue(response.getLightConsumptions().size() > 0);

		// Error situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.ERROR, IEcoModeBCL.class);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		response = getEcoModeBCF().generateFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.EXCEPTION, IEcoModeBCL.class);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		response = getEcoModeBCF().generateFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION);

		// Validation situation
		resetMocksToEcoModeArea();

		// Values empty
		ecoModeRequest = createInquiryEcoModeRequest();
		response = getEcoModeBCF().generateFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		response = getEcoModeBCF().generateFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED);

		// lightId empty
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		response = getEcoModeBCF().generateFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		// range date empty
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		response = getEcoModeBCF().generateFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED);

		// range date invalid
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMaxDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMinDate());
		response = getEcoModeBCF().generateFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_INVALID);

		// Test user context and tenant
		ecoModeRequest = new InquiryEcoModeRequest();
		response = getEcoModeBCF().generateFileCSV(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test page size
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setPageSize(NINE_FOUR_TIMES);
		response = getEcoModeBCF().generateFileCSV(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED);
	}

	@Test
	public void testInsertCSVProcess()
	{
		// Success situation
		ProcessRequest processRequest = createProcessRequest();
		ProcessResponse response = getEcoModeBCF().insertCSVProcess(processRequest);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getProcesses());
		assertTrue(response.getProcesses().size() > 0);

		// Error situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.ERROR, IProcessBCF.class);
		processRequest = createProcessRequest();
		response = getEcoModeBCF().insertCSVProcess(processRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.EXCEPTION, IProcessBCF.class);
		processRequest = createProcessRequest();
		response = getEcoModeBCF().insertCSVProcess(processRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION);
	}

	@Test
	public void testImportEcoModeBaselineFromFileCSV() throws IOException
	{
		File file = File.createTempFile("csvtmp", ".csv");
		file.deleteOnExit();

		// Success situation
		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setEcoModeCSVImport(file);
		InquiryEcoModeResponse response = getEcoModeBCF().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.ERROR, IEcoModeBCL.class);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setEcoModeCSVImport(file);
		response = getEcoModeBCF().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.EXCEPTION, IEcoModeBCL.class);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setEcoModeCSVImport(file);
		response = getEcoModeBCF().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION);

		// Validation situation
		resetMocksToEcoModeArea();
		ecoModeRequest = createInquiryEcoModeRequest();
		response = getEcoModeBCF().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_CSVFILEVALIDATOR_FILE_REQUIRED);

		// Test user context and tenant
		ecoModeRequest = new InquiryEcoModeRequest();
		response = getEcoModeBCF().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);
	}

	@Test
	public void testFetchLightConsumptionsToChart()
	{
		// Success situation
		Light light = createLight();
		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		InquiryEcoModeResponse response = getEcoModeBCF().fetchLightConsumptionsToChart(ecoModeRequest);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getLightConsumptions());
		assertTrue(!response.getLightConsumptions().isEmpty());

		// Error situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.ERROR, IEcoModeBCL.class);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		response = getEcoModeBCF().fetchLightConsumptionsToChart(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.EXCEPTION, IEcoModeBCL.class);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		response = getEcoModeBCF().fetchLightConsumptionsToChart(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION);

		// Validation situation
		resetMocksToEcoModeArea();

		// Values empty
		ecoModeRequest = createInquiryEcoModeRequest();
		response = getEcoModeBCF().fetchLightConsumptionsToChart(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		response = getEcoModeBCF().fetchLightConsumptionsToChart(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED);

		// lightId empty
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		response = getEcoModeBCF().fetchLightConsumptionsToChart(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		// range date empty
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		response = getEcoModeBCF().fetchLightConsumptionsToChart(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED);

		// range date invalid
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMaxDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMinDate());
		response = getEcoModeBCF().fetchLightConsumptionsToChart(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_INVALID);

		// Test user context and tenant
		ecoModeRequest = new InquiryEcoModeRequest();
		response = getEcoModeBCF().fetchLightConsumptionsToChart(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test page size
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setPageSize(NINE_FOUR_TIMES);
		response = getEcoModeBCF().fetchLightConsumptionsToChart(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED);
	}
}
