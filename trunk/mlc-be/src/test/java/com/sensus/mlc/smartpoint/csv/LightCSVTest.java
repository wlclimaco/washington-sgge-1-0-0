package com.sensus.mlc.smartpoint.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;

import com.sensus.common.util.CSVFileGenerator;
import com.sensus.mlc.base.AbstractTestBaseDAC;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;

/**
 * The Class LightCSVTest.
 */
public class LightCSVTest extends AbstractTestBaseDAC
{

	/** The Constant RNI_ID. */
	private static final String RNI_ID = "rni_id";

	/** The Constant BULB_NUMBER. */
	private static final String BULB_NUMBER = "bulb_number";

	/** The Constant INPUT_VOLTAGE. */
	private static final String INPUT_VOLTAGE = "input_voltage";

	/**
	 * Test csv.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCSV() throws Exception
	{
		List<HashMap<String, String>> lightsToCSV = new ArrayList<HashMap<String, String>>();
		InquiryLightRequest request = new InquiryLightRequest();
		request.setLightsToCSV(lightsToCSV);

		HashMap<String, String> map = new HashMap<String, String>();

		request.getLightsToCSV().add(map);
		request.setListColumn(new ArrayList<Column>());

		request.getListColumn().add(new Column());
		request.getListColumn().add(new Column());
		request.getListColumn().add(new Column());
		request.getListColumn().get(0).setFieldName(RNI_ID);
		request.getListColumn().get(1).setFieldName(BULB_NUMBER);
		request.getListColumn().get(2).setFieldName(INPUT_VOLTAGE);

		map.put(RNI_ID, "COL4");
		map.put(BULB_NUMBER, "COL5");
		map.put(INPUT_VOLTAGE, "COL6");

		File tmp = File.createTempFile("csvtmp", ".csv");
		tmp.deleteOnExit();

		LightMapCSVDataSource ds = new LightMapCSVDataSource(request);

		assertTrue(CSVFileGenerator.create(tmp.toString(), ds, null));

		FileReader fr = new FileReader(tmp);
		CSVReader reader = new CSVReader(fr);
		List<String[]> data = reader.readAll();

		fr.close();
		reader.close();

		// System.out.println(FileUtils.readFileToString(tmp));

		// check dimensions...
		assertEquals(2, data.size());
		assertEquals(ds.getNumColumns(), data.get(0).length);

		assertEquals("COL1", data.get(1)[0]);
		assertEquals("COL2", data.get(1)[1]);
		assertEquals("COL3", data.get(1)[2]);
	}
}