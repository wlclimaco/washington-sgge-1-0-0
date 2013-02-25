package com.sensus.mlc.wui.base.util;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.analytics.model.AnalyticsGroupOrderByEnum;
import com.sensus.mlc.base.model.MLCSortExpression;
import com.sensus.mlc.group.model.GroupOrderByEnum;
import com.sensus.mlc.process.model.ProcessOrderByEnum;
import com.sensus.mlc.schedule.model.ScheduleOrderByEnum;
import com.sensus.mlc.smartpoint.model.CustomSearchOrderByEnum;
import com.sensus.mlc.smartpoint.model.LightOrderByEnum;
import com.sensus.mlc.tag.model.TagOrderByEnum;

/**
 * The Class ActionPaginationUtil.
 * 
 * @author Jose Carlos Pereira
 */
public final class ActionPaginationUtil
{

	/**
	 * Instantiates a new action pagination util.
	 */
	private ActionPaginationUtil()
	{

	}

	/**
	 * The logger for this class.
	 */
	private static transient Log LOG = LogFactory.getLog(ActionPaginationUtil.class);

	/** The DISPLAY table start. */
	public static final String DISPLAY_START = "iDisplayStart";

	/** The DISPLAY table length. */
	public static final String DISPLAY_LENGTH = "iDisplayLength";

	/** The SORT table direction. */
	public static final String SORT_DIR = "sSortDir_0";

	/** The SORT table column. */
	public static final String SORT_COL = "iSortCol_0";

	/** The MESSAGE error. */
	public static final String ERR_DISPLAY_LENGTH = "The pagination display length is incorrect";

	/** The Constant TOTAL_ROWS. */
	public static final String TOTAL_ROWS = "iTotalDisplayRecords";

	/** The Constant ZERO. */
	public static final Integer ZERO = 0;

	/** The Constant FIFTEEN. */
	public static final Integer FIFTEEN = 15;

	/** The Constant COLUMN_0. */
	private static final int COLUMN_0 = 0;

	/** The Constant COLUMN_1. */
	private static final int COLUMN_1 = 1;

	/** The Constant COLUMN_2. */
	private static final int COLUMN_2 = 2;

	/** The Constant COLUMN_3. */
	private static final int COLUMN_3 = 3;

	/** The Constant COLUMN_4. */
	private static final int COLUMN_4 = 4;

	/** The Constant COLUMN_5. */
	private static final int COLUMN_5 = 5;

	/** The Constant COLUMN_6. */
	private static final int COLUMN_6 = 6;

	/** The Constant COLUMN_7. */
	private static final int COLUMN_7 = 7;
	/** The Constant COLUMN_7. */
	private static final int COLUMN_8 = 8;

	/** The Constant COLUMN_9. */
	private static final int COLUMN_9 = 9;

	/** The Constant COLUMN_15. */
	private static final int COLUMN_15 = 15;

	/** The Constant COLUMN_16. */
	private static final int COLUMN_16 = 16;

	/** The Constant COLUMN_17. */
	private static final int COLUMN_17 = 17;

	/** The Constant COLUMN_18. */
	private static final int COLUMN_18 = 18;

	/**
	 * Gets the current display start index.
	 * 
	 * @param parameters the parameters
	 * @return the current display start index
	 */
	public static Integer getCurrentDisplayStartIndex(Map<String, String[]> parameters)
	{
		Integer currentDisplayStartIndex = null;
		try
		{
			if (parameters.containsKey(DISPLAY_START))
			{
				currentDisplayStartIndex = Integer.parseInt(parameters.get(DISPLAY_START)[0]);
			}
			else
			{
				return ZERO;
			}
		}
		catch (NumberFormatException e)
		{
			LOG.error("The pagination display start index is incorrect");
			return null;
		}
		return currentDisplayStartIndex;
	}

	/**
	 * Gets the page display size.
	 * 
	 * @param parameters the parameters
	 * @return the page display size
	 */
	public static Integer getPageDisplaySize(Map<String, String[]> parameters)
	{
		Integer displaySize = null;
		try
		{
			if (parameters.containsKey(DISPLAY_LENGTH))
			{
				displaySize = Integer.parseInt(parameters.get(DISPLAY_LENGTH)[0]);
			}
			else
			{
				return FIFTEEN;
			}
		}
		catch (NumberFormatException e)
		{
			LOG.error(ERR_DISPLAY_LENGTH);
			return null;
		}
		return displaySize;
	}

