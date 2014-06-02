package com.qat.samples.complexmo.dac;

import java.util.List;

import com.qat.samples.complexmo.model.Help;
import com.qat.samples.complexmo.model.request.HelpInquiryRequest;

/**
 * The Interface IHelpDAC
 */
public interface IHelpDAC
{

	/**
	 * Fetch all help.
	 * 
	 * @return the list
	 */
	List<Help> fetchAllHelp();

	/**
	 * Fetch all help.
	 * 
	 * @param startRow the start row
	 * @param max the max
	 * @return the list
	 */
	List<Help> fetchAllHelp(int startRow, int max);

	/**
	 * Fetch help by page.
	 * 
	 * @param request the request
	 * @return the list
	 */
	List<Help> fetchHelpByPage(HelpInquiryRequest request);

}
