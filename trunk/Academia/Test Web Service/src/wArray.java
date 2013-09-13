import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.log4j.Logger;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@WebService(serviceName = "wArray", portName = "wArrayPort", targetNamespace = "http://barndt.com/services/test")
public class wArray
{
	private final Logger log = Logger.getLogger(getClass());

	@WebMethod(action = "sendArray")
	@WebResult(name = "sendArrayReturn")
	public String sendArray(@WebParam(name = "arrS") String[] arrS)
	{
		log.debug("started executing sendArray");
		String strRet = "";
		strRet = "Length = " + arrS.length + " ";
		for (int i = 0; i < arrS.length; i++)
		{
			strRet += "str" + i + " = " + arrS[i] + " ";
		}
		return strRet;
	}
}