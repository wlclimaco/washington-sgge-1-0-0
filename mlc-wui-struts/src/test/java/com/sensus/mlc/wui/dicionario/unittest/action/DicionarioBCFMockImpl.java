package com.sensus.mlc.wui.dicionario.unittest.action;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.ResultsSetInfo;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.dicionario.bcf.IDicionarioBCF;
import com.sensus.mlc.dicionario.model.Abas;
import com.sensus.mlc.dicionario.model.Atributos;
import com.sensus.mlc.dicionario.model.Dicionario;
import com.sensus.mlc.dicionario.model.DicionarioTypeEnum;
import com.sensus.mlc.dicionario.model.Table;
import com.sensus.mlc.dicionario.model.Tabs;
import com.sensus.mlc.dicionario.model.Tela;
import com.sensus.mlc.dicionario.model.Validation;
import com.sensus.mlc.dicionario.model.request.TelaRequest;
import com.sensus.mlc.dicionario.model.response.TelaResponse;
import com.sensus.mlc.dicionario.model.response.InquiryTelaResponse;
import com.sensus.mlc.filial.model.Filial;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

/**
 * Test the functionality of GroupAjaxAction.
 * 
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class DicionarioBCFMockImpl extends BaseMockImpl implements IDicionarioBCF
{

	/** The Constant GROUP_COUNT. */
	public static final Integer GROUP_COUNT = 20;

	/** The Constant GROUP_FILTER_COUNT. */
	public static final Integer GROUP_FILTER_COUNT = 19;

	/** The Constant GROUP_NAME. */
	public static final String GROUP_NAME = "Group %d";

	/** The Constant SMARTPOINT_NAME. */
	public static final String SMARTPOINT_NAME = "SmartPoint %d";
	
	public Integer pageSize;

	
	private Dicionario createDicionario(Integer id,Integer positionX,Integer positionY,Integer tamanho,String name,boolean busca,boolean requerid,DicionarioTypeEnum type,List<String>listComboBox){
		Dicionario dicionario = new Dicionario();
		if(DicionarioTypeEnum.COMBO.equals(type)){
			dicionario.setId(id);
			dicionario.setLabel(name);
			dicionario.setName(name);
			dicionario.setPositionX(new Double(positionX));
			dicionario.setPositionY(new Double(positionY));
			dicionario.setType(type);
			dicionario.setBusca(busca);
			dicionario.setRequerid(requerid);
			dicionario.setTamanho(tamanho);
			dicionario.setDominios(listComboBox);
		}else{			
			dicionario.setId(id);
			dicionario.setLabel(name);
			dicionario.setName(name);
			dicionario.setPositionX(new Double(positionX));
			dicionario.setPositionY(new Double(positionY));
			dicionario.setType(type);
			dicionario.setBusca(busca);
			dicionario.setRequerid(requerid);
			dicionario.setTamanho(tamanho);
		}
		return dicionario;
	}
	
	private Abas createAbs(List<Dicionario> dicionario ,String title,Integer id){
		Abas  abs = new Abas();		
		abs.setId_abs(2);
		abs.setTitle("Administrativo");
		abs.setAtributos(dicionario);
	return abs;	
		
	}
	
	
	private List<Tabs> createTabs(Integer id_tab,Integer height, Integer width,List<Abas> abs){
		List<Tabs>  tabss = new ArrayList<Tabs>();
		Tabs tabs = new Tabs();
		tabs.setId_tab(id_tab);
		tabs.setHeight(height);
		tabs.setWidth(width);
		tabs.setAbs(abs);
		tabss.add(tabs);
		return tabss;
	}
	
	private Tela createTela(Integer id_tela,String title,Integer height,Integer width,List<Tabs>tabs){
		Tela tela = new Tela(3);
		tela.setId_tela(id_tela);
		tela.setTitle(title);
		tela.setHeight(height);
		tela.setWidth(width);
		tela.setTabs(tabs);
		return tela;
	}
	@Override
	public TelaResponse insertDicionario(
			TelaRequest dicionarioRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TelaResponse updateDicionario(
			TelaRequest dicionarioRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TelaResponse deleteDicionario(
			TelaRequest dicionarioRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InquiryTelaResponse fetchAllDicionarios(
			InquiryPaginationRequest inquiryPaginationRequest) {
		
		InquiryTelaResponse response = new InquiryTelaResponse();

		if (inquiryPaginationRequest.getPageSize() > 0)
		{
			setPageSize(inquiryPaginationRequest.getPageSize());
		}

		Integer startRow = inquiryPaginationRequest.getStartRow();

		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setDicionarios(new ArrayList<Dicionario>());
			for (int i = startRow; i < (startRow + getPageSize()); i++)
			{
				Dicionario dicionario = new Dicionario();
				
				 List<String> wordcount = new ArrayList<String>();
				 
				 wordcount.add("1|aaaaaaaa");
				 wordcount.add("2|aaaaaaab");
				 wordcount.add("3|aaaaaaac");
				 wordcount.add("4|aaaaaaad");
				
				dicionario.setId(i);
				dicionario.setLabel("Label"+i);
				dicionario.setName("Name"+i);
				dicionario.setPositionX(new Double(2.2));
				dicionario.setPositionY(new Double(3.2));
				dicionario.setType(DicionarioTypeEnum.COMBO);
				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(200);
				dicionario.setValidation(new ArrayList<Validation>());
				
				Validation validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 200 caracter");
				dicionario.getValidation().add(validation);
				
				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setCreateDate(new Date());
				statusMessage.setDate(new Date());

				response.getDicionarios().add(dicionario);

			}
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			ResultsSetInfo resultSetInfo = new ResultsSetInfo();
			resultSetInfo.setTotalRowsAvailable(20);

			response.setResultsSetInfo(resultSetInfo);

			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public TelaResponse fetchDicionarioById(
			TelaRequest dicionarioRequest) {
		
		TelaResponse response = new TelaResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{

			
			
				Tela tela = new Tela(1);
				Dicionario dicionario = new Dicionario();
				
				List<Dicionario> dicionarios = new ArrayList<Dicionario>();
				
				List<String> wordcount = new ArrayList<String>();
				 
				 wordcount.add("1|aaaaaaaa");
				 wordcount.add("2|aaaaaaab");
				 wordcount.add("3|aaaaaaac");
				 wordcount.add("4|aaaaaaad");
				
				dicionario.setId(1);
				dicionario.setLabel("Cod Grupo");
				dicionario.setName("codGrupo");
				dicionario.setPositionX(new Double(2.2));
				dicionario.setPositionY(new Double(3.2));
				dicionario.setType(DicionarioTypeEnum.INPUT);
//				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.TRUE);
				dicionario.setTamanho(50);
				dicionario.setValidation(new ArrayList<Validation>());
				
				Validation validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(2);
				dicionario.setLabel("Descricao do grupo");
				dicionario.setName("desGrupo");
				dicionario.setPositionX(new Double(2.2));
				dicionario.setPositionY(new Double(3.2));
				dicionario.setType(DicionarioTypeEnum.INPUT);
//				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.TRUE);
				dicionario.setTamanho(70);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 70 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(3);
				dicionario.setLabel("Sigla");
				dicionario.setName("sigla");
				dicionario.setPositionX(new Double(2.2));
				dicionario.setPositionY(new Double(3.2));
				dicionario.setType(DicionarioTypeEnum.INPUT);
//				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(50);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(4);
				dicionario.setLabel("Permitir saldo negativo ?");
				dicionario.setName("perSalNeg");
				dicionario.setPositionX(new Double(2.2));
				dicionario.setPositionY(new Double(3.2));
				dicionario.setType(DicionarioTypeEnum.CHECKBOX);
//				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(50);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(5);
				dicionario.setLabel("Permitir Saldo de lote negativo");
				dicionario.setName("perSalLotNeg");
				dicionario.setPositionX(new Double(2.2));
				dicionario.setPositionY(new Double(3.2));
				dicionario.setType(DicionarioTypeEnum.CHECKBOX);
//				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(50);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(6);
				dicionario.setLabel("Publicar na web ?");
				dicionario.setName("pubWeb");
				dicionario.setPositionX(new Double(2.2));
				dicionario.setPositionY(new Double(3.2));
				dicionario.setType(DicionarioTypeEnum.CHECKBOX);
//				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(50);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(7);
				dicionario.setLabel("Teste");
				dicionario.setName("test");
				dicionario.setPositionX(new Double(2.2));
				dicionario.setPositionY(new Double(3.2));
				dicionario.setType(DicionarioTypeEnum.COMBO);
				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(50);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
				
				
				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setCreateDate(new Date());
				statusMessage.setDate(new Date());

				tela.setId_tela(1);
				tela.setAtributos(dicionarios);
				response.getTelas().add(tela);

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			ResultsSetInfo resultSetInfo = new ResultsSetInfo();
			resultSetInfo.setTotalRowsAvailable(20);



			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.dicionario.bcf.IDicionarioBCF#fetchTelaById(com.sensus.mlc.dicionario.model.request.DicionarioRequest)
	 */
	@Override
	public TelaResponse fetchTelaById(TelaRequest dicionarioRequest) {
		
		TelaResponse response = new TelaResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{

		if(dicionarioRequest.getTela().getId_tela()== 1){	
			
				Tela tela = new Tela(1);
				Dicionario dicionario = new Dicionario();
				tela.setHeight(200);
				tela.setWidth(450);
				tela.setTitle("Grupo");
				List<Dicionario> dicionarios = new ArrayList<Dicionario>();
				
				List<String> wordcount = new ArrayList<String>();
				 
				 wordcount.add("1|aaaaaaaa");
				 wordcount.add("2|aaaaaaab");
				 wordcount.add("3|aaaaaaac");
				 wordcount.add("4|aaaaaaad");
				
				dicionario.setId(1);
				dicionario.setLabel("Cod Grupo");
				dicionario.setName("codGrupo");
				dicionario.setPositionX(new Double(5));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.INPUT);
//				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.TRUE);
				dicionario.setTamanho(60);
				dicionario.setValidation(new ArrayList<Validation>());
				
				Validation validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(2);
				dicionario.setLabel("Descricao do grupo");
				dicionario.setName("desGrupo");
				dicionario.setPositionX(new Double(5));
				dicionario.setPositionY(new Double(100));
				dicionario.setType(DicionarioTypeEnum.INPUT);
//				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.TRUE);
				dicionario.setTamanho(200);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 70 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(3);
				dicionario.setLabel("Sigla");
				dicionario.setName("sigla");
				dicionario.setPositionX(new Double(5));
				dicionario.setPositionY(new Double(320));
				dicionario.setType(DicionarioTypeEnum.INPUT);
//				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(50);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(4);
				dicionario.setLabel("Permitir saldo negativo ?");
				dicionario.setName("perSalNeg");
				dicionario.setPositionX(new Double(50));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.CHECKBOX);
//				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(50);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(5);
				dicionario.setLabel("Permitir Saldo de lote negativo");
				dicionario.setName("perSalLotNeg");
				dicionario.setPositionX(new Double(70));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.CHECKBOX);
//				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(50);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(6);
				dicionario.setLabel("Publicar na web ?");
				dicionario.setName("pubWeb");
				dicionario.setPositionX(new Double(90));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.CHECKBOX);
//				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(50);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(7);
				dicionario.setLabel("Teste");
				dicionario.setName("test");
				dicionario.setPositionX(new Double(110));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.COMBO);
				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(120);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
				
				
				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setCreateDate(new Date());
				statusMessage.setDate(new Date());

				tela.setId_tela(1);
				tela.setAtributos(dicionarios);
				response.setTelas(new ArrayList<Tela>());
				response.getTelas().add(tela);
				
				
				
//======================== cadastro empresa/filial ==================================================================//
		}
				if(dicionarioRequest.getTela().getId_tela()== 2){
				Tela tela = new Tela(2);
				Dicionario dicionario = new Dicionario();
				tela.setHeight(800);
				tela.setWidth(800);
				tela.setTitle("Empresa");
				List<Dicionario> dicionarios = new ArrayList<Dicionario>();
				
				List<String>wordcount = new ArrayList<String>();
				 
				 wordcount.add("1|Perfil A");
				 wordcount.add("2|Perfil B");
				 wordcount.add("3|Perfil C");
				 
				 List<String>  indAtiSPEED = new ArrayList<String>();
				 
				 indAtiSPEED.add("0|<--Selecione-->");
				
				dicionario.setId(1);
				dicionario.setLabel("Cod Emp");
				dicionario.setName("codEmp");
				dicionario.setPositionX(new Double(5));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.TRUE);
				dicionario.setTamanho(60);
				dicionario.setValidation(new ArrayList<Validation>());
				
				Validation validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(2);
				dicionario.setLabel("Razao Social da Empresa");
				dicionario.setName("RazSocial");
				dicionario.setPositionX(new Double(5));
				dicionario.setPositionY(new Double(100));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.TRUE);
				dicionario.setTamanho(200);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 70 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(3);
				dicionario.setLabel("Nome Fantasia");
				dicionario.setName("nomfantasia");
				dicionario.setPositionX(new Double(50));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(290);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================

				dicionario = new Dicionario();
				dicionario.setId(4);
				dicionario.setLabel("Contato");
				dicionario.setName("Contato");
				dicionario.setPositionX(new Double(95));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(290);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(5);
				dicionario.setLabel("Cod EAN");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(140));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(60);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(5);
				dicionario.setLabel("Intercambio de almox entre filiais ?");
				dicionario.setName("intAlmFil");
				dicionario.setPositionX(new Double(150));
				dicionario.setPositionY(new Double(90));
				dicionario.setType(DicionarioTypeEnum.CHECKBOX);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(50);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
//==============================================================================================
				dicionario = new Dicionario();
				dicionario.setId(7);
				dicionario.setLabel("Geral");
				dicionario.setName("geral");
				dicionario.setPositionX(new Double(110));
				dicionario.setPositionY(new Double(80));
				dicionario.setType(DicionarioTypeEnum.TAB);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(50);
				tela.setTabs(new ArrayList<Tabs>());
				Tabs tabs = new Tabs();
				tabs.setId_tab(1);
				tabs.setHeight(300);
				tabs.setWidth(780);
				tabs.setAbs(new ArrayList<Abas>());
				
				List<Abas>  abss = new ArrayList<Abas>();
				
//====================================== abs 001 ==========================================
				Abas abs = new Abas();
				abs.setId_abs(1);
				abs.setTitle("Geral");
				abs.setAtributos(new ArrayList<Dicionario>());
				dicionarios.add(dicionario);
				//============================
				List<Dicionario>  dicionarioTabs = new ArrayList<Dicionario>();
				dicionario = new Dicionario();
				dicionario.setId(1);
				dicionario.setLabel("Cod Filial");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(50));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(60);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				dicionario = new Dicionario();
				dicionario.setId(2);
				dicionario.setLabel("Razao Social da Filial");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(50));
				dicionario.setPositionY(new Double(100));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.TRUE);
				dicionario.setTamanho(200);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				dicionario = new Dicionario();
				dicionario.setId(3);
				dicionario.setLabel("Nome Fantasia");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(50));
				dicionario.setPositionY(new Double(320));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(200);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				dicionario = new Dicionario();
				dicionario.setId(4);
				dicionario.setLabel("CNPJ");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(100));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.TRUE);
				dicionario.setTamanho(120);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				dicionario = new Dicionario();
				dicionario.setId(5);
				dicionario.setLabel("Inscricao Estadual");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(100));
				dicionario.setPositionY(new Double(160));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.TRUE);
				dicionario.setTamanho(120);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(6);
				dicionario.setLabel("Inscricao Municipal");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(100));
				dicionario.setPositionY(new Double(300));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(120);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(7);
				dicionario.setLabel("CNAE");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(100));
				dicionario.setPositionY(new Double(440));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(80);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(8);
				dicionario.setLabel("Endereco");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(140));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.TRUE);
				dicionario.setTamanho(400);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(9);
				dicionario.setLabel("Numero");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(140));
				dicionario.setPositionY(new Double(440));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(80);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(10);
				dicionario.setLabel("Complemento");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(180));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(250);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(11);
				dicionario.setLabel("Bairro");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(180));
				dicionario.setPositionY(new Double(280));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(130);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(12);
				dicionario.setLabel("Cep");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(180));
				dicionario.setPositionY(new Double(420));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(100);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(13);
				dicionario.setLabel("DDD");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(220));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.TRUE);
				dicionario.setTamanho(50);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(14);
				dicionario.setLabel("Telefone");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(220));
				dicionario.setPositionY(new Double(88));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(100);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(15);
				dicionario.setLabel("Fax");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(220));
				dicionario.setPositionY(new Double(200));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(100);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(16);
				dicionario.setLabel("Email");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(220));
				dicionario.setPositionY(new Double(311));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(210);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(17);
				dicionario.setLabel("Pagina WEB");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(260));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(281);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(18);
				dicionario.setLabel("Cod Dist Fil");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(260));
				dicionario.setPositionY(new Double(313));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.TRUE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(100);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(19);
				dicionario.setLabel("Matriz");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(280));
				dicionario.setPositionY(new Double(440));
				dicionario.setType(DicionarioTypeEnum.CHECKBOX);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(60);
				dicionarioTabs.add(dicionario);
				
				abs.setAtributos(dicionarioTabs);
				abss.add(abs);
