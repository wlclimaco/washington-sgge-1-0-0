package com.prosperitasglobal.sendsolv.integration.twilio.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.sendsolv.common.controller.BaseController;
import com.prosperitasglobal.sendsolv.integration.twilio.bai.IIvrBAI;
import com.prosperitasglobal.sendsolv.integration.twilio.model.IvrIdentity;
import com.prosperitasglobal.sendsolv.integration.twilio.model.SupportedLanguage;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.prosperitasglobal.sendsolv.integration.twilio.model.response.IvrResponse;
import com.qat.framework.validation.ValidationUtil;

@Controller
@RequestMapping("/ivrapi")
public class IvrAPIController extends BaseController
{
	private static final String UTF_8 = "utf-8";

	private static final String PROCESS = "/process";

	private static final String DISCONNECT = "/disconnect";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(IvrAPIController.class);

	/** The organization bai. */
	private IIvrBAI ivrBAI;

	private String mainUrl;

	private String controllerPath;

	private String resourcePath;

	private String staticResourcePath;

	private List<SupportedLanguage> supportedLanguagesList;

	@ResponseBody
	@RequestMapping(value = PROCESS, method = RequestMethod.POST)
	public ResponseEntity<byte[]> processAction(HttpServletRequest request)
	{
		IvrResponse ivrResponse = new IvrResponse();

		try
		{
			ivrResponse = getIvrBAI().processAction(prepareRequest(request));

			// catch error when ivrResponse.getTwimlResponseList() is null
			String xml = ivrResponse.getTwimlResponseList().get(0);

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.TEXT_XML);
			headers.setCacheControl("no-cache");

			return new ResponseEntity<byte[]>(xml.getBytes(UTF_8), headers, HttpStatus.OK);
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private IvrRequest prepareRequest(HttpServletRequest request)
	{
		IvrRequest ivrRequest = new IvrRequest();
		IvrIdentity ivrIdentity = new IvrIdentity();

		// remove the '+' from the phone.
		if (!ValidationUtil.isNullOrEmpty(request.getParameter("From")))
		{
			ivrIdentity.setPhoneNumber(request.getParameter("From").substring(1));
		}

		ivrRequest.setDigits(request.getParameter("Digits"));
		ivrRequest.setCallSid(request.getParameter("CallSid"));

		ivrRequest.setIvrController(request.getParameter("action"));
		ivrRequest.setAction(request.getParameter("subaction"));
		ivrRequest.setPreviousController(request.getParameter("previous"));
		ivrRequest.setIvrIdentity(ivrIdentity);

		ivrRequest.setMainUrl(getMainUrl());
		ivrRequest.setControllerPath(getControllerPath());
		ivrRequest.setResourcePath(getResourcePath());
		ivrRequest.setStaticResourcePath(getStaticResourcePath());
		ivrRequest.setSupportedLanguagesList(getSupportedLanguagesList());

		if (LOG.isDebugEnabled())
		{
			LOG.debug("*********************");
			LOG.debug("IvrRequest being passed: " + ivrRequest.toString());
			LOG.debug("*********************");
		}

		return ivrRequest;
	}

	/**
	 * Called by Twilio when the call is completed
	 *
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = DISCONNECT, method = RequestMethod.POST)
	public void processDisconnect(HttpServletResponse response, HttpServletRequest request) throws Exception
	{
		IvrRequest ivrRequest = new IvrRequest();
		ivrRequest.setCallSid(request.getParameter("CallSid"));
		ivrRequest.setDurationInSeconds(Integer.parseInt(request.getParameter("CallDuration")));
		getIvrBAI().processDisconnect(ivrRequest);

		// Need to send OK response to Twilio
		response.sendError(HttpServletResponse.SC_OK);
	}

	public IIvrBAI getIvrBAI()
	{
		return ivrBAI;
	}

	@Resource
	public void setIvrBAI(IIvrBAI ivrBAI)
	{
		this.ivrBAI = ivrBAI;
	}

	public List<SupportedLanguage> getSupportedLanguagesList()
	{
		return supportedLanguagesList;
	}

	@Resource
	public void setSupportedLanguagesList(List<SupportedLanguage> supportedLanguagesList)
	{
		this.supportedLanguagesList = supportedLanguagesList;
	}

	public String getResourcePath()
	{
		return resourcePath;
	}

	@Resource
	public void setResourcePath(String resourcePath)
	{
		this.resourcePath = resourcePath;
	}

	public String getMainUrl()
	{
		return mainUrl;
	}

	@Resource
	public void setMainUrl(String mainUrl)
	{
		this.mainUrl = mainUrl;
	}

	public String getControllerPath()
	{
		return controllerPath;
	}

	@Resource
	public void setControllerPath(String controllerPath)
	{
		this.controllerPath = controllerPath;
	}

	public String getStaticResourcePath()
	{
		return staticResourcePath;
	}

	@Resource
	public void setStaticResourcePath(String staticResourcePath)
	{
		this.staticResourcePath = staticResourcePath;
	}
}
