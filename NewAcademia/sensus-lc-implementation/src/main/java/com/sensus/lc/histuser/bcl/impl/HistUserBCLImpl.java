package com.sensus.lc.histuser.bcl.impl;

import javax.annotation.Resource;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.analytics.bcl.IAnalyticsBCL;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.base.util.LCHelp;
import com.sensus.lc.histuser.bcl.IHistUserBCL;
import com.sensus.lc.histuser.dac.IHistUserDAC;
import com.sensus.lc.histuser.model.HistUser;
import com.sensus.lc.histuser.model.request.HistUserRequest;
import com.sensus.lc.histuser.model.request.InquiryHistUserRequest;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.process.bcl.IProcessBCL;
import com.sensus.lc.server.client.MlcServerClient;
import com.sensus.lc.tag.bcl.ITagBCL;

public class HistUserBCLImpl implements IHistUserBCL
{

	/** The Constant ALL_HISTUSER_REMOVED. */
	private static final String ALL_HISTUSER_REMOVED = "sensus.mlc.histUserbcfimpl.histUsers.deleted";

	/** The lc help. */
	private LCHelp lcHelp; // injected by Spring through setter

	/** The histUser dac. */
	private IHistUserDAC histUserDAC; // injected by Spring through setter

	/** The tag bcl. */
	private ITagBCL tagBCL; // injected by Spring through setter

	/** The light bcl. */
	private ILightBCL lightBCL; // injected by Spring through setter

	/** The process bcl. */
	private IProcessBCL processBCL; // injected by Spring through setter

	/** The analytics bcl. */
	private IAnalyticsBCL analyticsBCL; // injected by Spring through setter

	/** The Constant MAX_HISTUSER_PER_LIGHT. */
	private static final Integer MAX_HISTUSER_PER_LIGHT = 5;

	/** The Constant AUTO_GROUP_REMOVED. */
	private static final String AUTO_GROUP_REMOVED = "sensus.mlc.tagvalidator.autohistUser.removed";

	/** The Constant GROUP_REMOVED. */
	private static final String GROUP_REMOVED = "sensus.mlc.histUserbcfimpl.mlchistUserdeleted";

	/** The Constant PROCESS_RUNNING. */
	private static final String PROCESS_RUNNING = "sensus.mlc.histUserbcfimpl.processrunning";

	/** The Constant NO_LIGHTS_IN_HISTUSER. */
	private static final String NO_LIGHTS_IN_HISTUSER = "sensus.mlc.mlc_action.light_status.no.lights_in_histUsers";

	/** The mlc gateway ws. */
	private MlcServerClient mlcGatewayWs;

	/**
	 * Gets the lc help.
	 * 
	 * @return the lc help
	 */
	public LCHelp getLcHelp()
	{
		return lcHelp;
	}

	/**
	 * Sets the lc help.
	 * 
	 * @param lcHelp the new lc help
	 */
	public void setLcHelp(LCHelp lcHelp)
	{
		this.lcHelp = lcHelp;
	}

	/**
	 * Gets the tag bcl.
	 * 
	 * @return the tag bcl
	 */
	public ITagBCL getTagBCL()
	{
		return tagBCL;
	}

	/**
	 * Sets the tag bcl.
	 * 
	 * @param tagBCL the new tag bcl
	 */
	public void setTagBCL(ITagBCL tagBCL)
	{
		this.tagBCL = tagBCL;
	}

	/**
	 * Sets the histUser dac.
	 * 
	 * @param histUserDACParam the new histUser dac
	 */
	public void setHistUserDAC(IHistUserDAC histUserDACParam)
	{
		histUserDAC = histUserDACParam;
	}

	/**
	 * Gets the histUser dac.
	 * 
	 * @return the histUser dac
	 */
	public IHistUserDAC getHistUserDAC()
	{
		return histUserDAC;
	}

	/**
	 * Gets the light bcl.
	 * 
	 * @return the light bcl
	 */
	public ILightBCL getLightBCL()
	{
		return lightBCL;
	}

	/**
	 * Sets the light bcl.
	 * 
	 * @param lightBCL the new light bcl
	 */
	@Resource
	public void setLightBCL(ILightBCL lightBCL)
	{
		this.lightBCL = lightBCL;
	}

	/**
	 * Gets the process bcl.
	 * 
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return processBCL;
	}

	/**
	 * Sets the process bcl.
	 * 
	 * @param processBCL the new process bcl
	 */
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/**
	 * Sets the mlc gateway ws.
	 * 
	 * @param mlcGatewayWs the new mlc gateway ws
	 */
	public void setMlcGatewayWs(MlcServerClient mlcGatewayWs)
	{
		this.mlcGatewayWs = mlcGatewayWs;
	}

	/**
	 * Gets the mlc gateway ws.
	 * 
	 * @return the mlc gateway ws
	 */
	public MlcServerClient getMlcGatewayWs()
	{
		return mlcGatewayWs;
	}

	/**
	 * Gets the analytics bcl.
	 * 
	 * @return the analytics bcl
	 */
	public IAnalyticsBCL getAnalyticsBCL()
	{
		return analyticsBCL;
	}

	/**
	 * Sets the analytics bcl.
	 * 
	 * @param analyticsBCL the new analytics bcl
	 */
	public void setAnalyticsBCL(IAnalyticsBCL analyticsBCL)
	{
		this.analyticsBCL = analyticsBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.histUser.bcl.IHistUserBCL#insertHistUser(com.sensus.mlc.histUser.model.request.HistUserRequest)
	 */
	@Override
	public InternalResultsResponse<HistUser> insertHistUser(HistUserRequest histUserRequest)
	{
		InternalResultsResponse<HistUser> response = getHistUserDAC().insertHistUser(histUserRequest);

		if (!response.isInError())
		{
			// create a Process for the created histUser

			// insert default values to analytics summarized tables.
			AnalyticsRequest analyticsRequest = new AnalyticsRequest();
			analyticsRequest.setUserContext(histUserRequest.getUserContext());

			InternalResponse internalResponse = getAnalyticsBCL().insertAnalyticsSummarized(analyticsRequest);

			if (internalResponse.isInError())
			{
				response.addMessages(response.getMessageInfoList());
			}
		}
		return response;
	}

	@Override
	public InternalResultsResponse<HistUser> fetchAllHistUsers(InquiryHistUserRequest inquiryHistUserRequest)
	{
		return getHistUserDAC().fetchAllHistUsers(inquiryHistUserRequest);
	}

	@Override
	public InternalResultsResponse<HistUser> fetchHistUserById(InquiryHistUserRequest inquiryHistUserRequest)
	{
		return getHistUserDAC().fetchHistUserById(inquiryHistUserRequest);
	}

	@Override
	public InternalResultsResponse<HistUser> fetchHistUsersByUser(InquiryHistUserRequest histUserRequest)
	{
		return getHistUserDAC().fetchHistUserByUser(histUserRequest);
	}

}
