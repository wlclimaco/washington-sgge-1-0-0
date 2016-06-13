package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.talesolutions.cep.CEP;
import org.talesolutions.cep.CEPService;
import org.talesolutions.cep.CEPServiceFactory;

@Controller
@RequestMapping("/cep/api")
public class HelloWorldRestController {

	private CEPService buscaCEP;



	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public CEP fetchCep(@RequestBody String cep) {
		buscaCEP = CEPServiceFactory.getCEPService();
		return buscaCEP.obtemPorNumeroCEP(cep);
	}

}