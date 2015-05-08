package com.prosperitasglobal.sendsolv.ach.model.request;

import com.prosperitasglobal.sendsolv.ach.model.criteria.AchNotificationCriteria;
import com.qat.framework.model.request.Request;

/**
 * The class AchNotificationRequest.
 */
public class AchNotificationRequest extends Request
{
	/** The criteria of the request. */
	private AchNotificationCriteria criteria;

	/**
	 * Get the criteria of the request.
	 *
	 * @return The criteria.
	 */
	public AchNotificationCriteria getCriteria()
	{
		return criteria;
	}

	/**
	 * Set the criteria of the request.
	 *
	 * @param criteria The criteria to set.
	 */
	public void setCriteria(AchNotificationCriteria criteria)
	{
		this.criteria = criteria;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AchNotificationRequest [getCriteria()=" + getCriteria() + ", getUserContext()=" + getUserContext()
				+ "]";
	}
}
