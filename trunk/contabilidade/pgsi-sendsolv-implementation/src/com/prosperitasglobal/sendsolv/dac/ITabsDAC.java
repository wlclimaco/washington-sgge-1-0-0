package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.Tabs;
import com.qat.framework.model.response.InternalResultsResponse;

public interface ITabsDAC
{
	/**
	 * Update tabs.
	 *
	 * @param tabs the tabs
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateTabs(Tabs tabs, InternalResultsResponse<?> response);

	/**
	 * Insert tabs.
	 *
	 * @param tabs the tabs
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertTabs(Tabs tabs, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business tabs.
	 *
	 * @param tabs the tabs
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessTabs(Tabs tabs, InternalResultsResponse<?> response);

	/**
	 * Delete person tabs.
	 *
	 * @param tabs the tabs
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonTabs(Tabs tabs, InternalResultsResponse<?> response);

	/**
	 * Fetch tabs by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< tabs>
	 */
	public InternalResultsResponse<Tabs> fetchTabsByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch tabs by id.
	 *
	 * @param id the id
	 * @return the internal results response< tabs>
	 */
	public InternalResultsResponse<Tabs> fetchTabsById(Integer id);

	/**
	 * Maintain tabs associations.
	 *
	 * @param tabsList the tabs list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainTabsAssociations(List<Tabs> tabsList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}