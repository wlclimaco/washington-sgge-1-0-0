package com.prosperitasglobal.sendsolv.filter.impl;

import org.springframework.stereotype.Component;

import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.qat.framework.model.UserContext;

/**
 * The Class FilterTags.
 */
@Component
public class FilterEmployer extends AbstractFilterBase
{
	/** The Constant TAGS. */
	private static final String EMPLOYER = "EMPLOYER";

	@Override
	public boolean isAssignableFrom(String filter)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		// TODO Auto-generated method stub

	}

	// private ILocationBAI locationBAI;
	//
	// /**
	// * @return the memberBCF
	// */
	// public ILocationBAI getLocationBAI()
	// {
	// return locationBAI;
	// }
	//
	// /**
	// * @param memberBCF the memberBCF to set
	// */
	// @Resource
	// public void setLocationBAI(ILocationBAI locationrBAI)
	// {
	// locationBAI = locationrBAI;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	// */
	// @Override
	// public boolean isAssignableFrom(String filter)
	// {
	// return EMPLOYER.equals(filter);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	// * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	// */
	// @Override
	// public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	// {
	//
	// PagedInquiryRequest pagedInquiryRequest = new PagedInquiryRequest();
	// pagedInquiryRequest.setPageSize(0);
	//
	// filtersResponse.addFilter(EMPLOYER.toLowerCase(), null);
	// }

}
