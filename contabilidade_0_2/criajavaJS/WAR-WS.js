
war_ws = function (teste,bar,local){


var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';
text = text + '\n';
text = text + '\n';

text = text + 'package com.qat.samples.sysmgmt.service;\n';
text = text + '\n';
text = text + 'import javax.jws.WebMethod;\n';
text = text + 'import javax.jws.WebParam;\n';
text = text + 'import javax.jws.WebResult;\n';
text = text + 'import javax.jws.WebService;\n';
text = text + '\n';
text = text + 'import org.apache.cxf.annotations.WSDLDocumentation;\n';
text = text + '\n';
text = text + 'import com.qat.samples.sysmgmt.model.request.'+bar+'MaintenanceRequest;\n';
text = text + 'import com.qat.samples.sysmgmt.model.response.'+bar+'Response;\n';
text = text + 'import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;\n';
text = text + 'import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;\n';
text = text + 'import com.qat.samples.sysmgmt.util.model.request.'+bar+'InquiryRequest;\n';
text = text + 'import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;\n';
text = text + '\n';
text = text + '/**\n';
text = text + ' * The Interface I'+bar+'WS.\n';
text = text + ' */\n';
text = text + '@WebService(serviceName = "'+bar+'Service", targetNamespace = "http://qat.com/sysmgmt", portName = "'+bar+'ServicePort")\n';
text = text + 'public interface I'+bar+'WS\n';
text = text + '{\n';

for(i=0;i < teste.length;i++){

	var nome = teste[i].classe.toLowerCase();
	nomeM = titleize(teste[i].classe)
	text = text + "\n";
	text = text + '//===================================### '+nomeM.toUpperCase()+' ####======================================\n';
	text = text + '\n';
	text = text + '	/**\n';
	text = text + '	 * Insert procedure.\n';
	text = text + '	 *\n';
	text = text + '	 * @param request the request\n';
	text = text + '	 *\n';
	text = text + '	 * @return the procedure response\n';
	text = text + '	 */\n';
	text = text + '	@WebMethod(action = "insert'+nomeM+'")\n';
	text = text + '	@WebResult(name = "insert'+nomeM+'Return")\n';
	text = text + '	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")\n';
	text = text + '	public '+nomeM+'Response insert'+nomeM+'(@WebParam(name = "request") '+nomeM+'MaintenanceRequest request);\n';
	text = text + '\n';
	text = text + '	/**\n';
	text = text + '	 * Update procedure.\n';
	text = text + '	 *\n';
	text = text + '	 * @param request the request\n';
	text = text + '	 *\n';
	text = text + '	 * @return the procedure response\n';
	text = text + '	 */\n';
	text = text + '	@WebMethod(action = "update'+nomeM+'")\n';
	text = text + '	@WebResult(name = "update'+nomeM+'Return")\n';
	text = text + '	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")\n';
	text = text + '	public '+nomeM+'Response update'+nomeM+'(@WebParam(name = "request") '+nomeM+'MaintenanceRequest request);\n';
	text = text + '\n';
	text = text + '	/**\n';
	text = text + '	 * Delete procedure.\n';
	text = text + '	 *\n';
	text = text + '	 * @param request the request\n';
	text = text + '	 *\n';
	text = text + '	 * @return the procedure response\n';
	text = text + '	 */\n';
	text = text + '	@WebMethod(action = "delete'+nomeM+'")\n';
	text = text + '	@WebResult(name = "delete'+nomeM+'Return")\n';
	text = text + '	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")\n';
	text = text + '	public '+nomeM+'Response delete'+nomeM+'(@WebParam(name = "request") '+nomeM+'MaintenanceRequest request);\n';
	text = text + '\n';
	text = text + '	/**\n';
	text = text + '	 * Refresh procedures.\n';
	text = text + '	 *\n';
	text = text + '	 * @param request the request\n';
	text = text + '	 *\n';
	text = text + '	 * @return the procedure response\n';
	text = text + '	 */\n';
	text = text + '	@WebMethod(action = "refresh'+nomeM+'s")\n';
	text = text + '	@WebResult(name = "refresh'+nomeM+'sReturn")\n';
	text = text + '	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")\n';
	text = text + '	public '+nomeM+'Response refresh'+nomeM+'s(@WebParam(name = "request") RefreshRequest request);\n';
	text = text + '\n';
	text = text + '	/**\n';
	text = text + '	 * Fetch all procedures.\n';
	text = text + '	 *\n';
	text = text + '	 * @param request the request\n';
	text = text + '	 *\n';
	text = text + '	 * @return the procedure response\n';
	text = text + '	 */\n';
	text = text + '	@WebMethod(action = "fetchAll'+nomeM+'s")\n';
	text = text + '	@WebResult(name = "fetchAll'+nomeM+'sReturn")\n';
	text = text + '	@WSDLDocumentation(value = "Returns a complete list of all procedures.")\n';
	text = text + '	public '+nomeM+'Response fetchAll'+nomeM+'s(@WebParam(name = "request") FetchAllRequest request);\n';
	text = text + '\n';
	text = text + '	/**\n';
	text = text + '	 * Fetch procedure by id.\n';
	text = text + '	 *\n';
	text = text + '	 * @param request the request\n';
	text = text + '	 *\n';
	text = text + '	 * @return the procedure response\n';
	text = text + '	 */\n';
	text = text + '	@WebMethod(action = "fetch'+nomeM+'ById")\n';
	text = text + '	@WebResult(name = "fetch'+nomeM+'ByIdReturn")\n';
	text = text + '	@WSDLDocumentation(value = "Returns the desired procedure.")\n';
	text = text + '	public '+nomeM+'Response fetch'+nomeM+'ById(@WebParam(name = "request") FetchByIdRequest request);\n';
	text = text + '\n';
	text = text + '	/**\n';
	text = text + '	 * Fetch procedures by request.\n';
	text = text + '	 *\n';
	text = text + '	 * @param request the request\n';
	text = text + '	 * @return the procedure response\n';
	text = text + '	 */\n';
	text = text + '	@WebMethod(action = "fetch'+nomeM+'sByRequest")\n';
	text = text + '	@WebResult(name = "fetch'+nomeM+'sByRequestReturn")\n';
	text = text + '	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")\n';
	text = text + '	public '+nomeM+'Response fetch'+nomeM+'sByRequest(@WebParam(name = "request") '+nomeM+'InquiryRequest request);\n';
	text = text + '\n';
}
text = text + '}\n';
text = text + '\n';

return text;
}
