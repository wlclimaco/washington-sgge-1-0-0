
function titleize(text) {

    // Convertendo primeira letra em maiuscula.
    text = text.charAt(0).toUpperCase() + text.slice(1);

    for (var i = 0; i < text.length; i++) {
        if (text.charAt(i) ===" ") {

            // Convertendo letra ap�s o ESPA�O em maiuscula
            var charToUper = text.charAt(i+1).toUpperCase();

            // Colocando texto de antes do ESPA�O na vari�vel
            var sliceBegin = text.slice(0, (i+1));

            // colocando o texto de depois do ESPA�O na vari�vel
            var sliceEnd = text.slice(i + 2);

            // Juntando tudo
            text = sliceBegin + charToUper + sliceEnd;

        } else {

            // NAO CONSIGO PENSAR EM COMO TRANSFORMAR O RESTANTE DAS LETRAS EM MINUSCULA
        }
    }
    return text;
}

jms_WS = function (teste,bar,local){

var text = "";

var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';
text = text + 'package com.qat.samples.sysmgmt.service;\n';
text = text + '\n';
text = text + 'import javax.jws.WebMethod;\n';
text = text + 'import javax.jws.WebParam;\n';
text = text + 'import javax.jws.WebResult;\n';
text = text + 'import javax.jws.WebService;\n';
text = text + '\n';
text = text + 'import org.apache.cxf.annotations.WSDLDocumentation;\n';
text = text + '\n';
text = text + 'import com.qat.samples.sysmgmt.model.response.'+bar+'Response;\n';
text = text + 'import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;\n';
text = text + 'import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;\n';
text = text + '\n';
text = text + '/**\n';
text = text + ' * The Interface I'+bar+'BAS delegate used by a JMS listener. (Business Area Service - BAS)\n';
text = text + ' */\n';
text = text + '@WebService(serviceName = "'+bar+'Service", targetNamespace = "http://qat.com/jms", portName = "'+bar+'ServicePort")\n';
text = text + 'public interface I'+bar+'WS\n';
text = text + '{\n';
for(i=0;i < teste.length;i++){
var nome = teste[i].classe.toLowerCase();
nomeM = titleize(teste[i].classe)
text = text + "\n";
text = text + '//===================================### '+nomeM.toUpperCase()+' ####======================================\n';
text = text + '\n';
text = text + '/**\n';
text = text + '	 * Rebuild a list of '+nomeM+'s.\n';
text = text + '	 *\n';
text = text + '	 * @param request '+nomeM+'Request object containing parameter for building the list of '+nomeM+' objects.\n';
text = text + '	 *\n';
text = text + '	 * @return the '+nomeM+'Response containing the list of '+nomeM+'s built\n';
text = text + '	 */\n';
text = text + '	@WebMethod(action = "insert'+nomeM+'s")\n';
text = text + '	@WebResult(name = "insert'+nomeM+'sReturn")\n';
text = text + '	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")\n';
text = text + '	'+nomeM+'Response insert'+nomeM+'(@WebParam(name = "request") '+nomeM+'MaintenanceRequest request);\n';
text = text + '	\n';
text = text + '	/**\n';
text = text + '	 * Rebuild a list of '+nomeM+'s.\n';
text = text + '	 *\n';
text = text + '	 * @param request '+nomeM+'Request object containing parameter for building the list of '+nomeM+' objects.\n';
text = text + '	 *\n';
text = text + '	 * @return the '+nomeM+'Response containing the list of '+nomeM+'s built\n';
text = text + '	 */\n';
text = text + '	@WebMethod(action = "update'+nomeM+'s")\n';
text = text + '	@WebResult(name = "update'+nomeM+'sReturn")\n';
text = text + '	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")\n';
text = text + '	'+nomeM+'Response update'+nomeM+'(@WebParam(name = "request") '+nomeM+'MaintenanceRequest request);\n';
text = text + '	\n';
text = text + '	/**\n';
text = text + '	 * Rebuild a list of '+nomeM+'s.\n';
text = text + '	 *\n';
text = text + '	 * @param request '+nomeM+'Request object containing parameter for building the list of '+nomeM+' objects.\n';
text = text + '	 *\n';
text = text + '	 * @return the '+nomeM+'Response containing the list of '+nomeM+'s built\n';
text = text + '	 */\n';
text = text + '	@WebMethod(action = "delete'+nomeM+'s")\n';
text = text + '	@WebResult(name = "delete'+nomeM+'sReturn")\n';
text = text + '	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")\n';
text = text + '	'+nomeM+'Response delete'+nomeM+'(@WebParam(name = "request") '+nomeM+'MaintenanceRequest request);\n';
text = text + '	\n';
text = text + '	/**\n';
text = text + '	 * Rebuild a list of '+nomeM+'s.\n';
text = text + '	 *\n';
text = text + '	 * @param request '+nomeM+'Request object containing parameter for building the list of '+nomeM+' objects.\n';
text = text + '	 *\n';
text = text + '	 * @return the '+nomeM+'Response containing the list of '+nomeM+'s built\n';
text = text + '	 */\n';
text = text + '	@WebMethod(action = "fetch'+nomeM+'ById")\n';
text = text + '	@WebResult(name = "fetch'+nomeM+'ByIdReturn")\n';
text = text + '	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")\n';
text = text + '	'+nomeM+'Response fetch'+nomeM+'ById(@WebParam(name = "request") FetchByIdRequest request);\n';
text = text + '	\n';
text = text + '	/**\n';
text = text + '	 * Rebuild a list of '+nomeM+'s.\n';
text = text + '	 *\n';
text = text + '	 * @param request '+nomeM+'Request object containing parameter for building the list of '+nomeM+' objects.\n';
text = text + '	 *\n';
text = text + '	 * @return the '+nomeM+'Response containing the list of '+nomeM+'s built\n';
text = text + '	 */\n';
text = text + '	@WebMethod(action = "fetch'+nomeM+'sByRequest")\n';
text = text + '	@WebResult(name = "fetch'+nomeM+'sByRequestReturn")\n';
text = text + '	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")\n';
text = text + '	'+nomeM+'Response fetch'+nomeM+'sByRequest(@WebParam(name = "request") '+nomeM+'InquiryRequest request);\n';
text = text + '	\n';
text = text + '	\n';
text = text + '	/**\n';
text = text + '	 * Rebuild a list of '+nomeM+'s.\n';
text = text + '	 *\n';
text = text + '	 * @param request '+nomeM+'Request object containing parameter for building the list of '+nomeM+' objects.\n';
text = text + '	 *\n';
text = text + '	 * @return the '+nomeM+'Response containing the list of '+nomeM+'s built\n';
text = text + '	 */\n';
text = text + '	@WebMethod(action = "refresh'+nomeM+'s")\n';
text = text + '	@WebResult(name = "refresh'+nomeM+'sReturn")\n';
text = text + '	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")\n';
text = text + '	'+nomeM+'Response refresh'+nomeM+'s(@WebParam(name = "request") RefreshRequest request);\n';
text = text + '	\n';
text = text + '\n';
text = text + '	/**\n';
text = text + '	 * Fetch all '+nomeM+'s.\n';
text = text + '	 *\n';
text = text + '	 * @param request the request\n';
text = text + '	 *\n';
text = text + '	 * @return the '+nomeM+'Response containing all '+nomeM+' objects\n';
text = text + '	 */\n';
text = text + '	@WebMethod(action = "fetchAll'+nomeM+'s")\n';
text = text + '	@WebResult(name = "fetchAll'+nomeM+'sReturn")\n';
text = text + '	@WSDLDocumentation(value = "Returns a complete list of all counties.")\n';
text = text + '	'+nomeM+'Response fetchAll'+nomeM+'s(@WebParam(name = "request") FetchAllRequest request);\n';
text = text + '\n';
text = text + "\n";
}
text = text + "}\n";
return text;
}
