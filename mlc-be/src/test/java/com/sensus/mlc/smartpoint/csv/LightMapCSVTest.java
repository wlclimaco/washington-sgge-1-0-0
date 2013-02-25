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
 * The Class LightMapCSVTest.
 */
public class LightMapCSVTest extends AbstractTestBaseDAC
{

	/** The Constant ECO_MODE. */
	private static final String ECO_MODE = "eco_mode";

	/** The Constant RNI_ID. */
	private static final String RNI_ID = "rni_id";

	/**
	 * Test csv.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCSV() throws Exception
	{
		InquiryLightRequest request = new InquiryLightRequest();
		List<Column> list = new ArrayList<Column>();
		request.setListColumn(list);
		list.add(new Column(RNI_ID));
		list.add(new Column(ECO_MODE));

		List<HashMap<String, String>> lightsToCSV = new ArrayList<>();
		request.setLightsToCSV(lightsToCSV);

		HashMap<String, String> lightMap = new HashMap<>();
		lightMap.put(ECO_MODE, "61.0");
		lightMap.put(RNI_ID, "12345678");
		lightsToCSV.add(lightMap);

		File tmp = File.createTempFile("csvtmp", ".csv");
		tmp.deleteOnExit();

		LightMapCSVDataSource ds = new LightMapCSVDataSource(request);
		assertTrue(CSVFileGenerator.create(tmp.toString(), ds, null));

		FileReader fr = new FileReader(tmp);
		CSVReader reader = new CSVReader(fr);
		List<String[]> data = reader.readAll();

		fr.close();
		reader.close();

		// check dimensions...
		assertEquals(lightsToCSV.size() + 1, data.size());
		assertEquals(ds.getNumColumns(), data.get(0).length);

		assertEquals("123456789", data.get(1)[0]);
		assertEquals("60.0", data.get(1)[1]);
	}
}