//========================================= fim abs001 ===================================
				
//====================================== abs 002 ==========================================
				abs = new Abas();
				abs.setId_abs(2);
				abs.setTitle("Tributacao");
				abs.setAtributos(new ArrayList<Dicionario>());
				dicionarios.add(dicionario);
				//============================
				dicionarioTabs = new ArrayList<Dicionario>();
				dicionario = new Dicionario();
				dicionario.setId(20);
				dicionario.setLabel("Simples");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(70));
				dicionario.setPositionY(new Double(70));
				dicionario.setType(DicionarioTypeEnum.CHECKBOX);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(60);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				dicionario = new Dicionario();
				dicionario.setId(21);
				dicionario.setLabel("Contribuinte IPI");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(70));
				dicionario.setPositionY(new Double(200));
				dicionario.setType(DicionarioTypeEnum.CHECKBOX);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(60);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				dicionario = new Dicionario();
				dicionario.setId(22);
				dicionario.setLabel("%PIS");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(120));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(80);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				dicionario = new Dicionario();
				dicionario.setId(23);
				dicionario.setLabel("%CONFINS");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(120));
				dicionario.setPositionY(new Double(110));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(100);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				dicionario = new Dicionario();
				dicionario.setId(24);
				dicionario.setLabel("%IR");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(120));
				dicionario.setPositionY(new Double(220));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(100);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(25);
				dicionario.setLabel("%COND SOCIAL");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(120));
				dicionario.setPositionY(new Double(330));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(100);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(26);
				dicionario.setLabel("%ISS");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(120));
				dicionario.setPositionY(new Double(440));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(100);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				//============================
				dicionario = new Dicionario();
				dicionario.setId(27);
				dicionario.setLabel("%SIMPLES");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(120));
				dicionario.setPositionY(new Double(550));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(100);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				
				//=========================
				dicionario = new Dicionario();
				dicionario.setId(28);
				dicionario.setLabel("Teste");
				dicionario.setName("test");
				dicionario.setPositionX(new Double(110));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.COMBO);
				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(120);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
				//=========================
				dicionario = new Dicionario();
				dicionario.setId(29);
				dicionario.setLabel("Teste");
				dicionario.setName("test");
				dicionario.setPositionX(new Double(110));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.COMBO);
				dicionario.setDominios(wordcount);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(120);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarios.add(dicionario);
				//============================
				abs.setAtributos(dicionarioTabs);
				abss.add(abs);
