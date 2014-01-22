package com.sensus.lc.process.csv;

import java.util.List;
import java.util.TimeZone;

import com.sensus.common.util.CSVDataSource;
import com.sensus.common.util.SensusConvertUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.request.InquiryProcessRequest;

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

	/** The Constant ACTION_DESCRIPTION. */
	private static final String ACTION_TYPE = "Action Type";

	/** The Constant ITEMS_COUNT. */
	private static final String ITEMS_COUNT = "Lights";

	/** The Constant ITEMS_FAILED. */
	private static final String ITEMS_FAILED = "Failed";

	/** The Constant DESCRIPTION. */
	private static final String ACTION_NAME = "Action Name";

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "Requested By";

	/** The Constant START_TIME. */
	private static final String START_TIME = "Start Time";

	/** The Constant STATUS. */
	private static final String STATUS = "Status";

	/** The Constant IS_COMPLETED. */
	private static final String IS_COMPLETED = "Completed";

	/** The Constant PROCESSING. */
	private static final String PROCESSING = "Processing";

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
		addColumns(ACTION_TYPE, ACTION_NAME, ITEMS_COUNT, ITEMS_FAILED, CREATE_USER, START_TIME, STATUS);

		addData(list);

		if (!ValidationUtil.isNull(request.getTimezone()))
		{
			timezone = request.getTimezone();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.util.CSVDataSource#getDataForColumn(java.lang.Object, int)
	 */
	@Override
	protected String getDataForColumn(Process process, int column)
	{
		switch (column)
		{
			case COLUMN_0:
				return String.valueOf(process.getLcAction().getActionType());
			case COLUMN_1:
				return process.getDescription();
			case COLUMN_2:
				return String.valueOf(process.getProcessItemAmount());
			case COLUMN_3:
				return String.valueOf(process.getProcessItemFailedAmount());
			case COLUMN_4:
				return process.getCreateUser();
			case COLUMN_5:
				return SensusConvertUtil.toDateString(
						LCDateUtil.convertDateUTCToTimezone(process.getStartTime(), timezone),
						LCDateUtil.DEFAULT_DATETIME_FORMAT);
			case COLUMN_6:

				if (!ValidationUtil.isNull(process.getIsProcessComplete()) && process.getIsProcessComplete())
				{
					return IS_COMPLETED;
				}
				return PROCESSING;
			default:
				break;
		}

		return null;
	}
}
