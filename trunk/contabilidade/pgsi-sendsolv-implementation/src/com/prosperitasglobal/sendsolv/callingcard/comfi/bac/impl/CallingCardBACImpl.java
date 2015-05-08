package com.prosperitasglobal.sendsolv.callingcard.comfi.bac.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.prosperitasglobal.sendsolv.callingcard.bac.ICallingCardBAC;
import com.prosperitasglobal.sendsolv.callingcard.dac.ICallingCardDAC;
import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.prosperitasglobal.sendsolv.callingcard.model.request.CallingCardMaintenanceRequest;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

public class CallingCardBACImpl implements ICallingCardBAC
{
	/** The person dac. */
	private ICallingCardDAC callingCardDAC;

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_CC_CANNOT_READ_XML =
			"sendsolv.base.callingcard.cannot.read.xml";
	private static final String PROSPERITASGLOBAL_BASE_CC_HTTP_ERROR = "sendsolv.base.callingcard.http.error";
	private static final String PROSPERITASGLOBAL_BASE_CC_IO_ERROR = "sendsolv.base.callingcard.io.error";
	private static final String PROSPERITASGLOBAL_BASE_NO_CC_FOUND = "sendsolv.base.callingcard.no.cc.found";
	private static final String PROSPERITASGLOBAL_BASE_NO_TAG_FOUND = "sendsolv.base.callingcard.no.tag.found";
	private static final String PROSPERITASGLOBAL_BASE_NO_CC_INFO_FOUND = "sendsolv.base.callingcard.no.cc.info.found";
	private static final String PROSPERITASGLOBAL_BASE_REFILL_FAILED = "sendsolv.base.callingcard.refill.failed";
	private static final String PROSPERITASGLOBAL_BASE_NO_PIN_INFO_FOUND =
			"sendsolv.base.callingcard.no.pin.info.found";

	private static final String ID_PIN = "id_pin";
	// private static final String PGSI_CARD_IDs = "109";
	private String callingCardType;
	private String callingCardUrl;
	private String callingCardResponseType;
	private String callingCardApiEmail;
	private String callingCardApiPwd;
	private Integer qtyToGenerate;
	private Properties methodsProp;

	private static final String BALANCE_METHOD_KEY = "balance";
	private static final String REFILL_METHOD_KEY = "refill";
	private static final String GENERATE_METHOD_KEY = "generate";

	private static final String BALANCE_TAG_NAME = "balance";
	private static final String RESULT_TAG_NAME = "result";

	private static final Logger LOG = LoggerFactory.getLogger(CallingCardBACImpl.class);
	private static final int HTTP_OK = 200;
	private static final String PGSI_PREFIX = "PGSi-";
	private static final String PINS_TAG_NAME = "pins";

	@Override
	public InternalResultsResponse<CallingCardInfo> fetchAvailablePins(CallingCardMaintenanceRequest request)
	{
		InternalResultsResponse<CallingCardInfo> response = new InternalResultsResponse<CallingCardInfo>();

		response = getCallingCardDAC().fetchAvailablePins(request);

		return response;
	}

