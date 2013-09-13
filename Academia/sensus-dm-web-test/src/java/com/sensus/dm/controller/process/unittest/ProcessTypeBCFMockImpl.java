package com.sensus.dm.controller.process.unittest;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.process.bcf.IProcessTypeBCF;
import com.sensus.dm.common.process.model.ProcessCategory;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;

public class ProcessTypeBCFMockImpl extends BaseMockImpl implements IProcessTypeBCF
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessTypeBCF#fetchAllProcessCategory(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchAllProcessCategory(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<ProcessCategory> processList = new ArrayList<ProcessCategory>();

			for (Integer i = 0; i <= 5; i++)
			{
				ProcessCategory processCategory = new ProcessCategory(i, "sensus.epm.process.category.group");
				processList.add(processCategory);
			}

			response.setProcessCategories(processList);
			response.setOperationSuccess(Boolean.TRUE);

			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}
}
