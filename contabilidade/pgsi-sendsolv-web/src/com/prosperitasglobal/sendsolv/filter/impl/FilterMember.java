package com.prosperitasglobal.sendsolv.filter.impl;

import org.springframework.stereotype.Component;

import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.qat.framework.model.UserContext;

/**
 * The Class FilterTags.
 */
@Component
public class FilterMember extends AbstractFilterBase
{

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
	// /** The Constant TAGS. */
	// private static final String MEMBERS = "MEMBER";
	//
	// /** The member bai. */
	// private IMemberBAI memberBAI;
	//
	// /**
	// * Gets the member bai.
	// *
	// * @return the memberBAI
	// */
	// public IMemberBAI getMemberBAI()
	// {
	// return memberBAI;
	// }
	//
	// /**
	// * Sets the member bai.
	// *
	// * @param memberBAI the memberBAI to set
	// */
	// @Resource
	// public void setMemberBAI(IMemberBAI memberBAI)
	// {
	// this.memberBAI = memberBAI;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.prosperitasglobal.sendsolv.filter.model.IFilter#isAssignableFrom(java.lang.String)
	// */
	// @Override
	// public boolean isAssignableFrom(String filter)
	// {
	// return MEMBERS.equals(filter);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.prosperitasglobal.sendsolv.filter.model.IFilter#createFilter(com.qat.framework.model.UserContext,
	// * com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse, java.lang.Object[])
	// */
	// @Override
	// public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	// {
	//
	// MemberInquiryRequest pagedInquiryRequest = new MemberInquiryRequest();
	//
	// pagedInquiryRequest.setPageSize(0);
	//
	// filtersResponse.addFilter(MEMBERS.toLowerCase(), null);
	// }

}
