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
