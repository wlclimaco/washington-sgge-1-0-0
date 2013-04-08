package com.sensus.mlc.tipcliente.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.tipcliente.model.Tipcliente;


public class TipclienteResponse extends Response
{
    
    private Integer parentRetry;
    
    private List<Tipcliente> tipcliente ;
}
