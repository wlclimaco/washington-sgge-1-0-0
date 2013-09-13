import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.log4j.Logger;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@WebService(serviceName = "Registry", portName = "RegistryPort", targetNamespace = "http://barndt.com/services/test")
public class Registry
{
	private final Logger log = Logger.getLogger(getClass());

	@WebMethod(action = "doReg")
	@WebResult(name = "doRegReturn")
	public String doReg(@WebParam(name = "strS") String strS)
	{
		log.debug("started executing doReg " + strS);
		String strRet = "";

		if (strS.length() > 0)
		{

			long lRand = 0;
			Random rand = new Random();
			lRand = rand.nextLong();
			if (lRand < 0)
			{
				lRand = lRand * -1;
			}
			strRet = Long.toString(lRand);

		}
		return strRet;
	}
}