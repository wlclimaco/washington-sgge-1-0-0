package com.prosperitasglobal.sendsolv.tela.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import com.prosperitasglobal.controller.delegate.UtilControllerD;

/**
 * The Class TelaBaseController.
 */

/**
 * @author Flavio Tosta.
 *
 */
public class TelaBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TelaBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "TelaBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Tela BAI. */
	private ITelaBAI locationBAI;

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
	public ITelaBAI getTelaBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the location bai
	 */
	@Resource
	public void setTelaBAI(ITelaBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Tela edit mav.
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
						getMapper().writeValueAsString(fetchTelaById(new FetchByIdRequest(locationId))));

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
	public TelaResponse fetchTelaByRequest(PagedInquiryRequest pagedInquiryRequest)
	{

		TelaResponse locationResponse = new TelaResponse();
		try
		{

			locationResponse = Mock();
			// getTelaBAI().fetchTelaByRequest(pagedInquiryRequest);

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
	public TelaResponse fetchTelaById(FetchByIdRequest fetchByIdRequest)
	{

		TelaResponse locationResponse = new TelaResponse();
		try
		{

			locationResponse = MockById();
			// getTelaBAI().fetchTelaById(fetchByIdRequest);

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
	public TelaResponse fetchTelaByOrganization(PagedInquiryRequest pagedInquiryRequest)
	{

		TelaResponse locationResponse = new TelaResponse();
		try
		{

			locationResponse =
					getTelaBAI().fetchTelaByOrganization(pagedInquiryRequest);

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

	public TelaResponse Mock()
	{
		TelaResponse telaResponse = new TelaResponse();

		List<Tela> telas = new ArrayList<Tela>();
		for (Integer i = 0; i < 100; i++)
		{
			Tela tela = new Tela();
			tela.setId(i);
			tela.setNome("nome_" + i);
			Socio socio = new Socio();
			socio.setId(1);
			socio.setNome("Washington");
			tela.setSocios(new ArrayList<Socio>());
			tela.getSocios().add(socio);
			Endereco endereco = new Endereco();
			tela.setEnderecos(new ArrayList<Endereco>());
			endereco.setBairro("bairro");
			endereco.setCep("cep");
			endereco.setCidade("cidade");
			endereco.setEstado("estado");
			endereco.setId(1);
			endereco.setLogradouro("logradouro");
			endereco.setNumero("1000");
			tela.getEnderecos().add(endereco);
			tela.setEmails(new ArrayList<Email>());
			Email email = new Email();
			email.setId(1);
			email.setEmail("wlclimaco@gmail.com");
			tela.getEmails().add(email);
			tela.setCnaes(new ArrayList<Cnae>());
			Cnae cnae = new Cnae();
			cnae.setId(1);
			cnae.setDescription("1-(4-BETA-HIDROXIETILSULFOFENIL)-3-METIL-5-PIRAZOLONA; FABRICAÇÃO DE");
			cnae.setNumber("2029-1/00");
			tela.getCnaes().add(cnae);
			cnae = new Cnae();
			cnae.setId(2);
			cnae.setDescription("1-(4-SULFOFENIL)-3-METIL-5-PIRAZOLONA (ÁCIDO PIRAZÓLICO); FABRICAÇÃO DE");
			cnae.setNumber("2029-2/00");
			tela.getCnaes().add(cnae);
			tela.setDocumentos(new ArrayList<Documento>());
			Documento documento = new Documento();
			documento.setId(1);
			documento.setType("CNPJ");
			documento.setNumero("000000000001111/000-9");
			tela.getDocumentos().add(documento);
			documento = new Documento();
			documento.setId(2);
			documento.setType("IM");
			documento.setNumero("00000001");
			tela.getDocumentos().add(documento);
			tela.setTelefones(new ArrayList<Telefone>());
			Telefone telefone = new Telefone();
			telefone.setId(1);
			telefone.setDdd("34");
			telefone.setNumero("91782776");
			tela.getTelefones().add(telefone);
			tela.setRegime("Simples Nacional");
			telas.add(tela);

		}
		telaResponse.setTelaList(telas);
		return telaResponse;
	}

	public TelaResponse MockById()
	{
		TelaResponse telaResponse = new TelaResponse();

		List<Tela> telas = new ArrayList<Tela>();

		Tela tela = new Tela();
		tela.setId(1);
		tela.setNome("nome_" + 1);
		Socio socio = new Socio();
		socio.setId(1);
		socio.setNome("Washington");
		tela.setSocios(new ArrayList<Socio>());
		tela.getSocios().add(socio);
		Endereco endereco = new Endereco();
		tela.setEnderecos(new ArrayList<Endereco>());
		endereco.setBairro("bairro");
		endereco.setCep("cep");
		endereco.setCidade("cidade");
		endereco.setEstado("estado");
		endereco.setId(1);
		endereco.setLogradouro("logradouro");
		endereco.setNumero("1000");
		tela.getEnderecos().add(endereco);
		tela.setEmails(new ArrayList<Email>());
		Email email = new Email();
		email.setId(1);
		email.setEmail("wlclimaco@gmail.com");
		email.setDescription("NF-e");
		tela.getEmails().add(email);
		tela.setCnaes(new ArrayList<Cnae>());
		Cnae cnae = new Cnae();
		cnae.setId(1);
		cnae.setDescription("1-(4-BETA-HIDROXIETILSULFOFENIL)-3-METIL-5-PIRAZOLONA; FABRICAÇÃO DE");
		cnae.setNumber("2029-1/00");
		tela.getCnaes().add(cnae);
		cnae = new Cnae();
		cnae.setId(2);
		cnae.setDescription("1-(4-SULFOFENIL)-3-METIL-5-PIRAZOLONA (ÁCIDO PIRAZÓLICO); FABRICAÇÃO DE");
		cnae.setNumber("2029-2/00");
		tela.getCnaes().add(cnae);
		tela.setDocumentos(new ArrayList<Documento>());
		Documento documento = new Documento();
		documento.setId(1);
		documento.setType("CNPJ");
		documento.setNumero("000000000001111/000-9");
		tela.getDocumentos().add(documento);
		documento = new Documento();
		documento.setId(2);
		documento.setType("IM");
		documento.setNumero("00000001");
		tela.getDocumentos().add(documento);
		tela.setTelefones(new ArrayList<Telefone>());
		Telefone telefone = new Telefone();
		telefone.setId(1);
		telefone.setDdd("34");
		telefone.setNumero("91782776");
		telefone.setType("Residencial");
		tela.getTelefones().add(telefone);
		tela.setRegime("Simples Nacional");
		telas.add(tela);

		telaResponse.setTelaList(telas);
		return telaResponse;
	}
}