	/**
	 * Gets the mlc sort expression.
	 * 
	 * @param parameters the parameters
	 * @return the mlc sort expression
	 */
	public static MLCSortExpression getMLCSortExpression(Map<String, String[]> parameters)
	{
		MLCSortExpression sortExpression = new MLCSortExpression();
		try
		{
			if (!ValidationUtil.isNull(parameters.get(SORT_COL))
					&& (!ValidationUtil.isNull(parameters.get(SORT_COL)[0])
					&& !"".equals(parameters.get(SORT_COL)[0])))
			{
				switch (Integer.parseInt(parameters.get(SORT_COL)[0]))
				{
					case COLUMN_1:
						sortExpression.setField(LightOrderByEnum.POLE_ID.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						sortExpression.setIsProperty(true);
						break;
					case COLUMN_2:
						sortExpression.setField(LightOrderByEnum.RNI_ID.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						sortExpression.setIsProperty(false);
						break;
					case COLUMN_3:
						sortExpression.setField(LightOrderByEnum.LAMP_TYPE.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						sortExpression.setIsProperty(true);
						break;
					case COLUMN_4:
						sortExpression.setField(LightOrderByEnum.DATE_ADDED.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						sortExpression.setIsProperty(true);
						break;
					case COLUMN_5:
						sortExpression.setField(LightOrderByEnum.CITY_NAME.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						sortExpression.setIsProperty(true);
						break;
					case COLUMN_8:
						sortExpression.setField(LightOrderByEnum.PROTECTED.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						sortExpression.setIsProperty(false);
						break;
					case COLUMN_9:
						sortExpression.setField(LightOrderByEnum.STATUS.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						sortExpression.setIsProperty(false);
						break;
					default:
						break;
				}
			}
		}
		catch (NumberFormatException e)
		{
			LOG.error("The order by column is incorrect");
			return null;
		}
		return sortExpression;
	}

	/**
	 * Gets the sort expression group.
	 * 
	 * @param parameters the parameters
	 * @return the sort expression
	 */
	public static SortExpression getSortExpressionGroup(Map<String, String[]> parameters)
	{
		SortExpression sortExpression = new SortExpression();
		try
		{
			if (!ValidationUtil.isNull(parameters.get(SORT_COL))
					&& (!ValidationUtil.isNull(parameters.get(SORT_COL)[0])
					&& !"".equals(parameters.get(SORT_COL)[0])))
			{
				switch (Integer.parseInt(parameters.get(SORT_COL)[0]))
				{
					case COLUMN_1:
						sortExpression.setField(GroupOrderByEnum.NAME_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_3:
						sortExpression.setField(GroupOrderByEnum.SMARTPOINTS_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_4:
						sortExpression.setField(GroupOrderByEnum.DATE_ADDED_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					default:
						break;
				}
			}
		}
		catch (NumberFormatException e)
		{
			LOG.error("The group order by column is incorrect");
			return null;
		}
		return sortExpression;
	}

	/**
	 * Gets the sort expression tag.
	 * 
	 * @param parameters the parameters
	 * @return the sort expression tag
	 */
	public static SortExpression getSortExpressionTag(Map<String, String[]> parameters)
	{
		SortExpression sortExpression = new SortExpression();
		try
		{
			if (!ValidationUtil.isNull(parameters.get(SORT_COL))
					&& (!ValidationUtil.isNull(parameters.get(SORT_COL)[0])
					&& !"".equals(parameters.get(SORT_COL)[0])))
			{
				switch (Integer.parseInt(parameters.get(SORT_COL)[0]))
				{
					case COLUMN_1:
						sortExpression.setField(TagOrderByEnum.NAME_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_3:
						sortExpression.setField(TagOrderByEnum.SMARTPOINTS_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_4:
						sortExpression.setField(TagOrderByEnum.DATE_ADDED_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					default:
						break;
				}
			}
		}
		catch (NumberFormatException e)
		{
			LOG.error("The tag order by column is incorrect");
			return null;
		}
		return sortExpression;
	}

	/**
	 * Gets the sort expression group.
	 * 
	 * @param parameters the parameters
	 * @return the sort expression
	 */
	public static SortExpression getSortExpressionLRP(Map<String, String[]> parameters)
	{
		SortExpression sortExpression = new SortExpression();
		try
		{
			if (!ValidationUtil.isNull(parameters.get(SORT_COL))
					&& (!ValidationUtil.isNull(parameters.get(SORT_COL)[0])
					&& !"".equals(parameters.get(SORT_COL)[0])))
			{
				switch (Integer.parseInt(parameters.get(SORT_COL)[0]))
				{
					case COLUMN_2:
						sortExpression.setField(ProcessOrderByEnum.ID_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_3:
						sortExpression.setField(ProcessOrderByEnum.ACTION_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_5:
						sortExpression.setField(ProcessOrderByEnum.SMARTPOINTS_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_15:
						sortExpression.setField(ProcessOrderByEnum.DESCRIPTION_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_16:
						sortExpression.setField(ProcessOrderByEnum.START_TIME_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_17:
						sortExpression.setField(ProcessOrderByEnum.COMPLETE_IN_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_18:
						sortExpression.setField(ProcessOrderByEnum.STATUS_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					default:
						break;
				}
			}
		}
		catch (NumberFormatException e)
		{
			LOG.error("The long running process order by column is incorrect");
			return null;
		}
		return sortExpression;
	}

	/**
	 * Gets the sort expression light history.
	 * 
	 * @param parameters the parameters
	 * @return the sort expression light history
	 */
	public static MLCSortExpression getSortExpressionLightHistory(Map<String, String[]> parameters)
	{
		MLCSortExpression sortExpression = new MLCSortExpression();
		try
		{
			if (!ValidationUtil.isNull(parameters.get(SORT_COL))
					&& (!ValidationUtil.isNull(parameters.get(SORT_COL)[0])
					&& !"".equals(parameters.get(SORT_COL)[0])))
			{
				switch (Integer.parseInt(parameters.get(SORT_COL)[0]))
				{
					case COLUMN_0:
						sortExpression.setField(ProcessOrderByEnum.ID_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_1:
						sortExpression.setField(ProcessOrderByEnum.ACTION_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_3:
						sortExpression.setField(ProcessOrderByEnum.USER_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_4:
						sortExpression.setField(ProcessOrderByEnum.START_TIME_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_5:
						sortExpression.setField(ProcessOrderByEnum.STATUS_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					default:
						break;
				}
			}
		}
		catch (NumberFormatException e)
		{
			LOG.error("The Light History order by column is incorrect");
			return null;
		}
		return sortExpression;
	}

	/**
	 * Gets the sort expression process.
	 * 
	 * @param parameters the parameters
	 * @return the sort expression process
	 */
	public static SortExpression getSortExpressionProcess(Map<String, String[]> parameters)
	{
		SortExpression sortExpression = new SortExpression();
		try
		{
			if (!ValidationUtil.isNull(parameters.get(SORT_COL))
					&& (!ValidationUtil.isNull(parameters.get(SORT_COL)[0])
					&& !"".equals(parameters.get(SORT_COL)[0])))
			{
				switch (Integer.parseInt(parameters.get(SORT_COL)[0]))
				{
					case COLUMN_0:
						sortExpression.setField(ProcessOrderByEnum.ID_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;

					case COLUMN_1:
						sortExpression.setField(ProcessOrderByEnum.ACTION_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;

					case COLUMN_3:
						sortExpression.setField(ProcessOrderByEnum.SMARTPOINTS_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;

					case COLUMN_6:
						sortExpression.setField(ProcessOrderByEnum.USER_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;

					case COLUMN_7:
						sortExpression.setField(ProcessOrderByEnum.START_TIME_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;

					case COLUMN_8:
						sortExpression.setField(ProcessOrderByEnum.STATUS_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;

					default:
						break;
				}
			}
		}
		catch (NumberFormatException e)
		{
			LOG.error("The process order by column is incorrect");
			return null;
		}
		return sortExpression;
	}

	/**
	 * Gets the sort expression schedule.
	 * 
	 * @param parameters the parameters
	 * @return the sort expression schedule
	 */
	public static SortExpression getSortExpressionSchedule(Map<String, String[]> parameters)
	{
		SortExpression sortExpression = new SortExpression();
		try
		{
			if (!ValidationUtil.isNull(parameters.get(SORT_COL))
					&& (!ValidationUtil.isNull(parameters.get(SORT_COL)[0])
					&& !"".equals(parameters.get(SORT_COL)[0])))
			{
				switch (Integer.parseInt(parameters.get(SORT_COL)[0]))
				{
					case COLUMN_1:
						sortExpression.setField(ScheduleOrderByEnum.NAME_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_3:
						sortExpression.setField(ScheduleOrderByEnum.TYPE_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_4:
						sortExpression.setField(ScheduleOrderByEnum.SMARTPOINTS_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_5:
						sortExpression.setField(ScheduleOrderByEnum.DATE_ADDED_COLUMN.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					default:
						break;
				}
			}
		}
		catch (NumberFormatException e)
		{
			LOG.error("The schedule order by column is incorrect");
			return null;
		}
		return sortExpression;
	}

	/**
	 * Gets the sort expression analytics.
	 * 
	 * @param parameters the parameters
	 * @param analyticsGroupOrderByEnum the analytics group order by enum
	 * @return the sort expression analytics
	 */
	public static SortExpression getSortExpressionAnalytics(Map<String, String[]> parameters,
			String[] analyticsGroupOrderByEnum)
	{
		SortExpression sortExpression = new SortExpression();

		switch (Integer.parseInt(parameters.get(SORT_COL)[0]))
		{
			case COLUMN_1:
				sortExpression.setField(analyticsGroupOrderByEnum[0]);
				sortExpression.setDirection(getOrderByDir(parameters));
				break;
			case COLUMN_2:
				sortExpression.setField(analyticsGroupOrderByEnum[1]);
				sortExpression.setDirection(getOrderByDir(parameters));
				break;
			case COLUMN_3:
				sortExpression.setField(analyticsGroupOrderByEnum[2]);
				sortExpression.setDirection(getOrderByDir(parameters));
				break;
			case COLUMN_4:
				sortExpression.setField(analyticsGroupOrderByEnum[COLUMN_3]);
				sortExpression.setDirection(getOrderByDir(parameters));
				break;
			case COLUMN_5:
				sortExpression.setField(analyticsGroupOrderByEnum[COLUMN_4]);
				sortExpression.setDirection(getOrderByDir(parameters));
				break;
			default:
				break;
		}

		return sortExpression;
	}

	/**
	 * Gets the sort expression group.
	 * 
	 * @param parameters the parameters
	 * @param type the type
	 * @return the sort expression
	 */
	public static SortExpression setSortAnalyticsType(Map<String, String[]> parameters, Integer type)
	{

		try
		{
			String[] analyticsGroupOrderBy = new String[COLUMN_5];

			if (!ValidationUtil.isNull(parameters.get(SORT_COL))
					&& (!ValidationUtil.isNull(parameters.get(SORT_COL)[0])
					&& !"".equals(parameters.get(SORT_COL)[0])))
			{

				switch (type)
				{

					case COLUMN_1:
						analyticsGroupOrderBy[0] = AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_NAME.getValue();
						analyticsGroupOrderBy[COLUMN_1] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_TOTAL.getValue();
						analyticsGroupOrderBy[COLUMN_2] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_LAMP_FAILURE.getValue();
						analyticsGroupOrderBy[COLUMN_3] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_POWER_FAILURE.getValue();
						analyticsGroupOrderBy[COLUMN_4] = null;
						break;
					case COLUMN_2:
						analyticsGroupOrderBy[0] = AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_NAME.getValue();
						analyticsGroupOrderBy[COLUMN_1] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_TOTAL.getValue();
						analyticsGroupOrderBy[COLUMN_2] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_POWER_SURGE.getValue();
						analyticsGroupOrderBy[COLUMN_3] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_BROWNOUT_DETECTED.getValue();
						analyticsGroupOrderBy[COLUMN_4] = null;
						break;
					case COLUMN_3:
						analyticsGroupOrderBy[0] = AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_NAME.getValue();
						analyticsGroupOrderBy[COLUMN_1] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_TOTAL.getValue();
						analyticsGroupOrderBy[COLUMN_2] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_INDUCTION.getValue();
						analyticsGroupOrderBy[COLUMN_3] = AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_LED.getValue();
						analyticsGroupOrderBy[COLUMN_4] = AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_OTHER.getValue();
						break;
					case COLUMN_4:
						analyticsGroupOrderBy[0] = AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_NAME.getValue();
						analyticsGroupOrderBy[COLUMN_1] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_TOTAL.getValue();
						analyticsGroupOrderBy[COLUMN_2] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_INDUCTION.getValue();
						analyticsGroupOrderBy[COLUMN_3] = AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_LED.getValue();
						analyticsGroupOrderBy[COLUMN_4] = AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_OTHER.getValue();
						break;
					case COLUMN_5:
						analyticsGroupOrderBy[0] = AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_NAME.getValue();
						analyticsGroupOrderBy[COLUMN_1] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_TOTAL.getValue();
						analyticsGroupOrderBy[COLUMN_2] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_INDUCTION.getValue();
						analyticsGroupOrderBy[COLUMN_3] = AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_LED.getValue();
						analyticsGroupOrderBy[COLUMN_4] = AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_OTHER.getValue();
						break;
					case COLUMN_6:
						analyticsGroupOrderBy[0] = AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_NAME.getValue();
						analyticsGroupOrderBy[COLUMN_1] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_CREDITS_CREATED.getValue();
						analyticsGroupOrderBy[COLUMN_2] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_ENERGY_SAVED.getValue();
						analyticsGroupOrderBy[COLUMN_3] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_BARRELS_OF_OIL_SAVED.getValue();
						analyticsGroupOrderBy[COLUMN_4] =
								AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_METRIC_OF_CO_SABED.getValue();
						break;
					default:
						break;
				}
				return getSortExpressionAnalytics(parameters, analyticsGroupOrderBy);
			}
			else
			{
				return null;
			}
		}
		catch (NumberFormatException e)
		{
			LOG.error("The analytics order by column is incorrect");
			return null;
		}

	}

	/**
	 * Gets the order by direction.
	 * 
	 * @param parameters the parameters
	 * @return the order by direction
	 */
	private static Direction getOrderByDir(Map<String, String[]> parameters)
	{
		try
		{
			String direction = parameters.get(SORT_DIR)[0];
			if (direction.equals("asc"))
			{
				return Direction.Ascending;
			}
			else if (direction.equals("desc"))
			{
				return Direction.Descending;
			}

		}
		catch (NumberFormatException e)
		{
			LOG.error("The pagination order by direction is incorrect");
			return null;
		}
		return null;
	}

	/**
	 * Gets the sort expression saved search.
	 * 
	 * @param parameters the parameters
	 * @return the sort expression
	 */
	public static SortExpression getSortExpressionSavedSearch(Map<String, String[]> parameters)
	{
		SortExpression sortExpression = new SortExpression();
		try
		{
			if (!ValidationUtil.isNull(parameters.get(SORT_COL))
					&& (!ValidationUtil.isNull(parameters.get(SORT_COL)[0])
					&& !"".equals(parameters.get(SORT_COL)[0])))
			{
				switch (Integer.parseInt(parameters.get(SORT_COL)[0]))
				{
					case COLUMN_1:
						sortExpression.setField(CustomSearchOrderByEnum.CUSTOM_SEARCH_NAME.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					case COLUMN_4:
						sortExpression.setField(CustomSearchOrderByEnum.CUSTOM_SEARCH_DATE_ADDED.getValue());
						sortExpression.setDirection(getOrderByDir(parameters));
						break;
					default:
						break;
				}
			}
		}
		catch (NumberFormatException e)
		{
			LOG.error("The search order by column is incorrect");
			return null;
		}
		return sortExpression;
	}

	/**
	 * Gets the total rows record.
	 * 
	 * @param parameters the parameters
	 * @return the total rows record
	 */
	public static Integer getTotalRowsRecord(Map<String, String[]> parameters)
	{
		Integer displaySize = null;
		try
		{
			if (parameters.containsKey(TOTAL_ROWS))
			{
				displaySize = Integer.parseInt(parameters.get(TOTAL_ROWS)[0]);
			}
			else
			{
				return ZERO;
			}
		}
		catch (NumberFormatException e)
		{
			LOG.error(ERR_DISPLAY_LENGTH);
			return null;
		}
		return displaySize;
	}
}