jms_WsImpl = function (teste,bar,local){

	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';
text = text + 'package com.qat.samples.sysmgmt.service.impl;\n';

text = text + 'import javax.jws.WebService;\n';

text = text + 'import org.slf4j.Logger;\n';
text = text + 'import org.slf4j.LoggerFactory;\n';
text = text + 'import org.springframework.stereotype.Service;\n';

text = text + 'import com.qat.framework.model.response.InternalResultsResponse;\n';
text = text + 'import com.qat.framework.util.ResponseHandler;\n';
text = text + 'import com.qat.samples.sysmgmt.bac.I'+titleize(bar)+'BAC;\n';
text = text + 'import com.qat.samples.sysmgmt.model.'+titleize(bar)+';\n';
text = text + 'import com.qat.samples.sysmgmt.model.response.'+titleize(bar)+'Response;\n';
text = text + 'import com.qat.samples.sysmgmt.service.I'+titleize(bar)+'WS;\n';
text = text + 'import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;\n';
text = text + 'import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;\n';

text = text + '/**\n';
text = text + ' * '+titleize(bar)+'WS used to provide WS interface. Delegates all calls to the I'+titleize(bar)+'BAC.\n';
text = text + ' *\n';
text = text + ' */\n';
text = text + '@Service\n';
text = text + '@WebService(targetNamespace = "http://qat.com/jms")\n';
text = text + 'public class '+titleize(bar)+'WSImpl implements I'+titleize(bar)+'WS\n';
text = text + '{\n';
text = text + '	/** The Constant DEFAULT_EXCEPTION_MSG. */\n';
text = text + '	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.'+bar.toLowerCase()+'jmswsimpl.defaultexception";\n';

text = text + '	/** The Constant DEFAULT_ERROR_MSG. */\n';
text = text + '	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.'+bar.toLowerCase()+'jmswsimpl.defaulterror";\n';

text = text + '	/** The Constant CLASS_NAME. */\n';
text = text + '	private static final String CLASS_NAME = '+titleize(bar)+'WSImpl.class.getName();\n';

text = text + '	/** The Constant LOG. */\n';
text = text + '	private static final Logger LOG = LoggerFactory.getLogger('+titleize(bar)+'WSImpl.class);\n';

text = text + '	private I'+titleize(bar)+'BAC '+bar.toLowerCase()+'BAC;\n';

text = text + '	/**\n';
text = text + '	 * @return the '+bar.toLowerCase()+'BAC which is expected to provide the implementation.\n';
text = text + '	 */\n';
text = text + '	public I'+titleize(bar)+'BAC get'+titleize(bar)+'BAC()\n';
text = text + '	{	\n';
text = text + '		return '+bar.toLowerCase()+'BAC;\n';
text = text + '	}\n';
text = text + '	/**\n';
text = text + '	 * Spring injection uses this method to inject an implementation of {@link I'+bar+'BAC}.\n';
text = text + '	 *\n';
text = text + '	 * @param '+bar.toLowerCase()+'BAC the '+bar.toLowerCase()+'BAC to set.\n';
text = text + '	 */\n';
text = text + '	public void set'+bar+'BAC(I'+bar+'BAC '+bar.toLowerCase()+'BAC)\n';
text = text + '	{\n';
text = text + '		this.'+bar.toLowerCase()+'BAC = '+bar.toLowerCase()+'BAC;\n';
text = text + '	}\n';
text = text + '	\n';

for(i=0;i < teste.length;i++){
var nome = teste[i].classe.toLowerCase();
nomeM = titleize(teste[i].classe)
text = text + "\n";
text = text + '//===================================### '+nomeM.toUpperCase()+' ####======================================\n';
text = text + '\n';


text = text + '	/**\n';
text = text + '	 * Delegates call to {@link I'+nomeM+'BAC}\n';
text = text + '	 *\n';
text = text + '	 * @param request a '+nomeM+'Request\n';
text = text + '	 * @return '+nomeM+'Response\n';
text = text + '	 */\n';
text = text + '	@Override\n';
text = text + '	public '+nomeM+'Response insert'+nomeM+'('+nomeM+'MaintenanceRequest request)\n';
text = text + '	{\n';
text = text + '		// This method is demo code only. Do not view this as a QAT Global Standard.\n';
text = text + '		'+nomeM+'Response response = new '+nomeM+'Response();\n';

text = text + '		try\n';
text = text + '		{\n';
text = text + '			InternalResultsResponse<'+nomeM+'> internalResponse = get'+bar+'BAC().insert'+nomeM+'(request);\n';
text = text + '			ResponseHandler\n';
text = text + '					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());\n';
text = text + '		}\n';
text = text + '		catch (Exception ex)\n';
text = text + '		{\n';
text = text + '			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});\n';
text = text + '		}\n';

text = text + '		return response;\n';
text = text + '	}\n';
text = text + '	\n';
text = text + '	/**\n';
text = text + '	 * Delegates call to {@link I'+nomeM+'BAC}\n';
text = text + '	 *\n';
text = text + '	 * @param request a '+nomeM+'Request\n';
text = text + '	 * @return '+nomeM+'Response\n';
text = text + '	 */\n';
text = text + '	@Override\n';
text = text + '	public '+nomeM+'Response update'+nomeM+'('+nomeM+'MaintenanceRequest request)\n';
text = text + '	{\n';
text = text + '		// This method is demo code only. Do not view this as a QAT Global Standard.\n';
text = text + '		'+nomeM+'Response response = new '+nomeM+'Response();\n';

text = text + '		try\n';
text = text + '		{\n';
text = text + '			InternalResultsResponse<'+nomeM+'> internalResponse = get'+bar+'BAC().update'+nomeM+'(request);\n';
text = text + '			ResponseHandler\n';
text = text + '					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());\n';
text = text + '		}\n';
text = text + '		catch (Exception ex)\n';
text = text + '		{\n';
text = text + '			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});\n';
text = text + '		}\n';

text = text + '		return response;\n';
text = text + '	}\n';
text = text + '	\n';
text = text + '	/**\n';
text = text + '	 * Delegates call to {@link I'+nomeM+'BAC}\n';
text = text + '	 *\n';
text = text + '	 * @param request a '+nomeM+'Request\n';
text = text + '	 * @return '+nomeM+'Response\n';
text = text + '	 */\n';
text = text + '	@Override\n';
text = text + '	public '+nomeM+'Response delete'+nomeM+'('+nomeM+'MaintenanceRequest request)\n';
text = text + '	{\n';
text = text + '		// This method is demo code only. Do not view this as a QAT Global Standard.\n';
text = text + '		'+nomeM+'Response response = new '+nomeM+'Response();\n';

text = text + '		try\n';
text = text + '		{\n';
text = text + '			InternalResultsResponse<'+nomeM+'> internalResponse = get'+bar+'BAC().delete'+nomeM+'(request);\n';
text = text + '			ResponseHandler\n';
text = text + '					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());\n';
text = text + '		}\n';
text = text + '		catch (Exception ex)\n';
text = text + '		{\n';
text = text + '			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});\n';
text = text + '		}\n';

text = text + '		return response;\n';
text = text + '	}\n';
text = text + '	\n';
text = text + '	/**\n';
text = text + '	 * Delegates call to {@link I'+nomeM+'BAC}\n';
text = text + '	 *\n';
text = text + '	 * @param request a '+nomeM+'Request\n';
text = text + '	 * @return '+nomeM+'Response\n';
text = text + '	 */\n';
text = text + '	@Override\n';
text = text + '	public '+nomeM+'Response fetch'+nomeM+'ById(FetchByIdRequest request)\n';
text = text + '	{\n';
text = text + '		// This method is demo code only. Do not view this as a QAT Global Standard.\n';
text = text + '		'+nomeM+'Response response = new '+nomeM+'Response();\n';

text = text + '		try\n';
text = text + '		{\n';
text = text + '			InternalResultsResponse<'+nomeM+'> internalResponse = get'+bar+'BAC().fetch'+nomeM+'ById(request);\n';
text = text + '			ResponseHandler\n';
text = text + '					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());\n';
text = text + '		}\n';
text = text + '		catch (Exception ex)\n';
text = text + '		{\n';
text = text + '			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});\n';
text = text + '		}\n';

text = text + '		return response;\n';
text = text + '	}\n';
text = text + '	\n';
text = text + '	/**\n';
text = text + '	 * Delegates call to {@link I'+nomeM+'BAC}\n';
text = text + '	 *\n';
text = text + '	 * @param request a '+nomeM+'Request\n';
text = text + '	 * @return '+nomeM+'Response\n';
text = text + '	 */\n';
text = text + '	@Override\n';
text = text + '	public '+nomeM+'Response fetch'+nomeM+'sByRequest('+nomeM+'InquiryRequest request)\n';
text = text + '	{\n';
text = text + '		// This method is demo code only. Do not view this as a QAT Global Standard.\n';
text = text + '		'+nomeM+'Response response = new '+nomeM+'Response();\n';

text = text + '		try\n';
text = text + '		{\n';
text = text + '			InternalResultsResponse<'+nomeM+'> internalResponse = get'+bar+'BAC().fetch'+nomeM+'sByRequest(request);\n';
text = text + '			ResponseHandler\n';
text = text + '					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());\n';
text = text + '		}\n';
text = text + '		catch (Exception ex)\n';
text = text + '		{\n';
text = text + '			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});\n';
text = text + '		}\n';

text = text + '		return response;\n';
text = text + '	}\n';

text = text + '	/**\n';
text = text + '	 * Delegates call to {@link I'+nomeM+'BAC}\n';
text = text + '	 *\n';
text = text + '	 * @param request a '+nomeM+'Request\n';
text = text + '	 * @return '+nomeM+'Response\n';
text = text + '	 */\n';
text = text + '	@Override\n';
text = text + '	public '+nomeM+'Response refresh'+nomeM+'s(RefreshRequest request)\n';
text = text + '	{\n';
text = text + '		// This method is demo code only. Do not view this as a QAT Global Standard.\n';
text = text + '		'+nomeM+'Response response = new '+nomeM+'Response();\n';

text = text + '		try\n';
text = text + '		{\n';
text = text + '			InternalResultsResponse<'+nomeM+'> internalResponse = get'+bar+'BAC().refresh'+nomeM+'s(request);\n';
text = text + '			ResponseHandler\n';
text = text + '					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());\n';
text = text + '		}\n';
text = text + '		catch (Exception ex)\n';
text = text + '		{\n';
text = text + '			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});\n';
text = text + '		}\n';

text = text + '		return response;\n';
text = text + '	}\n';

text = text + '	/**\n';
text = text + '	 * Delegates call to {@link I'+nomeM+'BAC}\n';
text = text + '	 *\n';
text = text + '	 * @param request a '+nomeM+'Request\n';
text = text + '	 * @return '+nomeM+'Response\n';
text = text + '	 */\n';
text = text + '	@Override\n';
text = text + '	public '+nomeM+'Response fetchAll'+nomeM+'s(FetchAllRequest request)\n';
text = text + '	{\n';
text = text + '		'+nomeM+'Response response = new '+nomeM+'Response();\n';

text = text + '		try\n';
text = text + '		{\n';
text = text + '			InternalResultsResponse<'+nomeM+'> internalResponse = get'+bar+'BAC().fetchAll'+nomeM+'s(new '+nomeM+'());\n';
text = text + '			ResponseHandler\n';
text = text + '					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());\n';
text = text + '		}\n';
text = text + '		catch (Exception ex)\n';
text = text + '		{\n';
text = text + '			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});\n';
text = text + '		}\n';

text = text + '		return response;\n';
text = text + '	}\n';
}
text = text + '}\n';

return text;
}
