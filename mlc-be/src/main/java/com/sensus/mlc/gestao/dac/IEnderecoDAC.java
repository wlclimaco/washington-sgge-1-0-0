package com.sensus.mlc.gestao.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Endereco;
import com.sensus.mlc.gestao.model.request.EnderecoRequest;
import com.sensus.mlc.gestao.model.request.InquiryEnderecoRequest;
import com.sensus.mlc.gestao.model.response.EnderecoResponse;


/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IEnderecoDAC
{

/**
* Update endereco.
*
* @param enderecoRequest the endereco request
* @return the endereco response
*/
public InternalResultsResponse<Endereco> updateEndereco(EnderecoRequest enderecoRequest);

/**
* Delete endereco.
*
* @param enderecoRequest the endereco request
* @return the endereco response
*/
public InternalResponse deleteEndereco(EnderecoRequest enderecoRequest);

/**
* Fetch all endereco.
*
* @param inquiryenderecoRequest the inquiryendereco request
* @return the inquiry endereco response
*/
public InternalResultsResponse<Endereco> fetchAllEndereco(InquiryEnderecoRequest inquiryenderecoRequest);

/**
* Fetch endereco by id.
*
* @param inquiryenderecoRequest the inquiryendereco request
* @return the internal results response
*/
public InternalResultsResponse<Endereco> fetchEnderecoById(EnderecoRequest enderecoRequest);

/**
* Generate file csv.
*
* @param inquiryEnderecoRequest the inquiry endereco request
* @return the inquiry endereco response
*/
public InternalResponse generateFileCSV(InquiryEnderecoRequest inquiryEnderecoRequest);

/**
* Fetch all endereco types.
*
* @param request the request
* @return the endereco response
*/
public EnderecoResponse fetchAllEnderecoTypes(Request request);

/**
* Fetch all endereco filial.
*
* @param request the request
* @return the endereco response
*/
public EnderecoResponse fetchAllEnderecoFilial(Request request);

public InternalResultsResponse<Endereco> insertEndereco(EnderecoRequest enderecoRequest);
}


