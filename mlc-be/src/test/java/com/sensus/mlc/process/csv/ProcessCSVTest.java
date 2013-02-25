package com.sensus.mlc.process.csv;

import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;

import com.sensus.common.util.CSVFileGenerator;
import com.sensus.mlc.base.AbstractTestBaseDAC;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;

/**
 * The Class ProcessCSVTest.
 */
public class ProcessCSVTest extends AbstractTestBaseDAC
{
	/** The Constant ARBITRARY_CREATE_USER. */
	private static final String ARBITRARY_CREATE_USER = "CRUSER";

	/** The Constant ARBITRARY_PROCESS_DESCRIPTION. */
	private static final String ARBITRARY_PROCESS_DESCRIPTION = "DESC5";

	/** The Constant ARBITRARY_ACTION_DESCRIPTION. */
	private static final String ARBITRARY_ACTION_DESCRIPTION = "DESC4";

	/** The Constant COLUMN_0. */
	private static final Integer COLUMN_0 = 0;

	/** The Constant COLUMN_1. */
	private static final Integer COLUMN_1 = 1;

	/** The Constant COLUMN_2. */
	private static final Integer COLUMN_2 = 2;

	/** The Constant COLUMN_6. */
	private static final Integer COLUMN_6 = 6;

	/** The Constant COLUMN_4. */
	private static final Integer COLUMN_4 = 4;

	/** The Constant COLUMN_3. */
	private static final Integer COLUMN_3 = 3;

	/**
	 * Test csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testCSV() throws Exception
	{
		InquiryProcessRequest request = new InquiryProcessRequest();
		List<Process> list = new ArrayList<Process>();

		Process p = new Process();
		p.setId(1);
		p.setLcAction(new LCAction());
		p.getLcAction().setDescription(ARBITRARY_ACTION_DESCRIPTION);
		p.setProcessItems(new ArrayList<ProcessItem>());
		p.setDescription(ARBITRARY_PROCESS_DESCRIPTION);
		p.setCreateUser(ARBITRARY_CREATE_USER);
		p.setStartTime(getNewDateUTC());
		p.setIsProcessComplete(true);
		list.add(p);

		File tmp = File.createTempFile("csvtmp", ".csv");
		tmp.deleteOnExit();

		ProcessCSVDataSource ds = new ProcessCSVDataSource(request, list);

		assertTrue(CSVFileGenerator.create(tmp.toString(), ds, null));

		FileReader fr = new FileReader(tmp);
		CSVReader reader = new CSVReader(fr);
		List<String[]> data = reader.readAll();

		fr.close();
		reader.close();

		// System.out.println(FileUtils.readFileToString(tmp));

		// check dimensions...
		assertEquals(list.size() + 1, data.size());
		assertEquals(ds.getNumColumns(), data.get(0).length);

		assertEquals("1", data.get(1)[COLUMN_0]);
		assertEquals(ARBITRARY_ACTION_DESCRIPTION, data.get(1)[COLUMN_1]);
		assertEquals("0", data.get(1)[COLUMN_2]);
		assertEquals(ARBITRARY_PROCESS_DESCRIPTION, data.get(1)[COLUMN_3]);
		assertEquals(ARBITRARY_CREATE_USER, data.get(1)[COLUMN_4]);
		assertEquals("true", data.get(1)[COLUMN_6]);
	}
}