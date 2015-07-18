package com.prosperitasglobal.sendsolv.filter.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum;
import com.qat.framework.model.UserContext;

/**
 * The Class FilterTags.
 */
@Component
public class FilterStatus extends AbstractFilterBase
{
	/** The Constant TAGS. */
	private static final String STATUS = "STATUS";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return STATUS.equals(filter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	 * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	 */
	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{

		List<List<String>> records = new ArrayList<List<String>>();

		records.add(Arrays.asList(CdStatusTypeEnum.ANALISANDO.getValue().toString(),
				CdStatusTypeEnum.ANALISANDO.getLabelKey()));
		records.add(Arrays.asList(CdStatusTypeEnum.ATIVO.getValue().toString(),
				CdStatusTypeEnum.ATIVO.getLabelKey()));
		records.add(Arrays.asList(CdStatusTypeEnum.DELETADO.getValue().toString(),
				CdStatusTypeEnum.DELETADO.getLabelKey()));
		records.add(Arrays.asList(CdStatusTypeEnum.INATIVO.getValue().toString(),
				CdStatusTypeEnum.INATIVO.getLabelKey()));
		records.add(Arrays.asList(CdStatusTypeEnum.SUSPENSO.getValue().toString(),
				CdStatusTypeEnum.SUSPENSO.getLabelKey()));

		filtersResponse.addFilter(STATUS.toLowerCase(), records);
	}

}
