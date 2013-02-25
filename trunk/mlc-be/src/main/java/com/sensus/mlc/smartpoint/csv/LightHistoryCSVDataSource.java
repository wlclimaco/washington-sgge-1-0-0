package com.sensus.mlc.smartpoint.csv;

import java.util.List;
import java.util.TimeZone;

import com.sensus.common.util.CSVDataSource;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.smartpoint.model.LightHistory;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;

/**
 * The Class LightHistoryCSVDataSource.
 */
public class LightHistoryCSVDataSource extends CSVDataSource<LightHistory>
{

	/** The Constant COLUMN_4. */
	private static final int COLUMN_4 = 4;

	/** The Constant COLUMN_3. */
	private static final int COLUMN_3 = 3;

	/** The Constant COLUMN_2. */
	private static final int COLUMN_2 = 2;

	/** The Constant COLUMN_1. */
	private static final int COLUMN_1 = 1;

	/** The Constant COLUMN_0. */
	private static final int COLUMN_0 = 0;

	/** The Constant ACTION. */
	private static final String ACTION = "Action";

	/** The Constant DESCRIPTION. */
	private static final String DESCRIPTION = "Description";

	/** The Constant REQUESTED_BY. */
	private static final String REQUESTED_BY = "Requested By";

	/** The Constant START_TIME. */
	private static final String START_TIME = "Start time";

	/** The Constant STATUS. */
	private static final String STATUS = "Status";

	/** The timezone. */
	private String timezone = TimeZone.getDefault().getID();

	/**
	 * Instantiates a new light history csv data source.
	 *
	 * @param request the request
	 * @param list the list
	 */
	public LightHistoryCSVDataSource(InquiryLightRequest request, List<LightHistory> list)
	{
		super(request);
		addColumns(ACTION, DESCRIPTION, REQUESTED_BY, START_TIME, STATUS);
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
	protected String getDataForColumn(LightHistory lightHistory, int column)
	{
		switch (column)
		{
			case COLUMN_0:
				return lightHistory.getName();
			case COLUMN_1:
				return lightHistory.getDescription();
			case COLUMN_2:
				return lightHistory.getCreateUser();
			case COLUMN_3:
				return LCDateUtil.convertDateUTCToTimezone(lightHistory.getCreateDate(), timezone).toString();
			case COLUMN_4:
				return String.valueOf(lightHistory.isStatusComplete());
			default:
				break;
		}

		return null;
	}
}
