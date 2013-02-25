package com.sensus.mlc.wui.filial.unittest.action;

import java.util.ArrayList;
import java.util.Date;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.Request;
import com.sensus.common.model.ResultsSetInfo;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.filial.bcf.IFilialBCF;
import com.sensus.mlc.filial.model.Filial;
import com.sensus.mlc.filial.model.request.FilialRequest;
import com.sensus.mlc.filial.model.request.InquiryFilialRequest;
import com.sensus.mlc.filial.model.response.FilialResponse;
import com.sensus.mlc.filial.model.response.InquiryFilialResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;


/**
 * The Class ScheduleBCFMockImpl.
 * 
 * @ahthor Washington Costa
 */
public class FilialBCFMockImpl extends BaseMockImpl implements IFilialBCF
{

	/** The Constant FILE_NAME. */
	public static final String FILE_NAME = "C:\\QATEclipseWorkSpace\\epm-wui\\WebContent\\File.csv";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant ZERO. */
	private static final int TWENTY = 20;

	/** The page size. */
	private Integer pageSize = 100;
	
	/** The Constant ONE_HUNDRED. */
	public static final Integer ONE_HUNDRED = 100;
	
	/** The Constant KABOOM. */
	public static final String KABOOM = "Kaboom";

	/**
	 * Gets the page size.
	 * 
	 * @return the page size
	 */
	public Integer getPageSize()
	{
		return pageSize;
	}

	/**
	 * Sets the page size.
	 * 
	 * @param pageSize the new page size
	 */
	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	@Override
	public FilialResponse insertFilial(FilialRequest filialRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilialResponse updateFilial(FilialRequest filialRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilialResponse deleteFilial(FilialRequest filialRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InquiryFilialResponse fetchAllFilial(
			InquiryFilialRequest inquiryfilialRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilialResponse fetchFilialById(FilialRequest filialRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InquiryFilialResponse generateFileCSV(
			InquiryFilialRequest inquiryFilialRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse insertCSVProcess(InquiryFilialRequest filialRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilialResponse fetchAllFilialTypes(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InquiryFilialResponse fetchAllFilialEmpresa(
			InquiryFilialRequest inquiryFilialRequest) {
		InquiryFilialResponse response = new InquiryFilialResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setFilial(new ArrayList<Filial>());

			pageSize = inquiryFilialRequest.getPageSize();


			Filial filial = new Filial();
			if(inquiryFilialRequest.getFilial().get(0).getCodemp() == 1){

				filial.setCodemp(1);
				filial.setBairfilial("Mangueiras");
				filial.setNomefilial("Nome empresa");
				filial.setRazfilial("Razao da empresa");
				filial.setCepfilial("38082-243");
				filial.setCidfilial("Uberaba");
				filial.setCnpjfilial("1234567890/123");
				filial.setCodfilial(1);
				filial.setCodmunic("123456");
				filial.setCodpais(55);
				filial.setComplfilial("complemp");
				filial.setCreateDate(new Date());
				filial.setCreateUser("createUser");
				filial.setDddfilial("dddemp");
				filial.setInscfilial("inscemp");				
				response.getFilial().add(filial);
				filial = new Filial();
				filial.setCodemp(1);
				filial.setBairfilial("Mangueiras");
				filial.setNomefilial("Nome empresa");
				filial.setRazfilial("Razao da empresa");
				filial.setCepfilial("38082-243");
				filial.setCidfilial("Uberaba");
				filial.setCnpjfilial("1234567890/123");
				filial.setCodfilial(2);
				filial.setCodmunic("123456");
				filial.setCodpais(55);
				filial.setComplfilial("complemp");
				filial.setCreateDate(new Date());
				filial.setCreateUser("createUser");
				filial.setDddfilial("dddemp");
				filial.setInscfilial("inscemp");				
				response.getFilial().add(filial);
				
			}
			if(inquiryFilialRequest.getFilial().get(0).getCodemp() == 2){

				filial.setCodemp(2);
				filial.setBairfilial("Mangueiras");
				filial.setNomefilial("Nome empresa");
				filial.setRazfilial("Razao da empresa");
				filial.setCepfilial("38082-243");
				filial.setCidfilial("Uberaba");
				filial.setCnpjfilial("1234567890/123");
				filial.setCodfilial(3);
				filial.setCodmunic("123456");
				filial.setCodpais(55);
				filial.setComplfilial("complemp");
				filial.setCreateDate(new Date());
				filial.setCreateUser("createUser");
				filial.setDddfilial("dddemp");
				filial.setInscfilial("inscemp");				
				response.getFilial().add(filial);
				filial = new Filial();
				filial.setCodemp(2);
				filial.setBairfilial("Mangueiras");
				filial.setNomefilial("Nome empresa");
				filial.setRazfilial("Razao da empresa");
				filial.setCepfilial("38082-243");
				filial.setCidfilial("Uberaba");
				filial.setCnpjfilial("1234567890/123");
				filial.setCodfilial(4);
				filial.setCodmunic("123456");
				filial.setCodpais(55);
				filial.setComplfilial("complemp");
				filial.setCreateDate(new Date());
				filial.setCreateUser("createUser");
				filial.setDddfilial("dddemp");
				filial.setInscfilial("inscemp");				
				response.getFilial().add(filial);
				
			}
				ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
				resultsSetInfo.setTotalRowsAvailable(ONE_HUNDRED);
				response.setResultsSetInfo(resultsSetInfo);
			
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
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
		throw new RuntimeException(KABOOM);
	}






}
