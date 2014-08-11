package com.qat.samples.sysmgmt.produto.bad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.produto.model.Embalagem;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.request.EmbalagemInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.supermercado.model.Supermercado;
import com.qat.samples.sysmgmt.supermercado.model.request.SupermercadoInquiryRequest;
import com.qat.samples.sysmgmt.util.Criteria;
import com.qat.samples.sysmgmt.util.Filter;
import com.qat.samples.sysmgmt.util.TableTypeEnum;

/**
 * Delegate class for the Procedure BAC. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ProdutoBAD
{

	private static Random PRICE = new Random();

	private ProdutoBAD()
	{
	}

	/**
	 * Calculate procedure price.
	 * 
	 * @param i the i
	 * 
	 * @return the big decimal
	 */
	public static SupermercadoInquiryRequest MontarFiltroSup(List<Filter> filters)
	{
		SupermercadoInquiryRequest request = new SupermercadoInquiryRequest();
		List<Supermercado> listSuper = new ArrayList<Supermercado>();
		for (Integer i = 0; i < filters.size(); i++)
		{
			if (filters.get(i).getTableEnum().equals(TableTypeEnum.SUPERMERCADO))
			{

				for (Integer a = 0; a < filters.get(i).getListId().size(); a++)
				{
					listSuper.add(new Supermercado(filters.get(i).getListId().get(a)));
				}
				if (ValidationUtil.isNullOrEmpty(filters.get(i).getNome()))
				{
					Supermercado cadastro = new Supermercado();
					cadastro.setNome(filters.get(i).getNome());
					listSuper.add(cadastro);
				}
			}
		}
		Criteria criteria = new Criteria();
		criteria.setSupermercado(listSuper);
		request.setCriteria(criteria);
		return request;
	}

	public static ProdutoInquiryRequest MontarFiltrosProd(List<Filter> filters)
	{
		ProdutoInquiryRequest request = new ProdutoInquiryRequest();
		String produtos = "";
		String cadastros = "";
		String embalagens = "";
		List<Produto> listSuper = new ArrayList<Produto>();
		List<Cadastro> listCad = new ArrayList<Cadastro>();
		List<Embalagem> listEmb = new ArrayList<Embalagem>();
		for (Integer i = 0; i < filters.size(); i++)
		{
			if (filters.get(i).getTableEnum().equals(TableTypeEnum.PRODUTO))
			{
				for (Integer a = 0; a < filters.get(i).getListId().size(); a++)
				{
					if (a < (filters.get(i).getListId().size() - 1))
					{
						produtos = produtos + "" + filters.get(i).getListId().get(a) + ",";
					}
					else
					{
						produtos = produtos + "" + filters.get(i).getListId().get(a);
					}
				}
				if (ValidationUtil.isNullOrEmpty(filters.get(i).getNome()))
				{
					Produto cadastro = new Produto();
					cadastro.setStringIds(produtos);
					cadastro.setNome(filters.get(i).getNome());
					listSuper.add(cadastro);
				}
			}
			if ((filters.get(i).getTableEnum().equals(TableTypeEnum.MARCA)) ||
					(filters.get(i).getTableEnum().equals(TableTypeEnum.MENU)) ||
					(filters.get(i).getTableEnum().equals(TableTypeEnum.SUBMENU)) ||
					(filters.get(i).getTableEnum().equals(TableTypeEnum.TRIMENU)) ||
					(filters.get(i).getTableEnum().equals(TableTypeEnum.UNIMED)))
			{

				for (Integer a = 0; a < filters.get(i).getListId().size(); a++)
				{
					if (a < (filters.get(i).getListId().size() - 1))
					{
						cadastros = cadastros + "" + filters.get(i).getListId().get(a) + ",";
					}
					else
					{
						cadastros = cadastros + "" + filters.get(i).getListId().get(a);
					}
				}
				if (ValidationUtil.isNullOrEmpty(filters.get(i).getNome()))
				{
					Cadastro cadastro = new Cadastro();
					cadastro.setStringIds(cadastros);
					cadastro.setNome(filters.get(i).getNome());
					listCad.add(cadastro);
				}
			}
			if (filters.get(i).getTableEnum().equals(TableTypeEnum.EMBALAGEM))
			{

				for (Integer a = 0; a < filters.get(i).getListId().size(); a++)
				{
					if (a < (filters.get(i).getListId().size() - 1))
					{
						embalagens = embalagens + "" + filters.get(i).getListId().get(a) + ",";
					}
					else
					{
						embalagens = embalagens + "" + filters.get(i).getListId().get(a);
					}
				}
				if (ValidationUtil.isNullOrEmpty(filters.get(i).getNome()))
				{
					Embalagem cadastro = new Embalagem();
					cadastro.setStringIds(embalagens);
					cadastro.setNome(filters.get(i).getNome());
					listEmb.add(cadastro);
				}

			}
		}

		Criteria criteria = new Criteria();
		criteria.setProduto(listSuper);
		criteria.setCadastros(listCad);
		criteria.setEmbalagens(listEmb);
		request.setCriteria(criteria);

		return request;
	}

	public static ProdutoInquiryRequest MontarFiltrosCadas(List<Filter> filters)
	{
		ProdutoInquiryRequest request = new ProdutoInquiryRequest();
		List<Cadastro> listSuper = new ArrayList<Cadastro>();
		List<Embalagem> listCadastro = new ArrayList<Embalagem>();
		for (Integer i = 0; i < filters.size(); i++)
		{
			if ((filters.get(i).getTableEnum().equals(TableTypeEnum.MARCA)) ||
					(filters.get(i).getTableEnum().equals(TableTypeEnum.MENU)) ||
					(filters.get(i).getTableEnum().equals(TableTypeEnum.SUBMENU)) ||
					(filters.get(i).getTableEnum().equals(TableTypeEnum.TRIMENU)) ||
					(filters.get(i).getTableEnum().equals(TableTypeEnum.UNIMED)))
			{

				for (Integer a = 0; a < filters.get(i).getListId().size(); a++)
				{
					listSuper.add(new Cadastro(filters.get(i).getListId().get(a)));
				}
				if (ValidationUtil.isNullOrEmpty(filters.get(i).getNome()))
				{
					Cadastro cadastro = new Cadastro();
					cadastro.setNome(filters.get(i).getNome());
					listSuper.add(cadastro);
				}
			}
			if (filters.get(i).getTableEnum().equals(TableTypeEnum.EMBALAGEM))
			{

				for (Integer a = 0; a < filters.get(i).getListId().size(); a++)
				{
					listCadastro.add(new Embalagem(filters.get(i).getListId().get(a)));
				}
				if (ValidationUtil.isNullOrEmpty(filters.get(i).getNome()))
				{
					Embalagem cadastro = new Embalagem();
					cadastro.setNome(filters.get(i).getNome());
					listCadastro.add(cadastro);
				}

			}
		}
		Criteria criteria = new Criteria();
		criteria.setCadastros(listSuper);
		criteria.setEmbalagens(listCadastro);
		request.setCriteria(criteria);

		return request;
	}

	// public static ProdutoInquiryRequest MontarFiltrosProd(List<Filter> filters)
	// {
	// ProdutoInquiryRequest request = new ProdutoInquiryRequest();
	// for (Integer i = 0; i < filters.size(); i++){
	// if (filters.get(i).getTableEnum().equals(TableTypeEnum.CLIENTE))
	// {
	// List<Cliente> listSuper = new ArrayList<Cliente>();
	// for (Integer a = 0; a < filters.get(i).getListId().size(); a++)
	// {
	// listSuper.add(new Cliente(filters.get(i).getListId().get(a)));
	// }
	// if (ValidationUtil.isNullOrEmpty(filters.get(i).getNome()))
	// {
	// Cliente cadastro = new Cliente();
	// cadastro.setNome(filters.get(i).getNome());
	// listSuper.add(cadastro);
	// }
	// }
	// }
	// public static ProdutoInquiryRequest MontarFiltrosProd(List<Filter> filters)
	// {
	// ProdutoInquiryRequest request = new ProdutoInquiryRequest();
	// for (Integer i = 0; i < filters.size(); i++){
	// if (filters.get(i).getTableEnum().equals(TableTypeEnum.CIDADE))
	// {
	// List<Cidade> listSuper = new ArrayList<Cidade>();
	// for (Integer a = 0; a < filters.get(i).getListId().size(); a++)
	// {
	// listSuper.add(new Cidade(filters.get(i).getListId().get(a)));
	// }
	// if (ValidationUtil.isNullOrEmpty(filters.get(i).getNome()))
	// {
	// Cidade cadastro = new Cidade(filters.get(i).getNome());
	// listSuper.add(cadastro);
	// }
	// }
	//
	// }
	// }
	public static EmbalagemInquiryRequest MontarFiltrosEmbalagem(List<Filter> filters)
	{
		EmbalagemInquiryRequest request = new EmbalagemInquiryRequest();
		List<Embalagem> listSuper = new ArrayList<Embalagem>();
		for (Integer i = 0; i < filters.size(); i++)
		{
			if (filters.get(i).getTableEnum().equals(TableTypeEnum.EMBALAGEM))
			{

				for (Integer a = 0; a < filters.get(i).getListId().size(); a++)
				{
					listSuper.add(new Embalagem(filters.get(i).getListId().get(a)));
				}
				if (ValidationUtil.isNullOrEmpty(filters.get(i).getNome()))
				{
					Embalagem cadastro = new Embalagem();
					cadastro.setNome(filters.get(i).getNome());
					listSuper.add(cadastro);
				}

			}

		}
		Criteria criteria = new Criteria();
		criteria.setEmbalagens(listSuper);
		request.setCriteria(criteria);

		return request;

	}
}