	@Override
	public InternalResultsResponse<CallingCardInfo> getCardBalance(CallingCardMaintenanceRequest request)
	{
		InternalResultsResponse<CallingCardInfo> response = new InternalResultsResponse<CallingCardInfo>();

		// First thing to do is to retrieve the pin_id for the Member passed on the request
		Integer pinId = retrievePinIdByPersonId(request.getCallingCardInfo().getPersonId(), response);

		if (!response.isInError())
		{
			// Create a list of parameters that will go in the URL
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

			// Add pin id to list
			urlParameters.add(new BasicNameValuePair(ID_PIN, pinId.toString()));

			Document doc =
					callCallingCardApi(urlParameters, response, getMethodsProp().get(BALANCE_METHOD_KEY).toString());

			if (!response.isInError())
			{
				// Find the <BALANCE> Tags
				NodeList nList = doc.getElementsByTagName(BALANCE_TAG_NAME);

				if (ValidationUtil.isNull(nList) || (nList.getLength() == 0))
				{
					response.setStatus(Status.SystemError);
					response.addMessage(PROSPERITASGLOBAL_BASE_NO_TAG_FOUND, MessageSeverity.Fatal, MessageLevel.Other,
							new Object[] {BALANCE_TAG_NAME});
				}
				else
				{
					// Take first <BALANCE> tag
					Node nNode = nList.item(0);

					if (LOG.isDebugEnabled())
					{
						LOG.debug("Current Element :" + nNode.getNodeName());
					}

					if (nNode.getNodeType() == Node.ELEMENT_NODE)
					{
						Element eElement = (Element)nNode;
						CallingCardInfo callingCardInfo = new CallingCardInfo();

						// Get the values for different tags inside <BALANCE> and populate callingCardInfo
						callingCardInfo.setBalance(new BigDecimal(eElement.getElementsByTagName(BALANCE_TAG_NAME)
								.item(0).getTextContent()));
						callingCardInfo.setLotCode(eElement.getElementsByTagName("lot").item(0).getTextContent());
						callingCardInfo.setCallingCardNumber(eElement.getElementsByTagName("pin").item(0)
								.getTextContent());

						response.addResult(callingCardInfo);
						response.setStatus(Status.OperationSuccess);
					}
					else
					{
						response.setStatus(Status.SystemError);
						response.addInternalMessage(PROSPERITASGLOBAL_BASE_NO_CC_INFO_FOUND, MessageSeverity.Fatal,
								BALANCE_TAG_NAME);
					}
				}
			}
		}

		return response;
	}

	@Override
	public InternalResultsResponse<CallingCardInfo> refillCard(CallingCardMaintenanceRequest request)
	{
		InternalResultsResponse<CallingCardInfo> response = new InternalResultsResponse<CallingCardInfo>();

		// First thing to do is to retrieve the pin_id for the Member passed on the request
		Integer pinId = retrievePinIdByPersonId(request.getCallingCardInfo().getPersonId(), response);

		if (!response.isInError())
		{
			// Create a list of parameters that will go in the URL
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

			// Add the amount. Notice it is truncated to two decimals
			urlParameters.add(new BasicNameValuePair("amount", new DecimalFormat("0.00").format(request
					.getCallingCardInfo().getAmount())));
			urlParameters.add(new BasicNameValuePair(ID_PIN, pinId.toString()));

			Document doc =
					callCallingCardApi(urlParameters, response, getMethodsProp().get(REFILL_METHOD_KEY).toString());

			if (!response.isInError())
			{
				NodeList nList = doc.getElementsByTagName(RESULT_TAG_NAME);

				if (ValidationUtil.isNull(nList) || (nList.getLength() == 0))
				{
					response.setStatus(Status.ExternalError);
					response.addMessage(PROSPERITASGLOBAL_BASE_NO_TAG_FOUND, MessageSeverity.Fatal, MessageLevel.Other,
							new Object[] {RESULT_TAG_NAME});
				}
				else
				{
					// Assume an error to start with
					response.setStatus(Status.ExternalError);

					for (int temp = 0; temp < nList.getLength(); temp++)
					{
						Node nNode = nList.item(temp);

						if (LOG.isDebugEnabled())
						{
							LOG.debug("Current Element :" + nNode.getNodeName());
						}

						if (nNode.getNodeType() == Node.ELEMENT_NODE)
						{
							Element eElement = (Element)nNode;

							if ("OK".equalsIgnoreCase(eElement.getTextContent()))
							{
								response.setStatus(Status.OperationSuccess);
							}
						}
					}

					if (response.isInError())
					{
						response.addInternalMessage(PROSPERITASGLOBAL_BASE_REFILL_FAILED, MessageSeverity.Fatal,
								RESULT_TAG_NAME);
					}
				}
			}
		}

		return response;
	}

