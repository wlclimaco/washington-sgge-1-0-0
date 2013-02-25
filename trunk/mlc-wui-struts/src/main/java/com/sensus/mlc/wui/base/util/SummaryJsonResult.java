package com.sensus.mlc.wui.base.util;

import java.util.Map;

import com.sensus.mlc.wui.base.model.JsonResult;
import com.sensus.mlc.wui.process.model.SummaryProcessView;

public class SummaryJsonResult extends JsonResult
{
	private Map<String, String> time;

	private SummaryProcessView processView;

	public Map<String, String> getTime()
	{
		return time;
	}

	public void setTime(Map<String, String> time)
	{
		this.time = time;
	}

	public SummaryProcessView getProcessView()
	{
		return processView;
	}

	public void setProcessView(SummaryProcessView processView)
	{
		this.processView = processView;
	}

}
