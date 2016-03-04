package com.qat.webdaptive.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.qat.framework.util.QATAppContext;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.agencia.model.response.AgenciaResponse;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.response.BeneficiosResponse;
import com.qat.samples.sysmgmt.condpag.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.response.ContatoResponse;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.ProfissaoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.response.BancoResponse;
import com.qat.samples.sysmgmt.dp.model.response.ContaResponse;
import com.qat.samples.sysmgmt.dp.model.response.ConvenioResponse;
import com.qat.samples.sysmgmt.dp.model.response.EventoResponse;
import com.qat.samples.sysmgmt.dp.model.response.FormaPgResponse;
import com.qat.samples.sysmgmt.dp.model.response.FuncionarioResponse;
import com.qat.samples.sysmgmt.dp.model.response.HorarioFuncResponse;
import com.qat.samples.sysmgmt.dp.model.response.ProfissaoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.bas.IPessoaBAS;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContaInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContadorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ConvenioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.response.ClienteResponse;
import com.qat.samples.sysmgmt.pessoa.model.response.ContadorResponse;
import com.qat.samples.sysmgmt.pessoa.model.response.FornecedorResponse;
import com.qat.samples.sysmgmt.pessoa.model.response.TransportadorResponse;

/**
 * The Class CountyBaseController.
 */
public class PessoaBaseController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PessoaBaseController.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "webdaptive.controller.pessoa.defaultexception";

	private static final String PROCEDURE_RESPONSE = null;

	protected ModelAndView funcionarioMAV(FuncionarioInquiryRequest request, String returnViewName)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			modelAndView.addObject("funcionarioList", mapper.writeValueAsString(fetchFuncionarioByRequest(request)));
		}
		catch (Exception ex)
		{
			LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
			modelAndView.addObject(PROCEDURE_RESPONSE, null);
		}
		return modelAndView;
	}

	protected AgenciaResponse fetchAgenciaByRequest(AgenciaInquiryRequest request)
	{
		AgenciaResponse response = new AgenciaResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchAgenciaByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected BancoResponse fetchBancoByRequest(BancoInquiryRequest request)
	{
		BancoResponse response = new BancoResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchBancoByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected BeneficiosResponse fetchBeneficiosRequest(BeneficiosInquiryRequest request)
	{
		BeneficiosResponse response = new BeneficiosResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchBeneficiosRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ClienteResponse fetchClienteById(FetchByIdRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchClienteById(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ClienteResponse fetchClienteByRequest(ClienteInquiryRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchClienteByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ContaResponse fetchContaByRequest(ContaInquiryRequest request)
	{
		ContaResponse response = new ContaResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchContaByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ContadorResponse fetchContadorById(FetchByIdRequest request)
	{
		ContadorResponse response = new ContadorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchContadorById(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ContadorResponse fetchContadorByRequest(ContadorInquiryRequest request)
	{
		ContadorResponse response = new ContadorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchContadorByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ContatoResponse fetchContatoByRequest(ContatoInquiryRequest request)
	{
		ContatoResponse response = new ContatoResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchContatoByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ConvenioResponse fetchConvenioByRequest(ConvenioInquiryRequest request)
	{
		ConvenioResponse response = new ConvenioResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchConvenioByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected EventoResponse fetchEventosRequest(EventoInquiryRequest request)
	{
		EventoResponse response = new EventoResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchEventosRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected FormaPgResponse fetchFormaPgByRequest(FormaPgInquiryRequest request)
	{
		FormaPgResponse response = new FormaPgResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchFormaPgByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected FornecedorResponse fetchFornecedorById(FetchByIdRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchFornecedorById(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected FornecedorResponse fetchFornecedorByRequest(FornecedorInquiryRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchFornecedorByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected FuncionarioResponse fetchFuncionarioById(FetchByIdRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchFuncionarioById(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected FuncionarioResponse fetchFuncionarioByRequest(FuncionarioInquiryRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchFuncionarioByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected HorarioFuncResponse fetchHorarioFuncsRequest(HoraFuncInquiryRequest request)
	{
		HorarioFuncResponse response = new HorarioFuncResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchHorarioFuncsRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ProfissaoResponse fetchProfissaoByRequest(ProfissaoInquiryRequest request)
	{
		ProfissaoResponse response = new ProfissaoResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchProfissaoByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected TransportadorResponse fetchTransportadorById(FetchByIdRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchTransportadorById(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected TransportadorResponse fetchTransportadorByRequest(TransportadorInquiryRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.fetchTransportadorByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ClienteResponse insertCliente(ClienteMaintenanceRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.insertCliente(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ClienteResponse updateCliente(ClienteMaintenanceRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.updateCliente(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ClienteResponse deleteCliente(ClienteMaintenanceRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.deleteCliente(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ContadorResponse insertContador(ContadorMaintenanceRequest request)
	{
		ContadorResponse response = new ContadorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.insertContador(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ContadorResponse updateCliente(ContadorMaintenanceRequest request)
	{
		ContadorResponse response = new ContadorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.updateContador(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ContadorResponse deleteCliente(ContadorMaintenanceRequest request)
	{
		ContadorResponse response = new ContadorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.deleteContador(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected FornecedorResponse insertFornecedor(FornecedorMaintenanceRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.insertFornecedor(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected FornecedorResponse updateCliente(FornecedorMaintenanceRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.updateFornecedor(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected FornecedorResponse deleteCliente(FornecedorMaintenanceRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.deleteFornecedor(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected FuncionarioResponse insertFuncionario(FuncionarioMaintenanceRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.insertFuncionario(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected FuncionarioResponse updateFuncionario(FuncionarioMaintenanceRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.updateFuncionario(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected FuncionarioResponse deleteFuncionario(FuncionarioMaintenanceRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.deleteFuncionario(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected TransportadorResponse insertTransportador(TransportadorMaintenanceRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.insertTransportador(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected TransportadorResponse updateTransportador(TransportadorMaintenanceRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.updateTransportador(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected TransportadorResponse deleteTransportador(TransportadorMaintenanceRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			IPessoaBAS client = (IPessoaBAS)QATAppContext.getBean("pessoaBASClientTarget");
			response = client.deleteTransportador(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}
}
