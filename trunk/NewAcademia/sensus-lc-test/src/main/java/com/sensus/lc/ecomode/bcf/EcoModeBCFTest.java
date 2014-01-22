package com.sensus.lc.ecomode.bcf;

import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.createEcoModeRequest;
import static com.sensus.lc.base.TestBaseUtil.createInquiryEcoModeRequest;
import static com.sensus.lc.base.TestBaseUtil.createInquiryPaginationRequest;
import static com.sensus.lc.base.TestBaseUtil.createLight;
import static com.sensus.lc.base.TestBaseUtil.createLightWithEcoModeBaseline;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.util.CSVDataSource;
import com.sensus.common.util.CSVFileGenerator;
import com.sensus.common.util.SensusDateUtil;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.ecomode.bcl.IEcoModeBCL;
import com.sensus.lc.ecomode.model.request.EcoModeCSVRequest;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.ecomode.model.response.EcoModeResponse;
import com.sensus.lc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.bcf.IProcessBCF;
import com.sensus.lc.process.model.response.ProcessResponse;

/**
 * The Class EcoModeBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/ecomode/ecomodebcfimpltest.xml"})
public class EcoModeBCFTest extends AbstractTestBaseBusiness
{
	/** The Constant FIFTEEN. */
	private static final int FIFTEEN = 15;

	/** The Constant SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION. */
	private static final String SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION =
			"sensus.mlc.ecomodebclimpl.defaultexception";

	/** The Constant SENSUS_MLC_CSVFILEVALIDATOR_FILE_REQUIRED. */
	private static final String SENSUS_MLC_CSVFILEVALIDATOR_FILE_REQUIRED =
			"sensus.mlc.csvfilevalidator.file.required";

	/** The Constant SENSUS_MLC_VALIDATOR_POLEID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_POLEID_REQUIRED =
			"sensus.mlc.validator.poleid.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_ID_REQUIRED =
			"sensus.mlc.lightvalidator.id.required";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED =
			"sensus.mlc.ecomodevalidator.replacedtype.required";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED =
			"sensus.mlc.ecomodevalidator.replacedwattage.required";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_INVALID. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_INVALID =
			"sensus.mlc.ecomodevalidator.replacedwattage.invalid";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED =
			"sensus.mlc.ecomodevalidator.rangedate.required";

	/** The Constant SENSUS_MLC_CSVFILEVALIDATOR_FILE_IS_EMPTY. */
	private static final String SENSUS_MLC_CSVFILEVALIDATOR_FILE_IS_EMPTY =
			"sensus.mlc.csvfilevalidator.file.empty";

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
		return ecoModeBCF;
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
		resetMocksData(getEcoModeBCF());
		setEcoModeArea();
	}

	/**
	 * Test upsert eco mode.
	 */
	@Test
	public void testUpsertEcoMode()
	{
		// Success situation
		EcoModeRequest ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(createLightWithEcoModeBaseline());
		EcoModeResponse response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertTrue(response.isOperationSuccess());
		assertMessages(response, SUCCESS_UPD_ECOMODE_BY_POLE_ID);

		// Error situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.ERROR, IEcoModeBCL.class);
		ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(createLightWithEcoModeBaseline());
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.EXCEPTION, IEcoModeBCL.class);
		ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(createLightWithEcoModeBaseline());
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION);

		// Validation situation
		resetMocksToEcoModeArea();

		// EcoModeBaseline empty
		ecoModeRequest = createEcoModeRequest();
		Light light = createLightWithEcoModeBaseline();
		light.setEcoModeBaseline(null);
		ecoModeRequest.addLight(light);
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response,
				SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED,
				SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED);

		// EcoModeBaseline - poleId empty
		light = createLightWithEcoModeBaseline();
		light.setPoleId(null);
		ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(light);
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_VALIDATOR_POLEID_REQUIRED);

		light.setId(null);
		ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(light);
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response,
				SENSUS_MLC_VALIDATOR_POLEID_REQUIRED,
				SENSUS_MLC_LIGHTVALIDATOR_ID_REQUIRED);

		// EcoModeBaseline - replacedType empty
		light = createLightWithEcoModeBaseline();
		light.getEcoModeBaseline().setReplacedType(null);
		ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(light);
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_TYPE_REQUIRED);

		// EcoModeBaseline - replacedWattage empty
		light = createLightWithEcoModeBaseline();
		light.getEcoModeBaseline().setReplacedWattage(null);
		ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(light);
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_REQUIRED);

		// EcoModeBaseline - replacedWattage less that 0
		light = createLightWithEcoModeBaseline();
		light.getEcoModeBaseline().setReplacedWattage(-1D);
		ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(light);
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_WATTAGE_INVALID);

		// Test user context and tenant
		ecoModeRequest = new EcoModeRequest();
		response = getEcoModeBCF().upsertEcoMode(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);
	}

	/**
	 * Test fetch light consumptions by light id.
	 */
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

		// user context
		ecoModeRequest = new InquiryEcoModeRequest();
		response = getEcoModeBCF().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// range date empty
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setPageSize(FIFTEEN);
		response = getEcoModeBCF().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED);

		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		response = getEcoModeBCF().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertTrue(response.isOperationSuccess());

	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSV()
	{
		// Success situation
		Light light = createLight();
		EcoModeCSVRequest request = new EcoModeCSVRequest();
		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		request.setUserContext(ecoModeRequest.getUserContext());
		request.setInquiryEcoModeRequest(ecoModeRequest);
		CSVResponse response = getEcoModeBCF().generateFileCSV(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToEcoModeArea();
		request = new EcoModeCSVRequest();
		setSituation(getEcoModeBCF(), SituationsEnum.ERROR, IEcoModeBCL.class);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		request.setUserContext(ecoModeRequest.getUserContext());
		request.setInquiryEcoModeRequest(ecoModeRequest);
		response = getEcoModeBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToEcoModeArea();
		request = new EcoModeCSVRequest();
		setSituation(getEcoModeBCF(), SituationsEnum.EXCEPTION, IEcoModeBCL.class);
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		request.setUserContext(ecoModeRequest.getUserContext());
		request.setInquiryEcoModeRequest(ecoModeRequest);
		response = getEcoModeBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION);

		// Validation situation
		resetMocksToEcoModeArea();

		// user context
		request = new EcoModeCSVRequest();
		ecoModeRequest = new InquiryEcoModeRequest();
		request.setInquiryEcoModeRequest(ecoModeRequest);
		response = getEcoModeBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Test page size
		ecoModeRequest.setUserContext(TestBaseUtil.createUserContext());
		request.setUserContext(ecoModeRequest.getUserContext());
		response = getEcoModeBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_REQUIRED);

		// range date empty
		request = new EcoModeCSVRequest();
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setPageSize(FIFTEEN);
		request.setUserContext(ecoModeRequest.getUserContext());
		request.setInquiryEcoModeRequest(ecoModeRequest);
		response = getEcoModeBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED);

		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		request.setInquiryEcoModeRequest(ecoModeRequest);
		response = getEcoModeBCF().generateFileCSV(request);
		assertTrue(response.isOperationSuccess());

	}

	/**
	 * Test insert csv process.
	 */
	@Test
	public void testInsertCSVProcess()
	{
		// Success situation
		InquiryPaginationRequest processRequest = createInquiryPaginationRequest();
		ProcessResponse response = getEcoModeBCF().insertCSVProcess(processRequest);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getProcesses());
		assertTrue(response.getProcesses().size() > 0);

		// Error situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.ERROR, IProcessBCF.class);
		processRequest = createInquiryPaginationRequest();
		response = getEcoModeBCF().insertCSVProcess(processRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.EXCEPTION, IProcessBCF.class);
		processRequest = createInquiryPaginationRequest();
		response = getEcoModeBCF().insertCSVProcess(processRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION);
	}

	/**
	 * Test import eco mode baseline from file csv.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testImportEcoModeBaselineFromFileCSV() throws IOException
	{
		EcoModeRequest ecoModeRequest = createEcoModeRequest();
		File file = File.createTempFile("csvtmp", ".csv");
		file.deleteOnExit();

		EcoModeCSVDataSource ds = new EcoModeCSVDataSource(ecoModeRequest);
		InternalResponse internalResponse = new InternalResponse();
		assertTrue(CSVFileGenerator.create(file.toString(), ds, internalResponse));
		assertResponse(internalResponse);

		// Success situation
		ecoModeRequest.setEcoModeCSVImport(file);
		EcoModeResponse response = getEcoModeBCF().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.ERROR, IEcoModeBCL.class);
		ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.setEcoModeCSVImport(file);
		response = getEcoModeBCF().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToEcoModeArea();
		setSituation(getEcoModeBCF(), SituationsEnum.EXCEPTION, IEcoModeBCL.class);
		ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.setEcoModeCSVImport(file);
		response = getEcoModeBCF().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION);

		// Validation situation
		resetMocksToEcoModeArea();
		ecoModeRequest = createEcoModeRequest();
		response = getEcoModeBCF().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_CSVFILEVALIDATOR_FILE_REQUIRED);

		// Test user context and tenant
		ecoModeRequest = new EcoModeRequest();
		response = getEcoModeBCF().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation
		file = File.createTempFile("csvtmp", ".csv");
		file.deleteOnExit();

		resetMocksToEcoModeArea();
		ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.setEcoModeCSVImport(file);
		response = getEcoModeBCF().importEcoModeBaselineFromFileCSV(ecoModeRequest);
		assertMessages(response, SENSUS_MLC_CSVFILEVALIDATOR_FILE_IS_EMPTY);

	}

	/**
	 * Test fetch light consumptions to chart.
	 */
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

		// user context
		ecoModeRequest = new InquiryEcoModeRequest();
		response = getEcoModeBCF().fetchLightConsumptionsToChart(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// range date empty
		ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setPageSize(FIFTEEN);
		response = getEcoModeBCF().fetchLightConsumptionsToChart(ecoModeRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED);

		ecoModeRequest.setInitialDate(SensusDateUtil.getMinDate());
		ecoModeRequest.setEndDate(SensusDateUtil.getMaxDate());
		response = getEcoModeBCF().fetchLightConsumptionsToChart(ecoModeRequest);
		assertTrue(response.isOperationSuccess());

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
}
