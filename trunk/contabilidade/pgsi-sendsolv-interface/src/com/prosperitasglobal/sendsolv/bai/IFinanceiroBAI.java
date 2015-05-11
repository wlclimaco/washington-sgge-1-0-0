package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.PessoaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PessoaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.PessoaResponse;

public interface IFinanceiroBAI
{

	public PessoaResponse insertPessoa(PessoaMaintenanceRequest request);

	public PessoaResponse updatePessoa(PessoaMaintenanceRequest request);

	public PessoaResponse deletePessoa(PessoaMaintenanceRequest request);

	public PessoaResponse fetchPessoaById(FetchByIdRequest request);

	public PessoaResponse fetchPessoaByRequest(PessoaInquiryRequest request);

}