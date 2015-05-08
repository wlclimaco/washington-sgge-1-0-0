package com.prosperitasglobal.sendsolv.payment.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI;
import com.prosperitasglobal.sendsolv.filter.FilterFactory;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferStatusMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferResponse;
import com.qat.framework.model.response.MaintenanceResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class PaymentBaseController.
 */

/**
 * @author QAT BRAZIL
 *
 */
public class TransactionBaseController extends PaymentBaseController
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TransactionBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "TransactionBaseController";

	/** The money transfer bai. */
	private IMoneyTransferBAI moneyTransferBAI;

	/** The filter factory. */
	private FilterFactory filterFactory;

	/**
	 * Gets the filter factory.
	 *
	 * @return the filterFactory
	 */
	@Override
	public FilterFactory getFilterFactory()
	{
		return filterFactory;
	}

	/**
	 * Sets the filter factory.
	 *
	 * @param filterFactory the filterFactory to set
	 */
	@Override
	@Resource
	public void setFilterFactory(FilterFactory filterFactory)
	{
		this.filterFactory = filterFactory;
	}

	/**
	 * Gets the money transfer bai.
	 *
	 * @return the money transfer bai
	 */
	@Override
	public IMoneyTransferBAI getMoneyTransferBAI()
	{
		return moneyTransferBAI;
	}

	/**
	 * Sets the money transfer bai.
	 *
	 * @param moneyTransferBAI the new money transfer bai
	 */
	@Override
	@Resource
	public void setMoneyTransferBAI(IMoneyTransferBAI moneyTransferBAI)
	{
		this.moneyTransferBAI = moneyTransferBAI;
	}

	/**
	 * Batches edit mav.
	 *
	 * @param returnViewName the return view name
	 * @param transactionId the batche id
	 * @param view the view
	 * @return the model and view
	 */
	protected ModelAndView transactionEditMAV(String returnViewName, Integer transactionId, HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{
			if (!ValidationUtil.isNullOrZero(transactionId))
			{
				FetchByIdRequest fetchByIdRequest = new FetchByIdRequest(transactionId);
				fetchByIdRequest.setUserContext(fetchUserContext(request));
				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchByIdRequest));

			}

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return modelAndView;
	}

	@Override
	public MoneyTransferResponse fetchTransactionsByRequest(MoneyTransferInquiryRequest request)
	{

		MoneyTransferResponse moneyTransferResponse = new MoneyTransferResponse();
		try
		{

			moneyTransferResponse = getMoneyTransferBAI().fetchMoneyTransferByRequest(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return moneyTransferResponse;
	}

	/**
	 * Fetch transfer batches by request.
	 *
	 * @param request the request
	 * @return the money transfer response
	 */
	@Override
	public MoneyTransferResponse fetchTransferBatchesByRequest(MoneyTransferInquiryRequest request)
	{

		MoneyTransferResponse moneyTransferResponse = new MoneyTransferResponse();
		try
		{

			moneyTransferResponse = getMoneyTransferBAI().fetchMoneyTransferByRequest(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return moneyTransferResponse;
	}

	/**
	 * Update money transfer.
	 *
	 * @param moneyTransferRequest the money transfer request
	 * @return the maintenance response
	 */
	@Override
	public MaintenanceResponse updateMoneyTransfer(MoneyTransferMaintenanceRequest moneyTransferRequest)
	{
		MaintenanceResponse moneyTransferResponse = new MaintenanceResponse();

		try
		{

			moneyTransferResponse = getMoneyTransferBAI().updateMoneyTransfer(moneyTransferRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return moneyTransferResponse;
	}

	@Override
	public MaintenanceResponse insertMoneyTransferStatus(MoneyTransferStatusMaintenanceRequest moneyTransferRequest)
	{
		MaintenanceResponse moneyTransferResponse = new MaintenanceResponse();

		try
		{

			moneyTransferResponse = getMoneyTransferBAI().insertMoneyTransferStatus(moneyTransferRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return moneyTransferResponse;
	}

	/**
	 * Update.
	 *
	 * @param request the request
	 * @return the maintenance response
	 */
	@Override
	public MaintenanceResponse update(MoneyTransferBatchMaintenanceRequest request)
	{

		MaintenanceResponse moneyTransferResponse = new MaintenanceResponse();
		try
		{

			moneyTransferResponse = getMoneyTransferBAI().updateMoneyTransferBatch(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return moneyTransferResponse;
	}

	@Override
	public MoneyTransferResponse fetchMoneyTransferById(FetchByIdRequest request)
	{
		MoneyTransferResponse moneyTransferResponse = new MoneyTransferResponse();
		try
		{
			moneyTransferResponse = getMoneyTransferBAI().fetchMoneyTransferById(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return moneyTransferResponse;

	}
}
