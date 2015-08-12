package com.prosperitasglobal.sendsolv.empresa.controller;

import java.util.Calendar;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IEmpresaBAI;
import com.prosperitasglobal.sendsolv.common.util.IAsyncDMFacade;
import com.prosperitasglobal.sendsolv.model.request.CidadeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CnaeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.DepositoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.DepositoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.EmpresaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EmpresaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.FilialInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FilialMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.RegimeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.CidadeResponse;
import com.prosperitasglobal.sendsolv.model.response.CnaeResponse;
import com.prosperitasglobal.sendsolv.model.response.DepositoResponse;
import com.prosperitasglobal.sendsolv.model.response.EmpresaResponse;
import com.prosperitasglobal.sendsolv.model.response.FilialResponse;
import com.prosperitasglobal.sendsolv.model.response.RegimeResponse;
import com.qat.framework.validation.ValidationUtil;

public class EmpresaBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EmpresaBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "EmpresaBaseController";

	/** The Empresa BAI. */
	private IEmpresaBAI empresaBAI;

	private IAsyncDMFacade asyncDMFacade;

	public IAsyncDMFacade getAsyncDMFacade()
	{
		return asyncDMFacade;
	}

	@Resource
	public void setAsyncDMFacade(IAsyncDMFacade asyncDMFacade)
	{
		this.asyncDMFacade = asyncDMFacade;
	}

	/**
	 * Gets the empresa bai.
	 *
	 * @return the empresa bai
	 */
	@Override
	public IEmpresaBAI getEmpresaBAI()
	{
		return empresaBAI;
	}

	/**
	 * Sets the empresa bai.
	 *
	 * @param empresaBAI the empresa bai
	 */
	@Override
	@Resource
	public void setEmpresaBAI(IEmpresaBAI empresaBAI)
	{
		this.empresaBAI = empresaBAI;
	}

	/**
	 * Empresa edit mav.
	 *
	 * @param empresaId the empresa id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView empresaEditMAV(Integer empresaId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(empresaId))
			{

				modelAndView.addObject("cidadeResponse", getMapper()
						.writeValueAsString(loadColumns(new CidadeInquiryRequest())));

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchEmpresaById(new FetchByIdRequest(empresaId))));

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
	 * Fetch empresa by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the empresa response
	 */
	public EmpresaResponse fetchEmpresaByRequest(EmpresaInquiryRequest pagedInquiryRequest)
	{

		EmpresaResponse empresaResponse = new EmpresaResponse();
		try
		{

			empresaResponse = getEmpresaBAI().fetchEmpresaByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return empresaResponse;
	}

	// filial
	public FilialResponse fetchFilialByRequest(FilialInquiryRequest pagedInquiryRequest)
	{

		FilialResponse empresaResponse = new FilialResponse();
		try
		{

			empresaResponse = getEmpresaBAI().fetchFilialByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return empresaResponse;
	}

	// deposito
	public DepositoResponse fetchDepositoByRequest(DepositoInquiryRequest pagedInquiryRequest)
	{

		DepositoResponse empresaResponse = new DepositoResponse();
		try
		{

			empresaResponse = getEmpresaBAI().fetchDepositoByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return empresaResponse;
	}

	// cnae
	public CnaeResponse fetchCnaeByRequest(CnaeInquiryRequest pagedInquiryRequest)
	{

		CnaeResponse empresaResponse = new CnaeResponse();
		try
		{

			empresaResponse = getEmpresaBAI().fetchCnaeByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return empresaResponse;
	}

	// regime
	public RegimeResponse fetchRegimeByRequest(RegimeInquiryRequest pagedInquiryRequest)
	{

		RegimeResponse empresaResponse = new RegimeResponse();
		try
		{

			empresaResponse = getEmpresaBAI().fetchRegimeByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return empresaResponse;
	}

	/**
	 * Fetch empresa by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the empresa response
	 */
	public EmpresaResponse fetchEmpresaById(FetchByIdRequest fetchByIdRequest)
	{

		EmpresaResponse empresaResponse = new EmpresaResponse();
		try
		{

			empresaResponse = getEmpresaBAI().fetchEmpresaById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return empresaResponse;
	}

	public FilialResponse fetchFilialById(FetchByIdRequest fetchByIdRequest)
	{

		FilialResponse empresaResponse = new FilialResponse();
		try
		{

			empresaResponse = getEmpresaBAI().fetchFilialById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return empresaResponse;
	}

	public DepositoResponse fetchDepositoById(FetchByIdRequest fetchByIdRequest)
	{

		DepositoResponse empresaResponse = new DepositoResponse();
		try
		{

			empresaResponse = getEmpresaBAI().fetchDepositoById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return empresaResponse;
	}

	public EmpresaResponse insert(EmpresaMaintenanceRequest locationRequest)
	{
		EmpresaResponse locationResponse = new EmpresaResponse();

		try
		{

			locationRequest.getEmpresa().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			locationResponse = getEmpresaBAI().insertEmpresa(locationRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

	}

	public FilialResponse insert(FilialMaintenanceRequest locationRequest)
	{
		FilialResponse locationResponse = new FilialResponse();

		try
		{

			locationRequest.getFilial().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			locationResponse = getEmpresaBAI().insertFilial(locationRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

	}

	public DepositoResponse insert(DepositoMaintenanceRequest locationRequest)
	{
		DepositoResponse locationResponse = new DepositoResponse();

		try
		{

			locationRequest.getDeposito().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			locationResponse = getEmpresaBAI().insertDeposito(locationRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

	}

	public EmpresaResponse delete(EmpresaMaintenanceRequest locationRequest)
	{
		EmpresaResponse locationResponse = new EmpresaResponse();
		try
		{

			locationResponse = getEmpresaBAI().deleteEmpresa(locationRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

	}

	public FilialResponse delete(FilialMaintenanceRequest locationRequest)
	{
		FilialResponse locationResponse = new FilialResponse();
		try
		{

			locationResponse = getEmpresaBAI().deleteFilial(locationRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

	}

	public DepositoResponse delete(DepositoMaintenanceRequest locationRequest)
	{
		DepositoResponse locationResponse = new DepositoResponse();
		try
		{

			locationResponse = getEmpresaBAI().deleteDeposito(locationRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

	}

	public EmpresaResponse edit(EmpresaMaintenanceRequest locationRequest)
	{
		EmpresaResponse locationResponse = new EmpresaResponse();
		try
		{

			locationResponse = getEmpresaBAI().updateEmpresa(locationRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}
		return locationResponse;

	}

	public FilialResponse edit(FilialMaintenanceRequest locationRequest)
	{
		FilialResponse locationResponse = new FilialResponse();
		try
		{

			locationResponse = getEmpresaBAI().updateFilial(locationRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}
		return locationResponse;

	}

	public DepositoResponse edit(DepositoMaintenanceRequest locationRequest)
	{
		DepositoResponse locationResponse = new DepositoResponse();
		try
		{

			locationResponse = getEmpresaBAI().updateDeposito(locationRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}
		return locationResponse;

	}

	public Future<CidadeResponse> loadColumns(
			CidadeInquiryRequest inquiryPaginationRequest)
	{

		return getAsyncDMFacade().<CidadeResponse> callAsyncMethod(getEmpresaBAI(), "fetchCidadeByRequest",
				inquiryPaginationRequest, null);

	}

}
