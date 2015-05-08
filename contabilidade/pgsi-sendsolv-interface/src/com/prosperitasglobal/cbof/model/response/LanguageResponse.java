package com.prosperitasglobal.cbof.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.cbof.model.Language;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class LanguageResponse.
 */
public class LanguageResponse extends InquiryResponse
{

	/** The language list. */
	private List<Language> languageList;

	/**
	 * Gets the language list.
	 *
	 * @return the language list
	 */
	public List<Language> getLanguageList()
	{
		return languageList;
	}

	/**
	 * Sets the language list.
	 *
	 * @param languageList the language list
	 */
	public void setLanguageList(List<Language> languageList)
	{
		this.languageList = languageList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setLanguageList((List<Language>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LanguageResponse [getLanguageList()=" + getLanguageList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}
}
