Memo1
package com.sensus.mlc.UnidMed.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.UnidMed.model.Unidmed;


public class UnidmedResponse extends Response

    
    private Integer parentRetry;
    
    private List<Unidmed> unidmeds ;
}
package com.sensus.mlc.wui.UnidMed;
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
import com.sensus.mlc.UnidMed.bcf.IUnidmedBCF;
import com.sensus.mlc.UnidMed.model.request.UnidmedRequest;
import com.sensus.mlc.UnidMed.model.request.InquiryUnidmedRequest;
import com.sensus.mlc.UnidMed.model.response.UnidmedResponse;
import com.sensus.mlc.UnidMed.model.response.InquiryUnidmedResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/UnidMed") 
public class UnidmedAPIController extends BaseController  
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


private IUnidmedBCF UnidMedBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "UnidmedAPIController";



public IUnidmedBCF getUnidmedBCF() { 
return UnidMedBCF;
}  

public void setUnidmedBCF(IUnidmedBCF UnidMedBCF) {  
this.UnidMedBCF = UnidMedBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody UnidmedRequest UnidMedRequest, HttpServletRequest request) 
{ 

UnidmedResponse response = new UnidmedResponse();
try  
{ 
setUserContext(UnidMedRequest, request);

response = getUnidmedBCF().insertUnidmed(UnidMedRequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update UnidMed.  
*    
* @param UnidMedRequest the UnidMed request  
* @return the UnidMed response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updateUnidmed(@RequestBody UnidmedRequest UnidMedRequest, HttpServletRequest request) 
{ 

UnidmedResponse response = new UnidmedResponse();
try  
{ 
setUserContext(UnidMedRequest, request);

response = getUnidmedBCF().updateUnidmed(UnidMedRequest);

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
public Response deleteUnidmed(@RequestBody UnidmedRequest UnidMedRequest, HttpServletRequest request) 
{  

UnidmedResponse response = new UnidmedResponse();
try 
{ 
setUserContext(UnidMedRequest, request);

response = getUnidmedBCF().deleteUnidmed(UnidMedRequest);

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
public Response fetch(@RequestBody InquiryUnidmedRequest inquiryUnidmedRequest, HttpServletRequest request) 
{  

InquiryUnidmedResponse response = new InquiryUnidmedResponse();
try
{ 
setUserContext(inquiryUnidmedRequest, request);

response = getUnidmedBCF().fetchAllUnidmed(inquiryUnidmedRequest);

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
public Response fetch(@RequestBody UnidmedRequest UnidMedRequest, HttpServletRequest request)   
{ 

UnidmedResponse response = new UnidmedResponse();
try   
{  
setUserContext(UnidMedRequest, request);

response = getUnidmedBCF().fetchUnidmedById(UnidMedRequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
package com.sensus.mlc.ClassTitulares.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.ClassTitulares.model.Classtitulares;


public class ClasstitularesResponse extends Response

    
    private Integer parentRetry;
    
    private List<Classtitulares> classtitularess ;
}
package com.sensus.mlc.wui.ClassTitulares;
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
import com.sensus.mlc.ClassTitulares.bcf.IClasstitularesBCF;
import com.sensus.mlc.ClassTitulares.model.request.ClasstitularesRequest;
import com.sensus.mlc.ClassTitulares.model.request.InquiryClasstitularesRequest;
import com.sensus.mlc.ClassTitulares.model.response.ClasstitularesResponse;
import com.sensus.mlc.ClassTitulares.model.response.InquiryClasstitularesResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/ClassTitulares") 
public class ClasstitularesAPIController extends BaseController  
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


private IClasstitularesBCF ClassTitularesBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "ClasstitularesAPIController";



public IClasstitularesBCF getClasstitularesBCF() { 
return ClassTitularesBCF;
}  

public void setClasstitularesBCF(IClasstitularesBCF ClassTitularesBCF) {  
this.ClassTitularesBCF = ClassTitularesBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody ClasstitularesRequest ClassTitularesRequest, HttpServletRequest request) 
{ 

ClasstitularesResponse response = new ClasstitularesResponse();
try  
{ 
setUserContext(ClassTitularesRequest, request);

response = getClasstitularesBCF().insertClasstitulares(ClassTitularesRequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update ClassTitulares.  
*    
* @param ClassTitularesRequest the ClassTitulares request  
* @return the ClassTitulares response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updateClasstitulares(@RequestBody ClasstitularesRequest ClassTitularesRequest, HttpServletRequest request) 
{ 

ClasstitularesResponse response = new ClasstitularesResponse();
try  
{ 
setUserContext(ClassTitularesRequest, request);

response = getClasstitularesBCF().updateClasstitulares(ClassTitularesRequest);

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
public Response deleteClasstitulares(@RequestBody ClasstitularesRequest ClassTitularesRequest, HttpServletRequest request) 
{  

ClasstitularesResponse response = new ClasstitularesResponse();
try 
{ 
setUserContext(ClassTitularesRequest, request);

response = getClasstitularesBCF().deleteClasstitulares(ClassTitularesRequest);

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
public Response fetch(@RequestBody InquiryClasstitularesRequest inquiryClasstitularesRequest, HttpServletRequest request) 
{  

InquiryClasstitularesResponse response = new InquiryClasstitularesResponse();
try
{ 
setUserContext(inquiryClasstitularesRequest, request);

response = getClasstitularesBCF().fetchAllClasstitulares(inquiryClasstitularesRequest);

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
public Response fetch(@RequestBody ClasstitularesRequest ClassTitularesRequest, HttpServletRequest request)   
{ 

ClasstitularesResponse response = new ClasstitularesResponse();
try   
{  
setUserContext(ClassTitularesRequest, request);

response = getClasstitularesBCF().fetchClasstitularesById(ClassTitularesRequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
package com.sensus.mlc.TipoTitulares.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.TipoTitulares.model.Tipotitulares;


public class TipotitularesResponse extends Response

    
    private Integer parentRetry;
    
    private List<Tipotitulares> tipotitularess ;
}
package com.sensus.mlc.wui.TipoTitulares;
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
import com.sensus.mlc.TipoTitulares.bcf.ITipotitularesBCF;
import com.sensus.mlc.TipoTitulares.model.request.TipotitularesRequest;
import com.sensus.mlc.TipoTitulares.model.request.InquiryTipotitularesRequest;
import com.sensus.mlc.TipoTitulares.model.response.TipotitularesResponse;
import com.sensus.mlc.TipoTitulares.model.response.InquiryTipotitularesResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/** 
* @author QATEmployee 
* 
*/ 
@Controller
@RequestMapping("/api/TipoTitulares") 
public class TipotitularesAPIController extends BaseController  
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


private ITipotitularesBCF TipoTitularesBCF;

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

/** The Constant CONTROLLER_EXCEPTION_MSG. */  
public static final String CONTROLLER_EXCEPTION_MSG = "TipotitularesAPIController";



public ITipotitularesBCF getTipotitularesBCF() { 
return TipoTitularesBCF;
}  

public void setTipotitularesBCF(ITipotitularesBCF TipoTitularesBCF) {  
this.TipoTitularesBCF = TipoTitularesBCF;
}






@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) 
@ResponseBody   
public Response insert(@RequestBody TipotitularesRequest TipoTitularesRequest, HttpServletRequest request) 
{ 

TipotitularesResponse response = new TipotitularesResponse();
try  
{ 
setUserContext(TipoTitularesRequest, request);

response = getTipotitularesBCF().insertTipotitulares(TipoTitularesRequest);

}  
catch (Exception e)
{   
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
}  
return response;

} 



/**  
* Update TipoTitulares.  
*    
* @param TipoTitularesRequest the TipoTitulares request  
* @return the TipoTitulares response  
*/  

@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  
@ResponseBody  
public Response updateTipotitulares(@RequestBody TipotitularesRequest TipoTitularesRequest, HttpServletRequest request) 
{ 

TipotitularesResponse response = new TipotitularesResponse();
try  
{ 
setUserContext(TipoTitularesRequest, request);

response = getTipotitularesBCF().updateTipotitulares(TipoTitularesRequest);

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
public Response deleteTipotitulares(@RequestBody TipotitularesRequest TipoTitularesRequest, HttpServletRequest request) 
{  

TipotitularesResponse response = new TipotitularesResponse();
try 
{ 
setUserContext(TipoTitularesRequest, request);

response = getTipotitularesBCF().deleteTipotitulares(TipoTitularesRequest);

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
public Response fetch(@RequestBody InquiryTipotitularesRequest inquiryTipotitularesRequest, HttpServletRequest request) 
{  

InquiryTipotitularesResponse response = new InquiryTipotitularesResponse();
try
{ 
setUserContext(inquiryTipotitularesRequest, request);

response = getTipotitularesBCF().fetchAllTipotitulares(inquiryTipotitularesRequest);

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
public Response fetch(@RequestBody TipotitularesRequest TipoTitularesRequest, HttpServletRequest request)   
{ 

TipotitularesResponse response = new TipotitularesResponse();
try   
{  
setUserContext(TipoTitularesRequest, request);

response = getTipotitularesBCF().fetchTipotitularesById(TipoTitularesRequest);

} 
catch (Exception e)
{
SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  
			new String[] {CONTROLLER_EXCEPTION_MSG});
} 
return response;

} 



}  