	/*
	 * This is the structure of this response
	 * <generate-pins>
	 * <invoice>18469487</invoice>
	 * -<pins>
	 * --<row nn="0">
	 * ---<row nn="0">8436013</row>
	 * ---<row nn="1">9850-607-165</row>
	 * --</row>
	 * --<row nn="1">
	 * ---<row nn="0">8436014</row>
	 * ---<row nn="1">8139-615-198</row>
	 * --</row>
	 * -</pins>
	 * </generate-pins>
	 */
	@Override
	public InternalResultsResponse<CallingCardInfo> retrieveMorePinNumbers(CallingCardMaintenanceRequest request)
	{
		InternalResultsResponse<CallingCardInfo> response = new InternalResultsResponse<CallingCardInfo>();

		String lotCode = generateLotCode();

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("card_id", getCallingCardType()));
		urlParameters.add(new BasicNameValuePair("qty", getQtyToGenerate().toString()));
		urlParameters.add(new BasicNameValuePair("lot", lotCode));

		Document doc =
				callCallingCardApi(urlParameters, response, getMethodsProp().get(GENERATE_METHOD_KEY).toString());

		if (!response.isInError())
		{
			NodeList nList = doc.getElementsByTagName(PINS_TAG_NAME);

			if (ValidationUtil.isNull(nList) || (nList.getLength() == 0))
			{
				response.setStatus(Status.ExternalError);
				response.addMessage(PROSPERITASGLOBAL_BASE_NO_TAG_FOUND, MessageSeverity.Fatal, MessageLevel.Other,
						new Object[] {PINS_TAG_NAME});
			}
			else
			{
				// <PINS> tag
				Node pinsNode = nList.item(0);

				// List of children for for the <PIN> tag. This include <ROW> tags, but also some text nodes that we
				// will ignore.
				NodeList rowList = pinsNode.getChildNodes();

				if (ValidationUtil.isNull(rowList) || (rowList.getLength() == 0))
				{
					response.setStatus(Status.ExternalError);
					response.addMessage(PROSPERITASGLOBAL_BASE_NO_TAG_FOUND, MessageSeverity.Fatal, MessageLevel.Other,
							new Object[] {PINS_TAG_NAME});
				}
				else
				{
					if (LOG.isDebugEnabled())
					{
						LOG.debug("rowList.getLength() :" + rowList.getLength());
					}

					// Cycle through the nodes
					for (int outerIndex = 0; outerIndex < rowList.getLength(); outerIndex++)
					{
						// one <ROW>
						Node rowNode = rowList.item(outerIndex);

						// Only use it if it is an Element
						if (!ValidationUtil.isNull(rowNode) && (rowNode.getNodeType() == Node.ELEMENT_NODE))
						{
							// Try to extract CallingCardInfo from <ROW>
							CallingCardInfo callingCardInfo = processPinInfo(rowNode, lotCode, response);

							// If not OK get out.
							if (response.isInError())
							{
								break;
							}
							else
							{
								response.addResult(callingCardInfo);
							}
						}
					}
				}
			}
		}

