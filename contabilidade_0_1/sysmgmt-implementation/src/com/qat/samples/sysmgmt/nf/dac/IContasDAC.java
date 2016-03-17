package com.qat.samples.sysmgmt.nf.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.nf.model.Contas;

public interface IContasDAC
{

	public Integer updateContas(Contas contas, InternalResultsResponse<?> response);

	public Integer insertContas(Contas contas, String statementName, InternalResultsResponse<?> response);

	public Integer deleteContas(Contas contas, InternalResultsResponse response);

}