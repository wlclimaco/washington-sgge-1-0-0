package com.sensus.mlc.analytics.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;

import com.sensus.common.util.CSVFileGenerator;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsGroupColumns;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.base.AbstractTestBaseDAC;

/**
 * The Class AnalyticsGroupCSVTest.
 */
public class AnalyticsGroupCSVTest extends AbstractTestBaseDAC
{

	/** The Constant SIZE_4. */
	private static final Integer SIZE_4 = 4;

	/** The Constant SIZE_3. */
	private static final Integer SIZE_3 = 3;

	/** The Constant ARBITRARY_ANALYTICS_GROUP_8_0. */
	private static final double ARBITRARY_ANALYTICS_GROUP_8_0 = 8.0;

	/** The Constant ARBITRARY_ANALYTICS_GROUP_7_0. */
	private static final double ARBITRARY_ANALYTICS_GROUP_7_0 = 7.0;

	/** The Constant ARBITRARY_ANALYTICS_GROUP_6_0. */
	private static final double ARBITRARY_ANALYTICS_GROUP_6_0 = 6.0;

	/** The Constant ARBITRARY_ANALYTICS_GROUP_5_0. */
	private static final double ARBITRARY_ANALYTICS_GROUP_5_0 = 5.0;

	/** The Constant ARBITRARY_ANALYTICS_GROUP_4_0. */
	private static final double ARBITRARY_ANALYTICS_GROUP_4_0 = 4.0;

	/** The Constant ARBITRARY_ANALYTICS_GROUP_3_0. */
	private static final double ARBITRARY_ANALYTICS_GROUP_3_0 = 3.0;

	/**
	 * Test csv.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCSV() throws Exception
	{
		InquiryAnalyticsRequest request = new InquiryAnalyticsRequest();
		request.setGroups(new ArrayList<AnalyticsGroup>());

		AnalyticsGroup group = new AnalyticsGroup();
		group.setName("NAME1");
		group.setColumns(new ArrayList<AnalyticsGroupColumns>());
		group.getColumns().add(new AnalyticsGroupColumns("COL5", 1.0, null));
		group.getColumns().add(new AnalyticsGroupColumns("COL6", 2.0, null));
		group.getColumns().add(new AnalyticsGroupColumns("COL7", ARBITRARY_ANALYTICS_GROUP_3_0, null));
		group.getColumns().add(new AnalyticsGroupColumns("COL8", ARBITRARY_ANALYTICS_GROUP_4_0, null));
		request.getGroups().add(group);

		group = new AnalyticsGroup();
		group.setName("NAME3");
		group.setColumns(new ArrayList<AnalyticsGroupColumns>());
		group.getColumns().add(new AnalyticsGroupColumns("COL1", ARBITRARY_ANALYTICS_GROUP_5_0, null));
		group.getColumns().add(new AnalyticsGroupColumns("COL2", ARBITRARY_ANALYTICS_GROUP_6_0, null));
		group.getColumns().add(new AnalyticsGroupColumns("COL3", ARBITRARY_ANALYTICS_GROUP_7_0, null));
		group.getColumns().add(new AnalyticsGroupColumns("COL4", ARBITRARY_ANALYTICS_GROUP_8_0, null));
		request.getGroups().add(group);

		File tmp = File.createTempFile("csvtmp", ".csv");
		tmp.deleteOnExit();

		AnalyticsGroupCSVDataSource ds = new AnalyticsGroupCSVDataSource(request);

		assertTrue(CSVFileGenerator.create(tmp.toString(), ds, null));

		FileReader fr = new FileReader(tmp);
		CSVReader reader = new CSVReader(fr);
		List<String[]> data = reader.readAll();

		fr.close();
		reader.close();

		// System.out.println(FileUtils.readFileToString(tmp));

		// check dimensions...
		assertEquals(request.getGroups().size() + 1, data.size());
		assertEquals(ds.getNumColumns(), data.get(0).length);

		assertEquals("NAME2", data.get(2)[0]);
		assertEquals("5.0", data.get(2)[1]);
		assertEquals("6.0", data.get(2)[2]);
		assertEquals("7.0", data.get(2)[SIZE_3]);
		assertEquals("8.0", data.get(2)[SIZE_4]);
	}
}
