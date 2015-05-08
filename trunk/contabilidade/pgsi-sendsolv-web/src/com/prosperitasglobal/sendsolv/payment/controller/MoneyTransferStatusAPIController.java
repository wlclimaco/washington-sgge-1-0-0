package com.prosperitasglobal.sendsolv.payment.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI;
import com.prosperitasglobal.sendsolv.common.controller.BaseController;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferStatusMaintenanceRequest;
import com.qat.framework.model.response.MaintenanceResponse;

/**
 * The MoneyTransferStatusAPIController Class.
 *
 * @author Flavio Tosta
 *
 */

@Controller
@RequestMapping("/api/moneyTransferStatus")
public class MoneyTransferStatusAPIController extends BaseController
{

	private static final Logger LOG = LoggerFactory.getLogger(PaymentBaseController.class);
	private static final String CONTROLLER_EXCEPTION_MSG = "MoneyTransferStatusController";

	private IMoneyTransferBAI moneyTransferBai;

	/**
	 * @return the moneyTransferBai
	 */
	public IMoneyTransferBAI getMoneyTransferBai()
	{
		return moneyTransferBai;
	}

	/**
	 * @param moneyTransferBai the moneyTransferBai to set
	 */
	@Resource
	public void setMoneyTransferBai(IMoneyTransferBAI moneyTransferBai)
	{
		this.moneyTransferBai = moneyTransferBai;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public MaintenanceResponse insert(@RequestBody MoneyTransferStatusMaintenanceRequest moneyTransferStatusRequest)
	{
		MaintenanceResponse maintenanceResponse = new MaintenanceResponse();

		try
		{

			maintenanceResponse = getMoneyTransferBai().insertMoneyTransferStatus(moneyTransferStatusRequest);
		}

		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return maintenanceResponse;
	}
}