		return response;
	}

	@Override
	public InternalResultsResponse<CallingCardInfo> assignCard(CallingCardMaintenanceRequest request)
	{
		InternalResultsResponse<CallingCardInfo> response = new InternalResultsResponse<CallingCardInfo>();

		request.getCallingCardInfo().setModelAction(PersistanceActionEnum.UPDATE);

		applyContextParameters(request);

		response = getCallingCardDAC().updateCallingCard(request);

		return response;
	}

	/**
	 * Reads an XML {@link Node} passed as parameter and tries to retrieve {@link CallingCardInfo} to add to database.
	 *
	 * @param rowNode the row node
	 * @param lotCode the lot code
	 * @param response the response
	 * @return the calling card info
	 */
	protected CallingCardInfo processPinInfo(Node rowNode, String lotCode,
			InternalResultsResponse<CallingCardInfo> response)
	{
		CallingCardInfo callingCardInfo = null;
		Integer callingCardId = 0;
		String callingCardNumber = "";

		// List of children for for the <ROW> tag. This include the other <ROW> tags, but also some text nodes that we
		// will ignore.
		NodeList pinList = rowNode.getChildNodes();

		if (LOG.isDebugEnabled())
		{
			LOG.debug("pinList.getLength() :" + pinList.getLength());
		}

		for (int innerIndex = 0; innerIndex < pinList.getLength(); innerIndex++)
		{
			Node pinNode = pinList.item(innerIndex);

			if (!ValidationUtil.isNull(pinNode) && (pinNode.getNodeType() == Node.ELEMENT_NODE))
			{
				Element eElement = (Element)pinNode;

				// <row nn="0">8436013</row> --> pin_id
				// <row nn="1">9850-607-165</row> --> pin number
				switch (eElement.getAttribute("nn"))
				{
					case "0":
						callingCardId = Integer.parseInt(eElement.getTextContent());
						break;
					case "1":
						callingCardNumber = eElement.getTextContent();
						break;
					default:
						break;
				}
			}
		}

		if (ValidationUtil.isNullOrZero(callingCardId)
				|| ValidationUtil.isNullOrEmpty(callingCardNumber))
		{
			response.setStatus(Status.ExternalError);
			response.addInternalMessage(PROSPERITASGLOBAL_BASE_NO_PIN_INFO_FOUND,
					MessageSeverity.Fatal, "");
		}
		else
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("callingCardId :" + callingCardId);
				LOG.debug("callingCardNumber :" + callingCardNumber);
			}

			callingCardInfo = new CallingCardInfo();

			callingCardInfo.setCallingCardId(callingCardId);
			callingCardInfo.setCallingCardNumber(callingCardNumber);
			callingCardInfo.setLotCode(lotCode);
		}

		return callingCardInfo;
	}

	/**
	 * Fetch pin_id by person id passed as parameter.
	 *
	 * @param personId the person id
	 * @param response the response
	 * @return the pin_id
	 */
	private Integer retrievePinIdByPersonId(Integer personId, InternalResultsResponse<CallingCardInfo> response)
	{
		InternalResultsResponse<Integer> integerResponse = getCallingCardDAC().fetchIdPinByPersonId(personId);

		if (integerResponse.isInError())
		{
			response.setStatus(Status.NoRowsFoundError);
			response.addMessage(PROSPERITASGLOBAL_BASE_NO_CC_FOUND, MessageSeverity.Error, MessageLevel.Object,
					new Object[] {personId});
		}

		return integerResponse.getFirstResult();
	}

	/**
	 * Generates a lot code containing a prefix and date/time.
	 *
	 * @return the string
	 */
	private String generateLotCode()
	{
		SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd'-'hh:mm:ss");
		return PGSI_PREFIX + ft.format(new Date());
	}

	/**
	 * Calls Comfi calling card API and returns an XML {@link Document} as response.
	 *
	 * @param urlParameters the url parameters
	 * @param response the response
	 * @param methodName the method name
	 * @return the document
	 */
	private Document callCallingCardApi(List<NameValuePair> urlParameters,
			InternalResultsResponse<CallingCardInfo> response, String methodName)
	{
		Document doc = null;

		StringBuilder requestUrl =
				new StringBuilder(getCallingCardUrl() + methodName + getCallingCardResponseType());

		HttpClient client = HttpClientBuilder.create().build();

		urlParameters.add(new BasicNameValuePair("l_email", getCallingCardApiEmail()));
		urlParameters.add(new BasicNameValuePair("l_password", getCallingCardApiPwd()));

		try
		{
			requestUrl.append("?");
			requestUrl.append(URLEncodedUtils.format(urlParameters, "utf-8"));

			HttpPost post = new HttpPost(requestUrl.toString());

			if (LOG.isDebugEnabled())
			{
				LOG.debug("Executing request " + post.getRequestLine());
			}

			HttpResponse httpResponse = client.execute(post);

			if (LOG.isDebugEnabled())
			{
				LOG.debug("Response Code : " + httpResponse.getStatusLine().getStatusCode());
			}

			if (httpResponse.getStatusLine().getStatusCode() == HTTP_OK)
			{
				doc = readXml(httpResponse.getEntity().getContent(), response);
			}
			else
			{
				response.setStatus(Status.ExternalError);
				response.addInternalMessage(PROSPERITASGLOBAL_BASE_CC_HTTP_ERROR, MessageSeverity.Fatal, "");

				LOG.info("Http code: " + httpResponse.getStatusLine().getStatusCode());
			}
		}
		catch (IOException e)
		{
			response.setStatus(Status.ExceptionError);
			response.addInternalMessage(PROSPERITASGLOBAL_BASE_CC_IO_ERROR, MessageSeverity.Fatal, e.toString());

			LOG.info(e.getStackTrace().toString());
		}

		return doc;
	}

	/**
	 * Tries to build an XML {@link Document} by using the {@link InputStream} passed as parameter.
	 *
	 * @param inputStream the input stream
	 * @param response the response
	 * @return the document
	 */
	private Document readXml(InputStream inputStream, InternalResultsResponse<CallingCardInfo> response)
	{
		Document doc = null;

		try
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(inputStream);

			// optional, but recommended. Read this:
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			if (LOG.isDebugEnabled())
			{
				LOG.debug("Root element :" + doc.getDocumentElement().getNodeName());
			}
		}
		catch (Exception e)
		{
			response.setStatus(Status.ExceptionError);
			response.addInternalMessage(PROSPERITASGLOBAL_BASE_CC_CANNOT_READ_XML, MessageSeverity.Fatal, e.toString());

			LOG.info(e.getStackTrace().toString());
		}

		return doc;
	}

	protected void applyContextParameters(CallingCardMaintenanceRequest request)
	{
		if (request != null)
		{
			String userId = null;

			if (request.getUserContext() != null)
			{
				userId = request.getUserContext().getUserId();
			}

			CallingCardInfo info = request.getCallingCardInfo();

			if (PersistanceActionEnum.INSERT.equals(info.getModelAction()))
			{
				if (info.getCreateUser() == null)
				{
					info.setCreateUser(userId);
				}
				if (info.getCreateDateUTC() == null)
				{
					info.setCreateDateUTC(PGSiDateUtil.getCurrentDateMillisUTC());
				}
			}
			if (PersistanceActionEnum.UPDATE.equals(info.getModelAction()))
			{
				if (info.getModifyUser() == null)
				{
					info.setModifyUser(userId);
				}
				if (info.getModifyDateUTC() == null)
				{
					info.setModifyDateUTC(PGSiDateUtil.getCurrentDateMillisUTC());
				}
			}
		}
	}

	/**********************************
	 * Getters and Setters live below *
	 **********************************/
	public String getCallingCardUrl()
	{
		return callingCardUrl;
	}

	public void setCallingCardUrl(String callingCardUrl)
	{
		this.callingCardUrl = callingCardUrl;
	}

	public String getCallingCardResponseType()
	{
		return callingCardResponseType;
	}

	public void setCallingCardResponseType(String callingCardResponseType)
	{
		this.callingCardResponseType = callingCardResponseType;
	}

	public String getCallingCardApiEmail()
	{
		return callingCardApiEmail;
	}

	public void setCallingCardApiEmail(String callingCardApiEmail)
	{
		this.callingCardApiEmail = callingCardApiEmail;
	}

	public String getCallingCardApiPwd()
	{
		return callingCardApiPwd;
	}

	public void setCallingCardApiPwd(String callingCardApiPwd)
	{
		this.callingCardApiPwd = callingCardApiPwd;
	}

	public Properties getMethodsProp()
	{
		return methodsProp;
	}

	public void setMethodsProp(Properties methodsProp)
	{
		this.methodsProp = methodsProp;
	}

	public Integer getQtyToGenerate()
	{
		return qtyToGenerate;
	}

	public void setQtyToGenerate(Integer qtyToGenerate)
	{
		this.qtyToGenerate = qtyToGenerate;
	}

	public ICallingCardDAC getCallingCardDAC()
	{
		return callingCardDAC;
	}

	public void setCallingCardDAC(ICallingCardDAC callingCardDAC)
	{
		this.callingCardDAC = callingCardDAC;
	}

	public String getCallingCardType()
	{
		return callingCardType;
	}

	public void setCallingCardType(String callingCardType)
	{
		this.callingCardType = callingCardType;
	}
}
