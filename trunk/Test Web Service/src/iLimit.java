import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.log4j.Logger;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@WebService(serviceName = "iLimit", portName = "iLimitPort", targetNamespace = "http://barndt.com//services/test")
public class iLimit
{
	private final Logger log = Logger.getLogger(getClass());

	@WebMethod(action = "getLimit")
	@WebResult(name = "detCreditReturn")
	public CredLmt[] detCredit(@WebParam(name = "sIn") int sIn)
	{
		log.debug("started executing detCreditLimit " + sIn + " times.");
		CredLmt[] oRet = null;
		oRet = new CredLmt[sIn + 1];
		for (int i = 0; i <= sIn; i++)
		{
			oRet[i] = new CredLmt(("Item" + i), ("T" + i), i, (i * i), ("RC" + i), ("RC0" + i), ("RC1" + i), ("RC2" + i), ("RC3" + i), ("RC4" + i), ("RC5" + i), ("RC6" + i),
					("RC7" + i), ("RC8" + i), ("RC9" + i));

		}

		return oRet;
	}
}