package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Embalagens;
import com.sensus.mlc.gestao.model.request.EmbalagensRequest;
import com.sensus.mlc.gestao.model.request.InquiryEmbalagensRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface IEmbalagensBCL
{

	/**
	 * Insert embalagens.
	 *
	 * @param embalagensRequest the embalagens request
	 * @return the embalagens response
	 */
	public InternalResultsResponse<Embalagens> insertEmbalagens(EmbalagensRequest embalagensRequest);

	/**
	 * Update embalagens.
	 *
	 * @param embalagensRequest the embalagens request
	 * @return the embalagens response
	 */
	public InternalResultsResponse<Embalagens> updateEmbalagens(EmbalagensRequest embalagensRequest);

	/**
	 * Delete embalagens.
	 *
	 * @param embalagensRequest the embalagens request
	 * @return the embalagens response
	 */
	public InternalResponse deleteEmbalagens(EmbalagensRequest embalagensRequest);

	/**
	 * Fetch all embalagens.
	 *
	 * @param inquiryembalagensRequest the inquiryembalagens request
	 * @return the inquiry embalagens response
	 */
	public InternalResultsResponse<Embalagens> fetchAllEmbalagens(InquiryEmbalagensRequest inquiryembalagensRequest);

	/**
	 * Fetch embalagens by id.
	 *
	 * @param inquiryembalagensRequest the inquiryembalagens request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Embalagens> fetchEmbalagensById(EmbalagensRequest embalagensRequest);

	/**
	 * Fetch all embalagens types.
	 *
	 * @param request the request
	 * @return the embalagens response
	 */
	public InternalResultsResponse<Embalagens> fetchAllEmbalagensTypes(InquiryEmbalagensRequest inquiryembalagensRequest);

	/**
	 * Fetch all embalagens filial.
	 *
	 * @param request the request
	 * @return the embalagens response
	 */
	public InternalResultsResponse<Embalagens> fetchAllEmbalagensFilial(EmbalagensRequest embalagensRequest);

}
