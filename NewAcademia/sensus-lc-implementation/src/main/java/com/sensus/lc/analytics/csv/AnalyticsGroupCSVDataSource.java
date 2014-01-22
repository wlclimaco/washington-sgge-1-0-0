package com.sensus.lc.analytics.csv;

import java.util.List;

import com.sensus.common.util.CSVDataSource;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.analytics.model.AnalyticsGroup;
import com.sensus.lc.analytics.model.AnalyticsGroupColumns;
import com.sensus.lc.analytics.model.request.InquiryAnalyticsRequest;

public class AnalyticsGroupCSVDataSource extends CSVDataSource<AnalyticsGroup>
{
	static final String GROUPS = "Groups";

	public AnalyticsGroupCSVDataSource(InquiryAnalyticsRequest request)
	{
		super(request);

		// These should be localized...
		addColumn(GROUPS);

		List<AnalyticsGroup> list = request.getGroups();

		if (!ValidationUtil.isNullOrEmpty(list))
		{
			AnalyticsGroup group = list.get(0);

			for (AnalyticsGroupColumns column : group.getColumns())
			{
				addColumn(column.getDescription());
			}

			addData(list);
		}
	}

	@Override
	protected String getDataForColumn(AnalyticsGroup group, int column)
	{
		if (column == 0)
		{
			return group.getName();
		}

		AnalyticsGroupColumns columns = group.getColumns().get(column - 1);
		return String.valueOf(columns.getValue());
	}
}
