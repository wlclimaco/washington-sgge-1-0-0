package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Liaison;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class NoteResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class LiaisonResponse extends InquiryResponse
{

	/** Attributes */
	private List<Liaison> liaisonList;

	/**
	 * The Constructor.
	 */
	public LiaisonResponse()
	{

	}

	/**
	 * Get the list of liaisons.
	 *
	 * @return The liaisons.
	 */
	public List<Liaison> getLiaisonList()
	{
		return liaisonList;
	}

	/**
	 * Set the list of liaisons.
	 *
	 * @param liaisonList The list to set.
	 */
	public void setLiaisonList(List<Liaison> liaisonList)
	{
		this.liaisonList = liaisonList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setLiaisonList((List<Liaison>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LiaisonResponse [getLiaisonList()=" + getLiaisonList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}
}