package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.filial.model.Filial;


public class FilialResponse extends Response
{
    
    private Integer parentRetry;
    
    private List<Filial> filial ;
}
