package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lftrattrib.model.Lftrattrib;

/**
 * The Class LftrattribResponse.
 *
 * @author - Washington
 */
public class LftrattribResponse extends Response
{

	/** The lftrattrib. */
	private List<Lftrattrib> lftrattrib = new ArrayList<Lftrattrib>();

	/**
	 * Sets the lftrattribs.
	 * 
	 * @param lftrattribList the new lftrattrib
	 */
	public void setLftrattribs(List<Lftrattrib> lftrattribList)
	{
		this.lftrattrib = lftrattribList;
	}

	/**
	 * Gets the lftrattrib.
	 * 
	 * @return the lftrattrib
	 */
	public List<Lftrattrib> getLftrattrib()
	{
		return this.lftrattrib;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LftrattribResponse [getLftrattrib()=" + this.getLftrattrib() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

