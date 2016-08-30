
bacImpl = function (teste,bar,local){

	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';


text = text + "package com.qat.samples.sysmgmt.bac."+titleize(local)+";\n";
text = text + "\n";
text = text + "\n";
text = text + "import org.slf4j.Logger;\n";
text = text + "import org.slf4j.LoggerFactory;\n";
text = text + "import org.springframework.stereotype.Component;\n";
text = text + "\n";
text = text + "import com.qat.framework.model.BaseModel.PersistenceActionEnum;\n";
text = text + "import com.qat.framework.model.MessageLevel;\n";
text = text + "import com.qat.framework.model.MessageSeverity;\n";
text = text + "import com.qat.framework.model.response.InternalResponse;\n";
text = text + "import com.qat.framework.model.response.InternalResponse.SystemErrorCategory;\n";
text = text + "import com.qat.framework.model.response.InternalResultsResponse;\n";
text = text + "import com.qat.framework.validation.ValidationContext;\n";
text = text + "import com.qat.framework.validation.ValidationContextIndicator;\n";
text = text + "import com.qat.framework.validation.ValidationController;\n";
text = text + "import com.qat.framework.validation.ValidationUtil;\n";
text = text + "import com.qat.samples.sysmgmt.bac.I"+titleize(bar)+"BAC;\n";
text = text + "import com.qat.samples.sysmgmt.bar.IComprasBAR;\n";
text = text + "import com.qat.samples.sysmgmt.model."+titleize(bar)+";\n";
text = text + "import com.qat.samples.sysmgmt.model.request."+titleize(bar)+"MaintenanceRequest;\n";
text = text + "import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;\n";
text = text + "import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;\n";
text = text + "import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;\n";
text = text + "\n";
text = text + "/**\n";
text = text + " * Standards based implementation of a BAC for "+titleize(bar)+" leveraging the injected I"+bar+"BAR.\n";
text = text + " */\n";
text = text + "@Component\n";
text = text + "public class "+bar+"BACImpl implements I"+bar+"BAC\n";
text = text + "{\n";
text = text + "\n";
text = text + "	/** The Constant MINIMUM_ENTRIES. */\n";
text = text + "	private static final int MINIMUM_ENTRIES = 5;\n";
text = text + "\n";
text = text + "	/** The Constant DEFAULT_"+bar.toUpperCase()+"_BAC_EXCEPTION_MSG. */\n";
text = text + '	private static final String DEFAULT_'+bar.toUpperCase()+'_BAC_EXCEPTION_MSG = "sysmgmt.base.'+bar+'bacimpl.defaultexception";\n';
text = text + "\n";
text = text + '	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";\n';
text = text + "\n";
text = text + "	/** The Constant LOG. */\n";
text = text + "	private static final Logger LOG = LoggerFactory.getLogger("+titleize(bar)+"BACImpl.class);\n";
text = text + "\n";
text = text + "	/** The "+bar+" BAR. */\n";
text = text + "	private I"+titleize(bar)+"BAR "+bar.toLowerCase()+"BAR; // injected by Spring through setter\n";
text = text + "\n";
text = text + "	private ValidationController validationController; // injected by Spring through setter\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "	 * Spring Sets the "+bar+" BAR.\n";
text = text + "	 *\n";
text = text + "	 * @param "+bar+"BAR the new "+bar+" BAR\n";
text = text + "	 */\n";
text = text + "	public void set"+titleize(bar)+"BAR(I"+titleize(bar)+"BAR "+bar.toLowerCase()+"BAR)\n";
text = text + "	{\n";
text = text + "		this."+bar.toLowerCase()+"BAR = "+bar.toLowerCase()+"BAR;\n";
text = text + "	}\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "	 * Gets the "+bar+" BAR.\n";
text = text + "	 *\n";
text = text + "	 * @return the "+bar+" BAR\n";
text = text + "	 */\n";
text = text + "	public I"+titleize(bar)+"BAR get"+titleize(bar)+"BAR()\n";
text = text + "	{\n";
text = text + "		return "+bar.toLowerCase()+"BAR;\n";
text = text + "	}\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "	 * Gets the validation controller\n";
text = text + "	 *\n";
text = text + "	 * @return ValidationController\n";
text = text + "	 */\n";
text = text + "	public ValidationController getValidationController()\n";
text = text + "	{\n";
text = text + "		return validationController;\n";
text = text + "	}\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "	 * Sets the validation Controller\n";
text = text + "	 *\n";
text = text + "	 * @param validationController\n";
text = text + "	 */\n";
text = text + "	public void setValidationController(ValidationController validationController)\n";
text = text + "	{\n";
text = text + "		this.validationController = validationController;\n";
text = text + "	}\n";


for(i=0;i < teste.length;i++){

var nome = teste[i].classe.toLowerCase();
nomeM = titleize(nome)
text = text + "\n";
text = text + '//===================================### '+nomeM.toUpperCase()+' ####======================================\n';
text = text + "	/**\n";
text = text + "/*\n";
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see\n";
text = text + " * com.qat.samples.sysmgmt.bac.ICountyBAC#insert"+nomeM+"(com.qat.samples.sysmgmt.model.request."+nomeM+"MaintenanceRequest\n";
text = text + " * )\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResultsResponse<"+nomeM+"> insert"+nomeM+"("+nomeM+"MaintenanceRequest request)\n";
text = text + "{\n";
text = text + "	InternalResultsResponse<"+nomeM+"> response =\n";
text = text + "			process"+nomeM+"(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);\n";
text = text + "	return response;\n";
text = text + "}\n";
text = text + "\n";
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see\n";
text = text + " * com.qat.samples.sysmgmt.bac.I"+nomeM+"BAC#update"+nomeM+"(com.qat.samples.sysmgmt.model.request."+nomeM+"MaintenanceRequest\n";
text = text + " * )\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResultsResponse<"+nomeM+"> update"+nomeM+"("+nomeM+"MaintenanceRequest request)\n";
text = text + "{\n";
text = text + "	InternalResultsResponse<"+nomeM+"> response =\n";
text = text + "			process"+nomeM+"(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);\n";
text = text + "	return response;\n";
text = text + "}\n";
text = text + "\n";
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see\n";
text = text + " * com.qat.samples.sysmgmt.bac.I"+nomeM+"BAC#delete"+nomeM+"(com.qat.samples.sysmgmt.model.request."+nomeM+"MaintenanceRequest\n";
text = text + " * )\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResultsResponse<"+nomeM+"> delete"+nomeM+"("+nomeM+"MaintenanceRequest request)\n";
text = text + "{\n";
text = text + "	InternalResultsResponse<"+nomeM+"> response =\n";
text = text + "			process"+nomeM+"(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);\n";
text = text + "	return response;\n";
text = text + "}\n";
text = text + "\n";
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see com.qat.samples.sysmgmt.bac.I"+nomeM+"BAC#refresh"+nomeM+"s(com.qat.samples.sysmgmt.model.request.RefreshRequest)\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResultsResponse<"+nomeM+"> refresh"+nomeM+"s(RefreshRequest request)\n";
text = text + "{\n";
text = text + "	// This method is demo code only. Do not view this as a QAT Global Standard.\n";
text = text + "	get"+titleize(bar)+"BAR().deleteAll"+nomeM+"s();\n";
text = text + "	int refreshNumber = request.getRefreshInt();\n";
text = text + "	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;\n";
text = text + "\n";
text = text + "	for (int i = 1; i <= refreshNumber; i++)\n";
text = text + "	{\n";
text = text + '	get'+titleize(bar)+'BAR().insert'+nomeM+'(new '+nomeM+'(i, "'+nomeM+'Desc" + i));\n';
text = text + "	}\n";
text = text + "\n";
text = text + "	// Call maintain to see if we need to return the "+nome+" list and if so whether it should be paged or not\n";
text = text + "	return maintainReturnList"+nomeM+"(request.getReturnList(), request.getReturnListPaged(),new "+nomeM+"());\n";
text = text + "}\n";
text = text + "\n";
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see com.qat.samples.sysmgmt.bac.I"+nomeM+"BAC#fetchAll"+nomeM+"s("+nomeM+" "+nome+")\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResultsResponse<"+nomeM+"> fetchAll"+nomeM+"s("+nomeM+" "+nome+")\n";
text = text + "{\n";
text = text + "	InternalResultsResponse<"+nomeM+"> response = new InternalResultsResponse<"+nomeM+">();\n";
text = text + "	response.getResultsList().addAll(get"+titleize(bar)+"BAR().fetchAll"+nomeM+"s("+nome+").getResultsList());\n";
text = text + "	return response;\n";
text = text + "}\n";
text = text + "\n";
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see\n";
text = text + " * com.qat.samples.sysmgmt.bac.I"+nomeM+"BAC#fetch"+nomeM+"ById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest\n";
text = text + " * )\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResultsResponse<"+nomeM+"> fetch"+nomeM+"ById(FetchByIdRequest request)\n";
text = text + "{\n";
text = text + "	InternalResultsResponse<"+nomeM+"> response = new InternalResultsResponse<"+nomeM+">();\n";
text = text + "	// validate fetchId field\n";
text = text + "	if (ValidationUtil.isNull(request.getFetchId()))\n";
text = text + "	{\n";
text = text + "		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);\n";
text = text + "		response.setStatus(SystemErrorCategory.SystemValidation);\n";
text = text + "	}\n";
text = text + "	else\n";
text = text + "	{\n";
text = text + "		response.getResultsList().add(get"+titleize(bar)+"BAR().fetch"+nomeM+"ById(request));\n";
text = text + "	}\n";
text = text + "\n";
text = text + "	return response;\n";
text = text + "}\n";
text = text + "\n";
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see com.qat.samples.sysmgmt.bac.I"+nomeM+"BAC#fetch"+nomeM+"sByRequest(com.qat.samples.sysmgmt.model.request.\n";
text = text + " * PagedInquiryRequest)\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResultsResponse<"+nomeM+"> fetch"+nomeM+"sByRequest("+nomeM+"InquiryRequest request)\n";
text = text + "{\n";
text = text + "	return get"+titleize(bar)+"BAR().fetch"+nomeM+"sByRequest(request);\n";
text = text + "}\n";
text = text + "\n";
text = text + "/**\n";
text = text + " * Process.\n";
text = text + " *\n";
text = text + " * @param indicator the indicator\n";
text = text + " * @param persistType the persist type\n";
text = text + " * @param request the request\n";
text = text + " * @return the "+nome+" response\n";
text = text + " */\n";
text = text + "private InternalResultsResponse<"+nomeM+"> process"+nomeM+"(ValidationContextIndicator indicator,\n";
text = text + "		PersistenceActionEnum persistType,\n";
text = text + "		"+nomeM+"MaintenanceRequest request)\n";
text = text + "		{\n";
text = text + "	InternalResultsResponse<"+nomeM+"> response = null;\n";
text = text + "\n";
text = text + "	// Validate\n";
text = text + "	//ValidationContext context = new ValidationContext("+nomeM+".class.getSimpleName(), request.get"+nomeM+"(), indicator);\n";
text = text + "	//if (!getValidationController().validate(context))\n";
text = text + "	//{\n";
text = text + "	//	response = new InternalResultsResponse<"+nomeM+">();\n";
text = text + "	//	response.setStatus(SystemErrorCategory.SystemValidation);\n";
text = text + "	//	response.addMessages(context.getMessages());\n";
text = text + "	//	return response;\n";
text = text + "	//}\n";
text = text + "\n";
text = text + "		// Persist\n";
text = text + "		InternalResponse internalResponse = doPersistence"+nomeM+"(request.get"+nomeM+"(), persistType);\n";
text = text + "		if (internalResponse.isInError())\n";
text = text + "		{\n";
text = text + "			response = new InternalResultsResponse<"+nomeM+">();\n";
text = text + "			response.setStatus(internalResponse.getError());\n";
text = text + "			response.addMessages(internalResponse.getMessageInfoList());\n";
text = text + "			response.addMessage(DEFAULT_"+bar.toUpperCase()+"_BAC_EXCEPTION_MSG, MessageSeverity.Error,\n";
text = text + "					MessageLevel.Object, new Object[] {internalResponse.errorToString()});\n";
text = text + "\n";
text = text + "			return response;\n";
text = text + "		}\n";
text = text + "\n";
text = text + "		// Call maintainReurnList to see if we need to return the "+nome+" list and if so whether it should be paged or\n";
text = text + "		// not\n";
text = text + "		response = maintainReturnList"+nomeM+"(request.getReturnList(), request.getReturnListPaged(),new "+nomeM+"());\n";
text = text + "\n";
text = text + "		return response;\n";
text = text + "			}\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "	 * Do persistence"+nomeM+".\n";
text = text + "	 *\n";
text = text + "	 * @param request the request\n";
text = text + "	 * @param updateType the update type\n";
text = text + "	 * @return the internal response\n";
text = text + "	 */\n";
text = text + "	private InternalResponse doPersistence"+nomeM+"("+nomeM+" "+nome+", PersistenceActionEnum updateType)\n";
text = text + "	{\n";
text = text + "		switch (updateType)\n";
text = text + "		{\n";
text = text + "			case INSERT:\n";
text = text + "				return get"+titleize(bar)+"BAR().insert"+nomeM+"("+nome+");\n";
text = text + "\n";
text = text + "			case UPDATE:\n";
text = text + "				return get"+titleize(bar)+"BAR().update"+nomeM+"("+nome+");\n";
text = text + "\n";
text = text + "			case DELETE:\n";
text = text + "				return get"+titleize(bar)+"BAR().delete"+nomeM+"ById("+nome+");\n";
text = text + "			default:\n";
text = text + "				if (LOG.isDebugEnabled())\n";
text = text + "				{\n";
text = text + '					LOG.debug("updateType missing!");\n';
text = text + "				}\n";
text = text + "				break;\n";
text = text + "		}\n";
text = text + "\n";
text = text + "		return null;\n";
text = text + "	}\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "	 * Maintain return list.\n";
text = text + "	 *\n";
text = text + "	 * @param request the request\n";
text = text + "	 * @param response the response\n";
text = text + "	 */\n";
text = text + "	private InternalResultsResponse<"+nomeM+"> maintainReturnList"+nomeM+"(Boolean listIndicator, Boolean pageListIndicator,"+nomeM+" "+nomeM.toLowerCase()+")\n";
text = text + "	{\n";
text = text + "		// Fetch again if requested.\n";
text = text + "		if (listIndicator)\n";
text = text + "		{\n";
text = text + "			// Fetch Paged is requested.\n";
text = text + "			if (pageListIndicator)\n";
text = text + "			{\n";
text = text + "				"+nomeM+"InquiryRequest request = new "+nomeM+"InquiryRequest();\n";
text = text + "				request.setPreQueryCount(true);\n";
text = text + "				return fetch"+nomeM+"sByRequest(request);\n";
text = text + "			}\n";
text = text + "			else\n";
text = text + "			{\n";
text = text + "				// otherwise return all rows not paged\n";
text = text + "				return fetchAll"+nomeM+"s("+nomeM.toLowerCase()+");\n";
text = text + "			}\n";
text = text + "		}\n";
text = text + "		else\n";
text = text + "		{\n";
text = text + "			return new InternalResultsResponse<"+nomeM+">();\n";
text = text + "		}\n";
text = text + "	}\n";
}
text = text + "}\n";
return text;
}
