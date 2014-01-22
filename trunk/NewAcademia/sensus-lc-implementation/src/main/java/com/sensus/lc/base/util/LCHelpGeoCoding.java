package com.sensus.lc.base.util;

import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sensus.common.validation.ValidationUtil;

/**
 * The Class LCHelp.
 */
public class LCHelpGeoCoding
{
	/** The LOG. */
	private static final Log LOG = LogFactory.getLog(LCHelpGeoCoding.class);

	/** The Constant REVERSE_LOOKUP_OK. */
	private static final String REVERSE_LOOKUP_OK = "OK";

	/** The STREET. */
	private static String STREET = "Street";

	/** The CITY. */
	private static String CITY = "City";

	/** The COUNTY. */
	private static String COUNTY = "County";

	/** The STATE. */
	private static String STATE = "State";

	/** The ZIP. */
	private static String ZIP = "Zip";

	/** The Constant HTTP_SUCCESS. */
	private static final Integer HTTP_SUCCESS = 200;

	// Variables relative to reverse lookup of lat and long
	/** The URL Address for the reverse lookup service. */
	private String reverseLookup;

	/** The base expression for all XPaths. */
	private String xpathExpressionBase;

	/** The XPATH expression for status. */
	private String xpathExpressionStatus;

	/** The XPATH expression for state. */
	private String xpathExpressionState;

	/** The XPATH expression for county. */
	private String xpathExpressionCounty;

	/** The XPATH expression for city. */
	private String xpathExpressionCity;

	/** The XPATH expression for zip. */
	private String xpathExpressionZip;

	/** The XPATH expression for route. */
	private String xpathExpressionRoute;

	/**
	 * Instantiates a new lC help geo coding.
	 */
	public LCHelpGeoCoding()
	{
	}

	/**
	 * Gets the Reverse Lookup.
	 * 
	 * @return the Reverse Lookup
	 */
	public String getReverseLookup()
	{
		return reverseLookup;
	}

	/**
	 * Sets the Reverse Lookup.
	 * 
	 * @param rEVERSELOOKUP the new Reverse Lookup.
	 */
	public void setReverseLookup(String reverseLookupValue)
	{
		reverseLookup = reverseLookupValue;
	}

	/**
	 * Gets the Xpath Expression Base.
	 * 
	 * @return the Xpath Expression Base
	 */
	public String getXpathExpressionBase()
	{
		return xpathExpressionBase;
	}

	/**
	 * Sets theXpath Expression Base.
	 * 
	 * @param xPATHEXPRESSIONBASE the new Xpath Expression Base
	 */
	public void setXpathExpressionBase(String xpathExpressionBaseValue)
	{
		xpathExpressionBase = xpathExpressionBaseValue;
	}

	/**
	 * Gets the Xpath Expression Status.
	 * 
	 * @return the Xpath Expression Status
	 */
	public String getXpathExpressionStatus()
	{
		return xpathExpressionStatus;
	}

	/**
	 * Sets the xpath Expression Status.
	 * 
	 * @param xPATHEXPRESSIONSTATUS the new xpath Expression Status
	 */
	public void setXpathExpressionStatus(String xpathExpressionStatusValue)
	{
		xpathExpressionStatus = xpathExpressionStatusValue;
	}

	/**
	 * Gets the xPAT Xpath Expression State.
	 * 
	 * @return the xPAT Xpath Expression State
	 */
	public String getXpathExpressionState()
	{
		return xpathExpressionState;
	}

	/**
	 * Sets the Xpath Expression State.
	 * 
	 * @param xPATHEXPRESSIONSTATE the new Xpath Expression State
	 */
	public void setXpathExpressionState(String xpathExpressionStateValue)
	{
		xpathExpressionState = xpathExpressionStateValue;
	}

	/**
	 * Gets the xpath Expression County.
	 * 
	 * @return the new xpath Expression County
	 */
	public String getXpathExpressionCounty()
	{
		return xpathExpressionCounty;
	}

	/**
	 * Sets the xPAT h_ expressio n_ county.
	 * 
	 * @param xPATHEXPRESSIONCOUNTY the new xPAT h_ expressio n_ county
	 */
	public void setXpathExpressionCounty(String xpathExpressionCountyValue)
	{
		xpathExpressionCounty = xpathExpressionCountyValue;
	}

	/**
	 * Gets the Xpath Expression City.
	 * 
	 * @return the Xpath Expression City
	 */
	public String getXpathExpressionCity()
	{
		return xpathExpressionCity;
	}

