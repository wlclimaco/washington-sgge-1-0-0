package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.response.EmpresaResponse;

@Controller
@RequestMapping("/api/empresa")
public class EmpresaAPIController extends EmpresaBaseController
{
	/** The URL mapping constants. */
	private static final String DELETE_EMPRESA = "/delete";

	/** The Constant EDIT_EMPRESA. */
	private static final String EDIT_EMPRESA = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant INSERT_EMPRESA. */
	private static final String INSERT_EMPRESA = "/add";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	private static final String FETCH_REGIME = "/fetch/regime";

	private static final String FETCH_CNAE = "/fetch/cnae";

	private static final String FETCH_ALL_FILIAL = "/fetchall/filial";

	private static final String FETCH_FILIAL = "/fetch/filial";

	private static final String EDIT_FILIAL = "/filial/edit";

	private static final String DELETE_FILIAL = "/filial/delete";

	private static final String INSERT_FILIAL = "/filial/add";

	private static final String FETCH_DEPOSITO = "/fetch/deposito";

	private static final String FETCH_ALL_DEPOSITO = "/fetchall/deposito";

	private static final String EDIT_DEPOSITO = "/deposito/edit";

	private static final String DELETE_DEPOSITO = "/deposito/delete";

	private static final String INSERT_DEPOSITO = "/deposito/add";

	/**
	 * Insert one empresa.
	 * 
	 * @param empresaRequest the empresa request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_EMPRESA, method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse insertEmpresa(@RequestBody EmpresaMaintenanceRequest empresaRequest)
	{
		// empresaRequest.setEmpresa(insertMockEmpresa(PersistanceActionEnum.INSERT));
		return insert(empresaRequest);

	}

}
