package com.prosperitasglobal.sendsolv.produto.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IProdutoBAI;
import com.prosperitasglobal.sendsolv.model.request.ProdutoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.prosperitasglobal.sendsolv.model.response.ProdutoResponse;
import com.qat.framework.validation.ValidationUtil;

public class ProdutoBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProdutoBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ProdutoBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Produto BAI. */
	private IProdutoBAI produtoBAI;

	/**
	 * Gets the produto bai.
	 *
	 * @return the produto bai
	 */
	public IProdutoBAI getProdutoBAI()
	{
		return produtoBAI;
	}

	/**
	 * Sets the produto bai.
	 *
	 * @param produtoBAI the produto bai
	 */
	@Resource
	public void setProdutoBAI(IProdutoBAI produtoBAI)
	{
		this.produtoBAI = produtoBAI;
	}

	/**
	 * Produto edit mav.
	 *
	 * @param produtoId the produto id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView produtoEditMAV(Integer produtoId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(produtoId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchProdutoById(new FetchByIdRequest(produtoId))));

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
	 * Fetch enrolled members.
	 *
	 * @param produtoId the produto id
	 * @return the integer
	 */
	private Integer fetchEnrolledMembers(Integer produtoId, HttpServletRequest request)
	{
		MemberResponse memberResponse = fetchMembersEnrolledMember(produtoId, BusinessTypeEnum.LOCATION, request);

		if (memberResponse.getMemberList() != null)
		{
			return memberResponse.getMemberList().size();
		}

		return 0;
	}

	/**
	 * Fetch produto by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the produto response
	 */
	public ProdutoResponse fetchProdutoByRequest(ProdutoInquiryRequest pagedInquiryRequest)
	{

		ProdutoResponse produtoResponse = new ProdutoResponse();
		try
		{

			produtoResponse = getProdutoBAI().fetchProdutoByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return produtoResponse;
	}

	/**
	 * Fetch produto by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the produto response
	 */
	public ProdutoResponse fetchProdutoById(FetchByIdRequest fetchByIdRequest)
	{

		ProdutoResponse produtoResponse = new ProdutoResponse();
		try
		{

			produtoResponse = getProdutoBAI().fetchProdutoById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return produtoResponse;
	}
}
