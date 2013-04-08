package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tipcliente.model.Tipcliente;


public class TipclienteRequest extends LightSelectionRequest
{
    
    private Integer parentRetry;
    
    private Tipcliente  tipcliente;
}
