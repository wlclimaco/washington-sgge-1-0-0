package com.qat.samples.sysmgmt.dac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.samples.sysmgmt.documento.model.Documento;

/**
 * The Interface IDocumentoDAC. (Data Access Component - DAC)
 */
public interface IDocumentoDAC
{

	/**
	 * Insert documento.
	 * 
	 * @param documento the documento
	 * @return the internal response
	 */
	public InternalResponse insertDocumento(Documento documento);

	/**
	 * Update documento.
	 * 
	 * @param documento the documento
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateDocumento(Documento documento);

	/**
	 * Delete documento.
	 * 
	 * @param documento the documento
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteDocumento(Documento documento);

}