	/**
	 * Sets the Xpath Expression City.
	 * 
	 * @param xPATHEXPRESSIONCITY the new Xpath Expression City
	 */
	public void setXpathExpressionCity(String xpathExpressionCityValue)
	{
		xpathExpressionCity = xpathExpressionCityValue;
	}

	/**
	 * Gets the Xpath Expression Zip.
	 * 
	 * @return the Xpath Expression Zip
	 */
	public String getXpathExpressionZip()
	{
		return xpathExpressionZip;
	}

	/**
	 * Sets the Xpath Expression Zip.
	 * 
	 * @param xPATHEXPRESSIONZIP the new Xpath Expression Zip
	 */
	public void setXpathExpressionZip(String xpathExpressionZipValue)
	{
		xpathExpressionZip = xpathExpressionZipValue;
	}

	/**
	 * Gets the Xpath Expression Route.
	 * 
	 * @return the Xpath Expression Route
	 */
	public String getXpathExpressionRoute()
	{
		return xpathExpressionRoute;
	}

	/**
	 * Sets the Xpath Expression Route .
	 * 
	 * @param xPATHEXPRESSIONROUTE the Xpath Expression Route
	 */
	public void setXpathExpressionRoute(String xpathExpressionRouteValue)
	{
		xpathExpressionRoute = xpathExpressionRouteValue;
	}

	/**
	 * Performs reverse lookup based on Google's Geocoding Api.
	 * 
	 * @param lat the latitude
	 * @param lng the longitude
	 * @return the hash map containing the different address pieces
	 */
	public HashMap<String, String> fetchLocationInfo(Double lat, Double lng)
	{
		HashMap<String, String> addrInfo = new HashMap<String, String>();

		// First, only continue if lat and long are not zero
		if (!ValidationUtil.isNullOrZero(lat) && !ValidationUtil.isNullOrZero(lng))
		{
			// Create PostMethod specifying service url
			String serviceUrl = String.format(getReverseLookup(), lat.toString(), lng.toString());
			HttpPost post = new HttpPost(serviceUrl);

			// Send request
			HttpClient httpclient = new DefaultHttpClient();
			int result = 0;
			Document document = null;
			try
			{
				HttpResponse response = httpclient.execute(post);
				result = response.getStatusLine().getStatusCode();
				InputSource is = new InputSource(response.getEntity().getContent());
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				document = builder.parse(is);
			}
			catch (Exception ex)
			{
				if (LOG.isErrorEnabled())
				{
					LOG.error(ex);
				}
			}
			finally
			{
				// Release current connection to the connection pool once done
				post.releaseConnection();
			}

			if (result == HTTP_SUCCESS)
			{

				XPathFactory xPathFactory = XPathFactory.newInstance();
				XPath xpath = xPathFactory.newXPath();
				String status = evaluateXpath(document, xpathExpressionStatus, xpath);

				// Check if the returned message is ok
				if (!ValidationUtil.isNullOrEmpty(status) && status.equalsIgnoreCase(REVERSE_LOOKUP_OK))
				{
					// Populates the HashMap with the different address pieces
					addrInfo = new HashMap<String, String>();

					addrInfo
							.put(STREET,
									evaluateXpath(document, xpathExpressionBase + xpathExpressionRoute,
											xpath));
					addrInfo.put(CITY,
							evaluateXpath(document, xpathExpressionBase + xpathExpressionCity, xpath));
					addrInfo.put(COUNTY,
							evaluateXpath(document, xpathExpressionBase + xpathExpressionCounty, xpath));
					addrInfo.put(STATE,
							evaluateXpath(document, xpathExpressionBase + xpathExpressionState, xpath));
					addrInfo.put(ZIP,
							evaluateXpath(document, xpathExpressionBase + xpathExpressionZip, xpath));

				}
			}

		}

		return addrInfo;
	}

	/**
	 * Evaluates a simple Xpath expression and returns the first node of the nodeset.
	 * 
	 * @param document the document
	 * @param xpathExpression the xpath expression
	 * @param xpath the xpath
	 * @return the string
	 */
	public String evaluateXpath(Document document, String xpathExpression, XPath xpath)
	{
		// Guarantee the response is at a minimum empty
		String response = "";

		try
		{
			if (ValidationUtil.isNull(xpath))
			{
				return response;
			}

			NodeList nodes = (NodeList)xpath.evaluate(xpathExpression, document, XPathConstants.NODESET);

			if (ValidationUtil.isNull(nodes.item(0)))
			{
				return response;
			}

			response = nodes.item(0).getTextContent();
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(e);
			}
		}

		return response;
	}

}
