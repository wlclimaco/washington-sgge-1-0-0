package com.prosperitasglobal.sendsolv.tabela.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import com.prosperitasglobal.controller.delegate.UtilControllerD;

/**
 * The Class TabelaBaseController.
 */

/**
 * @author Flavio Tosta.
 *
 */
public class TabelaBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TabelaBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "TabelaBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Tabela BAI. */
	private ITabelaBAI locationBAI;

	/** The Member BAI. */
	private IMemberBAI memberBAI;

	/**
	 * Gets the member BAI.
	 *
	 * @return the member BAI
	 */
	@Override
	public IMemberBAI getMemberBAI()
	{
		return memberBAI;
	}

	/**
	 * Sets the member bai.
	 *
	 * @param memberBAI the member bai
	 */
	@Override
	@Resource
	public void setMemberBAI(IMemberBAI memberBAI)
	{
		this.memberBAI = memberBAI;
	}

	/**
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public ITabelaBAI getTabelaBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the location bai
	 */
	@Resource
	public void setTabelaBAI(ITabelaBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Tabela edit mav.
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
				modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(locationId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchTabelaById(new FetchByIdRequest(locationId))));

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
	 * Fetch enrolled members.
	 *
	 * @param locationId the location id
	 * @return the integer
	 */
	private Integer fetchEnrolledMembers(Integer locationId, HttpServletRequest request)
	{
		MemberResponse memberResponse = fetchMembersEnrolledMember(locationId, BusinessTypeEnum.LOCATION, request);

		if (memberResponse.getMemberList() != null)
		{
			return memberResponse.getMemberList().size();
		}

		return 0;
	}

	/**
	 * Fetch location by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the location response
	 */
	public TabelaResponse fetchTabelaByRequest(PagedInquiryRequest pagedInquiryRequest)
	{

		TabelaResponse locationResponse = new TabelaResponse();
		try
		{

			locationResponse = Mock();
			// getTabelaBAI().fetchTabelaByRequest(pagedInquiryRequest);

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
	public TabelaResponse fetchTabelaById(FetchByIdRequest fetchByIdRequest)
	{

		TabelaResponse locationResponse = new TabelaResponse();
		try
		{

			locationResponse = MockById();
			// getTabelaBAI().fetchTabelaById(fetchByIdRequest);

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
	 * Fetch location by organization.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the location response
	 */
	public TabelaResponse fetchTabelaByOrganization(PagedInquiryRequest pagedInquiryRequest)
	{

		TabelaResponse locationResponse = new TabelaResponse();
		try
		{

			locationResponse =
					getTabelaBAI().fetchTabelaByOrganization(pagedInquiryRequest);

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

	public TabelaResponse Mock()
	{
		TabelaResponse empresaResponse = new TabelaResponse();

		List<Tabela> empresas = new ArrayList<Tabela>();
		for (Integer i = 0; i < 100; i++)
		{
			Tabela empresa = new Tabela();
			empresa.setId(i);
			empresa.setNome("nome_" + i);
			Socio socio = new Socio();
			socio.setId(1);
			socio.setNome("Washington");
			empresa.setSocios(new ArrayList<Socio>());
			empresa.getSocios().add(socio);
			Endereco endereco = new Endereco();
			empresa.setEnderecos(new ArrayList<Endereco>());
			endereco.setBairro("bairro");
			endereco.setCep("cep");
			endereco.setCidade("cidade");
			endereco.setEstado("estado");
			endereco.setId(1);
			endereco.setLogradouro("logradouro");
			endereco.setNumero("1000");
			empresa.getEnderecos().add(endereco);
			empresa.setEmails(new ArrayList<Email>());
			Email email = new Email();
			email.setId(1);
			email.setEmail("wlclimaco@gmail.com");
			empresa.getEmails().add(email);
			empresa.setCnaes(new ArrayList<Cnae>());
			Cnae cnae = new Cnae();
			cnae.setId(1);
			cnae.setDescription("1-(4-BETA-HIDROXIETILSULFOFENIL)-3-METIL-5-PIRAZOLONA; FABRICAÇÃO DE");
			cnae.setNumber("2029-1/00");
			empresa.getCnaes().add(cnae);
			cnae = new Cnae();
			cnae.setId(2);
			cnae.setDescription("1-(4-SULFOFENIL)-3-METIL-5-PIRAZOLONA (ÁCIDO PIRAZÓLICO); FABRICAÇÃO DE");
			cnae.setNumber("2029-2/00");
			empresa.getCnaes().add(cnae);
			empresa.setDocumentos(new ArrayList<Documento>());
			Documento documento = new Documento();
			documento.setId(1);
			documento.setType("CNPJ");
			documento.setNumero("000000000001111/000-9");
			empresa.getDocumentos().add(documento);
			documento = new Documento();
			documento.setId(2);
			documento.setType("IM");
			documento.setNumero("00000001");
			empresa.getDocumentos().add(documento);
			empresa.setTelefones(new ArrayList<Telefone>());
			Telefone telefone = new Telefone();
			telefone.setId(1);
			telefone.setDdd("34");
			telefone.setNumero("91782776");
			empresa.getTelefones().add(telefone);
			empresa.setRegime("Simples Nacional");
			empresas.add(empresa);

		}
		empresaResponse.setTabelaList(empresas);
		return empresaResponse;
	}

	public TabelaResponse MockById()
	{
		TabelaResponse empresaResponse = new TabelaResponse();

		List<Tabela> empresas = new ArrayList<Tabela>();

		Tabela empresa = new Tabela();
		empresa.setId(1);
		empresa.setNome("nome_" + 1);
		Socio socio = new Socio();
		socio.setId(1);
		socio.setNome("Washington");
		empresa.setSocios(new ArrayList<Socio>());
		empresa.getSocios().add(socio);
		Endereco endereco = new Endereco();
		empresa.setEnderecos(new ArrayList<Endereco>());
		endereco.setBairro("bairro");
		endereco.setCep("cep");
		endereco.setCidade("cidade");
		endereco.setEstado("estado");
		endereco.setId(1);
		endereco.setLogradouro("logradouro");
		endereco.setNumero("1000");
		empresa.getEnderecos().add(endereco);
		empresa.setEmails(new ArrayList<Email>());
		Email email = new Email();
		email.setId(1);
		email.setEmail("wlclimaco@gmail.com");
		email.setDescription("NF-e");
		empresa.getEmails().add(email);
		empresa.setCnaes(new ArrayList<Cnae>());
		Cnae cnae = new Cnae();
		cnae.setId(1);
		cnae.setDescription("1-(4-BETA-HIDROXIETILSULFOFENIL)-3-METIL-5-PIRAZOLONA; FABRICAÇÃO DE");
		cnae.setNumber("2029-1/00");
		empresa.getCnaes().add(cnae);
		cnae = new Cnae();
		cnae.setId(2);
		cnae.setDescription("1-(4-SULFOFENIL)-3-METIL-5-PIRAZOLONA (ÁCIDO PIRAZÓLICO); FABRICAÇÃO DE");
		cnae.setNumber("2029-2/00");
		empresa.getCnaes().add(cnae);
		empresa.setDocumentos(new ArrayList<Documento>());
		Documento documento = new Documento();
		documento.setId(1);
		documento.setType("CNPJ");
		documento.setNumero("000000000001111/000-9");
		empresa.getDocumentos().add(documento);
		documento = new Documento();
		documento.setId(2);
		documento.setType("IM");
		documento.setNumero("00000001");
		empresa.getDocumentos().add(documento);
		empresa.setTelefones(new ArrayList<Telefone>());
		Telefone telefone = new Telefone();
		telefone.setId(1);
		telefone.setDdd("34");
		telefone.setNumero("91782776");
		telefone.setType("Residencial");
		empresa.getTelefones().add(telefone);
		empresa.setRegime("Simples Nacional");
		empresas.add(empresa);

		empresaResponse.setTabelaList(empresas);
		return empresaResponse;
	}
}
