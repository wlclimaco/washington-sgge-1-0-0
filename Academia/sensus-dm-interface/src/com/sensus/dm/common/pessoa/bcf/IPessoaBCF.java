package com.sensus.dm.common.pessoa.bcf;

import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.pessoa.model.request.PessoaRequest;
import com.sensus.dm.common.pessoa.model.request.InquiryPessoaRequest;
import com.sensus.dm.common.pessoa.model.response.PessoaResponse;
import com.sensus.dm.common.pessoa.model.response.InquiryPessoaResponse;

/**
 * The Interface IPessoaBCF.
 * 
 * @author Washington.
 * 
 */
public interface IPessoaBCF
{

	/**
	 * Fetch all pessoas.
	 * 
	 * @param inquiryPessoaRequest the inquiry pessoa request
	 * @return the inquiry pessoa response
	 */
	InquiryPessoaResponse fetchAllPessoas(InquiryPessoaRequest inquiryPessoaRequest);

	/**
	 * Fetch pessoa by id.
	 * 
	 * @param inquiryPessoaRequest the inquiry pessoa request
	 * @return the pessoa response
	 */
	PessoaResponse fetchPessoaById(InquiryPessoaRequest inquiryPessoaRequest);

	/**
	 * Fetch pessoa by name.
	 * 
	 * @param inquiryPessoaRequest the inquiry pessoa request
	 * @return the pessoa response
	 */
	PessoaResponse fetchPessoaByName(InquiryPessoaRequest inquiryPessoaRequest);

	/**
	 * Insert pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the pessoa response
	 */
	PessoaResponse insertPessoa(PessoaRequest pessoaRequest);

	/**
	 * Update pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the pessoa response
	 */
	PessoaResponse updatePessoa(PessoaRequest pessoaRequest);

	/**
	 * Delete pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the pessoa response
	 */
	PessoaResponse deletePessoa(PessoaRequest pessoaRequest);

	/**
	 * Fetch devices by pessoa.
	 * 
	 * @param pessoaRequest the pessoa request
	 * @return the device response
	 */
	DeviceResponse fetchDevicesByPessoa(PessoaRequest pessoaRequest);

}

