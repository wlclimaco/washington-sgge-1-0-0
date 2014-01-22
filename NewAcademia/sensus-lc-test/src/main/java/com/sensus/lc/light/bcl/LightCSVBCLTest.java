package com.sensus.lc.light.bcl;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.dac.ILightDAC;
import com.sensus.lc.light.dac.INotificationHistoryDAC;
import com.sensus.lc.light.model.request.LightCSVRequest;
import com.sensus.lc.light.model.request.LightHistoryCSVRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.LightSummaryCSVRequest;
import com.sensus.lc.light.model.response.CSVInternalResponse;

@ContextConfiguration(locations = {
		"classpath:com/sensus/mlc/light/lightcsvbclimpltest.xml"})
public class LightCSVBCLTest extends AbstractTestBaseBusiness
{

	private static final int ONE = 1;

	/** The Constant TIMEZONE. */
	private static final String TIMEZONE = "America/New_York";

	/** The Constant LANGUAGE. */
	private static final String LANGUAGE = "en_US";

	/** The Constant SENSUS_LC_EXPORT_CSV_NO_FILENAME. */
	private static final String SENSUS_LC_EXPORT_CSV_NO_FILENAME = "sensus.lc.csv.no.filename";

	/** The Constant SENSUS_LC_EXPORT_CSV_NO_COLUMNS. */
	private static final String SENSUS_LC_EXPORT_CSV_NO_COLUMNS = "sensus.lc.csv.no.columns";

	/** The light csvbcl. */
	private ILightCSVBCL lightCSVBCL;

	/**
	 * @return the lightCSVBCL
	 */
	public ILightCSVBCL getLightCSVBCL()
	{
		return lightCSVBCL;
	}

	/**
	 * @param lightCSVBCL the lightCSVBCL to set
	 */
	@Resource(name = "lightCSVBCLTarget")
	public void setLightCSVBCL(ILightCSVBCL lightCSVBCL)
	{
		this.lightCSVBCL = lightCSVBCL;
	}

	@Test
	public void testGenerateLightDetailFileCSV() throws IOException
	{
		UserContext userContext = TestBaseUtil.createUserContext();
		userContext.setLocaleString(LANGUAGE);

		LightCSVRequest request = new LightCSVRequest();
		request.setUserContext(userContext);
		request.setProcessId(ONE);

		LightRequest lightRequest = new LightRequest();
		lightRequest.setUserContext(userContext);
		request.setLightRequest(lightRequest);

		// Columns to export
		List<String> lstCSVColumns = new ArrayList<String>();
		lstCSVColumns.add("LIFECYCLE_STATE");
		lstCSVColumns.add("ALERTS");
		lstCSVColumns.add("ECOMODE");
		lstCSVColumns.add("CITY");

		request.setCsvColumns(lstCSVColumns);

		// Create a temporary file
		File tmp = File.createTempFile("csvtmp", ".csv");

		request.setFileName(tmp.getName());

		CSVInternalResponse response = getLightCSVBCL().generateLightDetailFileCSV(request);
		TestBaseUtil.assertResponse(response);

		// Error situation - without columns to export
		request = new LightCSVRequest();
		lightRequest = new LightRequest();
		lightRequest.setUserContext(userContext);
		request.setLightRequest(lightRequest);

		request.setFileName(tmp.getName());

		response = getLightCSVBCL().generateLightDetailFileCSV(request);
		assertTrue(response.isInError());
		assertMessages(response, SENSUS_LC_EXPORT_CSV_NO_COLUMNS);

		// Error situation - without csv file name
		request = new LightCSVRequest();
		lightRequest = new LightRequest();
		lightRequest.setUserContext(userContext);
		request.setLightRequest(lightRequest);

		// Columns to export
		lstCSVColumns = new ArrayList<String>();
		lstCSVColumns.add("LIFECYCLE_STATE");
		lstCSVColumns.add("ALERTS");
		lstCSVColumns.add("ECOMODE");
		lstCSVColumns.add("CITY");

		request.setCsvColumns(lstCSVColumns);

		response = getLightCSVBCL().generateLightDetailFileCSV(request);
		assertTrue(response.isInError());
		assertMessages(response, SENSUS_LC_EXPORT_CSV_NO_FILENAME);

		// Error situation: in the method "fetchLightsToExport()"
		setSituation(getLightCSVBCL(), SituationsEnum.ERROR, ILightDAC.class,
				"fetchAllByRequest");

		request = new LightCSVRequest();
		lightRequest = new LightRequest();
		lightRequest.setUserContext(userContext);
		request.setLightRequest(lightRequest);

		// Columns to export
		lstCSVColumns = new ArrayList<String>();
		lstCSVColumns.add("LIFECYCLE_STATE");
		lstCSVColumns.add("ALERTS");
		lstCSVColumns.add("ECOMODE");
		lstCSVColumns.add("CITY");

		request.setCsvColumns(lstCSVColumns);
		request.setFileName(tmp.getName());

		response = getLightCSVBCL().generateLightDetailFileCSV(request);
		assertTrue(response.isInError());
	}

