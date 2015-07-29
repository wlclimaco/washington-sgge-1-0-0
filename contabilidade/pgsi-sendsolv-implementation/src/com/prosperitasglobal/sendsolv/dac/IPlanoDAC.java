package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Plano;
import com.prosperitasglobal.sendsolv.model.request.PlanoInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IPlanoDAC.
 */
public interface IPlanoDAC
{

	public Integer updatePlano(Plano Plano, InternalResultsResponse<?> response);

	public Integer insertPlano(Plano Plano, String statementName, InternalResultsResponse<?> response);

	public Integer deletePlano(Plano Plano, InternalResultsResponse<?> response);

	public InternalResultsResponse<Plano> fetchPlanoById(Integer id);

	public InternalResultsResponse<Plano> fetchPlanoByRequest(PlanoInquiryRequest request);

}
