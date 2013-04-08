package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.gestao.model.request.EmbalagensRequest;
import com.sensus.mlc.gestao.model.request.InquiryEmbalagensRequest;
import com.sensus.mlc.gestao.model.response.EmbalagensResponse;
import com.sensus.mlc.gestao.model.response.InquiryEmbalagensResponse;


/**
 * The Interface IEmbalagensBCF.
 *
 * @author Washington.Costa
 */
public interface IEmbalagensBCF
{

	/**
	 * Insert embalagens.
	 *
	 * @param embalagensRequest the embalagens request
	 * @return the embalagens response
	 */
	public EmbalagensResponse insertEmbalagens(EmbalagensRequest embalagensRequest);

	/**
	 * Update embalagens.
	 *
	 * @param embalagensRequest the embalagens request
	 * @return the embalagens response
	 */
	public EmbalagensResponse updateEmbalagens(EmbalagensRequest embalagensRequest);

	/**
	 * Delete embalagens.
	 *
	 * @param embalagensRequest the embalagens request
	 * @return the embalagens response
	 */
	public EmbalagensResponse deleteEmbalagens(EmbalagensRequest embalagensRequest);

	/**
	 * Fetch all embalagens.
	 *
	 * @param inquiryEmbalagensRequest the inquiryEmbalagens request
	 * @return the inquiry embalagens response
	 */
	public InquiryEmbalagensResponse fetchAllEmbalagens(InquiryEmbalagensRequest inquiryEmbalagensRequest);

	/**
	 * Fetch embalagens by id.
	 *
	 * @param EmbalagensRequest the embalagens request
	 * @return the embalagens response
	 */
	public EmbalagensResponse fetchEmbalagensById(EmbalagensRequest embalagensRequest);

}
