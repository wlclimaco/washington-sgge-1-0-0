package com.prosperitasglobal.sendsolv.report.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.sendsolv.common.controller.BaseViewController;

@Controller
@RequestMapping("/reports")
public class ReportViewController extends BaseViewController
{

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView load(HttpServletRequest request)
	{
		return new ModelAndView("/reports/reports_main");
	}
}
