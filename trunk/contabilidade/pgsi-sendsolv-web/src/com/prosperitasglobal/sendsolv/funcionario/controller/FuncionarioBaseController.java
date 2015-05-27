package com.prosperitasglobal.sendsolv.funcionario.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IFuncionarioBAI;
import com.prosperitasglobal.sendsolv.model.Funcionario;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.FuncionarioResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class FuncionarioBaseController.
 */

/**
 * @author Flavio Tosta.
 *
 */
public class FuncionarioBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FuncionarioBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "FuncionarioBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Funcionario BAI. */
	private IFuncionarioBAI locationBAI;

	/**
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public IFuncionarioBAI getFuncionarioBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the location bai
	 */
	@Resource
	public void setFuncionarioBAI(IFuncionarioBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Funcionario edit mav.
	 *
	 * @param locationId the location id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView locationEditMAV(Integer locationId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				// modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(locationId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchFuncionarioById(new FetchByIdRequest(locationId))));

				return modelAndView;
			}

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return modelAndView;
	}

	/**
	 * Fetch location by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the location response
	 */
	public FuncionarioResponse fetchFuncionarioByRequest(PagedInquiryRequest pagedInquiryRequest)
	{

		FuncionarioResponse locationResponse = new FuncionarioResponse();
		try
		{

			locationResponse = Mock();
			// getFuncionarioBAI().fetchFuncionarioByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return locationResponse;
	}

	/**
	 * Fetch location by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the location response
	 */
	public FuncionarioResponse fetchFuncionarioById(FetchByIdRequest fetchByIdRequest)
	{

		FuncionarioResponse locationResponse = new FuncionarioResponse();
		try
		{

			locationResponse = MockById();
			// getFuncionarioBAI().fetchFuncionarioById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return locationResponse;
	}

	public FuncionarioResponse Mock()
	{
		FuncionarioResponse empresaResponse = new FuncionarioResponse();

		List<Funcionario> funcionarioList = new ArrayList<Funcionario>();
		for (Integer i = 0; i < 100; i++)
		{
			Funcionario funcionario = new Funcionario();
			funcionario.setId(i);
			funcionario.setNome("nome_" + i);
			// Socio socio = new Socio();
			// socio.setId(1);
			// socio.setNome("Washington");
			// empresa.setSocios(new ArrayList<Socio>());
			// empresa.getSocios().add(socio);
			// Endereco endereco = new Endereco();
			// empresa.setEnderecos(new ArrayList<Endereco>());
			// endereco.setBairro("bairro");
			// endereco.setCep("cep");
			// endereco.setCidade("cidade");
			// endereco.setEstado("estado");
			// endereco.setId(1);
			// endereco.setLogradouro("logradouro");
			// endereco.setNumero("1000");
			// empresa.getEnderecos().add(endereco);
			// empresa.setEmails(new ArrayList<Email>());
			// Email email = new Email();
			// email.setId(1);
			// email.setEmail("wlclimaco@gmail.com");
			// empresa.getEmails().add(email);
			// empresa.setCnaes(new ArrayList<Cnae>());
			// Cnae cnae = new Cnae();
			// cnae.setId(1);
			// cnae.setDescription("1-(4-BETA-HIDROXIETILSULFOFENIL)-3-METIL-5-PIRAZOLONA; FABRICAÇÃO DE");
			// cnae.setNumber("2029-1/00");
			// empresa.getCnaes().add(cnae);
			// cnae = new Cnae();
			// cnae.setId(2);
			// cnae.setDescription("1-(4-SULFOFENIL)-3-METIL-5-PIRAZOLONA (ÁCIDO PIRAZÓLICO); FABRICAÇÃO DE");
			// cnae.setNumber("2029-2/00");
			// empresa.getCnaes().add(cnae);
			// empresa.setDocumentos(new ArrayList<Documento>());
			// Documento documento = new Documento();
			// documento.setId(1);
			// documento.setType("CNPJ");
			// documento.setNumero("000000000001111/000-9");
			// empresa.getDocumentos().add(documento);
			// documento = new Documento();
			// documento.setId(2);
			// documento.setType("IM");
			// documento.setNumero("00000001");
			// empresa.getDocumentos().add(documento);
			// empresa.setTelefones(new ArrayList<Telefone>());
			// Telefone telefone = new Telefone();
			// telefone.setId(1);
			// telefone.setDdd("34");
			// telefone.setNumero("91782776");
			// empresa.getTelefones().add(telefone);
			// empresa.setRegime("Simples Nacional");
			// empresas.add(empresa);

		}
		empresaResponse.setFuncionarioList(funcionarioList);
		return empresaResponse;
	}

	public FuncionarioResponse MockById()
	{
		FuncionarioResponse empresaResponse = new FuncionarioResponse();

		List<Funcionario> empresas = new ArrayList<Funcionario>();

		Funcionario empresa = new Funcionario();
		empresa.setId(1);
		empresa.setNome("nome_" + 1);
		// Socio socio = new Socio();
		// socio.setId(1);
		// socio.setNome("Washington");
		// empresa.setSocios(new ArrayList<Socio>());
		// empresa.getSocios().add(socio);
		// Endereco endereco = new Endereco();
		// empresa.setEnderecos(new ArrayList<Endereco>());
		// endereco.setBairro("bairro");
		// endereco.setCep("cep");
		// endereco.setCidade("cidade");
		// endereco.setEstado("estado");
		// endereco.setId(1);
		// endereco.setLogradouro("logradouro");
		// endereco.setNumero("1000");
		// empresa.getEnderecos().add(endereco);
		// empresa.setEmails(new ArrayList<Email>());
		// Email email = new Email();
		// email.setId(1);
		// email.setEmail("wlclimaco@gmail.com");
		// email.setDescription("NF-e");
		// empresa.getEmails().add(email);
		// empresa.setCnaes(new ArrayList<Cnae>());
		// Cnae cnae = new Cnae();
		// cnae.setId(1);
		// cnae.setDescription("1-(4-BETA-HIDROXIETILSULFOFENIL)-3-METIL-5-PIRAZOLONA; FABRICAÇÃO DE");
		// cnae.setNumber("2029-1/00");
		// empresa.getCnaes().add(cnae);
		// cnae = new Cnae();
		// cnae.setId(2);
		// cnae.setDescription("1-(4-SULFOFENIL)-3-METIL-5-PIRAZOLONA (ÁCIDO PIRAZÓLICO); FABRICAÇÃO DE");
		// cnae.setNumber("2029-2/00");
		// empresa.getCnaes().add(cnae);
		// empresa.setDocumentos(new ArrayList<Documento>());
		// Documento documento = new Documento();
		// documento.setId(1);
		// documento.setType("CNPJ");
		// documento.setNumero("000000000001111/000-9");
		// empresa.getDocumentos().add(documento);
		// documento = new Documento();
		// documento.setId(2);
		// documento.setType("IM");
		// documento.setNumero("00000001");
		// empresa.getDocumentos().add(documento);
		// empresa.setTelefones(new ArrayList<Telefone>());
		// Telefone telefone = new Telefone();
		// telefone.setId(1);
		// telefone.setDdd("34");
		// telefone.setNumero("91782776");
		// telefone.setType("Residencial");
		// empresa.getTelefones().add(telefone);
		// empresa.setRegime("Simples Nacional");
		empresas.add(empresa);

		empresaResponse.setFuncionarioList(empresas);
		return empresaResponse;
	}
}
