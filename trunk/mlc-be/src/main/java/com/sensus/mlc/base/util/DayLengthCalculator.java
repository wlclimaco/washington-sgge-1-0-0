package com.sensus.mlc.base.util;

import java.util.Calendar;

/**
 * Adapted from http://www.sci.fi/~benefon/rscalc.c
 * Comments/details have been shamelessly stripped away, since this author
 * makes no pretense of the understanding the algorithm. Just refer to the c
 * code above if you get the strong urge to understanding the mechanics.
 * Author - Venky Krishnan
 */
public final class DayLengthCalculator
{

	/**
	 * Instantiates a new day length calculator.
	 */
	private DayLengthCalculator()
	{

	}

	/** The Constant SunDay. */
	private static final double SUNDAY = 0.53;

	/** The Constant AirRefr. */
	private static final double AIRREFR = 0.566666667;

	/** The Constant TWELVE. */
	private static final Integer TWELVE = 12;

	/** The Constant NSEVEN. */
	private static final Integer NSEVEN = -7;

	/** The Constant NINE. */
	private static final Integer NINE = 9;

	/** The Constant FOUR. */
	private static final Integer FOUR = 4;

	/** The Constant ARBITRARY_NUMBER_275. */
	private static final Integer ARBITRARY_NUMBER_275 = 275;

	/** The Constant ARBITRARY_NUMBER_367. */
	private static final Integer ARBITRARY_NUMBER_367 = 367;

	/** The Constant ARBITRARY_NUMBER_730531_5. */
	private static final Double ARBITRARY_NUMBER_730531_5 = 730531.5;

	/** The Constant ARBITRARY_NUMBER_24_0. */
	private static final Double ARBITRARY_NUMBER_24_0 = 24.0;

	/** The Constant ARBITRARY_NUMBER_0_5. */
	private static final double ARBITRARY_NUMBER_0_5 = 0.5;

	/** The Constant ARBITRARY_NUMBER_0_99999. */
	private static final double ARBITRARY_NUMBER_0_99999 = 0.99999;

	/** The Constant ARBITRARY_NUMBER_280_461. */
	private static final double ARBITRARY_NUMBER_280_461 = 280.461;

	/** The Constant ARBITRARY_NUMBER_9856474. */
	private static final double ARBITRARY_NUMBER_9856474 = .9856474;

	/** The Constant ARBITRARY_NUMBER_23_439. */
	private static final double ARBITRARY_NUMBER_23_439 = 23.439;

	/** The Constant ARBITRARY_NUMBER_0000004. */
	private static final double ARBITRARY_NUMBER_0000004 = .0000004;

	/** The Constant ARBITRARY_NUMBER_7_5. */
	private static final double ARBITRARY_NUMBER_7_5 = 7.5;

	/** The Constant ARBITRARY_NUMBER_0_0001. */
	private static final double ARBITRARY_NUMBER_0_0001 = 0.0001;

	/** The Constant ARBITRARY_NUMBER_357_528. */
	private static final double ARBITRARY_NUMBER_357_528 = 357.528;

	/** The Constant ARBITRARY_NUMBER_9856003. */
	private static final double ARBITRARY_NUMBER_9856003 = .9856003;

	/** The Constant ARBITRARY_NUMBER_1_915. */
	private static final double ARBITRARY_NUMBER_1_915 = 1.915;

	/** The Constant ARBITRARY_NUMBER_0_02. */
	private static final double ARBITRARY_NUMBER_0_02 = 0.02;

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/**
	 * Calculate the length of day given the location latitude and day of the year.
	 *
	 * @param latitude the latitude
	 * @param calendar the calendar
	 * @return the double
	 */
	public static double calculate(double latitude, Calendar calendar)
	{
		double days = calculateDay(calendar);
		double meanLong = calculateSunLongitude(days);
		double meanAnom = calculateSunAnomaly(days);
		double lambda = calculateSunEclipticLongitude(days, meanLong, meanAnom);
		double obliq = Math.toRadians(ARBITRARY_NUMBER_23_439 - (ARBITRARY_NUMBER_0000004 * days));
		double delta = Math.asin(Math.sin(obliq) * Math.sin(lambda));
		double ha = calculateF0(latitude, delta);
		double daylen = Math.toDegrees(ha / ARBITRARY_NUMBER_7_5);
		if (daylen < ARBITRARY_NUMBER_0_0001)
		{
			daylen = 0.0;
		}
		return daylen;
	}

	/**
	 * Calculate day.
	 *
	 * @param calendar the calendar
	 * @return the double
	 */
	private static double calculateDay(Calendar calendar)
	{
		int y = calendar.get(Calendar.YEAR);
		int m = calendar.get(Calendar.MONTH) + 1;
		int d = calendar.get(Calendar.DAY_OF_MONTH);
		int h = TWELVE;
		long luku = ((NSEVEN * (y + ((m + NINE) / TWELVE))) / FOUR) + ((ARBITRARY_NUMBER_275 * m) / NINE) + d;
		luku += y * ARBITRARY_NUMBER_367;
		return (luku - ARBITRARY_NUMBER_730531_5) + (h / ARBITRARY_NUMBER_24_0);
	}

	/**
	 * Calculate range.
	 *
	 * @param x the x
	 * @return the double
	 */
	private static double calculateRange(double x)
	{
		double b = (ARBITRARY_NUMBER_0_5 * x) / Math.PI;
		double a = 2.0 * Math.PI * (b - (long)b);
		if (a < 0)
		{
			a = (2.0 * Math.PI) + a;
		}
		return a;
	}

	/**
	 * Calculate f0.
	 *
	 * @param lat the lat
	 * @param declin the declin
	 * @return the double
	 */
	private static double calculateF0(double lat, double declin)
	{
		double fo;
		double dfo;
		dfo = Math.toRadians((ARBITRARY_NUMBER_0_5 * SUNDAY) + AIRREFR);
		if (lat < 0.0)
		{
			dfo = -dfo;
		}
		fo = Math.tan(declin + dfo) * Math.tan(Math.toRadians(lat));
		if (fo > ARBITRARY_NUMBER_0_99999)
		{
			fo = 1.0; // to avoid overflow
		}
		fo = Math.asin(fo) + (Math.PI / 2.0);
		return fo;
	}

	/**
	 * Calculate sun longitude.
	 *
	 * @param d the d
	 * @return the double
	 */
	private static double calculateSunLongitude(double d)
	{
		return calculateRange(Math.toRadians(ARBITRARY_NUMBER_280_461) + Math.toRadians(ARBITRARY_NUMBER_9856474 * d));
	}

	/**
	 * Calculate sun anomaly.
	 *
	 * @param d the d
	 * @return the double
	 */
	private static double calculateSunAnomaly(double d)
	{
		return calculateRange(Math.toRadians(ARBITRARY_NUMBER_357_528 + (ARBITRARY_NUMBER_9856003 * d)));
	}

	/**
	 * Calculate sun ecliptic longitude.
	 *
	 * @param d the d
	 * @param l the l
	 * @param g the g
	 * @return the double
	 */
	private static double calculateSunEclipticLongitude(double d, double l, double g)
	{
		return calculateRange(l + Math.toRadians((ARBITRARY_NUMBER_1_915 * Math.sin(g)) + (ARBITRARY_NUMBER_0_02 * Math.sin(TWO * g))));
	}

}
