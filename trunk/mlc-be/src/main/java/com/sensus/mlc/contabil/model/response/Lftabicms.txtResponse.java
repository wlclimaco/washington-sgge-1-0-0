package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lftabicms.txt.model.Lftabicms.txt;

/**
 * The Class Lftabicms.txtResponse.
 *
 * @author - Washington
 */
public class Lftabicms.txtResponse extends Response
{

	/** The lftabicms.txt. */
	private List<Lftabicms.txt> lftabicms.txt = new ArrayList<Lftabicms.txt>();

	/**
	 * Sets the lftabicms.txts.
	 * 
	 * @param lftabicms.txtList the new lftabicms.txt
	 */
	public void setLftabicms.txts(List<Lftabicms.txt> lftabicms.txtList)
	{
		this.lftabicms.txt = lftabicms.txtList;
	}

	/**
	 * Gets the lftabicms.txt.
	 * 
	 * @return the lftabicms.txt
	 */
	public List<Lftabicms.txt> getLftabicms.txt()
	{
		return this.lftabicms.txt;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Lftabicms.txtResponse [getLftabicms.txt()=" + this.getLftabicms.txt() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

