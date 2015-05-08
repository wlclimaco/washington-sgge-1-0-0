package com.prosperitasglobal.sendsolv.payment.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI;
import com.prosperitasglobal.sendsolv.bai.IPayerBAI;
import com.prosperitasglobal.sendsolv.filter.FilterFactory;
import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferAutoApprovalRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferStatusMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferAutoApprovalResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchDTOResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferResponse;
import com.prosperitasglobal.sendsolv.model.response.PayerResponse;
import com.qat.framework.model.response.MaintenanceResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class PaymentBaseController.
 */

/**
 * @author QAT BRAZIL
 *
 */
public class PaymentBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	public static final Logger LOG = LoggerFactory.getLogger(PaymentBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "LocationBaseController";

	/** The Constant FILTER_ACTION_TYPE. */
	private static final String FILTER_ACTION_TYPE = "TRANSFERSTATUS";

	/** The Location BAI. */
	private ILocationBAI locationBAI;

	private IPayerBAI payerBAI;

	/** The money transfer bai. */
	private IMoneyTransferBAI moneyTransferBAI;

	/** The filter factory. */
	private FilterFactory filterFactory;

	/**
	 * @return the payerBAI
	 */
	public IPayerBAI getPayerBAI()
	{
		return payerBAI;
	}

	/**
	 * @param payerBAI the payerBAI to set
	 */
	@Resource
	public void setPayerBAI(IPayerBAI payerBAI)
	{
		this.payerBAI = payerBAI;
	}

	/**
	 * Gets the filter factory.
	 *
	 * @return the filterFactory
	 */
	public FilterFactory getFilterFactory()
	{
		return filterFactory;
	}

	/**
	 * Sets the filter factory.
	 *
	 * @param filterFactory the filterFactory to set
	 */
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
	public IMoneyTransferBAI getMoneyTransferBAI()
	{
		return moneyTransferBAI;
	}

	/**
	 * Sets the money transfer bai.
	 *
	 * @param moneyTransferBAI the new money transfer bai
	 */
	@Resource
	public void setMoneyTransferBAI(IMoneyTransferBAI moneyTransferBAI)
	{
		this.moneyTransferBAI = moneyTransferBAI;
	}

	/**
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public ILocationBAI getLocationBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the location bai
	 */
	@Resource
	public void setLocationBAI(ILocationBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Batches edit mav.
	 *
	 * @param returnViewName the return view name
	 * @param batcheId the batche id
	 * @param view the view
	 * @return the model and view
	 */
	protected ModelAndView batchesEditMAV(String returnViewName, Integer batcheId, Boolean view)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{
			if (!ValidationUtil.isNullOrZero(batcheId))
			{
				// Filters
				FiltersResponse filtersResponse = new FiltersResponse();
				getFilterFactory().configureFilter(FILTER_ACTION_TYPE, null, filtersResponse);

				if (view)
				{
					modelAndView.addObject(
							RESPONSE,
							getMapper().writeValueAsString(
									fecthMoneyTransferBatchWithSummaryById(new FetchByIdRequest(batcheId))));
				}
				else
				{
					modelAndView.addObject(RESPONSE,
							getMapper().writeValueAsString(fetchBatchesById(new FetchByIdRequest(batcheId))));
				}
				modelAndView.addObject("filters", getMapper().writeValueAsString(filtersResponse));
				return modelAndView;
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

	/**
	 * Fetch location by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the location response
	 */
	public LocationResponse fetchLocationByRequest(PagedInquiryRequest pagedInquiryRequest)
	{

		LocationResponse locationResponse = new LocationResponse();
		try
		{

			locationResponse =
					getLocationBAI().fetchLocationByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return locationResponse;
	}

	/**
	 * Fetch batches by request.
	 *
	 * @param request the request
	 * @return the money transfer batch response
	 */
	public MoneyTransferBatchResponse fetchBatchesByRequest(MoneyTransferBatchInquiryRequest request)
	{

		MoneyTransferBatchResponse moneyTransferBatchResponse = new MoneyTransferBatchResponse();
		try
		{

			moneyTransferBatchResponse = getMoneyTransferBAI().fetchMoneyTransferBatchByRequest(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return moneyTransferBatchResponse;
	}

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
	 * Fetch batches by id.
	 *
	 * @param request the request
	 * @return the money transfer batch response
	 */
	public MoneyTransferBatchResponse fetchBatchesById(FetchByIdRequest request)
	{

		MoneyTransferBatchResponse moneyTransferBatchResponse = new MoneyTransferBatchResponse();
		try
		{

			moneyTransferBatchResponse = getMoneyTransferBAI().fetchMoneyTransferBatchById(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return moneyTransferBatchResponse;
	}

	/**
	 * Fecth money transfer batch with summary by id.
	 *
	 * @param request the request
	 * @return the money transfer batch dto response
	 */
	public MoneyTransferBatchDTOResponse fecthMoneyTransferBatchWithSummaryById(FetchByIdRequest request)
	{

		MoneyTransferBatchDTOResponse moneyTransferBatchResponse = new MoneyTransferBatchDTOResponse();

		try
		{

			moneyTransferBatchResponse = getMoneyTransferBAI().fetchMoneyTransferBatchWithSummaryById(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return moneyTransferBatchResponse;
	}

	/**
	 * Fetch transfer batches by request.
	 *
	 * @param request the request
	 * @return the money transfer response
	 */
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

	/**
	 *
	 * @param request
	 * @return
	 */
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

	public PayerResponse fetchPayerByRequest(PayerInquiryRequest request)
	{

		PayerResponse payerResponse = new PayerResponse();
		try
		{
			payerResponse = getPayerBAI().fetchPayerByRequest(request);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return payerResponse;
	}

	public MoneyTransferAutoApprovalResponse fetchMoneyTransferAutoApprovalByTransferSetting(
			MoneyTransferAutoApprovalRequest request)
	{
		MoneyTransferAutoApprovalResponse moneyTransferResponse = new MoneyTransferAutoApprovalResponse();

		try
		{

			moneyTransferResponse = getMoneyTransferBAI().fetchMoneyTransferAutoApprovalByTransferSetting(request);

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
