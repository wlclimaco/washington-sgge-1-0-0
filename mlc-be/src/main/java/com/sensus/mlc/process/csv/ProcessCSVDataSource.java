package com.sensus.mlc.process.csv;

import java.util.List;
import java.util.TimeZone;

import com.sensus.common.util.CSVDataSource;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;

/**
 * The CSVDataSource for the Process model object.
 *
 * @author rpulley
 */
public class ProcessCSVDataSource extends CSVDataSource<Process>
{

	/** The Constant COLUMN_0. */
	private static final int COLUMN_0 = 0;

	/** The Constant COLUMN_1. */
	private static final int COLUMN_1 = 1;

	/** The Constant COLUMN_2. */
	private static final int COLUMN_2 = 2;

	/** The Constant COLUMN_6. */
	private static final int COLUMN_6 = 6;

	/** The Constant COLUMN_5. */
	private static final int COLUMN_5 = 5;

	/** The Constant COLUMN_4. */
	private static final int COLUMN_4 = 4;

	/** The Constant COLUMN_3. */
	private static final int COLUMN_3 = 3;

	/** The Constant PROCESS_ID. */
	private static final String PROCESS_ID = "Process ID";

	/** The Constant ACTION_DESCRIPTION. */
	private static final String ACTION_DESCRIPTION = "Action Description";

	/** The Constant ITEMS_COUNT. */
	private static final String ITEMS_COUNT = "Count";

	/** The Constant DESCRIPTION. */
	private static final String DESCRIPTION = "Description";

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "Create_User";

	/** The Constant START_TIME. */
	private static final String START_TIME = "Start Time";

	/** The Constant IS_COMPLETED. */
	private static final String IS_COMPLETED = "Completed";

	/** The timezone. */
	private String timezone = TimeZone.getDefault().getID();

	/**
	 * Instantiates a new process csv data source.
	 *
	 * @param request the request
	 * @param list the list
	 */
	public ProcessCSVDataSource(InquiryProcessRequest request, List<Process> list)
	{
		super(request);

		// These should be localized...
		addColumns(PROCESS_ID, ACTION_DESCRIPTION, ITEMS_COUNT, DESCRIPTION,
				CREATE_USER, START_TIME, IS_COMPLETED);

		addData(list);

		if (!ValidationUtil.isNull(request.getTimezone()))
		{
			timezone = request.getTimezone();
		}
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.util.CSVDataSource#getDataForColumn(java.lang.Object, int)
	 */
	@Override
	protected String getDataForColumn(Process process, int column)
	{
		switch (column)
		{
			case COLUMN_0:
				return process.getId().toString();
			case COLUMN_1:
				return process.getLcAction().getDescription();
			case COLUMN_2:
				return String.valueOf(process.getProcessItems().size());
			case COLUMN_3:
				return process.getDescription();
			case COLUMN_4:
				return process.getCreateUser();
			case COLUMN_5:
				return LCDateUtil.convertDateUTCToTimezone(process.getStartTime(), timezone).toString();
			case COLUMN_6:
				return process.getIsProcessComplete().toString();
			default:
				break;
		}

		return null;
	}
}
