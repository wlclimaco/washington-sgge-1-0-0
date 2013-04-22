package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfmensagem.model.Lfmensagem;

/**
 * The Class LfmensagemResponse.
 *
 * @author - Washington
 */
public class LfmensagemResponse extends Response
{

	/** The lfmensagem. */
	private List<Lfmensagem> lfmensagem = new ArrayList<Lfmensagem>();

	/**
	 * Sets the lfmensagems.
	 * 
	 * @param lfmensagemList the new lfmensagem
	 */
	public void setLfmensagems(List<Lfmensagem> lfmensagemList)
	{
		this.lfmensagem = lfmensagemList;
	}

	/**
	 * Gets the lfmensagem.
	 * 
	 * @return the lfmensagem
	 */
	public List<Lfmensagem> getLfmensagem()
	{
		return this.lfmensagem;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfmensagemResponse [getLfmensagem()=" + this.getLfmensagem() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

