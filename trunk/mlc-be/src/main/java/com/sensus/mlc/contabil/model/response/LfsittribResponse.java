package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfsittrib.model.Lfsittrib;

/**
 * The Class LfsittribResponse.
 *
 * @author - Washington
 */
public class LfsittribResponse extends Response
{

	/** The lfsittrib. */
	private List<Lfsittrib> lfsittrib = new ArrayList<Lfsittrib>();

	/**
	 * Sets the lfsittribs.
	 * 
	 * @param lfsittribList the new lfsittrib
	 */
	public void setLfsittribs(List<Lfsittrib> lfsittribList)
	{
		this.lfsittrib = lfsittribList;
	}

	/**
	 * Gets the lfsittrib.
	 * 
	 * @return the lfsittrib
	 */
	public List<Lfsittrib> getLfsittrib()
	{
		return this.lfsittrib;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfsittribResponse [getLfsittrib()=" + this.getLfsittrib() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