//========================================= fim abs002 ===================================
				
//====================================== abs 003 ==========================================
				abs = new Abas();
				abs.setId_abs(3);
				abs.setTitle("Contabilidade");
				abs.setAtributos(new ArrayList<Dicionario>());
				dicionarios.add(dicionario);
				//============================
				dicionarioTabs = new ArrayList<Dicionario>();

				dicionario = new Dicionario();
				dicionario.setId(1);
				dicionario.setLabel("Cod EAN");
				dicionario.setName("codEAN");
				dicionario.setPositionX(new Double(40));
				dicionario.setPositionY(new Double(20));
				dicionario.setType(DicionarioTypeEnum.INPUT);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(200);
				dicionario.setValidation(new ArrayList<Validation>());
				
				validation = new Validation();
				
				validation.setId(1);
				validation.setValidation("Tamanho 50 caracter");
				dicionario.getValidation().add(validation);
				dicionarioTabs.add(dicionario);
				//============================
				abs.setAtributos(dicionarioTabs);
				abss.add(abs);
//========================================= fim abs003 ===================================

				tabs.setAbs(abss);
				tela.getTabs().add(tabs);				
				
				
//=========================== EMPRESA ==================================================
				
				
				List<Atributos>  atributos = new ArrayList<Atributos>();
				
				Atributos atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("codemp");
				atributo.setForeignkeys(true);
				atributo.setPrimaryKey(false);
				atributo.setType("Integer");
				atributo.setTamanho(null);
				atributo.setForeignkeysTable(1);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("codfilial");
				atributo.setForeignkeys(true);
				atributo.setPrimaryKey(false);
				atributo.setType("Integer");
				atributo.setTamanho(null);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);
				
				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("razfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("nomefilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("mzfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("cnpjfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("inscfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("endfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("numfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("complfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("bairfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("cepfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("cidfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);
				
				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("uffilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("dddfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("fonefilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("faxfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("emailfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("wwwfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("coddistfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("percpisfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("perccofinsfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("percirfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("perccsocialfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("simplesfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("percsimplesfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("codmunic");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("siglauf");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("codpais");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("codempuc");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("codfilialuc");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("codunifcod");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("inscmunfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("cnaefilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("percissfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("contribipifilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("timbrefilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("perfilfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("indativfilial");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("dtins");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("hins");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("idusuins");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("dtalt");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("halt");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("idusualt");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("codempco");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("codfilialco");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("codfor");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("suframa");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);

				atributo = new Atributos();				
				atributo.setId(1);
				atributo.setName("sedeMatriz");
				atributo.setForeignkeys(false);
				atributo.setPrimaryKey(false);
				atributo.setType("String");
				atributo.setTamanho(255);
				atributo.setForeignkeysTable(null);
				atributos.add(atributo);
				
				Table table = new Table();
				table.setNome("Empresa");
				table.setAtributos(atributos);
				table.setWidth(800);
				table.setHeight(150);
				List<Filial>  filiais = new ArrayList<Filial>();
				Filial filial = new Filial();
				filial.setCodemp(1);

				/** The codfilial. */
				filial.setCodfilial(1);

				/** The razemp. */
				filial.setRazfilial("Razao");

				/** The nomeemp. */
				filial.setNomefilial("NOME FILIAL");

				/** The cnpjemp. */
				filial.setMzfilial("Sim");

				/** The inscemp. */
				filial.setCnpjfilial("123456789");

				/** The emdemp. */
				filial.setInscfilial("123456");

				/** The endfilial. */
				filial.setEndfilial("ENDERECO");

				/** The numemp. */
				filial.setNumfilial(554);

				/** The complemp. */
				filial.setComplfilial("");

				/** The bairemp. */
				filial.setBairfilial("BAirro");

				/** The cepemp. */
				filial.setCepfilial("38082-243");

				/** The cidemp. */
				filial.setCidfilial("");

				/** The ufemp. */
				filial.setUffilial("");

				/** The dddemp. */
				filial.setDddfilial("34");

				/** The foneemp. */
				filial.setFonefilial("3333-3333");

				/** The faxemp. */
				filial.setFaxfilial("3333-3333");

				/** The emailemp. */
				filial.setEmailfilial("Email");

				/** The wwwemp. */
				filial.setWwwfilial("SITE");

				/** The coddistfilial. */
				filial.setCoddistfilial(1);

				/** The percpisfilial. */
				filial.setPercpisfilial(new Float(12.00));

				/** The perccofinsfilial. */
				filial.setPerccofinsfilial(new Float(12.00));

				/** The percirfilial. */
				filial.setPercirfilial(new Float(12.00));

				/** The perccsocialfilial. */
				filial.setPerccsocialfilial(new Float(12.00));

				/** The codeanemp. */
				filial.setSimplesfilial("");

				/** The percsimplesfilial. */
				filial.setPercsimplesfilial(new Float(12.00));

				/** The codmunic. */
				filial.setCodmunic("");

				/** The multialmoxemp. */
				filial.setSiglauf("MG");

				/** The codpais. */
				filial.setCodpais(55);

				/** The codempuc. */
				filial.setCodempuc(1);

				/** The codfilialuc. */
				filial.setCodfilialuc(1);

				/** The codunifcod. */
				filial.setCodunifcod(1);

				/** The inscmunfilial. */
				filial.setInscmunfilial("");

				/** The cnaefilial. */
				filial.setCnaefilial("");

				/** The percissfilial. */
				filial.setPercissfilial(new Float(12.00));

				/** The contribipifilial. */
				filial.setContribipifilial("");

				/** The fotoemp. */
				filial.setTimbrefilial(null);

				/** The codmunic. */
				filial.setPerfilfilial("");

				/** The siglauf. */
				filial.setIndativfilial("");

				/** The dtins. */
				filial.setDtins(null);

				/** The hins. */
				filial.setHins(null);

				/** The idusuins. */
				filial.setIdusuins(1);

				/** The dtalt. */
				filial.setDtalt(null);

				/** The halt. */
				filial.setHalt(null);

				/** The idusualt. */
				filial.setIdusualt("");

				/** The codempco. */
				filial.setCodempco(1);

				/** The codfilialco. */
				filial.setCodfilialco(1);

				/** The codfor. */
				filial.setCodfor(1);

				/** The suframa. */
				filial.setSuframa("");
				
				filial.setSedeMatriz(false);
				filial.setCodemp(1);
				filial.setBairfilial("Mangueiras");
				filial.setNomefilial("Nome empresa");
				filial.setRazfilial("Razao da empresa");
				filial.setCepfilial("38082-243");
				filial.setCidfilial("Uberaba");
				filial.setCnpjfilial("1234567890/123");
				filial.setCodmunic("123456");
				filial.setCodpais(55);
				filial.setComplfilial("complemp");
				filial.setCreateDate(new Date());
				filial.setCreateUser("createUser");
				filial.setDddfilial("dddemp");
				filial.setInscfilial("inscemp");				
				filiais.add(filial);
				
		
				
				
				
//==============================================================================================				
				dicionario = new Dicionario();
				dicionario.setId(8);
				dicionario.setLabel("");
				dicionario.setName("table");
				dicionario.setPositionX(new Double(110));
				dicionario.setPositionY(new Double(80));
				dicionario.setType(DicionarioTypeEnum.TABLE);
				dicionario.setBusca(Boolean.FALSE);
				dicionario.setRequerid(Boolean.FALSE);
				dicionario.setTamanho(50);
				dicionarios.add(dicionario);

				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setCreateDate(new Date());
				statusMessage.setDate(new Date());

				
				tela.setId_tela(2);
				tela.setFiliais(filiais);
				tela.setTable(new ArrayList<Table>());
				tela.getTable().add(table);
				tela.setAtributos(dicionarios);
				response.setTelas(new ArrayList<Tela>());
				response.getTelas().add(tela);
				}
				if(dicionarioRequest.getTela().getId_tela()== 3){
					List<Dicionario>  dicionarioTabs = new ArrayList<Dicionario>();

					 List<String> wordcount = new ArrayList<String>();
					 
					 wordcount.add("1|Juridica");
					 wordcount.add("2|Fisica");

					
					dicionarioTabs.add(createDicionario(1,200,250,200,"Tipo",false,true,DicionarioTypeEnum.COMBO,wordcount));
					dicionarioTabs.add(createDicionario(2,200,250,200,"codCliente",true,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(3,200,250,200,"Filial",false,true,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(4,200,250,200,"Nome-RazaoSocial",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(5,200,250,200,"Nome-Fantasia",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(6,200,250,200,"CNPJ",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(7,200,250,200,"Inscri-Est",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(8,200,250,200,"Isenta",false,false,DicionarioTypeEnum.CHECKBOX,null));
					dicionarioTabs.add(createDicionario(9,200,250,200,"codCliente",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(10,200,250,200,"Inscri-Mun",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(11,200,250,200,"Nao-Contri-ICMS",false,false,DicionarioTypeEnum.CHECKBOX,null));
					dicionarioTabs.add(createDicionario(12,200,250,200,"ICMS-Suspenso",false,false,DicionarioTypeEnum.CHECKBOX,null));
					dicionarioTabs.add(createDicionario(13,200,250,200,"ICMS-Solidario",false,false,DicionarioTypeEnum.CHECKBOX,null));
					dicionarioTabs.add(createDicionario(14,200,250,200,"Fone1",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(15,200,250,200,"Fone2",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(16,200,250,200,"Email1",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(17,200,250,200,"Email2",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(18,200,250,200,"Site",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(19,200,250,200,"Suframa",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(20,200,250,200,"Cliente-Inativo",false,false,DicionarioTypeEnum.CHECKBOX,null));
					wordcount = new ArrayList<String>();
					 
					 wordcount.add("1|Ativo");
					 wordcount.add("2|Inativo");
					 wordcount.add("2|Bloqueado");
					dicionarioTabs.add(createDicionario(21,200,250,200,"Status",false,false,DicionarioTypeEnum.COMBO,wordcount));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Cep",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Endereco",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Numero",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Complemento",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Bairro",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Pais",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"UF",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Cidade",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Ultilizar-Endereco-entrada",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Cep",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Endereco",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Numero",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Complemento",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Bairro",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Pais",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"UF",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Cidade",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Obs",false,false,DicionarioTypeEnum.INPUT,null));
					List<Abas>  abss = new ArrayList<Abas>();
					abss.add(createAbs(dicionarioTabs,"Cadastrais",1));
					dicionarioTabs = new ArrayList<Dicionario>();
	
					
					// tabs administrativo
					dicionarioTabs.add(createDicionario(22,200,250,200,"Banco",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Agencia",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Conta",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"CNAE",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Natureza",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Centro-RD",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Condicao-de-pag",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Transportadora",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Dia-Pref-Faturamento",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Obs-Faturamento",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Aliquota-IRRF",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"IVA",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Calcula-PIS",false,false,DicionarioTypeEnum.CHECKBOX,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Calcula-CONFINS",false,false,DicionarioTypeEnum.CHECKBOX,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Calcula-CSLL",false,false,DicionarioTypeEnum.CHECKBOX,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Calcula-ISS",false,false,DicionarioTypeEnum.CHECKBOX,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"RDA",false,false,DicionarioTypeEnum.CHECKBOX,null));
					
					abss.add(createAbs(dicionarioTabs,"Cadastrais",1));
					dicionarioTabs = new ArrayList<Dicionario>();

					dicionarioTabs.add(createDicionario(22,200,250,200,"Grupo",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Comissao",false,false,DicionarioTypeEnum.INPUT,null));
					wordcount = new ArrayList<String>();
					 
					 wordcount.add("1|Ativo");
					 wordcount.add("2|Inativo");
					 wordcount.add("2|Bloqueado");
					dicionarioTabs.add(createDicionario(22,200,250,200,"Limite-de-Credito",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Tipo-Frete",false,false,DicionarioTypeEnum.INPUT,wordcount));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Primeira-Compra",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Ultima-Compra",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Maior-Compra",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Qnt-Compra",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Cheque-Devolvido",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Maior-Atraso",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Ultima-Visita",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Potencial-Anual",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Seg-Negocios-1",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Seg-Negocios-2",false,false,DicionarioTypeEnum.INPUT,null));
					dicionarioTabs.add(createDicionario(22,200,250,200,"Seg-Negocios-3",false,false,DicionarioTypeEnum.INPUT,null));
				
					abss.add(createAbs(dicionarioTabs,"Venda",3));
					response.setTelas(new ArrayList<Tela>());
					response.getTelas().add(createTela(1, "Cliente",850, 850, createTabs(1, 800, 800, abss)));	
				}
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			ResultsSetInfo resultSetInfo = new ResultsSetInfo();
			resultSetInfo.setTotalRowsAvailable(20);



			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

}
