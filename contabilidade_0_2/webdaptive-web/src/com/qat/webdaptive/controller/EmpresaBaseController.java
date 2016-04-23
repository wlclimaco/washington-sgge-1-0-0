package com.qat.webdaptive.controller;

import org.slf4j.LoggerFactory;

import com.qat.framework.util.QATAppContext;
import com.qat.samples.sysmgmt.entidade.bas.IEmpresaBAS;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.response.EmpresaResponse;

public class EmpresaBaseController
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EmpresaBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "EmpresaBaseController";

	public EmpresaResponse insert(EmpresaMaintenanceRequest request)
	{
		EmpresaResponse response = new EmpresaResponse();

		try
		{

			IEmpresaBAS client = (IEmpresaBAS)QATAppContext.getBean("empresaBASClientTarget");
			response = client.insertEmpresa(request);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
		}

		return response;

	}

}
