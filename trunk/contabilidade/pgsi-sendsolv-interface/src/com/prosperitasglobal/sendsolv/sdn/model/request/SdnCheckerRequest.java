package com.prosperitasglobal.sendsolv.sdn.model.request;

import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.Person;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatch;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;
import com.qat.framework.model.request.Request;

public class SdnCheckerRequest<T> extends Request
{
	@Deprecated
	private SdnMatch sdnMatch;

	private T personOrBusiness;

	public T getPersonOrBusiness()
	{
		return personOrBusiness;
	}

	public void setPersonOrBusiness(T personOrBusiness)
	{
		this.personOrBusiness = personOrBusiness;
	}

	@Deprecated
	public SdnMatch getSdnMatch()
	{
		return sdnMatch;
	}

	/**
	 * @deprecated replaced by {@link #setBusiness(Business business)},{@link #setPerson(Person person)} and
	 *             {@link #setMatchType(SdnMatchTypeEnum matchType)} When creating the {@link SdnCheckerRequest} set the
	 *             corresponding type and object.
	 */
	@Deprecated
	public void setSdnMatch(SdnMatch sdnMatch)
	{
		this.sdnMatch = sdnMatch;
	}

	@Override
	public String toString()
	{
		return "SdnCheckerRequest [getPersonOrBusiness()=" + getPersonOrBusiness()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}
