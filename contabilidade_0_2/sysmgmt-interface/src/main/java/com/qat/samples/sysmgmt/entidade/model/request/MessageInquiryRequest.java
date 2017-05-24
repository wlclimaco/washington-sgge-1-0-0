package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.Message;
import com.qat.samples.sysmgmt.entidade.model.criteria.FilialCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class MessageInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private Message message;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public Message getMessage()
	{
		if (message == null)
		{
			message = new Message();
		}
		return message;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setMessage(Message criteria)
	{
		this.message = criteria;
	}

	@Override
	public String toString() {
		return "MessageInquiryRequest [getMessage()=" + getMessage() + ", toString()=" + super.toString() + "]";
	}


}
