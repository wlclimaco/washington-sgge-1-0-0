package com.qat.samples.complexmo.model.request;

import com.qat.samples.complexmo.model.Person;

public class PersonRequest extends com.qat.framework.model.request.InquiryRequest
{
	private Person person;
	private boolean lastNameSoundsLike = false;

	public Person getPerson()
	{
		return person;
	}

	public void setPerson(Person person)
	{
		this.person = person;
	}

	public boolean isLastNameSoundsLike()
	{
		return lastNameSoundsLike;
	}

	public void setLastNameSoundsLike(boolean lastNameSoundsLike)
	{
		this.lastNameSoundsLike = lastNameSoundsLike;
	}

	@Override
	public String toString()
	{
		return "PersonRequest [getPerson()=" + getPerson() + ", isLastNameSoundsLike()=" + isLastNameSoundsLike() + ", getPageSize()=" + getPageSize() + ", getStartPage()="
				+ getStartPage() + ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression() + ", isPreQueryCount()=" + isPreQueryCount()
				+ ", getMaxPreQueryCount()=" + getMaxPreQueryCount() + ", getUserContext()=" + getUserContext() + "]";
	}

}
