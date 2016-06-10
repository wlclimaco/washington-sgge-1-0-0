package cep;

import org.talesolutions.cep.CEPService;

/**
 * Fábrica de CEPService
 *
 */
public class CEPServiceFactory {

	/**
	 * @return uma instância thread safe de CEPService
	 */
	public static CEPService getCEPService() {
		return new BuscaCEP();
	}

}
