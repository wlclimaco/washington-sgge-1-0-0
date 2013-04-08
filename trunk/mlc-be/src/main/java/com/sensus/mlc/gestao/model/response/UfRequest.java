package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.uf.model.Uf;


public class UfRequest extends LightSelectionRequest
{
    
    private Integer parentRetry;
    
    private Uf  uf;
}
