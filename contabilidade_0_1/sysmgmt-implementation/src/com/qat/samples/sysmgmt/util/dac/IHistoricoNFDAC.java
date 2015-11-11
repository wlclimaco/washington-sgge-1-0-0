package com.qat.samples.sysmgmt.util.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.nf.model.HistoricoNF;

public interface IHistoricoNFDAC
{

	public Integer updateHistoricoNF(HistoricoNF historicoNF, InternalResultsResponse<?> response);

	public Integer insertHistoricoNF(HistoricoNF historicoNF, String statementName, InternalResultsResponse<?> response);

	public Integer deleteHistoricoNF(HistoricoNF historicoNF, InternalResultsResponse<?> response);

}