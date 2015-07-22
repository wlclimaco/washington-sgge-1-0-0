package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Contas;
import com.qat.framework.model.response.InternalResultsResponse;

public interface IContasDAC
{

	public Integer updateContas(Contas contas, InternalResultsResponse<?> response);

	public Integer insertContas(Contas contas, String statementName, InternalResultsResponse<?> response);

	public Integer deleteContas(Contas contas, InternalResultsResponse response);

}