	@Test
	public void testGenerateLightSummaryFileCSV() throws IOException
	{
		UserContext userContext = TestBaseUtil.createUserContext();
		userContext.setLocaleString(LANGUAGE);

		// Success situation
		LightSummaryCSVRequest request = new LightSummaryCSVRequest();
		request.setUserContext(userContext);
		request.setProcessId(ONE);

		// Create a temporary file
		File tmp = File.createTempFile("csvtmp", ".csv");

		request.setFileName(tmp.getName());
		request.setLights(Arrays.asList(TestBaseUtil.createLight(userContext)));

		CSVInternalResponse response = getLightCSVBCL().generateLightSummaryFileCSV(request);
		TestBaseUtil.assertResponse(response);

		// Error situation - NO FILE NAME
		request = new LightSummaryCSVRequest();
		request.setUserContext(userContext);

		// Create a temporary file
		request.setLights(Arrays.asList(TestBaseUtil.createLight(userContext)));

		response = getLightCSVBCL().generateLightSummaryFileCSV(request);
		assertTrue(response.isInError());
		assertMessages(response, SENSUS_LC_EXPORT_CSV_NO_FILENAME);
	}

	@Test
	public void testGenerateLightHistoryFileCSV() throws IOException
	{
		UserContext userContext = TestBaseUtil.createUserContext();
		userContext.setLocaleString(LANGUAGE);

		// Success situation
		LightHistoryCSVRequest request = new LightHistoryCSVRequest();
		request.setUserContext(userContext);
		request.setTimezone(TIMEZONE);
		request.setProcessId(ONE);

		// Create a temporary file
		File tmp = File.createTempFile("csvtmp", ".csv");
		request.setFileName(tmp.getName());

		CSVInternalResponse response = getLightCSVBCL().generateLightHistoryFileCSV(request);
		TestBaseUtil.assertResponse(response);

		// Error situation - NO FILE NAME
		request = new LightHistoryCSVRequest();
		request.setUserContext(userContext);
		request.setTimezone(TIMEZONE);

		response = getLightCSVBCL().generateLightHistoryFileCSV(request);
		assertTrue(response.isInError());
		assertMessages(response, SENSUS_LC_EXPORT_CSV_NO_FILENAME);

		// Error situation - Errors on method "fetchLightNotificationHistory"
		setSituation(getLightCSVBCL(), SituationsEnum.ERROR, INotificationHistoryDAC.class,
				"fetchLightNotificationHistory");

		request = new LightHistoryCSVRequest();
		request.setUserContext(userContext);
		request.setTimezone(TIMEZONE);

		// Create a temporary file
		request.setFileName(tmp.getName());

		response = getLightCSVBCL().generateLightHistoryFileCSV(request);
		assertTrue(response.isInError());

	}
}
