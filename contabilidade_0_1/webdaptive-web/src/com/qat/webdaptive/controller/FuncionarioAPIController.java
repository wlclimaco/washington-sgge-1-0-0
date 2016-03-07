package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qat.samples.sysmgmt.dp.model.request.FuncionarioMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.response.FuncionarioResponse;

@Controller
@RequestMapping("/api/funcionario")
public class FuncionarioAPIController extends PessoaBaseController
{
	/** The URL mapping constants. */
	private static final String DELETE_FUNCIONARIO = "/delete";

	/** The Constant EDIT_FUNCIONARIO. */
	private static final String EDIT_FUNCIONARIO = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant INSERT_FUNCIONARIO. */
	private static final String INSERT_FUNCIONARIO = "/add";

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
	 * Insert one funcionario.
	 * 
	 * @param funcionarioRequest the funcionario request
	 * @return the response
	 */

	@RequestMapping(value = INSERT_FUNCIONARIO, method = RequestMethod.POST)
	@ResponseBody
	public FuncionarioResponse insertFuncionario(@RequestBody FuncionarioMaintenanceRequest funcionarioRequest)
	{
		// funcionarioRequest.setFuncionario(insertMockFuncionario(PersistanceActionEnum.INSERT));
		return insertFunc(funcionarioRequest);

	}

}
