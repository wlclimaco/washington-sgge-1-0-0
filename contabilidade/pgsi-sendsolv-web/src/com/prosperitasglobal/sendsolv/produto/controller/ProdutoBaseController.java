package com.prosperitasglobal.sendsolv.produto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import com.prosperitasglobal.controller.delegate.UtilControllerD;

public class ProdutoBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProdutoBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ProdutoBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Produto BAI. */
	private IProdutoBAI locationBAI;

	/**
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public IProdutoBAI getProdutoBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the location bai
	 */
	@Resource
	public void setProdutoBAI(IProdutoBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Produto edit mav.
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
						getMapper().writeValueAsString(fetchProdutoById(new FetchByIdRequest(locationId))));

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
	public ProdutoResponse fetchProdutoByRequest(PagedInquiryRequest pagedInquiryRequest)
	{

		ProdutoResponse locationResponse = new ProdutoResponse();
		try
		{

			locationResponse = Mock();
			// getProdutoBAI().fetchProdutoByRequest(pagedInquiryRequest);

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
	public ProdutoResponse fetchProdutoById(FetchByIdRequest fetchByIdRequest)
	{

		ProdutoResponse locationResponse = new ProdutoResponse();
		try
		{

			locationResponse = MockById();
			// getProdutoBAI().fetchProdutoById(fetchByIdRequest);

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
	public ProdutoResponse fetchProdutoByOrganization(PagedInquiryRequest pagedInquiryRequest)
	{

		ProdutoResponse locationResponse = new ProdutoResponse();
		try
		{

			locationResponse =
					getProdutoBAI().fetchProdutoByOrganization(pagedInquiryRequest);

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

	public ProdutoResponse Mock()
	{
		ProdutoResponse produtoResponse = new ProdutoResponse();

		List<Produto> produtos = new ArrayList<Produto>();
		for (Integer i = 0; i < 100; i++)
		{
			Produto produto = new Produto();
			produto.setId(i);
			produto.setNome("nome_" + i);
			Socio socio = new Socio();
			socio.setId(1);
			socio.setNome("Washington");
			produto.setSocios(new ArrayList<Socio>());
			produto.getSocios().add(socio);
			Endereco endereco = new Endereco();
			produto.setEnderecos(new ArrayList<Endereco>());
			endereco.setBairro("bairro");
			endereco.setCep("cep");
			endereco.setCidade("cidade");
			endereco.setEstado("estado");
			endereco.setId(1);
			endereco.setLogradouro("logradouro");
			endereco.setNumero("1000");
			produto.getEnderecos().add(endereco);
			produto.setEmails(new ArrayList<Email>());
			Email email = new Email();
			email.setId(1);
			email.setEmail("wlclimaco@gmail.com");
			produto.getEmails().add(email);
			produto.setCnaes(new ArrayList<Cnae>());
			Cnae cnae = new Cnae();
			cnae.setId(1);
			cnae.setDescription("1-(4-BETA-HIDROXIETILSULFOFENIL)-3-METIL-5-PIRAZOLONA; FABRICAÇÃO DE");
			cnae.setNumber("2029-1/00");
			produto.getCnaes().add(cnae);
			cnae = new Cnae();
			cnae.setId(2);
			cnae.setDescription("1-(4-SULFOFENIL)-3-METIL-5-PIRAZOLONA (ÁCIDO PIRAZÓLICO); FABRICAÇÃO DE");
			cnae.setNumber("2029-2/00");
			produto.getCnaes().add(cnae);
			produto.setDocumentos(new ArrayList<Documento>());
			Documento documento = new Documento();
			documento.setId(1);
			documento.setType("CNPJ");
			documento.setNumero("000000000001111/000-9");
			produto.getDocumentos().add(documento);
			documento = new Documento();
			documento.setId(2);
			documento.setType("IM");
			documento.setNumero("00000001");
			produto.getDocumentos().add(documento);
			produto.setTelefones(new ArrayList<Telefone>());
			Telefone telefone = new Telefone();
			telefone.setId(1);
			telefone.setDdd("34");
			telefone.setNumero("91782776");
			produto.getTelefones().add(telefone);
			produto.setRegime("Simples Nacional");
			produtos.add(produto);

		}
		produtoResponse.setProdutoList(produtos);
		return produtoResponse;
	}

	public ProdutoResponse MockById()
	{
		ProdutoResponse produtoResponse = new ProdutoResponse();

		List<Produto> produtos = new ArrayList<Produto>();

		Produto produto = new Produto();
		produto.setId(1);
		produto.setNome("nome_" + 1);
		Socio socio = new Socio();
		socio.setId(1);
		socio.setNome("Washington");
		produto.setSocios(new ArrayList<Socio>());
		produto.getSocios().add(socio);
		Endereco endereco = new Endereco();
		produto.setEnderecos(new ArrayList<Endereco>());
		endereco.setBairro("bairro");
		endereco.setCep("cep");
		endereco.setCidade("cidade");
		endereco.setEstado("estado");
		endereco.setId(1);
		endereco.setLogradouro("logradouro");
		endereco.setNumero("1000");
		produto.getEnderecos().add(endereco);
		produto.setEmails(new ArrayList<Email>());
		Email email = new Email();
		email.setId(1);
		email.setEmail("wlclimaco@gmail.com");
		email.setDescription("NF-e");
		produto.getEmails().add(email);
		produto.setCnaes(new ArrayList<Cnae>());
		Cnae cnae = new Cnae();
		cnae.setId(1);
		cnae.setDescription("1-(4-BETA-HIDROXIETILSULFOFENIL)-3-METIL-5-PIRAZOLONA; FABRICAÇÃO DE");
		cnae.setNumber("2029-1/00");
		produto.getCnaes().add(cnae);
		cnae = new Cnae();
		cnae.setId(2);
		cnae.setDescription("1-(4-SULFOFENIL)-3-METIL-5-PIRAZOLONA (ÁCIDO PIRAZÓLICO); FABRICAÇÃO DE");
		cnae.setNumber("2029-2/00");
		produto.getCnaes().add(cnae);
		produto.setDocumentos(new ArrayList<Documento>());
		Documento documento = new Documento();
		documento.setId(1);
		documento.setType("CNPJ");
		documento.setNumero("000000000001111/000-9");
		produto.getDocumentos().add(documento);
		documento = new Documento();
		documento.setId(2);
		documento.setType("IM");
		documento.setNumero("00000001");
		produto.getDocumentos().add(documento);
		produto.setTelefones(new ArrayList<Telefone>());
		Telefone telefone = new Telefone();
		telefone.setId(1);
		telefone.setDdd("34");
		telefone.setNumero("91782776");
		telefone.setType("Residencial");
		produto.getTelefones().add(telefone);
		produto.setRegime("Simples Nacional");
		produtos.add(produto);

		produtoResponse.setProdutoList(produtos);
		return produtoResponse;
	}
}
