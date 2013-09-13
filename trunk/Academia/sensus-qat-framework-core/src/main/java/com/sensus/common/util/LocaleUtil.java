package com.sensus.common.util;

import java.util.Locale;

/**
 * Utility class for working with Locale related data and object instances.
 */
public final class LocaleUtil
{
	/**
	 * Must have since this is a final class.
	 */
	private LocaleUtil()
	{
	}

	/**
	 * For use with JDK6 ONLY
	 * Convert the string to a Locale instance if possible.
	 *
	 * @param localeString The locale string value to convert. This parameter supports an underline separated value in
	 *            the form of: language_country_variant. Country and variant are optional so you could just pass in a
	 *            value of "EN" to set the language to EN.
	 * @return the locale
	 */
	public static Locale getLocale(String localeString)
	{
		String lang = null;
		String country = null;
		String variant = null;

		if ((localeString != null) && !localeString.isEmpty())
		{
			String[] parts = localeString.split("_");

			lang = parts[0];

			// Work through which "parts" are present from the input paramater.
			if (parts.length > 1)
			{
				country = parts[1];
				if (parts.length > 2)
				{
					variant = parts[2];
				}
			}

			// Instantiate new Locale based on "parts"
			if (country != null)
			{
				if (variant != null)
				{
					return new Locale(lang, country, variant);
				}

				return new Locale(lang, country);
			}

			return new Locale(lang);
		}

		return Locale.getDefault();
	}

	// /**
	// * JDK 7 ONLY
	// * Convert the string to a Locale instance if possible.
	// *
	// * @param localeString The locale string value to convert. This parameter supports an underline separated value in
	// * the form of: language_country_variant. Country and variant are optional so you could just pass in a
	// * value of "EN" to set the language to EN.
	// * @return the locale
	// */
	// public static Locale getLocale(String localeString)
	// {
	// Locale.Builder builder = new Locale.Builder();
	//
	// if ((localeString != null) && !localeString.isEmpty())
	// {
	// String[] parts = localeString.split("_");
	//
	// // Set the base languages
	// builder.setLanguage(parts[0]);
	//
	// if (parts.length > 1)
	// {
	// builder.setRegion(parts[1]);
	// if (parts.length > 2)
	// {
	// builder.setVariant(parts[2]);
	// }
	// }
	// }
	//
	// return builder.build();
	// }
}
