package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.HistoricoNF;
import com.qat.framework.model.response.InternalResultsResponse;

public interface IHistoricoNFDAC
{

	public Integer updateHistoricoNF(HistoricoNF historicoNF, InternalResultsResponse<?> response);

	public Integer insertHistoricoNF(HistoricoNF historicoNF, String statementName, InternalResultsResponse<?> response);

	public Integer deleteHistoricoNF(HistoricoNF historicoNF, InternalResultsResponse<?> response);

}