Memo1
package com.sensus.mlc.CNAE.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.CNAE.model.Cnae;


public class CnaeResponse extends Response

    
    private Integer parentRetry;
    
    private List<Cnae> cnaes ;
}
package com.sensus.mlc.wui.CNAE;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.CNAE.bcf.ICnaeBCF;
import com.sensus.mlc.CNAE.model.request.CnaeRequest;
import com.sensus.mlc.CNAE.model.request.InquiryCnaeRequest;
import com.sensus.mlc.CNAE.model.response.CnaeResponse;
import com.sensus.mlc.CNAE.model.response.InquiryCnaeResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/CNAE") 
public class CnaeAPIController extends BaseController  
{ 

/* 
* URLs Mapping   
*/  
/** The Constant MAP_FETCH. */ 
private static final String MAP_FETCH = "/fetch";

private static final String MAP_FETCHALL = "/fetchall";

/** The Constant MAP_DELETE. */ 
private static final String MAP_DELETE = "/delete";

/** The Constant MAP_INSERT. */  
private static final String MAP_INSERT = "/insert";

/** The Constant MAP_INSERT. */  
private static final String MAP_UPDATE = "/update";


private ICnaeBCF CNAEBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "CnaeAPIController";



public ICnaeBCF getCnaeBCF() { 
return CNAEBCF;
}  

public void setCnaeBCF(ICnaeBCF CNAEBCF) {  
this.CNAEBCF = CNAEBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody CnaeRequest CNAERequest, HttpServletRequest request) 
{ 

CnaeResponse response = new CnaeResponse();
try  
{ 
setUserContext(CNAERequest, request);

response = getCnaeBCF().insertCnae(CNAERequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update CNAE.  
*    
* @param CNAERequest the CNAE request  
* @return the CNAE response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updateCnae(@RequestBody CnaeRequest CNAERequest, HttpServletRequest request) 
{ 

CnaeResponse response = new CnaeResponse();
try  
{ 
setUserContext(CNAERequest, request);

response = getCnaeBCF().updateCnae(CNAERequest);

} 
catch (Exception e) 
{  
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

}  


/**  
* Delete filial. 
*      
* @param filialRequest the filial request 
* @return the filial response    
*/      

@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)  
@ResponseBody  
public Response deleteCnae(@RequestBody CnaeRequest CNAERequest, HttpServletRequest request) 
{  

CnaeResponse response = new CnaeResponse();
try 
{ 
setUserContext(CNAERequest, request);

response = getCnaeBCF().deleteCnae(CNAERequest);

} 
catch (Exception e) 
{ 
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
			new String[] {CONTROLLER_EXCEPTION_MSG});
}
return response;

}  

/** 
* Fetch all filial.  
*  
* @param inquiryfilialRequest the inquiryfilial request  
* @return the inquiry filial response  
*/ 

@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody InquiryCnaeRequest inquiryCnaeRequest, HttpServletRequest request) 
{  

InquiryCnaeResponse response = new InquiryCnaeResponse();
try
{ 
setUserContext(inquiryCnaeRequest, request);

response = getCnaeBCF().fetchAllCnae(inquiryCnaeRequest);

}
catch (Exception e) 
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 

/**  
* Fetch filial by id. 
* 
* @param filialRequest the filial request   
* @return the filial response   
*/    

@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody CnaeRequest CNAERequest, HttpServletRequest request)   
{ 

CnaeResponse response = new CnaeResponse();
try   
{  
setUserContext(CNAERequest, request);

response = getCnaeBCF().fetchCnaeById(CNAERequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
package com.sensus.mlc.bairro.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.bairro.model.Bairro;


public class BairroResponse extends Response

    
    private Integer parentRetry;
    
    private List<Bairro> bairros ;
}
package com.sensus.mlc.wui.bairro;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.bairro.bcf.IBairroBCF;
import com.sensus.mlc.bairro.model.request.BairroRequest;
import com.sensus.mlc.bairro.model.request.InquiryBairroRequest;
import com.sensus.mlc.bairro.model.response.BairroResponse;
import com.sensus.mlc.bairro.model.response.InquiryBairroResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/bairro") 
public class BairroAPIController extends BaseController  
{ 

/* 
* URLs Mapping   
*/  
/** The Constant MAP_FETCH. */ 
private static final String MAP_FETCH = "/fetch";

private static final String MAP_FETCHALL = "/fetchall";

/** The Constant MAP_DELETE. */ 
private static final String MAP_DELETE = "/delete";

/** The Constant MAP_INSERT. */  
private static final String MAP_INSERT = "/insert";

/** The Constant MAP_INSERT. */  
private static final String MAP_UPDATE = "/update";


private IBairroBCF bairroBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "BairroAPIController";



public IBairroBCF getBairroBCF() { 
return bairroBCF;
}  

public void setBairroBCF(IBairroBCF bairroBCF) {  
this.bairroBCF = bairroBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody BairroRequest bairroRequest, HttpServletRequest request) 
{ 

BairroResponse response = new BairroResponse();
try  
{ 
setUserContext(bairroRequest, request);

response = getBairroBCF().insertBairro(bairroRequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update bairro.  
*    
* @param bairroRequest the bairro request  
* @return the bairro response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updateBairro(@RequestBody BairroRequest bairroRequest, HttpServletRequest request) 
{ 

BairroResponse response = new BairroResponse();
try  
{ 
setUserContext(bairroRequest, request);

response = getBairroBCF().updateBairro(bairroRequest);

} 
catch (Exception e) 
{  
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

}  


/**  
* Delete filial. 
*      
* @param filialRequest the filial request 
* @return the filial response    
*/      

@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)  
@ResponseBody  
public Response deleteBairro(@RequestBody BairroRequest bairroRequest, HttpServletRequest request) 
{  

BairroResponse response = new BairroResponse();
try 
{ 
setUserContext(bairroRequest, request);

response = getBairroBCF().deleteBairro(bairroRequest);

} 
catch (Exception e) 
{ 
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
			new String[] {CONTROLLER_EXCEPTION_MSG});
}
return response;

}  

/** 
* Fetch all filial.  
*  
* @param inquiryfilialRequest the inquiryfilial request  
* @return the inquiry filial response  
*/ 

@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody InquiryBairroRequest inquiryBairroRequest, HttpServletRequest request) 
{  

InquiryBairroResponse response = new InquiryBairroResponse();
try
{ 
setUserContext(inquiryBairroRequest, request);

response = getBairroBCF().fetchAllBairro(inquiryBairroRequest);

}
catch (Exception e) 
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 

/**  
* Fetch filial by id. 
* 
* @param filialRequest the filial request   
* @return the filial response   
*/    

@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody BairroRequest bairroRequest, HttpServletRequest request)   
{ 

BairroResponse response = new BairroResponse();
try   
{  
setUserContext(bairroRequest, request);

response = getBairroBCF().fetchBairroById(bairroRequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
package com.sensus.mlc.MUNICIPIO.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.MUNICIPIO.model.Municipio;


public class MunicipioResponse extends Response

    
    private Integer parentRetry;
    
    private List<Municipio> municipios ;
}
package com.sensus.mlc.wui.MUNICIPIO;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.MUNICIPIO.bcf.IMunicipioBCF;
import com.sensus.mlc.MUNICIPIO.model.request.MunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.response.MunicipioResponse;
import com.sensus.mlc.MUNICIPIO.model.response.InquiryMunicipioResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/MUNICIPIO") 
public class MunicipioAPIController extends BaseController  
{ 

/* 
* URLs Mapping   
*/  
/** The Constant MAP_FETCH. */ 
private static final String MAP_FETCH = "/fetch";

private static final String MAP_FETCHALL = "/fetchall";

/** The Constant MAP_DELETE. */ 
private static final String MAP_DELETE = "/delete";

/** The Constant MAP_INSERT. */  
private static final String MAP_INSERT = "/insert";

/** The Constant MAP_INSERT. */  
private static final String MAP_UPDATE = "/update";


private IMunicipioBCF MUNICIPIOBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "MunicipioAPIController";



public IMunicipioBCF getMunicipioBCF() { 
return MUNICIPIOBCF;
}  

public void setMunicipioBCF(IMunicipioBCF MUNICIPIOBCF) {  
this.MUNICIPIOBCF = MUNICIPIOBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody MunicipioRequest MUNICIPIORequest, HttpServletRequest request) 
{ 

MunicipioResponse response = new MunicipioResponse();
try  
{ 
setUserContext(MUNICIPIORequest, request);

response = getMunicipioBCF().insertMunicipio(MUNICIPIORequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update MUNICIPIO.  
*    
* @param MUNICIPIORequest the MUNICIPIO request  
* @return the MUNICIPIO response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updateMunicipio(@RequestBody MunicipioRequest MUNICIPIORequest, HttpServletRequest request) 
{ 

MunicipioResponse response = new MunicipioResponse();
try  
{ 
setUserContext(MUNICIPIORequest, request);

response = getMunicipioBCF().updateMunicipio(MUNICIPIORequest);

} 
catch (Exception e) 
{  
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

}  


/**  
* Delete filial. 
*      
* @param filialRequest the filial request 
* @return the filial response    
*/      

@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)  
@ResponseBody  
public Response deleteMunicipio(@RequestBody MunicipioRequest MUNICIPIORequest, HttpServletRequest request) 
{  

MunicipioResponse response = new MunicipioResponse();
try 
{ 
setUserContext(MUNICIPIORequest, request);

response = getMunicipioBCF().deleteMunicipio(MUNICIPIORequest);

} 
catch (Exception e) 
{ 
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
			new String[] {CONTROLLER_EXCEPTION_MSG});
}
return response;

}  

/** 
* Fetch all filial.  
*  
* @param inquiryfilialRequest the inquiryfilial request  
* @return the inquiry filial response  
*/ 

@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody InquiryMunicipioRequest inquiryMunicipioRequest, HttpServletRequest request) 
{  

InquiryMunicipioResponse response = new InquiryMunicipioResponse();
try
{ 
setUserContext(inquiryMunicipioRequest, request);

response = getMunicipioBCF().fetchAllMunicipio(inquiryMunicipioRequest);

}
catch (Exception e) 
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 

/**  
* Fetch filial by id. 
* 
* @param filialRequest the filial request   
* @return the filial response   
*/    

@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody MunicipioRequest MUNICIPIORequest, HttpServletRequest request)   
{ 

MunicipioResponse response = new MunicipioResponse();
try   
{  
setUserContext(MUNICIPIORequest, request);

response = getMunicipioBCF().fetchMunicipioById(MUNICIPIORequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
package com.sensus.mlc.MUNICIPIO.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.MUNICIPIO.model.Municipio;


public class MunicipioResponse extends Response

    
    private Integer parentRetry;
    
    private List<Municipio> municipios ;
}
package com.sensus.mlc.wui.MUNICIPIO;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.MUNICIPIO.bcf.IMunicipioBCF;
import com.sensus.mlc.MUNICIPIO.model.request.MunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.response.MunicipioResponse;
import com.sensus.mlc.MUNICIPIO.model.response.InquiryMunicipioResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/MUNICIPIO") 
public class MunicipioAPIController extends BaseController  
{ 

/* 
* URLs Mapping   
*/  
/** The Constant MAP_FETCH. */ 
private static final String MAP_FETCH = "/fetch";

private static final String MAP_FETCHALL = "/fetchall";

/** The Constant MAP_DELETE. */ 
private static final String MAP_DELETE = "/delete";

/** The Constant MAP_INSERT. */  
private static final String MAP_INSERT = "/insert";

/** The Constant MAP_INSERT. */  
private static final String MAP_UPDATE = "/update";


private IMunicipioBCF MUNICIPIOBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "MunicipioAPIController";



public IMunicipioBCF getMunicipioBCF() { 
return MUNICIPIOBCF;
}  

public void setMunicipioBCF(IMunicipioBCF MUNICIPIOBCF) {  
this.MUNICIPIOBCF = MUNICIPIOBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody MunicipioRequest MUNICIPIORequest, HttpServletRequest request) 
{ 

MunicipioResponse response = new MunicipioResponse();
try  
{ 
setUserContext(MUNICIPIORequest, request);

response = getMunicipioBCF().insertMunicipio(MUNICIPIORequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update MUNICIPIO.  
*    
* @param MUNICIPIORequest the MUNICIPIO request  
* @return the MUNICIPIO response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updateMunicipio(@RequestBody MunicipioRequest MUNICIPIORequest, HttpServletRequest request) 
{ 

MunicipioResponse response = new MunicipioResponse();
try  
{ 
setUserContext(MUNICIPIORequest, request);

response = getMunicipioBCF().updateMunicipio(MUNICIPIORequest);

} 
catch (Exception e) 
{  
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

}  


/**  
* Delete filial. 
*      
* @param filialRequest the filial request 
* @return the filial response    
*/      

@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)  
@ResponseBody  
public Response deleteMunicipio(@RequestBody MunicipioRequest MUNICIPIORequest, HttpServletRequest request) 
{  

MunicipioResponse response = new MunicipioResponse();
try 
{ 
setUserContext(MUNICIPIORequest, request);

response = getMunicipioBCF().deleteMunicipio(MUNICIPIORequest);

} 
catch (Exception e) 
{ 
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
			new String[] {CONTROLLER_EXCEPTION_MSG});
}
return response;

}  

/** 
* Fetch all filial.  
*  
* @param inquiryfilialRequest the inquiryfilial request  
* @return the inquiry filial response  
*/ 

@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody InquiryMunicipioRequest inquiryMunicipioRequest, HttpServletRequest request) 
{  

InquiryMunicipioResponse response = new InquiryMunicipioResponse();
try
{ 
setUserContext(inquiryMunicipioRequest, request);

response = getMunicipioBCF().fetchAllMunicipio(inquiryMunicipioRequest);

}
catch (Exception e) 
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 

/**  
* Fetch filial by id. 
* 
* @param filialRequest the filial request   
* @return the filial response   
*/    

@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody MunicipioRequest MUNICIPIORequest, HttpServletRequest request)   
{ 

MunicipioResponse response = new MunicipioResponse();
try   
{  
setUserContext(MUNICIPIORequest, request);

response = getMunicipioBCF().fetchMunicipioById(MUNICIPIORequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
package com.sensus.mlc.UF.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.UF.model.Uf;


public class UfResponse extends Response

    
    private Integer parentRetry;
    
    private List<Uf> ufs ;
}
package com.sensus.mlc.wui.UF;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.UF.bcf.IUfBCF;
import com.sensus.mlc.UF.model.request.UfRequest;
import com.sensus.mlc.UF.model.request.InquiryUfRequest;
import com.sensus.mlc.UF.model.response.UfResponse;
import com.sensus.mlc.UF.model.response.InquiryUfResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/UF") 
public class UfAPIController extends BaseController  
{ 

/* 
* URLs Mapping   
*/  
/** The Constant MAP_FETCH. */ 
private static final String MAP_FETCH = "/fetch";

private static final String MAP_FETCHALL = "/fetchall";

/** The Constant MAP_DELETE. */ 
private static final String MAP_DELETE = "/delete";

/** The Constant MAP_INSERT. */  
private static final String MAP_INSERT = "/insert";

/** The Constant MAP_INSERT. */  
private static final String MAP_UPDATE = "/update";


private IUfBCF UFBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "UfAPIController";



public IUfBCF getUfBCF() { 
return UFBCF;
}  

public void setUfBCF(IUfBCF UFBCF) {  
this.UFBCF = UFBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody UfRequest UFRequest, HttpServletRequest request) 
{ 

UfResponse response = new UfResponse();
try  
{ 
setUserContext(UFRequest, request);

response = getUfBCF().insertUf(UFRequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update UF.  
*    
* @param UFRequest the UF request  
* @return the UF response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updateUf(@RequestBody UfRequest UFRequest, HttpServletRequest request) 
{ 

UfResponse response = new UfResponse();
try  
{ 
setUserContext(UFRequest, request);

response = getUfBCF().updateUf(UFRequest);

} 
catch (Exception e) 
{  
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

}  


/**  
* Delete filial. 
*      
* @param filialRequest the filial request 
* @return the filial response    
*/      

@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)  
@ResponseBody  
public Response deleteUf(@RequestBody UfRequest UFRequest, HttpServletRequest request) 
{  

UfResponse response = new UfResponse();
try 
{ 
setUserContext(UFRequest, request);

response = getUfBCF().deleteUf(UFRequest);

} 
catch (Exception e) 
{ 
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
			new String[] {CONTROLLER_EXCEPTION_MSG});
}
return response;

}  

/** 
* Fetch all filial.  
*  
* @param inquiryfilialRequest the inquiryfilial request  
* @return the inquiry filial response  
*/ 

@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody InquiryUfRequest inquiryUfRequest, HttpServletRequest request) 
{  

InquiryUfResponse response = new InquiryUfResponse();
try
{ 
setUserContext(inquiryUfRequest, request);

response = getUfBCF().fetchAllUf(inquiryUfRequest);

}
catch (Exception e) 
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 

/**  
* Fetch filial by id. 
* 
* @param filialRequest the filial request   
* @return the filial response   
*/    

@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody UfRequest UFRequest, HttpServletRequest request)   
{ 

UfResponse response = new UfResponse();
try   
{  
setUserContext(UFRequest, request);

response = getUfBCF().fetchUfById(UFRequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
package com.sensus.mlc.CNAE.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.CNAE.model.Cnae;


public class CnaeResponse extends Response

    
    private Integer parentRetry;
    
    private List<Cnae> cnaes ;
}
package com.sensus.mlc.wui.CNAE;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.CNAE.bcf.ICnaeBCF;
import com.sensus.mlc.CNAE.model.request.CnaeRequest;
import com.sensus.mlc.CNAE.model.request.InquiryCnaeRequest;
import com.sensus.mlc.CNAE.model.response.CnaeResponse;
import com.sensus.mlc.CNAE.model.response.InquiryCnaeResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/CNAE") 
public class CnaeAPIController extends BaseController  
{ 

/* 
* URLs Mapping   
*/  
/** The Constant MAP_FETCH. */ 
private static final String MAP_FETCH = "/fetch";

private static final String MAP_FETCHALL = "/fetchall";

/** The Constant MAP_DELETE. */ 
private static final String MAP_DELETE = "/delete";

/** The Constant MAP_INSERT. */  
private static final String MAP_INSERT = "/insert";

/** The Constant MAP_INSERT. */  
private static final String MAP_UPDATE = "/update";


private ICnaeBCF CNAEBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "CnaeAPIController";



public ICnaeBCF getCnaeBCF() { 
return CNAEBCF;
}  

public void setCnaeBCF(ICnaeBCF CNAEBCF) {  
this.CNAEBCF = CNAEBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody CnaeRequest CNAERequest, HttpServletRequest request) 
{ 

CnaeResponse response = new CnaeResponse();
try  
{ 
setUserContext(CNAERequest, request);

response = getCnaeBCF().insertCnae(CNAERequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update CNAE.  
*    
* @param CNAERequest the CNAE request  
* @return the CNAE response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updateCnae(@RequestBody CnaeRequest CNAERequest, HttpServletRequest request) 
{ 

CnaeResponse response = new CnaeResponse();
try  
{ 
setUserContext(CNAERequest, request);

response = getCnaeBCF().updateCnae(CNAERequest);

} 
catch (Exception e) 
{  
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

}  


/**  
* Delete filial. 
*      
* @param filialRequest the filial request 
* @return the filial response    
*/      

@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)  
@ResponseBody  
public Response deleteCnae(@RequestBody CnaeRequest CNAERequest, HttpServletRequest request) 
{  

CnaeResponse response = new CnaeResponse();
try 
{ 
setUserContext(CNAERequest, request);

response = getCnaeBCF().deleteCnae(CNAERequest);

} 
catch (Exception e) 
{ 
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
			new String[] {CONTROLLER_EXCEPTION_MSG});
}
return response;

}  

/** 
* Fetch all filial.  
*  
* @param inquiryfilialRequest the inquiryfilial request  
* @return the inquiry filial response  
*/ 

@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody InquiryCnaeRequest inquiryCnaeRequest, HttpServletRequest request) 
{  

InquiryCnaeResponse response = new InquiryCnaeResponse();
try
{ 
setUserContext(inquiryCnaeRequest, request);

response = getCnaeBCF().fetchAllCnae(inquiryCnaeRequest);

}
catch (Exception e) 
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 

/**  
* Fetch filial by id. 
* 
* @param filialRequest the filial request   
* @return the filial response   
*/    

@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody CnaeRequest CNAERequest, HttpServletRequest request)   
{ 

CnaeResponse response = new CnaeResponse();
try   
{  
setUserContext(CNAERequest, request);

response = getCnaeBCF().fetchCnaeById(CNAERequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
package com.sensus.mlc.BAIRRO.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.BAIRRO.model.Bairro;


public class BairroResponse extends Response

    
    private Integer parentRetry;
    
    private List<Bairro> bairros ;
}
package com.sensus.mlc.wui.BAIRRO;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.BAIRRO.bcf.IBairroBCF;
import com.sensus.mlc.BAIRRO.model.request.BairroRequest;
import com.sensus.mlc.BAIRRO.model.request.InquiryBairroRequest;
import com.sensus.mlc.BAIRRO.model.response.BairroResponse;
import com.sensus.mlc.BAIRRO.model.response.InquiryBairroResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/BAIRRO") 
public class BairroAPIController extends BaseController  
{ 

/* 
* URLs Mapping   
*/  
/** The Constant MAP_FETCH. */ 
private static final String MAP_FETCH = "/fetch";

private static final String MAP_FETCHALL = "/fetchall";

/** The Constant MAP_DELETE. */ 
private static final String MAP_DELETE = "/delete";

/** The Constant MAP_INSERT. */  
private static final String MAP_INSERT = "/insert";

/** The Constant MAP_INSERT. */  
private static final String MAP_UPDATE = "/update";


private IBairroBCF BAIRROBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "BairroAPIController";



public IBairroBCF getBairroBCF() { 
return BAIRROBCF;
}  

public void setBairroBCF(IBairroBCF BAIRROBCF) {  
this.BAIRROBCF = BAIRROBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody BairroRequest BAIRRORequest, HttpServletRequest request) 
{ 

BairroResponse response = new BairroResponse();
try  
{ 
setUserContext(BAIRRORequest, request);

response = getBairroBCF().insertBairro(BAIRRORequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update BAIRRO.  
*    
* @param BAIRRORequest the BAIRRO request  
* @return the BAIRRO response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updateBairro(@RequestBody BairroRequest BAIRRORequest, HttpServletRequest request) 
{ 

BairroResponse response = new BairroResponse();
try  
{ 
setUserContext(BAIRRORequest, request);

response = getBairroBCF().updateBairro(BAIRRORequest);

} 
catch (Exception e) 
{  
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

}  


/**  
* Delete filial. 
*      
* @param filialRequest the filial request 
* @return the filial response    
*/      

@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)  
@ResponseBody  
public Response deleteBairro(@RequestBody BairroRequest BAIRRORequest, HttpServletRequest request) 
{  

BairroResponse response = new BairroResponse();
try 
{ 
setUserContext(BAIRRORequest, request);

response = getBairroBCF().deleteBairro(BAIRRORequest);

} 
catch (Exception e) 
{ 
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
			new String[] {CONTROLLER_EXCEPTION_MSG});
}
return response;

}  

/** 
* Fetch all filial.  
*  
* @param inquiryfilialRequest the inquiryfilial request  
* @return the inquiry filial response  
*/ 

@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody InquiryBairroRequest inquiryBairroRequest, HttpServletRequest request) 
{  

InquiryBairroResponse response = new InquiryBairroResponse();
try
{ 
setUserContext(inquiryBairroRequest, request);

response = getBairroBCF().fetchAllBairro(inquiryBairroRequest);

}
catch (Exception e) 
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 

/**  
* Fetch filial by id. 
* 
* @param filialRequest the filial request   
* @return the filial response   
*/    

@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody BairroRequest BAIRRORequest, HttpServletRequest request)   
{ 

BairroResponse response = new BairroResponse();
try   
{  
setUserContext(BAIRRORequest, request);

response = getBairroBCF().fetchBairroById(BAIRRORequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
package com.sensus.mlc.MUNICIPIO.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.MUNICIPIO.model.Municipio;


public class MunicipioResponse extends Response

    
    private Integer parentRetry;
    
    private List<Municipio> municipios ;
}
package com.sensus.mlc.wui.MUNICIPIO;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.MUNICIPIO.bcf.IMunicipioBCF;
import com.sensus.mlc.MUNICIPIO.model.request.MunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.response.MunicipioResponse;
import com.sensus.mlc.MUNICIPIO.model.response.InquiryMunicipioResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/MUNICIPIO") 
public class MunicipioAPIController extends BaseController  
{ 

/* 
* URLs Mapping   
*/  
/** The Constant MAP_FETCH. */ 
private static final String MAP_FETCH = "/fetch";

private static final String MAP_FETCHALL = "/fetchall";

/** The Constant MAP_DELETE. */ 
private static final String MAP_DELETE = "/delete";

/** The Constant MAP_INSERT. */  
private static final String MAP_INSERT = "/insert";

/** The Constant MAP_INSERT. */  
private static final String MAP_UPDATE = "/update";


private IMunicipioBCF MUNICIPIOBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "MunicipioAPIController";



public IMunicipioBCF getMunicipioBCF() { 
return MUNICIPIOBCF;
}  

public void setMunicipioBCF(IMunicipioBCF MUNICIPIOBCF) {  
this.MUNICIPIOBCF = MUNICIPIOBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody MunicipioRequest MUNICIPIORequest, HttpServletRequest request) 
{ 

MunicipioResponse response = new MunicipioResponse();
try  
{ 
setUserContext(MUNICIPIORequest, request);

response = getMunicipioBCF().insertMunicipio(MUNICIPIORequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update MUNICIPIO.  
*    
* @param MUNICIPIORequest the MUNICIPIO request  
* @return the MUNICIPIO response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updateMunicipio(@RequestBody MunicipioRequest MUNICIPIORequest, HttpServletRequest request) 
{ 

MunicipioResponse response = new MunicipioResponse();
try  
{ 
setUserContext(MUNICIPIORequest, request);

response = getMunicipioBCF().updateMunicipio(MUNICIPIORequest);

} 
catch (Exception e) 
{  
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

}  


/**  
* Delete filial. 
*      
* @param filialRequest the filial request 
* @return the filial response    
*/      

@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)  
@ResponseBody  
public Response deleteMunicipio(@RequestBody MunicipioRequest MUNICIPIORequest, HttpServletRequest request) 
{  

MunicipioResponse response = new MunicipioResponse();
try 
{ 
setUserContext(MUNICIPIORequest, request);

response = getMunicipioBCF().deleteMunicipio(MUNICIPIORequest);

} 
catch (Exception e) 
{ 
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
			new String[] {CONTROLLER_EXCEPTION_MSG});
}
return response;

}  

/** 
* Fetch all filial.  
*  
* @param inquiryfilialRequest the inquiryfilial request  
* @return the inquiry filial response  
*/ 

@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody InquiryMunicipioRequest inquiryMunicipioRequest, HttpServletRequest request) 
{  

InquiryMunicipioResponse response = new InquiryMunicipioResponse();
try
{ 
setUserContext(inquiryMunicipioRequest, request);

response = getMunicipioBCF().fetchAllMunicipio(inquiryMunicipioRequest);

}
catch (Exception e) 
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 

/**  
* Fetch filial by id. 
* 
* @param filialRequest the filial request   
* @return the filial response   
*/    

@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody MunicipioRequest MUNICIPIORequest, HttpServletRequest request)   
{ 

MunicipioResponse response = new MunicipioResponse();
try   
{  
setUserContext(MUNICIPIORequest, request);

response = getMunicipioBCF().fetchMunicipioById(MUNICIPIORequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
package com.sensus.mlc.UF.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.UF.model.Uf;


public class UfResponse extends Response

    
    private Integer parentRetry;
    
    private List<Uf> ufs ;
}
package com.sensus.mlc.wui.UF;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.UF.bcf.IUfBCF;
import com.sensus.mlc.UF.model.request.UfRequest;
import com.sensus.mlc.UF.model.request.InquiryUfRequest;
import com.sensus.mlc.UF.model.response.UfResponse;
import com.sensus.mlc.UF.model.response.InquiryUfResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/UF") 
public class UfAPIController extends BaseController  
{ 

/* 
* URLs Mapping   
*/  
/** The Constant MAP_FETCH. */ 
private static final String MAP_FETCH = "/fetch";

private static final String MAP_FETCHALL = "/fetchall";

/** The Constant MAP_DELETE. */ 
private static final String MAP_DELETE = "/delete";

/** The Constant MAP_INSERT. */  
private static final String MAP_INSERT = "/insert";

/** The Constant MAP_INSERT. */  
private static final String MAP_UPDATE = "/update";


private IUfBCF UFBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "UfAPIController";



public IUfBCF getUfBCF() { 
return UFBCF;
}  

public void setUfBCF(IUfBCF UFBCF) {  
this.UFBCF = UFBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody UfRequest UFRequest, HttpServletRequest request) 
{ 

UfResponse response = new UfResponse();
try  
{ 
setUserContext(UFRequest, request);

response = getUfBCF().insertUf(UFRequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update UF.  
*    
* @param UFRequest the UF request  
* @return the UF response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updateUf(@RequestBody UfRequest UFRequest, HttpServletRequest request) 
{ 

UfResponse response = new UfResponse();
try  
{ 
setUserContext(UFRequest, request);

response = getUfBCF().updateUf(UFRequest);

} 
catch (Exception e) 
{  
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

}  


/**  
* Delete filial. 
*      
* @param filialRequest the filial request 
* @return the filial response    
*/      

@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)  
@ResponseBody  
public Response deleteUf(@RequestBody UfRequest UFRequest, HttpServletRequest request) 
{  

UfResponse response = new UfResponse();
try 
{ 
setUserContext(UFRequest, request);

response = getUfBCF().deleteUf(UFRequest);

} 
catch (Exception e) 
{ 
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
			new String[] {CONTROLLER_EXCEPTION_MSG});
}
return response;

}  

/** 
* Fetch all filial.  
*  
* @param inquiryfilialRequest the inquiryfilial request  
* @return the inquiry filial response  
*/ 

@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody InquiryUfRequest inquiryUfRequest, HttpServletRequest request) 
{  

InquiryUfResponse response = new InquiryUfResponse();
try
{ 
setUserContext(inquiryUfRequest, request);

response = getUfBCF().fetchAllUf(inquiryUfRequest);

}
catch (Exception e) 
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 

/**  
* Fetch filial by id. 
* 
* @param filialRequest the filial request   
* @return the filial response   
*/    

@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody UfRequest UFRequest, HttpServletRequest request)   
{ 

UfResponse response = new UfResponse();
try   
{  
setUserContext(UFRequest, request);

response = getUfBCF().fetchUfById(UFRequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
package com.sensus.mlc.PAIS.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.PAIS.model.Pais;


public class PaisResponse extends Response

    
    private Integer parentRetry;
    
    private List<Pais> paiss ;
}
package com.sensus.mlc.wui.PAIS;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.PAIS.bcf.IPaisBCF;
import com.sensus.mlc.PAIS.model.request.PaisRequest;
import com.sensus.mlc.PAIS.model.request.InquiryPaisRequest;
import com.sensus.mlc.PAIS.model.response.PaisResponse;
import com.sensus.mlc.PAIS.model.response.InquiryPaisResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/PAIS") 
public class PaisAPIController extends BaseController  
{ 

/* 
* URLs Mapping   
*/  
/** The Constant MAP_FETCH. */ 
private static final String MAP_FETCH = "/fetch";

private static final String MAP_FETCHALL = "/fetchall";

/** The Constant MAP_DELETE. */ 
private static final String MAP_DELETE = "/delete";

/** The Constant MAP_INSERT. */  
private static final String MAP_INSERT = "/insert";

/** The Constant MAP_INSERT. */  
private static final String MAP_UPDATE = "/update";


private IPaisBCF PAISBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "PaisAPIController";



public IPaisBCF getPaisBCF() { 
return PAISBCF;
}  

public void setPaisBCF(IPaisBCF PAISBCF) {  
this.PAISBCF = PAISBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody PaisRequest PAISRequest, HttpServletRequest request) 
{ 

PaisResponse response = new PaisResponse();
try  
{ 
setUserContext(PAISRequest, request);

response = getPaisBCF().insertPais(PAISRequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update PAIS.  
*    
* @param PAISRequest the PAIS request  
* @return the PAIS response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updatePais(@RequestBody PaisRequest PAISRequest, HttpServletRequest request) 
{ 

PaisResponse response = new PaisResponse();
try  
{ 
setUserContext(PAISRequest, request);

response = getPaisBCF().updatePais(PAISRequest);

} 
catch (Exception e) 
{  
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

}  


/**  
* Delete filial. 
*      
* @param filialRequest the filial request 
* @return the filial response    
*/      

@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)  
@ResponseBody  
public Response deletePais(@RequestBody PaisRequest PAISRequest, HttpServletRequest request) 
{  

PaisResponse response = new PaisResponse();
try 
{ 
setUserContext(PAISRequest, request);

response = getPaisBCF().deletePais(PAISRequest);

} 
catch (Exception e) 
{ 
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
			new String[] {CONTROLLER_EXCEPTION_MSG});
}
return response;

}  

/** 
* Fetch all filial.  
*  
* @param inquiryfilialRequest the inquiryfilial request  
* @return the inquiry filial response  
*/ 

@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody InquiryPaisRequest inquiryPaisRequest, HttpServletRequest request) 
{  

InquiryPaisResponse response = new InquiryPaisResponse();
try
{ 
setUserContext(inquiryPaisRequest, request);

response = getPaisBCF().fetchAllPais(inquiryPaisRequest);

}
catch (Exception e) 
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 

/**  
* Fetch filial by id. 
* 
* @param filialRequest the filial request   
* @return the filial response   
*/    

@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody PaisRequest PAISRequest, HttpServletRequest request)   
{ 

PaisResponse response = new PaisResponse();
try   
{  
setUserContext(PAISRequest, request);

response = getPaisBCF().fetchPaisById(PAISRequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
package com.sensus.mlc.Eventos.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.Eventos.model.Eventos;


public class EventosResponse extends Response

    
    private Integer parentRetry;
    
    private List<Eventos> eventoss ;
}
package com.sensus.mlc.wui.Eventos;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.Eventos.bcf.IEventosBCF;
import com.sensus.mlc.Eventos.model.request.EventosRequest;
import com.sensus.mlc.Eventos.model.request.InquiryEventosRequest;
import com.sensus.mlc.Eventos.model.response.EventosResponse;
import com.sensus.mlc.Eventos.model.response.InquiryEventosResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/Eventos") 
public class EventosAPIController extends BaseController  
{ 

/* 
* URLs Mapping   
*/  
/** The Constant MAP_FETCH. */ 
private static final String MAP_FETCH = "/fetch";

private static final String MAP_FETCHALL = "/fetchall";

/** The Constant MAP_DELETE. */ 
private static final String MAP_DELETE = "/delete";

/** The Constant MAP_INSERT. */  
private static final String MAP_INSERT = "/insert";

/** The Constant MAP_INSERT. */  
private static final String MAP_UPDATE = "/update";


private IEventosBCF EventosBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "EventosAPIController";



public IEventosBCF getEventosBCF() { 
return EventosBCF;
}  

public void setEventosBCF(IEventosBCF EventosBCF) {  
this.EventosBCF = EventosBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody EventosRequest EventosRequest, HttpServletRequest request) 
{ 

EventosResponse response = new EventosResponse();
try  
{ 
setUserContext(EventosRequest, request);

response = getEventosBCF().insertEventos(EventosRequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update Eventos.  
*    
* @param EventosRequest the Eventos request  
* @return the Eventos response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updateEventos(@RequestBody EventosRequest EventosRequest, HttpServletRequest request) 
{ 

EventosResponse response = new EventosResponse();
try  
{ 
setUserContext(EventosRequest, request);

response = getEventosBCF().updateEventos(EventosRequest);

} 
catch (Exception e) 
{  
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

}  


/**  
* Delete filial. 
*      
* @param filialRequest the filial request 
* @return the filial response    
*/      

@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)  
@ResponseBody  
public Response deleteEventos(@RequestBody EventosRequest EventosRequest, HttpServletRequest request) 
{  

EventosResponse response = new EventosResponse();
try 
{ 
setUserContext(EventosRequest, request);

response = getEventosBCF().deleteEventos(EventosRequest);

} 
catch (Exception e) 
{ 
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
			new String[] {CONTROLLER_EXCEPTION_MSG});
}
return response;

}  

/** 
* Fetch all filial.  
*  
* @param inquiryfilialRequest the inquiryfilial request  
* @return the inquiry filial response  
*/ 

@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody InquiryEventosRequest inquiryEventosRequest, HttpServletRequest request) 
{  

InquiryEventosResponse response = new InquiryEventosResponse();
try
{ 
setUserContext(inquiryEventosRequest, request);

response = getEventosBCF().fetchAllEventos(inquiryEventosRequest);

}
catch (Exception e) 
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 

/**  
* Fetch filial by id. 
* 
* @param filialRequest the filial request   
* @return the filial response   
*/    

@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST) 
@ResponseBody 
public Response fetch(@RequestBody EventosRequest EventosRequest, HttpServletRequest request)   
{ 

EventosResponse response = new EventosResponse();
try   
{  
setUserContext(EventosRequest, request);

response = getEventosBCF().fetchEventosById(EventosRequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
