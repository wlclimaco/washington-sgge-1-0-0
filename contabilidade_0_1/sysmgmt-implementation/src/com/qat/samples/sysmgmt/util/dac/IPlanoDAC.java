package com.qat.samples.sysmgmt.util.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.contabilidade.Plano;
import com.qat.samples.sysmgmt.fiscal.model.request.PlanoInquiryRequest;

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
