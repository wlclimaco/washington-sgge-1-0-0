package com.sensus.dm.common.base.util.csv.cellprocessor;

import java.util.Locale;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import com.sensus.common.util.SensusMessageUtil;
import com.sensus.dm.common.base.util.csv.model.CsvColumnEnum;
import com.sensus.dm.elec.action.model.DemandResponseParticipationEnum;

/**
 * The Class CorvertDemandResponse.
 * 
 * @author QAT Global.
 */
public class CorvertDemandResponse extends CellProcessorAdaptor implements StringCellProcessor
{
	/** The Constant YES. */
	private static final String YES = "dm.common.process.csv.yes";

	/** The Constant NO. */
	private static final String NO = "dm.common.process.csv.no";

	/** The column. */
	private final String column;

	/** The locale. */
	private final Locale locale;

	/**
	 * Instantiates a new corvert demand response.
	 * 
	 * @param col the col
	 * @param localeParam the locale
	 */
	public CorvertDemandResponse(String col, Locale localeParam)
	{
		super();
		column = col;
		locale = localeParam;

	}

	/*
	 * (non-Javadoc)
	 * @see org.supercsv.cellprocessor.ift.CellProcessor#execute(java.lang.Object, org.supercsv.util.CsvContext)
	 */
	@Override
	public Object execute(Object value, CsvContext context)
	{
		if (!String.class.isAssignableFrom(value.getClass()))
		{
			throw new SuperCsvCellProcessorException(String.class, value, context, this);
		}
		String result = SensusMessageUtil.getMessage(convertParticipation((String)value), null, null, locale);
		return next.execute(result, context);
	}

	/**
	 * Convert participation.
	 * 
	 * @param participation the participation
	 * @return the string
	 */
	private String convertParticipation(String participation)
	{
		if (CsvColumnEnum.FULL_PARTICIPATION.getValue().equals(column))
		{
			if (DemandResponseParticipationEnum.FULL_PARTICIPATION.getValue().equalsIgnoreCase(participation))
			{
				return YES;
			}
		}
		else if (CsvColumnEnum.PARTICIPATION.getValue().equals(column))
		{
			if (DemandResponseParticipationEnum.PARTIAL_PARTICIPATION.getValue().equalsIgnoreCase(participation)
					|| DemandResponseParticipationEnum.PARTIAL_PARTICIPATION_AND_OPT_OUT.getValue().equalsIgnoreCase(
							participation))
			{
				return YES;
			}
		}
		else if (CsvColumnEnum.OPT_OUT.getValue().equals(column))
		{
			if (DemandResponseParticipationEnum.OPT_OUT.getValue().equalsIgnoreCase(participation))
			{
				return YES;
			}
		}
		return NO;
	}
}
